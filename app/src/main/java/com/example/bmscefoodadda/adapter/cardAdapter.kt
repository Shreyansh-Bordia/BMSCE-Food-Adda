package com.example.bmscefoodadda.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bmscefoodadda.databinding.CartItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class cardAdapter(private val context: android.content.Context, private val CartItems:MutableList<String>, private val CartItemPrice:MutableList<String>, private val CartImage: MutableList<String>, private val CartQuantity:MutableList<Int>) : RecyclerView.Adapter<cardAdapter.CartViewHolder>(){

    private val auth = FirebaseAuth.getInstance()
    init {
        val database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid?:""
        val cartItemNumber = CartItems.size
        itemQuantity = IntArray(cartItemNumber){1}
        cartItemsReference = database.reference.child("appUser").child(userId).child("cartItems")
    }
    companion object{
        private var itemQuantity: IntArray = intArrayOf()
        private lateinit var cartItemsReference: DatabaseReference
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
       val binding=CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int = CartItems.size

    inner class CartViewHolder(private val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
           binding.apply {
               val quantity = itemQuantity[position]
               cartFoodName.text = CartItems[position]
               cartItemPrice.text = CartItemPrice[position]
               val uriString = CartImage[position]
               val uri = Uri.parse(uriString)
               Glide.with(itemView.context).load(uri).into(cartImage)
               cartItemQuantity.text = quantity.toString()

               minusButton.setOnClickListener {
                    decreaseQuantity(position)
               }
               plusButton.setOnClickListener {
                    increaseQuantity(position)
               }
               deleteButton.setOnClickListener {
                    val itemPosition = adapterPosition
                   if(itemPosition != RecyclerView.NO_POSITION){
                       deleteItem(itemPosition)
                   }
               }
           }
        }
        private fun deleteItem(position: Int){
            val positionRetrieve = position
            getUniqueKeyAtPosition(positionRetrieve){uniqueKey ->
                if(uniqueKey != null){
                    removeItem(position,uniqueKey)
                }
            }
        }

        private fun removeItem(position: Int, uniqueKey: String) {
            if(uniqueKey!= null){
                cartItemsReference.child(uniqueKey).removeValue().addOnSuccessListener {
                    CartItems.removeAt(position)
                    CartQuantity.removeAt(position)
                    CartImage.removeAt(position)
                    CartItemPrice.removeAt(position)
                    Toast.makeText(itemView.context, "Item removed successfully", Toast.LENGTH_SHORT).show()

                    itemQuantity = itemQuantity.filterIndexed { index, i -> index!=position }.toIntArray()
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,CartItems.size)
                }.addOnFailureListener {
                    Toast.makeText(itemView.context, "Failed to delete the item", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun getUniqueKeyAtPosition(positionRetrieve: Int, onComplete:(String?) -> Unit) {
            cartItemsReference.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var uniqueKey:String?=null
                    snapshot.children.forEachIndexed { index, dataSnapshot ->
                        if(index == positionRetrieve){
                            uniqueKey = dataSnapshot.key
                            return@forEachIndexed
                        }
                    }
                    onComplete(uniqueKey)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }

        private fun increaseQuantity(position: Int){
            if(itemQuantity[position]<10){
                itemQuantity[position]++
                binding.cartItemQuantity.text = itemQuantity[position].toString()
            }
        }
        private fun decreaseQuantity(position: Int){
            if(itemQuantity[position]>1){
                itemQuantity[position]--
                binding.cartItemQuantity.text = itemQuantity[position].toString()
            }
        }
    }
}