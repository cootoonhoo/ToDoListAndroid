package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.ui.theme.LightGray

@Composable
fun WelcomeTextComponent(qntTarefas: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Olá, bem-vindo!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Text(
            text = "$qntTarefas tarefas para hoje.",
            fontSize = 22.sp,
            color = LightGray
        )
    }
}