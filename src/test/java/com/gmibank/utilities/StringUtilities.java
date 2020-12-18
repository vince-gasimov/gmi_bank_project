package com.gmibank.utilities;

public class StringUtilities {

    public static int getIndexOfString(String innerText, String outerText){
        return outerText.indexOf(innerText);
    }

    public static String getInnerStringBetweenIndexes(String text, int startIndex, int endIndex){
        return text.substring(startIndex,endIndex);
    }

    public static String getInnerStringBetweenTwoText(String mainText, String firstPart, String secondPart){
        int startIndex = getIndexOfString(firstPart, mainText) + firstPart.length();
        int endIndex = getIndexOfString(secondPart, mainText);
        return getInnerStringBetweenIndexes(mainText, startIndex, endIndex).trim();
    }

}
