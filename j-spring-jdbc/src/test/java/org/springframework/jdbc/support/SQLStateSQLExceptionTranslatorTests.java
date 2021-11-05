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

package org.springframework.jdbc.support;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.jdbc.BadSqlGrammarException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * @author Rick Evans
 * @author Juergen Hoeller
 * @author Chris Beams
 */
public class SQLStateSQLExceptionTranslatorTests {

	private static final String REASON = "The game is afoot!";

	private static final String TASK = "Counting sheep... yawn.";

	private static final String SQL = "select count(0) from t_sheep where over_fence = ... yawn... 1";


	@Test
	public void testTranslateNullException() {
		assertThatIllegalArgumentException().isThrownBy(() ->
				new SQLStateSQLExceptionTranslator().translate("", "", null));
	}

	@Test
	public void testTranslateBadSqlGrammar() {
		doTest("07", BadSqlGrammarException.class);
	}

	@Test
	public void testTranslateDataIntegrityViolation() {
		doTest("23", DataIntegrityViolationException.class);
	}

	@Test
	public void testTranslateDataAccessResourceFailure() {
		doTest("53", DataAccessResourceFailureException.class);
	}

	@Test
	public void testTranslateTransientDataAccessResourceFailure() {
		doTest("S1", TransientDataAccessResourceException.class);
	}

	@Test
	public void testTranslateConcurrencyFailure() {
		doTest("40", ConcurrencyFailureException.class);
	}

	@Test
	public void testTranslateUncategorized() {
		assertThat(new SQLStateSQLExceptionTranslator().translate("", "", new SQLException(REASON, "00000000"))).isNull();
	}


	private void doTest(String sqlState, Class<?> dataAccessExceptionType) {
		SQLException ex = new SQLException(REASON, sqlState);
		SQLExceptionTranslator translator = new SQLStateSQLExceptionTranslator();
		DataAccessException dax = translator.translate(TASK, SQL, ex);
		assertThat(dax).as("Specific translation must not result in a null DataAccessException being returned.").isNotNull();
		assertThat(dax.getClass()).as("Wrong DataAccessException type returned as the result of the translation").isEqualTo(dataAccessExceptionType);
		assertThat(dax.getCause()).as("The original SQLException must be preserved in the translated DataAccessException").isNotNull();
		assertThat(dax.getCause()).as("The exact same original SQLException must be preserved in the translated DataAccessException").isSameAs(ex);
	}

}
