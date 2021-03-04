package com.si7ateck.dz

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val simpleDateFormat = SimpleDateFormat("EEEE'/'HH:mm")
        val currentDateAndTime: String = simpleDateFormat.format(Date())
        Log.d("timeTest", currentDateAndTime)


    }
    fun isTimeBetweenTwoTime(initialTime String, finalTime String ,  currentTime String): Boolean {

        return false
    }



//    public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime,
//    String currentTime) throws ParseException {
//
//        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
//        if (initialTime.matches(reg) && finalTime.matches(reg) &&
//            currentTime.matches(reg))
//        {
//            boolean valid = false;
//            //Start Time
//            //all times are from java.util.Date
//            Date inTime = new SimpleDateFormat("HH:mm:ss").parse(initialTime);
//            Calendar calendar1 = Calendar.getInstance();
//            calendar1.setTime(inTime);
//
//            //Current Time
//            Date checkTime = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
//            Calendar calendar3 = Calendar.getInstance();
//            calendar3.setTime(checkTime);
//
//            //End Time
//            Date finTime = new SimpleDateFormat("HH:mm:ss").parse(finalTime);
//            Calendar calendar2 = Calendar.getInstance();
//            calendar2.setTime(finTime);
//
//            if (finalTime.compareTo(initialTime) < 0)
//            {
//                calendar2.add(Calendar.DATE, 1);
//                calendar3.add(Calendar.DATE, 1);
//            }
//
//            java.util.Date actualTime = calendar3.getTime();
//            if ((actualTime.after(calendar1.getTime()) ||
//                        actualTime.compareTo(calendar1.getTime()) == 0) &&
//                actualTime.before(calendar2.getTime()))
//            {
//                valid = true;
//                return valid;
//            } else {
//                throw new IllegalArgumentException("Not a valid time, expecting
//                        HH:MM:SS format");
//            }
//        }
//    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
                R.id.action_settings -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.settingsFragment)
                    return true
                }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


//    override fun attachBaseContext(newBase: Context?) {
////        val localeToSwitchTo = Locale("ar")
////        val localeUpdatedContext =
////            ContextUtils.updateLocale(newBase!!, localeToSwitchTo)
//        super.attachBaseContext(localeUpdatedContext)
//    }


   fun GoToSignIn(v : View){
       Log.d("MyFirstLogd","Image Called")
   }

   fun GoToSignUp(v : View){
        Log.d("MyFirstLogd","Sign Up Called")
    }


}