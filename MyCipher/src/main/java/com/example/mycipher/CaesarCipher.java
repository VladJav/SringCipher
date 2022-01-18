package com.example.mycipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CaesarCipher implements Cipher{

    private final Pattern pattern = Pattern.compile(
            "[" +
                    "\\s" +
                    "\\p{Punct}" +
                    "]" +
                    "*");
    private final Pattern languagePattern = Pattern.compile(
            "[" +
                    "а-яА-ЯёЁ" +
                    "]" +
                    "*");
    private Matcher languageMatcher;

    @Override
    public String encrypt(String text, long s)
    {

        if(checkOnCorect(text,s)){
            return "Ошибка в тексті";
        }

        StringBuffer result= new StringBuffer();

        for (int i=0; i<text.length(); i++)
        {
            languageMatcher = languagePattern.matcher(text.charAt(i)+"");
            if(String.valueOf(text.charAt(i)).equals(" ")){
                result.append(" ");
            }

            else if(languageMatcher.matches()){
                if (Character.isUpperCase(text.charAt(i)))
                {
                    Character ch = (char)(((int)text.charAt(i) + s - 1040) % 32 + 1040);
                    result.append(ch);
                }

                else if(Character.isLowerCase(text.charAt(i)))
                {
                    Character ch = (char)(((int)text.charAt(i) + s - 1072) % 32 + 1072);
                    result.append(ch);
                }
                else{
                    result.append(text.charAt(i));
                }
            }
            else{
                if (Character.isUpperCase(text.charAt(i)))
                {
                    char ch = (char)(((int)text.charAt(i) + s - 65) % 26 + 65);
                    result.append(ch);
                }

                else if(Character.isLowerCase(text.charAt(i)))
                {
                    char ch = (char)(((int)text.charAt(i) + s - 97) % 26 + 97);
                    result.append(ch);
                }
                else{
                    result.append(text.charAt(i));
                }
            }




        }

        return result+"";
    }



    private boolean checkOnCorect(String text, long s){
        Matcher matcher = pattern.matcher(text);

        if(matcher.matches()){
            return true;
        }

        return false;
    }

}