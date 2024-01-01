package com.appdist.ms.archivos.service;

import com.appdist.ms.archivos.controller.FileController;
import com.appdist.ms.archivos.model.entity.FileDist;
import com.appdist.ms.archivos.repository.FileRepository;
import com.appdist.ms.archivos.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class FileServiceImp implements FileService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private FileRepository fileRepository;


    @Autowired
    private ResourceLoader resourceLoader;

    private static final Logger logInfo = LoggerFactory.getLogger(FileServiceImp.class);

    private static final String path = "G:\\proyectofinal\\proyecto-final\\ms-archivos\\src\\main\\resources\\static";


    @Override
    public void saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            String filePath = path + File.separator + fileName;
            file.transferTo(new File(filePath));
            FileDist fileDist = new FileDist();
            fileDist.setFilePath(filePath);
            fileDist.setFileName(fileName);
            fileDist.setType(file.getContentType());
            fileRepository.save(fileDist);
        }catch (Exception e){
            logInfo.warn("Service: " + e.getMessage());
        }
    }

    @Override
    public Optional<FileDist> byId(Long id) {
        return Optional.ofNullable(fileRepository.findById(id).orElseThrow(() -> new ArchivoNotFoundException(id)));
    }

    @Override
    public void delete(Long id) {

    }
    class ArchivoNotFoundException extends RuntimeException {
        public ArchivoNotFoundException(Long id) {
            super("Archivo no encontrado con ID: " + id);
        }
    }


}
