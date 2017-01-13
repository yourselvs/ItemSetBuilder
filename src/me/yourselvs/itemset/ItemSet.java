package me.yourselvs.itemset;

public class ItemSet {
	private String map;
	private ItemSetBlock[] blocks;
	private String title;
	private boolean priority;
	private String mode;
	private String type;
	private int sortrank;
	private String champion;
	
	public String getMap() {return map;}
	public void setMap(String map) {this.map = map;}
	
	public ItemSetBlock[] getBlocks() {return blocks;}
	public void setBlocks(ItemSetBlock[] blocks) {this.blocks = blocks;}

	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}

	public boolean isPriority() {return priority;}
	public void setPriority(boolean priority) {this.priority = priority;}

	public String getMode() {return mode;}
	public void setMode(String mode) {this.mode = mode;}

	public String getType() {return type;}
	public void setType(String type) {this.type = type;}

	public int getSortrank() {return sortrank;}
	public void setSortrank(int sortrank) {this.sortrank = sortrank;}

	public String getChampion() {return champion;}
	public void setChampion(String champion) {this.champion = champion;}
}
