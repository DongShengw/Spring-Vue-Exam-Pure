package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Login;
import com.example.demo.entity.Student;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.StudentMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminMapper adminMapper;
    //新增
    @PostMapping
    public Result<?> save(@RequestBody Admin admin){
        if(admin.getAdminId() == null){
            admin.setPwd("123456");
        }
        adminMapper.insert(admin);
        return Result.success();
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody Admin admin){
        adminMapper.updateById(admin);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        adminMapper.deleteById(id);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String search){
        LambdaQueryWrapper<Admin> wrapper =  Wrappers.<Admin>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Admin::getAdminName,search);
        }
        Page<Admin> userPage = adminMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }
    //登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody Login login){
        Admin res = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getAdminId,login.getUsername()).eq(Admin::getPwd,login.getPassword()));
        if(res == null){
            return Result.error("-1","用户名密码错误！");
        }
        return Result.success(res);
    }
//    @PostMapping("/register")
//    public Result<?> register(@RequestBody Student user){
//        User res = studentMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
//        if(res != null){
//            return Result.error("-1","用户名重复！");
//        }
//        if(user.getPassword()==null){
//            user.setPassword("123456");
//        }
//        studentMapper.insert(user);
//        return Result.success();
//    }
}
