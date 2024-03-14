package data

import data.attendant.AttendantDao
import data.attendant.AttendantDaoImpl
import data.message.MessageDao
import data.message.MessageDaoImpl
import java.sql.Connection
import java.sql.DriverManager

class DesktopDatabase private constructor() {
    companion object {
        fun getInstance(): DesktopDatabase {
            return DesktopDatabase()
        }
    }

    private var connection: Connection? = null

    init {
        connect()
    }

    private fun connect() {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:database.db")
            createTables()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createTables() {
        val statement = connection?.createStatement()
        statement?.executeUpdate("CREATE TABLE IF NOT EXISTS attendant (id INTEGER PRIMARY KEY, name TEXT, bio TEXT, link TEXT)")
        statement?.executeUpdate("CREATE TABLE IF NOT EXISTS message (id INTEGER PRIMARY KEY, message TEXT, active BOOLEAN)")
    }

    fun getConnection(): Connection? {
        return connection
    }

    fun getAttendantDao(): AttendantDao {
        val connection = getConnection() ?: throw IllegalStateException("Database connection is not initialized.")
        return AttendantDaoImpl(connection)
    }

    fun getMessageDao(): MessageDao {
        val connection = getConnection() ?: throw IllegalStateException("Database connection is not initialized.")
        return MessageDaoImpl(connection)
    }
}