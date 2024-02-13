package com.example.contacts.models

data class Contact (val id: Int, var name:String, var phoneNumber: String){
    constructor(name: String, phoneNumber: String): this(-1, name, phoneNumber)
}