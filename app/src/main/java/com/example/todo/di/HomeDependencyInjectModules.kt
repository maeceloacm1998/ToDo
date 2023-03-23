package com.example.todo.di

import com.example.todo.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeDependencyInjectModules {
    private val homeModules = module {
        viewModel { HomeViewModel(get()) }
    }

    val modules = arrayOf(homeModules)
}