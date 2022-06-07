package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.service

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.repository.TransactionRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto.*
import java.time.LocalDateTime

class TransactionService(
    private val transactionRepository: TransactionRepository
) {
    fun findAllTransactions() =
        transactionRepository.findAllTransactions()

    fun countyAndNumberCount() =
        transactionRepository.countyAndNumberCount()

    suspend fun stateAndNumber() =
        transactionRepository.stateAndNumber()

    suspend fun entryAndNumber() =
        transactionRepository.entryAndNumber()

    suspend fun typeAndNumber() =
        transactionRepository.typeAndNumber()

    suspend fun sourceAndNumber() =
        transactionRepository.sourceAndNumber()

    fun flagAndNumber() =
        transactionRepository.findAllTransactions()
            .map{ it.flaged }
            .map (::flagToFlagAndNumber)

    suspend fun totalOfAmount(): Double {
        var x = 0.0
        transactionRepository.findAllTransactions()
            .map{ it.amount }
            .map {x+=it}
            .collect()
        return x
    }

    private suspend fun flagToFlagAndNumber(flaged: Boolean)=
        FlagAndNumber(
            flaged,
            countAllByFlag(flaged)
        )

    suspend fun countAllByFlag(flaged: Boolean)=
        transactionRepository.countAllByFlaged(flaged)

    suspend fun countAllBySource(source: Transaction.TransactionSource)=
        transactionRepository.countAllTransactionsBySource(source)

    suspend fun countAllByType(type: Transaction.TransactionType)=
        transactionRepository.countAllTransactionsByType(type)

    suspend fun countAllByEntryMethod(entryMethod: Transaction.TransactionEntryMethod)=
        transactionRepository.countAllTransactionsEntryMethod(entryMethod)

    suspend fun countAllByState(state: Transaction.TransactionState)=
        transactionRepository.countAllTransactionsByState(state)

    fun findAllTransactionsByCurrency(currency: String) =
        transactionRepository.findAllTransactionsByCurrency(currency)

    fun findAllTransactionsByState(state: Transaction.TransactionState) =
        transactionRepository.findAllTransactionsByState(state)

    fun findAllTransactionsByCreatedDate(createdDate: LocalDateTime) =
        transactionRepository.findAllTransactionsByCreatedDate(createdDate)

    fun findAllTransactionsByCreatedDateBetween(startDate: LocalDateTime, endDate: LocalDateTime = startDate) =
        transactionRepository.findAllTransactionsByCreatedDateBetween(startDate, endDate)

    fun findAllTransactionsByCreatedDateBefore(beforeDate: LocalDateTime) =
        transactionRepository.findAllTransactionsByCreatedDateBefore(beforeDate)

    fun findAllTransactionsByCreatedDateAfter(fromDate: LocalDateTime) =
        transactionRepository.findAllTransactionsByCreatedDateAfter(fromDate)

    fun findAllTransactionsByMerchantCategory(merchantCategory: String) =
        transactionRepository.findAllTransactionsByMerchantCategory(merchantCategory)

    fun findAllTransactionsByMerchantCountry(merchantCountry: String) =
        transactionRepository.findAllTransactionsByMerchantCountry(merchantCountry)

    fun findAllTransactionsByEntryMethod(entryMethod: Transaction.TransactionEntryMethod) =
        transactionRepository.findAllTransactionsByEntryMethod(entryMethod)

    fun findAllTransactionsByUserId(userId: Long) =
        transactionRepository.findAllTransactionsByUserId(userId)

    fun findAllTransactionsByType(type: Transaction.TransactionType) =
        transactionRepository.findAllTransactionsByType(type)

    fun findAllTransactionsBySource(source: Transaction.TransactionSource) =
        transactionRepository.findAllTransactionsBySource(source)

    fun findAllTransactionsByCurrencyAndCreatedDate(currency: String, createdDate: LocalDateTime) =
        transactionRepository.findAllTransactionsByCurrencyAndCreatedDate(currency, createdDate)

    fun findAllTransactionsByMerchantCategoryAndUserId(merchantCountry: String, userId: Long) =
        transactionRepository.findAllTransactionsByMerchantCategoryAndUserId(merchantCountry, userId)

    fun findAllTransactionsByTypeAndUserId(type: Transaction.TransactionType, userId: Long) =
        transactionRepository.findAllTransactionsByTypeAndUserId(type, userId)

    fun findAllTransactionsByEntryMethodAndUserId(entryMethod: Transaction.TransactionEntryMethod, userId: Long) =
        transactionRepository.findAllTransactionsByEntryMethodAndUserId(entryMethod, userId)

    suspend fun countEntryMethodAndNumberByUserId(userId: Long)=
        transactionRepository.countEntryMethodAndNumberByUserId(userId)

    suspend fun countCurrencyAndNumberByUserId(userId: Long)=
        transactionRepository.countCurrencyAndNumberByUserId(userId)

    suspend fun countStateAndNumberByUserId(userId: Long)=
        transactionRepository.countStateAndNumberByUserId(userId)

    suspend fun countTypeAndNumberByUserId(userId: Long)=
        transactionRepository.countTypeAndNumberByUserId(userId)

    suspend fun countSourceAndNumberByUserId(userId: Long)=
        transactionRepository.countSourceAndNumberByUserId(userId)

    suspend fun countAllTransactionsByCurrency(currency: String) =
        transactionRepository.countAllTransactionsByCurrency(currency)

    suspend fun countAllTransactionsByState(state: Transaction.TransactionState) =
        transactionRepository.countAllTransactionsByState(state)

    suspend fun countAllTransactionsByType(type: Transaction.TransactionType) =
        transactionRepository.countAllTransactionsByType(type)

    suspend fun countAllTransactionsBySource(source: Transaction.TransactionSource) =
        transactionRepository.countAllTransactionsBySource(source)

    suspend fun countAllTransactionsMerchantCategory(merchantCategory: String) =
        transactionRepository.countAllTransactionsMerchantCategory(merchantCategory)

    suspend fun countAllTransactionsMerchantCountry(merchantCountry: String) =
        transactionRepository.countAllTransactionsMerchantCountry(merchantCountry)
            .also { print(merchantCountry) }

    suspend fun countAllTransactionsEntryMethod(entryMethod: Transaction.TransactionEntryMethod) =
        transactionRepository.countAllTransactionsEntryMethod(entryMethod)

    suspend fun countAllTransactionsUserId(userId: Long) =
        transactionRepository.countAllTransactionsUserId(userId)

    suspend fun countAllByEntryMethodAndUserId(entryMethod: Transaction.TransactionEntryMethod, userId: Long): Int =
        transactionRepository.countAllByEntryMethodAndUserId(entryMethod, userId)

    suspend fun countAllByCurrencyAndUserId(currency: String, userId: Long): Int =
        transactionRepository.countAllByCurrencyAndUserId(currency, userId)

    suspend fun countAllByStateAndUserId(state: Transaction.TransactionState, userId: Long): Int =
        transactionRepository.countAllByStateAndUserId(state, userId)

    suspend fun countAllByTypeAndUserId(type: Transaction.TransactionType, userId: Long): Int =
        transactionRepository.countAllByTypeAndUserId(type, userId)

    suspend fun countAllBySourceAndUserId(source: Transaction.TransactionSource, userId: Long): Int =
        transactionRepository.countAllBySourceAndUserId(source, userId)

    suspend fun countAllByMerchantCategoryLikeAndUserId(merchantCountry: String, userId: Long): Int =
        transactionRepository.countAllByMerchantCategoryLikeAndUserId(merchantCountry, userId)

    suspend fun countAllByMerchantCountryAndUserId(merchantCategory: String, userId: Long): Int =
        transactionRepository.countAllByMerchantCountryAndUserId(merchantCategory, userId)

    suspend fun countAll()=
        transactionRepository.countAll()

    suspend fun countAllByIsFlaged(flaged:Boolean)=
        transactionRepository.countAllByFlaged(flaged)

    suspend fun countAllByCreatedDateContains(year:String)=
        transactionRepository.countAllByCreatedDateContains(year)

    suspend fun countAllByIsFlagedAndUserId(flaged: Boolean, userId: Long)=
        transactionRepository.countAllByFlagedAndUserId(flaged,userId)

    suspend fun countFlagedTransactionsByUserId( userId: Long)=
        transactionRepository.countAllFlagedAndUserId(userId)

    suspend fun flagTransaction(transactionId: Long) =
        transactionRepository.flagTransaction(transactionId)

}