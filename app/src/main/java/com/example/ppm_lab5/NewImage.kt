package com.example.ppm_lab5

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import androidx.compose.material3.IconButton
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material3.SnackbarHost
import kotlinx.coroutines.launch
import kotlin.collections.maxOfOrNull
import kotlin.collections.none
import kotlin.text.isNotBlank


@ExperimentalMaterial3Api
@Composable
fun AddImageScreen(navController: NavController) {

    val url = remember { mutableStateOf("") }
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Agregar una Imagen", fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("images") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.height(40.dp)
            ) {

            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    if (url.value.isNotBlank() &&
                        title.value.isNotBlank() &&
                        description.value.isNotBlank()
                        && images.none { it.imageUrl == url.value }
                        && images.none { it.title == title.value }
                    ) {
                        val newId = (images.maxOfOrNull { it.id } ?: 1) + 1
                        images.add(
                            ImageClass(
                                id = newId,
                                title = title.value,
                                description = description.value,
                                imageUrl = url.value
                            )
                        )

                        scope.launch {
                            snackbarHostState.showSnackbar("Imagen agregada correctamente")
                        }

                        url.value = ""
                        title.value = ""
                        description.value = ""
                        navController.navigate("images")

                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("Información Inválida")
                        }
                    }
                },
                modifier = Modifier.width(300.dp).paddingFromBaseline(
                    top = 10.dp,
                    bottom = 100.dp
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(40.dp),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Row {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Agregar Imagen",
                        modifier = Modifier.size(30.dp)
                    )

                    Text(
                        text = "Agregar Imagen",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterVertically).padding(start = 10.dp)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->


        Surface(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {

            Column {
                TextField(
                    value = url.value,
                    onValueChange = { url.value = it },
                    label = { Text("URL de la Imagen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp, start = 16.dp, end = 16.dp)
                        .height(120.dp)
                        .background(
                            Color.White,
                            androidx.compose.foundation.shape.RoundedCornerShape(40.dp)
                        )
                )

                TextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Título de la Imagen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .background(
                            Color.White,
                            androidx.compose.foundation.shape.RoundedCornerShape(40.dp)
                        )
                )

                TextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text("Descripción de la Imagen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .height(100.dp)
                        .background(
                            Color.White,
                            androidx.compose.foundation.shape.RoundedCornerShape(40.dp)
                        )
                )
            }

        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewAddImageScreen() {

    val url = remember { mutableStateOf("") }
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Agregar una Imagen", fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.height(40.dp)
            ) {

            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (url.value.isNotBlank() &&
                        title.value.isNotBlank() &&
                        description.value.isNotBlank()
                    ) {
                        val newId = (images.maxOfOrNull { it.id } ?: 1) + 1
                        images.add(
                            ImageClass(
                                id = newId,
                                title = title.value,
                                description = description.value,
                                imageUrl = url.value
                            )
                        )

                        scope.launch {
                            snackbarHostState.showSnackbar("Imagen agregada correctamente")
                        }

                        url.value = ""
                        title.value = ""
                        description.value = ""


                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("La información no es válida")
                        }
                    }
                },
                modifier = Modifier.width(300.dp).paddingFromBaseline(
                    top = 10.dp,
                    bottom = 100.dp
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(40.dp),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Row {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Agregar Imagen",
                        modifier = Modifier.size(30.dp)
                    )

                    Text(
                        text = "Agregar Imagen",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterVertically).padding(start = 10.dp)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->


        Surface(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {

            Column {
                TextField(
                    value = url.value,
                    onValueChange = { url.value = it },
                    label = { Text("URL de la Imagen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp, start = 16.dp, end = 16.dp)
                        .height(120.dp)
                        .background(
                            Color.White,
                            androidx.compose.foundation.shape.RoundedCornerShape(40.dp)
                        )
                )

                TextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Título de la Imagen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .background(
                            Color.White,
                            androidx.compose.foundation.shape.RoundedCornerShape(40.dp)
                        )
                )

                TextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text("Descripción de la Imagen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .height(100.dp)
                        .background(
                            Color.White,
                            androidx.compose.foundation.shape.RoundedCornerShape(40.dp)
                        )
                )
            }

        }
    }

}