/*
 * Copyright 2002-2019 the original author or authors.
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

package org.springframework.jdbc.core.namedparam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.jdbc.core.RowMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Thomas Risberg
 * @author Phillip Webb
 */
public class NamedParameterQueryTests {

	private DataSource dataSource;

	private Connection connection;

	private PreparedStatement preparedStatement;

	private ResultSet resultSet;

	private ResultSetMetaData resultSetMetaData;

	private NamedParameterJdbcTemplate template;


	@BeforeEach
	public void setup() throws Exception {
		connection = mock(Connection.class);
		dataSource = mock(DataSource.class);
		preparedStatement = mock(PreparedStatement.class);
		resultSet = mock(ResultSet.class);
		resultSetMetaData = mock(ResultSetMetaData.class);
		template = new NamedParameterJdbcTemplate(dataSource);
		given(dataSource.getConnection()).willReturn(connection);
		given(resultSetMetaData.getColumnCount()).willReturn(1);
		given(resultSetMetaData.getColumnLabel(1)).willReturn("age");
		given(connection.prepareStatement(anyString())).willReturn(preparedStatement);
		given(preparedStatement.executeQuery()).willReturn(resultSet);
	}

	@AfterEach
	public void verifyClose() throws Exception {
		verify(preparedStatement).close();
		verify(resultSet).close();
		verify(connection).close();
	}


	@Test
	public void testQueryForListWithParamMap() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, true, false);
		given(resultSet.getObject(1)).willReturn(11, 12);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		List<Map<String, Object>> li = template.queryForList(
				"SELECT AGE FROM CUSTMR WHERE ID < :id", params);

		assertThat(li.size()).as("All rows returned").isEqualTo(2);
		assertThat(((Integer) li.get(0).get("age")).intValue()).as("First row is Integer").isEqualTo(11);
		assertThat(((Integer) li.get(1).get("age")).intValue()).as("Second row is Integer").isEqualTo(12);

		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID < ?");
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForListWithParamMapAndEmptyResult() throws Exception {
		given(resultSet.next()).willReturn(false);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		List<Map<String, Object>> li = template.queryForList(
				"SELECT AGE FROM CUSTMR WHERE ID < :id", params);

		assertThat(li.size()).as("All rows returned").isEqualTo(0);
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID < ?");
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForListWithParamMapAndSingleRowAndColumn() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getObject(1)).willReturn(11);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		List<Map<String, Object>> li = template.queryForList(
				"SELECT AGE FROM CUSTMR WHERE ID < :id", params);

		assertThat(li.size()).as("All rows returned").isEqualTo(1);
		assertThat(((Integer) li.get(0).get("age")).intValue()).as("First row is Integer").isEqualTo(11);
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID < ?");
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForListWithParamMapAndIntegerElementAndSingleRowAndColumn()
			throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(11);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		List<Integer> li = template.queryForList("SELECT AGE FROM CUSTMR WHERE ID < :id",
				params, Integer.class);

		assertThat(li.size()).as("All rows returned").isEqualTo(1);
		assertThat(li.get(0).intValue()).as("First row is Integer").isEqualTo(11);
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID < ?");
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForMapWithParamMapAndSingleRowAndColumn() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getObject(1)).willReturn(11);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		Map<String, Object> map = template.queryForMap("SELECT AGE FROM CUSTMR WHERE ID < :id", params);

		assertThat(((Integer) map.get("age")).intValue()).as("Row is Integer").isEqualTo(11);
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID < ?");
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForObjectWithParamMapAndRowMapper() throws Exception {
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(22);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		Object o = template.queryForObject("SELECT AGE FROM CUSTMR WHERE ID = :id",
				params, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		});

		boolean condition = o instanceof Integer;
		assertThat(condition).as("Correct result type").isTrue();
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID = ?");
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForObjectWithMapAndInteger() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(22);

		Map<String, Object> params = new HashMap<>();
		params.put("id", 3);
		Object o = template.queryForObject("SELECT AGE FROM CUSTMR WHERE ID = :id",
				params, Integer.class);

		boolean condition = o instanceof Integer;
		assertThat(condition).as("Correct result type").isTrue();
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID = ?");
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForObjectWithParamMapAndInteger() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(22);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		Object o = template.queryForObject("SELECT AGE FROM CUSTMR WHERE ID = :id",
				params, Integer.class);

		boolean condition = o instanceof Integer;
		assertThat(condition).as("Correct result type").isTrue();
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID = ?");
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForObjectWithParamMapAndList() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID IN (:ids)";
		String sqlToUse = "SELECT AGE FROM CUSTMR WHERE ID IN (?, ?)";
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(22);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("ids", Arrays.asList(3, 4));
		Object o = template.queryForObject(sql, params, Integer.class);

		boolean condition = o instanceof Integer;
		assertThat(condition).as("Correct result type").isTrue();
		verify(connection).prepareStatement(sqlToUse);
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForObjectWithParamMapAndListOfExpressionLists() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(22);

		MapSqlParameterSource params = new MapSqlParameterSource();
		List<Object[]> l1 = new ArrayList<>();
		l1.add(new Object[] {3, "Rod"});
		l1.add(new Object[] {4, "Juergen"});
		params.addValue("multiExpressionList", l1);
		Object o = template.queryForObject(
				"SELECT AGE FROM CUSTMR WHERE (ID, NAME) IN (:multiExpressionList)",
				params, Integer.class);

		boolean condition = o instanceof Integer;
		assertThat(condition).as("Correct result type").isTrue();
		verify(connection).prepareStatement(
				"SELECT AGE FROM CUSTMR WHERE (ID, NAME) IN ((?, ?), (?, ?))");
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForIntWithParamMap() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(22);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 3);
		int i = template.queryForObject("SELECT AGE FROM CUSTMR WHERE ID = :id", params, Integer.class).intValue();

		assertThat(i).as("Return of an int").isEqualTo(22);
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID = ?");
		verify(preparedStatement).setObject(1, 3);
	}

	@Test
	public void testQueryForLongWithParamBean() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getLong(1)).willReturn(87L);

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(new ParameterBean(3));
		long l = template.queryForObject("SELECT AGE FROM CUSTMR WHERE ID = :id", params, Long.class).longValue();

		assertThat(l).as("Return of a long").isEqualTo(87);
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID = ?");
		verify(preparedStatement).setObject(1, 3, Types.INTEGER);
	}

	@Test
	public void testQueryForLongWithParamBeanWithCollection() throws Exception {
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getLong(1)).willReturn(87L);

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(new ParameterCollectionBean(3, 5));
		long l = template.queryForObject("SELECT AGE FROM CUSTMR WHERE ID IN (:ids)", params, Long.class).longValue();

		assertThat(l).as("Return of a long").isEqualTo(87);
		verify(connection).prepareStatement("SELECT AGE FROM CUSTMR WHERE ID IN (?, ?)");
		verify(preparedStatement).setObject(1, 3);
		verify(preparedStatement).setObject(2, 5);
	}


	static class ParameterBean {

		private final int id;

		public ParameterBean(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}


	static class ParameterCollectionBean {

		private final Collection<Integer> ids;

		public ParameterCollectionBean(Integer... ids) {
			this.ids = Arrays.asList(ids);
		}

		public Collection<Integer> getIds() {
			return ids;
		}
	}

}
