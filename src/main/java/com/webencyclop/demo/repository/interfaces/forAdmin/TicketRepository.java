package com.webencyclop.demo.repository.interfaces.forAdmin;

import com.webencyclop.demo.model.forAdmin.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
