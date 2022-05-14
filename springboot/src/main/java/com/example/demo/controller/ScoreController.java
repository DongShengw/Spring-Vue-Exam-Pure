package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.FillQuestion;
import com.example.demo.entity.Score;
import com.example.demo.mapper.FillQuestionMapper;
import com.example.demo.mapper.ScoreMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Resource
    ScoreMapper scoreMapper;
    //新增
    @PostMapping
    public Result<?> save(@RequestBody Score score){
        scoreMapper.insert(score);
        return Result.success();
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody Score score){
        scoreMapper.updateById(score);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        scoreMapper.deleteById(id);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String search){
        LambdaQueryWrapper<Score> wrapper =  Wrappers.<Score>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Score::getExamCode,search);
        }
        Page<Score> userPage = scoreMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }
    @GetMapping("/{id}")
    public Result<?> find(@PathVariable Long id,
                          @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam (defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Score> wrapper =  Wrappers.<Score>lambdaQuery();
        if (id != null){
            wrapper.eq(Score::getStudentId,id);
        }
        Page<Score> userPage = scoreMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
//        scoreMapper.selectById(id);
        return Result.success(userPage);
    }
    @GetMapping("/exam")
    public Result<?> findByExam(@RequestParam Long id,
                          @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam (defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Score> wrapper =  Wrappers.<Score>lambdaQuery();
        if (id != null){
            wrapper.eq(Score::getExamCode,id);
        }
        Page<Score> userPage = scoreMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
//        scoreMapper.selectById(id);
        return Result.success(userPage);
    }
}
