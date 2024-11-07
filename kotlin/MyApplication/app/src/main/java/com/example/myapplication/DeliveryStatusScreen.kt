import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeliveryStatusScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // 상단 앱바
        TopAppBar(title = { Text("배송 상황", fontSize = 20.sp) }, navigationIcon = {
            IconButton(onClick = { /* 뒤로 가기 동작 */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "Back")
            }
        })

        Spacer(modifier = Modifier.height(8.dp))

        // 진행 상태 표시 바
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_truck), contentDescription = "Truck")
            LinearProgressIndicator(
                progress = 0.5f,
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                color = Color(0xFF4CAF50),
                trackColor = Color.LightGray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 상품 정보
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.ic_sample_image),
                contentDescription = "Product Image",
                modifier = Modifier.size(80.dp).padding(end = 8.dp)
            )
            Column {
                Text("스케이트보드", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("New Project 01", fontSize = 16.sp)
                Text("2024.11.06", fontSize = 14.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 송장 정보 및 전화 버튼
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text("송장번호", fontWeight = FontWeight.Bold)
                Text("택배사", fontWeight = FontWeight.Bold)
            }
            Column {
                Text("물건이 준비중입니다.")
                Text("물건이 준비중입니다.")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { /* 택배사 전화하기 동작 */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF263238))) {
                Text("택배사 전화하기", color = Color.White)
            }
            Button(onClick = { /* 배송기사 전화하기 동작 */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF263238))) {
                Text("배송기사 전화하기", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 수령인 정보 섹션
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("이유민", fontWeight = FontWeight.Bold)
                Text("우편번호 : 34134")
                Text("주소 : 대전광역시 유성구 궁동 대학교 99 공과대학 5호관 1층")
                Text("전화번호 : 010-1234-5678")
                Text("배송요청사항 : 없음")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 경고 메시지와 수정 버튼
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFDE7)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_warning),
                    contentDescription = "Warning",
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("혹시 배송정보를 잘못 입력했나요?", color = Color.Red)
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { /* 수정하기 동작 */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0F7FA))) {
                    Text("변경하기", color = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 하단 문의하기 버튼
        TextButton(onClick = { /* 문의하기 동작 */ }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("문의하기", color = Color.Gray)
        }
    }
}


