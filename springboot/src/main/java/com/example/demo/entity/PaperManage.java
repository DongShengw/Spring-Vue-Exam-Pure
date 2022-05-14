package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("paper_manage")
@NoArgsConstructor
@AllArgsConstructor
public class PaperManage {
    @TableId(type = IdType.AUTO)
    private Integer paperId;

    private Integer questionType;

    private Integer questionId;

}
