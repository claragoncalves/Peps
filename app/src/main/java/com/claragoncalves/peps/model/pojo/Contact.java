package com.claragoncalves.peps.model.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "contacts_table", indices = {@Index("id")})
public class Contact {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String description;
    @Ignore
    private Boolean addToDb;

    public Contact() {
    }

    public void setAddToDb(Boolean addToDb) {
        this.addToDb = addToDb;
    }

    public Boolean getAddToDb() {
        return addToDb;
    }

    @Ignore
    public Contact(String id, String name, String address, String phoneNumber, String description) {
        this.addToDb = false;
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
