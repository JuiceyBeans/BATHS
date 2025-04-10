package wars; 


/**
 * Details of your team
 * 
 * @author CS26
 * @version 25/03/2025
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "CS26";
        
        details[1] = "Ayush (Karthi) Bijitha Suresh";
        details[2] = "Z";
        details[3] = "23012077";

        details[4] = "Leyton Mpanga";
        details[5] = "Samuel";
        details[6] = "22204531";

        details[7] = "Obuobi";
        details[8] = "Christopher";
        details[9] = "21050029";


        details[10] = "Pang";
        details[11] = "Cheuk Yin Bosca";
        details[12] = "23014216";

	
	   // only if applicable
        details[13] = "surname of member5";
        details[14] = "first name of member5";
        details[15] = "SRN of member5";


    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
