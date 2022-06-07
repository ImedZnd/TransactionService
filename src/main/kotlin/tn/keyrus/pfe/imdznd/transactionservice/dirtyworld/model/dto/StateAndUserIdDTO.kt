package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto

import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction

data class StateAndUserIdDTO(
    val state:Transaction.TransactionState,
    val userId:Long
)