package com.example.todo.extension

import android.view.View

fun View.setVisible(condition: Boolean) = apply {
    this.visibility = if (condition) {
        View.VISIBLE
    } else {
        View.GONE
    }
}