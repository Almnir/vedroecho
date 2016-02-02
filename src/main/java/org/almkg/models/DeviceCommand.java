package org.almkg.models;

/**
 * Created by yarnykh on 26.01.2016.
 */
public class DeviceCommand {

    public static final String TABLE_NAME = "devicecommand";

    public static final String ID_FIELD = "command_id";
    public static final String COMMAND_START_FIELD = "command_start";
    public static final String COMMAND_END_FIELD = "command_end";
    public static final String DEVICE_DATE_FIELD = "device_date";

    private int commandId;

    private String commandStartTime;

    private String commandEndTime;

    private String deviceDate;

    public DeviceCommand(String commandStartTime, String commandEndTime, String deviceDate) {
        this.commandStartTime = commandStartTime;
        this.commandEndTime = commandEndTime;
        this.deviceDate = deviceDate;
    }

    public String getCommandStartTime() {
        return commandStartTime;
    }

    public void setCommandStartTime(String commandStartTime) {
        this.commandStartTime = commandStartTime;
    }

    public String getCommandEndTime() {
        return commandEndTime;
    }

    public void setCommandEndTime(String commandEndTime) {
        this.commandEndTime = commandEndTime;
    }

    public String getDeviceDate() {
        return deviceDate;
    }

    public void setDeviceDate(String deviceDate) {
        this.deviceDate = deviceDate;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }
}
