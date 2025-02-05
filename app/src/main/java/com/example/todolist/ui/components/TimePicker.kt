package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.text.filter
import kotlin.text.isDigit

@Composable
fun TimePicker(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Horário"
) {
    /** Claude 3.5 Hakiu - Início
     * Faça esse OutlinedTextField virar um time picker de 24hrs
     *
     *
     * Copy
     * OutlinedTextField(
     *     value = startTime,
     *     onValueChange = { newValue ->
     *         startTime = newValue.filter { it.isDigit() }
     *     },
     *     label = { Text("Horário Inicial") },
     *     keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
     *     modifier = Modifier.weight(1f)
     * )
     */
    var hours by remember { mutableStateOf(value.take(2).padStart(2, '0')) }
    var minutes by remember { mutableStateOf(value.takeLast(2).padStart(2, '0')) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = hours,
            onValueChange = {
                val filteredValue = it.take(2).filter { it.isDigit() }
                hours = filteredValue.take(2).padStart(2, '0')
                    .coerceIn("00", "23")
                onValueChange("$hours:$minutes")
            },
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.width(80.dp),
            singleLine = true,
            maxLines = 1
        )
        Text(":")
        OutlinedTextField(
            value = minutes,
            onValueChange = {
                val filteredValue = it.take(2).filter { it.isDigit() }
                minutes = filteredValue.take(2).padStart(2, '0')
                    .coerceIn("00", "59")
                onValueChange("$hours:$minutes")
            },
            label = { Text("") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.width(80.dp),
            singleLine = true,
            maxLines = 1
        )
    }
}
/** Claude 3.5 Hakiu - Final */
