package com.example.bmscefoodadda.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bmscefoodadda.databinding.FragmentHomeBinding
import com.example.bmscefoodadda.databinding.PopularBinding

class PopularAdapter (private val items:List<String>, private val price :List<String>, private val image: List<Int> ):RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(PopularBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        val images = image[position]
        val price = price [position]
        holder.bind(item , price, images)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PopularViewHolder (private var binding: PopularBinding) : RecyclerView.ViewHolder(binding.root){
        private val imagesView = binding.imageView3
        fun bind(item: String, price: String , images: Int) {
            binding.FoodItemPopular.text = item
            binding.PricePopular.text = price
            imagesView.setImageResource(images)
        }

    }

}