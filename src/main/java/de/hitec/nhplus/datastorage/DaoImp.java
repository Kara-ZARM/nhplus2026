package de.hitec.nhplus.datastorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DaoImp<T> implements Dao<T> {
    protected Connection connection;

    public DaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(T t) throws SQLException {
        try (PreparedStatement statement = getCreateStatement(t)) {
            statement.executeUpdate();
        }
    }

    /**
     * Get the Id of the last created dataset on this connection.
     * Useful if a Dao object is created without a persisted Id, so the database utilizes the auto-increment.
     * @return Id of the last created dataset
     * @throws SQLException if the connection did not fetch any result from last_insert_rowid.
     */
    public long getLastCreatedId() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid()");
            if(resultSet.next()){
                return resultSet.getLong(1);
            } else {
                throw new SQLException("No Id returned");
            }
        }
    }

    @Override
    public T read(long key) throws SQLException {
        T object = null;
        try (PreparedStatement statement = getReadByIDStatement(key);
             ResultSet result = statement.executeQuery()) {
            if (result.next()) {
                object = getInstanceFromResultSet(result);
            }
        }
        return object;
    }

    @Override
    public List<T> readAll() throws SQLException {
        try (PreparedStatement statement = getReadAllStatement();
             ResultSet result = statement.executeQuery()) {
            return getListFromResultSet(result);
        }
    }

    @Override
    public void update(T t) throws SQLException {
        try (PreparedStatement statement = getUpdateStatement(t)) {
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(long key) throws SQLException {
        try (PreparedStatement statement = getDeleteStatement(key)) {
            statement.executeUpdate();
        }
    }

    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;

    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    protected abstract PreparedStatement getCreateStatement(T t);

    protected abstract PreparedStatement getReadByIDStatement(long key);

    protected abstract PreparedStatement getReadAllStatement();

    protected abstract PreparedStatement getUpdateStatement(T t);

    protected abstract PreparedStatement getDeleteStatement(long key);
}
