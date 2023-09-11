package org.monkey.distbtlock.netelement.vo;

import lombok.Data;

@Data
public class NewNetworkElement {
    private String neDn;
    private String neName;
    private String scName;
    private String scIp;
    private String actType;
    private String actUser;
    private String actPwd;
}
