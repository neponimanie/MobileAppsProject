package com.example.mobileappsproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

class FragmentB : Fragment(R.layout.activity_fragment_b) {
    private val credentialsManager = CredentialsManager()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logIn = view.findViewById<TextView>(R.id.newmember)
        val registerButton = view.findViewById<Button>(R.id.registerButton)
        val emailInputLayout = view.findViewById<TextInputLayout>(R.id.emailLayout)
        val passwordInputLayout = view.findViewById<TextInputLayout>(R.id.passwdLayout)

        registerButton.setOnClickListener {

            val email = emailInputLayout.editText?.text.toString().trim()
            val password = passwordInputLayout.editText?.text.toString().trim()

            emailInputLayout.error = null
            passwordInputLayout.error = null
                //TO DO fix credential manager tests like emailValid or passwordValid

            //if (!credentialsManager.emailValid(email)) {
              //  emailInputLayout.error = "Invalid email"
            //} else if (!credentialsManager.passwordValid(password)) {
              //  passwordInputLayout.error = "Password cannot be empty"
            //} else if (credentialsManager.register(email, password)) {
                val goToMain = Intent(requireActivity(), MainActivity::class.java)
                startActivity(goToMain)
            //} else {
              //  emailInputLayout.error = "Email already taken"
            //}
        }

        logIn.setOnClickListener {
            (requireActivity() as? FragmentSwitch)?.switchToLoginFragment()
        }
    }
}