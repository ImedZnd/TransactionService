package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository

import io.vavr.control.Either
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.amqp.rabbit.core.RabbitTemplate
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.repository.CurrencyRepository
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.repository.TransactionRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto.*
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.dao.TransactionDAO
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.dao.TransactionDAO.Companion.toDAO
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.queue.TransactionQueueSettings
import java.time.LocalDateTime
import java.util.stream.IntStream

class TransactionDatabaseRepository(
    private val transactionReactiveRepository: TransactionReactiveRepository,
    private val rabbitTemplate: RabbitTemplate,
    private val transactionQueueSettings: TransactionQueueSettings,
    private val currencyRepository: CurrencyRepository,
) : TransactionRepository {

    override fun findAllTransactions() =
        findAllTransactionByCriteria { it.findAll() }

    override fun countyAndNumberCount(): Flow<CountryAndNumber> {
        TODO("Not yet implemented")
    }

    override fun findAllTransactionsByCurrency(currency: String) =
        findAllTransactionByCriteria { it.findAllByCurrency(currency) }

    override fun findAllTransactionsByState(state: Transaction.TransactionState) =
        findAllTransactionByCriteria { it.findAllByState(state) }

    override fun findAllTransactionsByCreatedDate(createdDate: LocalDateTime) =
        findAllTransactionByCriteria { it.findAllByCreatedDate(createdDate) }

    override fun findAllTransactionsByCreatedDateBetween(startDate: LocalDateTime, endDate: LocalDateTime) =
        findAllTransactionByCriteria { it.findAllByCreatedDateBetween(startDate, endDate) }

    override fun findAllTransactionsByCreatedDateBefore(beforeDate: LocalDateTime) =
        findAllTransactionByCriteria { it.findAllByCreatedDateBefore(beforeDate) }

    override fun findAllTransactionsByCreatedDateAfter(fromDate: LocalDateTime) =
        findAllTransactionByCriteria { it.findAllByCreatedDateAfter(fromDate) }

    override fun findAllTransactionsByMerchantCategory(merchantCategory: String) =
        findAllTransactionByCriteria { it.findAllByMerchantCategory(merchantCategory) }

    override fun findAllTransactionsByMerchantCountry(merchantCountry: String) =
        findAllTransactionByCriteria { it.findAllByMerchantCountry(merchantCountry) }

    override fun findAllTransactionsByEntryMethod(entryMethod: Transaction.TransactionEntryMethod) =
        findAllTransactionByCriteria { it.findAllByEntryMethod(entryMethod) }

    override fun findAllTransactionsByUserId(userId: Long) =
        findAllTransactionByCriteria { it.findAllByUserId(userId) }

    override fun findAllTransactionsByType(type: Transaction.TransactionType) =
        findAllTransactionByCriteria { it.findAllByType(type) }

    override fun findAllTransactionsBySource(source: Transaction.TransactionSource) =
        findAllTransactionByCriteria { it.findAllBySource(source) }

    override fun findAllTransactionsByCurrencyAndCreatedDate(
        currency: String,
        createdDate: LocalDateTime
    ) =
        findAllTransactionByCriteria { it.findAllByCurrencyAndCreatedDate(currency, createdDate) }

    override fun findAllTransactionsByMerchantCategoryAndUserId(
        merchantCountry: String,
        userId: Long
    ) =
        findAllTransactionByCriteria { it.findAllByMerchantCategoryAndUserId(merchantCountry, userId) }

    override fun findAllTransactionsByTypeAndUserId(
        type: Transaction.TransactionType,
        userId: Long
    ) =
        findAllTransactionByCriteria { it.findAllByTypeAndUserId(type, userId) }

    override fun findAllTransactionsByEntryMethodAndUserId(
        entryMethod: Transaction.TransactionEntryMethod,
        userId: Long
    ) =
        findAllTransactionByCriteria { it.findAllByEntryMethodAndUserId(entryMethod, userId) }

    override suspend fun countAll() =
        transactionReactiveRepository.countAllByMerchantCategoryNotLike("xx")
            .let { mapToIntFromMono(it) }

    override suspend fun countAllTransactionsByCurrency(currency: String) =
        transactionReactiveRepository.countAllByCurrency(currency)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllTransactionsByState(state: Transaction.TransactionState) =
        transactionReactiveRepository.countAllByState(state)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllTransactionsByType(type: Transaction.TransactionType) =
        transactionReactiveRepository.countAllByType(type)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllTransactionsBySource(source: Transaction.TransactionSource) =
        transactionReactiveRepository.countAllBySource(source)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllTransactionsMerchantCategory(merchantCategory: String) =
        transactionReactiveRepository.countAllByMerchantCategoryLike(merchantCategory)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllTransactionsMerchantCountry(merchantCountry: String) =
        transactionReactiveRepository.countAllByMerchantCountry(merchantCountry)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllTransactionsEntryMethod(entryMethod: Transaction.TransactionEntryMethod) =
        transactionReactiveRepository.countAllByEntryMethod(entryMethod)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllTransactionsUserId(userId: Long) =
        transactionReactiveRepository.countAllByUserId(userId)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllByFlaged(flaged: Boolean) =
        transactionReactiveRepository.countAllByFlaged(flaged)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllByEntryMethodAndUserId(entryMethod: Transaction.TransactionEntryMethod, userId: Long) =
        transactionReactiveRepository.countAllByEntryMethodAndUserId(entryMethod, userId)
            .let { mapToIntFromMono(it) }


    override suspend fun countAllByCurrencyAndUserId(currency: String, userId: Long) =
        transactionReactiveRepository.countAllByCurrencyAndUserId(currency, userId)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllByStateAndUserId(state: Transaction.TransactionState, userId: Long) =
        transactionReactiveRepository.countAllByStateAndUserId(state, userId)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllByTypeAndUserId(type: Transaction.TransactionType, userId: Long) =
        transactionReactiveRepository.countAllByTypeAndUserId(type, userId)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllBySourceAndUserId(source: Transaction.TransactionSource, userId: Long) =
        transactionReactiveRepository.countAllBySourceAndUserId(source, userId)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllByMerchantCategoryLikeAndUserId(merchantCountry: String, userId: Long) =
        transactionReactiveRepository.countAllByMerchantCategoryLikeAndUserId(merchantCountry, userId)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllByMerchantCountryAndUserId(merchantCategory: String, userId: Long) =
        transactionReactiveRepository.countAllByMerchantCountryAndUserId(merchantCategory, userId)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllByFlagedAndUserId(flaged: Boolean, userId: Long) =
        transactionReactiveRepository.countAllByFlagedAndUserId(flaged, userId)
            .let { mapToIntFromMono(it) }

    override suspend fun countAllFlagedAndUserId(userId: Long): Flow<FlagedAndNumber> {
        val booleanValue = listOf(true,false)
        return booleanValue
            .map { booleanToFlaggedAndNumber(it,userId) }
            .asFlow()
    }

    private suspend fun booleanToFlaggedAndNumber(flaged: Boolean,userId: Long)=
        FlagedAndNumber(
            flaged,
            countAllByFlagedAndUserId(flaged,userId)
        )

    override suspend fun countAllByCreatedDateContains(year: String) =
        transactionReactiveRepository.countAllByCreatedDate_YearGreaterThan(year.toInt())
            .let { mapToIntFromMono(it) }

    override suspend fun stateAndNumber() =
        Transaction.TransactionState.values()
            .asFlow()
            .map(::stateToStateAndNumber)

    private suspend fun stateToStateAndNumber(state: Transaction.TransactionState) =
        StateAndNumber(
            state,
            countAllTransactionsByState(state)
        )

    override suspend fun entryAndNumber() =
        Transaction.TransactionEntryMethod.values()
            .asFlow()
            .map(::entryToEntryAndNumber)

    private suspend fun entryToEntryAndNumber(entryMethod: Transaction.TransactionEntryMethod) =
        EntryAndNumber(
            entryMethod,
            countAllTransactionsEntryMethod(entryMethod)
        )

    override suspend fun typeAndNumber() =
        Transaction.TransactionType.values()
            .asFlow()
            .map(::typeToTypeAndNumber)

    private suspend fun typeToTypeAndNumber(type: Transaction.TransactionType) =
        TypeAndNumber(
            type,
            countAllTransactionsByType(type)
        )

    override suspend fun sourceAndNumber() =
        Transaction.TransactionSource.values()
            .asFlow()
            .map(::sourceToSourceAndNumber)

    private suspend fun sourceToSourceAndNumber(source: Transaction.TransactionSource) =
        SourceAndNumber(
            source,
            countAllTransactionsBySource(source)
        )

    suspend fun allCountyAndNumberOfTransactions() {
        allCountry()
            .map(::countryToCountryAndNumber)
    }

    override suspend fun countEntryMethodAndNumberByUserId(userId: Long) =
        Transaction.TransactionEntryMethod.values()
            .asFlow()
            .map { entryToEntryAndNumberByUserId(it, userId) }

    override suspend fun countCurrencyAndNumberByUserId(userId: Long) =
        currencyRepository.findAllCurrency()
            .map { it.currencyName }
            .map { currencyAndUserToFlowOfCurrencyAndNumberByUserId(it, userId) }

    override suspend fun countStateAndNumberByUserId(userId: Long) =
        Transaction.TransactionState.values()
            .asFlow()
            .map{ stateAndUserIdToFlowOfStateAndNumberByUserId(it,userId) }

    override suspend fun countTypeAndNumberByUserId(userId: Long)=
        Transaction.TransactionType.values()
            .asFlow()
            .map {typeToTypeAndNumberByUserId(it,userId)}

    override suspend fun countSourceAndNumberByUserId(userId: Long)=
        Transaction.TransactionSource.values()
            .asFlow()
            .map{sourceToSourceAndNumberByUserId(it,userId)}

    private suspend fun sourceToSourceAndNumberByUserId(source: Transaction.TransactionSource,userId: Long)=
        SourceAndNumber(
            source,
            countAllBySourceAndUserId(source,userId)
        )

    private suspend fun typeToTypeAndNumberByUserId(type: Transaction.TransactionType,userId: Long)=
        TypeAndNumber(
            type,
            countAllByTypeAndUserId(type,userId)
        )

    private suspend fun stateAndUserIdToFlowOfStateAndNumberByUserId(state: Transaction.TransactionState,userId: Long)=
        StateAndNumber(
            state,
            countAllByStateAndUserId(state,userId)
        )

    private suspend fun currencyAndUserToFlowOfCurrencyAndNumberByUserId(currency: String, userId: Long) =
        StringAndNumber(
            currency,
            countAllByCurrencyAndUserId(currency, userId)
        )

    private suspend fun entryToEntryAndNumberByUserId(entryMethod: Transaction.TransactionEntryMethod, userId: Long) =
        EntryAndNumber(
            entryMethod,
            countAllByEntryMethodAndUserId(entryMethod, userId)
        )

    private suspend fun countryToCountryAndNumber(country: String) =
        CountryAndNumber(
            country,
            countAllTransactionsMerchantCountry(country)
        )

    suspend fun allCountry() =
        findAllTransactions()
            .toSet()
            .distinctBy { it.merchantCountry }
            .map { it.merchantCountry }
            .asFlow()

    override suspend fun saveTransaction(transaction: Transaction): Either<TransactionRepository.TransactionRepositoryError, Transaction> =
        try {
            manageMonoReturnEither { it.save(transaction.toDAO()) }
        } catch (exception: Exception) {
            Either.left(TransactionRepository.TransactionRepositoryError.TransactionRepositoryIOError)
        }

    override suspend fun flagTransaction(transactionId: Long): Either<TransactionRepository.TransactionRepositoryError, Transaction> {
        val eitherTransactionOrError = manageMonoReturnEither { it.findById(transactionId) }
        if (eitherTransactionOrError.isRight) {
            if (eitherTransactionOrError.get().flaged) {
                return Either.left(TransactionRepository.TransactionRepositoryError.TransactionFlagedRepositoryIOError)
            }
            return Either.right(flagTransactionById(eitherTransactionOrError.get()))
        } else
            return Either.left(TransactionRepository.TransactionRepositoryError.TransactionNotExistRepositoryError)
    }

    override fun publishFlagTransaction(transactionId: Long) {
        rabbitTemplate.convertAndSend(
            transactionQueueSettings.flag?.exchange ?: "",
            transactionQueueSettings.flag?.routingkey ?: "",
            transactionId
        )
    }

    private suspend fun flagTransactionById(transaction: Transaction) =
        manageMonoReturnEither {
            it.save(
                Transaction.of(
                    transaction.transactionId,
                    transaction.currency,
                    transaction.amount,
                    transaction.state,
                    transaction.createdDate,
                    transaction.merchantCategory,
                    transaction.merchantCategory,
                    transaction.entryMethod,
                    transaction.userId,
                    transaction.type,
                    transaction.source,
                    true
                ).get()
                    .toDAO()
            )
        }.get()

    private suspend fun manageMonoReturnEither(
        action: (TransactionReactiveRepository) -> Mono<TransactionDAO>
    ): Either<TransactionRepository.TransactionRepositoryError, Transaction> =
        try {
            action(transactionReactiveRepository)
                .map { it.toTransaction() }
                .filter { it.isRight }
                .map { it.get() }
                .map { Either.right<TransactionRepository.TransactionRepositoryError, Transaction>(it) }
                .awaitSingleOrNull()
                ?: Either.left(TransactionRepository.TransactionRepositoryError.TransactionNotExistRepositoryError)
        } catch (exception: Exception) {
            Either.left(TransactionRepository.TransactionRepositoryError.TransactionRepositoryIOError)
        }

    private suspend fun mapToIntFromMono(mono: Mono<Int>) =
        mono.awaitSingleOrNull() ?: 0

    private fun findAllTransactionByCriteria(criteria: (TransactionReactiveRepository) -> Flux<TransactionDAO>) =
        criteria(transactionReactiveRepository)
            .asFlow()
            .map { it.toTransaction() }
            .filter { it.isRight }
            .map { it.get() }
}