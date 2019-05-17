package com.alfredthomas.videopoker.card

class Shoe (val decks:Int, val jokers: Boolean)
{
    val shoe = arrayListOf<Card>()
    var cardPointer = 0
    init {
        val deck = Deck(jokers).deck
        for(i in 0..decks)
        {
            shoe.addAll(deck)
        }
        shuffle()

    }

    fun deal():Card
    {
        //reset index for now so it doesn't go out of bounds
        if(cardPointer>= shoe.size)
            cardPointer =0

        cardPointer++
        return shoe[cardPointer-1]
    }

    fun shuffle()
    {
        shoe.shuffle()
        cardPointer = 0
    }

}