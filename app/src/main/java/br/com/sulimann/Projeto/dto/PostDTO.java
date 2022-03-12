package br.com.sulimann.Projeto.dto;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idPost;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant date;
    
    private String title;
    private String body;
    private AuthorDTO author;

    public PostDTO(){
    }

    public PostDTO(String idPost, Instant date, String title, String body, AuthorDTO author) {
        this.idPost = idPost;
        this.date = date;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setId(String idPost) {
        this.idPost = idPost;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    
}
