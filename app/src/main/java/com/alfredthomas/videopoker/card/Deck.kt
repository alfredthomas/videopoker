package com.alfredthomas.videopoker.card


class Deck(val jokers:Boolean){
    val deck = arrayListOf<Card>()
    enum class Suit {Clubs,Diamonds,Hearts,Spades}
    enum class Rank {Ace,Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten,Jack,Queen,King,Joker}

    //fallback if no jokers are specified
    constructor():this(false)

    init {
        //add all cards by suit and rank
        for(s in Suit.values())
        {
            for(r in Rank.values())
            {
                //joker can only be added if joker flag set and only twice total
                if(r == Rank.Joker && (!jokers || (s== Suit.Clubs || s == Suit.Diamonds)))
                    continue
                this.deck.add(Card(r,s))
            }
        }
    }

    //shuffle individual deck
    fun shuffle()
    {
        deck.shuffle()
    }

    //decks will contain 52 cards without jokers or 54 with
    fun getSize():Int
    {
        if(jokers)
            return 54
        return 52
    }
}