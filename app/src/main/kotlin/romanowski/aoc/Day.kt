package romanowski.aoc

sealed class Day {
    abstract val title: String
    abstract val part1TestValue: Int
    abstract val part2TestValue: Int
    abstract fun part1(input: List<String>): Int
    abstract fun part2(input: List<String>): Int

    companion object {
        fun ofDate(date: String) : Day {
            return mapOf<String, Day>(
                "07" to Day07()
            )[date] ?: error("Missing day $date!")
        }
    }
}