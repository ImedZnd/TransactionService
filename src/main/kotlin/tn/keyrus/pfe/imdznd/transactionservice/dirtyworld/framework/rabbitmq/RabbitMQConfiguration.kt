package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.framework.rabbitmq

import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfiguration {

    @Bean
    fun rabbitAdmin( rabbitTemplate: RabbitTemplate) =
        RabbitAdmin(rabbitTemplate)

}