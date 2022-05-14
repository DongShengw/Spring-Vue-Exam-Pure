package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Login;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.mapper.TeacherMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    TeacherMapper teacherMapper;
    //新增
    @PostMapping
    public Result<?> save(@RequestBody Teacher teacher){
        if(teacher.getPwd() == null){
            teacher.setPwd("123456");
        }
        teacherMapper.insert(teacher);
        return Result.success();
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody Teacher teacher){
        teacherMapper.updateById(teacher);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        teacherMapper.deleteById(id);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String search){
        LambdaQueryWrapper<Teacher> wrapper =  Wrappers.<Teacher>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Teacher::getTeacherName,search);
        }
        Page<Teacher> teacherPage = teacherMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(teacherPage);
    }
    @GetMapping("/{id}")
    public Result<?> findPageById(@PathVariable Long id){
        LambdaQueryWrapper<Teacher> wrapper =  Wrappers.<Teacher>lambdaQuery();
        if (id!=null){
            wrapper.eq(Teacher::getTeacherId,id);
        }
        Page<Teacher> userPage = teacherMapper.selectPage(new Page<>(1,1),wrapper);
        return Result.success(userPage);
    }
    //登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody Login login){
        Teacher res = teacherMapper.selectOne(Wrappers.<Teacher>lambdaQuery()
                .eq(Teacher::getTeacherId,login.getUsername())
                .eq(Teacher::getPwd,login.getPassword()));
        if(res == null){
            return Result.error("-1","教师名密码错误！");
        }
        return Result.success(res);
    }

}
