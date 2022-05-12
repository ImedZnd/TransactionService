package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.repository

import kotlinx.coroutines.flow.Flow
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.model.Currency
import java.util.Optional

interface CurrencyRepository {
    fun findAllCurrency(): Flow<Currency>
    fun findCurrencyByCurrencyName(currencyName: String): Flow<Currency>
    fun findCurrencyByCodeISO(codeISO: Int): Flow<Currency>
    fun findAllCurrencyByIsCrypto(isCrypto: Boolean): Flow<Currency>
    fun saveCurrency(currency: Currency): Optional<Currency>
}