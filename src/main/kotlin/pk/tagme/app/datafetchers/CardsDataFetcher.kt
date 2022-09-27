package pk.tagme.app.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import pk.tagme.app.data.CardRepository
import pk.tagme.app.generated.DgsConstants
import pk.tagme.app.generated.types.Account
import pk.tagme.app.generated.types.Card
import reactor.core.publisher.Flux
import java.util.UUID

@DgsComponent
class CardsDataFetcher(
    private val cardRepository: CardRepository
) {

    @DgsData(parentType = DgsConstants.ACCOUNT.TYPE_NAME, field = DgsConstants.ACCOUNT.Cards)
    fun cards(dfe: DgsDataFetchingEnvironment): Flux<Card> {
        val account = dfe.getSource<Account>()
        return cardRepository.findAllByAccountId(UUID.fromString(account?.id)).map {
            Card(id = it.id.toString(), name = it.name)
        }
    }
}
