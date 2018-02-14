import java.io.*;
import java.util.*;

public class findAnagrams{
    public static List<String> getAnagrams(String s,String filename){
        List<String> list = new ArrayList<String>();
        if(s.length() == 0) return list;
        try{
            BufferedReader in = new BufferedReader(new FileReader(filename));
            char[] word = s.toCharArray();
            Arrays.sort(word);
            String line = null;
            while((line = in.readLine())!= null){
                if(line.length()!= s.length()) continue;
                char[] cLine = line.toCharArray();
                Arrays.sort(cLine);
                if(String.valueOf(word).equalsIgnoreCase(String.valueOf(cLine)))
                    list.add(line);
            }
        }catch(FileNotFoundException e){
            System.out.println("No File Found.!");
            return list;
        }
        catch(IOException e){
                return list;
        }
        return list;
    }
    public static void printList(List<String> list){
        for(int i =0; i < list.size()-1;i++)
            System.out.print(list.get(i)+",");
        System.out.println(list.get(list.size()-1));
    }
    public static void main(String[] args){
        if(args.length < 1)
            System.out.println("Invalid Arguments!");

        else{
			System.out.println("Welcome to the Anagram Finder");
			System.out.println("-----------------------------");
            Scanner in = new Scanner(System.in);
			while(true){
			System.out.print("AnagramFinder>");
			String word = in.nextLine();
			if(word.equals("exit")){
				break;
			}
			List<String> list = new ArrayList<>();
			list = getAnagrams(word,args[0]);
            if(list.size()==0)
                System.out.println("No Anagarams found for "+word);
            else
                printList(list);
			}
			in.close();
        }
    }
}
