/*package org.orion.assistant.config.ddbb;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "arma3EntityManagerFactory",
    transactionManagerRef = "arma3TransactionManager",
    basePackages = {
        "org.veluka.Vapi.ddbb.repository.arma3"
    }
)
public class Arma3Config {

    @Bean(name = "arma3DataSource")
    @ConfigurationProperties(prefix = "arma.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "arma3EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    barEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("arma3DataSource") DataSource dataSource ) {
        return
        builder.dataSource(dataSource).packages("org.veluka.Vapi.ddbb.entities.arma3").persistenceUnit("arma").build();
        //TODO: MIRA EL PAQUETE Y ANALIZALO
    }

    @Bean(name = "arma3TransactionManager")
    public PlatformTransactionManager arma3TransactionManager(
        @Qualifier("arma3EntityManagerFactory") EntityManagerFactory arma3EntityManagerFactory ) {
        return new JpaTransactionManager(arma3EntityManagerFactory);
    }
}*/