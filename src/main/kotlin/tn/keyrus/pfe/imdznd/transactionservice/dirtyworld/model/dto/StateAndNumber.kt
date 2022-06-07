package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto

import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction

data class StateAndNumber(
    val state: Transaction.TransactionState,
    val number:Int,
)