package pk.tagme.app.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table(name = "cards")
data class Card(
    @Id
    @Column("id")
    val id: UUID? = null,

    val accountId: UUID? = null,

    @Column("name")
    val name: String
)
