package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.queue

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "transaction.event")
@ConstructorBinding
class TransactionQueueSettings {

    var flag: Event? = null

    @ConstructorBinding
    data class Event(
        val queue: String = "",
        val exchange: String = "",
        val routingkey: String = ""
    )
}