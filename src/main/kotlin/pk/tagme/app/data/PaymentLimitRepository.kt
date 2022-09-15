package pk.tagme.app.data

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import pk.tagme.app.model.PaymentLimit
import java.util.UUID

interface PaymentLimitRepository : ReactiveCrudRepository<PaymentLimit, UUID>
