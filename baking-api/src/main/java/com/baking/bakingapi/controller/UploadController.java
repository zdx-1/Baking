package com.baking.bakingapi.controller;

import com.baking.bakingapi.response.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-02
 */
@RestController
@RequestMapping("/v1")
public class UploadController {
    //1.(MultipartFile file) MultipartFile是用来接收上传文件的对象
    //file必须与前端<el-upload name="file">的值一样！
    @PostMapping("/upload")
    public JsonResult upload(MultipartFile file) throws IOException {
        //2.得到文件名
        String fileName = file.getOriginalFilename();
        //3.得到文件后缀名 .jpg
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        //4.得到唯一文件名 UUID.randomUUID()得到一个唯一的标识符(字符串)
        fileName = UUID.randomUUID()+suffix; //29687c01-c21b-44b8-a160-f9a3a2287970.jpg
        //5.指定一个文件夹路径用来存图片
//        String dirPath = "d:/files";

        String baseDir = System.getProperty("user.dir"); // 当前工作目录
        String dirPath = baseDir + File.separator + "uploads"; // 使用File.separator来确保跨平台兼容性

        //6.自定义日期对象 以/开头以/结尾,注意日期MM是大写的
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        //7.将当前日期格式化成上面的日期格式
        String datePath = sdf.format(new Date());
        //8.创建一个封装了正确路径的文件对象
        File dirFile = new File(dirPath+datePath);
        //9.判断是否存在,如果不存在则创建
        if(!dirFile.exists()){
            dirFile.mkdirs();//创建多层文件夹
        }
        //10.准备一个完整的路径 文件夹路径 + 日期路径 + 图片文件名
        String filePath = dirPath+datePath+fileName;
        //11.把文件保存到指定的路径 异常抛出--看看文件系统中是否有该图片
        file.transferTo(new File(filePath));
        //12.把日期路径 + 图片文件名响应给前端 /2024/3/27/xxx.jpg
        return JsonResult.ok(datePath+fileName);
    }
}
