package me.yourselvs.itemdata;

import me.yourselvs.Program;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.constant.ItemListData;
import net.rithms.riot.api.endpoints.static_data.dto.ItemList;
import net.rithms.riot.constant.Region;

public class ItemData{
	private static ItemData data;
	
	public static void init() {
		data = new ItemData();
	}
	
	public static ItemData getInstance() {
		return data;
	}
	
	//ItemData after this point
	
	private ItemList itemList;
	
	public ItemData() {
		RiotApi api = Program.getApi();
		
		try {
			itemList = api.getDataItemList(Region.NA, null, null, ItemListData.ALL);
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
	}
	
	public ItemList getItemList() {
		return itemList;
	}
}
