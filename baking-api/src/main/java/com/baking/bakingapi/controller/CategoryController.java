package com.baking.bakingapi.controller;


import com.baking.bakingapi.entity.Category;
import com.baking.bakingapi.response.JsonResult;
import com.baking.bakingapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/v1/categories")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping("/type")
    public JsonResult typeList(){
        List <Category>list = categoryService.typeList();
        return JsonResult.ok(list);
    }

    @GetMapping("/{type}/sub")
    public JsonResult listByType(@PathVariable Integer type){
        List <Category>list = categoryService.listByType(type);
        return JsonResult.ok(list);
    }
}

