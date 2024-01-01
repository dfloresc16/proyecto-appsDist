package com.appdist.ms.archivos.repository;

import com.appdist.ms.archivos.model.entity.UserDist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDist,Long> {
}
