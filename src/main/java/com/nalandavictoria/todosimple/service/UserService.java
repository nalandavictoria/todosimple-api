package com.nalandavictoria.todosimple.service;

import com.nalandavictoria.todosimple.model.UserModel;
import com.nalandavictoria.todosimple.repository.UserRepository;
import com.nalandavictoria.todosimple.rest.dto.UserDTO;
import com.nalandavictoria.todosimple.service.exceptions.DataBindingViolationException;
import com.nalandavictoria.todosimple.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO findById (Long id){
        UserModel userModel = userRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado."));
        return new UserDTO(userModel);
    }

    public UserModel findEntityById (Long id){
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado."));
        return userModel;
    }


    @Transactional
    public UserDTO create (UserModel userModel){
        userModel.setId(null);
        UserModel created = userRepository.save(userModel);
        return new UserDTO(created);
    }

    @Transactional
    public UserDTO update (UserModel userModel){
        UserModel user = userRepository.findById(userModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado."));
        user.setPassword(userModel.getPassword());
        UserModel newUser = userRepository.save(user);
        return new UserDTO(newUser);
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
