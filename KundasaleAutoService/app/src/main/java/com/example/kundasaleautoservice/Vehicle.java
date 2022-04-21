package com.example.kundasaleautoservice;

import androidx.annotation.NonNull;

public class Vehicle {

    private String address;           //Find Variables
    private String regnum;
    private String vehicletype;
    private String makemodel;
    private String colour;
    private String chassis;
    private String year;
    private String insurance;

    public Vehicle() {          //Constructor Method
    }

    public String getAddress() {                //Create Getter and Setter
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegnum() {
        return regnum;
    }

    public void setRegnum(String regnum) {
        this.regnum = regnum;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getMakemodel() {
        return makemodel;
    }

    public void setMakemodel(String makemodel) {
        this.makemodel = makemodel;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public Vehicle(String address, String regnum, String vehicletype, String makemodel, String colour, String chassis, String year, String insurance){

            this.address = address;
            this.regnum = regnum;
            this.vehicletype = vehicletype;
            this.makemodel = makemodel;
            this.colour = colour;
            this.chassis = chassis;
            this.year = year;
            this.insurance = insurance;
    }

    @NonNull
    public String toString() {
        return this.address + "\n" + regnum + "\n" + vehicletype + "\n" + makemodel + "\n" + colour + "\n" + chassis + "\n" + year + "\n" + insurance;
    }
}
