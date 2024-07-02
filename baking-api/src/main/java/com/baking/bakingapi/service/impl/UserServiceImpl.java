package com.baking.bakingapi.service.impl;

import com.baking.bakingapi.entity.User;
import com.baking.bakingapi.exception.ServiceException;
import com.baking.bakingapi.mapper.UserMapper;
import com.baking.bakingapi.response.StatusCode;
import com.baking.bakingapi.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public User login(User user) {
        User user1=userMapper.selectByUsername(user.getUsername());
        if (user1 == null){
            throw new ServiceException(StatusCode.USERNAME_ERROR);
        }
        if (!user1.getPassword().equals(user.getPassword())){
            throw new ServiceException(StatusCode.PASSWORD_ERROR);
        }
        return user1;
    }

    @Override
    public void reg(User user) {
        User user1 = userMapper.selectByUsername(user.getUsername());
        if (user1 != null){
            throw new ServiceException(StatusCode.USERNAME_ALREADY_EXISTS);
        }
        User user2 = new User();
        BeanUtils.copyProperties(user, user2);
        user2.setCreateTime(new Date());
        user2.setIsAdmin(0);
        userMapper.insert(user2);
    }

    @Override
    public void update(User user) {
        User user1 = new User();
        BeanUtils.copyProperties(user, user1);
        userMapper.update(user1);
    }

    @Override
    public List<User> listForAdmin() {
        return userMapper.selectForAdmin();
    }

    @Override
    public void deleteById(Long id) {
        //先拿到当前用户的头像路径
        String url = userMapper.selectUrlById(id);
        //在磁盘中删除该路径对应的头像图片
        new File("d:/files"+url).delete();
        //再删除对应的用户数据
        userMapper.deleteById(id);
    }
}
