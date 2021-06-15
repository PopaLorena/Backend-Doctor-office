package ro.kronsoft.training.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:database.properties")
public class DatabaseConfiguration {

	public static final String PACKAGES_TO_SCAN = "ro.kronsoft.training.entitis";

	@Autowired
	private Environment env;
	@Value(value = "${hibernate.show_sql}")
	private String hibernateShow;
	@Primary // daca avem doua obiecte la fel, e prioritizata metoda asta
	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSourse = new HikariDataSource();
		dataSourse.setJdbcUrl(env.getRequiredProperty("database.url"));
		dataSourse.setUsername(env.getRequiredProperty("database.username"));
		dataSourse.setPassword(env.getRequiredProperty("database.password"));
		return dataSourse;
	}

	
/*	@Bean
	public DataSource secondDataSource() {
		HikariDataSource dataSourse = new HikariDataSource();
		dataSourse.setJdbcUrl(env.getRequiredProperty("database.url"));
		dataSourse.setUsername(env.getRequiredProperty("database.username"));
		dataSourse.setPassword(env.getRequiredProperty("database.password"));
		return dataSourse;
	}
*/
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan(PACKAGES_TO_SCAN);

		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(adapter);

		Map<String, Object> hibernateProperties = new HashMap<>();
		hibernateProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.ddl-auto"));
		hibernateProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		hibernateProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		emf.setJpaPropertyMap(hibernateProperties);
		return emf;

	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
