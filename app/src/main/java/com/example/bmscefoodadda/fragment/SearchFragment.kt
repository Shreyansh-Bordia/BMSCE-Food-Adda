package com.example.bmscefoodadda.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmscefoodadda.R
import com.example.bmscefoodadda.adapter.menuAdapter
import com.example.bmscefoodadda.databinding.FragmentSearchBinding



class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private var adapter : menuAdapter? = null
    private val originalMenuFoodName = listOf("Masala Dosa","Set Dosa","Idli (1 Plate)","Vada (1 Plate)","Coffee","Tea")
    private val originalMenuPrice = listOf("Rs. 60/-","Rs. 50/-","Rs. 30/-","Rs. 45/-", "Rs. 18/-", "Rs. 14/-")
    private val originalMenuFoodImages = listOf(R.drawable.masaladosa,R.drawable.setdosa,R.drawable.idli,R.drawable.vada,R.drawable.coffee,R.drawable.tea)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val filtterMenuFoodName = mutableListOf<String>()
    private val filtterMenuFoodPrice = mutableListOf<String>()
    private val filtterMenuFoodImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
       // val adapter = menuAdapter(filtterMenuFoodName,filtterMenuFoodPrice,filtterMenuFoodImage)
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        setupSearchView()
        showAllMenu()
        return binding.root
    }

    private fun showAllMenu() {
        filtterMenuFoodName.clear()
        filtterMenuFoodPrice.clear()
        filtterMenuFoodImage.clear()

        filtterMenuFoodName.addAll(originalMenuFoodName)
        filtterMenuFoodPrice.addAll(originalMenuPrice)
        filtterMenuFoodImage.addAll(originalMenuFoodImages)

        adapter?.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    private fun filterMenuItems(query: String){
        filtterMenuFoodName.clear()
        filtterMenuFoodPrice.clear()
        filtterMenuFoodImage.clear()

        originalMenuFoodName.forEachIndexed { index, foodName ->
            if(foodName.contains(query, ignoreCase = true)){
                filtterMenuFoodName.add(foodName)
                filtterMenuFoodPrice.add(originalMenuPrice[index])
                filtterMenuFoodImage.add(originalMenuFoodImages[index])
            }
        }
        adapter?.notifyDataSetChanged()
    }
    companion object {

    }
}

