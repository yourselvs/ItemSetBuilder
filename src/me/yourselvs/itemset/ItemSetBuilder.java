package me.yourselvs.itemset;

import java.util.HashMap;
import java.util.Map;

import me.yourselvs.itemdata.ItemData;
import net.rithms.riot.api.endpoints.static_data.dto.Item;

public class ItemSetBuilder {
	private static Map<Integer, String> itemSetMeta = new HashMap<Integer, String>();
	
	public static String buildItemSetMeta(ItemSet set) {
		Map<String, Item> items = ItemData.getInstance().getItemList().getData();
		
		
		if(!itemSetMeta.containsKey(set.hashCode())) {
			String text = "";
			
			text += "Champion: " + set.getChampion() + "\n";
			text += "Title: " + set.getTitle() + "\n";
			text += "Items:\n";
			
			for(ItemSetBlock block : set.getBlocks()) {
				text += "  " + block.getType() + "\n";
				
				for(ItemSetItem item : block.getItems()) {
					if(items.get(item.getId()) == null) {
						continue;
					}
					
					text += "     " + items.get(item.getId()).getName();
					
					if(item.getCount() != 1) {
						text += ", " + item.getCount();
					}
					
					text += "\n";
				}
			}
			
			itemSetMeta.put(set.hashCode(), text);
		}
		
		return itemSetMeta.get(set.hashCode());
	}
}
