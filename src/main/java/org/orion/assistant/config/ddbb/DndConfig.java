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
    entityManagerFactoryRef = "dndEntityManagerFactory",
    transactionManagerRef = "dndTransactionManager",
    basePackages = {
        "org.veluka.Vapi.ddbb.repository.dnd"
    }
)
public class DndConfig {

    @Bean(name = "dndDataSource")
    @ConfigurationProperties("dnd.datasource")
    public DataSource dndDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dndEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("dndDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("org.veluka.Vapi.ddbb.entities.dnd").persistenceUnit("dnd").build();
    }

    @Bean(name = "dndTransactionManager")
    public PlatformTransactionManager dndTransactionManager(
        @Qualifier("dndEntityManagerFactory") EntityManagerFactory dndEntityManagerFactory ) {
        return new JpaTransactionManager(dndEntityManagerFactory);
    }
}
*/