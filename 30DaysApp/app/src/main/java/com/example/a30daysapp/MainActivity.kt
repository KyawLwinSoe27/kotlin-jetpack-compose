package com.example.a30daysapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysapp.data.Flower
import com.example.a30daysapp.data.FlowersRepository
import com.example.a30daysapp.ui.theme._30DaysAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DaysAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlowersApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FlowersApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar() }
    ) {
        val flowers = FlowersRepository.flowers
        FlowersList(flowers = flowers, modifier = Modifier.padding(16.dp).padding(top = 50.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowersList(flowers: List<Flower>, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)) {
    LazyColumn(modifier = modifier, contentPadding = contentPadding, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        itemsIndexed(flowers) {index, flower -> FlowerCard(index = index + 1, imageId = flower.image, name = flower.name, scientificName = flower.scientificName, description = flower.description) }
    }
}

@Composable
fun FlowerCard(index: Int, imageId: Int, name: String, scientificName: String, description: String) {
    val image = painterResource(id = imageId)
    Box(modifier = Modifier
        .background(MaterialTheme.colorScheme.secondary)
        .clip(shape = RoundedCornerShape(16.dp))) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start) {
            Text(text = "Day $index", style = MaterialTheme.typography.displayMedium)
            Text(text = name, style = MaterialTheme.typography.displayMedium)
            Image(painter = image, contentDescription = "image", modifier = Modifier.clip(shape = RoundedCornerShape(16.dp)))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = scientificName, style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = { Text(text = "30 Days of Flowers", style = MaterialTheme.typography.displayLarge) }, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _30DaysAppTheme {
        FlowersApp()
    }
}