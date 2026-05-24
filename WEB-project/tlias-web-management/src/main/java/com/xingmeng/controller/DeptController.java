package com.xingmeng.controller;

import com.xingmeng.anno.Log;
import com.xingmeng.pojo.Dept;
import com.xingmeng.pojo.Result;
import com.xingmeng.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger log= LoggerFactory.getLogger(DeptController.class);有注解@Slf4j，无需手动创建log对象

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
//        System.out.println("查询全部部门列表");
        log.info("查询全部部门列表");
        List<Dept> list = deptService.findAll();
        return Result.success(list);
    }
    @DeleteMapping("/{id}")
    //白雪
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("根据id删除部门："+id);
//        return Result.success();
//    }
//    public Result delete(@RequestParam(value = "id",required = false) Integer deptId){
//        System.out.println("根据id删除部门："+deptId);
//        return Result.success();
//    }
    @Log
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}", id);
//        System.out.println("根据id删除部门："+id);
        deptService.deleteById(id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门：{}", dept);
//        System.out.println("添加部门："+dept);
        deptService.add(dept);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询部门：{}", id);
//        System.out.println("根据id查询部门："+id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("更新部门：{}", dept);
//        System.out.println("更新部门："+dept);
        deptService.update(dept);
        return Result.success();
    }
}
