package br.com.sulimann.Projeto.model;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.sulimann.Projeto.dto.AuthorDTO;

@Document(collection = "comment")
public class CommentModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String idComment;
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant date;

    private AuthorDTO author;

    public CommentModel(){
    }

    public CommentModel(String idComment, String text, Instant date, AuthorDTO author) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idComment == null) ? 0 : idComment.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CommentModel other = (CommentModel) obj;
        if (idComment == null) {
            if (other.idComment != null)
                return false;
        } else if (!idComment.equals(other.idComment))
            return false;
        return true;
    }
    
}
