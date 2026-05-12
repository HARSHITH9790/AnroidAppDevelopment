package com.example.task3modifiers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task3Screen()
        }
    }
}

@Composable
fun Task3Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))   // light background so screen doesn't look empty
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Experiment 1: Padding")
        ExperimentPadding()

        Text("Experiment 2: Weight + fillMaxWidth")
        ExperimentWeightFill()

        Text("Experiment 3: SpaceBetween + Align")
        ExperimentArrangementAlign()
    }
}

@Composable
fun ExperimentPadding() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        Text(text = "Without padding", modifier = Modifier.background(Color.Yellow))
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "With padding",
            modifier = Modifier
                .background(Color.Cyan)
                .padding(16.dp)
        )
    }
}

@Composable
fun ExperimentWeightFill() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f)
        ) {
            Text("1x Weight")
        }
        Button(
            onClick = {},
            modifier = Modifier.weight(2f)
        ) {
            Text("2x Weight")
        }
    }
}

@Composable
fun ExperimentArrangementAlign() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Left")
        Text(text = "Right")
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun Task3PreviewPhone() {
    Task3Screen()
}

@Preview(showBackground = true, widthDp = 700, heightDp = 1000)
@Composable
fun Task3PreviewTablet() {
    Task3Screen()
}