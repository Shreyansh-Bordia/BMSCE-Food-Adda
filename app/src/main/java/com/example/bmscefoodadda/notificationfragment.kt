package com.example.bmscefoodadda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmscefoodadda.adapter.notificationAdapter
import com.example.bmscefoodadda.databinding.FragmentNotificationfragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class notificationfragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNotificationfragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationfragmentBinding.inflate(layoutInflater,container,false)
        val notifications = listOf("Your Order has been Cancelled Successfully","Order has been taken by the Canteen","Congrats! Your Order is Placed")
        val notificationsImages = listOf(R.drawable.baseline_cancel_24, R.drawable.baseline_directions_car_24, R.drawable.baseline_done_all_24)
        val adapter = notificationAdapter(ArrayList(notifications), ArrayList(notificationsImages))
        binding.notificationRecyclerTextView.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationRecyclerTextView.adapter = adapter
        return binding.root
    }

    companion object {

    }
}