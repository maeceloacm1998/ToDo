package com.example.todo.services.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo.models.ToDoItemModel

@Entity(tableName = "todo_list_table")
class ToDoListEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = ""

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "finish")
    var finish: Boolean = false
}

fun ToDoListEntity.asDomainMode() = ToDoItemModel(id = id, finish = finish, title = title)