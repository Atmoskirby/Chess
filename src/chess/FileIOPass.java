package chess;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;

public class FileIOPass {

    Pattern pattern = Pattern.compile("([KkQqBbNnRrPp])([LlDd])([a-gA-G])([1-8])");
    Pattern take = Pattern.compile("(.{2})(.{2})(\\*)");
    Pattern doubleMovement = Pattern.compile("([a-gA-G][1-8])([a-gA-G][1-8])([a-gA-G][1-8])([a-gA-G][1-8])");
    Pattern pattern2 = Pattern.compile("([a-gA-G][1-8])([a-gA-G][1-8])");
    int commands = 0;
    private Map map = new HashMap();
    
    public static void main(String[] args) throws IOException {
        FileIOPass p = new FileIOPass();
        if(args.length != 0)
        {
            p.run(args[0]);
        }
        else
        {
            p.run(null);
        }
    }
    
    private void run(String fileL) throws IOException
    {
        map.put('k', "King");
        map.put('q', "Queen");
        map.put('b', "Bishop");
        map.put('n', "Knight");
        map.put('r', "Rook");
        map.put('p', "Pawn");
        List<String> pieces = new ArrayList<String>();
        
        if(fileL != null)
        {
            String fileLocation = fileL;
            File file = new File(fileLocation);
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            while (br.ready()) {
                Decipher(br.readLine());
            }
        }
        else
        {
            System.out.println("No File input detected relying on user input now");
            System.out.println("Please Type in commands and leave it blank when you are done");
            Scanner input = new Scanner(System.in);
            String in = "";
            boolean proper = true;
            while(proper)
            {
                in = input.nextLine();
                if(in.isEmpty())
                {
                    
                }
                else
                {
                    pieces.add(in);
                }
                if(in.equals(""))
                {
                    proper = false;
                    break;
                }
            }
            for(String p : pieces)
            {
                Decipher(p);
            }
            
        }
    }
    
    public void Decipher(String command) {

        Matcher piece1Match = pattern.matcher(command);
        Matcher piecesMatch2 = pattern2.matcher(command);
        
        if(command.length() >= 8)
        {
            Matcher doubleMather = doubleMovement.matcher(command);
            if(doubleMather.find())
            {              
                
                System.out.println("The piece at " + doubleMather.group(1) + " Moves to " + doubleMather.group(2) + " and the piece at " + doubleMather.group(3) + " Moves to " + doubleMather.group(4));
            }
            commands++;
        }
        else
        {
            if(piece1Match.find())
            {
                System.out.println("The " + color(piece1Match.group(2)) + piece(piece1Match.group(1)) + " Moves to " + piece1Match.group(3) + piece1Match.group(4));
                commands++;
            }   
            else if(piecesMatch2.find())
            {
                commands++;
                Matcher taking = take.matcher(command);
                if(taking.find())
                {
                    System.out.println("The " + piecesMatch2.group(1) + " Moves and takes the piece at " + taking.group(2));      
                }
                else
                {
                    System.out.println("The Piece at " + piecesMatch2.group(1) + " Moves To " + piecesMatch2.group(2));
                }
                
            }
            else
            {
                System.out.println("Invalid Command " + command);
            }
        }
    }

    public String piece(String pie) {
        char pieceCode = pie.charAt(0);
        if(map.get(pieceCode) == null)
        {
            return "Invalid Piece";
        }
        return map.get(pieceCode).toString();
    }

    public static String color(String col) {
        char color = col.charAt(0);
        switch (color) {
            case 'l':
                return "Light ";
            case 'd':
                return "Dark ";
            default:
                return " Invalid Color Code ";
        }
    }      
}
