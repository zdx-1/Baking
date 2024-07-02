package com.baking.bakingapi.service.impl;

import com.baking.bakingapi.entity.Content;
import com.baking.bakingapi.mapper.ContentMapper;
import com.baking.bakingapi.service.ContentService;
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
 * @since 2024-07-02
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {
    @Resource
    ContentMapper contentMapper;
    @Override
    public void addNew(Content content) {
        Content content1 = new Content();
        BeanUtils.copyProperties(content,content1);
        content.setCreateTime(new Date());
        contentMapper.insert(content1);
    }

    @Override
    public List<Content> listForManagement(Content content) {
        return contentMapper.selectForManagement(content);
    }

    @Override
    public void deleteById(Long id) {
        contentMapper.deleteById(id);
    }

    @Override
    public Content getDetailForUpdate(Long id) {
        return contentMapper.selectForUpdateById(id);
    }

    @Override
    public List<Content> listForIndex(Content content) {
        return contentMapper.selectForIndex(content);
    }

    @Override
    public Content getDetailById(Long id) {
        //每通过id查一次该文章的内容详情，说明该文章被浏览了一次
        //直接在此处让浏览量 +1 即可
        contentMapper.updateViewCountById(id);
        return contentMapper.selectForDetailById(id);
    }

    @Override
    public List<Content> listForOtherByUserId(Long id) {
        return contentMapper.selectOtherByUserId(id);
    }

    @Override
    public List<Content> listForHot() {
        return contentMapper.selectHot();
    }

    @Override
    public List<Content> listByType(Integer type) {
        return contentMapper.selectByType(type);
    }

    @Override
    public List<Content> searchByWd(String wd) {
        return contentMapper.selectByWd(wd);
    }

    @Override
    public List<Content> listForAdmin() {
        return contentMapper.selectForAdmin();
    }
}
