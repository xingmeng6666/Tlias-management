package com.xingmeng.controller;

import com.xingmeng.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收到上传文件：name: {}, age: {}, file: {}", name, age, file);
        //保存文件
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString()+extension;

        file.transferTo(new File("E:\\Java\\IDEA\\demo\\file\\" + newFileName));
        return Result.success();
    }
}
