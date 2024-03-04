package data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.sql.Connection

class AttendantDaoImpl(private val connection: Connection) : AttendantDao {
    override fun fetch(): MutableState<List<Attendant>> {
        val attendants = mutableListOf<Attendant>()
        val query = "SELECT * FROM attendant ORDER BY id DESC"
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
        val query = "INSERT INTO attendant (name, bio, link) VALUES ('${attendant.name}', '${attendant.bio}', '${attendant.link}')"
        connection.prepareStatement(query).executeUpdate()
    }

    override fun update(attendant: Attendant) {
        val query = "UPDATE attendant set name = '${attendant.name}', bio = '${attendant.bio}', link = '${attendant.link}' WHERE id = ${attendant.id}"
        connection.prepareStatement(query).executeUpdate()
    }

    override fun deleteById(id: Int) {
        val query = "DELETE FROM attendant WHERE id = $id"
        connection.prepareStatement(query).executeUpdate()
    }
}
