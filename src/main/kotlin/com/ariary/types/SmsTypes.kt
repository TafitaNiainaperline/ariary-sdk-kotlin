package com.ariary.types

data class SendSmsDto(
    val phones: List<String>,
    val message: String
)

data class BulkSmsDto(
    val messages: List<Map<String, Any>>
)

data class SendSmsResponseDto(
    val id: String,
    val phones: List<String>,
    val message: String,
    val status: String,
    val createdAt: String? = null
)

data class MultiSmsResponseDto(
    val data: List<Map<String, Any>>,
    val message: String,
    val statusCode: Int
)

data class BulkSmsResponseDto(
    val data: List<Map<String, Any>>,
    val message: String,
    val statusCode: Int
)

data class ResponseSmsDto(
    val id: String,
    val phones: List<String>,
    val message: String,
    val status: String,
    val createdAt: String? = null
)
