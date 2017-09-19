package com.example.user.anroid_lab_1.CSVReader;

/**
 * Created by User on 19.09.2017.
 */

public class CSV {

    public static String[][] read(String text, int width, int height, char separator){
        String[][] strings = new String[height][width];
        String variable;

        int pointer = 0;
        boolean beginningOfVar = false;

        for(int y = 0; y < height; y++){
            next_variable:for(int x = 0; x < width; x++){
                variable = "";
                for(int i = pointer; i < text.length(); i++){
                    if(!beginningOfVar){
                        if(text.charAt(i) == separator) {
                            beginningOfVar = true;
                        }
                        continue;
                    }
                    else if((beginningOfVar) && (text.charAt(i) == separator)){
                        beginningOfVar = false;
                        strings[y][x] = variable;
                        pointer = (i + 1);
                        continue next_variable;
                    }
                    else if(beginningOfVar){
                        variable += text.charAt(i);
                    }
                }
            }
        }


        return strings;
    }
}
