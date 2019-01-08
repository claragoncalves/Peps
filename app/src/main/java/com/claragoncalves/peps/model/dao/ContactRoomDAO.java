package com.claragoncalves.peps.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.claragoncalves.peps.model.pojo.Contact;
import com.claragoncalves.peps.model.pojo.Product;

import java.util.List;

@Dao
public interface ContactRoomDAO {

    @Query("SELECT * FROM contacts_table ORDER BY name ASC")
    LiveData<List<Contact>> getAllContacts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertContact(Contact contact);

    @Query("SELECT * FROM contacts_table WHERE id == :contactId")
    Contact getContactById(String contactId);

}
