package com.baking.bakingapi.mapper;

import com.baking.bakingapi.entity.Content;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
public interface ContentMapper extends BaseMapper<Content> {

    List<Content> selectForManagement(Content content);


    Content selectForUpdateById(Long id);

    List<Content> selectForIndex(Content content);

    void updateViewCountById(Long id);

    Content selectForDetailById(Long id);

    List<Content> selectOtherByUserId(Long id);

    List<Content> selectHot();

    List<Content> selectByType(Integer type);

    List<Content> selectByWd(String wd);

    List<Content> selectForAdmin();

    void updateCommentCountById(Long contentId);
}
