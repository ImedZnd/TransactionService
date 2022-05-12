package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.repository

import kotlinx.coroutines.flow.Flow
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction

interface TransactionRepository {
    fun findAllTransactions(): Flow<Transaction>
    fun findAllTransactionsByCurrency(): Flow<Transaction>
    fun findAllTransactionsByState(): Flow<Transaction>
    fun findAllTransactionsByCreatedDate(): Flow<Transaction>
    fun findAllTransactionsByCreatedDateBetween(): Flow<Transaction>
    fun findAllTransactionsByCreatedDateBefore(): Flow<Transaction>
    fun findAllTransactionsByCreatedDateAfter(): Flow<Transaction>
    fun findAllTransactionsByMerchantCategory(): Flow<Transaction>
    fun findAllTransactionsByMerchantCountry(): Flow<Transaction>
    fun findAllTransactionsByEntryMethod(): Flow<Transaction>
    fun findAllTransactionsByUserId(): Flow<Transaction>
    fun findAllTransactionsByType(): Flow<Transaction>
    fun findAllTransactionsByCurrencyAndCreatedDate(): Flow<Transaction>
    fun findAllTransactionsByMerchantCategoryAndUserId(): Flow<Transaction>
    fun findAllTransactionsByTypeAndUserId(): Flow<Transaction>
    fun findAllTransactionsByEntryMethodAndUserId(): Flow<Transaction>
}