package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.configuration

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.transaction.queue.TransactionQueueSettings

@Component
data class TransactionQueueInitializer(
    val rabbitAdmin: RabbitAdmin,
    val transactionQueueSettings: TransactionQueueSettings
    ) {

    @EventListener(ApplicationReadyEvent::class)
    fun onStart() {
        createCommunicationPipe(
            rabbitAdmin,
            transactionQueueSettings.flag?.queue?:"",
            transactionQueueSettings.flag?.exchange?:"",
            transactionQueueSettings.flag?.routingkey?:""
        )
    }

    private fun createCommunicationPipe(
        rabbitAdmin: RabbitAdmin,
        queueName: String,
        exchangeName: String,
        routingkey: String
    ) {
        val queue = Queue(queueName)
        rabbitAdmin.declareQueue(queue)
        val exchange = ExchangeBuilder.directExchange(exchangeName).build<Exchange>()
        rabbitAdmin.declareExchange(exchange)
        val binding: Binding = BindingBuilder.bind(queue).to(exchange).with(routingkey).noargs()
        rabbitAdmin.declareBinding(binding)
    }
}