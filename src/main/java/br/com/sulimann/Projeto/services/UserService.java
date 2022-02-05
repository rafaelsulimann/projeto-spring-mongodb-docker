package br.com.sulimann.Projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sulimann.Projeto.dto.UserDTO;
import br.com.sulimann.Projeto.model.UserModel;
import br.com.sulimann.Projeto.repository.UserRepository;
import br.com.sulimann.Projeto.services.exceptions.NotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<UserModel> findAll(){
        return repository.findAll();
    }

    public UserModel findById(String id){
        Optional<UserModel> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("Objeto não encontrado"));
    }

    public UserModel insert(UserModel obj){
        return repository.insert(obj);
    }

    public void delete(String id){
        findById(id);
        repository.deleteById(id);
    }

    public UserModel update(String id, UserModel obj){
        UserModel entity = findById(obj.getId());
        updateData(entity, obj);
        return repository.save(entity);
    }

    public void updateData(UserModel entity, UserModel obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
    }

    public UserModel fromDTO (UserDTO objDTO){
        return new UserModel(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }
}
