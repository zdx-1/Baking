package com.baking.bakingapi.mapper;

import com.baking.bakingapi.entity.Banner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-03
 */
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

    List<Banner> selectForIndex();

    List<Banner> selectForAdmin();

    String selectUrlById(Long id);
}
