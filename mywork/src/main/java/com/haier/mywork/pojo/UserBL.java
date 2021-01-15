package com.haier.mywork.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_bl")
public class UserBL {

    private String infoId;
    private String orderId;
    private String industry;
    private String area;
    private String complaintCate;
    private String complaintType;
    private String degree;
    private Date complaintDate;
    private Date dealDate;
    private Date upDate;
    private String dealLevel;
    private String upReason;
    private String workmanOne;
    private String workmanTwo;
}
