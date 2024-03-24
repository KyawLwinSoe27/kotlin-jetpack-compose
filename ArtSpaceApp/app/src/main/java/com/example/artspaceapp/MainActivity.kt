package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtWorkLayout()
                }
            }
        }
    }
}

@Composable
fun ArtWorkLayout() {
    var currentArtWorkIndex by remember { mutableIntStateOf(1) }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        when(currentArtWorkIndex) {
            1 -> ArtWork(artworkTitle = "The Scream", artistName = "Edvard Munch 1982", image = R.drawable.edvard)
            2 -> ArtWork(artworkTitle = "The Edvard", artistName = "Vincent van Gogh 1889", image = R.drawable.the_scream)
            3 -> ArtWork(artworkTitle = "Mona Lisa", artistName = "Leonardo da Vinci 1503", image = R.drawable.pixel_character)
        }
        Spacer(modifier = Modifier.height(16.dp))
        DisplayController(prevBtn = {currentArtWorkIndex--}, nextBtn = {currentArtWorkIndex++}, prevEnable = currentArtWorkIndex > 1, nextEnable = currentArtWorkIndex < 3)
    }
}

@Composable
fun DisplayController(prevBtn : () -> Unit, nextBtn : () -> Unit, prevEnable: Boolean = true, nextEnable: Boolean = true) {
    Row (horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.Bottom, modifier = Modifier.width(300.dp)) {
        CommonButton(buttonName = "Previous", onClick = {prevBtn()}, prevEnable)
        CommonButton(buttonName = "Next", onClick = {nextBtn()}, nextEnable)
    }
}

@Composable
fun CommonButton(buttonName: String, onClick: () -> Unit, enabled: Boolean = true) {
    Button(enabled = enabled ,onClick = { onClick() }) {
        Text(text = buttonName)
    }
}

@Composable
fun ArtWork(artworkTitle: String, artistName: String, image: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ArtWorkWall(image)
        Spacer(modifier = Modifier.height(32.dp))
        ArtWorkDescriptor(artworkTitle,artistName)
    }
}

@Composable
fun ArtWorkWall(imageId: Int) {
    val image = painterResource(id = imageId)
    Box(
        modifier = Modifier
            .height(600.dp) // Set height of the container
            .padding(16.dp) // Add padding around the container
            .border(width = 2.dp, color = Color.White) // Add border with width and color
            .background(Color.White) // Add background color to the container
            .padding(2.dp) // Add padding to separate the border from the image
            .then(Modifier.wrapContentSize(Alignment.Center)) // Center the image
            .then(Modifier.padding(8.dp)) // Add padding to separate the elevation from the container
            .then(Modifier.shadow(10.dp)), // Add elevation to the container
        contentAlignment = Alignment.Center // Center the content inside the box

    ) {
        Image(
            painter = image,
            contentDescription = "Artwork Wall",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .aspectRatio(0.7f) // Aspect ratio of the image (adjust as needed)
                .fillMaxSize() // Image fills the available space while maintaining aspect ratio
        )
    }
}

@Composable
fun ArtWorkDescriptor(artworkTitle: String, artistName: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = artworkTitle, style = MaterialTheme.typography.bodyLarge)
        Text(text = artistName, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtWorkLayout()
    }
}