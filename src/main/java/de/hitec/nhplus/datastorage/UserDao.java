package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.User;
import de.hitec.nhplus.utils.DateConverter;
import de.hitec.nhplus.utils.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class UserDao extends DaoImp<User>{

    public UserDao(Connection connection) {
        super(connection);
    }

    /**
     * Maps a <code>ResultSet</code> of one user to an object of <code>User</code>.
     *
     * @param result ResultSet with a single row. Columns will be mapped to an object of class <code>User</code>.
     * @return Object of class <code>User</code> with the data from the resultSet.
     */
    @Override
    protected User getInstanceFromResultSet(ResultSet result) throws SQLException {
        return new User(
                result.getInt("uid"),
                result.getString("username"),
                result.getString("password_hash"),
                result.getString("salt"),
                User.Role.valueOf(result.getString("role")),
                DateConverter.convertStringToLocalDateTime(result.getString("created_at")),
                DateConverter.convertStringToLocalDateTime(result.getString("last_login")));
    }

    /**
     * Maps a <code>ResultSet</code> of all users to an <code>ArrayList</code> of <code>User</code> objects.
     *
     * @param result ResultSet with all rows. The Columns will be mapped to objects of class <code>User</code>.
     * @return <code>ArrayList</code> with objects of class <code>User</code> of all rows in the
     * <code>ResultSet</code>.
     */
    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<User> list = new ArrayList<>();
        while (result.next()) {
            User.Role role = User.Role.valueOf(result.getString("role"));
            LocalDateTime creationDate = DateConverter.convertStringToLocalDateTime(result.getString("created_at"));
            LocalDateTime lastLogin = DateConverter.convertStringToLocalDateTime(result.getString("last_login"));
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

    /**
     * Generates a <code>PreparedStatement</code> to persist the given object of <code>User</code>.
     *
     * @param user Object of <code>User</code> to persist.
     * @return <code>PreparedStatement</code> to insert the given user.
     */
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

    /**
     * Generates a <code>PreparedStatement</code> to query a user by a given user id (uid).
     *
     * @param uid User id to query.
     * @return <code>PreparedStatement</code> to query the user.
     */
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

    /**
     * Generates a <code>PreparedStatement</code> to query all users.
     *
     * @return <code>PreparedStatement</code> to query all users.
     */
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

    /**
     * Generates a <code>PreparedStatement</code> to update the given user's <code>username</code> and <code>role</code>, identified
     * by the id of the user (uid).
     *
     * @param user User object to update.
     * @return <code>PreparedStatement</code> to update the given user.
     */
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

    /**
     * Generates a <code>PreparedStatement</code> to delete a user with the given id.
     *
     * @param uid Id of the user to delete.
     * @return <code>PreparedStatement</code> to delete user with the given id.
     */
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

    /**
     * Generates a <code>PreparedStatement</code> to update the given user's <code>password_hash</code> and <code>salt</code>, identified
     * by the id of the user (uid).
     *
     * @param user User object to update.
     * @return <code>PreparedStatement</code> to update the given user.
     */
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

    /**
     * Generates a <code>PreparedStatement</code> to update the given user's <code>last_login</code>, identified
     * by the id of the user (uid).
     *
     * @param user User object to update.
     * @return <code>PreparedStatement</code> to update the given user.
     */
    public void getLastLoginUpdateStatement(User user) throws SQLException {
        user.setLastLogin(DateConverter.convertLocalDateTimeToString(LocalDateTime.now()));
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
        preparedStatement.executeUpdate();
    }

    /**
     * Searches for a specific <code>User</code> in the database by the given <code>username</code> and returns the <code>User</code> if found, or null if the given <code>username</code> does not match any <code>User</code>.
     * @param username the username of the User that is being searched for in the database.
     * @return <code>User</code>
     * @throws SQLException
     */
    public User findByUsername(String username) throws SQLException{
        PreparedStatement preparedStatement = null;
            final String SQL =
                    "SELECT * FROM user WHERE username = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                return getInstanceFromResultSet(result);
            }
            return null;
    }
}
