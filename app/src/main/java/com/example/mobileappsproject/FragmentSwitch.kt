package com.example.mobileappsproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class FragmentSwitch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_switch)

        findViewById<View>(R.id.main)?.let { mainView ->
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        supportFragmentManager.commit {
            replace<FragmentB>(R.id.fragmentSwitchActivity)
        }
    }

    fun switchToRegisterFragment() {
        supportFragmentManager.commit {
            replace<FragmentB>(R.id.fragmentSwitchActivity)
            addToBackStack(null)
        }
    }

    fun switchToLoginFragment() {
        supportFragmentManager.commit {
            replace<FragmentA>(R.id.fragmentSwitchActivity)
            addToBackStack(null)
        }
    }
}