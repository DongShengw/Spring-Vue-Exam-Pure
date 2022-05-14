package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.ExamManage;
import com.example.demo.entity.JudgeQuestion;
import com.example.demo.mapper.ExamManageMapper;
import com.example.demo.mapper.JudgeQuestionMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/judgeQuestion")
public class JudgeQuestionController {
    @Resource
    JudgeQuestionMapper judgeQuestionMapper;
    //新增
    @PostMapping
    public Result<?> save(@RequestBody JudgeQuestion judgeQuestion){
        judgeQuestionMapper.insert(judgeQuestion);
        return Result.success();
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody JudgeQuestion judgeQuestion){
        judgeQuestionMapper.updateById(judgeQuestion);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        judgeQuestionMapper.deleteById(id);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String search){
        LambdaQueryWrapper<JudgeQuestion> wrapper =  Wrappers.<JudgeQuestion>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(JudgeQuestion::getQuestion,search);
        }
        Page<JudgeQuestion> userPage = judgeQuestionMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }
    @GetMapping("/new")
    public Result<?> findById(){
        LambdaQueryWrapper<JudgeQuestion> wrapper =  Wrappers.<JudgeQuestion>lambdaQuery();
        wrapper.orderByDesc(JudgeQuestion::getQuestionId);
        Page<JudgeQuestion> userPage = judgeQuestionMapper.selectPage(new Page<>(1,1),wrapper);
        return Result.success(userPage);
    }
}
