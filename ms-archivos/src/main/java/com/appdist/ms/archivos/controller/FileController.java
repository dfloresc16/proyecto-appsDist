package com.appdist.ms.archivos.controller;

import com.appdist.ms.archivos.model.dto.GenericResponseDTO;
import com.appdist.ms.archivos.model.entity.FileDist;
import com.appdist.ms.archivos.service.FileService;
import com.appdist.ms.archivos.service.FileServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/v1/api/files")
public class FileController extends CommonController{

    private static final Logger logInfo = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileServiceImp fileServiceImp;

    @RequestMapping(path = "/uploadFile",method = RequestMethod.POST)
    public ResponseEntity<GenericResponseDTO<?>> cargarArchivo(@RequestParam("archivo") MultipartFile archivo) {
        try {
            fileServiceImp.saveFile(archivo);
            return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_SUCCESS,null,
                    null,"servicio ejecutado exitosamente",null));
        }catch (Exception e){
            log.warn(e.getMessage());
            return new ResponseEntity<>(new GenericResponseDTO<>(ERROR,HTTP_APP_FAILURE,null,
                    e.getMessage(),null,null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/download/{id}")
    public ResponseEntity<GenericResponseDTO<?>> descargarArchivo(@PathVariable Long id) throws IOException {
        try {
            Optional<FileDist> file1 = fileServiceImp.byId(id);
            if(file1.isPresent()){
                return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_SUCCESS,null,
                        null,"servicio ejecutado exitosamente",file1.get().getFilePath()));
            }
            return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_SUCCESS,null,
                    null,"No se encontro el archivo solicitado",null));
        }catch (Exception e){
            log.warn(e.getMessage());
            return new ResponseEntity<>(new GenericResponseDTO<>(ERROR,HTTP_APP_FAILURE,null,
                    e.getMessage(),null,null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping("/listar")
    public ResponseEntity<List<Archivo>> listarArchivos() {
        List<Archivo> archivos = archivoService.listarArchivos();
        return ResponseEntity.ok(archivos);
    }*/

}
