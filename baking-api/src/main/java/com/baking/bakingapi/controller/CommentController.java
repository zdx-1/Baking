package com.baking.bakingapi.controller;


import com.baking.bakingapi.entity.Comment;
import com.baking.bakingapi.response.JsonResult;
import com.baking.bakingapi.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-03
 */
@RestController
@RequestMapping("/v1/comments")
public class CommentController {
    @Resource
    CommentService commentService;
    @PostMapping("/add-new")
    public JsonResult addNew(Comment comment){
        commentService.addNew(comment);
        return JsonResult.ok();
    }
    @GetMapping("/{id}")
    public JsonResult listByContentId(@PathVariable Long id){
        List<Comment> list = commentService.listByContentId(id);
        return JsonResult.ok(list);
    }
}

