package com.example.todo.di

import androidx.room.Room
import com.example.todo.services.database.TodoListDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object RoomDependencyInjectModules {
    private val roomDModule = module {
        single {
            Room.databaseBuilder(
                androidContext(), TodoListDB::class.java, TodoListDB.DATABASE_NAME
            ).allowMainThreadQueries().build()
        }
        single {
            val database = get<TodoListDB>()
            database.todoListDAO()
        }
    }


    val modules = arrayOf(roomDModule)
}