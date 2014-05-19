package chess;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class FileIOPass {
    static Pattern pattern1 = Pattern.compile("[KQBNRP][L|D][A-H][1-8]");
    static Pattern pattern2 = Pattern.compile("[KQBNRP][L|D][A-H][1-8]+\\*?");
    static Matcher matcher;
    static Matcher matcher2;
    static Map map = new HashMap();
    public static void main(String[] args) throws IOException {
        String fileLocation = "C://tmp/newfile.txt";
        File file = new File(fileLocation);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        map.put('K', "King");
        map.put('Q', "Queen");
        map.put('B', "Bishop");
        map.put('N', "Knight");
        map.put('R', "Rook");
        map.put('P', "Pawn");
        while(br.ready()) {
            Decipher(br.readLine());
        }
    }
    
    public static void Decipher(String command) {
        command = command.toUpperCase();
        
        String piece[] = new String[command.length()/4];
        for(int i = 0;i < command.length()/4;i++) {
            piece[i] = "";
            for(int o = 0; o < 8;o = o + 4) {
                 piece[i] = command.substring(o, o + 4);
            }
            System.out.println(piece[i]);
        }
        matcher = pattern1.matcher(piece[0]);
        matcher2 = pattern2.matcher(piece[1]);
               
        String p1c[] = new String[2];
        
        if(matcher.matches()&&matcher2.matches()) {
            matcher.matches();
            matcher2.matches();
            p1c[0] = color(matcher.group().charAt(1));
            p1c[1] = color(matcher2.group().charAt(1));
        } else {
            System.out.println("One or more piece color did not match");
        }
        if(matcher.matches()&&matcher2.matches()) {
            matcher.matches();
            matcher2.matches();
            piece[0] = (piece(matcher.group().charAt(0)));
            piece[1] = (piece(matcher2.group().charAt(0)));
        } else {
            System.out.println("One or more of the pieces does not match");
        }
        
        
        if(piece.length == 4) {
            Matcher matcher3 = pattern1.matcher(piece[2]);
            Matcher matcher4 = pattern1.matcher(piece[3]);
            if(matcher3.matches()) {
                matcher3.matches();
                piece[2] = (piece(matcher3.group().charAt(0)));
                p1c[2] = color(matcher3.group().charAt(1));
            }
            if(matcher4.matches()) {
                matcher4.matches();
                piece[3] = piece(matcher4.group().charAt(0));
                p1c[3] = color(matcher4.group().charAt(1));
            }
            
            System.out.println("Command " + command + " = " + p1c[0] +  piece[0] + " on " + command.substring(2, 4) + " Moves To " + command.substring(7,9) + " And the " + p1c[2] + piece[2] + " On " + matcher3.group().substring(2, 4) + " Moves to " + matcher4.group().substring(2, 4));
        } else if(!command.endsWith("*")) {
            System.out.println("Command " + command + " = " + p1c[0] +  piece[0] + " on " + command.substring(2, 4) + " Moves To " + command.substring(7,command.length()));
        } else {
            System.out.println("Command " + command + " = " + p1c[0] + piece[0] + " on " + command.substring(2, 4) + " Moves To " + command.substring(7,command.length() - 1) + " And Takes The " + p1c[1] + piece[1] + " At " + command.substring(command.length() - 3, command.length() - 1));
        }
    }
    
    
    public static String piece(char pieceCode) {
        return map.get(pieceCode).toString();
    }
    
    public static String color(char command) {
        switch(command) {
            case 'L' : return "Light ";
            case 'D' : return "Dark ";
            default : return "Neither Dark or Light ";
        }
    }
}