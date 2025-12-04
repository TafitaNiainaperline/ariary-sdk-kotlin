package com.ariary.types

data class SendTransactionDto(
    val phone: String,
    val amount: Int
)

data class SendTransactionResponse(
    val id: String,
    val phone: String,
    val amount: Int,
    val status: String,
    val createdAt: String? = null
)

data class TransactionResponseDto(
    val id: String,
    val phone: String,
    val amount: Int,
    val status: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
