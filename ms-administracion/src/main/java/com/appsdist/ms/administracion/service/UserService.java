package com.appsdist.ms.administracion.service;

import com.appsdist.ms.administracion.model.entity.UserDist;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDist> list();

    Optional<UserDist> byId(Long id);

    UserDist save(UserDist userDist);

    void delete(Long id);
}
