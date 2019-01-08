package com.claragoncalves.peps.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.claragoncalves.peps.model.PepsRoomDB;
import com.claragoncalves.peps.model.dao.ContactDAO;
import com.claragoncalves.peps.model.dao.ContactRoomDAO;
import com.claragoncalves.peps.model.pojo.Contact;

import java.util.List;

public class ContactRepository {
    private ContactRoomDAO contactRoomDAO;
    private LiveData<List<Contact>> contacts;

    public ContactRepository(Application application){
        PepsRoomDB pepsRoomDB = PepsRoomDB.getDatabase(application);
        contactRoomDAO = pepsRoomDB.contactDAO();
        contacts = contactRoomDAO.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts(){
        return contacts;
    }

    public void insertContact(Contact contact){
        contactRoomDAO.insertContact(contact);
    }

    public void insertContacts(List<Contact> contacts){
        for (Contact contact : contacts) {
            contactRoomDAO.insertContact(contact);
        }
    }

    public Contact getContactById(String contactId){
        return contactRoomDAO.getContactById(contactId);
    }

    public List<Contact> getUserContacts(Context context){
        ContactDAO contactDAO = new ContactDAO();
        return contactDAO.getUserContactsFromPhone(context);
    }
}
