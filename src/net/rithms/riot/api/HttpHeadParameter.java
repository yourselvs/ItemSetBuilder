/*
 * Copyright 2016 Taylor Caldwell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.rithms.riot.api;

public class HttpHeadParameter implements Cloneable {

	private final String key;
	private final String value;

	public HttpHeadParameter(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public HttpHeadParameter clone() {
		return new HttpHeadParameter(key, value);
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}