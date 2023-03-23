package com.example.todo.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.adapters.ToDoListAdapter
import com.example.todo.databinding.ActivityHomeBinding
import com.example.todo.extension.setVisible
import com.example.todo.models.StateSuccess
import com.example.todo.models.ToDoItemModel
import com.example.todo.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val toDoListAdapter by lazy { ToDoListAdapter(clickListener = ::clickListener) }

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observables()
        setupViews()
        viewModel.getTodoList()
    }

    private fun observables() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is StateSuccess -> {
                    toDoListAdapter.submitList(state.data)
                }
                else -> {
                    binding.emptyError.emptyScreen.setVisible(true)
                }
            }
        }
    }

    private fun setupViews() {
        binding.itemsRv.apply {
            adapter = toDoListAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }

    private fun clickListener(item: ToDoItemModel) {
        val copy = item.copy(finish = !item.finish)
        viewModel.updateItem(copy)
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, HomeActivity::class.java)
    }
}