package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.configuration

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.repository.CurrencyRepository
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.service.CurrencyService
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.repository.CurrencyDatabaseRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.repository.CurrencyReactiveRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.rest.handler.CurrencyHandler

@Configuration
class CurrencyConfiguration {

    @Bean
    fun currencyDatabaseRepository(
        currencyReactiveRepository: CurrencyReactiveRepository
    ): CurrencyRepository =
        CurrencyDatabaseRepository(currencyReactiveRepository)

    @Bean
    fun currencyService(
        currencyRepository: CurrencyRepository
    ) =
        CurrencyService(currencyRepository)

    @Bean
    fun currencyHandler(
        currencyService: CurrencyService,
        messageSource: MessageSource
    ) =
        CurrencyHandler(
            currencyService,
            messageSource
        )
}