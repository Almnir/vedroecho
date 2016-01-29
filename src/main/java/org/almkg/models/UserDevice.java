package org.almkg.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Set;

/**
 * Created by yarnykh on 26.01.2016.
 */
@DatabaseTable(tableName = "user_devices")
public class UserDevice {

    private static final String DEVICE_GROUP_FIELD = "device_group";

    @DatabaseField(generatedId = true)
    private int userId;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Device> devices;

    @DatabaseField(foreign = true, columnName = DEVICE_GROUP_FIELD)
    private DeviceGroup deviceGroup;

    public UserDevice() {
    }

    public ForeignCollection<Device> getDevices() {
        return devices;
    }

    public void setDevices(ForeignCollection<Device> devices) {
        this.devices = devices;
    }

    public DeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }
}
