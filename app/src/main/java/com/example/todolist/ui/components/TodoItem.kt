import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.data.Todo
import com.example.todolist.ui.theme.LightBlue
import com.example.todolist.ui.theme.LightGreen
import com.example.todolist.ui.theme.LightPurple


@Composable
fun TodoItem(
    todo: Todo,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: () -> Unit
) {
    val taskColor = remember { listOf(LightPurple, LightGreen, LightBlue).random() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = todo.startTime,
            modifier = Modifier.width(50.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            fontSize = 16.sp,
            color = Color.Black
        )

        RadioButton(
            selected = todo.isCompleted,
            onClick = { onCheckedChange(!todo.isCompleted) },
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape),
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Black,
                unselectedColor = Color.DarkGray
            )
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(12.dp),
            color = Color.Black
        )

        // Card
        Row(
            modifier = Modifier.fillMaxWidth().padding( horizontal = 0.dp ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.weight(1f).padding( horizontal =  0.dp),
                colors = CardDefaults.cardColors(taskColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        // Título
                        Text(
                            text = todo.title,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            fontSize = 22.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        // Descrição
                        if(todo.description.isNotEmpty())
                        {
                            Text(
                                text = todo.description,
                                fontSize = 16.sp,
                                color = Color.DarkGray,
                                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        // Horário
                        if(todo.startTime.isNotEmpty() && todo.endTime.isNotEmpty())
                            Text(
                                text = "${todo.startTime} - ${todo.endTime}",
                                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                fontSize = 18.sp,
                                color = Color.DarkGray
                            )
                    }
                    // Botão de excluir
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Excluir",
                            tint = Color.Black,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(48.dp),
                color = Color.Black
            )
        }
    }
}