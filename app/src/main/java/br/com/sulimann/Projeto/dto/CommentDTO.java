package br.com.sulimann.Projeto.dto;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String idComment;
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant date;

    private AuthorDTO author;

    public CommentDTO(){
    }

    public CommentDTO(String idComment, String text, Instant date, AuthorDTO author) {
        this.idComment = idComment;
        this.text = text;
        this.date = date;
        this.author = author;
    }

    public String getIdComment() {
        return idComment;
    }

    public void setId(String idComment) {
        this.idComment = idComment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }    
}
