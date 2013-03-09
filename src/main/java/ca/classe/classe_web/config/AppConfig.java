package ca.classe.classe_web.config;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_service.commun.BusEvenementSimple;

@Configuration
@ComponentScan("ca.classe.classe_service")
@EnableTransactionManagement
public class AppConfig {
	@Inject
    org.springframework.core.env.Environment environment;

    @Bean
    public DataSource dataSource() throws Exception {
        DataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource("jdbc/class_manager");
    }
    
    @Bean
    public BusEvenement busEvenement() {
    	return new BusEvenementSimple();
    }

    protected Class<? extends Dialect> getDialect() {
        return MySQL5Dialect.class;
    }

    protected boolean isGenerateDdl() {
        return false;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() throws Exception {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[] { "" });
        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put(Environment.DIALECT, getDialect().getName());

        factoryBean.setJpaPropertyMap(new HashMap<String, Object>());
        addJpaProperties(factoryBean.getJpaPropertyMap());

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter() {

            {
                setGenerateDdl(AppConfig.this.isGenerateDdl());
                setShowSql(Boolean.valueOf(environment.getProperty("hibernate.show_sql", "false")));
            }
        };
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager txManager() throws Exception {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    protected void addJpaProperties(final Map<String, Object> jpaProperties) {
        jpaProperties.put("hibernate.format_sql",
                Boolean.valueOf(environment.getProperty("hibernate.format_sql", "false")));
        jpaProperties.put(" hibernate.default_batch_fetch_size", 4);
    }
}
