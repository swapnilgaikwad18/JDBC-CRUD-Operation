package com.pojos;

public class AddressDetails {
    //addressId | city   | country | empId
    private int addressId;
    private String city;
    private String country;
    private int empId;

    public AddressDetails() {
    }

    public AddressDetails(int addressId, String city, String country, int empId) {
        super();
        this.addressId = addressId;
        this.city = city;
        this.country = country;
        this.empId = empId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "addressId=" + addressId + "  city='" + city + '\'' + "  country='" + country + '\'' + "  empId=" + empId;
    }
}
