package org.almkg.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by yarnykh on 26.01.2016.
 */

@DatabaseTable(tableName = "devices")
public class Device {

    public static final String PASSWORD_FIELD = "passwd";
    public static final String DEVICE_DATE_FIELD = "device_date";
    public static final String UPDATE_DATE_FIELD = "update_date";
    public static final String DEVICE_MODE_FIELD = "device_mode";
    public static final String TIMER_1_START_FIELD = "timer_1_start";
    public static final String TIMER_1_STOP_FIELD = "timer_1_stop";
    public static final String TIMER_2_START_FIELD = "timer_2_start";
    public static final String TIMER_2_STOP_FIELD = "timer_2_stop";
    public static final String FLOW_RATE_FIELD = "flowrate";
    public static final String FLOW_ENABLED_FIELD = "flow_enabled";
    public static final String PREDICTION_FIELD = "prediction";
    public static final String GAS_AVAILABILITY_FIELD = "gas_available";
    public static final String VOLUME_FIELD = "volume";
    public static final String REDUCER_TYPE_FIELD = "reducer_type";
    public static final String LIGHT_ENABLED_FIELD = "light_enabled";

    @DatabaseField(generatedId = true)
    private int deviceId;

    @DatabaseField(columnName = PASSWORD_FIELD, canBeNull = false)
    private String password;

    @DatabaseField(columnName = DEVICE_DATE_FIELD, canBeNull = false)
    private Date deviceDate;

    @DatabaseField(columnName = UPDATE_DATE_FIELD)
    private Date updateDate;

    @DatabaseField(columnName = DEVICE_MODE_FIELD)
    private String deviceMode;

    @DatabaseField(columnName = TIMER_1_START_FIELD)
    private Date timer1start;

    @DatabaseField(columnName = TIMER_1_STOP_FIELD)
    private Date timer1stop;

    @DatabaseField(columnName = TIMER_2_START_FIELD)
    private Date timer2start;

    @DatabaseField(columnName = TIMER_2_STOP_FIELD)
    private Date timer2stop;

    @DatabaseField(columnName = FLOW_RATE_FIELD)
    private Integer flowRate;

    @DatabaseField(columnName = FLOW_ENABLED_FIELD)
    private Boolean flowControlEnabled;

    @DatabaseField(columnName = PREDICTION_FIELD)
    private Integer prediction;

    @DatabaseField(columnName = GAS_AVAILABILITY_FIELD)
    private Boolean gasAvailability;

    @DatabaseField(columnName = VOLUME_FIELD)
    private Integer volume;

    @DatabaseField(columnName = REDUCER_TYPE_FIELD)
    private Integer reducerType;

    @DatabaseField(columnName = LIGHT_ENABLED_FIELD)
    private Boolean lightEnabled;

    public Device() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDeviceDate() {
        return deviceDate;
    }

    public void setDeviceDate(Date deviceDate) {
        this.deviceDate = deviceDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeviceMode() {
        return deviceMode;
    }

    public void setDeviceMode(String deviceMode) {
        this.deviceMode = deviceMode;
    }

    public Date getTimer1start() {
        return timer1start;
    }

    public void setTimer1start(Date timer1start) {
        this.timer1start = timer1start;
    }

    public Date getTimer1stop() {
        return timer1stop;
    }

    public void setTimer1stop(Date timer1stop) {
        this.timer1stop = timer1stop;
    }

    public Date getTimer2start() {
        return timer2start;
    }

    public void setTimer2start(Date timer2start) {
        this.timer2start = timer2start;
    }

    public Date getTimer2stop() {
        return timer2stop;
    }

    public void setTimer2stop(Date timer2stop) {
        this.timer2stop = timer2stop;
    }

    public Integer getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(Integer flowRate) {
        this.flowRate = flowRate;
    }

    public Boolean getFlowControlEnabled() {
        return flowControlEnabled;
    }

    public void setFlowControlEnabled(Boolean flowControlEnabled) {
        this.flowControlEnabled = flowControlEnabled;
    }

    public Integer getPrediction() {
        return prediction;
    }

    public void setPrediction(Integer prediction) {
        this.prediction = prediction;
    }

    public Boolean getGasAvailability() {
        return gasAvailability;
    }

    public void setGasAvailability(Boolean gasAvailability) {
        this.gasAvailability = gasAvailability;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getReducerType() {
        return reducerType;
    }

    public void setReducerType(Integer reducerType) {
        this.reducerType = reducerType;
    }

    public Boolean getLightEnabled() {
        return lightEnabled;
    }

    public void setLightEnabled(Boolean lightEnabled) {
        this.lightEnabled = lightEnabled;
    }
}
