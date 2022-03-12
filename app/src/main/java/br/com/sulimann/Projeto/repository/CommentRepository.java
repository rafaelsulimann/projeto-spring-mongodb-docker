package br.com.sulimann.Projeto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.sulimann.Projeto.model.CommentModel;

@Repository
public interface CommentRepository extends MongoRepository<CommentModel, String> {
    
}
