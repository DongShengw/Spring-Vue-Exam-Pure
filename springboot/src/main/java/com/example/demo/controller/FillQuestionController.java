package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.ExamManage;
import com.example.demo.entity.FillQuestion;
import com.example.demo.entity.JudgeQuestion;
import com.example.demo.mapper.ExamManageMapper;
import com.example.demo.mapper.FillQuestionMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/fillQuestion")
public class FillQuestionController {
    @Resource
    FillQuestionMapper fillQuestionMapper;
    //新增
    @PostMapping
    public Result<?> save(@RequestBody FillQuestion fillQuestion){
        fillQuestionMapper.insert(fillQuestion);
        return Result.success();
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody FillQuestion fillQuestion){
        fillQuestionMapper.updateById(fillQuestion);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        fillQuestionMapper.deleteById(id);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String search){
        LambdaQueryWrapper<FillQuestion> wrapper =  Wrappers.<FillQuestion>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(FillQuestion::getQuestion,search);
        }
        Page<FillQuestion> userPage = fillQuestionMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }
    @GetMapping("/new")
    public Result<?> findById(){
        LambdaQueryWrapper<FillQuestion> wrapper =  Wrappers.<FillQuestion>lambdaQuery();
        wrapper.orderByDesc(FillQuestion::getQuestionId);
        Page<FillQuestion> userPage = fillQuestionMapper.selectPage(new Page<>(1,1),wrapper);
        return Result.success(userPage);
    }
}
