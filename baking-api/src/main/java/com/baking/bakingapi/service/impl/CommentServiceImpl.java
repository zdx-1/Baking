package com.baking.bakingapi.service.impl;

import com.baking.bakingapi.entity.Comment;
import com.baking.bakingapi.mapper.CommentMapper;
import com.baking.bakingapi.mapper.ContentMapper;
import com.baking.bakingapi.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Resource
    CommentMapper commentMapper;
    @Resource
    ContentMapper contentMapper;
    @Override
    public void addNew(Comment comment) {
        Comment comment1 = new Comment();
        BeanUtils.copyProperties(comment,comment1);
        comment1.setCreateTime(new Date());
        commentMapper.insert(comment);
        //调用新增评论时,对应文章的评论量+1
        contentMapper.updateCommentCountById(comment.getContentId());
    }

    @Override
    public List<Comment> listByContentId(Long id) {
        List<Comment> list = commentMapper.selectByContentId(id);
        return list;
    }
}
