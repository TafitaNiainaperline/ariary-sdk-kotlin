import com.ariary.AriarySDK
import com.ariary.types.*

fun main() {
    // Initialiser le SDK avec les credentials
    val config = ApiConfig(
        apiKey = "votre_api_key",
        secretId = "votre_secret_id",
        projectId = "votre_project_id"
    )

    val sdk = AriarySDK(config)

    try {
        // ============ EXEMPLES DE PAIEMENT ============
        println("=== Exemples de paiement ===")

        // Créer un paiement
        val payment = sdk.payment.createPayment(
            CreatePaymentDto(
                code = "PAY-001",
                amount = 50000,
                projectId = "votre_project_id"
            )
        )
        println("Paiement créé: $payment")

        // Récupérer tous les paiements
        val allPayments = sdk.payment.getAllPayments()
        println("Tous les paiements: $allPayments")

        // Récupérer un paiement spécifique
        val paymentById = sdk.payment.getPaymentById(payment.id)
        println("Paiement par ID: $paymentById")

        // Mettre à jour le reste d'un paiement
        val updatedPayment = sdk.payment.updatePaymentRest(payment.id, "TICKET123")
        println("Paiement mis à jour: $updatedPayment")

        // ============ EXEMPLES SMS ============
        println("\n=== Exemples SMS ===")

        // Envoyer un SMS à plusieurs numéros
        val multiSms = sdk.sms.sendMultiSms(
            SendSmsDto(
                phones = listOf("261345678901", "261345678902", "261345678903"),
                message = "Message à tous"
            )
        )
        println("SMS multiples envoyés: $multiSms")

        // Envoyer des SMS différents en masse
        val bulkSms = sdk.sms.sendBulkSms(
            BulkSmsDto(
                messages = listOf(
                    mapOf("phones" to listOf("261345678901"), "message" to "Message 1"),
                    mapOf("phones" to listOf("261345678902"), "message" to "Message 2"),
                    mapOf("phones" to listOf("261345678903"), "message" to "Message 3")
                )
            )
        )
        println("SMS en masse envoyés: $bulkSms")

        // Récupérer l'historique des SMS
        val smsHistory = sdk.sms.getSmsHistory()
        println("Historique des SMS: $smsHistory")

        // Récupérer un SMS spécifique
        if (multiSms.data.isNotEmpty()) {
            val smsById = sdk.sms.getSmsById((multiSms.data[0]["id"] as? String) ?: "")
            println("SMS par ID: $smsById")

            // Mettre à jour un SMS
            val updatedSms = sdk.sms.updateSms(
                (multiSms.data[0]["id"] as? String) ?: "",
                mapOf("message" to "Message mis à jour")
            )
            println("SMS mis à jour: $updatedSms")
        }

        // ============ EXEMPLES DE TRANSFERT ============
        println("\n=== Exemples de transfert ===")

        // Envoyer une transaction
        val transaction = sdk.transfer.sendTransaction(
            SendTransactionDto(
                phone = "261345678901",
                amount = 100000
            )
        )
        println("Transaction envoyée: $transaction")

        // Récupérer toutes les transactions
        val allTransactions = sdk.transfer.getAllTransactions()
        println("Toutes les transactions: $allTransactions")

        // Récupérer une transaction spécifique
        val transactionById = sdk.transfer.getTransactionById(transaction.id)
        println("Transaction par ID: $transactionById")

        // Mettre à jour une transaction
        val updatedTransaction = sdk.transfer.updateTransaction(
            transaction.id,
            SendTransactionDto(
                phone = "261345678902",
                amount = 150000
            )
        )
        println("Transaction mise à jour: $updatedTransaction")

    } catch (e: Exception) {
        println("Erreur: ${e.message}")
        e.printStackTrace()
    }
}
