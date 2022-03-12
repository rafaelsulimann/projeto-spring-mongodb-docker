package br.com.sulimann.Projeto.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.sulimann.Projeto.dto.AuthorDTO;

@Document (collection = "post")
public class PostModel implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    private String idPost;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant date;
    
    private String title;
    private String body;

    private AuthorDTO author;
    private List<CommentModel> comments = new ArrayList<>();

    public PostModel(){
    }

    public PostModel(String idPost, Instant date, String title, String body, AuthorDTO author) {
        this.idPost = idPost;
        this.date = date;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
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

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public void addComment(CommentModel comment) {
        comments.add(comment);
    }

    public void removeComment(CommentModel comment) {
        comments.remove(comment);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPost == null) ? 0 : idPost.hashCode());
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
        PostModel other = (PostModel) obj;
        if (idPost == null) {
            if (other.idPost != null)
                return false;
        } else if (!idPost.equals(other.idPost))
            return false;
        return true;
    }

    
}
