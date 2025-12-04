package com.ariary.types

data class CreatePaymentDto(
    val code: String,
    val amount: Int,
    val projectId: String
)

data class PaymentResponseDto(
    val id: String,
    val code: String,
    val amount: Int,
    val projectId: String,
    val status: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
