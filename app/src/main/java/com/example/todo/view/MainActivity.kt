package com.example.todo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToHome()
    }

    private fun goToHome() {
        startActivity(HomeActivity.newInstance(this))
    }
}