package pk.tagme.app.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.DgsSubscription
import com.netflix.graphql.dgs.InputArgument
import org.reactivestreams.Publisher
import pk.tagme.app.data.AccountRepository
import pk.tagme.app.generated.types.Account
import pk.tagme.app.generated.types.Withdrawal
import reactor.core.publisher.ConnectableFlux
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.util.UUID
import javax.annotation.PostConstruct

@DgsComponent
class AccountDataFetcher(
    private val accountRepository: AccountRepository
) {

    private lateinit var reviewsStream: FluxSink<Account?>
    private lateinit var reviewsPublisher: ConnectableFlux<Account?>

    @PostConstruct
    fun init() {
        val publisher: Flux<Account?> = Flux.create<Account?> { emitter: FluxSink<Account?> ->
            reviewsStream = emitter
        }

        reviewsPublisher = publisher.publish()
        reviewsPublisher.connect()
    }

    @DgsQuery
    fun accounts(): Flux<Account> {
        return accountRepository.findAll().map {
            Account(
                id = it.id?.toString(),
                name = it.name,
                balance = it.balance.toDouble()
            )
        }
    }

    @DgsMutation
    fun withdraw(@InputArgument withdrawal: Withdrawal): Mono<Account> {
        val accountMono = accountRepository.findById(UUID.fromString(withdrawal.accountId))
        return accountMono.flatMap {
            it.balance = it.balance.subtract(BigDecimal.valueOf(withdrawal.amount))
            val mono = accountRepository.save(it)

            mono
        }.map {
            val account = Account(
                id = it.id?.toString(),
                name = it.name,
                balance = it.balance.toDouble()
            )

            reviewsStream.next(account)

            account
        }
    }

    @DgsSubscription
    fun accountBalanceChanged(@InputArgument accountId: String?): Publisher<Account?>? {
        return reviewsPublisher
    }
}
