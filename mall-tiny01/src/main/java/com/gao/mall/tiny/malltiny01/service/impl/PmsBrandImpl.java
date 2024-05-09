package com.gao.mall.tiny.malltiny01.service.impl;

import com.gao.mall.tiny.malltiny01.config.RedisConfig;
import com.gao.mall.tiny.malltiny01.mbg.mapper.PmsBrandMapper;
import com.gao.mall.tiny.malltiny01.mbg.model.PmsBrand;
import com.gao.mall.tiny.malltiny01.mbg.model.PmsBrandExample;
import com.gao.mall.tiny.malltiny01.service.PmsBrandService;
import com.gao.mall.tiny.malltiny01.service.RedisService;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PmsBrandImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Autowired
    private RedisService redisService;

    //@Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brandlist", unless = "#result==null")
    @Override
    public List<PmsBrand> listAllBrand() {
        //return pmsBrandMapper.selectByExample(new PmsBrandExample());
        String KEY = RedisConfig.REDIS_KEY_DATABASE + ":pmsBrand:ALL";
        Long SIZE = redisService.lSize(KEY);
        List<Object> cacheList = redisService.lRange(KEY, 0, SIZE);
        if (cacheList.isEmpty()) {
            List<PmsBrand> newBrands = pmsBrandMapper.selectByExample(new PmsBrandExample());
            redisService.lPushAll(KEY, newBrands.toArray());
            System.out.println("Cache is empty");
            return newBrands;
        } else {
            System.out.println("Already exist in cache");
            System.out.println(cacheList.getClass().getName());
            //return cacheList.stream().map(o -> (PmsBrand) o).toList();
            return convertToObjectList(cacheList);
        }

    }

    public static List<PmsBrand> convertToObjectList(List<Object> cachedObjects) {
        List<PmsBrand> pmsBrands = new ArrayList<>();

        for (Object cachedObject : cachedObjects) {
            if (cachedObject instanceof PmsBrand) { // 验证对象是否为PmsBrand
                PmsBrand pmsBrand = (PmsBrand) cachedObject;
                pmsBrands.add(pmsBrand);
            } else {
                throw new IllegalStateException("Unexpected data type in cache. Expected PmsBrand, but got " + cachedObject.getClass().getName());
            }
        }
        return pmsBrands;
    }

    @Override
    public int createBrand(PmsBrand pmsBrand) {
        return pmsBrandMapper.insertSelective(pmsBrand);
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key="'pms:brand:'+#id")
    @Override
    public int updateBrand(Long id, PmsBrand pmsBrand) {
        pmsBrand.setId(id);
        return pmsBrandMapper.updateByPrimaryKeySelective(pmsBrand);
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key="'pms:brand:'+#id")
    @Override
    public int deleteBrand(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    //@Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key="'pms:brand:'+#id", unless = "#result=null")
    @Override
    public PmsBrand getBrand(Long id ) {
        //return pmsBrandMapper.selectByPrimaryKey(id);
        PmsBrand cacheBrand = (PmsBrand) redisService.get(RedisConfig.REDIS_KEY_DATABASE + ":pmsBrand:" + id);
        if(cacheBrand != null){
            //System.out.println("Cache is using");
            return cacheBrand;
        } else {
            PmsBrand newBrand = pmsBrandMapper.selectByPrimaryKey(id);
            redisService.set(RedisConfig.REDIS_KEY_DATABASE + ":pmsBrand:" + id, newBrand);
            return newBrand;
        }
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        RowBounds rowBounds = new RowBounds((pageNum-1) * pageSize, pageSize);

        return pmsBrandMapper.selectByExampleWithRowbounds(new PmsBrandExample(), rowBounds);
    }
}
