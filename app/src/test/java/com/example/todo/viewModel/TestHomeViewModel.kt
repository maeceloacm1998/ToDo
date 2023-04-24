package com.example.todo.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.todo.extensions.getOrAwaitValue
import com.example.todo.models.StateSuccess
import com.example.todo.services.dao.ToDoListDAO
import com.example.todo.services.entity.ToDoListEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.java.KoinJavaComponent.inject

class TestHomeViewModel {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule

    private val viewModel: HomeViewModel by inject(HomeViewModel::class.java)
    private val dao: ToDoListDAO by inject(ToDoListDAO::class.java)

    @Before
    fun setup() {
        dao.deleteTable()
    }

    @Test
    fun `Verificar o estato inicial do dado`() {
        // Prepare - Data
        val task = ToDoListEntity().apply {
            id = "1"
            title = "Task de teste"
            finish = false
        }
        dao.insertItem(task)
        // Execute
        val uiModel = viewModel.state.getOrAwaitValue()
        // Verify
        assert(uiModel == StateSuccess(task))
    }
}