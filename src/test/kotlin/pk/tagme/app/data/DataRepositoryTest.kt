package pk.tagme.app.data

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import pk.tagme.app.BasicContextIntegrationTest
import pk.tagme.app.model.Account
import pk.tagme.app.model.Card
import pk.tagme.app.model.PaymentLimit
import java.math.BigDecimal

class DataRepositoryTest : BasicContextIntegrationTest() {

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Autowired
    lateinit var cardRepository: CardRepository

    @Autowired
    lateinit var paymentLimitRepository: PaymentLimitRepository

    @Test
    fun `basic repo test`() {
        val account = accountRepository.save(Account(null, "account", BigDecimal.TEN)).block()
        val visa = cardRepository.save(Card(name = "MY VISA", accountId = account?.id)).block()
        // val mastercard = cardRepository.save(Card(name = "MY MASTERCARD", accountId = account?.id)).block()

        paymentLimitRepository.save(PaymentLimit(type = "daily", amount = BigDecimal.TEN, cardId = visa?.id)).block()
        println(account)
    }
}
