package org.example.daoproject;

public class TicketDto {

	private String id;

	private String title;
	private String description;

	private String status;

	public TicketDto(final String id, final String title, final String description, final String status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public TicketDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}
}
