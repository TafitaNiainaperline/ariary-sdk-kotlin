package com.ariary

import com.ariary.types.ApiConfig
import com.ariary.client.ApiClient
import com.ariary.services.PaymentService
import com.ariary.services.SmsService
import com.ariary.services.TransferService

class AriarySDK(config: ApiConfig) {
    private val apiClient = ApiClient(config)

    val payment = PaymentService(apiClient)
    val sms = SmsService(apiClient)
    val transfer = TransferService(apiClient)
}
