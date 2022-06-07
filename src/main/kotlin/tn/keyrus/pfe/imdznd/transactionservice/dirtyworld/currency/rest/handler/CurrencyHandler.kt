package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.rest.handler

import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.web.reactive.function.server.*
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.service.CurrencyService
import java.util.*

class CurrencyHandler(
    private val currencyService: CurrencyService,
    private val messageSource: MessageSource

) {

    suspend fun findAllCurrency() =
        ServerResponse.ok()
            .bodyAndAwait(
                currencyService.findAllCurrency()
            )

    suspend fun findCurrencyByCurrencyName(request: ServerRequest) =
        ServerResponse
            .ok()
            .bodyValueAndAwait(
                currencyService.findCurrencyByCurrencyName(request.pathVariable("name"))
            )

    suspend fun findCurrencyByCodeISO(request: ServerRequest) =
        ServerResponse
            .ok()
            .bodyValueAndAwait(
                currencyService.findCurrencyByCodeISO(request.pathVariable("codeiso").toInt())
            )

    suspend fun findAllCurrencyByIsCrypto(request: ServerRequest) =
        ServerResponse
            .ok()
            .bodyAndAwait(
                currencyService.findAllCurrencyByIsCrypto(request.pathVariable("isCrypto").toBoolean())
            )

    suspend fun countCurrencies() =
        ServerResponse
            .ok()
            .bodyValueAndAwait(
                currencyService.countCurrencies()
            )

    private fun headerErrorInBadRequestError() =
        try {
            messageSource.getMessage("CountryCodeError", null, Locale.US)
        } catch (exception: NoSuchMessageException) {
            "No Such Message Exception Raised"
        }

    fun countAllCountry() {
        TODO("Not yet implemented")
    }
}