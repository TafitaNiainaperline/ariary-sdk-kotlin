# ariary-kotlin-sdk

SDK officiel Kotlin pour l'API de paiement Ariary. Permet d'envoyer des paiements, des SMS et des transferts d'argent.

## Installation

Ajoute la dépendance à ton `pom.xml`:

```xml
<dependency>
    <groupId>com.ariary</groupId>
    <artifactId>ariary-kotlin-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Configuration

```kotlin
import com.ariary.AriarySDK
import com.ariary.types.ApiConfig

val config = ApiConfig(
    apiKey = "votre_api_key",
    secretId = "votre_secret_id",
    projectId = "votre_project_id"
)

val sdk = AriarySDK(config)
```

## Utilisation

### Paiement

```kotlin
import com.ariary.types.CreatePaymentDto

// Créer un paiement
val payment = sdk.payment.createPayment(CreatePaymentDto(
    code = "PAY-K1X2Y3Z4-ABC123",
    amount = 5000,
    projectId = "votre_project_id"
))

println(payment.id)

// Récupérer tous les paiements
val allPayments = sdk.payment.getAllPayments()

// Récupérer un paiement par ID
val payment = sdk.payment.getPaymentById(payment.id)

// Mettre à jour le reste d'un paiement
val updated = sdk.payment.updatePaymentRest(payment.id, "TICKET123")
```

### SMS

```kotlin
import com.ariary.types.SendSmsDto
import com.ariary.types.BulkSmsDto

// Envoyer un SMS à plusieurs numéros
val result = sdk.sms.sendMultiSms(SendSmsDto(
    phones = listOf("261345678901", "261345678902"),
    message = "Bonjour!"
))

// Envoyer des messages différents en masse
val bulkResult = sdk.sms.sendBulkSms(BulkSmsDto(
    messages = listOf(
        mapOf("phones" to listOf("261345678901"), "message" to "Message 1"),
        mapOf("phones" to listOf("261345678902"), "message" to "Message 2")
    )
))

// Récupérer l'historique des SMS
val history = sdk.sms.getSmsHistory()

// Récupérer un SMS par ID
val sms = sdk.sms.getSmsById(smsId)

// Mettre à jour un SMS
val updated = sdk.sms.updateSms(smsId, mapOf("message" to "Nouveau message"))

// Supprimer un SMS
sdk.sms.deleteSms(smsId)
```

### Transfer

```kotlin
import com.ariary.types.SendTransactionDto

// Envoyer une transaction
val transaction = sdk.transfer.sendTransaction(SendTransactionDto(
    phone = "261345678901",
    amount = 5000
))

// Récupérer toutes les transactions
val allTransactions = sdk.transfer.getAllTransactions()

// Récupérer une transaction par ID
val transaction = sdk.transfer.getTransactionById(transactionId)

// Mettre à jour une transaction
val updated = sdk.transfer.updateTransaction(transactionId, SendTransactionDto(
    phone = "261345678902",
    amount = 10000
))
```

## Types

Tous les types de données sont disponibles dans le package `com.ariary.types`:

```kotlin
import com.ariary.types.*

// Payment types
CreatePaymentDto
PaymentResponseDto

// SMS types
SendSmsDto
BulkSmsDto
SendSmsResponseDto
MultiSmsResponseDto
BulkSmsResponseDto
ResponseSmsDto

// Transfer types
SendTransactionDto
SendTransactionResponse
TransactionResponseDto

// Configuration
ApiConfig
```

## License

ISC
