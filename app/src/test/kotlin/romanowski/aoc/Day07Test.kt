package romanowski.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day07Test : FunSpec({
    val day = Day07()
    val input = getResourceAsInput("07-sample")

    test("Part 1") {
        day.part1(input) shouldBe day.part1TestValue
    }

    test("Part 2") {
        day.part2(input) shouldBe day.part2TestValue
    }

    test("Part 2 - Additional Input") {
        val additionalInput = listOf(
            "2345A 1",
            "Q2KJJ 13",
            "Q2Q2Q 19",
            "T3T3J 17",
            "T3Q33 11",
            "2345J 3",
            "J345A 2",
            "32T3K 5",
            "T55J5 29",
            "KK677 7",
            "KTJJT 34",
            "QQQJA 31",
            "JJJJJ 37",
            "JAAAA 43",
            "AAAAJ 59",
            "AAAAA 61",
            "2AAAA 23",
            "2JJJJ 53",
            "JJJJ2 41",
        )
        day.part2(additionalInput) shouldBe 6839
    }
})