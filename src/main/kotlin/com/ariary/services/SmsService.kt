package com.ariary.services

import com.ariary.client.ApiClient
import com.ariary.types.*
import com.google.gson.Gson

class SmsService(private val apiClient: ApiClient) {
    private val gson = Gson()

    fun sendMultiSms(dto: SendSmsDto): MultiSmsResponseDto {
        val response = apiClient.post("/sms/multi", dto)
        return gson.fromJson(gson.toJson(response), MultiSmsResponseDto::class.java)
    }

    fun sendBulkSms(dto: BulkSmsDto): BulkSmsResponseDto {
        val response = apiClient.post("/sms/bulk", dto)
        return gson.fromJson(gson.toJson(response), BulkSmsResponseDto::class.java)
    }

    fun getSmsHistory(): List<Map<String, Any>> {
        val response = apiClient.get("/sms")
        @Suppress("UNCHECKED_CAST")
        return (response["data"] as? List<Map<String, Any>>) ?: emptyList()
    }

    fun getSmsById(id: String): ResponseSmsDto {
        val response = apiClient.get("/sms/$id")
        val data = (response["data"] as? Map<String, Any>) ?: response
        return gson.fromJson(gson.toJson(data), ResponseSmsDto::class.java)
    }

    fun updateSms(id: String, data: Map<String, Any>): ResponseSmsDto {
        val response = apiClient.patch("/sms/$id", data)
        val responseData = (response["data"] as? Map<String, Any>) ?: response
        return gson.fromJson(gson.toJson(responseData), ResponseSmsDto::class.java)
    }

    fun deleteSms(id: String): Map<String, Any> {
        return apiClient.delete("/sms/$id")
    }
}
