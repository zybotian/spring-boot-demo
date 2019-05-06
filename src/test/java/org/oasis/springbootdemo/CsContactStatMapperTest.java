package org.oasis.springbootdemo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oasis.springbootdemo.dao.CsContactStatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author tianbo
 * @date 2019-05-05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CsContactStatMapperTest {

    @Autowired
    private CsContactStatMapper csContactStatMapper;

    @Test
    @Ignore
    public void test() throws Exception {
        List<Long> idList = new ArrayList<>();
        idList.add(111L);
        idList.add(112L);
        idList.add(113L);

        Set<Long> primaryKeys = csContactStatMapper.selectPrimaryKeys(idList, 1111L);
        System.err.println("result: " + primaryKeys);
    }
}
