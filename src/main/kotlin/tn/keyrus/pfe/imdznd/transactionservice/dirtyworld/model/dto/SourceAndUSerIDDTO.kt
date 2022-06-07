package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto

import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction

data class SourceAndUSerIDDTO(
    val source: Transaction.TransactionSource,
    val userId:Long
)