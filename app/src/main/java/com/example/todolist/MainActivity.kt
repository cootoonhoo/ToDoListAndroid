package com.example.todolist;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.data.TodoDatabase
import com.example.todolist.repository.TodoRepository
import com.example.todolist.ui.theme.TodoListTheme
import com.example.todolist.viewmodel.TodoViewModel
import com.example.todolist.viewmodel.TodoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = TodoDatabase.getDatabase(this)
        val repository = TodoRepository(database.todoDao())

        val viewModel = ViewModelProvider(
            this,
            TodoViewModelFactory(repository)
        ).get(TodoViewModel::class.java)

        setContent {
            TodoListTheme {
                TodoApp(viewModel = viewModel)
            }
        }
    }
}