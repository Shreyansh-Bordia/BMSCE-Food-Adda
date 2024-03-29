package com.example.bmscefoodadda.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bmscefoodadda.databinding.MenuItemBinding
import com.example.bmscefoodadda.model.CartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class menuAdapter(private val menuItems: List<com.example.bmscefoodadda.model.MenuItem>) : RecyclerView.Adapter<menuAdapter.MenuViewHolder>() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            auth = FirebaseAuth.getInstance()
            binding.menuAddToCart.setOnClickListener {
                addItemToCart()
            }
        }

        private fun addItemToCart() {
            val database = FirebaseDatabase.getInstance().reference
            val userId = auth.currentUser?.uid?:""
            val menuItem = menuItems[absoluteAdapterPosition]
            val cartItem = CartItems(foodName = menuItem.foodName, foodPrice = menuItem.foodPrice,foodImage = menuItem.foodImage, foodQuantity = 1)
            database.child("appUser").child(userId).child("cartItems").push().setValue(cartItem).addOnSuccessListener {
                Toast.makeText(itemView.context,"This item added to cart Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(itemView.context,"Item not added",Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(position: Int) {
            val menuItem = menuItems[position]
            binding.apply {
                MenuFoodItem.text = menuItem.foodName
                MenuPrice.text=menuItem.foodPrice
                val Uri = Uri.parse(menuItem.foodImage)
                Glide.with(itemView.context).load(Uri).into(menuImage)
            }
        }
    }


}