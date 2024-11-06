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
        backgroundColor = if (project.isActive) Color.Green else Color.Blue,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(project.name, fontSize = 18.sp)
            Text(project.date, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

data class Project(val name: String, val date: String, val isActive: Boolean = true)
