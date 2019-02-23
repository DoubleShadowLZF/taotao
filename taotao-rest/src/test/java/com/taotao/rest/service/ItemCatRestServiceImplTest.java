package com.taotao.rest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemCatRestServiceImplTest {

	@Autowired
	private ItemCatRestService itemCatRestService;

	Logger logger = LoggerFactory.getLogger(ItemCatRestServiceImplTest.class);
	@Test
	public void getItemCatList() {
//		List itemCatList = itemCatRestService.getItemCatList();
//		logger.debug("itemCatList:{}",itemCatList);
	}

}