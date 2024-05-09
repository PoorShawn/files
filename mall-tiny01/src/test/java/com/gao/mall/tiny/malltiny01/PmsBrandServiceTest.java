package com.gao.mall.tiny.malltiny01;

import com.gao.mall.tiny.malltiny01.mbg.mapper.PmsBrandMapper;
import com.gao.mall.tiny.malltiny01.mbg.model.PmsBrand;
import com.gao.mall.tiny.malltiny01.service.PmsBrandService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class PmsBrandServiceTest {

    @Autowired
    private PmsBrandService brandService;
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Test
    void getBrandList() {
        List<PmsBrand> actualBrands = brandService.listAllBrand();
        System.out.println(actualBrands);
    }

    @Test
    void getBrand() {
        PmsBrand pmsBrand = brandService.getBrand(1L);
        System.out.println(pmsBrand);
    }

    @Test
    void createBrand() {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setName("TestBrand");
        pmsBrand.setLogo("TestLogo");
        pmsBrand.setBrandStory("This is a TestBrandStory");


        //assertion and judgement
        int result = brandService.createBrand(pmsBrand);
        assert (result == 1);

        PmsBrand insertBrand = pmsBrandMapper.selectByPrimaryKey(pmsBrand.getId());
        assert (insertBrand.getName().equals("TestBrand"));
        assert (insertBrand.getLogo().equals("TestLogo"));
        assert (insertBrand.getBrandStory().equals("This is a TestBrandStory"));
    }

    @Test
    void listPageBrand() {
        List<PmsBrand> brandList = brandService.listBrand(2,3);
        PageInfo<PmsBrand> brandPageInfo = new PageInfo<>(brandList);
        System.out.println(brandPageInfo.getList());
    }

    @Test
    void updateBrand() {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setId(59L);
        pmsBrand.setName("TestBrand");
        pmsBrand.setLogo("TestLogo");
        pmsBrand.setBrandStory("This is a TestBrandStory");

        int result = brandService.updateBrand(59L, pmsBrand);
        assert (result == 1);

    }
}
