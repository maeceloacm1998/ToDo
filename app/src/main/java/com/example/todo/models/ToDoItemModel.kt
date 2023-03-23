package com.example.todo.models

import com.example.todo.services.entity.ToDoListEntity

data class ToDoItemModel(
    val id: String, val finish: Boolean, val title: String
)

fun ToDoItemModel.asDomainMode() = ToDoListEntity().also {
    it.id = id
    it.title = title
    it.finish = finish
}
