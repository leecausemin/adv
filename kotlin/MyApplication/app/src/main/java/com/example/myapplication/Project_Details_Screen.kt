package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProjectDetailsScreen(projectName: String, projectDate: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = projectName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = projectDate, fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* TODO: Implement print functionality */ }) {
            Text("PRINT")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* TODO: Restart project */ }) {
            Text("RESTART")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectDetailsScreenPreview() {
    ProjectDetailsScreen(projectName = "Sample Project", projectDate = "2024.11.06")
}
