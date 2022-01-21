/*
 * Copyright 2002-2006 the original author or authors.
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

package com.jachs.spring_scheduling;

/**
 * @author Rob Harrop
 */
public class QuartzTestBean {

	private int importCount;

	private int exportCount;


	public void doImport() {
		++importCount;
		System.out.println("doImport\t"+importCount);
	}

	public void doExport() {
		++exportCount;
		System.out.println("doExport\t"+exportCount);
	}

	public int getImportCount() {
		return importCount;
	}

	public int getExportCount() {
		return exportCount;
	}

}
