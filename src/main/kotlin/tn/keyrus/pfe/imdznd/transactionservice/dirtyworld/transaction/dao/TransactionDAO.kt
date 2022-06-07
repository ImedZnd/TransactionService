package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.dao

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction
import java.time.LocalDateTime

@Table("transaction")
data class TransactionDAO(
    @Id
    val id: Long? = null,
    val currency: String = "",
    val amount: Double = 0.0,
    val state: Transaction.TransactionState = Transaction.TransactionState.COMPLETED,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val merchantCategory: String = "",
    val merchantCountry: String = "",
    val entryMethod: Transaction.TransactionEntryMethod = Transaction.TransactionEntryMethod.MISC,
    val userId: Long = 0,
    val type: Transaction.TransactionType = Transaction.TransactionType.P2P,
    val source: Transaction.TransactionSource = Transaction.TransactionSource.NYX,
    val flaged: Boolean = false
) {

    fun toTransaction() =
        Transaction.of(
            id,
            currency,
            amount,
            state,
            createdDate,
            merchantCategory,
            merchantCountry,
            entryMethod,
            userId,
            type,
            source,
            flaged
        )

    companion object {
        fun Transaction.toDAO() =
            TransactionDAO(
                id = transactionId,
                currency = currency,
                amount = amount,
                state = state,
                createdDate = createdDate,
                merchantCategory = merchantCategory,
                merchantCountry = merchantCountry,
                entryMethod = entryMethod,
                userId = userId,
                type = type,
                source = source,
                flaged = flaged
            )

    }

}