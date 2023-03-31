package com.example.todo.holders

import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.ItemListHolderBinding
import com.example.todo.models.ToDoItemModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ItemListHolder(
    private val binding: ItemListHolderBinding,
    private var clickListener: (item: ToDoItemModel) -> Unit,
    private var clickDeleteTaskListener: (item: ToDoItemModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ToDoItemModel) {
        binding.apply {
            itemContainer.apply {
                setOnClickListener {
                    backgroundItem(item)
                    clickListener(item)
                }

                setOnLongClickListener {
                    MaterialAlertDialogBuilder(context).setMessage(context.getString(R.string.delete_task_description))
                        .setNegativeButton(context.getString(R.string.delete_task_negative)) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton(context.getString(R.string.delete_task_accept)) { dialog, _ ->
                            clickDeleteTaskListener(item)
                            dialog.dismiss()
                        }.show()
                    true
                }
            }

            checkbox.apply {
                setOnClickListener {
                    backgroundItem(item)
                    clickListener(item)
                }
            }

            backgroundItem(item)
            title.text = item.title
        }
    }

    private fun backgroundItem(item: ToDoItemModel) {
        if (!item.finish) {
            setBackground(
                Paint.LINEAR_TEXT_FLAG,
                R.drawable.bg_uncheck_item,
                R.color.black,
            )
        } else {
            setBackground(
                Paint.STRIKE_THRU_TEXT_FLAG, R.drawable.bg_check_item, R.color.secondary
            )
        }

        binding.checkbox.isChecked = item.finish
    }

    private fun setBackground(paintFlag: Int, drawable: Int, textColor: Int) {
        binding.apply {
            title.paintFlags = paintFlag
            title.setTextColor(ContextCompat.getColor(title.context, textColor))
            itemContainer.background = ContextCompat.getDrawable(itemContainer.context, drawable)
        }
    }
}