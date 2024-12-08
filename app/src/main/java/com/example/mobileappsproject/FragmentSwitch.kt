package com.example.mobileappsproject

import android.os.Bundle
import android.content.Intent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class FragmentSwitch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment_switch)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<View>(R.id.fragSwitch).setOnClickListener{
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentActivity)
            supportFragmentManager.commit{
                if(currentFragment is FragmentA) {
                    replace<FragmentB>(R.id.fragmentActivity)
                    addToBackStack(null)
                }
                else {
                    supportFragmentManager.popBackStack()
                    replace<FragmentA>(R.id.fragmentActivity)
                }
            }
        }
    }
}