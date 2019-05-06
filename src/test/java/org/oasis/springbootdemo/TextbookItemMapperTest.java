package org.oasis.springbootdemo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oasis.springbootdemo.dao.TextbookItemMapper;
import org.oasis.springbootdemo.model.generated.TextbookItem;
import org.oasis.springbootdemo.model.generated.TextbookItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TextbookItemMapperTest {

    @Autowired
    TextbookItemMapper textbookItemMapper;

    @Ignore
    @Test
    public void test() {
        long start = System.currentTimeMillis();
        for (long i = 1; i <= 32163; i++) {
            TextbookItem textbookItem = textbookItemMapper.selectByPrimaryKey(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("单条查询， 耗时：" + (end - start));
    }

    @Test
    @Ignore
    public void test2() {
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 32163; i += 1000) {
            long offset = i;
            long pageSize = 1000;
            List<Long> idList = new ArrayList<>();
            for (int j = 0; j < pageSize; j++) {
                idList.add(j + offset);
            }
            TextbookItemExample example = new TextbookItemExample();
            example.createCriteria().andIdIn(idList);
            List<TextbookItem> textbookItems = textbookItemMapper.selectByExample(example);
        }
        long end = System.currentTimeMillis();
        System.out.println("批量查询， 耗时：" + (end - start));
    }

}
