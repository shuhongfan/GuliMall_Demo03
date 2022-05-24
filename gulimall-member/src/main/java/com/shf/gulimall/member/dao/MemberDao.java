package com.shf.gulimall.member.dao;

import com.shf.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author shuhongfan
 * @email shuhongfan@foxmail.com
 * @date 2022-05-24 09:39:20
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
