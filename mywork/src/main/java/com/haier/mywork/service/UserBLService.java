package com.haier.mywork.service;

public interface UserBLService {

    /*用户并联分析到工贸*/
    void analysisArea();

    /*用户并联分析到产业*/
    void analysisIndustry();

    /*用户并联分析到抱怨类型*/
    void analysisComplaint();

    /*用户并联分析到副总*/
    void analysisManager();

    /*用户并联分析到售后经理*/
    void analysisWorker();

    /*清除所有分析的数据表*/
    void deletedAll();

    /*本地数据库分析用户并联*/
    void runAll();
}
