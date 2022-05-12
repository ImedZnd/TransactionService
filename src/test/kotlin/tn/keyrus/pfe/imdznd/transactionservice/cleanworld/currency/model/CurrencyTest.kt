package tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CurrencyTest {

    @Test
    fun `valid test currency non empty field`() {
        val currencyName = "AED"
        val codeISO = 784
        val exponent = 2
        val isCrypto = false
        val result =
            Currency.of(
                currencyName,
                codeISO,
                exponent,
                isCrypto
            ).get()
        assertAll(
            { assert(result.currencyName == currencyName) },
            { assert(result.codeISO == codeISO) },
            { assert(result.exponent == exponent) },
            { assert(result.isCrypto == isCrypto) },
        )
    }

    @Test
    fun `error empty name currency name is empty`() {
        val currencyName = ""
        val codeISO = 784
        val exponent = 2
        val isCrypto = false
        val result =
            Currency.of(
                currencyName,
                codeISO,
                exponent,
                isCrypto
            ).left
        assertAll(
            { assert(result.count() == 1) },
            { assert(result.all { it is Currency.CurrencyError.CurrencyNameError }) },
        )
    }

    @Test
    fun `error name with currency name has numbers in it`() {
        val currencyName = "hhd5"
        val codeISO = 784
        val exponent = 2
        val isCrypto = false
        val result =
            Currency.of(
                currencyName,
                codeISO,
                exponent,
                isCrypto
            ).left
        assertAll(
            { assert(result.count() == 1) },
            { assert(result.all { it is Currency.CurrencyError.CurrencyNameError }) },
        )
    }

    @Test
    fun `error isoCode with isoCode is negative `() {
        val currencyName = "AED"
        val codeISO = -500
        val exponent = 2
        val isCrypto = false
        val result =
            Currency.of(
                currencyName,
                codeISO,
                exponent,
                isCrypto
            ).left
        assertAll(
            { assert(result.count() == 1) },
            { assert(result.all { it is Currency.CurrencyError.CodeISOError }) },
        )
    }

    @Test
    fun `error exponent with numbers exponent in currency is negative`() {
        val currencyName = "AED"
        val codeISO = 524
        val exponent = -3
        val isCrypto = false
        val result =
            Currency.of(
                currencyName,
                codeISO,
                exponent,
                isCrypto
            ).left
        assertAll(
            { assert(result.count() == 1) },
            { assert(result.all { it is Currency.CurrencyError.ExponentError }) },
        )
    }

    @Test
    fun ` 3 errors with 3 invalid fields`() {
        val currencyName = "A5ED"
        val codeISO = -5
        val exponent = -3
        val isCrypto = false
        val result =
            Currency.of(
                currencyName,
                codeISO,
                exponent,
                isCrypto
            ).left
        assertAll(
            { assert(result.count() == 3) },
            { assert(result.any { it is Currency.CurrencyError.ExponentError }) },
            { assert(result.any { it is Currency.CurrencyError.CodeISOError }) },
            { assert(result.any { it is Currency.CurrencyError.CurrencyNameError }) },
        )
    }
}