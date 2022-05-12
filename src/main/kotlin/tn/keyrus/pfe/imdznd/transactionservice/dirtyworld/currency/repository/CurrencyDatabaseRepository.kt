package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.repository

import io.vavr.control.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingleOrNull
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.model.Currency
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.repository.CurrencyRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.dao.CurrencyDAO
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.dao.CurrencyDAO.Companion.toDAO

class CurrencyDatabaseRepository(
    private val currencyReactiveRepository: CurrencyReactiveRepository
) : CurrencyRepository {

    override fun findAllCurrency(): Flow<Currency> =
        findAllCurrencyByCriteria { it.findAll() }

    override suspend fun findCurrencyByCurrencyName(currencyName: String): Either<CurrencyRepository.CurrencyRepositoryError, Currency> =
        currencyReactiveRepository.findByCurrencyName(currencyName)
            .let { returnEitherOnMono(it) }

    override suspend fun findCurrencyByCodeISO(codeISO: Int): Either<CurrencyRepository.CurrencyRepositoryError, Currency> =
        currencyReactiveRepository.findByCodeISO(codeISO)
            .let { returnEitherOnMono(it) }

    override fun findAllCurrencyByIsCrypto(isCrypto: Boolean) =
        findAllCurrencyByCriteria { it.findAllByCrypto(isCrypto) }


    override suspend fun saveCurrency(currency: Currency): Either<CurrencyRepository.CurrencyRepositoryError, Currency> =
        try {
            println(currency)
            currencyReactiveRepository.save(currency.toDAO())
                .also { print(it) }
                .let { returnEitherOnMono(it) }
        } catch (exception: Exception) {
            Either.left(CurrencyRepository.CurrencyRepositoryError.CurrencyRepositoryIOError)
        }

    private suspend fun returnEitherOnMono(mono: Mono<CurrencyDAO>): Either<CurrencyRepository.CurrencyRepositoryError, Currency> =
        mono
            .map { it.toCurrency() }
            .filter { it.isRight }
            .map { it.get() }
            .map { Either.right<CurrencyRepository.CurrencyRepositoryError, Currency>(it) }
            .awaitSingleOrNull()
            ?: Either.left(CurrencyRepository.CurrencyRepositoryError.CurrencyNotFoundRepositoryError)


    private fun findAllCurrencyByCriteria(criteria: (CurrencyReactiveRepository) -> Flux<CurrencyDAO>) =
        criteria(currencyReactiveRepository)
            .asFlow()
            .map { it.toCurrency() }
            .filter { it.isRight }
            .map { it.get() }
}