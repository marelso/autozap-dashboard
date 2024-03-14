package data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.sql.Connection

class MessageDaoImpl(private val connection: Connection) : MessageDao {
    override fun fetch(): MutableState<List<Message>> {
        val messages = mutableListOf<Message>()
        val query = "SELECT * FROM message ORDER BY active DESC, id DESC"
        connection.createStatement().use { statement ->
            statement.executeQuery(query).use { resultSet ->
                while (resultSet.next()) {
                    messages.add(Message(
                        id = resultSet.getInt("id"),
                        message = resultSet.getString("message"),
                        active = resultSet.getBoolean("active")
                    ))
                }
            }
        }
        return mutableStateOf(messages)
    }

    override fun insert(message: Message) {
        connection.prepareStatement("INSERT INTO message (message, active) VALUES (?, false)").apply {
            setString(1, message.message)
        }.executeUpdate()
    }

    override fun update(message: Message) {
        connection.prepareStatement("UPDATE message set message = ? WHERE id = ?").apply {
            setString(1, message.message)
            setInt(2, message.id)
        }.executeUpdate()
    }

    override fun deleteById(id: Int) {
        val query = "DELETE FROM message WHERE id = $id"
        connection.prepareStatement(query).executeUpdate()
    }

    override fun setActive(id: Int) {
        val query = "UPDATE message SET active = CASE WHEN id = $id THEN true ELSE false END;"
        connection.prepareStatement(query).executeUpdate()
    }
}