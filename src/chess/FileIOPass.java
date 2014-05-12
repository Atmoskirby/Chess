package chess;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileIOPass {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileLocation = args[0];
        File file = new File(fileLocation);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        
        while(br.ready()) {
            Decipher(br.readLine());
        }
    }
    
    public static void Decipher(String command) {
        command = command.replaceAll(" ","");
        command = command.toUpperCase();
        String piece1 = command.substring(0,4);
        String p1c = "";
        String piece2 = command.substring(4, command.length());
        String p2c = "";
        String color;
        boolean Capture = false;

        p1c = color(piece1);
        p2c = color(piece2);
            
        
        
        
        piece1 = (piece(piece1));
        piece2 = (piece(piece2));
        if(!command.contains("*")) {
            System.out.println("Command " + command + " = " + p1c +  piece1 + " on " + command.substring(2, 4) + " Moves To " + command.substring(6,command.length()));
        } else {
            System.out.println("Command " + command + " = " + piece1 + " on " + command.substring(2, 4) + " Moves To " + command.substring(6,command.length() - 1) + " And Takes The " + p2c + piece2 + " At " + piece2.substring(2, 4));
        }
    }
    
    
    public static String piece(String pieceCode) {
        String piece = "";
        
        if(pieceCode.startsWith("K")) {
            piece = "King";
        } else if(pieceCode.startsWith("Q")) {
            piece = "Queen";
        } else if(pieceCode.startsWith("B")) {
            piece = "Bishop";
        } else if(pieceCode.startsWith("N")) {
            piece = "Knight";
        } else if(pieceCode.startsWith("R")) {
            piece = "Rook";
        } else if(pieceCode.startsWith("P")) {
            piece = "Pawn";
        } else {
            piece = "Invalid piece";    
        }
        return piece;
    }
    
    public static String color(String command) {
     if(Character.toString(command.charAt(1)).equalsIgnoreCase("L")) {
            return "Light ";
        } else if(Character.toString(command.charAt(1)).equalsIgnoreCase("D")) {
            return "Dark ";
        }   
     return null;
    }
}