package com.example.mobileappsproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

class FragmentA : Fragment(R.layout.activity_fragment) {
    private val credentialsManager = CredentialsManager()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerNow = view.findViewById<TextView>(R.id.newmember)
        val loginEmail = view.findViewById<TextInputLayout>(R.id.emailLayout)
        val loginPassword = view.findViewById<TextInputLayout>(R.id.passwdLayout)
        val loginNextButton = view.findViewById<Button>(R.id.Next)

        loginNextButton.setOnClickListener {
            val emailText = loginEmail.editText?.text.toString().trim()
            val passwordText = loginPassword.editText?.text.toString().trim()

            loginEmail.error = null
            loginPassword.error = null

            if (!credentialsManager.emailValid(emailText)) {
                loginEmail.error = "Invalid email format"
                return@setOnClickListener
            }

            if (!credentialsManager.passwordValid(passwordText)) {
                loginPassword.error = "Password cannot be empty"
                return@setOnClickListener
            }

            if (credentialsManager.signIn(emailText, passwordText)) {
                // Handle successful login
            } else {
                loginEmail.error = "Incorrect email or password"
                loginPassword.error = "Incorrect email or password"
            }
        }

        registerNow.setOnClickListener {
            (requireActivity() as? FragmentSwitch)?.switchToRegisterFragment()
        }
    }
}