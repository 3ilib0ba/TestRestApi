package com.evonniy.testapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    @NotBlank
    private UserDto organizator;

    @NotBlank
    private String document;

    @NotNull
    private Boolean is_signed;
}
