package pk.tagme.app.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.UUID

@Table(name = "payment_limits")
data class PaymentLimit(
    @Id
    @Column("id")
    val id: UUID? = null,

    val cardId: UUID? = null,

    val type: String,

    val amount: BigDecimal
)
