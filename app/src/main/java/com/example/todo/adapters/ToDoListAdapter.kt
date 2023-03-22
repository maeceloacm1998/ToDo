package com.example.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.todo.holders.ItemListHolder
import com.example.todo.databinding.ItemListHolderBinding
import com.example.todo.models.ToDoItemModel

class ToDoListAdapter(
    private var clickListener: (item: ToDoItemModel) -> Unit
) : ListAdapter<ToDoItemModel, ItemListHolder>(TodoListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListHolder {
        val binding = ItemListHolderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemListHolder(binding = binding, clickListener = clickListener)
    }

    override fun onBindViewHolder(holder: ItemListHolder, position: Int) {
        holder.bind(getItem(position) as ToDoItemModel)
    }
}

class TodoListDiffCallback : DiffUtil.ItemCallback<ToDoItemModel>() {
    override fun areItemsTheSame(oldItem: ToDoItemModel, newItem: ToDoItemModel): Boolean {
        return newItem == oldItem
    }

    override fun areContentsTheSame(oldItem: ToDoItemModel, newItem: ToDoItemModel): Boolean {
        return true
    }
}