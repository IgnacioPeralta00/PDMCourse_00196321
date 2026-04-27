package com.pdm.fipr.studentprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.studentprofile.model.Student
import com.pdm.fipr.studentprofile.route.Routes
import com.pdm.fipr.studentprofile.screens.StudentImage
import com.pdm.fipr.studentprofile.screens.StudentListScreen
import com.pdm.fipr.studentprofile.ui.theme.StudentProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentProfileTheme {
                MainNavigator(
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun MainNavigator(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(Routes.Home)
    val studentsList = remember { mutableStateListOf<Student>() }     // Lista de estuadiantes

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {
                StudentListScreen(
                    modifier = modifier,
                    studentsList = studentsList,
                    onAddStudent = { id, name -> studentsList.add(Student(
                        id = id,
                        name = name
                    )) },
                    onStudentClick = { id -> backStack.add(Routes.StudentDetail(id)) }
                )
            }
            entry<Routes.StudentDetail> { studentId ->
                StudentImage(
                    modifier = Modifier,
                    studentId = studentId.id,
                    onBackHome = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}

//@Preview(showBackground = true)
/*
@Composable
fun GreetingPreview() {
    StudentProfileTheme {
        Greeting("Android")
    }
}*/
