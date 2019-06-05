package com.alfredthomas.videopoker.games

import com.alfredthomas.videopoker.card.Card
import com.alfredthomas.videopoker.card.Deck

abstract class PokerBase
{
    enum class result{
        RoyalFlush,StraightFlush,FourOfAKind,FullHouse,Flush,Straight,ThreeOfAKind,TwoPair,Pair,Nothing
    }
    fun checkHand(hand: ArrayList<Card>): result
    {
        /*
            Hand Heirarchy:
                Royal Flush
                Straight Flush
                4 of a Kind
                Full House
                Flush
                Straight
                3 of a Kind
                Two Pair
                Pair
                Nothing
         */
        val sortedHand = getSortedHand(hand)
        if(isRoyalFlush(sortedHand))
            return result.RoyalFlush
        if(isStraightFlush(sortedHand))
            return result.StraightFlush
        if(isFourOfAKind(sortedHand))
            return result.FourOfAKind
        if(isFullHouse(sortedHand))
            return result.FullHouse
        if(isFlush(sortedHand))
            return result.Flush
        if(isStraight(sortedHand))
            return result.Straight
        if(isThreeOfAKind(sortedHand))
            return result.ThreeOfAKind
        if(isTwoPair(sortedHand))
            return result.TwoPair
        if(isPair(sortedHand))
            return result.Pair

            return result.Nothing
    }
    fun isRoyalFlush(hand: ArrayList<Card>):Boolean
    {
        //royal flush shows as A 10 J Q K when sorted
        //simple way to check is check for straight flush, and check beginning and end of straight
        //in this case hand[0] and hand[1]

        return isStraightFlush(hand) && hand[0].rank == Deck.Rank.Ace && hand[1].rank == Deck.Rank.Ten
    }


    fun isStraightFlush(hand: ArrayList<Card>):Boolean
    {
        //straight flush is simply flush + straight
        if(isFlush(hand) && isStraight(hand))
            return true
        return false
    }
    fun isStraight(hand: ArrayList<Card>):Boolean
    {
        //check card by card against previous card
        val firstAce = hand[0].rank == Deck.Rank.Ace
        for (i in 1 until hand.size)
        {
            //if first card is ace, ignore and go to next card
            if(i-1 == 0 && firstAce)
                continue
            if(hand[i].rank.ordinal != hand[i-1].rank.ordinal+1)
                return false
        }
        //if the first card is an ace, only possible straights are A2345 or A10JQK aka 10JQKA
        if(firstAce && hand[1].rank!= Deck.Rank.Two && hand[1].rank!= Deck.Rank.Ten)
            return false

        return true
    }
    fun isFourOfAKind(hand: ArrayList<Card>):Boolean
    {
        //since hand comes in sorted, only possible 4 of a kinds look like ****X or X****
        //simply check hand[0] vs hand[3] and hand[1] vs hand[4]
        if(hand[0].rank==hand[3].rank || hand[1].rank == hand[4].rank)
            return true
        return false
    }
    fun isFullHouse(hand: ArrayList<Card>):Boolean
    {
        //full house contain a 2pair and a 3 of a Kind
        //using this logic, a full house can only look like AAAKK or AAKKK
        //therefore check first 2 for pair, last three for triple and the opposite

        if((pairCheck(hand,0,2)&&tripleCheck(hand,2,hand.size))||(tripleCheck(hand,0,3) && pairCheck(hand,3,hand.size)))
            return true
        return false
    }
    fun pairCheck(hand: ArrayList<Card>, start:Int, end:Int):Boolean
    {
        //since the deck is sorted, compare each card against the previous
        for(num in start+1 until end)
        {
            if(hand[num].rank == hand[num-1].rank)
                return true
        }
        return false
    }
    fun tripleCheck(hand: ArrayList<Card>, start:Int, end:Int):Boolean
    {
        //since the deck is sorted, go through from start to end, looking for count == 3
        var count = 1
        for(num in start+1 until end)
        {
            if(hand[num].rank == hand[num-1].rank)
                count++
            //reset count for different card found
            else
                count=1

            if(count==3)
                return true
        }
        return false
    }
    fun isFlush(hand: ArrayList<Card>):Boolean
    {
        //loop through and check all cards suits against first
        var suit = hand[0].suit
        for(i in 1 until hand.size)
        {
            if(suit!= hand[i].suit)
                return false
        }
        return true
    }
    fun isThreeOfAKind(hand: ArrayList<Card>):Boolean
    {
        return tripleCheck(hand,0,hand.size)
    }
    fun isTwoPair(hand: ArrayList<Card>):Boolean
    {
        //only ways to have 2 pair are JQQKK JJQQK JJQKK in a 5 card hand
        //so we can check first two and last 3, and first 3 and last two

        return(pairCheck(hand,0,2)&&pairCheck(hand,2,hand.size))||(pairCheck(hand,0,3)&&pairCheck(hand,3,hand.size))
    }
    fun isPair(hand: ArrayList<Card>):Boolean
    {
        //already have pair check, so just pass in entire range
        return pairCheck(hand,0,hand.size)
    }
    fun getSortedHand(hand: ArrayList<Card>):ArrayList<Card>
    {
        //returns sorted copy of hand by ranks
        val sorted = arrayListOf<Card>()
        sorted.addAll(hand)
        //using rank to compare
        sorted.sortWith(compareBy{it.rank})

        return sorted
    }

}