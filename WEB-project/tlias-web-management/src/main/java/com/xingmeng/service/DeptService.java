package com.xingmeng.service;

import com.xingmeng.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService{
    List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    Dept findById(Integer id);

    void update(Dept dept);
}
