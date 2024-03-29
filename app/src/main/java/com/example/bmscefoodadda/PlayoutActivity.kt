package com.example.bmscefoodadda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmscefoodadda.databinding.ActivityPlayoutBinding

class PlayoutActivity : AppCompatActivity() {
    lateinit var binding : ActivityPlayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.PlaceMyOrderButton.setOnClickListener {
            val bottomSheetDialog = CongratsBottomSheet()
            bottomSheetDialog.show(supportFragmentManager, "Test")
        }
    }
}