package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.JudgeQuestion;
import com.example.demo.entity.MultiQuestion;
import com.example.demo.mapper.JudgeQuestionMapper;
import com.example.demo.mapper.MultiQuestionMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/multiQuestion")
public class MultiQuestionController {
    @Resource
    MultiQuestionMapper multiQuestionMapper;
    //新增
    @PostMapping
    public Result<?> save(@RequestBody MultiQuestion multiQuestion){
        multiQuestionMapper.insert(multiQuestion);
        return Result.success();
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody MultiQuestion multiQuestion){
        multiQuestionMapper.updateById(multiQuestion);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        multiQuestionMapper.deleteById(id);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String search){
        LambdaQueryWrapper<MultiQuestion> wrapper =  Wrappers.<MultiQuestion>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(MultiQuestion::getQuestion,search);
        }
        Page<MultiQuestion> userPage = multiQuestionMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }
    @GetMapping("/new")
    public Result<?> findById(){
        LambdaQueryWrapper<MultiQuestion> wrapper =  Wrappers.<MultiQuestion>lambdaQuery();
        wrapper.orderByDesc(MultiQuestion::getQuestionId);
        Page<MultiQuestion> userPage = multiQuestionMapper.selectPage(new Page<>(1,1),wrapper);
        return Result.success(userPage);
    }
}
