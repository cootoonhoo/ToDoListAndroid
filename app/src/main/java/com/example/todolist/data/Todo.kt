package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/** ChatGPT - Início
 * Em kotlin, crie um app que permite salvar localmente uma tarefa que possui:
 *  - Titulo
 *  - Descrição
 *  - Horário Inicial
 *  - Horário Término
 *  - Concluido (booleano)
 */

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val isCompleted: Boolean = false
)

/** ChatGPT - Fim */
