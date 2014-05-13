/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textBoard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Bryan Schreckengost
 */
public class consoleBoard {
    private static String board[][] = new String[8][8];
    public static void main(String[] args) throws FileNotFoundException, IOException{
        File file = new File("C://tmp/board.txt");
        //[numbers][letters a-h]
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        for(int i = 0; i < 8;i++) {
            for(int o = 0; o < 8;o++) {
                board[i][o] = " - ";
            }
        }
        while(br.ready()) {
            pieces(br.readLine());
        }
        
        for(int i = 0; i < 8;i++) {
            for(int o = 0; o < 8;o++) {
                System.out.print(board[i][o]);
                if(o == 7) {
                    System.out.println();
                }
            }
        }
    }
    
    public static void pieces(String command) {
        command = command.replace(" ", "");
        command = command.toUpperCase();
        String piece1 = command.substring(0,4);
        String piece2 = command.substring(4,command.length());
        String piecenum1 = piece(piece1);
        System.out.println(piece1);
    }
    
    
    public static String piece(String pieceCode) {
        String piece = "";
        
        if(pieceCode.startsWith("K")) {
            piece = "K";
        } else if(pieceCode.startsWith("Q")) {
            piece = "Q";
        } else if(pieceCode.startsWith("B")) {
            piece = "B";
        } else if(pieceCode.startsWith("N")) {
            piece = "N";
        } else if(pieceCode.startsWith("R")) {
            piece = "R";
        } else if(pieceCode.startsWith("P")) {
            piece = "P";
        } else {
            piece = "?";    
        }
        return piece;
    }
}
