package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction

interface TransactionReactiveRepository:ReactiveCrudRepository<Transaction, Long> {
}