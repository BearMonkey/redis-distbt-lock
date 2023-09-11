package org.monkey.distbtlock.source.controller;

import org.monkey.distbtlock.source.service.SourceService;
import org.monkey.distbtlock.source.pojo.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/source")
public class SourcesCotroller {

    @Autowired
    private SourceService sourcesService;

    @PostMapping
    public String addNewSource(Source source) {
        sourcesService.addNewSource(source);
        return "add new source success";
    }

    @GetMapping
    public List<Source> findAll() {
        return sourcesService.findAllSource();
    }

}
