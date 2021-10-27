package com.giovann.mymemorysecond.models

import com.giovann.mymemorysecond.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize: BoardSize, private val customImages: List<String>?) {
    val cards: List<MemoryCard>
    var numPairsFound: Int = 0
    var indexOfSingleSelectedCard: Int? = null
    var numMoves = 0

    init {
        if (customImages == null) {
            val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
            val randomizedImages = (chosenImages + chosenImages).shuffled()
            cards = randomizedImages.map { MemoryCard(it) }
        }
        else {
            val randomizedImages = (customImages + customImages).shuffled()
            cards = randomizedImages.map { MemoryCard(it.hashCode(), it) }
        }
    }

    fun flipCard(position: Int): Boolean {
        val card = cards[position]
        var foundMatch: Boolean = false

        if (indexOfSingleSelectedCard == null) {
            //0 or 2 card -> reset + FLIP + save index pos
            resetBoard()
            indexOfSingleSelectedCard = position
        }
        else {
            foundMatch = checkForMatch(position, indexOfSingleSelectedCard!!)
            indexOfSingleSelectedCard = null
            numMoves++
        }

        //1 card -> Check identifier + Flip + numpairsfound + ismatched
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if (cards[position1].identifier == cards[position2].identifier) {
            cards[position1].isMatched = true
            cards[position2].isMatched = true
            numPairsFound++
            return true
        }
        return false
    }

    private fun resetBoard() {
        for (each in cards) {
            if (!each.isMatched) {
                each.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }
}
