package com.example.umc_practice.web.dto;

import com.example.umc_practice.validation.annotation.ExistStore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class CreateMission {
        @ExistStore
        Long storeId;
        @NotNull
        Integer reward;
        @NotNull
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
        LocalDateTime deadline;
        @NotBlank
        String missionSpec;
    }
}
