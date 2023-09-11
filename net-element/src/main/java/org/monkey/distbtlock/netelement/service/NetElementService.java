package org.monkey.distbtlock.netelement.service;

import org.monkey.distbtlock.netelement.pojo.NetElmt;
import org.monkey.distbtlock.netelement.vo.NewNetworkElement;

import java.util.List;

public interface NetElementService {
    void addNewNetElement(NewNetworkElement element);

    List<NetElmt> findAllNetElement();
}
