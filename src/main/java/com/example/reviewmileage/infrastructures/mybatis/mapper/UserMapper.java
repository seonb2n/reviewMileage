package com.example.reviewmileage.infrastructures.mybatis.mapper;

import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.infrastructures.mybatis.model.UserMapperDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserMapperDTO> findAll();

    User save(UserMapperDTO userMapperDTO);
}
