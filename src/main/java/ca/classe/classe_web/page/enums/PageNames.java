package ca.classe.classe_web.page.enums;

public enum PageNames {
	SUBJECT("Subject", "Les matières"), MODIFY("Modify", "Modification");
	
	private String uri;
	private String title;
	
	private PageNames(String uri, String title) {
		this.uri = uri;
		this.title = title;
	}
	
	public String getUri() {
		return this.uri;
	}

	public String getTitle() {
		return this.title;
	}
	
	public static PageNames getByUri(String uri) {
		for(PageNames pn : values()) {
			if (pn.getUri().equals(uri)) {
				return pn;
			}
		}
		return null;
	}
}
