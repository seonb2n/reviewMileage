package com.example.reviewmileage.infrastructures.mybatis.model;

import com.example.reviewmileage.common.util.TokenGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserMapperDTO {

    private Long user_id;
    private String user_name;
    private int user_mileage_point;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
    private String user_token;

    @Builder
    public UserMapperDTO(Long user_id, String user_name) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_mileage_point = 0;
        this.created_at = LocalDateTime.now();
        this.update_at = LocalDateTime.now();
        this.user_token = TokenGenerator.randomCharacterWithPrefix("user_");
    }
}
