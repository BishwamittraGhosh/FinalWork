package com.example.vacuumtubee.finalapproach;

/**
 * Created by NIT on 4/25/2016.
 */
public class WorkerSearchResult {
    String name;
    String phoneNumber;
    String address;
    String id;

    public WorkerSearchResult(String name, String phoneNumber, String address, String id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.id=id;
    }

    @Override
    public String toString() {
        return "WorkerSearchResult{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
