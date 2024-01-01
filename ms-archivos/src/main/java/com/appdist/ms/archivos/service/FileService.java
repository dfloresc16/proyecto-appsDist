package com.appdist.ms.archivos.service;

import com.appdist.ms.archivos.model.entity.FileDist;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface FileService {

    void saveFile(MultipartFile file) throws IOException;

    Optional<FileDist> byId(Long id);

    void delete(Long id);
}
