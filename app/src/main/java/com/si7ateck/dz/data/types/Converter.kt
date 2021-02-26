package com.si7ateck.dz.data.types

import androidx.room.TypeConverter
import com.si7ateck.dz.data.types.Specialty
import com.si7ateck.dz.data.types.Type

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
}