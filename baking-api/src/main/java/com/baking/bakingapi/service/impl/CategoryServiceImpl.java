package com.baking.bakingapi.service.impl;

import com.baking.bakingapi.entity.Category;
import com.baking.bakingapi.mapper.CategoryMapper;
import com.baking.bakingapi.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;
    @Override
    public List<Category> typeList() {
        return categoryMapper.selectForType();
    }

    @Override
    public List<Category> listByType(Integer type) {
        return categoryMapper.selectByType(type);
    }
}
