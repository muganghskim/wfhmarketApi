//package com.wfhmarket.api.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "postgresEntityManagerFactory", transactionManagerRef = "postgresTransactionManager", basePackages = {
//        "com.wfhmarket.api.repository" })
//public class PostgresDataSourceConfig {
//
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties postgresDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.configuration")
//    public DataSource postgresDataSource(
//            @Qualifier("postgresDataSourceProperties") DataSourceProperties dataSourceProperties) {
//        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder builder,
//                                                                              @Qualifier("postgresDataSource") DataSource dataSource) {
//        return builder.dataSource(dataSource).packages("com.wfhmarket.api.model")
//                .persistenceUnit("postgresEntityManager").build();
//    }
//
//    @Bean
//    public PlatformTransactionManager postgresTransactionManager(
//            @Qualifier("postgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//}

//package com.wfhmarket.api.config;
//
//import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
//import io.r2dbc.postgresql.PostgresqlConnectionFactory;
//import io.r2dbc.spi.ConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//
//@Configuration
//public class PostgresDataSourceConfig extends AbstractR2dbcConfiguration {
//
//    @Value("${spring.r2dbc.url}")
//    private String url;
//
//    @Value("${spring.r2dbc.username}")
//    private String username;
//
//    @Value("${spring.r2dbc.password}")
//    private String password;
//
//    @Value("${spring.r2dbc.properties.schema}")
//    private String schema;
//
//    private static final String R2DBC_POSTGRESQL_PROTOCOL = "r2dbc:postgresql://";
//
//    private String getPostgresHost() {
//        return url.replace(R2DBC_POSTGRESQL_PROTOCOL, "").split(":")[0];
//    }
//
//    private int getPostgresPort() {
//        String[] urlParts = url.split(":");
//        if (urlParts.length >= 3) {
//            return Integer.parseInt(urlParts[2].split("/")[0]);
//        } else {
//            return 5432; // PostgreSQL 기본 포트
//        }
//    }
//
//    private String getPostgresDatabase() {
//        return url.split("/")[3];
//    }
//
//    @Bean
//    @Override
//    public ConnectionFactory connectionFactory() {
//        PostgresqlConnectionConfiguration.Builder configurationBuilder = PostgresqlConnectionConfiguration.builder()
//                .host(getPostgresHost())
//                .port(getPostgresPort())
//                .username(username)
//                .password(password)
//                .database(getPostgresDatabase())
//                .schema(schema);
//
//        return new PostgresqlConnectionFactory(configurationBuilder.build());
//    }
//}


