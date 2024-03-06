package data

object WindowsServiceManager {
    private const val service = "autozap.exe"


    fun start(): Boolean {
        try {
            val process = Runtime.getRuntime().exec("net start $service")
            val inputStream = process.inputStream.bufferedReader()
            val output = inputStream.readText()
            return output.contains("is starting") && output.contains("was started")
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun stop(): Boolean {
        try {
            val process = Runtime.getRuntime().exec("net stop $service")
            val inputStream = process.inputStream.bufferedReader()
            val output = inputStream.readText()
            return output.contains("is stopping") && output.contains("was stopped")
        } catch (e: Exception) {
            e.printStackTrace()
            return false
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
}