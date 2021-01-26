package com.haier.mywork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haier.mywork.mapper.*;
import com.haier.mywork.pojo.*;
import com.haier.mywork.service.UserBLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserBLServiceImpl implements UserBLService {

    @Autowired
    private AreaAnalysisMapper areaAnalysisMapper;
    @Autowired
    private ComplaintAnalysisMapper complaintAnalysisMapper;
    @Autowired
    private IndustryAnalysisMapper industryAnalysisMapper;
    @Autowired
    private ManagerAnalysisMapper managerAnalysisMapper;
    @Autowired
    private WorkermanAnalysisMapper workermanAnalysisMapper;
    @Autowired
    private UserBLMapper userBLMapper;



    @Override
    public void analysisArea() {
        //1.查询出所有工贸，set集合
        List<UserBL> userBLS = userBLMapper.selectList(null);
        if (userBLS == null ||userBLS.size() == 0){
            return;
        }
        Set<String> areaSet = new HashSet<>();
        for (UserBL userBL : userBLS) {
            String area = userBL.getArea();
            area.replace("小微","");
            areaSet.add(area);
        }

        //2.遍历工贸集合，获取每个工贸
        for (String area : areaSet) {
            //3.1创建AreaAnalysis对象，根据工贸查询并联量、答复量、未答复量、未答复率
            AreaAnalysis areaAnalysis = new AreaAnalysis(null,area,0,0,0,0f);
            for (UserBL userBL : userBLS) {
                if (area.equals(userBL.getArea())){
                    //设置并联量
                    areaAnalysis.setComplaintCount(areaAnalysis.getComplaintCount() + 1);

                    //设置答复量
                    if (userBL.getDealDate() != null){
                        areaAnalysis.setReplyCount(areaAnalysis.getReplyCount() + 1);
                    }else {
                        //设置未答复量
                        areaAnalysis.setNoReplyCount(areaAnalysis.getNoReplyCount() + 1);
                    }
                }
            }
            //设置未答复率
            areaAnalysis.setNoReplyRate((float)areaAnalysis.getNoReplyCount()/ areaAnalysis.getComplaintCount());

            //3.2将AreaAnalysis对象插入数据表
            areaAnalysisMapper.insert(areaAnalysis);
        }
    }


    @Override
    public void analysisIndustry() {
        //1.查询出所有产业，set集合
        List<UserBL> userBLS = userBLMapper.selectList(null);
        if (userBLS == null ||userBLS.size() == 0){
            return;
        }
        Set<String> industrySet = new HashSet<>();
        for (UserBL userBL : userBLS) {
            String industry = userBL.getIndustry();
            industrySet.add(industry);
        }

        //2.遍历产业集合，获取每个产业
        for (String industry : industrySet) {
            //3.1创建AreaAnalysis对象，根据工贸查询并联量、答复量、未答复量、未答复率
            IndustryAnalysis industryAnalysis = new IndustryAnalysis(null, industry, 0, 0, 0, 0f);
            for (UserBL userBL : userBLS) {
                if (industry.equals(userBL.getIndustry())){
                    //设置并联量
                    industryAnalysis.setComplaintCount(industryAnalysis.getComplaintCount() + 1);

                    //设置答复量
                    if (userBL.getDealDate() != null){
                        industryAnalysis.setReplyCount(industryAnalysis.getReplyCount() + 1);
                    }else {
                        //设置未答复量
                        industryAnalysis.setNoReplyCount(industryAnalysis.getNoReplyCount() + 1);
                    }
                }
            }
            industryAnalysis.setNoReplyRate((float)industryAnalysis.getNoReplyCount()/ industryAnalysis.getComplaintCount());

            //3.2将AreaAnalysis对象插入数据表
            industryAnalysisMapper.insert(industryAnalysis);
        }
    }


    @Override
    public void analysisComplaint() {
        //1.查询出所有抱怨类型，存到set集合
        List<UserBL> userBLS = userBLMapper.selectList(null);
        if (userBLS == null ||userBLS.size() == 0){
            return;
        }
        Set<String> complaintSet = new HashSet<>();
        for (UserBL userBL : userBLS) {
            String complaintType = userBL.getComplaintType();
            complaintSet.add(complaintType);
        }

        //2.遍历抱怨类型集合，获取每个抱怨类型
        for (String complaintType : complaintSet) {
            //3.1创建ComplaintAnalysis对象，根据抱怨类型查询抱怨量、答复量、未答复量、未答复率、抱怨类型占比
            ComplaintAnalysis complaintAnalysis = new ComplaintAnalysis(null, complaintType, 0, 0, 0, 0f,0f);
            for (UserBL userBL : userBLS) {
                if (complaintType.equals(userBL.getComplaintType())){
                    //设置并联量
                    complaintAnalysis.setCount(complaintAnalysis.getCount() + 1);

                    //设置答复量
                    if (userBL.getDealDate() != null){
                        complaintAnalysis.setReplyCount(complaintAnalysis.getReplyCount() + 1);
                    }else {
                        //设置未答复量
                        complaintAnalysis.setNoReplyCount(complaintAnalysis.getNoReplyCount() + 1);
                    }
                }
            }
            //设置抱怨类型占比
            complaintAnalysis.setTypeRate((float)complaintAnalysis.getCount()/userBLS.size());
            //设置未答复率
            complaintAnalysis.setNoReplyRate((float)complaintAnalysis.getNoReplyCount()/complaintAnalysis.getCount());

            //3.2将AreaAnalysis对象插入数据表
            complaintAnalysisMapper.insert(complaintAnalysis);
        }
    }


    @Override
    public void analysisManager() {
        //1.查询出TS2抱怨类型，存到set集合
        UserBL userBLBy = new UserBL();
        userBLBy.setDegree("TS2");

        QueryWrapper<UserBL> wrapper = new QueryWrapper<>(userBLBy);
        List<UserBL> userBLS = userBLMapper.selectList(wrapper);
        if (userBLS == null ||userBLS.size() == 0){
            return;
        }
        Set<String> areaSet = new HashSet<>();
        for (UserBL userBL : userBLS) {
            areaSet.add(userBL.getArea());
        }

        //2.遍历工贸集合，获取每个工贸
        for (String area : areaSet) {
            //3.1创建ManagerAnalysis对象，根据工贸查询副总、抱怨量、答复量、未答复量、未答复率
            ManagerAnalysis managerAnalysis = new ManagerAnalysis(null, area, null, 0, 0, 0, 0f);
           for (UserBL userBL : userBLS) {
                if (area.equals(userBL.getArea())){
                    //设置副总
                    if (managerAnalysis.getManager() == null){
                        managerAnalysis.setManager(userBL.getWorkmanOne());
                    }

                    //设置抱怨量
                    managerAnalysis.setComplaintCount(managerAnalysis.getComplaintCount() + 1);

                    //设置答复量
                    if (userBL.getDealDate() != null){
                        managerAnalysis.setReplyCount(managerAnalysis.getReplyCount() + 1);
                    }else {
                        //设置未答复量
                        managerAnalysis.setNoReplyCount(managerAnalysis.getNoReplyCount() + 1);
                    }
                }
            }

            //设置未答复率
            managerAnalysis.setNoReplyRate((float)managerAnalysis.getNoReplyCount()/managerAnalysis.getComplaintCount());

            //3.2将managerAnalysis对象插入数据表
            managerAnalysisMapper.insert(managerAnalysis);
        }
    }


    @Override
    public void analysisWorker() {
        //1.查询出TS1抱怨类型，存到set集合
        UserBL userBLBy = new UserBL();
        userBLBy.setDegree("TS1");

        QueryWrapper<UserBL> wrapper = new QueryWrapper<>(userBLBy);
        List<UserBL> userBLS = userBLMapper.selectList(wrapper);
        if (userBLS == null ||userBLS.size() == 0){
            return;
        }

        //set集合里面存储产业.工贸,例如：冰箱.上海
        Set<String> industryAreaSet = new HashSet<>();
        for (UserBL userBL : userBLS) {
            industryAreaSet.add(userBL.getIndustry() + "." +userBL.getArea());
        }

        //2.遍历工贸的每个产业集合，获取每个工贸,每个产业
        for (String industryArea : industryAreaSet) {

            String industry = industryArea.split("\\.")[0];
            String area = industryArea.split("\\.")[1];

            //3.1创建WorkermanAnalysis对象，根据产业、工贸查询售后经理、抱怨量、答复量、未答复量、未答复率
            WorkermanAnalysis workermanAnalysis = new WorkermanAnalysis(null,industry,area,null,0,0,0,0f);
            for (UserBL userBL : userBLS) {
                if (industry.equals(userBL.getIndustry()) && area.equals(userBL.getArea())){
                    //设置售后经理
                    if (workermanAnalysis.getWorkman() == null){
                        workermanAnalysis.setWorkman(userBL.getWorkmanOne());
                    }

                    //设置抱怨量
                    workermanAnalysis.setComplaintCount(workermanAnalysis.getComplaintCount() + 1);

                    //设置答复量
                    if (userBL.getDealDate() != null){
                        workermanAnalysis.setReplyCount(workermanAnalysis.getReplyCount() + 1);
                    }else {
                        //设置未答复量
                        workermanAnalysis.setNoReplyCount(workermanAnalysis.getNoReplyCount() + 1);
                    }
                }
            }

            //设置未答复率
            workermanAnalysis.setNoReplyRate((float)workermanAnalysis.getNoReplyCount()/workermanAnalysis.getComplaintCount());

            //3.2将managerAnalysis对象插入数据表
            workermanAnalysisMapper.insert(workermanAnalysis);
        }
    }



    @Override
    public void deletedAll() {
        areaAnalysisMapper.delete(null);
        complaintAnalysisMapper.delete(null);
        industryAnalysisMapper.delete(null);
        managerAnalysisMapper.delete(null);
        workermanAnalysisMapper.delete(null);
    }

    @Override
    public void runAll() {
        this.analysisArea();
        this.analysisComplaint();
        this.analysisIndustry();
        this.analysisManager();
        this.analysisWorker();
    }


}
