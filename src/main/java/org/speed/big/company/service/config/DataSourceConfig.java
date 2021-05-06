package org.speed.big.company.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
//@PropertySource("classpath:/db/postgres.properties")
public class DataSourceConfig {

/*    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getProperty("database.driverClassName"));
        ds.setUrl(environment.getProperty("database.url"));
        ds.setUsername(environment.getProperty("database.username"));
        ds.setPassword(environment.getProperty("database.password"));

        return ds;
    }*/

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.driverClassName}")
    private String dbDriverClassName;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(dbDriverClassName);
        ds.setUrl(datasourceUrl);
        ds.setUsername(dbUsername);
        ds.setPassword(dbPassword);

        return ds;
    }

}
