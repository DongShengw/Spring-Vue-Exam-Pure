package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("score")
@Data
public class Score {

    private Integer examCode;

    private Integer studentId;

    private String studentName;

    private String subject;

    private Integer ptScore;

    private Integer etScore;

    private Integer score;
    @TableId(type = IdType.AUTO)
    private Integer scoreId;

    private String answerDate;
}
