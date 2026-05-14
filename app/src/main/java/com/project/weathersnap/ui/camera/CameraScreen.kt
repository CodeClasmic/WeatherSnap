package com.project.weathersnap.ui.camera

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.project.weathersnap.viewmodel.ReportSharedViewModel
import java.io.File

@Composable
fun CameraScreen(
    navController: NavController,
    city: String,
    temperature: String,
    condition: String,
    humidity: String,
    wind: String,
    pressure: String,
    sharedViewModel: ReportSharedViewModel
) {

    val context =
        LocalContext.current

    val lifecycleOwner =
        LocalLifecycleOwner.current

    val previewView =
        remember {
            PreviewView(context)
        }

    var imageCapture:
            ImageCapture? by remember {

        mutableStateOf(null)
    }

    var hasPermission by remember {

        mutableStateOf(

            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher =
        rememberLauncherForActivityResult(

            contract =
                ActivityResultContracts
                    .RequestPermission()

        ) { granted ->

            hasPermission = granted
        }

    LaunchedEffect(Unit) {

        if (!hasPermission) {

            launcher.launch(
                Manifest.permission.CAMERA
            )
        }
    }

    LaunchedEffect(hasPermission) {

        if (!hasPermission) return@LaunchedEffect

        val cameraProviderFuture =
            ProcessCameraProvider
                .getInstance(context)

        val cameraProvider =
            cameraProviderFuture.get()

        val preview =
            Preview.Builder()
                .build()

        preview.surfaceProvider =
            previewView.surfaceProvider

        imageCapture =
            ImageCapture.Builder()
                .build()

        try {

            cameraProvider.unbindAll()

            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                imageCapture
            )

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        if (hasPermission) {

            AndroidView(
                factory = {
                    previewView
                },

                modifier =
                    Modifier.fillMaxSize()
            )
        }

        Button(
            onClick = {

                val imageFolder =
                    File(
                        context.cacheDir,
                        "images"
                    )

                imageFolder.mkdirs()

                val imageFile =
                    File(
                        imageFolder,
                        "weather_${System.currentTimeMillis()}.jpg"
                    )

                val imageUri =
                    FileProvider.getUriForFile(

                        context,

                        "${context.packageName}.provider",

                        imageFile
                    )

                val outputOptions =
                    ImageCapture.OutputFileOptions
                        .Builder(imageFile)
                        .build()

                imageCapture?.takePicture(

                    outputOptions,

                    ContextCompat.getMainExecutor(
                        context
                    ),

                    object :
                        ImageCapture
                        .OnImageSavedCallback {

                        override fun onImageSaved(
                            outputFileResults:
                            ImageCapture
                            .OutputFileResults
                        ) {

                            sharedViewModel.setImageUri(
                                imageUri
                            )

                            navController.popBackStack()
                        }

                        override fun onError(
                            exception:
                            ImageCaptureException
                        ) {

                            exception.printStackTrace()
                        }
                    }
                )
            },

            modifier =
                Modifier
                    .align(
                        Alignment.BottomCenter
                    )
                    .padding(24.dp)
        ) {

            Text(
                text = "Capture"
            )
        }
    }
}