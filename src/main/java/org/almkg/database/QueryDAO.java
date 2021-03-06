package org.almkg.database;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.UpdateResult;
import org.almkg.models.Device;
import org.almkg.models.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yarnykh on 02.02.2016.
 */
public class QueryDAO implements IQueryDAO {

    SQLConnection connection;

    Logger logger = LoggerFactory.getLogger(QueryDAO.class);

    public QueryDAO(Vertx vertx, IDBConnection idbConnection) {
        connection = idbConnection.getConnection(vertx);
    }

    private void executeStatement(String sql){
        connection.execute(sql, event -> {

        });
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        StringBuilder query = new StringBuilder();
        query.append(DBConstants.SELECT_ALL_FROM).append(User.TABLE_NAME);
        connection.query(query.toString(), res -> {
            if (res.succeeded()) {
                ResultSet rs = res.result();
                List<JsonObject> rows = rs.getRows();
                for (JsonObject row: rows) {
                    int id = row.getInteger(User.ID_FIELD);
                    String login = row.getString(User.LOGIN_FIELD);
                    String passwd = row.getString(User.PASSWORD_FIELD);
                    String passwdSalt = row.getString(User.PASSWORD_SALT_FIELD);
                    String name = row.getString(User.NAME_FIELD);
                    User user = new User.UserBuilder(id).setName(name)
                                        .setLogin(login).setPassword(passwd).
                                        setPasswordSalt(passwdSalt).build();
                    users.add(user);
                }
            } else {
                logger.error("Error while getAllUsers");
            }
        });
        return users;
    }

    @Override
    public User getUserByName(String name) {
        User[] user = {null};
        StringBuilder query = new StringBuilder();
        query.append(DBConstants.SELECT_ALL_FROM).append(User.TABLE_NAME).append(" where ? = ?");
        JsonArray params = new JsonArray().add(User.NAME_FIELD).add(name);
        connection.queryWithParams(query.toString(), params, res -> {
            if (res.succeeded()) {
                ResultSet rs = res.result();
                List<JsonObject> rows = rs.getRows();
                JsonObject row = rows.get(0);
                int id = row.getInteger(User.ID_FIELD);
                String login = row.getString(User.LOGIN_FIELD);
                String passwd = row.getString(User.PASSWORD_FIELD);
                String passwdSalt = row.getString(User.PASSWORD_SALT_FIELD);
                String uname = row.getString(User.NAME_FIELD);
                user[0] = new User.UserBuilder(id).setName(uname)
                            .setLogin(login).setPassword(passwd).setPasswordSalt(passwdSalt).build();
            } else {
                logger.error("Error while getUserByName(" + name + ")");
            }
        });
        return user[0];
    }

    @Override
    public void createUser(String login, String password, String passwordSalt, String name) {
        StringBuilder query = new StringBuilder();
        query.append(DBConstants.INSERT_INTO).append(User.TABLE_NAME)
                .append(" (")
                .append(User.LOGIN_FIELD).append(User.PASSWORD_FIELD).append(User.PASSWORD_SALT_FIELD).append(User.NAME_FIELD)
                .append(") ")
                .append("values (?,?,?,?);");
        JsonArray params = new JsonArray().add(login).add(password).add(passwordSalt).add(name);
        connection.updateWithParams(query.toString(), params, res -> {
            if (res.succeeded()) {
                UpdateResult updateResult = res.result();
                logger.info("created user" + name);
                logger.info("No of rows updated: " + updateResult.getUpdated());
            } else {
                logger.error("Error while createUser(" + name + ")");
            }
        });
    }

    @Override
    public void deleteUserByName(String name) {
        StringBuilder query = new StringBuilder();
        query.append(DBConstants.DELETE_FROM).append(User.TABLE_NAME)
                .append(" where ").append(User.NAME_FIELD).append(" = ?;");
        JsonArray params = new JsonArray().add(name);
        connection.updateWithParams(query.toString(), params, res -> {
            if (res.succeeded()) {
                UpdateResult updateResult = res.result();
                logger.info("deleted user" + name);
                logger.info("No of rows updated: " + updateResult.getUpdated());
            } else {
                logger.error("Error while deleteUserByName(" + name + ")");
            }
        });
    }

    @Override
    public Device getDeviceById(int id) {
        Device[] devices = new Device[]{null};
        StringBuilder query = new StringBuilder();
        query.append(DBConstants.SELECT_ALL_FROM).append(Device.TABLE_NAME).append(" where ? = ?");
        JsonArray params = new JsonArray().add(Device.ID_FIELD).add(id);
        connection.queryWithParams(query.toString(), params, res -> {
            if (res.succeeded()) {
                ResultSet rs = res.result();
                List<JsonObject> rows = rs.getRows();
                JsonObject row = rows.get(0);
                int device_id = row.getInteger(Device.ID_FIELD);
                String password = row.getString(Device.PASSWORD_FIELD);
                Instant deviceDate = row.getInstant(Device.DEVICE_DATE_FIELD);
                Instant deviceTime = row.getInstant(Device.DEVICE_TIME_FIELD);
                Instant updateTimestamp = row.getInstant(Device.UPDATE_DATA_TIMESTAMP_FIELD);
                Instant updateTimeTimestamp = row.getInstant(Device.UPDATE_TIME_TIMESTAMP_FIELD);
                String deviceMode  = row.getString(Device.DEVICE_MODE_FIELD);
                Instant timer1start  = row.getInstant(Device.TIMER_1_START_FIELD);
                Instant timer1stop = row.getInstant(Device.TIMER_1_STOP_FIELD);
                Instant timer2start = row.getInstant(Device.TIMER_2_START_FIELD);
                Instant timer2stop = row.getInstant(Device.TIMER_2_STOP_FIELD);
                Integer flowRateMode  = row.getInteger(Device.FLOW_RATE_MODE_FIELD);
                Boolean flowControlEnabled = row.getBoolean(Device.FLOW_CONTROL_ENABLED_FIELD);
                Integer gasAvailabilityPrediction  = row.getInteger(Device.GAS_AVAILABILITY_PREDICTION_FIELD);
                Boolean gasAvailability = row.getBoolean(Device.GAS_AVAILABILITY_FIELD);
                Integer volume = row.getInteger(Device.GAS_CYLINDER_VOLUME_FIELD);
                Integer reducerType = row.getInteger(Device.REDUCER_TYPE_FIELD);
                Boolean lightEnabled = row.getBoolean(Device.LIGHT_ENABLE_FIELD);
                Integer workingTime = row.getInteger(Device.WORKING_TIME_FIELD);
                String version = row.getString(Device.VERSION_FIELD);
                float motorTemperature = row.getFloat(Device.MOTOR_TEMPERATURE_FIELD);
                float heaterTemperature = row.getFloat(Device.HEATER_TEMPERATURE_FIELD);
                float temperature = row.getFloat(Device.TEMPERATURE_FIELD);
                devices[0] = new Device.DeviceBuilder(device_id).setPassword(password)
                        .setDeviceDate(deviceDate).setDeviceTime(deviceTime)
                        .setTimeUpdatedTimestamp(updateTimeTimestamp)
                        .setDeviceMode(deviceMode)
                        .setTimer1start(timer1start).setTimer1stop(timer1stop)
                        .setTimer2start(timer2start).setTimer2stop(timer2stop)
                        .setFlowRateMode(flowRateMode).setFlowControlEnabled(flowControlEnabled)
                        .setGasAvailabilityPrediction(gasAvailabilityPrediction)
                        .setGasAvailability(gasAvailability).setGasCylinderVolume(volume)
                        .setReducerType(reducerType).setLightEnabled(lightEnabled)
                        .setWorkingTime(workingTime)
                        .setVersion(version).setTemperature(temperature)
                        .setMotorTemperature(motorTemperature).setHeaterTemperature(heaterTemperature)
                        .build();
            } else {
                logger.error("Error while getDeviceById(" + id + ")");
            }
        });
        return devices[0];
    }

    @Override
    public void updateDeviceById(int id) {

    }
}
