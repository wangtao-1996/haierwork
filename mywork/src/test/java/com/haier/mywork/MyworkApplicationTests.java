package com.haier.mywork;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haier.mywork.mapper.*;
import com.haier.mywork.pojo.UserBL;
import com.haier.mywork.service.UserBLService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class MyworkApplicationTests {

	@Autowired
	private UserBLService userBLService;

	/*对用户并联分析的数据进行清空*/
	@Test
	void delete(){
		userBLService.deletedAll();
	}

	/*对用户并联信息进行分析*/
	@Test
	void BLWorkAnalysis(){
		userBLService.runAll();
	}

	@Autowired
	private RestTemplate restTemplate;

	@Test
	void sendMessageIHaier() throws InterruptedException {
		//String content = "董方旭牛逼";
		//String content = "{\"content\":\"董方旭牛逼\"}";
		/*HashMap<String, String> content = new HashMap<>();
		content.put("content","董方旭牛逼");
		String jsonString = JSON.toJSONString(content);
		System.out.println(jsonString);*/

		String url = "https://i.haier.net/gateway/robot/webhook/send?yzjtype=0&yzjtoken=492ae0fade0d4b6985e043806458c270";
		//restTemplate.postForEntity(url)

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);


		//String url = "https://oapi.dingtalk.com/robot/send?access_token=65eff73abfd26a3e5e11dc87c2c8bcbf359f15b65cd1d3bcb60443307fba675a1";
		while (true){
			Thread.sleep(100);
			int i = 1;
			String content = "{ \"content\": \"董方旭牛逼"+ i +"\"}";
			HttpEntity<String> request = new HttpEntity<>(content, headers);

			ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);

			String body = postForEntity.getBody();

			System.out.println(body);
		}


	}


	@Autowired
	private UserBLMapper userBLMapper;

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


	@Autowired
	private RedisTemplate redisTemplate;


	@Test
	public void testRedis(){
		redisTemplate.opsForValue().set("name","汪涛");
	}

	@Test
	public void testRedis2(){
		String name = (String) redisTemplate.opsForValue().get("name");
		System.out.println(name);
	}

}
