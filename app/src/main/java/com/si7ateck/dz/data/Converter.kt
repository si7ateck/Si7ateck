package com.si7ateck.dz.data

import androidx.room.TypeConverter
import com.si7ateck.dz.ui.types.*

class Converter {
    @TypeConverter
    fun fromType(type: Type):String{
        return type.name
    }
    @TypeConverter
    fun toType(type: String): Type {
        return Type.valueOf(type)
    }
    @TypeConverter
    fun fromSpecialty(specialty: Specialty):String{
        return specialty.name
    }
    @TypeConverter
    fun toSpecialty(specialty: String): Specialty {
        return Specialty.valueOf(specialty)
    }
    @TypeConverter
    fun fromCity(city: City):String{
        return city.name
    }
    @TypeConverter
    fun toCity(city: String): City {
        return City.valueOf(city)
    }
    @TypeConverter
    fun fromUserType(userType: UserType):String{
        return userType.name
    }
    @TypeConverter
    fun toUserType(userType: String): UserType {
        return UserType.valueOf(userType)
    }
    @TypeConverter
    fun fromBloodType(bloodType: BloodType):String{
        return bloodType.name
    }
    @TypeConverter
    fun toBloodType(bloodType : String): BloodType {
        return BloodType.valueOf(bloodType)
    }
}