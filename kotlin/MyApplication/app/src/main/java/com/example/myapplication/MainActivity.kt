package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.core.view.WindowCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

// 데이터 클래스 정의
data class ClassificationResponse(val result: String)
data class KeywordsResponse(val keywords: List<Pair<String, Float>>)
data class GenerateImageResponse(val result: String)

// Retrofit API 인터페이스 정의
interface ApiService {
    @POST("/model")
    suspend fun classifyText(@Body text: String): Response<ClassificationResponse>

    @POST("/keywords")
    suspend fun extractKeywords(@Body userPrompt: String): Response<KeywordsResponse>

    @POST("/generate")
    suspend fun generateImage(@Body word: String): Response<GenerateImageResponse>
}

// Retrofit 클라이언트 객체 생성
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8000"  // 에뮬레이터에서 로컬 호스트에 접근할 때 사용하는 주소

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false) // 전체 화면 모드 설정

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(Modifier.padding(innerPadding))
                }
            }
        }

        // 서버 호출 예제
        callClassifyTextApi("Hello from Android!")
        callExtractKeywordsApi("This is an example prompt")
        callGenerateImageApi("Tree")
    }

    // 텍스트 분류 API 호출
    private fun callClassifyTextApi(text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.classifyText(text)
                if (response.isSuccessful) {
                    val result = response.body()?.result
                    Log.d("API Response", "Classification result: $result")
                } else {
                    Log.e("API Error", "Request failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API Exception", "Error: ${e.message}")
            }
        }
    }

    // 키워드 추출 API 호출
    private fun callExtractKeywordsApi(userPrompt: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.extractKeywords(userPrompt)
                if (response.isSuccessful) {
                    val keywords = response.body()?.keywords
                    Log.d("API Response", "Extracted keywords: $keywords")
                } else {
                    Log.e("API Error", "Request failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API Exception", "Error: ${e.message}")
            }
        }
    }

    // 이미지 생성 API 호출
    private fun callGenerateImageApi(word: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.generateImage(word)
                if (response.isSuccessful) {
                    val result = response.body()?.result
                    Log.d("API Response", "Generated image result: $result")
                } else {
                    Log.e("API Error", "Request failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API Exception", "Error: ${e.message}")
            }
        }
    }
}

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "greeting", modifier = modifier) {
        composable("greeting") {
            Greeting(name = "Android")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}
