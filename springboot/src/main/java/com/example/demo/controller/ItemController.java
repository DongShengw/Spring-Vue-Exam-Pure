package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.Result;
import com.example.demo.entity.FillQuestion;
import com.example.demo.entity.JudgeQuestion;
import com.example.demo.entity.MultiQuestion;
import com.example.demo.entity.PaperManage;
import com.example.demo.mapper.FillQuestionMapper;
import com.example.demo.mapper.JudgeQuestionMapper;
import com.example.demo.mapper.MultiQuestionMapper;
import com.example.demo.mapper.PaperManageMapper;
import com.example.demo.questions.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Resource
    FillQuestionMapper fillQuestionMapper;
    @Resource
    JudgeQuestionMapper judgeQuestionMapper;
    @Resource
    MultiQuestionMapper multiQuestionMapper;
    @Resource
    PaperManageMapper paperManageMapper;

    @PostMapping
    public Result<?> Item(@RequestBody Item item){
        // 选择题
        Integer changeNumber = item.getChangeNumber();
        // 填空题
        Integer fillNumber = item.getFillNumber();
        // 判断题
        Integer judgeNumber = item.getJudgeNumber();
        //出卷id
        Integer paperId = item.getPaperId();

        // 选择题数据库获取
        List<MultiQuestion> changeNumbers = multiQuestionMapper.selectList(
                Wrappers.<MultiQuestion>lambdaQuery().eq(MultiQuestion::getSubject,item.getSubject()).last("limit "+changeNumber));
        if(changeNumbers==null){
            return Result.error("0000","选择题获取错误");
        }
        for (MultiQuestion number : changeNumbers) {
            PaperManage paperManage = new PaperManage(paperId,1,number.getQuestionId());
            int index = paperManageMapper.insert(paperManage);
            if(index==0)
                return Result.error("0000","判断题组卷错误");
        }

        // 填空题
        List<FillQuestion> fills = fillQuestionMapper.selectList(
                Wrappers.<FillQuestion>lambdaQuery().eq(FillQuestion::getSubject,item.getSubject()).last("limit "+fillNumber));
        if(fills==null)
            return Result.error("0000","填空题获取错误");
        for (FillQuestion fill : fills) {
            PaperManage paperManage = new PaperManage(paperId,2,fill.getQuestionId());
            int index = paperManageMapper.insert(paperManage);
            if(index==0)
                return Result.error("0000","判断题组卷错误");
        }
        // 判断题
        List<JudgeQuestion> judges = judgeQuestionMapper.selectList(
                Wrappers.<JudgeQuestion>lambdaQuery().eq(JudgeQuestion::getSubject,item.getSubject()).last("limit "+judgeNumber));
        if(judges==null)
            return Result.error("0000","判断题获取错误");
        for (JudgeQuestion judge : judges) {
            PaperManage paperManage = new PaperManage(paperId,3,judge.getQuestionId());
            int index = paperManageMapper.insert(paperManage);
            if(index==0)
                return Result.error("0000","判断题组卷错误");
        }
        return Result.success();
    }
}
