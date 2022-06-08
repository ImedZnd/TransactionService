package tn.keyrus.pfe.imdznd.transactionservice

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.framework.Initializer

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ContextConfiguration(initializers = [Initializer::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionServiceApplicationTests {

    @Test
    fun contextLoads() {
    }

}
