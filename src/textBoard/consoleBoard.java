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
import java.util.HashMap;
import java.util.Map;

public class consoleBoard {
    private static String board[][] = new String[9][9];
    static Map map = new HashMap();
    static String piece1;
    static String piece2;
    public static void main(String[] args) throws IOException{
        File file = new File("C://tmp/board.txt");
        //[numbers][letters a-h]
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        //-64
        map.put('K', "K");
        map.put('Q', "Q");
        map.put('B', "B");
        map.put('N', "N");
        map.put('R', "R");
        map.put('P', "P");
        printBoard(br);
        printPieces();
        
    }
    
    public static void pieces(String command) {
        command = command.toUpperCase();
        piece1 = command.substring(0,4);
        piece2 = command.substring(4,8);
        String piecenum1 = piece(piece1.charAt(0));
    }
    
    
    public static String piece(char pieceCode) {
        return (String) map.get(pieceCode);
    }
    
    public static void printBoard(BufferedReader br) throws IOException {
        for(int i = 0; i < 9;i++) {
            for(int o = 0; o < 9;o++) {
                char top = 65;
                if(o == 0) {
                    board[o][i] = " " + Character.toString((char) (top + (i - 1))) + " ";
                    board[i][o] = "" + i;
                } else {
                     board[i][o] = " - ";
                }
            }
        }
        board[0][0] = "#";
        while(br.ready()) {
            pieces(br.readLine());
        }
        
        for(int i = 0; i < 9;i++) {
            for(int o = 0; o < 9;o++) {
                System.out.print(board[i][o]);
                if(o == 8) {
                    System.out.println();
                }
            }
        }
        //Move pieces
        
    }
    
    public static void printPieces() {
        
    }
}
