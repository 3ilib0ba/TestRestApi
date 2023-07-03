package com.evonniy.testapi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingInfoDto {
    @NotNull
    @Min(1)
    private Long id;

    @NotBlank
    private String organizator;

    @NotBlank
    private String name;

    @Min(0)
    private long price;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date dateOf;

    private List<OnlyUserInMeetingDto> participants;

}
