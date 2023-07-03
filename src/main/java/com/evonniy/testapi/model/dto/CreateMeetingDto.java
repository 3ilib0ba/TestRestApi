package com.evonniy.testapi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMeetingDto {
    @NotBlank
    private String name;

    @Min(0)
    private long price;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;
}
