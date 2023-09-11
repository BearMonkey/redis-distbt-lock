package org.monkey.distbtlock.source.service.impl;

import org.monkey.distbtlock.source.mapper.SourceMapper;
import org.monkey.distbtlock.source.pojo.Source;
import org.monkey.distbtlock.source.service.SourceService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceMapper sourcesMapper;
    @Override
    public void addNewSource(Source source) {
        sourcesMapper.insert(source);
    }

    @Override
    public List<Source> findAllSource() {
        return sourcesMapper.selectAll();
    }

    @RabbitListener(queues = "distbtlock.new.ne" )
    @Transactional(rollbackFor=Exception.class)
    public void addNewSource(String msg) {
        System.out.println("recieve mq --addNewSource: msg=" + msg);
    }
    @RabbitListener(queues = "distbtlock.message.ne" )
    @Transactional(rollbackFor=Exception.class)
    public void testTopic(Object msg) {
        System.out.println("recieve mq --addNewSource: msg=" + msg.toString());
    }


}
