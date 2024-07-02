package com.baking.bakingapi.service;

import com.baking.bakingapi.entity.Content;
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
public interface ContentService extends IService<Content> {

    void addNew(Content content);

    List<Content> listForManagement(Content content);

    void deleteById(Long id);

    Content getDetailForUpdate(Long id);

    List<Content> listForIndex(Content content);

    Content getDetailById(Long id);

    List<Content> listForOtherByUserId(Long id);

    List<Content> listForHot();

    List<Content> listByType(Integer type);

    List<Content> searchByWd(String wd);

    List<Content> listForAdmin();
}
