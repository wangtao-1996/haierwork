package com.haier.mywork.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("manager_analysis_bl")
public class ManagerAnalysis {

    @TableId(type = IdType.ID_WORKER)
    private Long id;

    @TableField("area")
    private String area;

    @TableField("manager")
    private String manager;

    @TableField("complaint_count")
    private Integer complaintCount;

    @TableField("reply_count")
    private Integer replyCount;

    @TableField("no_reply_count")
    private Integer noReplyCount;

    @TableField("no_reply_rate")
    private Float noReplyRate;

}
