package data

import androidx.compose.runtime.MutableState
import androidx.room.Insert
import androidx.room.Query

interface AttendantDao {
    @Query("SELECT * From attendant")
    fun fetch() : MutableState<List<Attendant>>

    @Insert()
    fun insert(attendant: Attendant)

    @Query("DELETE FROM attendant where attendantId = :id")
    fun deleteById(id: Int)
}