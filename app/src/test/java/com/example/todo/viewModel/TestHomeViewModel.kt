package com.example.todo.viewModel

import KoinTestRule
import android.os.Build.VERSION_CODES.Q
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todo.models.StateError
import com.example.todo.models.StateSuccess
import com.example.todo.models.ToDoItemModel
import com.example.todo.services.dao.ToDoListDAO
import getOrAwaitValueTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.java.KoinJavaComponent.inject
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Q])
class TestHomeViewModel {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule()

    private val viewModel: HomeViewModel by inject(HomeViewModel::class.java)
    private val dao: ToDoListDAO by inject(ToDoListDAO::class.java)

    @Before
    fun setup() {
        KoinTestRule()
        dao.deleteTable()
    }

    @Test
    fun `Espere error caso a lista de task venha vazia`() {
        // Execute
        viewModel.deleteAllItems()
        viewModel.getTodoList()
        val uiState = viewModel.state.getOrAwaitValueTest()

        // Verify
        assertTrue(uiState is StateError)
    }

    @Test
    fun `Espere o estado de sucesso quando uma task for inserida no banco local`() {
        // Prepare - Data
        val task = ToDoItemModel(
            id = "1", title = "Task de teste", finish = false
        )

        // Execute
        viewModel.addNewTask(task)
        val uiState = viewModel.state.getOrAwaitValueTest()

        // Verify
        assertTrue(uiState is StateSuccess)
        if (uiState is StateSuccess) {
            assertEquals(uiState.data, listOf(task))
        }
    }

    @Test
    fun `Espere que o valor da propriedade "isFinish" mude quando utilizarmos a função de update de item na lista`() {
        // Data
        val task = ToDoItemModel(
            id = "1", title = "Task de teste", finish = false
        )

        // Execute
        viewModel.addNewTask(task)
        viewModel.updateItem(task.copy(finish = true))
        val uiState = viewModel.state.getOrAwaitValueTest()

        // Assets
        if (uiState is StateSuccess) {
            assertNotEquals(uiState.data, listOf(task))
        }
    }

    @Test
    fun `Espere que o task seja deletada do banco local`() {
        // Data
        val task1 = ToDoItemModel(
            id = "1", title = "Task de teste 1", finish = false
        )

        val task2 = ToDoItemModel(
            id = "2", title = "Task de teste 2", finish = false
        )

        // Execute
        viewModel.addNewTask(task1)
        viewModel.addNewTask(task2)
        viewModel.deleteItem(task1)
        val uiState = viewModel.state.getOrAwaitValueTest()

        // Assets
        if (uiState is StateSuccess) {
            assertFalse(uiState.data.contains(task1))
        }
    }

    @Test
    fun `Espere que quando deletar a ultima task do banco local, ele retorn state de Error`() {
        // Data
        val task = ToDoItemModel(
            id = "1", title = "Task de teste 1", finish = false
        )

        // Execute
        viewModel.addNewTask(task)
        viewModel.deleteItem(task)
        val uiState = viewModel.state.getOrAwaitValueTest()

        // Asset
        assertTrue(uiState is StateError)
    }

    @Test
    fun `Espere que ao deletar todas as tasks do banco local apareça mensagem de error`() {
        // Data
        val task1 = ToDoItemModel(
            id = "1", title = "Task de teste 1", finish = false
        )

        val task2 = ToDoItemModel(
            id = "2", title = "Task de teste 2", finish = false
        )

        // Execute
        viewModel.addNewTask(task1)
        viewModel.addNewTask(task2)
        viewModel.deleteAllItems()
        val uiState = viewModel.state.getOrAwaitValueTest()

        // Asset
        assertTrue(uiState is StateError)
    }
}