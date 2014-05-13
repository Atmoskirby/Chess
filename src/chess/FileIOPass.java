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

        p1c = color(piece1.substring(0));
        p2c = color(piece2.substring(0));
            
        
        
        
        piece1 = (piece(piece1.substring(1)));
        piece2 = (piece(piece2.substring(1)));
        if(!command.contains("*")) {
            System.out.println("Command " + command + " = " + p1c +  piece1 + " on " + command.substring(2, 4) + " Moves To " + command.substring(6,command.length()));
        } else {
            System.out.println("Command " + command + " = " + p1c + piece1 + " on " + command.substring(2, 4) + " Moves To " + command.substring(6,command.length() - 1) + " And Takes The " + p2c + piece2 + " At " + command.substring(6, command.length() - 1));
        }
    }
    
    
    public static String piece(String pieceCode) {
        switch(pieceCode) {
                case "K" : return "King";
                case "Q" : return "Queen";
                case "B" : return "Bishop";
                case "N" : return "Knight";
                case "R" : return "Rook";
                case "P" : return "Pawn";
                default : return "Invalid piece";
        }
    }
    
    public static String color(String command) {
        switch(command) {
            case "L" : return "Light";
            case "D" : return "Dark";
            default : return "Neither Dark or Light";
        }
    }
}