package com.example.contacts.models

data class Contact (val id: Int, val name:String, val phoneNumber: String){
    constructor(name: String, phoneNumber: String): this(-1, name, phoneNumber)
}