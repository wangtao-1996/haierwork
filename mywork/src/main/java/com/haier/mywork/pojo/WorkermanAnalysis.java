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
@TableName("workman_analysis_bl")
public class WorkermanAnalysis {

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String industry;
    private String area;
    private String workman;
    private Integer complaintCount;
    private Integer replyCount;
    private Integer noReplyCount;
    private Float noReplyRate;

}
