package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.dao

import org.apache.logging.log4j.util.Strings
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.model.Currency

@Table("currency")
data class CurrencyDAO(
    @Id
    val currencyName: String = "A",
    val codeISO: Int = 1,
    val exponent: Int = 1,
    val crypto: Boolean = false,
) : Persistable<String> {

    fun toCurrency() =
        Currency.of(
            currencyName,
            codeISO,
            exponent,
            crypto
        )

    companion object {
        fun Currency.toDAO() =
            CurrencyDAO(
                currencyName,
                codeISO,
                exponent,
            )
    }

    override fun getId(): String = currencyName

    override fun isNew(): Boolean = Strings.isNotEmpty(currencyName)

}