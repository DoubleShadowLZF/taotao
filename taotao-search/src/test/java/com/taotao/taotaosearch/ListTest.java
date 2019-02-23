package com.taotao.taotaosearch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author Double
 */
public class ListTest {
	@Test
	public void test01(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		ArrayList<Object> objects = new ArrayList<>();
		objects.addAll(list);
		for (int i = 0; i < objects.size(); i++) {
			if(i == 2){
				objects.remove(i);
			}
			System.out.println(objects.get(i));
		}
	}
}
