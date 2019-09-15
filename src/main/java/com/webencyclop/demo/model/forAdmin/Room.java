package com.webencyclop.demo.model.forAdmin;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="Number_Room")
    private String numberRoom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberRoom='" + numberRoom + '\'' +
                '}';
    }
}
