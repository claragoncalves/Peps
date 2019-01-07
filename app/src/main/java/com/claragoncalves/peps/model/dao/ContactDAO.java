package com.claragoncalves.peps.model.dao;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.claragoncalves.peps.model.pojo.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    public List<Contact> getUserContactsFromPhone(Context context) {
        List<Contact> contacts = new ArrayList<>();

        Cursor cP = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.CONTACT_ID },
                ContactsContract.RawContacts.ACCOUNT_TYPE + "= ?",
                new String[] { "com.whatsapp" },
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        int contactPhoneColumn = cP.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int contactNameColumn = cP.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int contactIdColumn = cP.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);

        while (cP.moveToNext()) {
            String phNumber = cP.getString(contactPhoneColumn).replace("+","");
            contacts.add(new Contact(cP.getString(contactIdColumn), cP.getString(contactNameColumn), null, phNumber, null));
        }

        cP.close();
        return contacts;
    }
}
