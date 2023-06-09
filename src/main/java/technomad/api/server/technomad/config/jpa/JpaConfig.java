package technomad.api.server.technomad.config.jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"technomad.api.server.technomad"})
public class JpaConfig {
    private final HibernateProperties hibernateProperties;
    public JpaConfig(HibernateProperties hibernateProperties){
        this.hibernateProperties = hibernateProperties;
    }

    @Primary
    @Bean(name = "jpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        JpaProperties properties = jpaProperties();
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(properties.isShowSql());
        adapter.setDatabasePlatform(properties.getDatabasePlatform());
        adapter.setGenerateDdl(properties.isGenerateDdl());
        return adapter;
    }

    @Primary
    @Bean(name = "entityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
            @Qualifier("jpaVendorAdapter") JpaVendorAdapter jpaVendorAdapter
    ) {
        return new EntityManagerFactoryBuilder(
                jpaVendorAdapter,
                jpaProperties().getProperties(),
                null);
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            @Qualifier("entityManagerFactoryBuilder") EntityManagerFactoryBuilder entityManagerFactoryBuilder
    ) {
        JpaProperties jpaProperties = jpaProperties();
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .properties(hibernateProperties.determineHibernateProperties(
                        jpaProperties.getProperties(), new HibernateSettings()))
                .packages("technomad.api.server.technomad")
                .build();
    }
}