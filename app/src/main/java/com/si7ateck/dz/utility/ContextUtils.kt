package com.si7ateck.dz.utility

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.text.SimpleDateFormat
import java.util.*

class ContextUtils(base: Context) : ContextWrapper(base) {

    companion object {

        fun updateLocale(c: Context, localeToSwitchTo: Locale): ContextWrapper {
            var context = c
            val resources: Resources = context.resources
            val configuration: Configuration = resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(localeToSwitchTo)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            } else {
                configuration.locale = localeToSwitchTo
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                context = context.createConfigurationContext(configuration)
            } else {
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
            return ContextUtils(context)
        }


        fun isTimeBetweenTwoTime(initialTime : String, finalTime : String ,  currentTime : String): Boolean {
            var isInclude = false

            //start Time
            var inTime =  SimpleDateFormat("HH:mm").parse(initialTime)
            var calendar1 = Calendar.getInstance()
            calendar1.time = inTime


            //Current Time
            var curTime =  SimpleDateFormat("HH:mm").parse(currentTime);
            var calendar3 = Calendar.getInstance()
            calendar3.time =curTime

            //End Time
            var finTime = SimpleDateFormat("HH:mm").parse(finalTime);
            var calendar2 = Calendar.getInstance();
            calendar2.time = finTime



            if (finalTime.compareTo(initialTime) < 0)
            {
                calendar2.add(Calendar.DATE, 1);
                calendar3.add(Calendar.DATE, 1);
            }

            var actualTime = calendar3.getTime()
            if ((actualTime.after(calendar1.getTime()) ||
                        actualTime.compareTo(calendar1.getTime()) == 0) &&
                actualTime.before(calendar2.getTime()))
            {
                isInclude = true
                return isInclude
            }

            return false
        }


    }
}