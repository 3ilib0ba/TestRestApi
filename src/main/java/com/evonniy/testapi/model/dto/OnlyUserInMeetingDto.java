package com.evonniy.testapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlyUserInMeetingDto {
    @NotNull
    private UserDto user;

    @NotBlank
    private String fullname;

    @Min(1)
    private int age;

    @NotBlank
    private String pcr;
}
