package chess;
import java.io.*;
import java.util.regex.*;

public class FileIOPass {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileLocation = "C://tmp/newfile.txt";
        File file = new File(fileLocation);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        while(br.ready()) {
            Decipher(br.readLine());
        }
    }
    
    public static void Decipher(String command) {
        command = command.toUpperCase();
        Pattern com = Pattern.compile("[KQBNRP][LD][A-H][1-8]");
        
       
        String piece[] = command.split(" ").clone();
        Matcher matcher = com.matcher(piece[0]);
        String p1c[] = new String[2];
 
        p1c[0] = color(Character.toString(piece[0].charAt(1)));
        p1c[1] = color(Character.toString(piece[1].charAt(1)));
        
        piece[0] = (piece(Character.toString(piece[0].charAt(0))));
        piece[1] = (piece(Character.toString(piece[1].charAt(0))));
        
        System.out.println();
        
        if(!command.endsWith("*")) {
            System.out.println("Command " + command + " = " + p1c[0] +  piece[0] + " on " + piece[0].substring(2, 4) + " Moves To " + command.substring(7,command.length()));
        } else {
            System.out.println("Command " + command + " = " + p1c[0] + piece[0] + " on " + piece[0].substring(2, 4) + " Moves To " + command.substring(7,command.length() - 1) + " And Takes The " + p1c[1] + piece[1] + " At " + command.substring(command.length() - 3, command.length() - 1));
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
            case "L" : return "Light ";
            case "D" : return "Dark ";
            default : return "Neither Dark or Light ";
        }
    }
}