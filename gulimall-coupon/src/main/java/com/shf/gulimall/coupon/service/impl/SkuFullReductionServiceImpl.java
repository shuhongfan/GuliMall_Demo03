package com.shf.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shf.common.to.MemberPrice;
import com.shf.common.to.SkuReductionTo;
import com.shf.common.utils.PageUtils;
import com.shf.common.utils.Query;
import com.shf.gulimall.coupon.dao.SkuFullReductionDao;
import com.shf.gulimall.coupon.entity.MemberPriceEntity;
import com.shf.gulimall.coupon.entity.SkuFullReductionEntity;
import com.shf.gulimall.coupon.entity.SkuLadderEntity;
import com.shf.gulimall.coupon.service.MemberPriceService;
import com.shf.gulimall.coupon.service.SkuFullReductionService;
import com.shf.gulimall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {

    @Autowired
    private SkuLadderService skuLadderService;

    @Autowired
    private MemberPriceService memberPriceService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkuReductionTo skuReductionTo) {
//        1.保存满减打折，会员价  sms_sku_ladder
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(skuReductionTo.getSkuId());
        skuLadderEntity.setFullCount(skuReductionTo.getFullCount());
        skuLadderEntity.setDiscount(skuReductionTo.getDiscount());
        skuLadderEntity.setAddOther(skuReductionTo.getCountStatus());
        if (skuReductionTo.getFullCount() > 0) {
            skuLadderService.save(skuLadderEntity);
        }

//        sms_sku_full_reduction
        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuReductionTo, skuFullReductionEntity);
        if (skuFullReductionEntity.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
            this.save(skuFullReductionEntity);
        }

//        sms_member_price
        List<MemberPrice> memberPrice = skuReductionTo.getMemberPrice();
        List<MemberPriceEntity> collect = memberPrice.stream().map(item -> {
                    MemberPriceEntity priceEntity = new MemberPriceEntity();
                    priceEntity.setSkuId(skuReductionTo.getSkuId());
                    priceEntity.setMemberLevelId(item.getId());
                    priceEntity.setMemberLevelName(item.getName());
                    priceEntity.setMemberPrice(item.getPrice());
                    priceEntity.setAddOther(1);
                    return priceEntity;
                }).filter(item -> item.getMemberPrice().compareTo(new BigDecimal("0")) == 1)
                .collect(Collectors.toList());
        memberPriceService.saveBatch(collect);
    }

}