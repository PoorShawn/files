package com.gao.mall.tiny.malltiny01.controller;

import com.gao.mall.tiny.malltiny01.common.CommonPage;
import com.gao.mall.tiny.malltiny01.common.CommonResult;
import com.gao.mall.tiny.malltiny01.mbg.model.PmsBrand;
import com.gao.mall.tiny.malltiny01.service.PmsBrandService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    //log
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @RequestMapping(value ="/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(brandService.listAllBrand());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(PmsBrand brand) {
        CommonResult commonResult;
        int count = brandService.createBrand(brand);

        if (count > 0) {
            commonResult = CommonResult.success(brand);
            LOGGER.debug("create brand {} success", brand);
        } else {
            commonResult = CommonResult.failure("create brand error");
            LOGGER.error("create brand {} error", brand);
        }
        return commonResult;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(Long id, PmsBrand brand) {
        CommonResult commonResult;
        int count = brandService.updateBrand(id, brand);

        if (count > 0) {
            commonResult = CommonResult.success(brand);
            LOGGER.debug("update brand {} success", brand);
        } else {
            commonResult = CommonResult.failure("update brand error");
            LOGGER.error("update brand {} error", brand);
        }
        return commonResult;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable Long id) {
        CommonResult commonResult;
        int count = brandService.deleteBrand(id);

        if (count > 0) {
            commonResult = CommonResult.success();
            LOGGER.debug("delete brand {} success", id);
        } else {
            commonResult = CommonResult.failure("delete brand error");
            LOGGER.error("delete brand {} error", id);
        }
        return commonResult;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> getBrand(@PathVariable Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> listAllBrand
            (@RequestParam(value = "pageNum", defaultValue = "3") Integer pageNum,
             @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(brandList);
    }
}
