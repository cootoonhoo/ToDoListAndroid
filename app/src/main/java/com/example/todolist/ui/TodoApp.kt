package com.example.todolist

import TodoItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.todolist.data.TodoDao
import com.example.todolist.data.TodoDatabase
import com.example.todolist.ui.components.AddTaskDialog
import com.example.todolist.ui.components.ProfileHeader
import com.example.todolist.ui.components.WelcomeTextComponent
import com.example.todolist.ui.theme.Purple80
import com.example.todolist.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(viewModel: TodoViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    val todos by viewModel.todos.collectAsState()
    val qntTodos = todos.count()


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = Color.Black,
                modifier = Modifier.size(100.dp),
                shape = CircleShape
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Adicionar",
                    modifier = Modifier.size(32.dp),
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        AddTaskDialog(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onAddTask = { title, description, startTime, endTime ->
                viewModel.addTodo(title, description, startTime, endTime)
            }
        )
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxHeight(0.85f)
                .fillMaxWidth()
            ,
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ProfileHeader()
                Spacer(modifier = Modifier.height(8.dp))
                WelcomeTextComponent(qntTarefas = qntTodos)
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(todos) { todo ->
                TodoItem(
                    todo = todo,
                    onCheckedChange = { checked ->
                        viewModel.updateTodo(todo.copy(isCompleted = checked))
                    },
                    onDelete = { viewModel.deleteTodo(todo) }
                )
            }
        }
    }
}