package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository.TransactionDatabaseRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository.TransactionReactiveRepository

@Configuration
class TransactionConfiguration {

    @Bean
    fun transactionDatabaseRepository(
        transactionReactiveRepository: TransactionReactiveRepository
    ) =
        TransactionDatabaseRepository(transactionReactiveRepository)

}