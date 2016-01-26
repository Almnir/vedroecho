package org.almkg.models;

import java.util.Date;

/**
 * Created by yarnykh on 26.01.2016.
 */
public class Device {
    private Long deviceId;
    private String password;
    private Date deviceDate;
    private Date updateDate;
    private String deviceMode;
    private Date timer1start;
    private Date timer1stop;
    private Date timer2start;
    private Date timer2stop;
    private Integer flowRate;
    private Boolean flowControlEnable;
    private Integer prediction;
    private Boolean gasAvailability;
    private Integer volume;
    private Integer reducerType;
    private Boolean lightEnable;
}
