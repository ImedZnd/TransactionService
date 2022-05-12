package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.model

import io.vavr.control.Either
import java.util.*

class Currency private constructor(
    val currencyName: String,
    val codeISO: Int,
    val exponent: Int,
    val crypto: Boolean,
) {
    companion object Builder {
        fun of(
            currencyName: String,
            codeISO: Int,
            exponent: Int,
            crypto: Boolean,
        ) =checkCurrency(
            currencyName,
            codeISO,
            exponent
            ).let{
            checkCurrencyErrors(
                it,
                currencyName,
                codeISO,
                exponent,
                crypto
            )
        }

        private fun checkCurrencyErrors(
            currencyErrors: Sequence<CurrencyError> = emptySequence(),
            currencyName: String,
            codeISO: Int,
            exponent: Int,
            crypto: Boolean
            ):Either<Sequence<CurrencyError>,Currency> =
            if (currencyErrors.count() == 0)
                Either.right(
                    Currency(
                        currencyName,
                        codeISO,
                        exponent,
                        crypto
                    )
                )
            else
                Either.left(currencyErrors)

        private fun checkCurrency(
            currencyName: String,
            codeISO: Int,
            exponent: Int,
            ):Sequence<CurrencyError> =
            sequenceOf(
                checkCurrencyName(currencyName),
                checkExponent(exponent),
                checkCodeISO(codeISO)
            )   .filter { it.isPresent }
                .map { it.get() }

        private fun checkCurrencyName(currencyName: String) =
            checkField(
                currencyName,
                CurrencyError.CurrencyNameError
            ) { currencyName.all{ it.isLetter() } and currencyName.isNotEmpty() }

        private fun checkExponent(exponent: Int) =
            checkNumber(exponent, CurrencyError.ExponentError)

        private fun checkCodeISO(codeISO: Int) =
            checkNumber(codeISO, CurrencyError.CodeISOError)

        private fun checkNumber(number: Int , error :CurrencyError) =
            checkField(
                number,
                error
            ) { number > 0 }

        private fun <T> checkField(
            field: T,
            error: CurrencyError,
            validCondition: (T) -> Boolean
        ) =
            if (validCondition(field))
                Optional.empty()
            else
                Optional.of(error)
    }

    sealed interface CurrencyError {
        object CurrencyNameError : CurrencyError
        object CodeISOError : CurrencyError
        object ExponentError : CurrencyError
    }
}