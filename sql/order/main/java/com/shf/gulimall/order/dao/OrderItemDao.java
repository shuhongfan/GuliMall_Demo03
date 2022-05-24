package com.shf.gulimall.order.dao;

import com.shf.gulimall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author shuhongfan
 * @email shuhongfan@foxmail.com
 * @date 2022-05-24 09:49:49
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
