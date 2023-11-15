package uz.mbr.novustest

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    private var mySharedPref: SharedPreferences

    init {
        mySharedPref = context.getSharedPreferences("AppConfig", Context.MODE_PRIVATE)
    }

    // Counter all the time
    fun getPhoneNumber(): String? {
        return mySharedPref.getString("phone", "900013060")
    }

    fun setPhoneNumber(number: String) {
        mySharedPref.edit().putString("phone", number).apply()
    }

    fun isRegistered(): Boolean {
        return mySharedPref.getBoolean("signup", false)
    }

    fun setRegistered(isRegistered: Boolean) {
        mySharedPref.edit().putBoolean("signup", isRegistered).apply()
    }
}