package com.appsdist.ms.administracion.repository;

import com.appsdist.ms.administracion.model.entity.UserDist;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDist,Long> {
}
