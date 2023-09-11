package org.monkey.distbtlock.feign.pojo;

import lombok.Data;

@Data
public class Source {
    private Integer id;
    private String scName;
    private String scIp;
    private Integer actId;
}
