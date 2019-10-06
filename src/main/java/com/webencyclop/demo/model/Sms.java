package com.webencyclop.demo.model;


import javax.persistence.*;
import java.util.Date;


@Table(name = "sms")
@Entity
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "froms")
    private String froms;

    @Column(name = "tos")
    private String tos;

    @Column(name = "subjects")
    private String subjects;

    @Column(name = "contents")
    private String contents;

    @Column(name = "date_getting_message")
    private Date dateGettingMessage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFroms() {
        return froms;
    }

    public void setFroms(String froms) {
        this.froms = froms;
    }

    public String getTos() {
        return tos;
    }

    public void setTos(String tos) {
        this.tos = tos;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getDateGettingMessage() {
        return dateGettingMessage;
    }

    public void setDateGettingMessage(Date dateGettingMessage) {
        this.dateGettingMessage = dateGettingMessage;
    }
}
