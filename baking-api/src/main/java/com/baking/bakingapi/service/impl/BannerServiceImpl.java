package com.baking.bakingapi.service.impl;

import com.baking.bakingapi.entity.Banner;
import com.baking.bakingapi.mapper.BannerMapper;
import com.baking.bakingapi.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-03
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    @Resource
    BannerMapper bannerMapper;
    @Override
    public List<Banner> listForIndex() {
        return bannerMapper.selectForIndex();
    }

    @Override
    public List<Banner> listForAdmin() {
        return bannerMapper.selectForAdmin();
    }

    @Override
    public void deleteById(Long id) {
        String imgUrl = bannerMapper.selectUrlById(id);
        String baseDir = System.getProperty("user.dir"); // 当前工作目录
        String dirPath = baseDir + File.separator + "uploads"; // 使用File.separator来确保跨平台兼容性
        new File(dirPath+imgUrl).delete();
        bannerMapper.deleteById(id);
    }
}
