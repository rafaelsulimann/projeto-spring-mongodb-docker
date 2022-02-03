package br.com.sulimann.Projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sulimann.Projeto.model.UserModel;
import br.com.sulimann.Projeto.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<UserModel> findAll(){
        return repository.findAll();
    }
}
