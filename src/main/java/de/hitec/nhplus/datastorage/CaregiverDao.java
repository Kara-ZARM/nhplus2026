package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.Caregiver;
import de.hitec.nhplus.utils.DateConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    /**
     * Maps a <code>ResultSet</code> of one caregiver to an object of <code>Caregiver</code>.
     *
     * @param result ResultSet with a single row. Columns will be mapped to an object of class <code>Caregiver</code>.
     * @return Object of class <code>Caregiver</code> with the data from the resultSet.
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        return new Caregiver(
                result.getInt("cid"),
                result.getString("firstname"),
                result.getString("surname"),
                DateConverter.convertStringToLocalDate(result.getString("dateOfBirth")),
                result.getString("street"),
                result.getString("postalcode"),
                result.getString("city"),
                result.getString("taxid"),
                result.getString("phonenumber"),
                result.getString("qualification"));
    }

    /**
     * Maps a <code>ResultSet</code> of all caregiver to an <code>ArrayList</code> of <code>Caregiver</code> objects.
     *
     * @param result ResultSet with all rows. The Columns will be mapped to objects of class <code>Caregiver</code>.
     * @return <code>ArrayList</code> with objects of class <code>Caregiver</code> of all rows in the
     * <code>ResultSet</code>.
     */
    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<>();
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString("dateOfBirth"));
            Caregiver caregiver = new Caregiver(
                    result.getInt("cid"),
                    result.getString("firstname"),
                    result.getString("surname"),
                    date,
                    result.getString("street"),
                    result.getString("postalcode"),
                    result.getString("city"),
                    result.getString("taxid"),
                    result.getString("phonenumber"),
                    result.getString("qualification"));
            list.add(caregiver);
        }
        return list;
    }

    /**
     * Generates a <code>PreparedStatement</code> to persist the given object of <code>Caregiver</code>.
     *
     * @param caregiver Object of <code>Caregiver</code> to persist.
     * @return <code>PreparedStatement</code> to insert the given caregiver.
     */
    @Override
    protected PreparedStatement getCreateStatement(Caregiver caregiver) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "INSERT INTO caregiver (firstname, surname, dateOfBirth, street, postalcode, city, taxid, phonenumber, qualification)" +
                    "VALUES(?,?,?,?,?,?,?,?,?)";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, caregiver.getFirstName());
            preparedStatement.setString(2, caregiver.getSurname());
            preparedStatement.setString(3, caregiver.getDateOfBirth());
            preparedStatement.setString(4, caregiver.getStreet());
            preparedStatement.setString(5, caregiver.getPostalcode());
            preparedStatement.setString(6, caregiver.getCity());
            preparedStatement.setString(7, caregiver.getTaxid());
            preparedStatement.setString(8, caregiver.getPhoneNumber());
            preparedStatement.setString(9, caregiver.getQualification());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to query a caregiver by a given caregiver id (cid).
     *
     * @param cid Caregiver id to query.
     * @return <code>PreparedStatement</code> to query the caregiver.
     */
    @Override
    protected PreparedStatement getReadByIDStatement(long cid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "SELECT * FROM caregiver WHERE cid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, cid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to query all caregiver.
     *
     * @return <code>PreparedStatement</code> to query all caregiver.
     */
    @Override
    protected PreparedStatement getReadAllStatement() {
        PreparedStatement statement = null;
        try {
            final String SQL = "SELECT * FROM caregiver";
            statement = this.connection.prepareStatement(SQL);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return statement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to update the given caregiver, identified
     * by the id of the caregiver (cid).
     *
     * @param caregiver Caregiver object to update.
     * @return <code>PreparedStatement</code> to update the given caregiver.
     */
    @Override
    protected PreparedStatement getUpdateStatement(Caregiver caregiver) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL =
                    "UPDATE caregiver SET " +
                            "firstname = ?, " +
                            "surname = ?, " +
                            "dateOfBirth = ?, " +
                            "street = ?, " +
                            "postalcode = ?, " +
                            "city = ?, " +
                            "taxid = ?, " +
                            "phonenumber = ?, " +
                            "qualification = ? " +
                            "WHERE cid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, caregiver.getFirstName());
            preparedStatement.setString(2, caregiver.getSurname());
            preparedStatement.setString(3, caregiver.getDateOfBirth());
            preparedStatement.setString(4, caregiver.getStreet());
            preparedStatement.setString(5, caregiver.getPostalcode());
            preparedStatement.setString(6, caregiver.getCity());
            preparedStatement.setString(7, caregiver.getTaxid());
            preparedStatement.setString(8, caregiver.getPhoneNumber());
            preparedStatement.setString(9, caregiver.getQualification());
            preparedStatement.setLong(10, caregiver.getCid());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to delete a caregiver with the given id.
     *
     * @param cid Id of the caregiver to delete.
     * @return <code>PreparedStatement</code> to delete caregiver with the given id.
     */
    @Override
    protected PreparedStatement getDeleteStatement(long cid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "DELETE FROM caregiver WHERE cid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, cid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }
}
