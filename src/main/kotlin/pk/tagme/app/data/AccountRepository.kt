package pk.tagme.app.data

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import pk.tagme.app.model.Account
import java.util.UUID

interface AccountRepository : ReactiveCrudRepository<Account, UUID>
