package de.hitec.nhplus.model;

import de.hitec.nhplus.utils.DateConverter;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
/**
 * Caregiver of the Nursing home, treating the patients
 */
public class Caregiver extends Person{
    private SimpleLongProperty cid;
    private final SimpleStringProperty dateOfBirth;
    private final SimpleStringProperty street;
    private final SimpleStringProperty postalcode;
    private final SimpleStringProperty city;
    private final SimpleStringProperty taxid;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty qualification;

    /**
     * Constructor to initiate an object of class <code>Caregiver</code> with the given parameter. Use this constructor
     * to initiate objects, which are not persisted yet, because it will not have a caregiver id (cid).
     *
     * @param firstName First name of the caregiver.
     * @param surname Last name of the caregiver.
     * @param dateOfBirth Date of birth of the caregiver.
     * @param street Street of the caregiver
     * @param postalcode Postalcode of the caregiver
     * @param city City of the caregiver
     * @param taxid Tax id of the caregiver
     * @param phoneNumber Phone number of the caregiver
     * @param qualification Qualification of the caregiver
     */
    public Caregiver(String firstName, String surname, LocalDate dateOfBirth, String street, String postalcode, String city, String taxid, String phoneNumber, String qualification) {
        this(0,firstName, surname, dateOfBirth, street, postalcode, city, taxid, phoneNumber, qualification);
    }

    /**
     * Constructor to initiate an object of class <code>Caregiver</code> with the given parameter. Use this constructor
     * to initiate objects, which are arlready persisted and have a caregiver id (cid).
     *
     * @param cid Caregiver id.
     * @param firstName First name of the caregiver.
     * @param surname Last name of the caregiver.
     * @param dateOfBirth Date of birth of the caregiver.
     * @param street Street of the caregiver.
     * @param postalcode Postalcode of the caregiver.
     * @param city City of the caregiver.
     * @param taxid Tax id of the caregiver.
     * @param phoneNumber Phone number of the caregiver.
     * @param qualification Qualification of the caregiver.
     */
    public Caregiver(long cid, String firstName, String surname, LocalDate dateOfBirth, String street, String postalcode, String city, String taxid, String phoneNumber, String qualification) {
        super(firstName, surname);
        this.cid = new SimpleLongProperty(cid);
        this.dateOfBirth = new SimpleStringProperty(DateConverter.convertLocalDateToString(dateOfBirth));
        this.street = new SimpleStringProperty(street);
        this.postalcode = new SimpleStringProperty(postalcode);
        this.city = new SimpleStringProperty(city);
        this.taxid = new SimpleStringProperty(taxid);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.qualification = new SimpleStringProperty(qualification);
    }

    public long getCid() {
        return cid.get();
    }

    public SimpleLongProperty cidProperty() {
        return cid;
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleStringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    /**
     * Stores the given string as new <code>birthOfDate</code>.
     *
     * @param dateOfBirth as string in the following format: YYYY-MM-DD.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getPostalcode() {
        return postalcode.get();
    }

    public SimpleStringProperty postalcodeProperty() {
        return postalcode;
    }

    public  void setPostalcode(String postalcode) {
        this.postalcode.set(postalcode);
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getTaxid() {
        return taxid.get();
    }

    public SimpleStringProperty taxidProperty() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid.set(taxid);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getQualification() {
        return qualification.get();
    }

    public SimpleStringProperty qualificationProperty() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification.set(qualification);
    }

    //Bei patient MNID?
    @Override
    public String toString() {
        return "Caregiver" + "\nID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nDateOfBirth=" + this.dateOfBirth +
                "\nStreet=" + this.street +
                "\nPostalcode=" + this.postalcode +
                "\nCity=" + this.city +
                "\nTaxid=" + this.taxid +
                "\nPhoneNumber=" + this.phoneNumber +
                "\nQualification=" + this.qualification +
                '\n';
    }
}
