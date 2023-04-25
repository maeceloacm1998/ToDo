package com.example.todo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.models.IHomeViewModel
import com.example.todo.models.StateError
import com.example.todo.models.StateResponse
import com.example.todo.models.StateSuccess
import com.example.todo.models.ToDoItemModel
import com.example.todo.models.asDomainMode
import com.example.todo.services.dao.ToDoListDAO
import com.example.todo.services.entity.asDomainMode
import java.io.IOError

class HomeViewModel(
    private val toDoListDAO: ToDoListDAO
) : ViewModel(), IHomeViewModel {

    private var _state = MutableLiveData<StateResponse<List<ToDoItemModel>>>()
    var state: LiveData<StateResponse<List<ToDoItemModel>>> = _state

    override fun getTodoList() {
        val items = toDoListDAO.getItems()
        if (items.isEmpty()) {
            _state.value = StateError(IOError(Throwable("Lista está vazia")))
            return
        }
        _state.value = StateSuccess(items.map { it.asDomainMode() })
    }

    override fun updateItem(item: ToDoItemModel) {
        val parseItem = item.asDomainMode()

        toDoListDAO.run {
            updateItem(
                id = parseItem.id, title = parseItem.title, finish = parseItem.finish
            )
        }

        getTodoList()
    }

    override fun addNewTask(item: ToDoItemModel) {
        toDoListDAO.insertItem(item.asDomainMode())
        getTodoList()
    }

    override fun deleteItem(item: ToDoItemModel) {
        toDoListDAO.deleteItem(item.id)
        getTodoList()
    }

    override fun deleteAllItems() {
        toDoListDAO.deleteTable()
        getTodoList()
    }
}