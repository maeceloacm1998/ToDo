package com.example.todo.holders

import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.ItemListHolderBinding
import com.example.todo.models.ToDoItemModel

class ItemListHolder(
    private val binding: ItemListHolderBinding,
    private var clickListener: (item: ToDoItemModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ToDoItemModel) {
        binding.apply {
            itemContainer.setOnClickListener {
                if (item.finish) {
                    setBackground(
                        Paint.LINEAR_TEXT_FLAG,
                        R.drawable.bg_uncheck_item,
                        R.color.black,
                    )
                } else {
                    setBackground(
                        Paint.STRIKE_THRU_TEXT_FLAG,
                        R.drawable.bg_check_item,
                        R.color.secondary
                    )
                }

                checkbox.isChecked = !item.finish
                clickListener(item)
            }

            checkbox.apply {
                setOnClickListener {
                    isChecked = item.finish
                }
            }

            title.text = item.title
        }
    }

    private fun setBackground(paintFlag: Int, drawable: Int, textColor: Int) {
        binding.apply {
            title.paintFlags = paintFlag
            title.setTextColor(ContextCompat.getColor(title.context, textColor))
            itemContainer.background = ContextCompat.getDrawable(itemContainer.context, drawable)
        }
    }
}