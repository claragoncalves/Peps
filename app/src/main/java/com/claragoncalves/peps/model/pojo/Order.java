package com.claragoncalves.peps.model.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.claragoncalves.peps.util.TimeConverter;

import java.util.Date;

@Entity(tableName = "orders_table", indices = {@Index("id")}, foreignKeys = {@ForeignKey(entity = Contact.class, parentColumns = "id", childColumns = "contactId", onDelete = ForeignKey.CASCADE)})
public class Order {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    private String contactId;
    private String name;
    @TypeConverters(TimeConverter.class)
    private Date date;
    private Double total;
    private String notes;

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Double getTotal() {
        return total;
    }

    public String getContactId() {
        return contactId;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }
}
