package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.repository.CurrencyDatabaseRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.repository.CurrencyReactiveRepository

@Configuration
class CurrencyConfiguration {

    @Bean
    fun currencyDatabaseRepository(
        currencyReactiveRepository: CurrencyReactiveRepository
    ) =
        CurrencyDatabaseRepository(currencyReactiveRepository)

}