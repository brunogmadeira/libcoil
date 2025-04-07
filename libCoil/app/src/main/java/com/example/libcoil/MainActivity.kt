// MainActivity.kt
package com.example.libcoil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.compose.ui.platform.LocalContext
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.RoundedCornersTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ImageListScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageListScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Download de imagens com Coil") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        ImageList(Modifier.padding(innerPadding))
    }
}

@Composable
fun ImageList(modifier: Modifier = Modifier) {
    val imageUrls = listOf(
        "https://files.passeidireto.com/e5aec418-1642-48ad-b031-a66df758dfc4/e5aec418-1642-48ad-b031-a66df758dfc4.jpeg",
        "https://photojournal.jpl.nasa.gov/jpeg/PIA12235.jpg",
        "https://cdn2.thecatapi.com/images/bpc.jpg",
        "https://picsum.photos/id/240/400/300",
        "https://images.unsplash.com/photo-1592194996308-7b43878e84a6"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(imageUrls.size) { index ->
            RemoteImage(imageUrl = imageUrls[index])
        }
    }
}

@Composable
fun RemoteImage(imageUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .transformations(RoundedCornersTransformation(40f))
            .build(),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.Crop
    )
}