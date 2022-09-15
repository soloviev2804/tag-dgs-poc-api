package pk.tagme.app.data

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import pk.tagme.app.model.Account
import pk.tagme.app.model.Card
import java.util.UUID

interface CardRepository : ReactiveCrudRepository<Card, UUID>
