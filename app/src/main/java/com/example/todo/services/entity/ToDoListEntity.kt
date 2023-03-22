package com.example.todo.services.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list_table")
class ToDoListEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = ""

    @ColumnInfo(name = "id")
    var title: String = ""

    @ColumnInfo(name = "id")
    var finish: Boolean = false
}