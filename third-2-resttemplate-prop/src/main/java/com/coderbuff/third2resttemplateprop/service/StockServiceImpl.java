package com.coderbuff.third2resttemplateprop.service;

import com.coderbuff.third2resttemplateprop.config.JuheConfig;
import com.coderbuff.third2resttemplateprop.entity.Person;
import com.coderbuff.third2resttemplateprop.entity.School;
import com.coderbuff.third2resttemplateprop.entity.JuheStockResponse;
import com.coderbuff.third2resttemplateprop.mapper.PersonMapper;
import com.coderbuff.third2resttemplateprop.mapper.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yulinfeng
 * @date 2019/12/26
 */
@Service
public class StockServiceImpl {

    /**
     * 配置类
     */
    @Autowired
    private JuheConfig juheConfig;


    @Resource
    private PersonMapper personMapper;

    @Resource
    private SchoolMapper schoolMapper;

    @Resource
    private StockServiceImpl stockService;

    /**
     * API URL
     */
    //请求示例：http://web.juhe.cn:8080/finance/stock/hs?gid=sh601009&key=您申请的APPKEY
    private static final String URL = "http://web.juhe.cn:8080/finance/stock/hs?gid=%s&key=%s";

    /**
     * 根据股票代码获取股票
     *
     * @param code 股票代码
     * @return 股票信息
     */
    public JuheStockResponse getStockByCode(String code) {
        String url = String.format(URL, code, juheConfig.getAppKey());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(parseContentType());
        JuheStockResponse response = restTemplate.getForObject(url, JuheStockResponse.class);
//        JuheStockResultDapanData juheStockResultDapanData = response.getResult().get(0).getDapandata();
//        StockDTO stockDTO = new StockDTO();
//        stockDTO.setName(juheStockResultDapanData.getName());
        return response;
    }

    /**
     * 支持所有的contentType类型
     *
     * @return 类型转换
     */
    private List<HttpMessageConverter<?>> parseContentType() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        return messageConverters;
    }

    @Transactional
    public int testAddPerson() {
        Person person = new Person();
        person.setName("yk11");
        int insert = personMapper.insert(person);
        stockService.testAddSchool();
        int i = 1 / 0;
        return insert;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int testAddSchool() {
        School school = new School();
        school.setName("商城11");
        int insert = schoolMapper.insert(school);
        return insert;
    }


}
