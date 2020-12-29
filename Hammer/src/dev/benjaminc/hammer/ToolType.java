package dev.benjaminc.hammer;

public enum ToolType {
	HAMMER("Hammer", "Not every tool's a hammer"),
	EXCAVATOR("Excavator", "Not your fluffy CAT");
	
	private String suffix;
	private String lore;
	
	ToolType(String suffix, String lore) {
		this.suffix = suffix;
		this.lore = lore;
	}
	
	public String getSuffix() {
		return suffix;
	}
	
	public String getLore() {
		return lore;
	}
}
