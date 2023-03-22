package com.example.todo.services.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todo.services.entity.ToDoListEntity

@Dao
interface ToDoListDAO {
    @Query("SELECT * FROM todo_list_table")
    fun getItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ToDoListEntity)

    @Query("DELETE FROM todo_list_table WHERE id=:id")
    fun deleteItem(id: Int)
}