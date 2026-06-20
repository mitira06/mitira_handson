package com.example.ems.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 9 / 10: Supplies the EntityManagerFactoryBuilder bean.
 *
 * Normally Spring Boot's HibernateJpaAutoConfiguration creates this bean
 * automatically. It is excluded in EmployeeManagementSystemApplication
 * because, left enabled, it would also try to build its own default
 * "entityManagerFactory"/"transactionManager" pair (picking whichever
 * DataSource is @Primary), which would collide with the two explicit
 * persistence units defined in PrimaryDataSourceConfig and
 * SecondaryDataSourceConfig. So this class hand-builds the same
 * EntityManagerFactoryBuilder, reading the Hibernate properties from
 * application.properties (Exercise 9: externalized configuration) instead
 * of relying on the auto-configuration to bind them.
 */
@Configuration
public class JpaSupportConfig {

    @Value("${spring.jpa.database-platform}")
    private String dialect;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.jpa.show-sql}")
    private String showSql;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String formatSql;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private String batchSize;

    @Value("${spring.jpa.properties.hibernate.order_inserts}")
    private String orderInserts;

    @Value("${spring.jpa.properties.hibernate.order_updates}")
    private String orderUpdates;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(Boolean.parseBoolean(showSql));
        adapter.setGenerateDdl(false); // ddl is controlled via hibernate.hbm2ddl.auto below
        return adapter;
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(JpaVendorAdapter jpaVendorAdapter) {
        return new EntityManagerFactoryBuilder(jpaVendorAdapter, hibernateProperties(), null);
    }

    private Map<String, String> hibernateProperties() {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.dialect", dialect);
        props.put("hibernate.hbm2ddl.auto", ddlAuto);
        props.put("hibernate.show_sql", showSql);
        props.put("hibernate.format_sql", formatSql);
        props.put("hibernate.jdbc.batch_size", batchSize);
        props.put("hibernate.order_inserts", orderInserts);
        props.put("hibernate.order_updates", orderUpdates);
        return props;
    }
}
