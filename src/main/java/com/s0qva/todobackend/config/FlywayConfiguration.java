package com.s0qva.todobackend.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@DependsOn("dataSource")
@Profile("prod")
public class FlywayConfiguration {
    private DataSource dataSource;

    @Value("${spring.flyway.locations}")
    private String locations;

    @Autowired
    public FlywayConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @ConditionalOnProperty(prefix = "spring.flyway", name = "enabled", matchIfMissing = true)
    @ConfigurationProperties(prefix = "spring.flyway")
    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return Flyway.configure()
                .locations(locations)
                .dataSource(dataSource)
                .load();
    }
}
