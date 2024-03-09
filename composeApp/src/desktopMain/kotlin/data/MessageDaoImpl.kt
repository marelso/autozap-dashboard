package data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.sql.Connection

class MessageDaoImpl(private val connection: Connection) : MessageDao {
    override fun fetch(): MutableState<List<Message>> {
        val messages = mutableListOf<Message>()
        val query = "SELECT * FROM message ORDER BY id DESC"
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
        val query = "INSERT INTO message (message) VALUES ('${message.message}', true)"
        connection.prepareStatement(query).executeUpdate()
    }

    override fun update(message: Message) {
        val query = "UPDATE message set message = '${message.message}' WHERE id = ${message.id}"
        connection.prepareStatement(query).executeUpdate()
    }

    override fun deleteById(id: Int) {
        val query = "DELETE FROM message WHERE id = $id"
        connection.prepareStatement(query).executeUpdate()
    }
}