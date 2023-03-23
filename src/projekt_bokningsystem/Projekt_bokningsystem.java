/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projekt_bokningsystem;

import java.util.Scanner;
/**
 *
 * @author carl.hummerhielm
 */
public class Projekt_bokningsystem {
    static Scanner scan = new Scanner(System.in);
    //Kollar om en input är tal eller inte
    static int try_catch_int(){
        
        int tal=0;
        while(true){
        try{
            tal= scan.nextInt();
            break;
        }
        catch(Exception e){
            System.out.print("Ange en siffra!\nval: ");
            scan.nextLine();
            continue;
        }
        }
               return tal;
    }
    //Kollar om en input är bokstäver eller inte
    static String try_catch_string(){
        String input="";
        try{
            input= scan.nextLine();
        }
        catch(Exception e){
            System.out.println("Ange en bokstäver!");
        }
        return input;
    }
    
    static int bokningslista(){
        int val=0;
        System.out.println("1. Lägga till en passagerare – boka en obokad plats");
        System.out.println("2. Skriv ut hur många lediga platser det finns");
        System.out.println("3. Beräkna vinsten av antalet sålda biljetter");
        System.out.println("4. Ta bort bokning: ange personnumret och bokning tas bort.");
        System.out.println("5. Lista alla personer som är över och under 18 år");
        System.out.println("6. Avsluta programmet");
        System.out.print("Val: ");
        val=try_catch_int();
        return val;
    }
    
    static void skriv_ut_lediga_platser(int[] fält){
        System.out.print("Lediga platser:\n");
        for(int i=0; i<fält.length ;i++){
            if(fält[i]==0){
                System.out.print((i+1)+" ");
            }
            else{
                System.out.print("Bokad ");
            }
        }
    }
    
    static void boka_plats(int[] fält){
        int personnummer=0;
        System.out.print("Ange personnummer för din bokning (yyyymmdd)"
                + ": ");
        personnummer = try_catch_int();
        
        for(int i = 0; i<fält.length;i++){
        if(fält[i]==0){
            fält[i]=personnummer;
            System.out.println("Plats "+(i+1)+" är bokad");
            break;
            }
        }
    }
            
    public static void main(String[] args) {
         int[] perssonummer_bokning = new int[21];
        switch(bokningslista()){
            
            case 1: 
                boka_plats(perssonummer_bokning);
        }
    }
    
}
