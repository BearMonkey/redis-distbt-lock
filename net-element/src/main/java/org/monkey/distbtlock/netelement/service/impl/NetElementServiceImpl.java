package org.monkey.distbtlock.netelement.service.impl;

import com.rabbitmq.client.ShutdownSignalException;
import org.monkey.distbtlock.feign.clients.AccountClient;
import org.monkey.distbtlock.feign.clients.SourceClient;
import org.monkey.distbtlock.feign.pojo.Account;
import org.monkey.distbtlock.feign.pojo.Source;
import org.monkey.distbtlock.netelement.mapper.NetElmtMapper;
import org.monkey.distbtlock.netelement.pojo.NetElmt;
import org.monkey.distbtlock.netelement.service.NetElementService;
import org.monkey.distbtlock.netelement.vo.NewNetworkElement;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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
    @Transactional(rollbackFor = Exception.class)
    public void addNewNetElement(NewNetworkElement element) {

        try {


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
            System.out.println("交换机方式发送消息");
            rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
                @Override
                public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                    System.out.println("MyConfirmCallback:ack=" + ack);
                }
            });

            rabbitTemplate.setMandatory(true);
            /*rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
                @Override
                public void returnedMessage(ReturnedMessage returnedMessage) {
                    String str = String.format("消息发送失败-消息回退，应答码：{}，原因：{}，交换机：{}，路由键：{}",
                            returnedMessage.getReplyCode(),
                            returnedMessage.getReplyText(),
                            returnedMessage.getExchange(),
                            returnedMessage.getRoutingKey());
                    System.out.println(str);
                }
            });*/

            rabbitTemplate.convertAndSend("boot_topic_exchange", "#.message.#", element);

        /*NetElmt netElmt = new NetElmt();
        netElmt.setNeDn(element.getNeDn());
        netElmt.setNeName(element.getNeName());
        netElmtMapper.insert(netElmt);*/

        } catch (ShutdownSignalException e) {
            System.out.println("交换机故障或者不存在。");
            e.printStackTrace();
        } catch (AmqpConnectException e) {
            System.out.println("服务器连接失败。");
            e.printStackTrace();
        }
    }

    @Override
    public List<NetElmt> findAllNetElement() {
        return netElmtMapper.selectAll();
    }
}
