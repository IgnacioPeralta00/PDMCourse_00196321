package com.pdm.FIPR.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun MyFirstScreen(modifier: Modifier = Modifier) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var isSheetVisible by rememberSaveable { mutableStateOf(false) }

    val onShowSnackBar: () -> Unit = {
        scope.launch {
            snackBarHostState.showSnackbar("¡Botón presionado!")
        }
    }

    AppScaffold(
        modifier = modifier,
        title = "Top app bar",
        bottomBarText = "Bottom app bar",
        snackbarHostState = snackBarHostState,
        onFabClick = { isSheetVisible = true }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("Hello World")
            Button(onClick = onShowSnackBar) {
                Text("Mostrar Snackbar")
            }
        }

        CustomModalBottomSheet(
            isVisible = isSheetVisible,
            onDismiss = { isSheetVisible = false }
        ) {
            Text("Opción 1")
            Text("Opción 2")
            Text("Opción 3")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyFirstScreenPreview() {
    MyFirstScreen()
}