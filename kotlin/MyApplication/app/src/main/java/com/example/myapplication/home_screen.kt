package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("내 프로젝트", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        ProjectList(projects = listOf(
            Project("New Project 01", "2024.11.06"),
            Project("가위눌린 날...ㅜㅜ", "2024.10.28"),
            Project("비가 갑자기 왔다.", "2024.10.15"),
            Project("낙엽이 예쁜 하루", "2024.10.15")
        ))
    }
}

@Composable
fun ProjectList(projects: List<Project>) {
    LazyColumn {
        items(projects) { project ->
            ProjectItem(project = project)
        }
    }
}

@Composable
fun ProjectItem(project: Project) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (project.isActive) Color.Green else Color.Blue
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(project.name, fontSize = 18.sp)
            Text(project.date, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

data class Project(val name: String, val date: String, val isActive: Boolean = true)
