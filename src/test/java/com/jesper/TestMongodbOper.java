package com.jesper;

import com.jesper.service.MongodbOper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMongodbOper {
    @Autowired
    private MongodbOper mongodbOper;
    @Test
    public void TestgetFileType(){
        String tmp = mongodbOper.getFileType("cat.jpeg");
        Assert.assertEquals(".jpeg",tmp);
    }
}
