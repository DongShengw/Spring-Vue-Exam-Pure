package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paperManage")
public class PaperManageController {

    @Resource
    FillQuestionMapper fillQuestionMapper;
    @Resource
    JudgeQuestionMapper judgeQuestionMapper;
    @Resource
    MultiQuestionMapper multiQuestionMapper;
    @Resource
    PaperManageMapper paperManageMapper;
    //新增
    @PostMapping
    public Result<?> save(@RequestBody PaperManage paperManage){
        paperManageMapper.insert(paperManage);
        return Result.success();
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody PaperManage paperManage){
        paperManageMapper.updateById(paperManage);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        paperManageMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping
    public Result<?> findById(@RequestParam (defaultValue = "") String paperId){

        List<MultiQuestion> multiQuestions = multiQuestionMapper.selectList(Wrappers.<MultiQuestion>lambdaQuery()
                .last(" where question_Id in (select question_Id from paper_manage where question_Type = 1 and paper_Id = '"+paperId+"') "));
        List<FillQuestion> fillQuestions = fillQuestionMapper.selectList(Wrappers.<FillQuestion>lambdaQuery()
                .last(" where question_Id in (select question_Id from paper_manage where question_Type = 2 and paper_Id = '"+paperId+"') "));
        List<JudgeQuestion> judgeQuestions = judgeQuestionMapper.selectList(Wrappers.<JudgeQuestion>lambdaQuery()
                .last(" where question_Id in (select question_Id from paper_manage where question_Type = 3 and paper_Id = '"+paperId+"') "));

        Map<Integer, List<?>> map = new HashMap<>();
        map.put(1,multiQuestions);
        map.put(2,fillQuestions);
        map.put(3,judgeQuestions);
        return Result.success(map);
    }
}
