package br.com.sulimann.Projeto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.sulimann.Projeto.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    
}
