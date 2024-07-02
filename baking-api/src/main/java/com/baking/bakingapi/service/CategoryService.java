package com.baking.bakingapi.service;

import com.baking.bakingapi.entity.Category;
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
public interface CategoryService extends IService<Category> {

    List<Category> typeList();

    List<Category> listByType(Integer type);
}
