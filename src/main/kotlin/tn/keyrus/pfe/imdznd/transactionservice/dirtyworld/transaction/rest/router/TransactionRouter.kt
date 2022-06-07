package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.rest.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.server.coRouter
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.rest.annotation.TransactionRouterInfo
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.rest.handler.TransactionHandler

@Configuration
class TransactionRouter {

    @Bean
    @TransactionRouterInfo
    @CrossOrigin(origins = ["http://localhost:4200/"], maxAge = 3600)
    fun transactionRoutes(transactionHandler: TransactionHandler) = coRouter {
        "/transactions".nest {
            GET("/all") { transactionHandler.findAllTransactions() }
            GET("/countyAndNumberCount") { transactionHandler.countyAndNumberCount() }
            GET("/stateAndNumber") { transactionHandler.stateAndNumber() }
            GET("/stateAndNumberStream") { transactionHandler.stateAndNumberStream() }
            GET("/entryAndNumber") { transactionHandler.entryAndNumber() }
            GET("/entryAndNumberStream") { transactionHandler.entryAndNumberStream() }
            GET("/typeAndNumber") { transactionHandler.typeAndNumber() }
            GET("/typeAndNumberStream") { transactionHandler.typeAndNumberStream() }
            GET("/sourceAndNumber") { transactionHandler.sourceAndNumber() }
            GET("/sourceAndNumberStream") { transactionHandler.sourceAndNumberStream() }
            GET("/flagAndNumber") { transactionHandler.flagAndNumber() }
            GET("/flagAndNumberStream") { transactionHandler.flagAndNumberStream() }
            GET("/totalOfAmount") { transactionHandler.totalOfAmount() }
            GET("/currencyName/{name}", transactionHandler::findAllTransactionsByCurrency)
            GET("/state/{state}", transactionHandler::findAllTransactionsByState)
            POST("/createdDate", transactionHandler::findAllTransactionsByCreatedDate)
            POST("/createdDateBetween", transactionHandler::findAllTransactionsByCreatedDateBetween)
            POST("/createdDateBefore", transactionHandler::findAllTransactionsByCreatedDateBefore)
            POST("/createdDateAfter", transactionHandler::findAllTransactionsByCreatedDateAfter)
            GET("/merchantCategory/{merchantCategory}", transactionHandler::findAllTransactionsByMerchantCategory)
            GET("/merchantCountry/{merchantCountry}", transactionHandler::findAllTransactionsByMerchantCountry)
            GET("/entryMethod/{entryMethod}", transactionHandler::findAllTransactionsByEntryMethod)
            GET("/userId/{userId}", transactionHandler::findAllTransactionsByUserId)
            GET("/type/{type}", transactionHandler::findAllTransactionsByType)
            GET("/source/{source}", transactionHandler::findAllTransactionsBySource)
            POST("/merchantCategoryAndUserId", transactionHandler::findAllTransactionsByMerchantCategoryAndUserId)
            POST("/typeAndUserId", transactionHandler::findAllTransactionsByTypeAndUserId)
            POST("/entryMethodAndUserId", transactionHandler::findAllTransactionsByEntryMethodAndUserId)
            GET("/countAll") { transactionHandler.countAll() }
            GET("/countAllStream") { transactionHandler.countAllStream() }
            GET("/countAllInYear/{year}", transactionHandler::countAllInYear )
            GET("/countCurrency/{currency}", transactionHandler::countAllTransactionsByCurrency)
            GET("/countState/{state}", transactionHandler::countAllTransactionsByState)
            GET("/countType/{type}", transactionHandler::countAllTransactionsByType)
            GET("/countSource/{source}", transactionHandler::countAllTransactionsBySource)
            GET("/countMerchantCategory/{merchantCategory}", transactionHandler::countAllTransactionsMerchantCategory)
            GET("/countMerchantCountry/{merchantCountry}", transactionHandler::countAllTransactionsMerchantCountry)
            GET("/countEntryMethod/{entryMethod}", transactionHandler::countAllTransactionsEntryMethod)
            GET("/countUserId/{userId}", transactionHandler::countAllTransactionsUserId)
            GET("/countIsFlaged/{flag}", transactionHandler::countAllByIsFlaged)
            POST("/countEntryMethodAndUserId", transactionHandler::countAllByEntryMethodAndUserId)
            GET("/countEntryMethodAndNumberByUserId/{userId}", transactionHandler::countEntryMethodAndNumberByUserId)
            POST("/countCurrencyAndUserId", transactionHandler::countAllByCurrencyAndUserId)
            GET("/countCurrencyAndNumberByUserId/{userId}", transactionHandler::countCurrencyAndNumberByUserId)
            POST("/countStateAndUserId", transactionHandler::countAllByStateAndUserId)
            GET("/countStateAndNumberByUserId/{userId}", transactionHandler::countStateAndNumberByUserId)
            POST("/countTypeAndUserId", transactionHandler::countAllByTypeAndUserId)
            GET("/countTypeAndNumberByUserId/{userId}", transactionHandler::countTypeAndNumberByUserId)
            POST("/countSourceAndUserId", transactionHandler::countAllBySourceAndUserId)
            GET("/countSourceAndNumberByUserId/{userId}", transactionHandler::countSourceAndNumberByUserId)
            POST("/countMerchantCategoryLikeAndUserId", transactionHandler::countAllByMerchantCategoryLikeAndUserId)
            POST("/countMerchantCountryAndUserId", transactionHandler::countAllByMerchantCountryAndUserId)
            POST("/countIsFlagedAndUserId", transactionHandler::countAllByIsFlagedAndUserId)
            GET("/countFlagedTransactionsByUserIdStream/{userId}", transactionHandler::countFlagedTransactionsByUserIdStream)
            GET("/flag/{transactionId}",transactionHandler::flagTransaction)
        }
    }
}