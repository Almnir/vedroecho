package org.almkg.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by yarnykh on 26.01.2016.
 */
@DatabaseTable(tableName = "device_commands")
public class DeviceCommand {

    private static final String COMMAND_START_FIELD = "command_start";
    private static final String COMMAND_END_FIELD = "command_end";
    private static final String DEVICE_DATE_FIELD = "device_date";

    @DatabaseField(generatedId = true)
    private int commandId;

    @DatabaseField(columnName = COMMAND_START_FIELD, canBeNull = false)
    private Date commandStartTime;

    @DatabaseField(columnName = COMMAND_END_FIELD, canBeNull = false)
    private Date commandEndTime;

    @DatabaseField(columnName = DEVICE_DATE_FIELD, canBeNull = false)
    private Date deviceDate;

    public DeviceCommand() {

    }

    public Date getCommandStartTime() {
        return commandStartTime;
    }

    public void setCommandStartTime(Date commandStartTime) {
        this.commandStartTime = commandStartTime;
    }

    public Date getCommandEndTime() {
        return commandEndTime;
    }

    public void setCommandEndTime(Date commandEndTime) {
        this.commandEndTime = commandEndTime;
    }

    public Date getDeviceDate() {
        return deviceDate;
    }

    public void setDeviceDate(Date deviceDate) {
        this.deviceDate = deviceDate;
    }
}
