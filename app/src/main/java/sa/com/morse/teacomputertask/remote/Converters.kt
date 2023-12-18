package sa.com.morse.teacomputertask.remote

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sa.com.morse.teacomputertask.data.models.ActorsResponse
import sa.com.morse.teacomputertask.domain.models.Actors


class RoomTypeConverters{
    @TypeConverter
    fun convertUserListToJSONString(actorList: Actors): String = Gson().toJson(actorList)
    @TypeConverter
    fun convertJSONStringToInvoiceList(jsonString: String): Actors = Gson().fromJson(jsonString,Actors::class.java)

}