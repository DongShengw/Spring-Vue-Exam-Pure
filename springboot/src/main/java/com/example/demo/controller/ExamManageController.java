package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.ExamManage;
import com.example.demo.mapper.ExamManageMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/exam")
public class ExamManageController {
    @Resource
    ExamManageMapper examManageMapper;
    //新增
    @PostMapping
    public Result<?> save(@RequestBody ExamManage examManage){
        examManageMapper.insert(examManage);
        return Result.success();
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody ExamManage examManage){
        examManageMapper.updateById(examManage);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        examManageMapper.deleteById(id);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String search,
                              @RequestParam (defaultValue = "") String teacherId){
        LambdaQueryWrapper<ExamManage> wrapper =  Wrappers.<ExamManage>lambdaQuery();
        if (StrUtil.isNotBlank(search)||StrUtil.isNotBlank(teacherId)){
            wrapper.like(ExamManage::getSource,search).eq(ExamManage::getTeacherId,teacherId);
        }
        Page<ExamManage> userPage = examManageMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);

    }
    @GetMapping("/{id}")
    public Result<?> findByCode(@PathVariable Long id){
        LambdaQueryWrapper<ExamManage> wrapper =  Wrappers.<ExamManage>lambdaQuery();
        if (id!=null){
            wrapper.eq(ExamManage::getExamCode,id);
        }
        ExamManage userPage = examManageMapper.selectOne(wrapper);
        return Result.success(userPage);
    }
    @GetMapping("/student")
    public Result<?> findPageByStudent(@RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam (defaultValue = "10") Integer pageSize,
                                       @RequestParam (defaultValue = "") String search,
                                       @RequestParam (defaultValue = "") String major){
        LambdaQueryWrapper<ExamManage> wrapper =  Wrappers.<ExamManage>lambdaQuery();
        if (StrUtil.isNotBlank(major)){
            wrapper.eq(ExamManage::getMajor,major).like(ExamManage::getSource,search);
        }
        Page<ExamManage> userPage = examManageMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);

    }
}
