package data

object WindowsServiceManager {
    private const val service = "autozap.exe"

    fun start() {
        try {
            execute("net start $service")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stop() {
        try {
            execute("net stop $service")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isRunning(): Boolean {
        try {
            val process = execute("sc query $service")
            val inputStream = process.inputStream.bufferedReader()
            val output = inputStream.readText()
            return output.contains("STATE") && output.contains("RUNNING")
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun uninstall() = buildProcess(listOf("./scripts/uninstall.bat")).start()

    fun install() = buildProcess(listOf("./scripts/install.bat")).start()

    fun auth() {
        buildProcess(listOf("./scripts/auth.bat")).start()
    }

    private fun buildProcess(command: List<String>): ProcessBuilder {
        return ProcessBuilder(command)
    }

    private fun execute(command: String): Process {
        return Runtime.getRuntime().exec(command)
    }
}