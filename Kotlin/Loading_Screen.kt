@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("로딩중....", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(16.dp))
        Text("그거 아시나요? 북극곰은 지구 온난화로 인해 개체수가 증가하였습니다.")
    }
}
