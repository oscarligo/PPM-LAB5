package com.example.ppm_lab5

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.ppm_lab5.ui.theme.PPMLAB5Theme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Registers a photo picker activity launcher in single-select mode.
        val pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
        setContent {
            PPMLAB5Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "images") {
                    composable("images") { ImagesScreen(navController) }
                    composable("add") { AddImageScreen(navController) }
                }
            }
        }
    }
}


