package com.oreilly.config;

// import com.oreilly.repositories.AccountRepository;
// import com.oreilly.repositories.JdbcAccountRepository;
// import com.oreilly.services.AccountService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
// import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.Properties;

import javax.sql.DataSource;
/**
 * Instantiates Spring beans.
 * 
 * @see Autowired
 * @see Bean
 * @see ComponentScan
 * @see Configuration
 * @see Environment
 * @see PropertySource
 * @see EnableTransactionManagement
 *
 */
@Configuration
@ComponentScan(basePackages = "com.oreilly")
@PropertySource("classpath:prod.properties")
@EnableTransactionManagement
public class AppConfig {
	/**
	 * Bean reading the properties from a {@link Properties} file in
	 * the classpath.
	 */
    @Autowired
    private Environment env;
    /**
     * Configures bean for test data source.
     * @return Bean
     * @see EmbeddedDatabaseBuilder
     * 
     * <p>{@link DataSource} does not have shutdown method.  {@link EmbeddedDatabase}
     *    and {@link BasicDataSource} do have shutdown method.</p>
     * @return {@link EmbeddedDatabase}
     * @see EmbeddedDatabaseBuilder
     * @see EmbeddedDatabase
     */
    @Bean(name = "dataSource", destroyMethod = "shutdown")
    @Profile("test")
    public DataSource dataSourceForTest() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScripts("data.sql")
                .build();
    }
    /**
     * Configures bean for test transaction manager.
     * @return bean
     */
    @Bean(name = "transactionManager")
    @Profile("test")
    public PlatformTransactionManager transactionManagerForTest() {
        return new DataSourceTransactionManager(dataSourceForTest());
    }
    /**
     * Configures bean for production data source.
<<<<<<< HEAD
=======
     * <p>Should this have a shutdown method specified?</p>
>>>>>>> fd2b606e0138ef2ee47d2e508decb82d9ff786e4
     * @return bean
     */
    @Bean(name = "dataSource")
    @Profile("prod")
    public BasicDataSource dataSourceForProd() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.pass"));
        return dataSource;
    }
    /**
     * Configures bean for production transaction manager.
     * @return bean
     */
    @Bean(name = "transactionManager")
    @Profile("prod")
    public PlatformTransactionManager transactionManagerForProd() {
        return new DataSourceTransactionManager(dataSourceForProd());
    }

}
