package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.User;
import de.hitec.nhplus.utils.DateConverter;
import de.hitec.nhplus.utils.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserDao extends DaoImp<User>{

    public UserDao(Connection connection) {
        super(connection);
    }

    /**
     * Maps a <code>ResultSet</code> of one patient to an object of <code>Patient</code>.
     *
     * @param result ResultSet with a single row. Columns will be mapped to an object of class <code>Patient</code>.
     * @return Object of class <code>Patient</code> with the data from the resultSet.
     */
    @Override
    protected User getInstanceFromResultSet(ResultSet result) throws SQLException {
        return new User(
                result.getInt("uid"),
                result.getString("username"),
                result.getString("password_hash"),
                result.getString("salt"),
                User.Role.valueOf(result.getString("role")),
                DateConverter.convertStringToLocalDate(result.getString("created_at")),
                DateConverter.convertStringToLocalDate(result.getString("last_login")));
    }

    /**
     * Maps a <code>ResultSet</code> of all patients to an <code>ArrayList</code> of <code>Patient</code> objects.
     *
     * @param result ResultSet with all rows. The Columns will be mapped to objects of class <code>Patient</code>.
     * @return <code>ArrayList</code> with objects of class <code>Patient</code> of all rows in the
     * <code>ResultSet</code>.
     */
    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<User> list = new ArrayList<>();
        while (result.next()) {
            User.Role role = User.Role.valueOf(result.getString("role"));
            LocalDate creationDate = DateConverter.convertStringToLocalDate(result.getString("created_at"));
            LocalDate lastLogin = null;
            User user = new User(
                    result.getInt("uid"),
                    result.getString("username"),
                    result.getString("password_hash"),
                    result.getString("salt"),
                    role,
                    creationDate,
                    lastLogin);
            list.add(user);
        }
        return list;
    }

    @Override
    protected PreparedStatement getCreateStatement(User user) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "INSERT INTO user (username, password_hash, salt, role, created_at) " +
                    "VALUES (?, ?, ?, ?, ?)";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPasswordHash());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setString(4, user.getRole().name());
            preparedStatement.setString(5, user.getCreatedAt());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getReadByIDStatement(long uid){
        PreparedStatement preparedStatement = null;
        try{
            final String SQL = "SELECT * FROM user WHERE uid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1,uid);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getReadAllStatement(){
        PreparedStatement statement = null;
        try{
            final String SQL = "SELECT * FROM user";
            statement = this.connection.prepareStatement(SQL);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getUpdateStatement(User user){
        PreparedStatement preparedStatement = null;
        try{
            final String SQL =
                    "UPDATE user SET " +
                            "username = ?, " +
                            "role = ? " +
                            "WHERE uid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getRole().name());
            preparedStatement.setLong(3, user.getUid());
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getDeleteStatement(long uid){
        PreparedStatement preparedStatement = null;
        try{
            final String SQL = "DELETE FROM user WHERE uid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, uid);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    protected PreparedStatement getPasswordUpdateStatement(User user){
        PreparedStatement preparedStatement = null;
        try{
            final String SQL =
                    "UPDATE user SET " +
                            "password_hash = ?, " +
                            "salt = ? " +
                            "WHERE uid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getPasswordHash());
            preparedStatement.setString(2, PasswordUtil.generateSalt());
            preparedStatement.setLong(3, user.getUid());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    protected PreparedStatement getLastLoginUpdateStatement(User user){
        PreparedStatement preparedStatement = null;
        try{
            final String SQL =
                    "UPDATE user SET " +
                            "last_login = ? " +
                            "WHERE uid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getLastLogin());
            preparedStatement.setLong(2, user.getUid());
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return preparedStatement;
    }
}
