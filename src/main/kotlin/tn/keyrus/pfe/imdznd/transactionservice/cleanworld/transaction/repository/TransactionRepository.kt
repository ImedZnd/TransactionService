package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.repository

import io.vavr.control.Either
import kotlinx.coroutines.flow.Flow
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto.*
import java.time.LocalDateTime

interface TransactionRepository {
    fun findAllTransactions(): Flow<Transaction>
    fun countyAndNumberCount(): Flow<CountryAndNumber>
    fun findAllTransactionsByCurrency(currency: String): Flow<Transaction>
    fun findAllTransactionsByState(state: Transaction.TransactionState): Flow<Transaction>
    fun findAllTransactionsByCreatedDate(createdDate: LocalDateTime): Flow<Transaction>
    fun findAllTransactionsByCreatedDateBetween(
        startDate: LocalDateTime,
        endDate: LocalDateTime = startDate
    ): Flow<Transaction>

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
    fun findAllTransactionsByEntryMethodAndUserId(
        entryMethod: Transaction.TransactionEntryMethod,
        userId: Long
    ): Flow<Transaction>
    suspend fun countAll(): Int
    suspend fun countAllTransactionsByCurrency(currency: String): Int
    suspend fun countAllTransactionsByState(state: Transaction.TransactionState): Int
    suspend fun countAllTransactionsByType(type: Transaction.TransactionType): Int
    suspend fun countAllTransactionsBySource(source: Transaction.TransactionSource): Int
    suspend fun countAllTransactionsMerchantCategory(merchantCategory: String): Int
    suspend fun countAllTransactionsMerchantCountry(merchantCountry: String): Int
    suspend fun countAllTransactionsEntryMethod(entryMethod: Transaction.TransactionEntryMethod): Int
    suspend fun countAllTransactionsUserId(userId: Long): Int
    suspend fun countAllByFlaged(flaged: Boolean): Int
    suspend fun countAllByEntryMethodAndUserId(entryMethod: Transaction.TransactionEntryMethod, userId: Long): Int
    suspend fun countAllByCurrencyAndUserId(currency: String, userId: Long): Int
    suspend fun countAllByStateAndUserId(state: Transaction.TransactionState, userId: Long): Int
    suspend fun countAllByTypeAndUserId(type: Transaction.TransactionType, userId: Long): Int
    suspend fun countAllBySourceAndUserId(source: Transaction.TransactionSource, userId: Long): Int
    suspend fun countAllByMerchantCategoryLikeAndUserId(merchantCountry: String, userId: Long): Int
    suspend fun countAllByMerchantCountryAndUserId(merchantCategory: String, userId: Long): Int
    suspend fun countAllByFlagedAndUserId(flaged: Boolean, userId: Long): Int
    suspend fun countAllFlagedAndUserId(userId: Long): Flow<FlagedAndNumber>
    suspend fun countAllByCreatedDateContains(year: String): Int
    suspend fun saveTransaction(transaction: Transaction): Either<TransactionRepositoryError, Transaction>
    suspend fun flagTransaction(transactionId: Long): Either<TransactionRepositoryError, Transaction>
    fun publishFlagTransaction(transactionId: Long)
    suspend fun stateAndNumber(): Flow<StateAndNumber>
    suspend fun entryAndNumber(): Flow<EntryAndNumber>
    suspend fun typeAndNumber(): Flow<TypeAndNumber>
    suspend fun sourceAndNumber(): Flow<SourceAndNumber>
    suspend fun countEntryMethodAndNumberByUserId(userId: Long): Flow<EntryAndNumber>
    suspend fun countCurrencyAndNumberByUserId(userId: Long): Flow<StringAndNumber>
    suspend fun countStateAndNumberByUserId(userId: Long): Flow<StateAndNumber>
    suspend fun countTypeAndNumberByUserId(userId: Long): Flow<TypeAndNumber>
    suspend fun countSourceAndNumberByUserId(userId: Long): Flow<SourceAndNumber>

    sealed interface TransactionRepositoryError {
        object TransactionRepositoryIOError : TransactionRepositoryError
        object TransactionNotExistRepositoryError : TransactionRepositoryError
        object TransactionFlagedRepositoryIOError : TransactionRepositoryError
    }
}