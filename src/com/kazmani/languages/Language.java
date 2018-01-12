package com.kazmani.languages;

public class Language {

	private int langId;
	private String langName;
	private String fluency;//average,very-good,excellent
	
	
	public Language(int langId, String langName, String fluency) {
		super();  
		this.langId = langId;
		this.langName = langName;
		this.fluency = fluency;
	}
	public String getFluency() {
		return fluency;
	}
	public void setFluency(String fluency) {
		this.fluency = fluency;
	}
	public int getLangId() {
		return langId;
	}
	public void setLangId(int langId) {
		this.langId = langId;
	}
	public String getLangName() {
		return langName;
	}
	public void setLangName(String langName) {
		this.langName = langName;
	}
	
}
