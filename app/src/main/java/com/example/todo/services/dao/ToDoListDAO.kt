package com.example.todo.services.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todo.services.entity.ToDoListEntity

@Dao
interface ToDoListDAO {
    @Query("SELECT * FROM todo_list_table")
    fun getItems(): List<ToDoListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ToDoListEntity)

    @Query("UPDATE todo_list_table SET finish= :finish, title= :title WHERE id= :id")
    fun updateItem(id: String, title: String, finish: Boolean)

    @Query("DELETE FROM todo_list_table WHERE id=:id")
    fun deleteItem(id: String)

    @Query("DELETE FROM todo_list_table")
    fun deleteTable()
}