package org.monkey.distbtlock.source.service;

import org.monkey.distbtlock.source.pojo.Source;

import java.util.List;

public interface SourceService {
    void addNewSource(Source source);

    List<Source> findAllSource();
}
