package br.com.sulimann.Projeto.dto;

import java.io.Serializable;
import java.time.Instant;

import br.com.sulimann.Projeto.model.PostModel;

public class PostDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Instant date;
    private String title;
    private String body;

    public PostDTO(){
    }

    public PostDTO(PostModel obj){
        date = obj.getDate();
        title = obj.getTitle();
        body = obj.getBody();
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

    
}
