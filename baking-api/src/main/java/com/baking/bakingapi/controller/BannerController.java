package com.baking.bakingapi.controller;


import com.baking.bakingapi.entity.Banner;
import com.baking.bakingapi.response.JsonResult;
import com.baking.bakingapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/v1/banners")
public class BannerController {
    @Resource
    BannerService bannerService;
    @GetMapping("/index")
    public JsonResult listForIndex(){
        List<Banner> list = bannerService.listForIndex();
        return JsonResult.ok(list);
    }
    @GetMapping("/admin")
    public JsonResult listForAdmin(){
        List<Banner> list = bannerService.listForAdmin();
        return JsonResult.ok(list);
    }
    @PostMapping("/{id}/delete")
    public JsonResult deleteById(@PathVariable Long id){
        bannerService.deleteById(id);
        return JsonResult.ok();
    }
}

