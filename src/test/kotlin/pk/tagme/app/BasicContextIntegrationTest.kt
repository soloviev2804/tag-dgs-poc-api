package pk.tagme.app

import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@ActiveProfiles("integration")
@SpringBootTest
class BasicContextIntegrationTest {

    companion object {

        @JvmStatic
        private val postgreSQLContainer = PostgreSQLContainer("postgres:12.2-alpine")
            .withDatabaseName("tag-dsg-poc")
            .withUsername("tag-dsg-poc")
            .withPassword("tag-dsg-poc")

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            postgreSQLContainer.start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add(
                "spring.r2dbc.url",
                { "r2dbc:postgresql://${postgreSQLContainer.getHost()}:${postgreSQLContainer.getFirstMappedPort()}/tag-dsg-poc" }
            )
            registry.add("spring.r2dbc.username", postgreSQLContainer::getUsername)
            registry.add("spring.r2dbc.password", postgreSQLContainer::getPassword)

            registry.add("spring.liquibase.url", postgreSQLContainer::getJdbcUrl)
            registry.add("spring.liquibase.user", postgreSQLContainer::getUsername)
            registry.add("spring.liquibase.password", postgreSQLContainer::getPassword)
        }
    }
}
