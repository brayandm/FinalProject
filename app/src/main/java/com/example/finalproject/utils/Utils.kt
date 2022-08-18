package com.example.finalproject.utils

class Utils {
    fun getID(city: String, country: String) : String
    {
        return "$city : $country"
    }

    fun containsDigit(cad: String) : Boolean
    {
        for (c in cad)
        {
            if(c.isDigit())return true
        }
        return false
    }

    fun containsLetter(cad: String) : Boolean
    {
        for (c in cad)
        {
            if(c.isLetter())return true
        }
        return false
    }

    fun isGoodPassword(password: String) : Boolean
    {
        if(password.length >= 8 && containsDigit(password) && containsLetter(password))return true
        return false
    }
}