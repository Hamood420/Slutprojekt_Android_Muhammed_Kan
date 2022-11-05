package com.example.firebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    lateinit var loginBtn: Button
    lateinit var signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginBtn = findViewById(R.id.mainLoginBtn)
        signUpBtn = findViewById(R.id.mainSignUpBtn)

        loginBtn.setOnClickListener() {replaceFragment(LoginFragment() ) }
        signUpBtn.setOnClickListener() { replaceFragment(SignUpFragment() ) }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment).addToBackStack(null)
        fragmentTransaction.commit()
    }
}