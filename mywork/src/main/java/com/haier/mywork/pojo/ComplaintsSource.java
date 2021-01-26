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
@TableName("complaints_source")
public class ComplaintsSource {

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String industry;
    private String area;
    private Integer installCount;
    private Integer installOverOnedayCount;
    private Integer callCount;
    private Integer callOverThreedayCount;
    private Integer infoCount;
    private Integer complaintCount;

}
