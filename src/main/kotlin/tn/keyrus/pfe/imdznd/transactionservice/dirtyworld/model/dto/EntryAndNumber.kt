package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto

import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction

data class EntryAndNumber(
    val entryMethod: Transaction.TransactionEntryMethod,
    val number:Int
)