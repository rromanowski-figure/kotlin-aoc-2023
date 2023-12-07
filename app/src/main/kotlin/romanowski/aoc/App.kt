package romanowski.aoc

fun main(args: Array<String>) {
    val date = getDateOrToday(args)

    val day = Day.ofDate(date)
    val input = getResourceAsInput(date)

    printTitle("Day $date - ${day.title}")
    println("Part 1: ${day.part1(input)}")
    println("Part 2: ${day.part2(input)}")
}

fun printTitle(title: String) {
    val t = "--- $title ---"
    val hBorder = "-".repeat(t.length)

    println("\n$hBorder\n$t\n$hBorder\n")
}