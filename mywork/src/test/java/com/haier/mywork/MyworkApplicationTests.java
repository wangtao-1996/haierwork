package com.haier.mywork;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haier.mywork.mapper.*;
import com.haier.mywork.pojo.UserBL;
import com.haier.mywork.service.UserBLService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class MyworkApplicationTests {

	@Autowired
	private UserBLMapper userBLMapper;



	@Test
	void BLWorkAnalysis(){
		userBLService.runAll();
	}



	@Test
	void delete(){
		userBLService.deletedAll();
	}


	@Test
	void contextLoads() {
		List<UserBL> userBLS = userBLMapper.selectList(null);
		System.out.println("-----------------------");
		System.out.println("共有" + userBLS.size() + "个");
		System.out.println("-----------------------");
		for (UserBL userBL : userBLS) {
			System.out.println(userBL);
		}
	}

	@Autowired
	private UserBLService userBLService;

	@Test
	void test1(){
		userBLService.analysisWorker();
	}

	@Test
	void test2(){
		//1.查询出所有抱怨类型，存到set集合
		UserBL userBLBy = new UserBL();
		userBLBy.setDegree("TS2");

		QueryWrapper<UserBL> wrapper = new QueryWrapper<>(userBLBy);
		List<UserBL> userBLS = userBLMapper.selectList(wrapper);
		System.out.println("共计 ：" + userBLS.size() + "个");
		for (UserBL userBL : userBLS) {
			System.out.println(userBL);
		}
	}

	@Test
	void test3(){
		UserBL userBLBy = new UserBL();
		userBLBy.setDegree("TS1");

		QueryWrapper<UserBL> wrapper = new QueryWrapper<>(userBLBy);
		List<UserBL> userBLS = userBLMapper.selectList(wrapper);

		//set集合里面存储产业.工贸,例如：冰箱.上海
		Set<String> areaInSet = new HashSet<>();
		for (UserBL userBL : userBLS) {
			areaInSet.add(userBL.getIndustry() + "." +userBL.getArea());
		}
		for (String areaIn : areaInSet) {
			System.out.println(areaIn);
		}
	}

}
