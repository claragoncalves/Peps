package com.claragoncalves.peps.model.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.claragoncalves.peps.util.TimeConverter;

import java.util.Date;

@Entity(tableName = "orders_table")
public class Order {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    private String name;
    @TypeConverters(TimeConverter.class)
    private Date date;
    private String notes;

    public Order() {
    }

    @Ignore
    public Order(String name, String notes) {
        this.name = name;
        this.date = new Date();
        this.notes = notes;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @NonNull
    public Integer getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    @Ignore
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                '}';
    }
}
