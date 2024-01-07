package com.appsdist.ms.administracion.controller;


import com.appsdist.ms.administracion.model.dto.GenericResponseDTO;
import com.appsdist.ms.administracion.model.entity.UserDist;
import com.appsdist.ms.administracion.service.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/v1/api/user")
public class UserController extends CommonController {

    private static final Logger logInfo = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceImp userServiceImp;

    /*@RequestMapping(path = "/{id}")
    public ResponseEntity<GenericResponseDTO<?>> showUser(@PathVariable Long id){
        try {
            if(userServiceImp.byId(id).isPresent()){
                return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_SUCCESS,null,
                        null,"servicio ejecutado exitosamente",userServiceImp.byId(id)));
            }
            return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_SUCCESS,null,
                    null,"No se encontraron usuarios",null));
        }catch (Exception e){
            log.warn(e.getMessage(),e);
            return new ResponseEntity<>(new GenericResponseDTO<>(ERROR,HTTP_APP_FAILURE,null,
                    e.getMessage(),null,null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /*@RequestMapping(path = "/newUser",method = RequestMethod.POST)
    public ResponseEntity<GenericResponseDTO<UserDist>> saveUser(@RequestBody UserDist userDist){
        try {
            UserDist dist = userServiceImp.save(userDist);
            return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_SUCCESS,null,
                    null,"Successfully created user",dist));
        }catch (Exception e){
            log.warn(e.getMessage(), e);
            return new ResponseEntity<>(new GenericResponseDTO<>(ERROR, HTTP_APP_FAILURE, null,
                    e.getMessage(), null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @RequestMapping(path = "/findUser/{id}",method = RequestMethod.GET)
    public ResponseEntity<Optional<UserDist>> getUser(@PathVariable Long id){
        try {
            return new ResponseEntity<>(userServiceImp.getbyId(id),HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            log.error("UserController -> findUser: "+e.getMessage());
            return null;
        }
    }

    @RequestMapping(path = "/createUser",method = RequestMethod.POST)
    public ResponseEntity<UserDist> createUser(@RequestBody UserDist userDist){
        try{
            return new ResponseEntity<>(userServiceImp.save(userDist),HttpStatus.CREATED);
        }catch (Exception e){
            log.info("UserController -> createUSer: " + e.getMessage());
            return null;
        }
    }


    @RequestMapping(path = "/listUsers",method = RequestMethod.GET)
    public ResponseEntity<List<UserDist>> listUsers(){
        try {
            return new ResponseEntity(userServiceImp.list(),HttpStatus.OK);
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(path = "/updateUser/{id}", method = RequestMethod.PUT)
    public ResponseEntity<GenericResponseDTO<?>> updateUser(@RequestBody UserDist userDist, @PathVariable Long id){
        try {
            Optional<UserDist> user1 = userServiceImp.getbyId(id);
            if(user1.isPresent()){
                UserDist userDist2 = user1.get();
                userDist2.setFirstName(userDist.getFirstName());
                userDist2.setEmail(userDist.getEmail());
                userDist2.setPassword(userDist.getPassword());
                userDist2.setLastName(userDist.getLastName());
                userDist2.setIsActive(userDist.getIsActive());
                userDist2.setSuscription(userDist.getSuscription());
                userServiceImp.save(userDist2);
                return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_SUCCESS,null,
                        null,"Successfully updated user",null));
            }
            return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_NOT_FOUND,null,
                    null,"Not found user",null));
        }catch (Exception e){
            log.warn(e.getMessage(), e);
            return new ResponseEntity<>(new GenericResponseDTO<>(ERROR, HTTP_APP_FAILURE, null,
                    e.getMessage(), null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/deleteUser/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<GenericResponseDTO<?>> deleteUser(@PathVariable Long id){
        try {
            Optional<UserDist> user = userServiceImp.getbyId(id);
            if(user.isPresent()){
                userServiceImp.delete(id);
                return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_NOT_CONTENT,null,
                        null,"Successfully deleted user",null));
            }
            return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_NOT_FOUND,null,
                    null,"Not found user",null));
        }catch (Exception e){
            log.warn(e.getMessage(), e);
            return new ResponseEntity<>(new GenericResponseDTO<>(ERROR, HTTP_APP_FAILURE, null,
                    e.getMessage(), null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
