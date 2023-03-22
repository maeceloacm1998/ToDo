package com.example.todo.services.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.services.dao.ToDoListDAO

@Database(entities = [], version = 1)
abstract class TodoListDB: RoomDatabase() {
    abstract fun todoListDAO(): ToDoListDAO

    companion object {
        const val DATABASE_NAME = "todo_list_database"
    }
}