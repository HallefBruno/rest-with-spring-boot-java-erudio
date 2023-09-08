package br.com.erudio.integrationtests.testcontainers;

import java.util.Map;
import java.util.stream.Stream;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
    
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	
	static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>("postgres");
	
	private static void startContainers() {
	    Startables.deepStart(Stream.of(postgreSQL)).join();
	}

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
	    startContainers();
	    ConfigurableEnvironment environment = applicationContext.getEnvironment();
	    MapPropertySource testcontainers = new MapPropertySource("testcontainers", createConnectionConfiguration());
	    environment.getPropertySources().addFirst(testcontainers);
	}
	
	private static Map<String, Object> createConnectionConfiguration() {
	    return Map.of(
	    "spring.datasource.url", postgreSQL.getJdbcUrl(),
	    "spring.datasource.username", postgreSQL.getUsername(),
	    "spring.datasource.password", postgreSQL.getPassword()
	    );
	}
    }
}
