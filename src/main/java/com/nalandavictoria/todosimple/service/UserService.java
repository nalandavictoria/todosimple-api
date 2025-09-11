package com.nalandavictoria.todosimple.service;

import com.nalandavictoria.todosimple.model.UserModel;
import com.nalandavictoria.todosimple.repository.UserRepository;
import com.nalandavictoria.todosimple.service.exceptions.DataBindingViolationException;
import com.nalandavictoria.todosimple.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserModel findById (Long id){
        return userRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado."));
    }

    @Transactional
    public UserModel create (UserModel userModel){
        userModel.setId(null);
        return userRepository.save(userModel);
    }

    @Transactional
    public UserModel update (UserModel userModel){
        UserModel newUser = findById(userModel.getId());
        newUser.setPassword(userModel.getPassword());
        return userRepository.save(newUser);
    }

    @Transactional
    public void delete (Long id) {
        findById(id);
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBindingViolationException("Não foi possível excluir o usuário, pois há entidades relacionadas.");
        }
    }
}
