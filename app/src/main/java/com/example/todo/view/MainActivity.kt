package com.example.todo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todo.R
import com.example.todo.di.HomeDependencyInjectModules
import com.example.todo.utils.KoinUtils.addModules
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin()
        goToHome()
    }

    private fun startKoin() {
        addModules(*HomeDependencyInjectModules.modules)
        startKoin { applicationContext }
    }

    private fun goToHome() {
        val intent = HomeActivity.newInstance(this).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        startActivity(intent)
    }
}