package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class TransactionTest {

    @Test
    fun `valid test Transaction non empty field`() {
        val transactionId : Long  = 1
        val currency = "GBP"
        val amount = 3738.0
        val state = Transaction.TransactionState.COMPLETED
        val createdDate = LocalDateTime.now().minusYears(1)
        val merchantCategory = "supermarket"
        val merchantCountry = "AUS"
        val entryMethod = Transaction.TransactionEntryMethod.CHIP
        val userId: Long = 10
        val type = Transaction.TransactionType.ATM
        val source = Transaction.TransactionSource.GAIA
        val result =
            Transaction.of(
                transactionId,
                currency,
                amount,
                state,
                createdDate,
                merchantCategory,
                merchantCountry,
                entryMethod,
                userId,
                type,
                source,
                false
            ).get()
        assertAll(
            { assert(result.currency == currency) },
            { assert(result.amount == amount) },
            { assert(result.state == state) },
            { assert(result.createdDate == createdDate) },
            { assert(result.merchantCategory == merchantCategory) },
            { assert(result.merchantCountry == merchantCountry) },
            { assert(result.entryMethod == entryMethod) },
            { assert(result.userId == userId) },
            { assert(result.type == type) },
            { assert(result.source == source) },
        )
    }

    @Test
    fun `error currency test Transaction empty currency`() {
        val transactionId : Long  = 1
        val currency = ""
        val amount = 3738.0
        val state = Transaction.TransactionState.COMPLETED
        val createdDate = LocalDateTime.now().minusYears(1)
        val merchantCategory = "supermarket"
        val merchantCountry = "AUS"
        val entryMethod = Transaction.TransactionEntryMethod.CHIP
        val userId: Long = 10
        val type = Transaction.TransactionType.ATM
        val source = Transaction.TransactionSource.INTERNAL
        val result =
            Transaction.of(
                transactionId,
                currency,
                amount,
                state,
                createdDate,
                merchantCategory,
                merchantCountry,
                entryMethod,
                userId,
                type,
                source,
                false
            ).left
        assertAll(
            { assert(result.count() == 1) },
            { assert(result.all { it is Transaction.TransactionError.TransactionCurrencyError }) },
        )
    }
    @Test
    fun `error amount test Transaction negative amount`() {
        val transactionId : Long  = 1
        val currency = "GBP"
        val amount = -6200.0
        val state = Transaction.TransactionState.DECLINED
        val createdDate = LocalDateTime.now().minusYears(1)
        val merchantCategory = "supermarket"
        val merchantCountry = "AUS"
        val entryMethod = Transaction.TransactionEntryMethod.CONT
        val userId: Long = 10
        val type = Transaction.TransactionType.BANK_TRANSFER
        val source = Transaction.TransactionSource.OPHION
        val result =
            Transaction.of(
                transactionId,
                currency,
                amount,
                state,
                createdDate,
                merchantCategory,
                merchantCountry,
                entryMethod,
                userId,
                type,
                source,
                false
            ).left
        assertAll(
            { assert(result.count() == 1) },
            { assert(result.all { it is Transaction.TransactionError.TransactionAmountError }) },
        )
    }

    @Test
    fun `error createdDate test Transaction created date is in the future`() {
        val transactionId : Long  = 1
        val currency = "GBP"
        val amount = 500.0
        val state = Transaction.TransactionState.FAILED
        val createdDate = LocalDateTime.now().plusYears(1)
        val merchantCategory = "supermarket"
        val merchantCountry = "AUS"
        val entryMethod = Transaction.TransactionEntryMethod.MAGS
        val userId: Long = 10
        val type = Transaction.TransactionType.CARD_PAYMENT
        val source = Transaction.TransactionSource.NYX
        val result =
            Transaction.of(
                transactionId,
                currency,
                amount,
                state,
                createdDate,
                merchantCategory,
                merchantCountry,
                entryMethod,
                userId,
                type,
                source,
                false
            ).left
        assertAll(
            { assert(result.count() == 1) },
            { assert(result.all { it is Transaction.TransactionError.TransactionCreatedDateError }) },
        )
    }

    @Test
    fun `error merchantCategory test Transaction empty merchantCategory`() {
        val transactionId : Long  = 1
        val currency = "GBP"
        val amount = 500.0
        val state = Transaction.TransactionState.REVERTED
        val createdDate = LocalDateTime.now().minusYears(1)
        val merchantCategory = ""
        val merchantCountry = "AUS"
        val entryMethod = Transaction.TransactionEntryMethod.MISC
        val userId: Long = 10
        val type = Transaction.TransactionType.P2P
        val source = Transaction.TransactionSource.CRONUS
        val result =
            Transaction.of(
                transactionId,
                currency,
                amount,
                state,
                createdDate,
                merchantCategory,
                merchantCountry,
                entryMethod,
                userId,
                type,
                source,
                false
            ).left
        assertAll(
            { assert(result.count() == 1) },
            { assert(result.all { it is Transaction.TransactionError.TransactionMerchantCategoryError }) },
        )
    }

    @Test
    fun `error merchantCountry test Transaction empty merchantCountry`() {
        val transactionId : Long  = 1
        val currency = "GBP"
        val amount = 500.0
        val state = Transaction.TransactionState.REVERTED
        val createdDate = LocalDateTime.now().minusYears(1)
        val merchantCategory = "supermarket"
        val merchantCountry = ""
        val entryMethod = Transaction.TransactionEntryMethod.MANU
        val userId: Long = 10
        val type = Transaction.TransactionType.TOPUP
        val source = Transaction.TransactionSource.HERA
        val result =
            Transaction.of(
                transactionId,
                currency,
                amount,
                state,
                createdDate,
                merchantCategory,
                merchantCountry,
                entryMethod,
                userId,
                type,
                source,
                false
            ).left
        assertAll(
            { assert(result.count() == 1) },
            { assert(result.all { it is Transaction.TransactionError.TransactionMerchantCountryError }) },
        )
    }

    @Test
    fun `error userId test Transaction negative userId`() {
        val transactionId : Long  = 1
        val currency = "GBP"
        val amount = 500.0
        val state = Transaction.TransactionState.REVERTED
        val createdDate = LocalDateTime.now().minusYears(1)
        val merchantCategory = "supermarket"
        val merchantCountry = "AUS"
        val entryMethod = Transaction.TransactionEntryMethod.MANU
        val userId: Long = -5
        val type = Transaction.TransactionType.TOPUP
        val source = Transaction.TransactionSource.APOLLO
        val result =
            Transaction.of(
                transactionId,
                currency,
                amount,
                state,
                createdDate,
                merchantCategory,
                merchantCountry,
                entryMethod,
                userId,
                type,
                source,
                false
            ).left
        assertAll(
            { assert(result.count() == 1) },
            { assert(result.all { it is Transaction.TransactionError.TransactionUserIdError }) },
        )
    }

    @Test
    fun ` list of errors userId test Transaction invalid inputs`() {
        val transactionId : Long  = 1
        val currency = ""
        val amount = -500.0
        val state = Transaction.TransactionState.REVERTED
        val createdDate = LocalDateTime.now().plusYears(1)
        val merchantCategory = ""
        val merchantCountry = ""
        val entryMethod = Transaction.TransactionEntryMethod.MCON
        val userId: Long = -5
        val type = Transaction.TransactionType.TOPUP
        val source = Transaction.TransactionSource.MINOS
        val result =
            Transaction.of(
                transactionId,
                currency,
                amount,
                state,
                createdDate,
                merchantCategory,
                merchantCountry,
                entryMethod,
                userId,
                type,
                source,
                false
            ).left
        assertAll(
            { assert(result.count() == 6) },
            { assert(result.any { it is Transaction.TransactionError.TransactionUserIdError }) },
            { assert(result.any { it is Transaction.TransactionError.TransactionMerchantCountryError }) },
            { assert(result.any { it is Transaction.TransactionError.TransactionMerchantCategoryError }) },
            { assert(result.any { it is Transaction.TransactionError.TransactionCreatedDateError }) },
            { assert(result.any { it is Transaction.TransactionError.TransactionCurrencyError }) },
            { assert(result.any { it is Transaction.TransactionError.TransactionAmountError }) },
            )
    }
}