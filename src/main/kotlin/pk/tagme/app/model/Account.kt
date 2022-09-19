package pk.tagme.app.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.UUID

@Table(name = "accounts")
data class Account(
    @Id
    @Column("id")
    val id: UUID? = null,

    @Column("name")
    val name: String,

    @Column("balance")
    var balance: BigDecimal
)
