package com.example.bmscefoodadda.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmscefoodadda.R
import com.example.bmscefoodadda.adapter.BuyAgainAdapter
import com.example.bmscefoodadda.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var buyAgainAdapter: BuyAgainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater,container,false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView(){
        val buyAgainFoodName = arrayListOf("Masala Dosa","Idli","Coffee")
        val buyAgainFoodPrice = arrayListOf("Rs.60/-","Rs. 30/-","Rs. 18/-")
        val buyAgainFoodImage = arrayListOf(R.drawable.masaladosa,R.drawable.idli,R.drawable.coffee)
        buyAgainAdapter = BuyAgainAdapter(buyAgainFoodName,buyAgainFoodPrice,buyAgainFoodImage)
        binding.BuyagainRecyclerView.adapter = buyAgainAdapter
        binding.BuyagainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    companion object {
    }
}