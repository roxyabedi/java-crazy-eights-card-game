package com.niantic.models;

import java.util.HashMap;
import java.util.Map;

public class Card
{
    private String suit;
    private String faceValue;

    public Card(String suit, String faceValue)
    {
        this.suit = suit;
        this.faceValue = faceValue;
    }

    public String getSuit()
    {
        return suit;
    }

    public void setSuit(String newSuit)
    {
        this.suit = newSuit;
    }

    public void setFaceValue(String faceValue)
    {
        this.faceValue = faceValue;
    }

    public String getFaceValue()
    {
        return faceValue;
    }
}
