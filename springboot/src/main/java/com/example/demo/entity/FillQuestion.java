package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//填空题实体类
@TableName("fill_question")
@Data
public class FillQuestion {
    @TableId(type = IdType.AUTO)
    private Integer questionId;

    private String type;

    private String subject;

    private String question;

    private String answer;

    private Integer score;

    private String level;

    private String section;

    private String analysis; //题目解析
}
