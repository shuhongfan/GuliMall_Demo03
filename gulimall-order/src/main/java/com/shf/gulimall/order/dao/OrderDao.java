package com.shf.gulimall.order.dao;

import com.shf.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author shuhongfan
 * @email shuhongfan@foxmail.com
 * @date 2022-05-24 09:49:49
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
