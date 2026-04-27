package com.pdm.fipr.studentprofile.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pdm.fipr.studentprofile.model.Student
import com.pdm.fipr.studentprofile.screens.components.AppScaffold

@Composable
fun StudentListScreen(
    modifier: Modifier = Modifier,
    onStudentClick: () -> Unit
) {


    var studentId by remember { mutableIntStateOf(0) }                       // Guarda el ID del estudiante
    val studentsList = remember { mutableStateListOf<Student>() }                   // Lista de estuadiantes
    var studentName by remember(studentId) { mutableStateOf("") }    // Guarda el nombre del estudiante



    AppScaffold(
        modifier = modifier,
        title = "Home"
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Nuevo estudiante",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            TextField(
                value = studentName,
                onValueChange = { studentName = it }, // it es el valor que se ingresa en el campo de texto
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    if(studentName.isNotEmpty()) {
                        studentsList.add(Student(studentId, studentName))
                        studentId++
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Agregar",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center

                )
            }
            Text(
                text = "Estudiantes registrados",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(8.dp)
                    )

            ) {
                itemsIndexed(studentsList) { index, student ->
                    Row(
                        modifier = Modifier
                            .clickable { onStudentClick() }
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${index + 1}.",
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(0.1f),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = student.name,
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(0.9f)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun StudentListScreenPreview() {
    StudentListScreen(
        modifier = Modifier,
        onStudentClick = { }
    )
}
