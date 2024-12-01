package com.example.mobileappsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val register = findViewById<TextView>(R.id.newmember)
        register.setOnClickListener {
            val go_to_registration = Intent(this,RegisterActivity::class.java)
            startActivity(go_to_registration)
        }
        val signInButton = findViewById<Button>(R.id.nextButton)
        val signInEmailText = findViewById<TextInputEditText>(R.id.emailTextField)
        val signInPasswdText = findViewById<TextInputEditText>(R.id.passwdTextField)
        val signInEmailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val signInPasswdLayout = findViewById<TextInputLayout>(R.id.passwdLayout)



        fun signIn() {

            val credentialsmanager = CredentialsManager()
            val email = signInEmailText.text.toString()
            val passwd = signInPasswdText.text.toString()

            if(!credentialsmanager.emailValid(email)) {
                signInEmailLayout.error = "Email not valid."
            }
            else {
                signInEmailLayout.error = null
            }

            if(!credentialsmanager.passwordValid(passwd)) {
                signInPasswdLayout.error = "Password incorrect."
            }
            else {
                signInPasswdLayout.error = null
            }

            if(credentialsmanager.signIn(email, passwd)) {
                val goToMain = Intent(this, MainActivity::class.java)
                startActivity(goToMain)
            }
            else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        signInButton.setOnClickListener {signIn()}
    }
}