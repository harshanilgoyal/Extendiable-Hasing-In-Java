package myproject;


import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Harsh
 */
public class extendiable {
    
    
    public static void main(String[] args) {
        System.out.println("##################%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ADS PROJECT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%##################");
        System.out.println("##################********************************** EXTENDIABLE HASHING **********************************##################");
        ArrayList<Integer> data=new ArrayList<>();
         ArrayList<Integer> datainsert=new ArrayList<>();
        for(int i=0;i<100;i++){
            int rand=(int)(Math.random()*1000+1);
            data.add(rand);
           // System.out.println(rand);
        }
        System.out.println("Inserting 100 Values");
        Long st,et;
        st=System.currentTimeMillis();
        Hashing h=new Hashing(data);
        et=System.currentTimeMillis();
        System.out.println("Time Takeen: "+(et-st)+" mili sec");
        
        System.out.println("Inserting 20 More Numbers");
        for(int i=0;i<20;i++){
            int rand=(int)(Math.random()*1000+1);
            datainsert.add(rand);
           // System.out.println(rand);
        }
        st=System.currentTimeMillis();
        
        h.insertion(datainsert);
        et=System.currentTimeMillis();
        System.out.println("Time Taken To Insert: "+(et-st)+" mili sec");
        
//h.display();
    }
}

class Hashing{
    List<Bucket> masterdir=new ArrayList<>(); 
    int gd=2,ld=2,bucketsize=6,count=0;
    List<Integer> data;
    List<String> hasheddata=new ArrayList<>();
    
    Hashing(ArrayList<Integer> data) {
        this.data=data;
        calculateHash_binary(data);
        masterdirectoryinitial();
        addingdata(0);
    }
    
    void insertion(ArrayList<Integer> datainserted){
        count=0;
        for(int i=0;i<datainserted.size();i++){
            data.add(datainserted.get(i));
            int j=datainserted.get(i)%128;
            String binary=Integer.toBinaryString(j);
          //  System.out.println(binary);
        int width=binary.length();
        if(width==7){String s="";hasheddata.add(s+binary);}
        if(width==6){String s="0";hasheddata.add(s+binary);}
        if(width==5){String s="00";hasheddata.add(s+binary);}
        if(width==4){String s="000";hasheddata.add(s+binary);}
        if(width==3){String s="0000";hasheddata.add(s+binary);}
        if(width==2){String s="00000";hasheddata.add(s+binary);}
        if(width==1){String s="000000";hasheddata.add(s+binary);}
        if(width==0){String s="0000000";hasheddata.add(s+binary);}
        }
        //System.out.println(data.size());
        addingdata(100);
    }
    
    void calculateHash_binary(ArrayList<Integer> data){
        int j;
        for(int i=0;i<data.size();i++){
        j=data.get(i)%128; 
        String binary=Integer.toBinaryString(j);
          //  System.out.println(binary);
        int width=binary.length();
        
        if(width==7){String s="";hasheddata.add(s+binary);}
        if(width==6){String s="0";hasheddata.add(s+binary);}
        if(width==5){String s="00";hasheddata.add(s+binary);}
        if(width==4){String s="000";hasheddata.add(s+binary);}
        if(width==3){String s="0000";hasheddata.add(s+binary);}
        if(width==2){String s="00000";hasheddata.add(s+binary);}
        if(width==1){String s="000000";hasheddata.add(s+binary);}
        if(width==0){String s="0000000";hasheddata.add(s+binary);}        
            }
        }
    void masterdirectoryinitial(){
            int mastersize=(int)Math.pow(2,gd);
            for(int i=0;i<mastersize;i++){
                Bucket b=new Bucket(gd,bucketsize);
                masterdir.add(b);
                }
        }
    void masterdirdoubling(int dircolided){
           // display(gd+1);
        
            int mastersize=masterdir.size()*2;
            
            System.out.println(mastersize);
            int k=0;
            for(int i=masterdir.size();i<mastersize;i++)
                { 
                    if(masterdir.get(k).tempbucket[0]<gd+1){
                        masterdir.add(masterdir.get(k));
                        }
                    else{
                    Bucket buc=new Bucket(gd+1,bucketsize);
                    masterdir.add(buc);
                    }
                    k++;   
                }
              for(int i=0;i<mastersize/2;i++)
                { 
                    if(masterdir.get(k).tempbucket[0]<gd+1){
                        
                    }  
                }
            //System.out.println("Masterdoubling");
          //  display(gd+1);
          // System.out.println("############");
    
    }
    
        void addingdata(int startindex){
        if(gd>7){
            System.out.println("***********");
            System.out.println("GD Become Irrevelent As Per Our Hash Function");
            System.out.println("***********");
            if(startindex!=data.size()){
                System.out.println(data.size()-startindex +"Elements Are Remaining To Be Alooted Using mod 128");
                System.out.println("Numbers are as follows");
                for(int i=0;i<data.size();i++){
                    System.out.print("\t"+data.get(i));
                }
            }
            
            exit(0);
            
        }
        int k,i,result=0,dircolided=0;
            System.out.println("");
        for(i=startindex;i<data.size();i++){ 
            String binary=hasheddata.get(i);
            //System.out.println(binary);
            //System.out.println(binary.length());
            String lsb=binary.substring(binary.length()-gd,binary.length());
            int lsbint=Integer.parseInt(lsb,2);
            result=masterdir.get(lsbint).add_item(data.get(i));
            if(result!=1){
                dircolided=lsbint;
                break;
                }
            }
        
        if(result!=1){
            
            
//            System.out.println("@@@@@@@@BEFORE DOUBLING @@@@@@@@@@");
//            System.out.println("#######FOR GAIN FACTOR :"+gd+"########");
//            display();
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            
//            System.out.println("GD: "+gd+"\tdircolided: "+dircolided+"\tld: "+masterdir.get(dircolided).tempbucket[0]);
count++;            
System.out.println("Collision On Inserting Value: "+Integer.toBinaryString((data.get(i))%128)+" Data- "+data.get(i)); 
           // System.out.println(" Beforte Changing Any Thing");
           // display(gd);
           // System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
           if(gd==2){
           //display(gd);
           } 
           
           
            if(masterdir.get(dircolided).tempbucket[0]<gd)
            {    
                masterdir.get(dircolided).tempbucket[0]++;
                reallotment2(i,dircolided);
            
                }
            else{
                if(gd!=2){
                   System.out.println("");
                   System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ REQUIRES SPLITING OF THE BUCKETS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                }
                 System.out.println("########################################### FOR GAIN FACTOR :"+(gd)+" #############################################");
      
                display(gd+1);
                masterdir.get(dircolided).tempbucket[0]++;
                masterdirdoubling(dircolided);
                gd++;
                reallotment(i,dircolided);
                //display(gd);
                
            }
            
            //display();
            }
        else{
            System.out.println("############################################## FINAL SNAPSHOT ##############################################");
            System.out.println("########################################### FOR GAIN FACTOR :"+(gd)+" #############################################");
      
            display(gd);
            System.out.println("#############################################################################################################");
            System.out.println("Total Number Of Collisions: "+count);
        }
        
        }
        void reallotment(int lastindex,int dircolided){

                    int[] array=new int[7];
                    for(int j=0;j<array.length;j++){
                       array[j]=masterdir.get(dircolided).tempbucket[j];
                        //System.out.println(array[j]);
                    }
                    for(int j=1;j<array.length;j++){
                    masterdir.get(dircolided).tempbucket[j]=-1;
                    }
                    //display();
                    //System.out.println("I am here"+array.length);
                    for(int j=1;j<array.length;j++)
                    {   //System.out.println("I am here in Loop");
                        int data= array[j];
                        //System.out.println(data);
                        if(data!=-1){
                        //System.out.println("I am here in condition");    
                        int datahash=data%128;
                        String binary=Integer.toBinaryString(datahash);
                              //System.out.println(binary);
                        int width=binary.length();
                        
                        if(width==7){String s="";binary=(s+binary);}
                        if(width==6){String s="0";binary=(s+binary);}
                        if(width==5){String s="00";binary=(s+binary);}
                        if(width==4){String s="000";binary=(s+binary);}
                        if(width==3){String s="0000";binary=(s+binary);}
                        if(width==2){String s="00000";binary=(s+binary);}
                        if(width==1){String s="000000";binary=(s+binary);}
                        if(width==0){String s="0000000";binary=(s+binary);} 
                        String lsb=binary.substring(binary.length()-array[0],binary.length());
                        int lsbint=Integer.parseInt(lsb,2);
                            //System.out.println("");
                        //System.out.println("lsbint?????"+lsbint);
                           // System.out.println("");
                        int result=masterdir.get(lsbint).add_item(data);
               }
             }
//            System.out.println("$$$$$$$$$ AFTER REALLOTMENT 1$$$$$$$$$$");
//            display(gd);
//            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//            
            addingdata(lastindex);
        }
        
        
        void reallotment2(int lastindex,int dircolided){

                    Bucket tempnew;
                    List<Integer> temp=new ArrayList<>();
                    
                    int[] array=new int[7];
                    for(int j=0;j<array.length;j++){
                       array[j]=masterdir.get(dircolided).tempbucket[j];
                        //System.out.println(array[j]);
                    }
                    
                    for(int i=0;i<masterdir.size();i++)
                        {
                          if(masterdir.get(i).equals(masterdir.get(dircolided)))
                          {
                              temp.add(i);
                              
                          }
                        }
                   // System.out.println("--------------------------------");
                    for(Integer x:temp){
                        if(x<(int)Math.pow(2,array[0])){
                      //  System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
                       // System.out.println("dirsame: "+x);
                      // System.out.println("ld: "+array[0]);
                       // System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
                        tempnew=new Bucket(array[0],bucketsize);
                        masterdir.set(x, tempnew);}
                        else{
                        masterdir.set(x,masterdir.get(x-(int)Math.pow(2,array[0])));
                        }
                        
                    }
                    //System.out.println("------------------------------------");
                    
                    
                    
                    for(int j=1;j<array.length;j++)
                    {   //System.out.println("I am here in Loop");
                        int data= array[j];
                        //System.out.println(data);
                        if(data!=-1){
                        //System.out.println("I am here in condition");    
                        int datahash=data%128;
                        String binary=Integer.toBinaryString(datahash);
                              //System.out.println(binary);
                        int width=binary.length();
                        if(width==8){String s="";binary=(s+binary);}
                        if(width==7){String s="0";binary=(s+binary);}
                        if(width==6){String s="00";binary=(s+binary);}
                        if(width==5){String s="000";binary=(s+binary);}
                        if(width==4){String s="0000";binary=(s+binary);}
                        if(width==3){String s="00000";binary=(s+binary);}
                        if(width==2){String s="000000";binary=(s+binary);}
                        if(width==1){String s="0000000";binary=(s+binary);}
                        if(width==0){String s="00000000";binary=(s+binary);} 
                        String lsb=binary.substring(binary.length()-array[0],binary.length());
                        int lsbint=Integer.parseInt(lsb,2);
                            //System.out.println("");
                        //System.out.println("lsbint?????"+lsbint);
                           // System.out.println("");
                        int result=masterdir.get(lsbint).add_item(data);
               }
             }
//            System.out.println("$$$$$$$$$ AFTER REALLOTMENT 2 $$$$$$$$$$");
//            System.out.println("#######FOR GAIN FACTOR :"+gd+"########");
//            display(gd);
//            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            
            addingdata(lastindex);
        }
        
        
        
        
        
        void display(int gf){
             for(int i=0;i<masterdir.size();i++){
            System.out.println("");
            String j=Integer.toBinaryString(i);
            int width=gf;
            int widthofdata=j.length();
            if(width!=widthofdata){
                int diff=width-widthofdata;
                        if(diff==1){String s="0";j=s+j;}
                        if(diff==2){String s="00";j=s+j;}
                        if(diff==3){String s="000";j=s+j;}
                        if(diff==4){String s="0000";j=s+j;}
                        if(diff==5){String s="00000";j=s+j;}
                        if(diff==6){String s="000000";j=s+j;}
                        //if(diff==7){String s="0000000";j=s+j;}
                        //if(diff==8){String s="00000000";j=s+j;}
            } 
            System.out.print(i+": Bucket: "+j+" --->");
            int width2=masterdir.get(i).tempbucket[0];
            j=j.substring(j.length()-width2,j.length());
            
            System.out.print(" "+j+" ");
            masterdir.get(i).displaybucket();
            System.out.println("");
        }
            System.out.println("");
        System.out.println("-X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--X--");
            System.out.println("");
        }
        
    }
    
    
