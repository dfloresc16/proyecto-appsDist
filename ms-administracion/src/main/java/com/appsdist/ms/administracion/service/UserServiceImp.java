package com.appsdist.ms.administracion.service;

import com.appsdist.ms.administracion.model.entity.UserDist;
import com.appsdist.ms.administracion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserDist> list() {
        return (List<UserDist>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDist> getbyId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public UserDist save(UserDist userDist) {
        return userRepository.save(userDist);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
