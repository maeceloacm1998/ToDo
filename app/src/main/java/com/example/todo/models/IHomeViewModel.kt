package com.example.todo.models

interface IHomeViewModel {
    fun getTodoList()

    fun updateItem(item: ToDoItemModel)

    fun addNewTask(item: ToDoItemModel)

    fun deleteItem(item: ToDoItemModel)

    fun deleteAllItems()
}