package data.reply

import androidx.compose.runtime.MutableState
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface ReplyDao {
    @Query("SELECT * FROM reply")
    fun fetch() : MutableState<List<Reply>>

    @Insert()
    fun insert(reply: Reply)

    @Update
    fun update(reply: Reply)

    @Query("DELETE FROM reply WHERE id = :id")
    fun deleteById(id: Int)

    @Query("UPDATE reply SET active = CASE WHEN id = :id THEN true ELSE false END;")
    fun setActive(id: Int)
}