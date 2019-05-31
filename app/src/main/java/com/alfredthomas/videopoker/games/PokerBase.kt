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
        if(isRoyal(hand))
            return result.RoyalFlush


            return result.Nothing
    }
    fun isRoyal(hand: ArrayList<Card>):Boolean
    {
        if(isSameSuit(hand))
            return true
        return false
    }

    fun isSameSuit(hand: ArrayList<Card>):Boolean
    {
        var suit = hand[0].suit
        for(i in 1 until hand.size)
        {
            if(suit!= hand[i].suit)
                return false
        }
        return true
    }
    fun isStraightFlush(hand: ArrayList<Card>):Boolean
    {
        if(isSameSuit(hand) && isStraight(hand))
            return true
        return false
    }
    fun isStraight(hand: ArrayList<Card>):Boolean
    {
        val sorted = getSortedHand(hand)
        for (i in sorted.size-1 downTo 0)
        {

        }
        return false
    }
    fun getSortedHand(hand: ArrayList<Card>):ArrayList<Card>
    {
        val sorted = arrayListOf<Card>()
        sorted.addAll(hand)

        sorted.sortWith(compareBy{it.rank})

        return sorted
    }

}