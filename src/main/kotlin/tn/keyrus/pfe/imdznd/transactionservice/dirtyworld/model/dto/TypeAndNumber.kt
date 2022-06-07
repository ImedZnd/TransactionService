package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto

import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction

data class TypeAndNumber(
    val type:Transaction.TransactionType,
    val number:Int
)