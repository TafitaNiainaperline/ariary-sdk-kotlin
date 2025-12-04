package com.ariary.client

import com.ariary.types.ApiConfig
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class ApiClient(private val config: ApiConfig) {
    private val client = OkHttpClient()
    private val gson = Gson()
    private val baseUrl = "https://fs-pay-rifont.atydago.com/payment"

    private fun getHeaders(): Map<String, String> {
        return mapOf(
            "Authorization" to "Bearer ${config.apiKey}",
            "X-Secret-Id" to config.secretId,
            "Content-Type" to "application/json"
        )
    }

    fun post(endpoint: String, data: Any): Map<String, Any> {
        val jsonData = gson.toJson(data)
        val requestBody = jsonData.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("$baseUrl$endpoint")
            .post(requestBody)
            .apply {
                getHeaders().forEach { (key, value) ->
                    addHeader(key, value)
                }
            }
            .build()

        return executeRequest(request)
    }

    fun get(endpoint: String): Map<String, Any> {
        val request = Request.Builder()
            .url("$baseUrl$endpoint")
            .get()
            .apply {
                getHeaders().forEach { (key, value) ->
                    addHeader(key, value)
                }
            }
            .build()

        return executeRequest(request)
    }

    fun patch(endpoint: String, data: Any): Map<String, Any> {
        val jsonData = gson.toJson(data)
        val requestBody = jsonData.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("$baseUrl$endpoint")
            .patch(requestBody)
            .apply {
                getHeaders().forEach { (key, value) ->
                    addHeader(key, value)
                }
            }
            .build()

        return executeRequest(request)
    }

    fun put(endpoint: String, data: Any): Map<String, Any> {
        val jsonData = gson.toJson(data)
        val requestBody = jsonData.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("$baseUrl$endpoint")
            .put(requestBody)
            .apply {
                getHeaders().forEach { (key, value) ->
                    addHeader(key, value)
                }
            }
            .build()

        return executeRequest(request)
    }

    fun delete(endpoint: String): Map<String, Any> {
        val request = Request.Builder()
            .url("$baseUrl$endpoint")
            .delete()
            .apply {
                getHeaders().forEach { (key, value) ->
                    addHeader(key, value)
                }
            }
            .build()

        return executeRequest(request)
    }

    @Suppress("UNCHECKED_CAST")
    private fun executeRequest(request: Request): Map<String, Any> {
        return try {
            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: "{}"
            gson.fromJson(body, Map::class.java) as Map<String, Any>
        } catch (e: Exception) {
            throw RuntimeException("API Error: ${e.message}", e)
        }
    }
}
