package com.example.todo.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.adapters.ToDoListAdapter
import com.example.todo.databinding.ActivityHomeBinding
import com.example.todo.models.ToDoItemModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    val list: List<ToDoItemModel> = listOf(ToDoItemModel(id = "1", title = "teste", finish = false))

    private val toDoListAdapter by lazy {
        ToDoListAdapter {
            finishItem()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        toDoListAdapter.submitList(list)

        binding.itemsRv.apply {
            adapter = toDoListAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }

    private fun finishItem() {

    }

    companion object {
        fun newInstance(context: Context) = Intent(context, HomeActivity::class.java)
    }
}