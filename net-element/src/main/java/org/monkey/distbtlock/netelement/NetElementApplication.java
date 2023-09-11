package org.monkey.distbtlock.netelement;

import org.monkey.distbtlock.feign.clients.AccountClient;
import org.monkey.distbtlock.feign.clients.SourceClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("org.monkey.distbtlock.netelement.mapper")
@EnableEurekaClient
@EnableFeignClients(clients = {AccountClient.class, SourceClient.class})
public class NetElementApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetElementApplication.class, args);
    }

}
