package com.example.bmscefoodadda

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.bmscefoodadda.databinding.ActivityChooseLocationBinding

class ChooseLocation : AppCompatActivity() {
    private val binding: ActivityChooseLocationBinding by lazy {
        ActivityChooseLocationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val locationList = arrayOf("Vidhyarthi khana (Indoor Stadium)", "Vidhyarthi Khana (Mechanical building)", "Sip and Snack")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,locationList)
        val autoCompleteTextView = binding.listoflocation
        autoCompleteTextView.setAdapter(adapter)
        binding.LocationChooseButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}