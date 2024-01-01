package com.appdist.ms.archivos.repository;

import com.appdist.ms.archivos.model.entity.FileDist;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<FileDist,Long> {
}
