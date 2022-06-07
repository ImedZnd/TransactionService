package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.framework.transaction.repository

import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.currency.model.Currency
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.repository.TransactionRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.repository.CurrencyDatabaseRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.currency.repository.CurrencyReactiveRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.framework.Initializer
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository.TransactionDatabaseRepository
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.repository.TransactionReactiveRepository
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ContextConfiguration(initializers = [Initializer::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TransactionDatabaseRepositoryTest(
    @Autowired val transactionReactiveRepository: TransactionReactiveRepository,
    @Autowired val transactionDatabaseRepository: TransactionDatabaseRepository,
    @Autowired val currencyDatabaseRepository: CurrencyDatabaseRepository,
    @Autowired val currencyReactiveRepository: CurrencyReactiveRepository,
    @Autowired private val rabbitAdmin: RabbitAdmin,
    ) {

    @BeforeAll
    fun beforeAll() {
        transactionReactiveRepository.deleteAll().subscribe()
        currencyReactiveRepository.deleteAll().subscribe()
    }

    @BeforeEach
    fun beforeEach() {
        transactionReactiveRepository.deleteAll().subscribe()
        currencyReactiveRepository.deleteAll().subscribe()
    }

    @AfterEach
    fun afterEach() {
        transactionReactiveRepository.deleteAll().subscribe()
        currencyReactiveRepository.deleteAll().subscribe()
    }

    @AfterAll
    fun afterAll() {
        transactionReactiveRepository.deleteAll().subscribe()
        currencyReactiveRepository.deleteAll().subscribe()
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactions()
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By Currency return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByCurrency(currencyName)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By State return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByState(state)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By CreatedDate return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByCreatedDate(createdDate)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By CreatedDateBetweenDate return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByCreatedDateBetween(createdDate.minusYears(1),createdDate)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By Created Date before return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByCreatedDateBefore(LocalDateTime.now())
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By Created Date after return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByCreatedDateAfter(createdDate.minusYears(3))
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By Merchant Category return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByMerchantCategory(merchantCategory)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By Merchant Country return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByMerchantCountry(merchantCountry)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By Entry Method return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByEntryMethod(entryMethod)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By User Id return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByUserId(userId)
            val y = result.count()
            assert(y == 1)
        }
    }
    @Test
    fun `find all By type return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByType(type)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By sourec return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsBySource(source)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By currency and created date return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByCurrencyAndCreatedDate(currencyName,createdDate)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By merchant category and user id return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByMerchantCategoryAndUserId(merchantCategory, userId)
            val y = result.count()
            println(y)
            assert(y == 1)
        }
    }

    @Test
    fun `find all By Type And User Id return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByTypeAndUserId(type, userId)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `find all By Entry Method And User Id return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .findAllTransactionsByEntryMethodAndUserId(entryMethod, userId)
            val y = result.count()
            assert(y == 1)
        }
    }

    @Test
    fun `flag transaction return one element on save operation if repository is have one element`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
            val x =
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .flagTransaction(x.get().transactionId!!)
            val f = result.get().flaged
            val y = result.count()
            assert(y == 1)
            assert(f)
        }
    }

    @Test
    fun `flag transaction return error on save operation if repository is flaged transaction`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
                    amount,
                    state,
                    createdDate,
                    merchantCategory,
                    merchantCountry,
                    entryMethod,
                    userId,
                    type,
                    source,
                    true
                ).get()
            val x =
                transactionDatabaseRepository.saveTransaction(transaction)
            val result =
                transactionDatabaseRepository
                    .flagTransaction(x.get().transactionId!!)
            assert(result.left is TransactionRepository.TransactionRepositoryError.TransactionFlagedRepositoryIOError)
        }
    }

    @Test
    fun `publish flag transaction return flaged on save operation if repository is flaged transaction`() {
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
            val amount = 3738.0
            val state = Transaction.TransactionState.COMPLETED
            val createdDate = LocalDateTime.now().minusYears(1)
            val merchantCategory = "supermarket"
            val merchantCountry = "AUS"
            val entryMethod = Transaction.TransactionEntryMethod.CHIP
            val userId: Long = 10
            val type = Transaction.TransactionType.ATM
            val source = Transaction.TransactionSource.GAIA
            val transaction =
                Transaction.of(
                    null,
                    currencyName,
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
            val x =
                transactionDatabaseRepository.saveTransaction(transaction)
                transactionDatabaseRepository
                    .publishFlagTransaction(x.get().transactionId!!)
            val result = rabbitAdmin.getQueueInfo("flagtransactionqueue").messageCount
            assert(result == 1)
        }
    }

    @Test
    fun `publish flag transaction return transaction not exist `() {
        runBlocking {
            val result =
                transactionDatabaseRepository
                    .flagTransaction(10)
            assert(result.left is TransactionRepository.TransactionRepositoryError.TransactionNotExistRepositoryError)
        }
    }

}