package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.Caregiver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DaoImp</code>. Overrides methods to generate specific <code>PreparedStatements</code>,
 * to execute the specific SQL Statements.
 */
public class CaregiverDao extends DaoImp<Caregiver> {
    /**
     * The constructor initiates an object of <code>CaregiverDao</code> and passes the connection to its super class.
     *
     * @param connection Object of <code>Connection</code> to execute the SQL-statements.
     */
    public CaregiverDao(Connection connection) {
        super(connection);
    }

    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet set) throws SQLException {
        return null;
    }

    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet set) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getCreateStatement(Caregiver caregiver) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "INSERT INTO caregiver ()" + "VALUES()";
            preparedStatement = this.connection.prepareStatement(SQL);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getReadByIDStatement(long key) {
        return null;
    }

    @Override
    protected PreparedStatement getReadAllStatement() {
        return null;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Caregiver caregiver) {
        return null;
    }

    @Override
    protected PreparedStatement getDeleteStatement(long key) {
        return null;
    }
}
