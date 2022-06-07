package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.repository

import io.vavr.control.Either
import kotlinx.coroutines.flow.Flow
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.model.Currency

interface CurrencyRepository {
    fun findAllCurrency(): Flow<Currency>
    suspend fun findCurrencyByCurrencyName(currencyName: String): Either<CurrencyRepositoryError, Currency>
    suspend fun findCurrencyByCodeISO(codeISO: Int): Either<CurrencyRepositoryError, Currency>
    fun findAllCurrencyByIsCrypto(isCrypto: Boolean): Flow<Currency>
    suspend fun countCurrencies(): Int
    suspend fun saveCurrency(currency: Currency): Either<CurrencyRepositoryError, Currency>

    sealed interface CurrencyRepositoryError {
        object CurrencyRepositoryIOError : CurrencyRepositoryError
        object CurrencyNotFoundRepositoryError : CurrencyRepositoryError
    }
}