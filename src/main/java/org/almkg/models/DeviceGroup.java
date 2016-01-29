package org.almkg.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yarnykh on 26.01.2016.
 */
@DatabaseTable(tableName = "device_groups")
public class DeviceGroup {

    private static final String DEVICE_GROUPNAME_FIELD = "groupname";
    private static final String USER_FIELD = "user";

    @DatabaseField(generatedId = true)
    private int deviceGroupId;

    @DatabaseField(columnName = DEVICE_GROUPNAME_FIELD)
    private String deviceGroupName;

    @DatabaseField(foreign = true, columnName = USER_FIELD)
    private User userId;

    public DeviceGroup() {

    }

    public String getDeviceGroupName() {
        return deviceGroupName;
    }

    public void setDeviceGroupName(String deviceGroupName) {
        this.deviceGroupName = deviceGroupName;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
