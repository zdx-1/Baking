package com.baking.bakingapi.mapper;

import com.baking.bakingapi.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select id,password,nickname,is_admin,img_url,username from user where username=#{username}")
    User selectByUsername(@Param("username") String username);

    void update(@RequestBody User user1);

    @Select("SELECT id,username,nickname,is_admin,create_time,img_url FROM user ORDER BY create_time DESC")
    List<User> selectForAdmin();

    @Select("SELECT img_url from user WHERE id=#{id}")
    String selectUrlById(Long id);

}
