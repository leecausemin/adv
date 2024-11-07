package com.example.myapplication

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// 응답 데이터 클래스 설정
data class ClassificationResponse(val result: String)
data class Keyword(val word: String, val score: Float)
data class KeywordsResponse(val keywords: List<Keyword>)
data class GenerateImageResponse(val result: String)

// Retrofit 인터페이스 정의
interface ApiService {
    @POST("/model")
    suspend fun classifyText(@Body text: String): Response<ClassificationResponse>

    @POST("/keywords")
    suspend fun extractKeywords(@Body userPrompt: String): Response<KeywordsResponse>

    @POST("/generate")
    suspend fun generateImage(@Body word: String): Response<GenerateImageResponse>
}
