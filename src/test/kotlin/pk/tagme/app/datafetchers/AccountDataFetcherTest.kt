package pk.tagme.app.datafetchers

import com.jayway.jsonpath.TypeRef
import com.netflix.graphql.dgs.DgsQueryExecutor
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import pk.tagme.app.BasicContextIntegrationTest
import pk.tagme.app.generated.types.Account

internal class AccountDataFetcherTest : BasicContextIntegrationTest() {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @Test
    fun accounts() {
        val accounts = dgsQueryExecutor.executeAndExtractJsonPathAsObject(
            """
                query{
                  accounts{
                    id
                    name
                    balance
                    cards {
                        id
                        name
                    }
                  }
                }
            """.trimIndent(),
            "data.accounts[*]",
            object : TypeRef<List<Account?>?>() {}
        )

        accounts?.forEach {
            Assertions.assertThat(it?.id).isNotEmpty()
            Assertions.assertThat(it?.name).isNotEmpty()
            Assertions.assertThat(it?.balance).isGreaterThan(0.0)
        }
    }

    // @Test
    // fun withdraw(){
    //     dgsQueryExecutor.executeAndExtractJsonPathAsObject("""
    //         mutation{
    //             wi
    //         }
    //     """.trimIndent()
    //     ,"data.account")
    // }
}
