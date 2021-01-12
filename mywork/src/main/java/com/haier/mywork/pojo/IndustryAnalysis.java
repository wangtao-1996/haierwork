package com.haier.mywork.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("industry_analysis_bl")
public class IndustryAnalysis {

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String industry;
    private Integer complaintCount;
    private Integer replyCount;
    private Integer noReplyCount;
    private Float noReplyRate;

}
