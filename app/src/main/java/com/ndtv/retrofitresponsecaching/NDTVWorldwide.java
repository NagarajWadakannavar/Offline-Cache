package com.ndtv.retrofitresponsecaching;

/**
 * Created by NAGARAJ on 1/12/2017.
 */

public class NDTVWorldwide {

    public static void main(String[] args) {

        String[] team = {"Farooque", "Nitesh", "Jay", "Rajan", "Elaa", "Nishant", "Thilak"};
        int size = team.length;
        for (int position = 0; position < size; position++) {
            System.out.println("Thanks " + team[position]);
        }
    }
}
