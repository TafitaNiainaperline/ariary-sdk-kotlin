package com.ariary.services

import com.ariary.client.ApiClient
import com.ariary.types.CreatePaymentDto
import com.ariary.types.PaymentResponseDto
import com.google.gson.Gson

class PaymentService(private val apiClient: ApiClient) {
    private val gson = Gson()

    fun createPayment(dto: CreatePaymentDto): PaymentResponseDto {
        val response = apiClient.post("/payment", dto)
        val data = (response["data"] as? Map<String, Any>) ?: response
        return gson.fromJson(gson.toJson(data), PaymentResponseDto::class.java)
    }

    fun getAllPayments(): List<PaymentResponseDto> {
        val response = apiClient.get("/payment")
        @Suppress("UNCHECKED_CAST")
        val dataList = (response["data"] as? List<Map<String, Any>>) ?: emptyList()
        return dataList.map { gson.fromJson(gson.toJson(it), PaymentResponseDto::class.java) }
    }

    fun getPaymentById(id: String): PaymentResponseDto {
        val response = apiClient.get("/payment/$id")
        val data = (response["data"] as? Map<String, Any>) ?: response
        return gson.fromJson(gson.toJson(data), PaymentResponseDto::class.java)
    }

    fun updatePaymentRest(id: String, ticketCode: String): PaymentResponseDto {
        val data = mapOf("ticketCode" to ticketCode)
        val response = apiClient.patch("/payment/$id", data)
        val responseData = (response["data"] as? Map<String, Any>) ?: response
        return gson.fromJson(gson.toJson(responseData), PaymentResponseDto::class.java)
    }
}
