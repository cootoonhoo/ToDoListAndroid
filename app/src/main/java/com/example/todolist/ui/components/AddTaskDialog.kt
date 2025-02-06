package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlin.text.isNotBlank


/** Claude 3.5 Hakiu - Início
 * Faça com que ao clicar no floatingActionButton, apareça um dialog com esse formulário
 * { paddingValues ->
 *     Column(
 *         modifier = Modifier
 *             .padding(paddingValues)
 *             .padding(0.dp)
 *             .fillMaxSize()
 *     ) {
 *         OutlinedTextField(
 *             value = title,
 *             onValueChange = { title = it },
 *             label = { Text("Título") },
 *             modifier = Modifier.fillMaxWidth()
 *         )
 *         Spacer(modifier = Modifier.height(8.dp))
 *         OutlinedTextField(
 *             value = description,
 *             onValueChange = { description = it },
 *             label = { Text("Descrição") },
 *             modifier = Modifier.fillMaxWidth()
 *         )
 *         Spacer(modifier = Modifier.height(8.dp))
 *         Row(
 *             modifier = Modifier.fillMaxWidth(),
 *             horizontalArrangement = Arrangement.spacedBy(8.dp)
 *         ) {
 *             OutlinedTextField(
 *                 value = startTime,
 *                 onValueChange = { newValue ->
 *                     startTime = newValue.filter { it.isDigit() }
 *                 },
 *                 label = { Text("Horário Inicial") },
 *                 keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
 *                 modifier = Modifier.weight(1f)
 *             )
 *             OutlinedTextField(
 *                 value = endTime,
 *                 onValueChange = { newValue ->
 *                     endTime = newValue.filter { it.isDigit() }
 *                 },
 *                 label = { Text("Horário Final") },
 *                 keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
 *                 modifier = Modifier.weight(1f)
 *             )
 *         }
 *         Spacer(modifier = Modifier.height(16.dp))
 *         LazyColumn {
 *             items(todos) { todo ->
 *                 TodoItem(
 *                     todo = todo,
 *                     onCheckedChange = { checked ->
 *                         viewModel.updateTodo(todo.copy(isCompleted = checked))
 *                     },
 *                     onDelete = { viewModel.deleteTodo(todo) }
 *                 )
 *             }
 *         }
 *     }
 * }
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onAddTask: (String, String, String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("00:00") }
    var endTime by remember { mutableStateOf("00:00") }

    if (showDialog) {
        Dialog(
            onDismissRequest = {
                onDismiss()
                title = ""
                description = ""
                startTime = ""
                endTime = ""
            }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Adicionar Tarefa",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Título") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Descrição") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TimePicker(
                            value = startTime,
                            onValueChange = { startTime = it },
                            label = "Horário Inicial"
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TimePicker(
                            value = endTime,
                            onValueChange = { endTime = it },
                            label = "Horário Final"
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = { onDismiss() }
                        ) {
                            Text("Cancelar")
                        }

                        Button(
                            onClick = {
                                if (title.isNotBlank()) {
                                    onAddTask(title, description, startTime, endTime)
                                    /** Marco Antonio - Inicio
                                     * Razão : Os valores eram 'herdados' em novas tarefas
                                     */
                                    title = ""
                                    description = ""
                                    startTime = "00:00"
                                    endTime = "00:00"
                                    onDismiss()
                                    /** Marco Antonio - Fim */
                                    }
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text("Adicionar")
                        }
                    }
                }
            }
        }
    }
}

/** Claude 3.5 Hakiu - Final */