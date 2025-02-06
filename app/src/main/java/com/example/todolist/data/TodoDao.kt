package com.example.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/** ChatGPT - Início
 * Em kotlin, crie um app que permite salvar localmente uma tarefa que possui:
 *  - Titulo
 *  - Descrição
 *  - Horário Inicial
 *  - Horário Término
 *  - Concluido (booleano)
 */

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun getAllTodos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}

/** ChatGPT - Fim */
