package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.rest.handler

import kotlinx.coroutines.flow.Flow
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.badRequest
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.service.TransactionService
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto.*
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class TransactionHandler(
    private val transactionService: TransactionService,
    private val messageSource: MessageSource
) {
    suspend fun findAllTransactions() =
        transactionService.findAllTransactions()
            .let { okServerResponse(it) }

    suspend fun countyAndNumberCount() =
        transactionService.countyAndNumberCount()
            .let {
                ServerResponse
                    .ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .bodyAndAwait(it)
            }

    suspend fun stateAndNumber() =
        transactionService.stateAndNumber()
            .let {
                ServerResponse
                    .ok()
                    .bodyAndAwait(it)
            }

    suspend fun stateAndNumberStream() =
        transactionService.stateAndNumber()
            .let {
                ServerResponse
                    .ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .bodyAndAwait(it)
            }

    suspend fun entryAndNumber() =
        transactionService.entryAndNumber()
            .let {
                ServerResponse
                    .ok()
                    .bodyAndAwait(it)
            }

    suspend fun entryAndNumberStream() =
        transactionService.entryAndNumber()
            .let {
                ServerResponse
                    .ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .bodyAndAwait(it)
            }

    suspend fun typeAndNumber() =
        transactionService.typeAndNumber()
            .let {
                ServerResponse
                    .ok()
                    .bodyAndAwait(it)
            }

    suspend fun typeAndNumberStream() =
        transactionService.typeAndNumber()
            .let {
                ServerResponse
                    .ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .bodyAndAwait(it)
            }

    suspend fun sourceAndNumber() =
        transactionService.sourceAndNumber()
            .let {
                ServerResponse
                    .ok()
                    .bodyAndAwait(it)
            }

    suspend fun sourceAndNumberStream() =
        transactionService.sourceAndNumber()
            .let {
                ServerResponse
                    .ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .bodyAndAwait(it)
            }

    suspend fun flagAndNumber() =
        transactionService.flagAndNumber()
            .let {
                ServerResponse
                    .ok()
                    .bodyAndAwait(it)
            }

    suspend fun flagAndNumberStream() =
        transactionService.flagAndNumber()
            .let {
                ServerResponse
                    .ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .bodyAndAwait(it)
            }

    suspend fun totalOfAmount() =
        transactionService.totalOfAmount()
            .let {
                ServerResponse
                    .ok()
                    .bodyValueAndAwait(it)
            }

    suspend fun findAllTransactionsByCurrency(serverRequest: ServerRequest) =
        transactionService.findAllTransactionsByCurrency(
            serverRequest.pathVariable("name")
        )
            .let { okServerResponse(it) }

    suspend fun findAllTransactionsByState(serverRequest: ServerRequest) =
        transactionService.findAllTransactionsByState(
            Transaction.TransactionState.valueOf(
                serverRequest.pathVariable("state").uppercase()
            )
        )
            .let { okServerResponse(it) }

    suspend fun findAllTransactionsByCreatedDate(serverRequest: ServerRequest) =
        serverRequest.awaitBody<DateDTO>()
            .let { date ->
                val dateTime = date.toLocalDate()
                if (dateTime.isLeft)
                    badRequestServerResponse("DateError")
                else
                    okServerResponse(
                        transactionService.findAllTransactionsByCreatedDate(
                            LocalDateTime.of(
                                dateTime.get(),
                                LocalTime.now()
                            )
                        )
                    )
            }

    suspend fun findAllTransactionsByCreatedDateBetween(serverRequest: ServerRequest) =
        serverRequest.awaitBody<DateRangeDTO>()
            .let { dateRange ->
                val dateRangeCheck = dateRange.checkStartDateAndEndDate()
                if (dateRangeCheck.isPresent)
                    returnDateErrors(dateRangeCheck)
                else
                    okServerResponse(
                        transactionService.findAllTransactionsByCreatedDateBetween(
                            LocalDateTime.of(dateRange.startDate.toLocalDate().get(), LocalTime.now()),
                            LocalDateTime.of(dateRange.endDate.toLocalDate().get(), LocalTime.now()),
                        )
                    )
            }

    suspend fun findAllTransactionsByCreatedDateBefore(serverRequest: ServerRequest) =
        serverRequest.awaitBody<DateDTO>()
            .let { date ->
                val dateTime = date.toLocalDate()
                if (dateTime.isLeft)
                    badRequestServerResponse("DateError")
                else
                    okServerResponse(
                        transactionService.findAllTransactionsByCreatedDateBefore(
                            LocalDateTime.of(
                                dateTime.get(),
                                LocalTime.now()
                            )
                        )
                    )
            }

    suspend fun findAllTransactionsByCreatedDateAfter(serverRequest: ServerRequest) =
        serverRequest.awaitBody<DateDTO>()
            .let { date ->
                val dateTime = date.toLocalDate()
                if (dateTime.isLeft)
                    badRequestServerResponse("DateError")
                else
                    okServerResponse(
                        transactionService.findAllTransactionsByCreatedDateAfter(
                            LocalDateTime.of(
                                dateTime.get(),
                                LocalTime.now()
                            )
                        )
                    )
            }

    suspend fun findAllTransactionsByMerchantCategory(serverRequest: ServerRequest) =
        transactionService.findAllTransactionsByMerchantCategory(
            serverRequest.pathVariable("merchantCategory")
        )
            .let { okServerResponse(it) }

    suspend fun findAllTransactionsByMerchantCountry(serverRequest: ServerRequest) =
        transactionService.findAllTransactionsByMerchantCountry(
            serverRequest.pathVariable("merchantCountry")
        )
            .let { okServerResponse(it) }

    suspend fun findAllTransactionsByEntryMethod(serverRequest: ServerRequest) =
        transactionService.findAllTransactionsByEntryMethod(
            Transaction.TransactionEntryMethod.valueOf(
                serverRequest.pathVariable("entryMethod").uppercase()
            )
        )
            .let { okServerResponse(it) }

    suspend fun findAllTransactionsByUserId(serverRequest: ServerRequest) =
        transactionService.findAllTransactionsByUserId(
            serverRequest.pathVariable("userId").toLong()
        )
            .let { okServerResponse(it) }

    suspend fun findAllTransactionsByType(serverRequest: ServerRequest) =
        transactionService.findAllTransactionsByType(
            Transaction.TransactionType.valueOf(
                serverRequest.pathVariable("type").uppercase()
            )
        )
            .let { okServerResponse(it) }

    suspend fun findAllTransactionsBySource(serverRequest: ServerRequest) =
        transactionService.findAllTransactionsBySource(
            Transaction.TransactionSource.valueOf(
                serverRequest.pathVariable("source").uppercase()
            )
        )
            .let { okServerResponse(it) }

    suspend fun findAllTransactionsByMerchantCategoryAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<StringAndUserIdDTO>()
            .let {
                transactionService.findAllTransactionsByMerchantCategoryAndUserId(
                    it.string,
                    it.userId
                )
            }.let { okServerResponse(it) }

    suspend fun findAllTransactionsByTypeAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<TypeAndUserIdDTO>()
            .let {
                transactionService.findAllTransactionsByTypeAndUserId(
                    it.type,
                    it.userId
                )
            }.let { okServerResponse(it) }

    suspend fun findAllTransactionsByEntryMethodAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<EntryMethodAndUserIdDTO>()
            .let {
                transactionService.findAllTransactionsByEntryMethodAndUserId(
                    it.entryMethod,
                    it.userId
                )
            }.let { okServerResponse(it) }

    suspend fun countAllTransactionsByCurrency(serverRequest: ServerRequest) =
        transactionService.countAllTransactionsByCurrency(
            serverRequest.pathVariable("currency")
        )
            .let { okCountServerResponse(it) }

    suspend fun countAllTransactionsByState(serverRequest: ServerRequest) =
        transactionService.countAllTransactionsByState(
            Transaction.TransactionState.valueOf(
                serverRequest.pathVariable("state").uppercase()
            )
        )
            .let { okCountServerResponse(it) }

    suspend fun countAllTransactionsByType(serverRequest: ServerRequest) =
        transactionService.countAllTransactionsByType(
            Transaction.TransactionType.valueOf(
                serverRequest.pathVariable("type").uppercase()
            )
        )
            .let { okCountServerResponse(it) }

    suspend fun countAllTransactionsBySource(serverRequest: ServerRequest) =
        transactionService.countAllTransactionsBySource(
            Transaction.TransactionSource.valueOf(
                serverRequest.pathVariable("source").uppercase()
            )
        )
            .let { okCountServerResponse(it) }

    suspend fun countAllTransactionsMerchantCategory(serverRequest: ServerRequest) =
        transactionService.countAllTransactionsMerchantCategory(
            serverRequest.pathVariable("merchantCategory")
        )
            .let { okCountServerResponse(it) }

    suspend fun countAllTransactionsMerchantCountry(serverRequest: ServerRequest) =
        transactionService.countAllTransactionsMerchantCountry(
            serverRequest.pathVariable("merchantCountry")
        )
            .let { okCountServerResponse(it) }

    suspend fun countAllTransactionsEntryMethod(serverRequest: ServerRequest) =
        transactionService.countAllTransactionsEntryMethod(
            Transaction.TransactionEntryMethod.valueOf(
                serverRequest.pathVariable("entryMethod").uppercase()
            )
        )
            .also { print(serverRequest.pathVariable("entryMethod")) }
            .let { okCountServerResponse(it) }

    suspend fun countAllByIsFlaged(serverRequest: ServerRequest) =
        transactionService.countAllByIsFlaged(
            serverRequest.pathVariable("flag").toBoolean()
        ).let { okCountServerResponse(it) }


    suspend fun countAllTransactionsUserId(serverRequest: ServerRequest) =
        transactionService.countAllTransactionsUserId(
            serverRequest.pathVariable("userId").toLong()
        )
            .let { okCountServerResponse(it) }

    suspend fun countAllByEntryMethodAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<EntryMethodAndUserIdDTO>()
            .let {
                transactionService.countAllByEntryMethodAndUserId(
                    it.entryMethod,
                    it.userId
                )
            }.let { okCountServerResponse(it) }

    suspend fun countAllByCurrencyAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<StringAndUserIdDTO>()
            .let {
                transactionService.countAllByCurrencyAndUserId(
                    it.string,
                    it.userId
                )
            }.let { okCountServerResponse(it) }

    suspend fun countCurrencyAndNumberByUserId(serverRequest: ServerRequest) =
        transactionService.countCurrencyAndNumberByUserId(
            serverRequest.pathVariable("userId").toLong()
        ).let {
            ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .bodyAndAwait(it)
        }

    suspend fun countStateAndNumberByUserId(serverRequest: ServerRequest) =
        transactionService.countStateAndNumberByUserId(
            serverRequest.pathVariable("userId").toLong()
        ).let {
            ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .bodyAndAwait(it)
        }

    suspend fun countTypeAndNumberByUserId(serverRequest: ServerRequest) =
        transactionService.countTypeAndNumberByUserId(
            serverRequest.pathVariable("userId").toLong()
        ).let {
            ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .bodyAndAwait(it)
        }

    suspend fun countSourceAndNumberByUserId(serverRequest: ServerRequest) =
        transactionService.countSourceAndNumberByUserId(
            serverRequest.pathVariable("userId").toLong()
        ).let {
            ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .bodyAndAwait(it)
        }

    suspend fun countAllByStateAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<StateAndUserIdDTO>()
            .let {
                transactionService.countAllByStateAndUserId(
                    it.state,
                    it.userId
                )
            }.let { okCountServerResponse(it) }

    suspend fun countAllByTypeAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<TypeAndUserIdDTO>()
            .let {
                transactionService.countAllByTypeAndUserId(
                    it.type,
                    it.userId
                )
            }.let { okCountServerResponse(it) }

    suspend fun countAllBySourceAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<SourceAndUSerIDDTO>()
            .let {
                transactionService.countAllBySourceAndUserId(
                    it.source,
                    it.userId
                )
            }.let { okCountServerResponse(it) }

    suspend fun countAllByMerchantCategoryLikeAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<StringAndUserIdDTO>()
            .let {
                transactionService.countAllByMerchantCategoryLikeAndUserId(
                    it.string,
                    it.userId
                )
            }.let { okCountServerResponse(it) }

    suspend fun countAllByMerchantCountryAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<StringAndUserIdDTO>()
            .let {
                transactionService.countAllByMerchantCountryAndUserId(
                    it.string,
                    it.userId
                )
            }.let { okCountServerResponse(it) }

    suspend fun countAllInYear(serverRequest: ServerRequest) =
        transactionService.countAllByCreatedDateContains(
            serverRequest.pathVariable("year")
        )
            .let { okCountServerResponse(it) }

    suspend fun countFlagedTransactionsByUserIdStream(serverRequest: ServerRequest) =
        transactionService.countFlagedTransactionsByUserId(
            serverRequest.pathVariable("userId").toLong()
        )
            .let {
                ServerResponse
                    .ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .bodyAndAwait(it)
            }

    suspend fun countAllByIsFlagedAndUserId(serverRequest: ServerRequest) =
        serverRequest.awaitBody<BooleanAndUserId>()
            .let {
                transactionService.countAllByIsFlagedAndUserId(
                    it.flag,
                    it.userId
                )
            }.let { okCountServerResponse(it) }

    suspend fun countAll() =
        transactionService.countAll()
            .let { okCountServerResponse(it) }

    suspend fun countAllStream() =
        transactionService.countAll()
            .let {
                ServerResponse
                    .ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .bodyValueAndAwait(it)
            }

    suspend fun flagTransaction(serverRequest: ServerRequest) =
        ServerResponse.ok().bodyValueAndAwait(
            transactionService.flagTransaction(
                serverRequest.pathVariable("transactionId").toLong()
            ).get()
        )

    suspend fun countEntryMethodAndNumberByUserId(serverRequest: ServerRequest) =
        transactionService.countEntryMethodAndNumberByUserId(
            serverRequest.pathVariable("userId").toLong()
        ).let {
            ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .bodyAndAwait(it)
        }

    private suspend fun returnDateErrors(dateRange: Optional<out DateRangeDTO.DateRangeError>) =
        if (dateRange.get() is DateRangeDTO.DateRangeError.DateIsNotValidError)
            dateRange.get().badRequestError("DateIsNotValidError")
        else
            dateRange.get().badRequestError("EndDateBeforeStartDateError")

    private suspend fun DateRangeDTO.DateRangeError.badRequestError(error: String): ServerResponse {
        return badRequest()
            .header(
                "error",
                headerErrorInBadRequestError(error)
            )
            .buildAndAwait()
    }

    private suspend fun badRequestServerResponse(error: String) =
        ServerResponse.badRequest()
            .header(
                "error",
                headerErrorInBadRequestError(error)
            )
            .buildAndAwait()

    private suspend fun okServerResponse(transactions: Flow<Transaction>) =
        ServerResponse
            .ok()
            .bodyAndAwait(
                transactions
            )

    private suspend fun okCountServerResponse(numberOfTransaction: Int) =
        ServerResponse
            .ok()
            .bodyValueAndAwait(
                numberOfTransaction
            )

    private fun headerErrorInBadRequestError(string: String) =
        try {
            messageSource.getMessage(string, null, Locale.US)
        } catch (exception: NoSuchMessageException) {
            "No Such Message Exception Raised"
        }
}