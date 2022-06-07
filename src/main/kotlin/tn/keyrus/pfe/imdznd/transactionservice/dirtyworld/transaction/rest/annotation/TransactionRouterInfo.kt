package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.rest.annotation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.Explode
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.enums.ParameterStyle
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.web.bind.annotation.RequestMethod
import tn.keyrus.pfe.imdznd.transactionservice.cleanworld.transaction.model.Transaction
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto.*
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.rest.handler.TransactionHandler

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@RouterOperations(
    RouterOperation(
        path = "/transactions/all",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactions",
        operation = Operation(
            operationId = "findAllTransactions",
            method = "GET",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get all transaction successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                )
            ]
        )
    ),

    RouterOperation(
        path = "/transactions/countAll",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAll",
        operation = Operation(
            operationId = "countAll",
            method = "GET",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count all transaction successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/currencyName/{name}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByCurrency",
        operation = Operation(
            operationId = "findAllTransactionsByCurrency",
            method = "GET",
            parameters = [Parameter(
                name = "name",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All Transaction By Currency Name successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/state/{state}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByState",
        operation = Operation(
            operationId = "findAllTransactionsByState",
            method = "GET",
            parameters = [Parameter(
                name = "state",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by state successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/createdDate",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByCreatedDate",
        operation = Operation(
            operationId = "findAllTransactionsByCreatedDate",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by creation date successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = DateDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/createdDateBetween",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByCreatedDateBetween",
        operation = Operation(
            operationId = "findAllTransactionsByCreatedDateBetween",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by creation date between successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = DateRangeDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/createdDateBefore",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByCreatedDateBefore",
        operation = Operation(
            operationId = "findAllTransactionsByCreatedDateBefore",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by creation date before successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = DateDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/createdDateAfter",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByCreatedDateAfter",
        operation = Operation(
            operationId = "findAllTransactionsByCreatedDateAfter",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by creation date after successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = DateDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/merchantCategory/{merchantCategory}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByMerchantCategory",
        operation = Operation(
            operationId = "findAllTransactionsByMerchantCategory",
            method = "GET",
            parameters = [Parameter(
                name = "merchantCategory",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by merchantCategory successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/merchantCountry/{merchantCountry}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByMerchantCountry",
        operation = Operation(
            operationId = "findAllTransactionsByMerchantCountry",
            method = "GET",
            parameters = [Parameter(
                name = "merchantCountry",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by merchantCountry successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/entryMethod/{entryMethod}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByEntryMethod",
        operation = Operation(
            operationId = "findAllTransactionsByEntryMethod",
            method = "GET",
            parameters = [Parameter(
                name = "entryMethod",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by entryMethod successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/userId/{userId}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByUserId",
        operation = Operation(
            operationId = "findAllTransactionsByUserId",
            method = "GET",
            parameters = [Parameter(
                name = "userId",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by userId successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/type/{type}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByType",
        operation = Operation(
            operationId = "findAllTransactionsByType",
            method = "GET",
            parameters = [Parameter(
                name = "type",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by type successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/source/{source}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsBySource",
        operation = Operation(
            operationId = "findAllTransactionsBySource",
            method = "GET",
            parameters = [Parameter(
                name = "source",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by source successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/merchantCategoryAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByMerchantCategoryAndUserId",
        operation = Operation(
            operationId = "findAllTransactionsByMerchantCategoryAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by MerchantCategoryAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = StringAndUserIdDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/typeAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByTypeAndUserId",
        operation = Operation(
            operationId = "findAllTransactionsByTypeAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by TypeAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = TypeAndUserIdDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/entryMethodAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "findAllTransactionsByEntryMethodAndUserId",
        operation = Operation(
            operationId = "findAllTransactionsByEntryMethodAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "get All transaction by EntryMethodAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Transaction::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = EntryMethodAndUserIdDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/countCurrency/{currency}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllTransactionsByCurrency",
        operation = Operation(
            operationId = "countAllTransactionsByCurrency",
            method = "GET",
            parameters = [Parameter(
                name = "currency",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by Currency successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/countAllInYear/{Year}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllInYear",
        operation = Operation(
            operationId = "countAllInYear",
            method = "GET",
            parameters = [Parameter(
                name = "Year",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by Year successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/countState/{state}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllTransactionsByState",
        operation = Operation(
            operationId = "countAllTransactionsByState",
            method = "GET",
            parameters = [Parameter(
                name = "state",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by state successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/countType/{type}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllTransactionsByType",
        operation = Operation(
            operationId = "countAllTransactionsByType",
            method = "GET",
            parameters = [Parameter(
                name = "type",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by type successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/countSource/{source}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllTransactionsBySource",
        operation = Operation(
            operationId = "countAllTransactionsBySource",
            method = "GET",
            parameters = [Parameter(
                name = "source",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by source successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/countMerchantCategory/{merchantCategory}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllTransactionsMerchantCategory",
        operation = Operation(
            operationId = "countAllTransactionsMerchantCategory",
            method = "GET",
            parameters = [Parameter(
                name = "merchantCategory",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by merchantCategory successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/countMerchantCountry/{merchantCountry}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllTransactionsMerchantCountry",
        operation = Operation(
            operationId = "countAllTransactionsMerchantCountry",
            method = "GET",
            parameters = [Parameter(
                name = "merchantCountry",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by merchantCountry successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/countEntryMethod/{entryMethod}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllTransactionsEntryMethod",
        operation = Operation(
            operationId = "countAllTransactionsEntryMethod",
            method = "GET",
            parameters = [Parameter(
                name = "entryMethod",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by entryMethod successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/countUserId/{userId}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllTransactionsUserId",
        operation = Operation(
            operationId = "countAllTransactionsUserId",
            method = "GET",
            parameters = [Parameter(
                name = "userId",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by userId successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/countIsFlaged/{flag}",
        method = [RequestMethod.GET],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllByIsFlaged",
        operation = Operation(
            operationId = "countAllByIsFlaged",
            method = "GET",
            parameters = [Parameter(
                name = "flag",
                `in` = ParameterIn.PATH,
                style = ParameterStyle.SIMPLE,
                explode = Explode.FALSE,
                required = true
            )],
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by flag successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                )
            ]
        )
    ),
    RouterOperation(
        path = "/transactions/countEntryMethodAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllByEntryMethodAndUserId",
        operation = Operation(
            operationId = "countAllByEntryMethodAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by EntryMethodAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = EntryMethodAndUserIdDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/countCurrencyAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllByCurrencyAndUserId",
        operation = Operation(
            operationId = "countAllByCurrencyAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by CurrencyAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = StringAndUserIdDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/countStateAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllByStateAndUserId",
        operation = Operation(
            operationId = "countAllByStateAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by StateAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = StateAndUserIdDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/countTypeAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllByTypeAndUserId",
        operation = Operation(
            operationId = "countAllByTypeAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by TypeAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = TypeAndUserIdDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/countSourceAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllBySourceAndUserId",
        operation = Operation(
            operationId = "countAllBySourceAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by SourceAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = SourceAndUSerIDDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/countMerchantCategoryLikeAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllByMerchantCategoryLikeAndUserId",
        operation = Operation(
            operationId = "countAllByMerchantCategoryLikeAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by CategoryLikeAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = StringAndUserIdDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/countMerchantCountryAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllByMerchantCountryAndUserId",
        operation = Operation(
            operationId = "countAllByMerchantCountryAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by MerchantCountryAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = StringAndUserIdDTO::class
                    )
                )]
            )
        )
    ),
    RouterOperation(
        path = "/transactions/countIsFlagedAndUserId",
        method = [RequestMethod.POST],
        beanClass = TransactionHandler::class,
        beanMethod = "countAllByIsFlagedAndUserId",
        operation = Operation(
            operationId = "countAllByIsFlagedAndUserId",
            method = "POST",
            responses = [
                ApiResponse(
                    responseCode = "200",
                    description = "count All transaction by FlagedAndUserId successfully.",
                    content = [Content(schema = Schema(implementation = Int::class))]
                ),
                ApiResponse(
                    responseCode = "400",
                    description = "Bad Date Range in Params.",
                    content = [Content(schema = Schema(implementation = String::class))]
                ),
            ],
            requestBody = RequestBody(
                content = [Content(
                    schema = Schema(
                        implementation = BooleanAndUserId::class
                    )
                )]
            )
        )
    )
)
annotation class TransactionRouterInfo