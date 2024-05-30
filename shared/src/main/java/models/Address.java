package models;

import java.io.Serializable;

/**
 * Address class with properties <b>zipCode</b>.
 */
public class Address implements Serializable {
    private String zipCode;

    /**
     *
     * @param postalAddress - Organization zip code
     */

    public Address(String postalAddress) {
        this.zipCode = postalAddress;
    }
    public Address(){};

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
}
