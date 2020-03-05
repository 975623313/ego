package com.an.ego.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderTest {

	public static void main(String[] args) {
		/*
		 * 加载spring容器，发布服务
		 */
		ClassPathXmlApplicationContext content = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml",
				"spring/applicationContext-service.xml",
				"spring/applicationContext-tx.xml",
				"spring/applicationContext-dubbo.xml");
		content.start();
		//阻塞线程
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		content.stop();
	
		
	}
	
	
}
