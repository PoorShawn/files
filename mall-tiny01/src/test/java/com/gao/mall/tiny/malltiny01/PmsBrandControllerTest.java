package com.gao.mall.tiny.malltiny01;

import com.gao.mall.tiny.malltiny01.common.CommonPage;
import com.gao.mall.tiny.malltiny01.common.CommonResult;
import com.gao.mall.tiny.malltiny01.common.ResponseCode;
import com.gao.mall.tiny.malltiny01.controller.PmsBrandController;
import com.gao.mall.tiny.malltiny01.mbg.model.PmsBrand;
import com.gao.mall.tiny.malltiny01.service.PmsBrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class PmsBrandControllerTest {
    @Autowired
    private PmsBrandController brandController;

    @Autowired
    private PmsBrandService pmsBrandService;

    private MockMvc mockMvc;
    @Autowired
    private PmsBrandController pmsBrandController;

    @Test
    public void listAll() {
        // 调用待测试方法
        CommonResult<List<PmsBrand>> result = brandController.getBrandList();

        // 验证响应状态
        System.out.println(result.getCode());
        System.out.println(result.getMsg());
        System.out.println(result.getData());
    }

    @Test
    public void create() {
        PmsBrand brand = new PmsBrand();
        brand.setName("TestBrand");
        brand.setLogo("TestLogo");
        brand.setBrandStory("This is a TestBrandStory");

        CommonResult result = brandController.createBrand(brand);
        System.out.println(result.getCode());
        System.out.println(result.getMsg());
        System.out.println(result.getData());

    }

    @Test
    public void update() {
        PmsBrand brand = new PmsBrand();
        brand.setName("A brand of test");
        brand.setBrandStory("That is a TestBrandStory");
        brand.setLogo(null);

        CommonResult result = brandController.updateBrand(59L,brand);
        System.out.println(result.getCode());
        System.out.println(result.getMsg());
        System.out.println(result.getData());


    }

    @Test
    public void list() {
        // 发起GET请求
        CommonResult<List<PmsBrand>> response = brandController.listAllBrand(1, 1);

        // 验证响应状态和数据
        System.out.println(response.getCode());
        System.out.println(response.getMsg());
        System.out.println(response.getData());

    }
}
