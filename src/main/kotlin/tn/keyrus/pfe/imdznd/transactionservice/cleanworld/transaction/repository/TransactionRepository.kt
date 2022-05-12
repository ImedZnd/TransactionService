package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.repository

import kotlinx.coroutines.flow.Flow
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Optional

interface TransactionRepository {
    fun findAllTransactions(): Flow<Transaction>
    fun findAllTransactionsByCurrency(currency: String): Flow<Transaction>
    fun findAllTransactionsByState(state: Transaction.TransactionState): Flow<Transaction>
    fun findAllTransactionsByCreatedDate(createdDate: LocalDateTime): Flow<Transaction>
    fun findAllTransactionsByCreatedDateBetween(startDate: LocalDate, endDate: LocalDate = startDate): Flow<Transaction>
    fun findAllTransactionsByCreatedDateBefore(beforeDate: LocalDateTime): Flow<Transaction>
    fun findAllTransactionsByCreatedDateAfter(fromDate: LocalDateTime): Flow<Transaction>
    fun findAllTransactionsByMerchantCategory(merchantCategory: String): Flow<Transaction>
    fun findAllTransactionsByMerchantCountry(merchantCountry: String): Flow<Transaction>
    fun findAllTransactionsByEntryMethod(entryMethod: Transaction.TransactionEntryMethod): Flow<Transaction>
    fun findAllTransactionsByUserId(userId: Long): Flow<Transaction>
    fun findAllTransactionsByType(type: Transaction.TransactionType): Flow<Transaction>
    fun findAllTransactionsBySource(source: Transaction.TransactionSource): Flow<Transaction>
    fun findAllTransactionsByCurrencyAndCreatedDate(currency: String, createdDate: LocalDateTime): Flow<Transaction>
    fun findAllTransactionsByMerchantCategoryAndUserId(merchantCountry: String, userId: Long): Flow<Transaction>
    fun findAllTransactionsByTypeAndUserId(type: Transaction.TransactionType, userId: Long): Flow<Transaction>
    fun findAllTransactionsByEntryMethodAndUserId(entryMethod: Transaction.TransactionEntryMethod, userId: Long): Flow<Transaction>
    fun saveTransaction(transaction: Transaction): Optional<Transaction>

    sealed interface TransactionRepositoryError{
        object TransactionRepositoryIOError: TransactionRepositoryError
    }
}