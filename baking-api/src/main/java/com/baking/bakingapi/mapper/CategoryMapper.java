package com.baking.bakingapi.mapper;

import com.baking.bakingapi.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface CategoryMapper extends BaseMapper<Category> {
    @Select("SELECT id,name,type FROM category WHERE level=1")
    List<Category> selectForType();

    List<Category> selectByType(@Param("type") Integer type);
}
