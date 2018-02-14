import java.io.*;
import java.util.*;

public class findAnagrams{
    public static List<String> getAnagrams(String s,BufferedReader in)throws IOException{
        List<String> list = new ArrayList<String>();
        if(s.length() == 0) return list;
        char[] word = s.toCharArray();
        Arrays.sort(word);
        String line = null;
        while((line = in.readLine())!= null){
            if(line.length()!= s.length()) continue;
            char[] cLine = line.toCharArray();
            Arrays.sort(cLine);
            if(String.valueOf(word).equalsIgnoreCase(String.valueOf(cLine)) && !line.equals(s))
               list.add(line);
           }
		in.close();
        return list;
    }
    public static void printList(List<String> list){
        for(int i =0; i < list.size()-1;i++)
            System.out.print(list.get(i)+",");
        System.out.println(list.get(list.size()-1));
    }
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length < 1)
		{
			System.out.println("Invalid Arguments!");
			return;
		}
		long start = 0, end = 0;
		try{
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
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
				start = System.nanoTime();
				try{
					br = new BufferedReader(new FileReader(args[0])); 
					list = getAnagrams(word,br);
					br.close();
				}catch(IOException e){
					continue;
				}
				end = System.nanoTime();
				if(list.size()<1)
						System.out.println("No Anagarams found for "+word+" in "+ ((end-start)/1000000) + " ms");
				else{
						System.out.println(list.size()+" Anagrams found for "+word+" in "+ ((end-start)/1000000) + " ms" );
						printList(list);
					}
            }
			in.close();
		}catch (FileNotFoundException e){
			System.out.println("No File Found");
			return;
		}
    }
}

