package com.lnsoft.service;

import com.lnsoft.test.TestFastJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 传统的调用接口方式：直接调用
 * Created By Chr on 2019/1/30/0030.
 */
@Service
public class InfoServiceTraditional {

    @Autowired
    private TestFastJson testFastJson;

    public String getInfoByTrad(){
        String info = testFastJson.getInfo();
        return info;
    }
}
