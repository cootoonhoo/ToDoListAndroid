package com.example.todolist.repository

import com.example.todolist.data.Todo
import com.example.todolist.data.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: Flow<List<Todo>> = todoDao.getAllTodos()

    suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
}
