package com.evonniy.testapi.model.dto;

import liquibase.pro.packaged.M;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForMeetingDto {
    @Min(1)
    private long meetingId;

    @NotBlank
    private String fullName;

    @Min(0)
    private int age;

    @NotBlank
    private String pcr;
}
