package br.com.sulimann.Projeto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sulimann.Projeto.dto.PostDTO;
import br.com.sulimann.Projeto.model.PostModel;
import br.com.sulimann.Projeto.repository.PostRepository;
import br.com.sulimann.Projeto.services.exceptions.NotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<PostModel> findAll(){
        return repository.findAll();
    }
    
    public PostModel findById(String idPost){
        Optional<PostModel> obj = repository.findById(idPost);
        return obj.orElseThrow(() -> new NotFoundException("Post nao encontrado"));
    }

    public PostModel insert(PostModel obj){
        return repository.insert(obj);
    }

    public void delete(String idPost){
        findById(idPost);
        repository.deleteById(idPost);
    }

    public PostModel update(String idPost, PostModel obj){
        PostModel entity = findById(obj.getIdPost());
        updateData(entity, obj);
        return repository.save(entity);
    }

    public PostModel updateComment(String idPost, PostModel obj){
        PostModel entity = findById(obj.getIdPost());
        updateDataComment(entity, obj);
        return repository.save(entity);
    }

    public void updateData(PostModel entity, PostModel obj){
        entity.setDate(obj.getDate());
        entity.setTitle(obj.getTitle());
        entity.setBody(obj.getBody());
        entity.setAuthor(obj.getAuthor());
    }

    public void updateDataComment(PostModel entity, PostModel obj){
        entity.setComments(obj.getComments());
    }

    public PostModel fromDTO (PostDTO objDTO){
        return new PostModel(objDTO.getIdPost(), objDTO.getDate(), objDTO.getTitle(), objDTO.getBody(), objDTO.getAuthor());
    }

    public List<PostModel> findByTitle(String text){
        return repository.searchTitle(text);
    }

    public List<PostModel> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repository.fullSearch(text, minDate, maxDate);
    }
}
