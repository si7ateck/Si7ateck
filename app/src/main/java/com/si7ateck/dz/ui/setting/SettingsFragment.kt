package com.si7ateck.dz.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.si7ateck.dz.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}