package com.example.mysweethome

//database use
class LostFound (val id: String, val date: String, val location: String, val item: String, val status: String) {

    //blank/initial constructor
    constructor() : this("", "", "", "", ""){}
}