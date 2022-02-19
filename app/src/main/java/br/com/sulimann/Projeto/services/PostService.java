package br.com.sulimann.Projeto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public PostModel findById(String id){
        Optional<PostModel> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("Objeto nao encontrado"));
    }

    public List<PostModel> findByTitle(String text){
        return repository.searchTitle(text);
    }

    public List<PostModel> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repository.fullSearch(text, minDate, maxDate);
    }
}
