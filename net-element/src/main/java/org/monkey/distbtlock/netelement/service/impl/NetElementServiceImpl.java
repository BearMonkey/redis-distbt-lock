package org.monkey.distbtlock.netelement.service.impl;

import org.monkey.distbtlock.feign.clients.AccountClient;
import org.monkey.distbtlock.feign.clients.SourceClient;
import org.monkey.distbtlock.feign.pojo.Account;
import org.monkey.distbtlock.feign.pojo.Source;
import org.monkey.distbtlock.netelement.mapper.NetElmtMapper;
import org.monkey.distbtlock.netelement.pojo.NetElmt;
import org.monkey.distbtlock.netelement.service.NetElementService;
import org.monkey.distbtlock.netelement.vo.NewNetworkElement;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NetElementServiceImpl implements NetElementService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private SourceClient sourceClient;

    @Autowired
    private NetElmtMapper netElmtMapper;
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void addNewNetElement(NewNetworkElement element) {

        /*Account account = new Account();
        account.setActType(element.getActType());
        account.setActUser(element.getActUser());
        account.setActPwd(element.getActPwd());
        accountClient.addNewAccount(account);

        Source source = new Source();
        source.setScIp(element.getScIp());
        source.setScName(element.getScName());
        sourceClient.addNewSource(source);*/

        System.out.println("producer 发送消息");
        String queueName = "distbtlock.new.ne";
        rabbitTemplate.convertAndSend(queueName, element.toString());
        rabbitTemplate.convertAndSend("boot_topic_exchange", "#.message.#", element);

        /*NetElmt netElmt = new NetElmt();
        netElmt.setNeDn(element.getNeDn());
        netElmt.setNeName(element.getNeName());
        netElmtMapper.insert(netElmt);*/
    }

    @Override
    public List<NetElmt> findAllNetElement() {
        return netElmtMapper.selectAll();
    }
}
