package com.webencyclop.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Column(name = "Genre")
    private String genre;

    @Column(name = "Graduation_Year")
    private int graduationYear;

    @Column(name = "Producer")
    private String director;

    @Column(name = "Duration")
    private String duration;

    @Column(name = "Main_Roles")
    private String mainRoles;

    @Column(name = "Discription")
    private String discription;

    @Column(name = "Url_Trailer")
    private String urlTrailer;

    @Column(name = "Url_Image")
    private String urlImage;

    @Column(name = "Name")
    private String nameMovie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMainRoles() {
        return mainRoles;
    }

    public void setMainRoles(String mainRoles) {
        this.mainRoles = mainRoles;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public void setUrlTrailer(String urlTrailer) {
        this.urlTrailer = urlTrailer;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }
}
