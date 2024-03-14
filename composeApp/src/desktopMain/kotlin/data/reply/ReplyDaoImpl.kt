package data.reply

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.sql.Connection

class ReplyDaoImpl (private val connection: Connection) : ReplyDao {
    override fun fetch(): MutableState<List<Reply>> {
        val replies = mutableListOf<Reply>()
        val query = "SELECT * FROM reply ORDER BY active DESC, id DESC"
        connection.createStatement().use { statement ->
            statement.executeQuery(query).use { resultSet ->
                while (resultSet.next()) {
                    replies.add(
                        Reply(
                            id = resultSet.getInt("id"),
                            reply = resultSet.getString("reply"),
                            active = resultSet.getBoolean("active")
                        )
                    )
                }
            }
        }
        return mutableStateOf(replies)
    }

    override fun insert(reply: Reply) {
        connection.prepareStatement("INSERT INTO reply (reply, active) VALUES (?, false)").apply {
            setString(1, reply.reply)
        }.executeUpdate()
    }

    override fun update(reply: Reply) {
        connection.prepareStatement("UPDATE reply set reply = ? WHERE id = ?").apply {
            setString(1, reply.reply)
            setInt(2, reply.id)
        }.executeUpdate()
    }

    override fun deleteById(id: Int) {
        val query = "DELETE FROM reply WHERE id = $id"
        connection.prepareStatement(query).executeUpdate()
    }

    override fun setActive(id: Int) {
        val query = "UPDATE reply SET active = CASE WHEN id = $id THEN true ELSE false END;"
        connection.prepareStatement(query).executeUpdate()
    }
}