package com.shf.gulimall.product.controller;

import com.shf.common.utils.PageUtils;
import com.shf.common.utils.R;
import com.shf.gulimall.product.entity.ProductAttrValueEntity;
import com.shf.gulimall.product.service.AttrService;
import com.shf.gulimall.product.service.ProductAttrValueService;
import com.shf.gulimall.product.vo.AttrRespVo;
import com.shf.gulimall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品属性
 *
 * @author shuhongfan
 * @email shuhongfan@foxmail.com
 * @date 2022-05-24 09:17:08
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrListForSpu(@PathVariable("spuId") Long spuId) {
        List<ProductAttrValueEntity> entity = productAttrValueService.baseAttrListForSpu(spuId);
        return R.ok().put("data", entity);
    }


    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(
            @RequestParam Map<String, Object> params,
            @PathVariable("catelogId") Long catelogId,
            @PathVariable("attrType") String type
    ) {
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId, type);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId) {
//        AttrEntity attr = attrService.getById(attrId);
        AttrRespVo attrVo = attrService.getAttrInfo(attrId);

        return R.ok().put("attr", attrVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attr:save")
//    public R save(@RequestBody AttrEntity attr){
    public R save(@RequestBody AttrVo attr) {
        attrService.saveAttr(attr);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrVo attr) {
//        attrService.updateById(attr);

        attrService.updateAttr(attr);
        return R.ok();
    }

    @RequestMapping("/update/{spuId}")
    //@RequiresPermissions("product:attr:update")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities) {
        productAttrValueService.updateSpuAttr(spuId, entities);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
