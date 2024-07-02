package com.baking.bakingapi.service;

import com.baking.bakingapi.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-03
 */
public interface BannerService extends IService<Banner> {

    List<Banner> listForIndex();

    List<Banner> listForAdmin();

    void deleteById(Long id);
}
