package com.example.mobileappsproject

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val credentialsManager = CredentialsManager()


        val sign_in = findViewById<TextView>(R.id.sign_in)
        sign_in.setOnClickListener {
            val go_to_sign_in = Intent(this,SignInActivity::class.java)
            startActivity(go_to_sign_in)
        }
        val emailInput = findViewById<TextInputLayout>(R.id.email_Input)
        val passwordInput = findViewById<TextInputLayout>(R.id.password_Input)
        val registerButton = findViewById<Button>(R.id.Register)

        registerButton.setOnClickListener {

            val email = emailInput.editText?.text.toString()
            val password = passwordInput.editText?.text.toString()

            if (!credentialsManager.emailValid(email)) {
                emailInput.error = "Wrong email"
                return@setOnClickListener
            }

            if (!credentialsManager.passwordValid(password)) {
                passwordInput.error = "Wrong password"
                return@setOnClickListener
            }

            if (credentialsManager.register(email, password)) {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }

            else
            {
                emailInput.error = "Email is already taken"
            }
        }
    }

}