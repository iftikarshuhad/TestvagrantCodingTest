import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;
public class Testvagrant{
	
	// I have used Map Interface data structure as it as More Functionality ,is Dynamic and is easily usable for data update
	//declared global static map data structure so they can be use throughout the class without passing in methods
    static Map<String,Integer> map1;
	static Map<String,List<Integer>> map2;

	
	//Map 1 conatins the Team name mapped to their Points 
	// it is crearted using constructors to avoid Unecessary methods
	static class Map1
	{
		Map1()
		{
			map1=new HashMap<>();
			map1.put("GT",20);
			map1.put("LSG",18);
			map1.put("RR",16);
			map1.put("DC",14);
			map1.put("RCB",14);
			map1.put("KKR",12);
			map1.put("PBKS",12);
			map1.put("SRH",12);
			map1.put("CSK",8);
			map1.put("MI",6);	
		}
	}
	// Map2 conatins Team names with their respective wins or losses
	// Here 1 is for win and 0 is for loss
	// Dynamic ArrayLists are used so list values can be increased or decreased after new Matches are played or rematches are played
	static class Map2
	{
	     Map2()	
		 {
			List<Integer> n1 = Arrays.asList(1,1,0,0,0);  // predefined list from PDF
			List<Integer> n2 = Arrays.asList(1,0,0,1,1);
			List<Integer> n3 = Arrays.asList(1,0,1,0,0);
			List<Integer> n4 = Arrays.asList(1,1,0,1,0);
			List<Integer> n5 = Arrays.asList(0,1,1,0,0);
			List<Integer> n6 = Arrays.asList(0,1,1,0,1);
			List<Integer> n7 = Arrays.asList(0,1,0,1,0);
			List<Integer> n8 = Arrays.asList(1,0,0,0,0);
			List<Integer> n9 = Arrays.asList(0,0,1,0,1);
			List<Integer> n10 = Arrays.asList(0,1,0,1,1);
			
			map2=new HashMap<String,List<Integer>>();
			map2.put("GT",n1);
			map2.put("LSG",n2);
			map2.put("RR",n3);
			map2.put("DC",n4);
			map2.put("RCB",n5);
			map2.put("KKR",n6);
			map2.put("PBKS",n7);
			map2.put("SRH",n8);
			map2.put("CSK",n9);
			map2.put("MI",n10); 
		 }
	}
    
	
	
	
	
	
	
	
	// BELOW METHODS ARE FOR AVERAGE FINDING -----------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	// This the Method for the AVERAGE points for the teams with Consecutive Wins or Losses
	// it traverse through the List and uses map1 for Points and averages it
	public static float average(List<String> listTeam)
	{
		if(listTeam.size()==0) return 0;
		String []a=new String[listTeam.size()];
		a=listTeam.toArray(a);
		int arr[]=new int[listTeam.size()];
		int sum=0;
		for(int i=0;i<listTeam.size();i++)
		{
			sum+=map1.get(a[i]);
		}
		float average=sum/(listTeam.size());
		
	    return (float)average;
	}
	

	
	
	
	
	
	
	// BELOW TWO METHODS ARE FOR CONSECUTIVE WINS OR LOSSES ------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	//This method is used for searching the Teams with consecutive wins or losses 
	// here K is the consecutive Number 
	// winloss is for Wins or Losses need to calculated by the user
	// It uses map2 for getting the Match details and passes it to the WINLOSS CHECK method for true or false 
	public static List<String> search(int k,int winloss)
	{   
	    //a list will hold the names of the teams
		List<String> teamConse=new ArrayList<String>();
		
		for(Map.Entry<String,List<Integer>> obj:map2.entrySet())
		{    
            //boolean is used here for checking
			if(winlossCheck(obj.getValue(),k,winloss))
			{
				String ss=obj.getKey();
				teamConse.add(ss);
			}
		}
		return teamConse;
    }
	
	// The method is used in the above Method search 
	// returns true or false if the condition satisfies Consecutive Wins or Losses
	static boolean winlossCheck(List<Integer> data,int k,int winloss)
	{
		//convert List to Integer
		Integer []a=new Integer[data.size()];
		a=data.toArray(a);
		int t;

		for(int i=0;i<a.length;i++)
		{
			t=0;
			int flag=0;
		    if(a[i]==winloss)
			{
				int j=0;
				t++;
				for(j=i+1;j<a.length;j++)
				{
					if(a[j]==winloss)
					{
						t++;
					}
					else 
					{
						break;
					}
					if(t==k)
					{
						break;
					}
				}
				i=j; // i is changed to j since this point is already checked
			}
			if(t==k)
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
	
	
	
	// BELOW METHOD IS FOR SORTED MAP AND DISPLAY POINTS TABLE ----------------------------------------------------------------------------------------------------------------------------------
	
	//here Map is sorted using a List based on the Values i.e the Points , and also the match details are displayed 
	public static void showTable()
	{
		   List<Entry<String, Integer>> list = new ArrayList<>(map1.entrySet());
		   list.sort(Entry.comparingByValue());
		   Collections.reverse(list);
		   
		   for(Entry<String,Integer> l:list)
		   {
			   System.out.println(l.getKey()+" "+l.getValue()+" "+map2.get(l.getKey()));
		   }
	}
	
	
	
	
	
	
	
	// BELOW IS THE MAIN METHOD ----------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
        new Map1();
		new Map2();

		//display points table in sorted mannner using both map1 and map2 and Comparator class method
        showTable();

		System.out.println();
		
		
		//TEAMS FOR CONSECUTIVE WINS OR LOSSES
		System.out.println("Please write the number for consecutive wins or losses:");
		
		int k=sc.nextInt(); // for consecutive number
		System.out.println("Please write 0 for loss and 1 for win:");
		
		int winloss=sc.nextInt();  // 1 for win and 0 for loss
		
		//method returns a list conatining the names of filtered teams 
		List<String> teamWinLoss=search(k,winloss);
		for(String str:teamWinLoss)
               System.out.println(str);
		System.out.println();
        
		
		// METHOD OFR AVERAGE OF THE FILTERED TEAMS 
		System.out.println(average(teamWinLoss));

	}
	
} 