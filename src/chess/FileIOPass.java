package chess;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class FileIOPass {

    static Pattern pattern = Pattern.compile("[KQBNRP][L|D][A-H][1-8]");
    static Map map = new HashMap();

    public static void main(String[] args) throws IOException {
        
        String fileLocation = args[0];
        File file = new File(fileLocation);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        
        map.put('K', "King");
        map.put('Q', "Queen");
        map.put('B', "Bishop");
        map.put('N', "Knight");
        map.put('R', "Rook");
        map.put('P', "Pawn");
        
        while (br.ready()) {
            Decipher(br.readLine());
        }
    }

    public static void Decipher(String command) {
        command = command.toUpperCase();

        String piece[] = new String[command.length() / 4];
        String pieceColor[] = new String[2];
        
        piece[0] = command.substring(0, 4);
        piece[1] = command.substring(4, 8);

        Matcher piece1Match = pattern.matcher(piece[0]);
        Matcher piece2Match = pattern.matcher(piece[1]);



        if (piece1Match.matches() && piece2Match.matches()) {
            pieceColor[0] = color(piece1Match.group().charAt(1));
            pieceColor[1] = color(piece2Match.group().charAt(1));
            piece[0] = (piece(piece1Match.group().charAt(0)));
            piece[1] = (piece(piece2Match.group().charAt(0)));
        } else {
            System.out.println("One or more of the pieces does not match for command : " + command);
        }

        if (piece.length == 4) {
            
            piece[2] = command.substring(8, 12);
            piece[3] = command.substring(12, 16);
            
            Matcher piece3Match = pattern.matcher(piece[2]);
            Matcher piece4Match = pattern.matcher(piece[3]);
            
            if (piece3Match.matches() && piece4Match.matches()) {
                
                piece[2] = (piece(piece3Match.group().charAt(0)));
                pieceColor[1] = color(piece3Match.group().charAt(1));
                piece[3] = piece(piece4Match.group().charAt(0));
                
                System.out.println("Command " + command + " = " + pieceColor[0] + piece[0] + " on " + piece1Match.group().substring(2, 4) + " Moves To " + piece2Match.group().substring(2, 4) + " And the " + pieceColor[1] + piece[2] + " On " + piece3Match.group().substring(2, 4) + " Moves to " + piece4Match.group().substring(2, 4));
            } else {
                System.out.println("One or more of the pieces does not match for command : " + command);
            }
        } else if (command.contains("*")) {
            System.out.println("Command " + command + " = " + pieceColor[0] + piece[0] + " on " + piece1Match.group().substring(2, 4) + " Moves To " + piece2Match.group().substring(2, 4) + " And Takes The " + pieceColor[1] + piece[1] + " At " + piece2Match.group().substring(2, 4));
        } else {
            System.out.println("Command " + command + " = " + pieceColor[0] + piece[0] + " on " + piece1Match.group().substring(2, 4) + " Moves To " + piece2Match.group().substring(2, 4));
        }
    }

    public static String piece(char pieceCode) {
        return map.get(pieceCode).toString();
    }

    public static String color(char color) {
        switch (color) {
            case 'L':
                return "Light ";
            case 'D':
                return "Dark ";
            default:
                return " Invalid Color Code ";
        }
    }
}
