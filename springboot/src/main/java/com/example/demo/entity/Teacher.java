package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("teacher")
@Data
public class Teacher {
    @TableId(type = IdType.AUTO)
    private Integer teacherId;
    private String teacherName;
    private String institute;
    private String sex;
    private String tel;
    private String email;
    private String pwd;
    private String cardId;
    private String type;
    private String role;

}
