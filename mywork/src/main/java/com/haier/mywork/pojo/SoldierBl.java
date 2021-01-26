package com.haier.mywork.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("soldier_bl")
public class SoldierBl {

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String orderId;
    private String industry;
    private String area;
    private String factory;
    private String originator;
    private String primaryReason;
    private String secondaryReason;
    private Date createTime;
    private String responsiblePerson;
    private String status;
    private Date closeTime;
}
