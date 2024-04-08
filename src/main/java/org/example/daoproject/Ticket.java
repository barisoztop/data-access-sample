package org.example.daoproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String description;

	private TicketStatus status = TicketStatus.CREATED;

	public Ticket() {
	}

	public Ticket(final Long id, final String title, final String description, final TicketStatus status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
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

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(final TicketStatus status) {
		this.status = status;
	}

	public TicketDto toDto() {
		return new TicketDto(String.valueOf(id), title, description, status.name());
	}
}
