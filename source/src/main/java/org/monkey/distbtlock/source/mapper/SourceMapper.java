package org.monkey.distbtlock.source.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.monkey.distbtlock.source.pojo.Source;

import java.util.List;

@Mapper
public interface SourceMapper {
    void insert(Source source);

    List<Source> selectAll();
}
