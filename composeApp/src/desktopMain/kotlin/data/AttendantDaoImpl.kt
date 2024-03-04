package data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.sql.Connection

class AttendantDaoImpl(private val connection: Connection) : AttendantDao {
    override fun fetch(): MutableState<List<Attendant>> {
        val attendants = mutableListOf<Attendant>()
        val query = "SELECT * FROM attendant"
        connection.createStatement().use { statement ->
            statement.executeQuery(query).use { resultSet ->
                while (resultSet.next()) {
                    attendants.add(Attendant(
                        id = resultSet.getInt("id"),
                        name = resultSet.getString("name"),
                        bio = resultSet.getString("bio"),
                        link = resultSet.getString("link")
                    ))
                }
            }
        }
        return mutableStateOf(attendants)
    }

    override fun insert(attendant: Attendant) {
        val query = "INSERT INTO attendant (name) VALUES (?)"
        connection.prepareStatement(query).use { statement ->
            statement.setString(1, attendant.name)
            statement.executeUpdate()
        }
    }

    override fun deleteById(id: Int) {
        val query = "DELETE FROM attendant WHERE attendantId = ?"
        connection.prepareStatement(query).use { statement ->
            statement.setInt(1, id)
            statement.executeUpdate()
        }
    }
}
