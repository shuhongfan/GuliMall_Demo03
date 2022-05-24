package com.shf.gulimall.order.dao;

import com.shf.gulimall.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 * 
 * @author shuhongfan
 * @email shuhongfan@foxmail.com
 * @date 2022-05-24 09:49:49
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {
	
}
