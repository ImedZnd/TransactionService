package tn.keyrus.pfe.imdznd.transactionservice

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@ComponentScan("tn.keyrus.pfe.imdznd.transactionservice.**")
@SpringBootApplication
@EnableR2dbcRepositories("tn.keyrus.pfe.imdznd.transactionservice.**")
@OpenAPIDefinition(info = Info(title = "Transaction Service", version = "1.0", description = "Documentation APIs v1.0"))
@ConfigurationPropertiesScan
class TransactionServiceApplication

fun main(args: Array<String>) {
    runApplication<TransactionServiceApplication>(*args)
}
