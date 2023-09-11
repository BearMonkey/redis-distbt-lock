package org.monkey.distbtlock.feign.clients;

import org.monkey.distbtlock.feign.pojo.Source;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("sourceClient")
public interface SourceClient {
    @PostMapping
    public String addNewSource(Source source);

    @GetMapping
    public List<Source> findAll();
}
