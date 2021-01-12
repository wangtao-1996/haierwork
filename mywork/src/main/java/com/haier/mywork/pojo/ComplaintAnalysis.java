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
@TableName("complaint_analysis_bl")
public class ComplaintAnalysis {

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String complaintType;
    private Integer count;
    private Integer replyCount;
    private Integer noReplyCount;
    private Float typeRate;
    private Float noReplyRate;

}
