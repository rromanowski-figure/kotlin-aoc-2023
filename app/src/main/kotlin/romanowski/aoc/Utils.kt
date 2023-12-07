package romanowski.aoc

import java.time.LocalDate
import kotlin.io.path.Path
import kotlin.io.path.readLines

fun getResourceAsInput(path: String): List<String> {
    val resource = object {}.javaClass.getClassLoader().getResource("day-$path.txt")
        ?: error("input for $path doesn't exist")

    return Path(resource.path).readLines()
}

fun getDateOrToday(args: Array<String>) = (args.getOrNull(0)
    ?: LocalDate.now().dayOfMonth.toString().padStart(2, '0'))