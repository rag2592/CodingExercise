import java.io.*;
import java.util.*;

public class findAnagrams{
	static HashMap<String,List<String>> map = new HashMap<>();
	public static void loadDictionary(String filename)throws FileNotFoundException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = null;
		try{
			while((line = br.readLine())!=null){
				char[] lArray = line.toLowerCase().toCharArray();
				Arrays.sort(lArray);
				String key = String.valueOf(lArray);
				if(!map.containsKey(key))
					map.put(key,new ArrayList<String>());
				map.get(key).add(line);
			}
		}catch(IOException e){
			System.out.println("Failed.!");
		}
	}
    public static List<String> getAnagrams(String word){
		if(word.length() == 0) return new ArrayList<String>();
		char[] wArray = word.toLowerCase().toCharArray();
		Arrays.sort(wArray);
		if(!map.containsKey(String.valueOf(wArray)))
	      	return new ArrayList<String>();
		else
			return map.get(String.valueOf(wArray));
	}
    public static void printList(List<String> list,String word){
        for(int i =0; i < list.size()-1;i++){
				System.out.print(list.get(i)+",");
		}
		System.out.println(list.get(list.size()-1));
    }
	public static void generateCommandLine(String filename) throws Exception{
		long start = 0,end = 0;
		try{
			System.out.println("Welcome to the Anagram Finder");
			System.out.println("-----------------------------");
			start = System.nanoTime();
			loadDictionary(filename);
			end = System.nanoTime();
			System.out.println("Dictionary loaded in "+((end-start)/1000000) + " ms");
			Scanner in = new Scanner(System.in);
			while(true){
				System.out.print("AnagramFinder>");
				String word = in.nextLine();
				if(word.equals("exit")){
					break;
				}
				List<String> list = new ArrayList<>();
				start = System.nanoTime();
				list = getAnagrams(word);
				end = System.nanoTime();
				
				if((list.size()== 1 && list.get(0).equals(word)) || list.size()<1)
						System.out.println("No Anagarams found for "+word+" in "+ ((end-start)/1000000) + " ms");
				else{
						System.out.println(list.size()+" Anagrams found for "+word+" in "+ ((end-start)/1000000) + " ms" );
						printList(list,word);
					}
            }
			in.close();
		}catch (FileNotFoundException e){
			System.out.println("No File Found");
			return;
		}	
		
	}
    public static void main(String[] args) throws Exception{
        if(args.length < 1)
		{
			System.out.println("Invalid Arguments!");
			return;
		}
		generateCommandLine(args[0]);
    }
}

