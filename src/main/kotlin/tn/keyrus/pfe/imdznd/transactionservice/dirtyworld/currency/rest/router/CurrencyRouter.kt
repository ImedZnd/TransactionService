package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.rest.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.server.coRouter
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.rest.handler.CurrencyHandler

@Configuration
class CurrencyRouter {

    @Bean
    @CrossOrigin(origins = ["http://localhost:4200/"], maxAge = 3600)
    fun currencyRoutes(currencyHandler: CurrencyHandler) = coRouter {
        "/currency".nest {
            GET("/all") {currencyHandler.findAllCurrency()}
            GET("/name/{name}", currencyHandler::findCurrencyByCurrencyName )
            GET("/codeiso/{codeiso}", currencyHandler::findCurrencyByCodeISO)
            GET("/isCrypto/{isCrypto}", currencyHandler::findAllCurrencyByIsCrypto)
            GET("/countCurrencies") { currencyHandler.countCurrencies() }
        }
    }

}