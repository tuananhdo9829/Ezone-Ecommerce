package com.tuananhdo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private Long price;
    private String imageUrl;
    private String description;
    private  MultipartFile file;
}
