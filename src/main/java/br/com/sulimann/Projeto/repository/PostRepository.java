package br.com.sulimann.Projeto.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.sulimann.Projeto.model.PostModel;

@Repository
public interface PostRepository extends MongoRepository<PostModel, String> {
    
    List<PostModel> findByTitleContainingIgnoreCase(String text);
}
