package com.example.destination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// DATA MODEL
data class Destination(
    val title: String,
    val country: String,
    val description: String
)

// SAMPLE DATA
val destinations = listOf(
    Destination("Eiffel Tower", "France", "Famous landmark in Paris."),
    Destination("Great Wall", "China", "Historic wall across China."),
    Destination("Taj Mahal", "India", "Beautiful marble monument."),
    Destination("Colosseum", "Italy", "Ancient Roman amphitheatre."),
    Destination("Mount Fuji", "Japan", "Iconic mountain in Japan.")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationScreen()
                }
            }
        }
    }
}

@Composable
fun DestinationScreen() {

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(destinations) { destination ->
            DestinationCard(destination)
        }
    }
}

@Composable
fun DestinationCard(destination: Destination) {

    var expanded by remember { mutableStateOf(false) }
    var favourite by remember { mutableStateOf(false) }

    val favColor by animateColorAsState(
        if (favourite) MaterialTheme.colorScheme.secondary
        else Color.Gray
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = MaterialTheme.shapes.medium
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = destination.title,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = destination.country,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                IconButton(
                    onClick = { favourite = !favourite }
                ) {
                    Icon(
                        imageVector = if (favourite)
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favourite",
                        tint = favColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (expanded) {
                Text(
                    text = destination.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            TextButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show Less" else "Show More")
            }
        }
    }
}