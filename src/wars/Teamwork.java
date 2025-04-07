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

        details[4] = "Obuobi";
        details[5] = "Christopher";
        details[6] = "21050029";

        details[7] = "Pang";
        details[8] = "Cheuk Yin Bosca";
        details[9] = "23014216";


        details[10] = "surname of member4";
        details[11] = "first name of member4";
        details[12] = "SRN of member4";

	
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
        
