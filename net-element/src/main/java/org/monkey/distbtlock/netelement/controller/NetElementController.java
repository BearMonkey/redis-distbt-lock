package org.monkey.distbtlock.netelement.controller;

import org.monkey.distbtlock.netelement.pojo.NetElmt;
import org.monkey.distbtlock.netelement.service.NetElementService;
import org.monkey.distbtlock.netelement.vo.NewNetworkElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/netelmt")
public class NetElementController {

    @Autowired
    private NetElementService netElementService;

    @PostMapping
    public String add(NewNetworkElement netElmt) {
        netElementService.addNewNetElement(netElmt);
        return "add new net element success";
    }

    @GetMapping
    public List<NetElmt> findAllNetElement() {
        return netElementService.findAllNetElement();
    }
}
