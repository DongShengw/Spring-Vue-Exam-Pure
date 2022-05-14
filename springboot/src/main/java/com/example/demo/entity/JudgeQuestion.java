package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//判断题实体类
@TableName("judge_question")
@Data
public class JudgeQuestion {
    @TableId(type = IdType.AUTO)
    private Integer questionId;

    private String subject;

    private String type;

    private String question;

    private String answer;

    private String level;

    private String section;

    private Integer score;

    private String analysis; //题目解析
}
