package me.yourselvs.itemset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ItemSetManager {
	private static Map<String, ArrayList<ItemSet>> itemSets = new TreeMap<String, ArrayList<ItemSet>>();
	private static String lastPath;
	
	public static Map<String, ArrayList<ItemSet>> getItemSets() {
		return itemSets;
	}
	
	private String path;	
	
	private Gson gson;
	
	public ItemSetManager() {
		gson = new Gson();
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void readItemSets() {
		if(!path.equals(lastPath)) {			
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
	
		    folder = getFolder(folder, "Config");
	
		    folder = getFolder(folder, "Champions");
		    
		    listOfFiles = folder.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++) {
		    	if (listOfFiles[i].isDirectory()) {
		    		folder = listOfFiles[i];
		    		
		    		String champName = folder.getName();
		    		
		    		if(!itemSets.containsKey(champName)) {
		    			itemSets.put(champName, new ArrayList<ItemSet>());
		    		}
		    		
		    		folder = getFolder(folder, "Recommended");
		    		
		    		File[] itemSetList = folder.listFiles();
		    		
		    		if(!itemSets.containsKey(champName)) {
		    			itemSets.put(champName, new ArrayList<ItemSet>());
		    		}
		    		
		    		for(File itemSet: itemSetList) {
		    			itemSets.get(champName).add(parseItemSet(folder.getAbsolutePath() + "\\" + itemSet.getName()));
		    		}
		    		
		    	}
		    }
		    
		    lastPath = path;
		}
		
	}
	
	private File getFolder(File folder, String target) {
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isDirectory() && listOfFiles[i].getName().equals(target)) {
	    		return listOfFiles[i];
	    	}
	    }
		
		return null;
	}
	
	private ItemSet parseItemSet(String path) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Type type = new TypeToken<ItemSet>(){}.getType();
		
		return gson.fromJson(br, type);
	}
}
