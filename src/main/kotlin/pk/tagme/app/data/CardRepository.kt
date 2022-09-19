package pk.tagme.app.data

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import pk.tagme.app.model.Card
import reactor.core.publisher.Flux
import java.util.UUID

interface CardRepository : ReactiveCrudRepository<Card, UUID> {
    fun findAllByAccountId(accountId: UUID): Flux<Card>

    fun findAllByAccountIdIn(accountId: List<UUID>): Flux<Card>
}
