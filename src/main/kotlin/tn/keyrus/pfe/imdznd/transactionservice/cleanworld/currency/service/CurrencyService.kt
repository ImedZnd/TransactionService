package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.service

import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.repository.CurrencyRepository

class CurrencyService(
    private val currencyRepository: CurrencyRepository
) {
    fun findAllCurrency() =
        currencyRepository.findAllCurrency()

    suspend fun findCurrencyByCurrencyName(currencyName: String) =
        currencyRepository.findCurrencyByCurrencyName(currencyName).get()

    suspend fun findCurrencyByCodeISO(codeISO: Int) =
        currencyRepository.findCurrencyByCodeISO(codeISO).get()

    fun findAllCurrencyByIsCrypto(isCrypto: Boolean) =
        currencyRepository.findAllCurrencyByIsCrypto(isCrypto)

    suspend fun countCurrencies() =
        currencyRepository.countCurrencies()
}