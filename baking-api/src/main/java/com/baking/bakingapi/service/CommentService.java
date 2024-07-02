package com.baking.bakingapi.service;

import com.baking.bakingapi.entity.Comment;
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
public interface CommentService extends IService<Comment> {

    void addNew(Comment comment);

    List<Comment> listByContentId(Long id);
}
