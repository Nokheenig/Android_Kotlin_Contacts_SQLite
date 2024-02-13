package com.example.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.contacts.data.DatabaseHandler
import com.example.contacts.models.Contact

class MainActivity : AppCompatActivity() {
    private lateinit var textViewData : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewData = findViewById(R.id.text_view_data)

        val db = DatabaseHandler(this)

        db.addContact(Contact("Alex", "1238721668"))
        db.addContact(Contact("John", "8924798732"))
        db.addContact(Contact("Maria", "2139021370"))
        db.addContact(Contact("Ana", "3487349078"))

        val contact = db.getContact(3)
        textViewData.text = "ID: ${contact.id}\n" +
                            "Name: ${contact.name}\n" +
                            "PhoneNumber: ${contact.phoneNumber}"

        val newContact = contact
        newContact.name = "Alina"
        newContact.phoneNumber = "1298217398"
        db.updateContact(newContact)

        val updatedContact = db.getContact(3)
        textViewData.append("\n\nID: ${updatedContact.id}\n" +
                            "Updated Name: ${updatedContact.name}\n" +
                            "Updated PhoneNumber: ${updatedContact.phoneNumber}")


//        val contactList = db.getAllContacts()
//        var data = ""
//        for (contact in contactList) {
//            data += "\nName: ${contact.name} " +
//                    "\nPhoneNumber: ${contact.phoneNumber}"
//        }
//        textViewData.text = data
    }
}