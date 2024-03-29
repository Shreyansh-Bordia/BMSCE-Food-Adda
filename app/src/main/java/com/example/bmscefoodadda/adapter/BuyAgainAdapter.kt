package com.example.bmscefoodadda.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bmscefoodadda.databinding.ActivitySplashScreenBinding
import com.example.bmscefoodadda.databinding.BuyAgainItemBinding

class BuyAgainAdapter(private val buyAgainFoodName:ArrayList<String>,private val
buyAgainPrice:ArrayList<String>, private val buyAgaunFoodImage:ArrayList<Int>) : RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>(){
    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        holder.bind(buyAgainFoodName[position],buyAgainPrice[position],buyAgaunFoodImage[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {
        val binding = BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BuyAgainViewHolder(binding)
    }

    override fun getItemCount(): Int = buyAgainFoodName.size

    class BuyAgainViewHolder(private val binding: BuyAgainItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(foodName: String, foodPrice: String, foodImage: Int) {
            binding.BuyAgainFoodName.text = foodName
            binding.BuyAgainFoodPrice.text = foodPrice
            binding.BuyAgainFoodImage.setImageResource(foodImage)
        }

    }
}