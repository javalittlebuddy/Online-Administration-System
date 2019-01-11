package com.ascending.blair.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ascending.blair.repository")
public class DatabaseConfig {

    @Value("#{ applicationProperties['database.serverName']}")
    protected String databaseUrl;

    @Value("#{ applicationProperties['database.userName']}")
    protected String databaseUserName;

    @Value("#{ applicationProperties['database.password']}")
    protected String databasePassword;

    @Value("#{ applicationProperties['database.dataSourceClassName']}")
    protected String driverClassName;

    private BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUserName);
        dataSource.setPassword(databasePassword);
//        dataSource.setValidationQuery(databaseValidationQuery);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }

    @Bean(name="dataSource")
    public DataSource getDataSource() {
        DataSource dataSource = createDataSource();
        return dataSource;
    }

    @Bean(name="entityManagerFactory")
    @Profile("unit")
    public LocalContainerEntityManagerFactoryBean entityUnitManagerFactoryBean() {
       // LocalContainerEntityManagerFactoryBean factoryBean = setUpLocalContainerEntityManagerFactoryBean();
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan(new String[] { "com.ascending.blair.domain","com.ascending.blair.repository" });
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisDialect");
        props.put("hibernate.hbm2ddl.auto", "validate");
//        props.put("hibernate.physical_naming_strategy", "io.ascending.training.extend.hibernate.ImprovedNamingStrategy");
        props.put("hibernate.connection.charSet","UTF-8");
        props.put("hibernate.show_sql","true");
        props.put("org.hibernate.flushMode","ALWAYS");
//            <property name="hibernate.ejb.interceptor" value="com.overture.family.repository.jpa.DBNullsFirstLastInteceptor"/>
        factoryBean.setJpaProperties(props);

        return factoryBean;
    }

    @Bean(name="entityManagerFactory")
    @Profile({"dev", "test", "stage", "prod"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        // LocalContainerEntityManagerFactoryBean factoryBean = setUpLocalContainerEntityManagerFactoryBean();
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan(new String[] { "com.ascending.blair.domain","com.ascending.blair.repository" });
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisDialect");
        props.put("hibernate.hbm2ddl.auto", "validate");
//        props.put("hibernate.physical_naming_strategy", "io.ascending.training.extend.hibernate.ImprovedNamingStrategy");
        props.put("hibernate.connection.charSet","UTF-8");
        props.put("hibernate.show_sql","true");
        props.put("org.hibernate.flushMode","ALWAYS");
//            <property name="hibernate.ejb.interceptor" value="com.overture.family.repository.jpa.DBNullsFirstLastInteceptor"/>
        factoryBean.setJpaProperties(props);

        return factoryBean;
    }

//    private LocalContainerEntityManagerFactoryBean setUpLocalContainerEntityManagerFactoryBean(){
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(getDataSource());
//        factoryBean.setPackagesToScan(new String[] { "com.ascending.blair.domain","com.ascending.blair.repository" });
//        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return factoryBean;
//    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory, @Autowired DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
