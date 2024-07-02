package com.baking.bakingapi.controller;


import com.baking.bakingapi.entity.Content;
import com.baking.bakingapi.response.JsonResult;
import com.baking.bakingapi.service.ContentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-02
 */
@RestController
@RequestMapping("/v1/contents")
public class ContentController {
    @Resource
    ContentService contentService;
    @PostMapping("/add-new")
    public JsonResult addNew(Content content){
        System.out.println("content="+content);

        contentService.addNew(content);

        return JsonResult.ok();
    }
//  查询稿件管理页面展示内容
    @GetMapping("/management")
    public JsonResult listForManagement(Content content){
        List<Content> list = contentService.listForManagement(content);
        return JsonResult.ok(list);
    }
//    通过id删除稿件
    @PostMapping("/{id}/delete")
    public JsonResult deleteById(@PathVariable Long id){
        contentService.deleteById(id);
        return JsonResult.ok();
    }
//    查询指定id对应内容的详细信息
    @GetMapping("/{id}/update")
    public JsonResult getDetailForUpdate(@PathVariable Long id){
        Content content = contentService.getDetailForUpdate(id);
        return JsonResult.ok(content);
    }
//    查询首页内容列表所有数据
    @GetMapping("/index")
    public JsonResult listForIndex(Content content){
        List<Content> list = contentService.listForIndex(content);
        return JsonResult.ok(list);
    }
//    查询详情页文章数据
    @GetMapping("/{id}/detail")
    public JsonResult getDetailById(@PathVariable Long id){
        Content content = contentService.getDetailById(id);
        return JsonResult.ok(content);
    }
//    查询作者其它文章
    @GetMapping("/{id}/other")
    public JsonResult listForOtherByUserId(@PathVariable Long id){
        List<Content> list = contentService.listForOtherByUserId(id);
        return JsonResult.ok(list);
    }
//    查询热门文章
    @GetMapping("/hot")
    public JsonResult listForHot(){
        List<Content> list = contentService.listForHot();
        return JsonResult.ok(list);
    }
    @GetMapping("/{type}/type")
    public JsonResult listByType(@PathVariable Integer type){
        List<Content> list = contentService.listByType(type);
        return JsonResult.ok(list);
    }
//  搜索内容
    @GetMapping("/{wd}/search")
    public JsonResult searchByWd(@PathVariable String wd){
        List<Content> list = contentService.searchByWd(wd);
        return JsonResult.ok(list);
    }
//    内容管理页面数据列表
    @GetMapping("/admin")
    public JsonResult listForAdmin(){
        List<Content> list = contentService.listForAdmin();
        return JsonResult.ok(list);
    }
}

