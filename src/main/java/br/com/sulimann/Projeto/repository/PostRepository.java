package br.com.sulimann.Projeto.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sulimann.Projeto.model.PostModel;

@Repository
public interface PostRepository extends MongoRepository<PostModel, String> {

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<PostModel> searchTitle(String text);
    
    List<PostModel> findByTitleContainingIgnoreCase(String text);
}
