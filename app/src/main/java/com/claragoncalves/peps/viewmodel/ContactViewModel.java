package com.claragoncalves.peps.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.claragoncalves.peps.model.pojo.Contact;
import com.claragoncalves.peps.model.repository.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository repository;
    private LiveData<List<Contact>> contacts;


    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
        contacts = repository.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contacts;
    }

    public void insertContact(Contact contact){
        repository.insertContact(contact);
    }

    public void insertContacts(List<Contact> contacts){
        repository.insertContacts(contacts);
    }

    public List<Contact> getUserContacts(Context context){
        return repository.getUserContacts(context);
    }

}
