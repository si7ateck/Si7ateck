package com.si7ateck.dz.ui.pharmacy2

import android.app.Application
import androidx.lifecycle.*
import com.si7ateck.dz.MyDataBase
import com.si7ateck.dz.data.gps.Location
import com.si7ateck.dz.data.pharmacy.Pharmacy
import com.si7ateck.dz.data.types.City
import com.si7ateck.dz.data.workingtime.WorkingTime
import com.si7ateck.dz.repository.LocationRepository
import com.si7ateck.dz.repository.PharmacyRepository
import com.si7ateck.dz.repository.WorkingTimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class PharmacyViewModel (application: Application) : AndroidViewModel(application) {
    private val wtDatabaseDao = MyDataBase.getDatabase(
        application
    ).wtDatabaseDao()
    private val locationDatabaseDao = MyDataBase.getDatabase(
        application
    ).locationDatabaseDao()
    private val pharmacyDatabaseDao = MyDataBase.getDatabase(
        application
    ).pharmacyDatabaseDao()
    private val repositoryl: LocationRepository = LocationRepository(locationDatabaseDao)
    private val repositorywt: WorkingTimeRepository = WorkingTimeRepository(wtDatabaseDao)
    private val repositoryp: PharmacyRepository = PharmacyRepository(pharmacyDatabaseDao)
    val getAllPharmacy : LiveData<List<Pharmacy>> = repositoryp.getAllPharmacy

    private val _pharmacyitems =MediatorLiveData<ArrayList<Pharmacy>>().apply {
        value = ArrayList()
        value!!.add(Pharmacy(1,"abc","belquace","R.Drawable.ppng","0540073829"))
        value!!.add(Pharmacy(2,"abcd","belquace","R.Drawable.ppng","0540073829"))
        value!!.add(Pharmacy(3,"abcde","belquace","R.Drawable.ppng","0540073829"))
        value!!.add(Pharmacy(4,"abcdef","belquace","R.Drawable.ppng","0540073829"))
        value!!.add(Pharmacy(5,"abcdefg","belquace","R.Drawable.ppng","0540073829"))

    }
    private val pharmacyitems : LiveData<ArrayList<Pharmacy>> = _pharmacyitems

    private val _locationitems = MutableLiveData<ArrayList<Location>>().apply {
        value = ArrayList<Location>()
        value!!.add(
            Location(
                "abc",
                2.22,
                0.5,
                City.SIDI_BEL_ABBES,
                "dar el berd",
                "2 rue belquace",
                233
            )
        )
        value!!.add(
            Location(
                "abcd",
                2.223,
                0.05,
                City.SIDI_BEL_ABBES,
                "dar el berd",
                "2 rue belquace",
                2334
            )
        )
        value!!.add(
            Location(
                "abcde",
                2.225,
                0.45,
                City.SIDI_BEL_ABBES,
                "dar el berd",
                "2 rue belquace",
                2335
            )
        )
        value!!.add(
            Location(
                "abcdef",
                2.227,
                0.35,
                City.SIDI_BEL_ABBES,
                "dar el berd",
                "2 rue belquace",
                2337
            )
        )
        value!!.add(
            Location(
                "abcdefg",
                2.228,
                0.75,
                City.SIDI_BEL_ABBES,
                "dar el berd",
                "2 rue belquace",
                2339
            )
        )
    }
    val locationitems: LiveData<ArrayList<Location>> = _locationitems

    private val _wtitems = MutableLiveData<ArrayList<WorkingTime>>().apply {
        value = ArrayList<WorkingTime>()
        value!!.add(
            WorkingTime(
                "abc",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
        value!!.add(
            WorkingTime(
                "abcd",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
        value!!.add(
            WorkingTime(
                "abcde",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
        value!!.add(
            WorkingTime(
                "abcdef",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
        value!!.add(
            WorkingTime(
                "abcdefg",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
    }
    val wtitems: LiveData<ArrayList<WorkingTime>> = _wtitems

    fun insertDatal(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryl.insert(location)
        }
    }
    fun insertDatap(pharmacy: Pharmacy) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryp.insert(pharmacy)
        }
    }

    fun insertDatawt(workingTime: WorkingTime) {
        viewModelScope.launch(Dispatchers.IO) {
            repositorywt.insert(workingTime)
        }
    }
    fun intilizeDatal() {

        locationitems.value?.forEach { item ->
            insertDatal(item)
        }
    }

    fun intilizeDatawt() {

        wtitems.value?.forEach { item ->
            insertDatawt(item)
        }
    }
    fun intilizeDatap() {

        pharmacyitems.value?.forEach { item ->
            insertDatap(item)
        }
    }
}