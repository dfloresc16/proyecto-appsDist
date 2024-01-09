package com.appdist.ms.archivos.client;

import com.appdist.ms.archivos.model.entity.UserDist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-administracion",url = "localhost:8081/v1/api/user")
public interface UserDistClientRest {

    @GetMapping("/findUser/{id}")
    public UserDist detalle(@PathVariable Long id);

}
