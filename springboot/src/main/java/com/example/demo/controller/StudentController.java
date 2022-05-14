package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Login;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    StudentMapper studentMapper;
    //新增
    @PostMapping
    public Result<?> save(@RequestBody Student student){
        if(student.getStudentId() == null){
            student.setPwd("123456");
        }
        studentMapper.insert(student);
        return Result.success();
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody Student student){
        studentMapper.updateById(student);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        studentMapper.deleteById(id);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String search){
        LambdaQueryWrapper<Student> wrapper =  Wrappers.<Student>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Student::getStudentName,search);
        }
        Page<Student> userPage = studentMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }
    @GetMapping("/{id}")
    public Result<?> findPageById(@PathVariable Long id){
        LambdaQueryWrapper<Student> wrapper =  Wrappers.<Student>lambdaQuery();
        if (id!=null){
            wrapper.eq(Student::getStudentId,id);
        }
        Page<Student> userPage = studentMapper.selectPage(new Page<>(1,1),wrapper);
        return Result.success(userPage);
    }
    //登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody Login login){
        Student res = studentMapper.selectOne(Wrappers.<Student>lambdaQuery().eq(Student::getStudentId,login.getUsername()).eq(Student::getPwd,login.getPassword()));
        if(res == null){
            return Result.error("-1","学生密码错误！");
        }
        return Result.success(res);
    }
    @PostMapping("/register")
    public Result<?> register(@RequestBody Student student){
        Student res = studentMapper.selectOne(Wrappers.<Student>lambdaQuery().eq(Student::getStudentName,student.getStudentName()));
        if(res != null){
            return Result.error("-1","学生名重复！");
        }
//        System.out.println(student);
        student.setMajor("计算机科学与技术");
        student.setInstitute("计算机学院");
        student.setGrade("2019");
//        System.out.println(student);
        studentMapper.insert(student);

         res = studentMapper.selectOne(Wrappers.<Student>lambdaQuery().eq(Student::getStudentName,student.getStudentName()));
        return Result.success(res);
    }
}
