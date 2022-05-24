package com.shf.gulimall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shf.gulimall.product.entity.BrandEntity;
import com.shf.gulimall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {

	@Autowired
	private BrandService brandService;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testSave(){
		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setName("华为");
		brandService.save(brandEntity);
	}

	@Test
	public void testUpdate(){
		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setBrandId(1L);
		brandEntity.setDescript("华为");
		brandService.updateById(brandEntity);
	}

	@Test
	public void testGetOne(){
		BrandEntity brand = brandService.getOne(
				new QueryWrapper<BrandEntity>()
						.eq("brand_id", 1));
		System.out.println(brand);
	}

}
