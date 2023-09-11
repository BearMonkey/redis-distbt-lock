package org.monkey.distbtlock.netelement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.monkey.distbtlock.netelement.pojo.NetElmt;

import java.util.List;

@Mapper
public interface NetElmtMapper {
    void insert(NetElmt netElmt);

    List<NetElmt> selectAll();
}
