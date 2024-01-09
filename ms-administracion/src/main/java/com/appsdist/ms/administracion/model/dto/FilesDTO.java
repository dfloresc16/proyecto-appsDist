package com.appsdist.ms.administracion.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FilesDTO {

    private String fileName;

    private String type;

    private String filePath;

}
