package data

import java.io.File

object WindowsServiceManager {
    private const val service = "autozap.exe"

    fun start() {
        try {
            buildProcess(listOf("net start $service")).start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stop() {
        try {
            buildProcess(listOf("net stop $service")).start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isRunning(): Boolean {
        try {
            val process = Runtime.getRuntime().exec("sc query $service")
            val inputStream = process.inputStream.bufferedReader()
            val output = inputStream.readText()
            return output.contains("STATE") && output.contains("RUNNING")
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun openAuth() {
        buildProcess(listOf("./auth.bat")).start()
    }

    private fun buildProcess(command: List<String>): ProcessBuilder {
        return ProcessBuilder(command).directory(File(System.getProperty("user.dir")))
    }
}