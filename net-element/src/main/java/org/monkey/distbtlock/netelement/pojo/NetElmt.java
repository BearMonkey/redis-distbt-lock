package org.monkey.distbtlock.netelement.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class NetElmt {
    private Integer id;
    private String neDn;
    private String neName;
    private Date optTime;
}
