package org.oasis.springbootdemo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.oasis.springbootdemo.dao.MessageMapper;
import org.oasis.springbootdemo.model.generated.Message;
import org.oasis.springbootdemo.model.generated.MessageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageMapperTest {

    @Autowired
    MessageMapper messageMapper;

//    @Before
//    public void setUp() throws Exception {
//        for (int i = 0; i < 1000; i++) {
//            List<Message> msgs = new ArrayList<>();
//            for (int j = 0; j < 1000; j++) {
//                msgs.add(generate());
//            }
//            messageMapper.insertBatch(msgs);
//        }
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        long count = messageMapper.countByExample(null);
//        System.err.println(count);
//    }

    @Test
    @Ignore
    public void testSingleSelect() throws Exception {
        long start = System.currentTimeMillis();
        for (long i = 1; i <= 1000000; i++) {
            messageMapper.selectByPrimaryKey(i);
        }
        long end = System.currentTimeMillis();

        System.err.println("test single select, time:" + (end - start));
    }

    @Test
    @Ignore
    public void testBatchSelect() throws Exception {
        long start = System.currentTimeMillis();
        for (long i = 1; i <= 1000; i++) {
            List<Long> idList = new ArrayList<>();
            long startId = 1 + 1000 * (i - 1);
            long endId = 1000 * i;
            for (long index = startId; index <= endId; index++) {
                idList.add(index);
            }
            MessageExample example = new MessageExample();
            example.createCriteria().andIdIn(idList);
            messageMapper.selectByExample(example);
        }
        long end = System.currentTimeMillis();

        System.err.println("test batch select, time:" + (end - start));
    }

    private Message generate() {
        Message message = new Message();
        message.setType(NumberUtils.toInt(RandomStringUtils.randomNumeric(1)) % 5);
        message.setStatus(1);
        message.setDepartment("dept_" + RandomStringUtils.randomAlphanumeric(6));
        message.setTitle("title_" + RandomStringUtils.randomAlphanumeric(64));
        message.setContent("content_" + RandomStringUtils.randomAlphanumeric(150));
        message.setShowStartTime(System.currentTimeMillis());
        message.setShowEndTime(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
        message.setSentTime(System.currentTimeMillis());
        message.setCreateBy("admin");
        message.setCreateTime(System.currentTimeMillis());
        message.setUpdateTime(System.currentTimeMillis());
        return message;
    }

}
