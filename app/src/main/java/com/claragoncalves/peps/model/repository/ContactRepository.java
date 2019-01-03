package com.claragoncalves.peps.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.claragoncalves.peps.model.PepsRoomDB;
import com.claragoncalves.peps.model.dao.ContactDAO;
import com.claragoncalves.peps.model.pojo.Contact;

import java.util.List;

public class ContactRepository {
    private ContactDAO contactDAO;
    private LiveData<List<Contact>> contacts;

    public ContactRepository(Application application){
        PepsRoomDB pepsRoomDB = PepsRoomDB.getDatabase(application);
        contactDAO = pepsRoomDB.contactDAO();
        contacts = contactDAO.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts(){
        return contacts;
    }

    public void insertContact(Contact contact){
        contactDAO.insertContact(contact);
    }

    public void insertContacts(List<Contact> contacts){
        for (Contact contact : contacts) {
            contactDAO.insertContact(contact);
        }
    }
}
