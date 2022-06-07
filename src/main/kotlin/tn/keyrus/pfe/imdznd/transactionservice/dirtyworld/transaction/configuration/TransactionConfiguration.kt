package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.configuration

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.repository.CurrencyRepository
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.repository.TransactionRepository
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.service.TransactionService
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.queue.TransactionQueueSettings
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository.TransactionDatabaseRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository.TransactionReactiveRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.rest.handler.TransactionHandler

@Configuration
class TransactionConfiguration {

    @Bean
    fun transactionDatabaseRepository(
        transactionReactiveRepository: TransactionReactiveRepository,
        rabbitTemplate: RabbitTemplate,
        transactionQueueSettings: TransactionQueueSettings,
        currencyRepository: CurrencyRepository
    ):TransactionRepository =
        TransactionDatabaseRepository(
            transactionReactiveRepository,
            rabbitTemplate,
            transactionQueueSettings,
            currencyRepository
        )

    @Bean
    fun transactionService(
        transactionRepository:TransactionRepository
    )=
        TransactionService(transactionRepository)

    @Bean
    fun transactionHandler(
        transactionService: TransactionService,
        messageSource: MessageSource
    )=
        TransactionHandler(transactionService,messageSource)

}