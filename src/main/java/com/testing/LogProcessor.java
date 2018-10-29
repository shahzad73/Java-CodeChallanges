/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testing;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;



public class LogProcessor {
    
	public static void main(String[] args) throws FileNotFoundException, ParseException {
            
            HashMap<String, HashSet<String>> IPUserSessions = new HashMap<String, HashSet<String>>();
            
            HashMap<String, UserSessions> UserServerSessions = new HashMap<String, UserSessions>();
            
            HashMap<String, HashMap<String, String>>  CurrentUserSessionsOnServer = new HashMap<String, HashMap<String, String>>();
            HashMap<String, ServerSessions> ServerSessionsAccumulator = new HashMap<String, ServerSessions>();
            
            
            Scanner scan = new Scanner(new File("d://useractivitylog.txt"));
            while(scan.hasNextLine())
            {
                String line = scan.nextLine();
                String[] parts = line.split(",");
                
                String TimeOfEvent = parts[0].trim();
                String EventType = parts[1].trim();
                String IPAddress = parts[2].trim();
                String User = parts[3].trim();

                /*
                        Note that after splitting a single record 
                        21:00:00, LOGIN, 10.0.0.1, user1
                        
                        the parts array contain following 
                        parts[0]    Time of event
                        parts[1]    Event type
                        parts[2]    IP 
                        parts[3]    User
                */
                
                
                //This part of the program is managing unique sessions of a server
                if (EventType.equals(new String("LOGIN")))
                {
                    HashSet set = IPUserSessions.get(IPAddress);
                    if(set == null)
                    {
                        //this player does not have an entry yet
                        //so let's add one!
                        set = new HashSet<String>();
                        IPUserSessions.put(IPAddress, set);
                    }
                    set.add(User);
                }
                
                
                
                //This part of the program is managing and tracking highest number of sessions at one time for a particular user
                if (EventType.equals(new String("LOGIN")))
                {
                    UserSessions us = UserServerSessions.get(User);
                    if(us == null)
                    {
                        us = new UserSessions();
                        UserServerSessions.put(User, us);
                    }
                    else
                        us.incrementCurrentSessions();
                }
                if (EventType.equals(new String("LOGOUT")))
                {
                    UserSessions us = UserServerSessions.get(User);
                    us.decrementCurrentSessionCount();
                }
                
                
                
                //this part of the program is managing average number of sessions on each server
                if (EventType.equals(new String("LOGIN")))
                {
                    HashMap map2 = CurrentUserSessionsOnServer.get(IPAddress); 
                    if(map2 == null)
                    {
                        map2 = new HashMap<String, String>();
                        CurrentUserSessionsOnServer.put(IPAddress, map2);
                    }
                    map2.put(User, TimeOfEvent);
                }
                if (EventType.equals(new String("LOGOUT")))
                {
                    HashMap map3 = CurrentUserSessionsOnServer.get(IPAddress);
                    String s = (String)map3.get(User);
                    //System.out.println("Logout even for user " + User + " " + s + TimeOfEvent);
                    
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); 
                    Date d1 = format.parse(s);
                    Date d2 =  format.parse(TimeOfEvent);
                    
                    long diff = d2.getTime() - d1.getTime();
                    long secs = diff/1000;
                    
                    ServerSessions sss = (ServerSessions)ServerSessionsAccumulator.get(IPAddress);
                    if(sss == null)
                    {
                        sss = new ServerSessions(secs, 1); 
                        ServerSessionsAccumulator.put(IPAddress, sss );
                    }
                    else
                        sss.IncrementServerSessionInformation(secs);
                }                
                
            }               
            
            
            
            
            
            
            String HighestSessionsIP = "";
            int NoOfSessions = 0;
            for (Map.Entry<String, HashSet<String>> entry : IPUserSessions.entrySet()) {
                String key = entry.getKey();
                HashSet value = (HashSet)entry.getValue();
                
                //System.err.println(key + " " + value.size());
                
                if(NoOfSessions < value.size())
                {
                    HighestSessionsIP = key;
                    NoOfSessions = value.size();
                }
            }            
            System.out.print("Highest number of distinct sessions on IP " + HighestSessionsIP + " with " + NoOfSessions + " distinct sessions\n\n");
            
            
           String HighestNumberOfSessionOfUserAtOneTime = "";
           int HighestNumberOfSessionRecordedAtOneTime = 0;
           for (Map.Entry<String, UserSessions> entry : UserServerSessions.entrySet()) {
                String key = entry.getKey();
                UserSessions value = (UserSessions)entry.getValue();
                
                //System.err.println(key + " " + value.getHighestSessionCount());
                if(HighestNumberOfSessionRecordedAtOneTime < value.getHighestSessionCount())
                {
                    HighestNumberOfSessionRecordedAtOneTime = value.getHighestSessionCount();
                    HighestNumberOfSessionOfUserAtOneTime = key;
                }
            }               
            System.out.println("Highest number of session of a user at one time were " + HighestNumberOfSessionRecordedAtOneTime + " of the user " +  HighestNumberOfSessionOfUserAtOneTime + "\n\n");
           
           
           long LongestAverageSessions = 0;
           String LongestAverageSessionOfServer = "";
           for (Map.Entry<String, ServerSessions> entry : ServerSessionsAccumulator.entrySet()) {
                String key = entry.getKey();
                ServerSessions value = (ServerSessions)entry.getValue();
                
                //System.out.println(key + " " + value.getAverageSessionTimeOnServer());
                if(LongestAverageSessions < value.getAverageSessionTimeOnServer())
                {
                    LongestAverageSessions = value.getAverageSessionTimeOnServer();
                    LongestAverageSessionOfServer = key;
                }
            }
           System.out.println("Longest average session was recorded for server " + LongestAverageSessionOfServer + " and average was " +  LongestAverageSessions);
                   
            
        }    
    
        
        
        
        
}













//This object is being used to track highest sessions by a particular user
class UserSessions {
    
    private int _CurrentSessions;
    private int _TopSessionCount;
    
    
    public UserSessions()
    {
        _CurrentSessions = 1; 
        _TopSessionCount = 1;        
    }
    
    public void incrementCurrentSessions()
    {
        _CurrentSessions++;
        if(_CurrentSessions > _TopSessionCount)
            _TopSessionCount = _CurrentSessions;
    }
    
    public void decrementCurrentSessionCount()
    {
        _CurrentSessions--;
    }
    
    public int getHighestSessionCount()
    {
        return _TopSessionCount;
    }
}


//This object is being used to track sessions in minutes on each server
class ServerSessions
{
   private long _seconds;
   private long _sessions;
   
   public ServerSessions(long sec, long ses)
   {
       _seconds = sec;
       _sessions = ses;
   }
   
   public void IncrementServerSessionInformation(long secs)
   {
       _seconds = _seconds + secs;
       _sessions++;       
   }
   
   public long getAverageSessionTimeOnServer()
   {
       return _seconds/_sessions;
   }
}





/*

Hi Shahzad,

Thank you for taking the time to do this design and coding test. You have a full 24 hours to do it.
The aim is to give us an idea of your sense of system architecture and also what your production quality code looks like.
Your task is to write an application for analyzing events from log file.
The log file can contain various different event types out of which we are only interested in two: user login and user logout.
Events are separated by new lines and have following comma separated format:
[time: HH:mm:ss], [type: LOGIN/LOGOUT], [ip: IPv4], [user: alpha-numeric string]
Example log:
 21:00:00, LOGIN, 10.0.0.1, user1
 21:02:00, LOGIN, 10.0.0.5, user1
 21:05:00, LOGOUT, 10.0.0.1, user1
 21:10:00, LOGIN, 10.0.0.2, user2
 21:12:00, LOGIN, 10.0.0.2, user3
 21:15:00, LOGOUT, 10.0.0.2, user2
 21:22:00, LOGOUT, 10.0.0.2, user3
 21:03:00, LOGOUT, 10.0.0.5, user1

For a given log file the application should provide following functionality:
1) Return IP that received most distinct user logins
result for example: 10.0.0.2

2) Return user that at one point had highest number of sessions open
result for example: user1

3) Return average session length in seconds (time between login and logout event for same user) per IP
result for example: (10.0.0.1: 300s), (10.0.0.2: 450s), (10.0.0.5: 60s)

As mentioned logs can contain other events in different format which should be ignored.
Same user can have multiple sessions open to same IP.
A complete answer will include the following:
document your assumptions
explain your approach
provide unit tests
explain the big-O (runtime and memory) complexity of all 3 operations
Please use either Scala, Java or Python. It's fine to use common libraries and frameworks like Spark.
Please email your submission to me as plain text file(s), and not zipped or archives.
Don't worry about preserving/documenting any directory structure.
Thank you for your time,
Danko









And here is my rough notes of the algorithim



 
1) Return IP that received most distinct user logins
result for example: 10.0.0.2 
 
 
 21:00:00, LOGIN, 10.0.0.1, user1
 21:05:00, LOGOUT, 10.0.0.1, user1
 
 21:10:00, LOGIN, 10.0.0.2, user2
 21:12:00, LOGIN, 10.0.0.2, user3
 21:15:00, LOGOUT, 10.0.0.2, user2
 21:22:00, LOGOUT, 10.0.0.2, user3

 21:03:00, LOGOUT, 10.0.0.5, user1
 21:02:00, LOGIN, 10.0.0.5, user1
 
use only login events       if ip exist then add ip+user      if ip exists then if user not exist in its list add user  
ip1    user1  user2  user3  user5             winner ip1  
ip2    user1  user3  user9    
 

each process has its own list of ip users     once done  all processes combine their list to driver program and driver calculates the results 

 
 
 
 

2) Return user that at one point had highest number of sessions open
result for example: user1 
 
 21:00:00, LOGIN, 10.0.0.1, user1
 21:02:00, LOGIN, 10.0.0.5, user1
 21:03:00, LOGOUT, 10.0.0.5, user1
 21:05:00, LOGOUT, 10.0.0.1, user1
 
 21:10:00, LOGIN, 10.0.0.2, user2
 21:12:00, LOGIN, 10.0.0.2, user3

 21:15:00, LOGOUT, 10.0.0.2, user2
 21:22:00, LOGOUT, 10.0.0.2, user3


user   current count      high count  
u1     1                  1

 
 

 
 
3) Return average session length in seconds (time between login and logout event for same user) per IP
result for example: (10.0.0.1: 300s), (10.0.0.2: 450s), (10.0.0.5: 60s) 
  
 21:00:00, LOGIN, 10.0.0.1, user1                   300 sec user1
 21:05:00, LOGOUT, 10.0.0.1, user1
 
 21:10:00, LOGIN, 10.0.0.2, user2                   300 sec user1
 21:15:00, LOGOUT, 10.0.0.2, user2
 21:12:00, LOGIN, 10.0.0.2, user3                   600 sec user2           average   450   
 21:22:00, LOGOUT, 10.0.0.2, user3
 
 21:02:00, LOGIN, 10.0.0.5, user1                   60 sec 
 21:03:00, LOGOUT, 10.0.0.5, user1                  


IP    user     in           
1     u1       t                        as soon as logout even for user in ip  get login time of user for ip    calculate seconds        
1     u2       t                        now in global structure  check ip exists then add secs and increment counts     or  add another ip  sec  1 
                                        at end calculate averages and see which ip has highest average 











*/