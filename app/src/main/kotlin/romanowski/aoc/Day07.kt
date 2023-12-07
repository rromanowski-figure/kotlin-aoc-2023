package romanowski.aoc

class Day07 : Day() {
    override val title: String = "Camel Cards"
    override val part1TestValue: Int = 6440
    override val part2TestValue: Int = 5905

    enum class HandType(val order: Int, private val withJokers: List<HandType>) {
        FIVE_OF_A_KIND(6, emptyList()),
        FOUR_OF_A_KIND(5, listOf(FIVE_OF_A_KIND)),
        FULL_HOUSE(4, emptyList()),
        THREE_OF_A_KIND(3, listOf(FOUR_OF_A_KIND, FIVE_OF_A_KIND)),
        TWO_PAIR(2, listOf(FULL_HOUSE)),
        ONE_PAIR(1, listOf(THREE_OF_A_KIND, FOUR_OF_A_KIND, FIVE_OF_A_KIND)),
        HIGH_CARD(0, listOf(ONE_PAIR, THREE_OF_A_KIND, FOUR_OF_A_KIND, FIVE_OF_A_KIND));

        fun plusJokers(jokerCount: Int): HandType {
            if (jokerCount == 0) return this
            if (jokerCount == 5) return FIVE_OF_A_KIND

            return this.withJokers[jokerCount - 1]
        }
    }

    data class Hand(val cards: String, val bid: Int, val withJokers: Boolean = false) : Comparable<Hand> {
        val type: HandType by lazy {
            val jokerCount = if (withJokers) cards.count { it == 'J' } else 0

            val cardCounts = cards.toCharArray()
                .filter { !withJokers || it != 'J' }
                .groupBy { it }
                .mapValues { it.value.count() }

            when {
                cardCounts.values.contains(5) -> HandType.FIVE_OF_A_KIND
                cardCounts.values.contains(4) -> HandType.FOUR_OF_A_KIND
                cardCounts.values.contains(3) -> when {
                    cardCounts.values.contains(2) -> HandType.FULL_HOUSE
                    else -> HandType.THREE_OF_A_KIND
                }

                cardCounts.values.count { it == 2 } == 2 -> HandType.TWO_PAIR
                cardCounts.values.contains(2) -> HandType.ONE_PAIR
                cardCounts.values.contains(1) -> HandType.HIGH_CARD
                else -> /* Only Jokers*/ HandType.FIVE_OF_A_KIND
            }.let { it.plusJokers(jokerCount) }
        }

        override fun compareTo(other: Hand): Int {
            val typeRank = this.type.order - other.type.order
            if (typeRank != 0) return typeRank

            this.cards.toCharArray().map { it.toCardValue(withJokers) }
                .zip(other.cards.toCharArray().map { it.toCardValue(withJokers) }).forEach {(t, o) ->
                if (t - o != 0) return t - o
            }

            return 0
        }

        override fun toString(): String {
            return "$cards ($type) for $bid"
        }

        private fun Char.toCardValue(withJokers: Boolean) = when(this) {
            'A' -> 14
            'K' -> 13
            'Q' -> 12
            'J' -> if (withJokers) 1 else 11
            'T' -> 10
            else -> this.toString().toInt()
        }
    }

    override fun part1(input: List<String>): Int {
        val sortedHands: List<Hand> = input.map { line ->
            val parts = line.split(" ")

            Hand(parts.first().trim(), parts.last().toInt())
        }.sorted()

        return sortedHands.foldIndexed(0) { i, score, h -> score + (i+1) * h.bid }
    }

    override fun part2(input: List<String>): Int {
        val sortedHands: List<Hand> = input.map { line ->
            val parts = line.split(" ")

            Hand(parts.first().trim(), parts.last().toInt(), withJokers = true)
        }.sorted().also {
            println(it.joinToString("\n"))
        }

        return sortedHands.foldIndexed(0) { i, score, h -> score + (i+1) * h.bid }
    }
}