/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harsh
 */
public class Bucket {
    int bucketsize,gd,ld;
    int[] tempbucket;
    Bucket(int gd,int bucketsize){
    this.gd=gd;
    this.ld=gd;
    this.bucketsize=bucketsize;
    make_bucket();
    }
    void make_bucket(){
        tempbucket=new int[7];
        tempbucket[0]=ld;
        for(int i=1;i<tempbucket.length;i++){
            tempbucket[i]=-1;
            }
        }
    void displaybucket(){
        System.out.print("\t Significant No Of Bits:"+tempbucket[0]+" |");  
        for(int i=1;i<tempbucket.length;i++){
            System.out.print("\t"+" | "+tempbucket[i]+" | ");
            }
    }
    int add_item(int i){
        int flag=0;
        for(int j=1;j<tempbucket.length;j++)
        {  
            if(tempbucket[j]==-1)
            {
                tempbucket[j]=i;
                flag=1;
                break;
            }
        }
//        if(flag==0){
//            tempbucket[0]=tempbucket[0]+1;
//        }
        return flag;
        }
}