package com.shf.gulimall.product.dao;

import com.shf.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author shuhongfan
 * @email shuhongfan@foxmail.com
 * @date 2022-05-24 09:17:08
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
