package com.example.todo.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.todo.R
import com.example.todo.databinding.DialogNewTaskBinding

class NewTaskDialog(
    val clickListener: (title: String) -> Unit
) : DialogFragment() {
    private lateinit var binding: DialogNewTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogNewTaskBinding.inflate(inflater, container, false)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        binding.close.setOnClickListener {
            dismiss()
        }

        binding.createTask.setOnClickListener {
            clickListener(binding.taskTitle.text.toString())
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), R.style.Theme_Dialog)
    }

    companion object {
        const val TAG = "new_task_dialog"
    }
}