package org.speed.big.company.service.config;

import org.speed.big.company.service.config.DataSourceConfig;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

/*@Configuration
@EnableTransactionManagement
@ComponentScan("org.speed.big.company.service.repository.jpa")
@Import(DataSourceConfig.class)*/
public class JPAConfig {
    private final DataSourceConfig  dataSourceConfig;

    public JPAConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryJpa(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);

        Map<String, Boolean> jpaPropertyMap = new HashMap<>();
        jpaPropertyMap.put("hibernate.format_sql", true);
        jpaPropertyMap.put("hibernate.use_sql_comments", true);
        //ENABLE_LAZY_LOAD_NO_TRANS - решение проблемы(org.hibernate.LazyInitializationException: could not initialize proxy no Session)  но рекомендуеться
        //jpaPropertyMap.put("hibernate.enable_lazy_load_no_trans", true);

        LocalContainerEntityManagerFactoryBean emf =
                new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSourceConfig.dataSource());
        emf.setPackagesToScan("org.speed.big.company.service.model");
        emf.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        emf.setJpaPropertyMap(jpaPropertyMap);

        return  emf;
    }

    @Bean
    public PlatformTransactionManager transactionManagerJpa(EntityManagerFactory entityManagerFactoryJpa) {
        return new JpaTransactionManager(entityManagerFactoryJpa);
    }
}
