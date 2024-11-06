@Composable
fun NewProjectScreen(onCreateProject: (String, String, String) -> Unit) {
    var projectName by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            value = projectName,
            onValueChange = { projectName = it },
            label = { Text("프로젝트명") }
        )
        OutlinedTextField(
            value = startDate,
            onValueChange = { startDate = it },
            label = { Text("시작일") }
        )
        OutlinedTextField(
            value = endDate,
            onValueChange = { endDate = it },
            label = { Text("종료일") }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                onCreateProject(projectName, startDate, endDate)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("START")
        }
    }
}
