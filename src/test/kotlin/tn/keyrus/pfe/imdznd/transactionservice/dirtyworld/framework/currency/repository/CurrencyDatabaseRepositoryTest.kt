package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.framework.currency.repository

import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.model.Currency
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.repository.CurrencyRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.repository.CurrencyDatabaseRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.repository.CurrencyReactiveRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.framework.Initializer
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository.TransactionDatabaseRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository.TransactionReactiveRepository

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ContextConfiguration(initializers = [Initializer::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CurrencyDatabaseRepositoryTest(
    @Autowired val currencyDatabaseRepository: CurrencyDatabaseRepository,
    @Autowired val currencyReactiveRepository: CurrencyReactiveRepository,
    @Autowired val transactionReactiveRepository: TransactionReactiveRepository,
    @Autowired val transactionDatabaseRepository: TransactionDatabaseRepository
){

    @BeforeAll
    fun beforeAll() {
        currencyReactiveRepository.deleteAll().subscribe()
        transactionReactiveRepository.deleteAll().subscribe()
    }

    @BeforeEach
    fun beforeEach() {
        currencyReactiveRepository.deleteAll().subscribe()
        transactionReactiveRepository.deleteAll().subscribe()
    }

    @AfterEach
    fun afterEach() {
        currencyReactiveRepository.deleteAll().subscribe()
        transactionReactiveRepository.deleteAll().subscribe()
    }

    @AfterAll
    fun afterAll() {
        currencyReactiveRepository.deleteAll().subscribe()
        transactionReactiveRepository.deleteAll().subscribe()
    }

    @Test
    fun `find all return one element on save operation if repository is have one element`() {
        runBlocking {
            val currencyName = "AED"
            val codeISO = 784
            val exponent = 2
            val isCrypto = false
            val currency =
                Currency.of(
                    currencyName,
                    codeISO,
                    exponent,
                    isCrypto
                ).get()
            currencyDatabaseRepository
                .saveCurrency(
                    currency
                )
            val result =
                currencyDatabaseRepository
                    .findAllCurrency()
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `findCurrencyByCurrencyName return one element on save operation if repository is have one element`() {
        runBlocking {
            val currencyName = "AED"
            val codeISO = 784
            val exponent = 2
            val isCrypto = false
            val currency =
                Currency.of(
                    currencyName,
                    codeISO,
                    exponent,
                    isCrypto
                ).get()
            currencyDatabaseRepository
                .saveCurrency(
                    currency
                )
            val result =
                currencyDatabaseRepository
                    .findCurrencyByCurrencyName(currencyName)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `findCurrencyByCurrencyName return currency not found if repository is empty`() {
        runBlocking {
            val result =
                currencyDatabaseRepository
                    .findCurrencyByCurrencyName("ff")
            assert(result.left is CurrencyRepository.CurrencyRepositoryError.CurrencyNotFoundRepositoryError)
        }
    }

    @Test
    fun `findCurrencyByCodeISO return one element on save operation if repository is have one element`() {
        runBlocking {
            val currencyName = "AED"
            val codeISO = 784
            val exponent = 2
            val isCrypto = false
            val currency =
                Currency.of(
                    currencyName,
                    codeISO,
                    exponent,
                    isCrypto
                ).get()
            currencyDatabaseRepository
                .saveCurrency(
                    currency
                )
            val result =
                currencyDatabaseRepository
                    .findCurrencyByCodeISO(784)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `findCurrencyByCodeISO return currency not found if repository is empty`() {
        runBlocking {
            val result =
                currencyDatabaseRepository
                    .findCurrencyByCodeISO(55)
            assert(result.left is CurrencyRepository.CurrencyRepositoryError.CurrencyNotFoundRepositoryError)
        }
    }

    @Test
    fun `findAllCurrencyByIsCrypto return one element on save operation if repository is have one element`() {
        runBlocking {
            val currencyName = "AED"
            val codeISO = 784
            val exponent = 2
            val isCrypto = false
            val currency =
                Currency.of(
                    currencyName,
                    codeISO,
                    exponent,
                    isCrypto
                ).get()
            currencyDatabaseRepository
                .saveCurrency(
                    currency
                )
            val result =
                currencyDatabaseRepository
                    .findAllCurrencyByIsCrypto(isCrypto)
            val y = result.count()
            assert(y == 1)
        }
    }
}