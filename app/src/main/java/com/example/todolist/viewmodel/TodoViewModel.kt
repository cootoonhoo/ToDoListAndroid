package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Todo
import com.example.todolist.repository.TodoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/** ChatGPT o3-mini - 
 * Em kotlin, crie uma variavel todos e funcoes CRUD(addTodo, updateTodo e deleteTodo) para armazenar um objeto Todo, 
 * utilizando o padrão ViewModel do JetpackCompose, considere que já tenha um TodoRepository configurado.
 */

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    val todos: StateFlow<List<Todo>> = repository.allTodos
        .stateIn(viewModelScope, started = kotlinx.coroutines.flow.SharingStarted.Lazily, initialValue = emptyList())

    fun addTodo(title: String, description: String, startTime: String, endTime: String) {
        viewModelScope.launch {
            repository.insertTodo(Todo(title = title, description = description, startTime = startTime, endTime = endTime))
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }
}

class TodoViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido")
    }

}

/** ChatGPT - Fim */
