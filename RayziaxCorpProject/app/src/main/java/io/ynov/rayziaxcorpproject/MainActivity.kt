package io.ynov.rayziaxcorpproject

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout : DrawerLayout

    companion object {
        public var dLocale : Locale? = null
    }
    init {
        updateConfig(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.main_drawer_menu) //find drawer menu by id
        val navView : NavigationView = findViewById(R.id.nav_view) // find navigation menu by id

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // call function when id item menu click is found
        navView.setNavigationItemSelectedListener {
            it.isChecked = true // highlight item menu when click
            when(it.itemId){
                R.id.nav_home -> replaceFragment(HomeFragment(),it.title.toString())
                R.id.nav_quiz -> replaceFragment(QuizFragment(),it.title.toString())
                R.id.nav_quit -> finish()
                //R.id.nav_account -> replaceFragment(AccountFragment(),it.title.toString())
                R.id.nav_settings -> replaceFragment(PreferencesFragment(),it.title.toString())
            }
            true
        }
        replaceFragment(HomeFragment(),title.toString()) // default add fragment home

        Toast.makeText(
            this@MainActivity,
            resources.getString(R.string.toast_welcome) + " " + intent.getStringExtra(DataObj.USER_NAME),
            Toast.LENGTH_SHORT
        ).show()
    }

    /***
     * Methode to replace fragment in MainActivity
     * param="fragment": target fragment you want show
     * param="title": title app or an other title
     */
    public fun replaceFragment(fragment: Fragment, title:String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frameLayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.close()
        setTitle(title)
    }

    /***
     * Method override to triggered menu drawer
     * param="item": menu attached to activity
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateConfig(wrapper: ContextThemeWrapper) {
        if(dLocale==Locale("") ){
            return
        }
        Locale.setDefault(dLocale)
        val configuration = Configuration()
        configuration.setLocale(dLocale)
        wrapper.applyOverrideConfiguration(configuration)
    }

}