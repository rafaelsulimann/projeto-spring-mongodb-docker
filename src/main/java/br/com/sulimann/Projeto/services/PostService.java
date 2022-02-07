package br.com.sulimann.Projeto.services;

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
    
    public PostModel findById(String id){
        Optional<PostModel> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("Objeto nao encontrado"));
    }
}