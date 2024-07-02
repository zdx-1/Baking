package com.baking.bakingapi.service;

import com.baking.bakingapi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-02
 */
public interface UserService extends IService<User> {
    User login(User user);

    void reg(User user);

    void update(User user);

    List<User> listForAdmin();

    void deleteById(Long id);
}
