package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.dao

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction
import java.time.LocalDateTime

@Table("transaction")
data class TransactionDAO(
    @Id
    val id: Long? = null,
    val currency: String,
    val amount: Double,
    val state: Transaction.TransactionState,
    val createdDate: LocalDateTime,
    val merchantCategory: String,
    val merchantCountry: String,
    val entryMethod: Transaction.TransactionEntryMethod,
    val userId: Long,
    val type: Transaction.TransactionType,
    val source: Transaction.TransactionSource,
    ) {

    fun toTransaction() =
        Transaction.of(
            currency,
            amount,
            state,
            createdDate,
            merchantCategory,
            merchantCountry,
            entryMethod,
            userId,
            type,
            source
        )

    companion object{
        fun Transaction.toDAO(){
            TransactionDAO(
                currency = currency,
                amount = amount,
                state = state,
                createdDate =createdDate,
                merchantCategory = merchantCategory,
                merchantCountry = merchantCountry,
                entryMethod = entryMethod,
                userId = userId,
                type = type,
                source = source
            )
        }
    }

}