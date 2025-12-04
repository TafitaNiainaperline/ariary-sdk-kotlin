package com.ariary.services

import com.ariary.client.ApiClient
import com.ariary.types.*
import com.google.gson.Gson

class TransferService(private val apiClient: ApiClient) {
    private val gson = Gson()

    fun sendTransaction(dto: SendTransactionDto): SendTransactionResponse {
        val response = apiClient.post("/transfer", dto)
        val data = (response["data"] as? Map<String, Any>) ?: response
        return gson.fromJson(gson.toJson(data), SendTransactionResponse::class.java)
    }

    fun getAllTransactions(): List<TransactionResponseDto> {
        val response = apiClient.get("/transfer")
        @Suppress("UNCHECKED_CAST")
        val dataList = (response["data"] as? List<Map<String, Any>>) ?: emptyList()
        return dataList.map { gson.fromJson(gson.toJson(it), TransactionResponseDto::class.java) }
    }

    fun getTransactionById(id: String): TransactionResponseDto {
        val response = apiClient.get("/transfer/$id")
        val data = (response["data"] as? Map<String, Any>) ?: response
        return gson.fromJson(gson.toJson(data), TransactionResponseDto::class.java)
    }

    fun updateTransaction(id: String, dto: SendTransactionDto): TransactionResponseDto {
        val response = apiClient.put("/transfer/$id", dto)
        val data = (response["data"] as? Map<String, Any>) ?: response
        return gson.fromJson(gson.toJson(data), TransactionResponseDto::class.java)
    }
}
