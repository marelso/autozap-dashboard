package data.message

import androidx.compose.runtime.MutableState
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface MessageDao {
    @Query("SELECT * FROM message")
    fun fetch() : MutableState<List<Message>>

    @Insert()
    fun insert(message: Message)

    @Update
    fun update(message: Message)

    @Query("DELETE FROM message WHERE id = :id")
    fun deleteById(id: Int)

    @Query("UPDATE message SET active = CASE WHEN id = :id THEN true ELSE false END;")
    fun setActive(id: Int)
}