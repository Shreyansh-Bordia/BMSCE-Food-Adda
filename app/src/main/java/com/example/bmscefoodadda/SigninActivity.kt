package com.example.bmscefoodadda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmscefoodadda.databinding.ActivitySigninBinding
import com.example.bmscefoodadda.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SigninActivity : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var username: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private val binding: ActivitySigninBinding by lazy {
        ActivitySigninBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = Firebase.auth
        database = Firebase.database.reference

        binding.SignUpButton.setOnClickListener {
            username = binding.editTextText2.text.toString()
            email = binding.editTextTextEmailAddress22.text.toString().trim()
            password = binding.editTextTextPassword22.text.toString().trim()

            if(email.isBlank() || username.isBlank() || password.isBlank()){
                Toast.makeText(this, "Please fill all the details",Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }
        }

        binding.HaveAccountButton.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_SHORT).show()
                saveUserData()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"Account Creation Failed",Toast.LENGTH_SHORT).show()
                Log.d("Account","createAccount: Failure",task.exception)
            }
        }
    }

    private fun saveUserData() {
        username = binding.editTextText2.text.toString()
        email = binding.editTextTextEmailAddress22.text.toString().trim()
        password = binding.editTextTextPassword22.text.toString().trim()

        val user = UserModel(username,email,password)
        val userId:String = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("appUser").child(userId).setValue(user)
    }
}