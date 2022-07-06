package com.example.reviewmileage.application.mybatis;

import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.infrastructures.mybatis.mapper.UserMapper;
import com.example.reviewmileage.infrastructures.mybatis.model.UserMapperDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void MyBatisTest() throws Exception {
        //given
        UserMapperDTO mybatis_user = UserMapperDTO.builder().user_id(3L).user_name("mybatis_user").build();
        userMapper.save(mybatis_user);

        //when
        List<UserMapperDTO> userList = userMapper.findAll();

        //then
        for(UserMapperDTO user : userList) {
            System.out.println(user.toString());
        }
    }
}
