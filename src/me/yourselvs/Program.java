package me.yourselvs;

import me.yourselvs.gui.GUI;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;

public class Program {
	private static RiotApi api;
	
	public static void main(String[] args) throws RiotApiException {
		ApiConfig test = new ApiConfig();
		test.setKey("93d44b37-3208-41f7-b9d3-c0a405bb305f");
        api = new RiotApi(test);
        
        GUI.run(args);
    }
	
	public static RiotApi getApi() {
		return api;
	}
}