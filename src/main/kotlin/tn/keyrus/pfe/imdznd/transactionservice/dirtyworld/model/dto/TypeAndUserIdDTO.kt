package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto

import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction

data class TypeAndUserIdDTO(
    val type: Transaction.TransactionType,
    val userId: Long
) {
}