package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.dao.TransactionDAO
import java.time.LocalDateTime

interface TransactionReactiveRepository : ReactiveCrudRepository<TransactionDAO, Long> {

    fun findAllByCurrency(currency: String): Flux<TransactionDAO>
    fun findAllByState(state: Transaction.TransactionState): Flux<TransactionDAO>
    fun findAllByCreatedDate(createdDate: LocalDateTime): Flux<TransactionDAO>
    fun findAllByCreatedDateBetween(startDate: LocalDateTime, endDate: LocalDateTime = startDate): Flux<TransactionDAO>
    fun findAllByCreatedDateBefore(beforeDate: LocalDateTime): Flux<TransactionDAO>
    fun findAllByCreatedDateAfter(beforeDate: LocalDateTime): Flux<TransactionDAO>
    fun findAllByMerchantCategory(merchantCategory: String): Flux<TransactionDAO>
    fun findAllByMerchantCountry(merchantCountry: String): Flux<TransactionDAO>
    fun findAllByEntryMethod(entryMethod: Transaction.TransactionEntryMethod): Flux<TransactionDAO>
    fun findAllByUserId(userId: Long): Flux<TransactionDAO>
    fun findAllByType(type: Transaction.TransactionType): Flux<TransactionDAO>
    fun findAllBySource(source: Transaction.TransactionSource): Flux<TransactionDAO>
    fun findAllByCurrencyAndCreatedDate(currency: String, createdDate: LocalDateTime): Flux<TransactionDAO>
    fun findAllByMerchantCountryAndUserId(merchantCountry: String, userId: Long): Flux<TransactionDAO>
    fun findAllByMerchantCategoryAndUserId(merchantCategory: String, userId: Long): Flux<TransactionDAO>
    fun findAllByTypeAndUserId(type: Transaction.TransactionType, userId: Long): Flux<TransactionDAO>
    fun findAllByEntryMethodAndUserId(
        entryMethod: Transaction.TransactionEntryMethod,
        userId: Long
    ): Flux<TransactionDAO>
    fun countAllByMerchantCategoryNotLike(alpha: String): Mono<Int>
    fun countAllByEntryMethod(entryMethod: Transaction.TransactionEntryMethod): Mono<Int>
    fun countAllByCurrency(currency: String): Mono<Int>
    fun countAllByState(state: Transaction.TransactionState): Mono<Int>
    fun countAllByType(type: Transaction.TransactionType): Mono<Int>
    fun countAllBySource(source: Transaction.TransactionSource): Mono<Int>
    fun countAllByMerchantCategoryLike(merchantCountry: String): Mono<Int>
    fun countAllByMerchantCountry(merchantCategory: String): Mono<Int>
    fun countAllByUserId(userId: Long): Mono<Int>
    fun countAllByFlaged(flaged: Boolean): Mono<Int>
    fun countAllByCreatedDate_YearGreaterThan(year: Int): Mono<Int>

    fun countAllByEntryMethodAndUserId(entryMethod: Transaction.TransactionEntryMethod, userId: Long): Mono<Int>
    fun countAllByCurrencyAndUserId(currency: String, userId: Long): Mono<Int>
    fun countAllByStateAndUserId(state: Transaction.TransactionState, userId: Long): Mono<Int>
    fun countAllByTypeAndUserId(type: Transaction.TransactionType, userId: Long): Mono<Int>
    fun countAllBySourceAndUserId(source: Transaction.TransactionSource, userId: Long): Mono<Int>
    fun countAllByMerchantCategoryLikeAndUserId(merchantCountry: String, userId: Long): Mono<Int>
    fun countAllByMerchantCountryAndUserId(merchantCategory: String, userId: Long): Mono<Int>
    fun countAllByFlagedAndUserId(flaged: Boolean, userId: Long): Mono<Int>

}