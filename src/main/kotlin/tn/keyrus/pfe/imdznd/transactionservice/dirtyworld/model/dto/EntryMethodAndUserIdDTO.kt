package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto

import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction

data class EntryMethodAndUserIdDTO(
    val entryMethod: Transaction.TransactionEntryMethod,
    val userId: Long
)