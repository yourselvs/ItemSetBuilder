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

package net.rithms.riot.api.endpoints.summoner.methods;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Level;

import com.google.gson.reflect.TypeToken;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.endpoints.summoner.SummonerApiMethod;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Region;
import net.rithms.util.Convert;

public class GetSummonersByName extends SummonerApiMethod {

	public GetSummonersByName(ApiConfig config, Region region, String summonerNames) {
		super(config);
		setRegion(region);
		summonerNames = Convert.normalizeSummonerName(summonerNames);
		setReturnType(new TypeToken<Map<String, Summoner>>() {
		}.getType());
		try {
			setUrlBase(region.getEndpoint() + "/v1.4/summoner/by-name/" + URLEncoder.encode(summonerNames, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// This should never happen
			RiotApi.log.log(Level.SEVERE, "URL Encoding Failed", e);
		}
		addApiKeyParameter();
	}
}