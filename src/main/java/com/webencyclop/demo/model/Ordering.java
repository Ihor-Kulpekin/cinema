package com.webencyclop.demo.model;

import com.webencyclop.demo.model.forAdmin.Movie;
import com.webencyclop.demo.model.forAdmin.Room;
import com.webencyclop.demo.model.forAdmin.Ticket;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ordering")
public class Ordering {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Ordering")
    private Date dateOrdering;

    @OneToOne
    @JoinColumn(name = "Id_Movie",referencedColumnName = "Id")
    private Movie movieId;

    @OneToOne
    @JoinColumn(name = "Id_Ticket",referencedColumnName = "Id")
    private Ticket ticketId;

    @OneToOne
    @JoinColumn(name = "Id_User",referencedColumnName = "auth_user_id")
    private User userId;

    @OneToOne
    @JoinColumn(name = "Id_Room",referencedColumnName = "Id")
    private Room roomId;

    @Column(name = "sum_price_ticket")
    private int sum_price_ticket;

    public Ordering() {
    }

    public Ordering(Movie movieId, Ticket ticketId, User userId, Room roomId,int sum_price_ticket) {
        this.movieId = movieId;
        this.ticketId = ticketId;
        this.userId = userId;
        this.roomId = roomId;
        dateOrdering = new Date();
        this.sum_price_ticket = sum_price_ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public Ticket getTicketId() {
        return ticketId;
    }

    public void setTicketId(Ticket ticketId) {
        this.ticketId = ticketId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Date getDateOrdering() {
        return dateOrdering;
    }

    public void setDateOrdering(Date dateOrdering) {
        this.dateOrdering = dateOrdering;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public int getSum_price_ticket() {
        return sum_price_ticket;
    }

    public void setSum_price_ticket(int sum_price_ticket) {
        this.sum_price_ticket = sum_price_ticket;
    }
}
