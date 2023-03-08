package io.ynov.rayziaxcorpproject

import android.app.Application
import android.content.res.Configuration
import android.provider.ContactsContract.Data
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

class App : Application() {

    companion object {
        var config = Configuration()
        var locale = Locale("en")
        var darkMode: Boolean = false
    }

    override fun onCreate() {
        super.onCreate()
        setLocaleLang(locale);
        setDarkMode(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES);
        apply()
    }

    public fun setDarkMode(pDarkMode: Boolean) {
        darkMode = pDarkMode

    }

    public fun applyDarkMode(){
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    public fun apply(){
        saveConfig();
        applyDarkMode();
        WelcomeActivity.dLocale = locale;
        MainActivity.dLocale = locale;
    }

    public fun setLocaleLang(pLocale: Locale) {
        locale = pLocale
        Locale.setDefault(pLocale)
        config.setLocale(pLocale)
    }

    public fun saveConfig() {
        applicationContext.createConfigurationContext(config)
    }
}
