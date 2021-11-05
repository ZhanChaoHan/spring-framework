/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.jdbc.config;

import java.util.function.Predicate;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.testfixture.EnabledForTestGroups;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.springframework.core.testfixture.TestGroup.LONG_RUNNING;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory.DEFAULT_DATABASE_NAME;

/**
 * @author Dave Syer
 * @author Juergen Hoeller
 * @author Chris Beams
 * @author Sam Brannen
 * @author Stephane Nicoll
 */
class JdbcNamespaceIntegrationTests {

	@Test
	@EnabledForTestGroups(LONG_RUNNING)
	void createEmbeddedDatabase() throws Exception {
		assertCorrectSetup("jdbc-config.xml", "dataSource", "h2DataSource", "derbyDataSource");
	}

	@Test
	@EnabledForTestGroups(LONG_RUNNING)
	void createEmbeddedDatabaseAgain() throws Exception {
		// If Derby isn't cleaned up properly this will fail...
		assertCorrectSetup("jdbc-config.xml", "derbyDataSource");
	}

	@Test
	void createWithResourcePattern() throws Exception {
		assertCorrectSetup("jdbc-config-pattern.xml", "dataSource");
	}

	@Test
	void createWithAnonymousDataSourceAndDefaultDatabaseName() throws Exception {
		assertCorrectSetupForSingleDataSource("jdbc-config-db-name-default-and-anonymous-datasource.xml",
			url -> url.endsWith(DEFAULT_DATABASE_NAME));
	}

	@Test
	void createWithImplicitDatabaseName() throws Exception {
		assertCorrectSetupForSingleDataSource("jdbc-config-db-name-implicit.xml", url -> url.endsWith("dataSource"));
	}

	@Test
	void createWithExplicitDatabaseName() throws Exception {
		assertCorrectSetupForSingleDataSource("jdbc-config-db-name-explicit.xml", url -> url.endsWith("customDbName"));
	}

	@Test
	void createWithGeneratedDatabaseName() throws Exception {
		Predicate<String> urlPredicate = url -> url.startsWith("jdbc:hsqldb:mem:");
		urlPredicate.and(url -> !url.endsWith("dataSource"));
		urlPredicate.and(url -> !url.endsWith("shouldBeOverriddenByGeneratedName"));
		assertCorrectSetupForSingleDataSource("jdbc-config-db-name-generated.xml", urlPredicate);
	}

	@Test
	void createWithEndings() throws Exception {
		assertCorrectSetupAndCloseContext("jdbc-initialize-endings-config.xml", 2, "dataSource");
	}

	@Test
	void createWithEndingsNested() throws Exception {
		assertCorrectSetupAndCloseContext("jdbc-initialize-endings-nested-config.xml", 2, "dataSource");
	}

	@Test
	void createAndDestroy() throws Exception {
		try (ClassPathXmlApplicationContext context = context("jdbc-destroy-config.xml")) {
			DataSource dataSource = context.getBean(DataSource.class);
			JdbcTemplate template = new JdbcTemplate(dataSource);
			assertNumRowsInTestTable(template, 1);
			context.getBean(DataSourceInitializer.class).destroy();
			// Table has been dropped
			assertThatExceptionOfType(BadSqlGrammarException.class).isThrownBy(() ->
					assertNumRowsInTestTable(template, 1));
		}
	}

	@Test
	void createAndDestroyNestedWithHsql() throws Exception {
		try (ClassPathXmlApplicationContext context = context("jdbc-destroy-nested-config.xml")) {
			DataSource dataSource = context.getBean(DataSource.class);
			JdbcTemplate template = new JdbcTemplate(dataSource);
			assertNumRowsInTestTable(template, 1);
			context.getBean(EmbeddedDatabaseFactoryBean.class).destroy();
			// Table has been dropped
			assertThatExceptionOfType(BadSqlGrammarException.class).isThrownBy(() ->
					assertNumRowsInTestTable(template, 1));
		}
	}

	@Test
	void createAndDestroyNestedWithH2() throws Exception {
		try (ClassPathXmlApplicationContext context = context("jdbc-destroy-nested-config-h2.xml")) {
			DataSource dataSource = context.getBean(DataSource.class);
			JdbcTemplate template = new JdbcTemplate(dataSource);
			assertNumRowsInTestTable(template, 1);
			context.getBean(EmbeddedDatabaseFactoryBean.class).destroy();
			 // Table has been dropped
			assertThatExceptionOfType(BadSqlGrammarException.class).isThrownBy(() ->
					assertNumRowsInTestTable(template, 1));
		}
	}

	@Test
	void multipleDataSourcesHaveDifferentDatabaseNames() throws Exception {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(factory).loadBeanDefinitions(new ClassPathResource(
			"jdbc-config-multiple-datasources.xml", getClass()));
		assertBeanPropertyValueOf("databaseName", "firstDataSource", factory);
		assertBeanPropertyValueOf("databaseName", "secondDataSource", factory);
	}

	@Test
	void initializeWithCustomSeparator() throws Exception {
		assertCorrectSetupAndCloseContext("jdbc-initialize-custom-separator.xml", 2, "dataSource");
	}

	@Test
	void embeddedWithCustomSeparator() throws Exception {
		assertCorrectSetupAndCloseContext("jdbc-config-custom-separator.xml", 2, "dataSource");
	}

	private ClassPathXmlApplicationContext context(String file) {
		return new ClassPathXmlApplicationContext(file, getClass());
	}

	private void assertBeanPropertyValueOf(String propertyName, String expected, DefaultListableBeanFactory factory) {
		BeanDefinition bean = factory.getBeanDefinition(expected);
		PropertyValue value = bean.getPropertyValues().getPropertyValue(propertyName);
		assertThat(value).isNotNull();
		assertThat(value.getValue().toString()).isEqualTo(expected);
	}

	private void assertNumRowsInTestTable(JdbcTemplate template, int count) {
		assertThat(template.queryForObject("select count(*) from T_TEST", Integer.class).intValue()).isEqualTo(count);
	}

	private void assertCorrectSetup(String file, String... dataSources) {
		assertCorrectSetupAndCloseContext(file, 1, dataSources);
	}

	private void assertCorrectSetupAndCloseContext(String file, int count, String... dataSources) {
		try (ConfigurableApplicationContext context = context(file)) {
			for (String dataSourceName : dataSources) {
				DataSource dataSource = context.getBean(dataSourceName, DataSource.class);
				assertNumRowsInTestTable(new JdbcTemplate(dataSource), count);
				assertThat(dataSource instanceof AbstractDriverBasedDataSource).isTrue();
				AbstractDriverBasedDataSource adbDataSource = (AbstractDriverBasedDataSource) dataSource;
				assertThat(adbDataSource.getUrl()).contains(dataSourceName);
			}
		}
	}

	private void assertCorrectSetupForSingleDataSource(String file, Predicate<String> urlPredicate) {
		try (ConfigurableApplicationContext context = context(file)) {
			DataSource dataSource = context.getBean(DataSource.class);
			assertNumRowsInTestTable(new JdbcTemplate(dataSource), 1);
			assertThat(dataSource instanceof AbstractDriverBasedDataSource).isTrue();
			AbstractDriverBasedDataSource adbDataSource = (AbstractDriverBasedDataSource) dataSource;
			assertThat(urlPredicate.test(adbDataSource.getUrl())).isTrue();
		}
	}

}
