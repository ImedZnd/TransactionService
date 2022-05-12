package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model

import io.vavr.control.Either
import java.time.LocalDateTime
import java.util.*

class Transaction private constructor(
    val currency: String,
    val amount: Double,
    val state: TransactionState,
    val createdDate: LocalDateTime,
    val merchantCategory: String,
    val merchantCountry: String,
    val entryMethod: TransactionEntryMethod,
    val userId: Long,
    val type: TransactionType,
    val source: TransactionSource,
) {

    companion object Builder {
        fun of(
            currency: String,
            amount: Double,
            state: TransactionState,
            createdDate: LocalDateTime,
            merchantCategory: String,
            merchantCountry: String,
            entryMethod: TransactionEntryMethod,
            userId: Long,
            type: TransactionType,
            source: TransactionSource,
        ) =checkTransaction(
            currency,
            amount,
            createdDate,
            merchantCategory,
            merchantCountry,
            userId,
            ).let {
            checkTransactionErrors(
                it,
                currency,
                amount,
                state,
                createdDate,
                merchantCategory,
                merchantCountry,
                entryMethod,
                userId,
                type,
                source
                )
        }

        private fun checkTransactionErrors(
            transactionErrors :Sequence<TransactionError>,
            currency: String,
            amount: Double,
            state: TransactionState,
            createdDate: LocalDateTime,
            merchantCategory: String,
            merchantCountry: String,
            entryMethod: TransactionEntryMethod,
            userId: Long,
            type: TransactionType,
            source: TransactionSource,
            ):Either<Sequence<TransactionError>,Transaction> =
            if (transactionErrors.count() == 0)
                Either.right(
                    Transaction(
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
                    )
                )
            else
                Either.left(transactionErrors)


        private fun checkTransaction(
            currency: String,
            amount: Double,
            createdDate: LocalDateTime,
            merchantCategory: String,
            merchantCountry: String,
            userId: Long,
        ): Sequence<TransactionError> =
            sequenceOf(
                checkUserId(userId),
                checkMerchantCategory(merchantCategory),
                checkMerchantCountry(merchantCountry),
                checkCreatedDate(createdDate),
                checkCurrency(currency),
                checkAmount(amount)
            )
                .filter { it.isPresent }
                .map { it.get() }

        private fun checkUserId(userId: Long) =
            checkField(
                userId,
                TransactionError.TransactionUserIdError,
            ) { userId > 0 }

        private fun checkMerchantCategory(merchantCategory: String) =
            checkString(
                merchantCategory,
                TransactionError.TransactionMerchantCategoryError
            )

        private fun checkMerchantCountry(merchantCountry: String) =
            checkString(
                merchantCountry,
                TransactionError.TransactionMerchantCountryError
            )

        private fun checkString(string: String, transactionError: TransactionError) =
            checkField(
                string,
                transactionError,
                String::isNotEmpty
            )

        private fun checkCreatedDate(createdDate: LocalDateTime) =
            checkField(
                createdDate,
                TransactionError.TransactionCreatedDateError,
            ) { createdDate.isAfter(LocalDateTime.now()).not() }

        private fun checkCurrency(currency: String) =
            checkField(
                currency,
                TransactionError.TransactionCurrencyError
            ) { currency.length == 3 }

        private fun checkAmount(amount: Double) =
            checkField(
                amount,
                TransactionError.TransactionAmountError
            ) { amount > 0 }


        private fun <T> checkField(
            field: T,
            error: TransactionError,
            validCondition: (T) -> Boolean
        ) =
            if (validCondition(field))
                Optional.empty()
            else
                Optional.of(error)


    }

    enum class TransactionState {
        COMPLETED,
        DECLINED,
        REVERTED,
        FAILED
    }

    enum class TransactionType {
        CARD_PAYMENT,
        P2P,
        TOPUP,
        ATM,
        BANK_TRANSFER
    }

    enum class TransactionSource {
        GAIA,
        INTERNAL,
        OPHION,
        NYX,
        CRONUS,
        HERA,
        APOLLO,
        MINOS,
        LIMOS,
        LETO,
        BRIZO,
    }

    enum class TransactionEntryMethod {
        MISC,
        CHIP,
        MAGS,
        MANU,
        CONT,
        MCON
    }

    sealed interface TransactionError {
        object TransactionCurrencyError : TransactionError
        object TransactionAmountError : TransactionError
        object TransactionCreatedDateError : TransactionError
        object TransactionMerchantCategoryError : TransactionError
        object TransactionMerchantCountryError : TransactionError
        object TransactionUserIdError : TransactionError

    }
}