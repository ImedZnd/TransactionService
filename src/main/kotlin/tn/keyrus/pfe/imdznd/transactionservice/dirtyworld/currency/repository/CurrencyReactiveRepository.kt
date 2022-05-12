package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.dao.CurrencyDAO

interface CurrencyReactiveRepository : ReactiveCrudRepository<CurrencyDAO, String> {

    fun findAllByCrypto(crypto: Boolean): Flux<CurrencyDAO>

    fun findByCurrencyName(currencyName: String): Mono<CurrencyDAO>

    fun findByCodeISO(codeISO: Int): Mono<CurrencyDAO>
}