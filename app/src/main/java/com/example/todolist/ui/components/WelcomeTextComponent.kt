package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.ui.theme.LightGray

@Composable
fun WelcomeTextComponent(qntTarefas: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Olá, bem-vindo!",
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            color = Color.DarkGray
        )
        Text(
            text = if(qntTarefas == 1) "$qntTarefas tarefa para hoje." else "$qntTarefas tarefas para hoje.",
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            fontSize = 22.sp,
            color = LightGray
        )
    }
}