package org.example.daoproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tickets")
public class Controller {

	@Autowired
	TicketRepository repository;

	@GetMapping("/{id}")
	public ResponseEntity<TicketDto> getTicket(@PathVariable final Long id) {

		final Optional<Ticket> byId = repository.findById(id);

		if (byId.isPresent()) {
			return ResponseEntity.ok(byId.get().toDto());
		} else {
			return ResponseEntity.noContent().build();
		}

	}

	@PostMapping
	public ResponseEntity<String> createTicket(@RequestBody TicketDto ticketDto) {

		final Ticket ticket = new Ticket();
		ticket.setTitle(ticketDto.getTitle());
		ticket.setDescription(ticketDto.getDescription());

		final Ticket savedTicket = repository.save(ticket);

		return ResponseEntity.ok(String.valueOf(savedTicket.getId()));

	}

	@PutMapping("/{id}")
	public ResponseEntity<TicketDto> updateTicket(@PathVariable final Long id, @RequestBody TicketDto ticketDto) {

		final Optional<Ticket> ticketById = repository.findById(id);

		if (ticketById.isPresent()) {
			try {
				final Ticket ticket = ticketById.get();
				ticket.setTitle(ticketDto.getTitle());
				ticket.setDescription(ticketDto.getDescription());
				ticket.setStatus(TicketStatus.valueOf(ticketDto.getStatus()));
				repository.save(ticket);
				return ResponseEntity.ok().build();

			} catch (IllegalArgumentException e) {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.noContent().build();

		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<TicketDto> deleteTicket(@PathVariable final Long id) {

		final Optional<Ticket> ticketById = repository.findById(id);

		if (ticketById.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.noContent().build();

		}

	}

	@GetMapping
	public ResponseEntity<List<TicketDto>> getTickets() {

		final List<Ticket> all = (List<Ticket>) repository.findAll();
		return ResponseEntity.ok(all.stream().map(Ticket::toDto).collect(Collectors.toList()));

	}
}
