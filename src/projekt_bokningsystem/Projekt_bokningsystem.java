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
    
    //p_fält står för personnummer fält och n_fält står för namn fält. Detta gäller för alla metoder som tar emot antingen namn fältet eller personnummers fältet
    static Scanner scan = new Scanner(System.in);
    static final double BARN_PRIS = 149.90;
    static final double PENSIONAR_PRIS = 199.90;
    static final double VUXEN_PRIS = 299.90;
    
    //Kollar om en input är tal eller inte
    static int try_catch_int(){
        
        int tal = 0;
        while(true){
        try{
            tal = scan.nextInt();
            break;
        }
        catch(Exception e) {
            System.out.print("Ange en siffra!\nval: ");
            scan.nextLine();
        }
        }
               return tal;
    }
    //Metoden tar emot användarens val. Används mest för att ta in valet från bokningslistan
    static int bokningslista(){
        int val;
        System.out.print("\nVal: ");
        val = try_catch_int();
        return val;
    }
    static void boka_plats(int[] p_fält, String[] n_fält, String[] k_fält){
        String val = null; 
        int personnummer;
        String kön = null;
        //ifall metoden kollar genom alla element i fältet men inte bokar en platts så är alla platser bokade. då kommer fortsätt1 vara true och det printas att alla plattser är bokade i slutet av while loopen
        boolean fortsätt1 = true;
        //ifall användaren angivit ett personnummer och namn som redan finns i systemet blir frotsätt2 false och bokningen kommer inte genomföras
        boolean fortsätt2 = true;
        //används så att metoden fortfarande går att kalla även om bara fösnterplatserna eller de vanliga platserna är bokade
        boolean fortsätt3 = true;
        String namn;
        while(fortsätt1 && fortsätt3) {
            int lediga_fösnterplatser = 0;
            int lediga_vanliga_platser = 0;
            //denna for loop kollar om det finns några fösnterplatser eller vanliga platser
            for(int i = 0; i < p_fält.length; i++){
                if(p_fält[i] == 0 && är_fönster_plats((i+1)) ) {
                    //om det finns en ledig fönsterplats
                    lediga_fösnterplatser++;
                }
                else if(p_fält[i] == 0 && är_fönster_plats((i+1)) == false) {
                    //om det finns en ledig vanlig plats
                    lediga_vanliga_platser++;
                }
            }
            //om det inte finns några fösnterplatser men det finns vanliga platser så printas det till användaren
            if(lediga_fösnterplatser == 0 && lediga_vanliga_platser != 0) {
                System.out.println("Alla fösnterplatser är bokade!");
                //valet att inte ha en fösnterplats väljs automatiskt
                val = "n";
            }
            //om det inte finns några vanliga platser men det finns fönsterplatser så säger printas det till användaren
            else if(lediga_vanliga_platser == 0 && lediga_fösnterplatser != 0) {
                System.out.println("Alla vanliga plattser är bokade! Det finns bara fönsterplatser.");
                //valet att ha en fösnterplats väljs automatiskt
                val = "j";
            }
            //om det finns några plattser så printar den det och fortsätt1 blir false vilket gör så att resten av metoden som bokar en plats inte körs
            else if(lediga_vanliga_platser == 0 && lediga_fösnterplatser == 0) {
                System.out.println("Alla platser är bokade!");
                fortsätt1=false;
            }
            //om det finns vanliga samt fönsterplatser så kommer programmet låta användaren välja 
            else if(lediga_vanliga_platser != 0 && lediga_fösnterplatser != 0) {
                scan.nextLine();
                System.out.println("Vill du ha en fönsterplatts? (j/n): ");
                while(true) {
                    val = scan.nextLine();
                    
                    if( val.equalsIgnoreCase("j") == false && val.equalsIgnoreCase("n") == false ) {
                        System.out.println("Ja eller Nej (j/n)");
                    }
                    
                    else if(val.equalsIgnoreCase("j") || val.equalsIgnoreCase("n")) {
                        break;
                    }
                }
            }
            //om det finns platser så är fortsätt1 true och användaren kan skriva in personuppgifter
            if(fortsätt1) {
                System.out.print("Ange personnummer(yyyymmdd) samt för- och efternamn för din bokning \nPersonnummer: ");
                personnummer = try_catch_int();
                System.out.print("Namn: ");
                scan.nextLine();
                namn = scan.nextLine();
                System.out.println("Kön?\n1. Man\n2. Kvinna");
                //Denn while loop håller koll så att svaret på vilket kön användaren är är antingen 1 eller 2.
                while(true){
                    int nummer_kön = try_catch_int();
                    //om svaret inte är 1 eller 2 börjar loopen om
                    if(nummer_kön!=1 && nummer_kön!=2){
                        System.out.println("Ditt svar måste vara 1 eller 2");
                    }
                    //om svaret är 1 eller 2 kopplas svaret till rätt kön och loopen bryts
                    else if(nummer_kön==1 || nummer_kön==2){
                        if(nummer_kön==1){
                            kön="Man";
                        }
                        else if(nummer_kön==2){
                            kön="Kvinna";
                        }
                        break;
                    }
                }
                for(int i = 0; i < p_fält.length; i++) {
                    //Kollar igenom fältet så att det inte sker några dubbelbokningar
                    for( int y = 0; y < p_fält.length; y++ ){
                        if(p_fält[y] == personnummer && n_fält[y].equalsIgnoreCase(namn)) {
                            System.out.println("Personnumret och namnet du angav har redan en bokning");
                            i = p_fält.length;
                            fortsätt1 = false;
                            fortsätt2 = false;
                        }
                    }
                    //om användaren inte vill boka en fösnterplats
                    if(val != null && val.equalsIgnoreCase("n") && fortsätt2) {
                        //om platsen inte är bokad samt att det inte är en fönsterplats så bokar den platen
                        if(p_fält[i] == 0 && är_fönster_plats((i+1)) == false) {
                        p_fält[i] = personnummer;
                        n_fält[i] = namn;
                        k_fält[i] = kön;
                        fortsätt1 = false;
                        System.out.println("Plats "+(i+1)+" är bokad för "+namn+". Personnummer "+personnummer);
                        break;
                        }
                    }
                    //om användaren valde att boka fönsterplats
                    else if(val != null && val.equalsIgnoreCase("j") && fortsätt2) {
                        //om platsen inte är bokad samt att det är en fönsterplats så bokar den platen
                        if(är_fönster_plats((i+1)) && p_fält[i] == 0) {
                            p_fält[i] = personnummer;
                            n_fält[i] = namn;
                            k_fält[i] = kön;
                            System.out.println("Plats "+(i+1)+" är bokad för "+namn+". Personnummer "+personnummer);
                            fortsätt1 = false;
                            break;
                        }
                        else {
                            fortsätt3 = false;
                        }
                    } 
                }
            }
        }
    }
    
    //Kollar ifall en person är över eller under 18 i ett fält
    static void över_under_18(int[] p_fält) {       
        int över_18;
        int under_18;
        över_18=+antalPensionärer(p_fält) + antalVuxna(p_fält);
        under_18=+antalBarn(p_fält);
        System.out.println("Personer som är 18 år eller äldre: "+ över_18 +"\nPersoner som är under 18år: "+ under_18);
    }
    
    //beräknar ålder på en person baserat på personnummret
    static int beräkna_ålder(int personnummer) {
        // delar födelseåret så att bara året är kvar 
        int födelseår=personnummer/10000;
        int ålder=2023-födelseår;
        return ålder;
    }
    //räknar ut antalet pensionärer som bokat
    static int antalPensionärer(int[] p_fält) {
        //antalet pensionärer som är över 69 år
        int antal = 0;
        //for loopen går igenom hela personnumers fältet och adderar antal med 1 om personen är pensionär
        for(int i = 0; i < p_fält.length; i++) {
            if(beräkna_ålder(p_fält[i]) > 69 && p_fält[i] != 0){
                antal++;
            } 
        }
        return antal;
    }
    //räknar ut antal vuxna som bokat
    static int antalVuxna(int[] p_fält) {
        //antal vuxna som är över 18 men under 70
        int antal = 0;
        //for loopen går igenom hela personnumers fältet och adderar antal med 1 om personen är vuxen
        for(int i = 0; i < p_fält.length; i++){
            if(beräkna_ålder(p_fält[i]) >= 18 && p_fält[i] != 0) {
                antal++;
            } 
        }
        return antal;
    }
    //räknar ut alla barn som bokat
    static int antalBarn(int[] p_fält) {
        //antal barn under 18
        int antal = 0;
        //for loopen går igenom hela personnumers fältet och adderar antal med 1 om personen är ett barn
        for(int i = 0; i < p_fält.length; i++){
            if(beräkna_ålder(p_fält[i]) < 18 && p_fält[i] != 0){
                antal++;
            } 
        }
        return antal;
    }
    
    //skriver ut de lediga plattserna
    static void skriv_ut_lediga_platser(int[] fält) {
        System.out.print("Lediga platser:\n");
        //for loopen går igenom fältet i detta fallet personnumret. Om elementet är 0 är platsen obokad
        for(int i = 0; i < fält.length ; i++){
            
            if(fält[i] == 0) {
                System.out.print((i+1)+" ");
            }
            
            //om plattsen inte va 0 så är plattsen bokad.            
            else {
                System.out.print("Bokad ");
            }
        }
    }
    
    // Kontrollera om platsen är fönster plats eller inte. Om den är det så returnar den true
    static boolean är_fönster_plats(int platsNummer) {
    return (platsNummer == 1 || platsNummer == 4 || platsNummer == 5 || platsNummer == 8 || platsNummer == 9 || platsNummer == 12 || platsNummer == 13 || platsNummer == 16 || platsNummer == 17 || platsNummer == 21);
    }
    
    //bokar en plats
    //hittar en bokning med namn och personnummer
    //hade barat använt personnummer men då jag inte använder de sista fyra kan folk ha samma personnummer/födelsedag så metoden dubbelkollar med namn
    static void hitta_bokning(int[] p_fält, String n_fält[]) {
        int personnummer;
        String namn;
        System.out.print("Vad är ditt personnummer? ");
        personnummer = try_catch_int();
        System.out.print("Vad är ditt namn? ");
        scan.nextLine();
        namn = scan.nextLine();
        //går igenom fältet av personnummer och namn och om de kopplas till en plats printas det och loopen avslutas
        for(int i = 0; i < 21; i++) {
            
            if(p_fält[i] == personnummer && n_fält[i].equalsIgnoreCase(namn)) {
                System.out.println(n_fält[i] + " Plats: "+(i+1));
                break;
            }
            
            //om loopen kommer till sitt slut utan att hitta ett peronnummer och namn som matchar så finns det inte.
            else if(20 == i) {
                System.out.println("Personummret " + personnummer + " finns inte i systemet");
            }
        }
    }
    
    //Skriver ut en lista på alla som bokat där den äldsta är först
    static void skriv_ut_sorterad_utskrift(int[] p_fält, String n_fält[], String k_fält[]) {
        String n_fält_kopia[] = n_fält.clone();
        int p_fält_kopia[] = p_fält.clone();
        String k_fält_kopia[] = k_fält.clone();
        
        //for loopar för sortera alla personnummer och namn så att äldst är först
        for(int i = 0; i < p_fält_kopia.length; i++) {            
            for(int j = i+1 ; j < p_fält_kopia.length; j++) {               
                if(p_fält_kopia[i] > p_fält_kopia[j]) {
                    
                    //sorterar personnummrerna
                    int tillfälligt_fält1 = p_fält_kopia[i];
                    p_fält_kopia[i] = p_fält_kopia[j];
                    p_fält_kopia[j] = tillfälligt_fält1;
                    
                    //sorterar namnen
                    String tillfälligt_fält2 = n_fält_kopia[i];
                    n_fält_kopia[i] = n_fält_kopia[j];
                    n_fält_kopia[j] = tillfälligt_fält2;
                    
                    //sorterar könen
                    String tillfälligt_fält3 = k_fält_kopia[i];
                    k_fält_kopia[i] = k_fält_kopia[j];
                    k_fält_kopia[j] = tillfälligt_fält3;
                            
                }
            }                      
        }
        
        //då alla obokade platser hamnar längst fram för de är 0 så måste metoden ignorerade dem och sen jämför looparna det osorterade fältet som visar platserna med det sorterade fältet
        System.out.println("Sorterad utskrift:");
        for(int i = 0;i < p_fält_kopia.length; i++) {
            for(int j = 0; j < p_fält_kopia.length; j++) {
                if(p_fält_kopia[i] == p_fält[j] && p_fält_kopia[i] != 0) {
                    System.out.println(n_fält_kopia[i] + ", " + p_fält_kopia[i] +" Kön: "+ k_fält_kopia[i] + ". Plats " + (j+1));                   
                }                   
            }
        }
    }
    //Metod som räknar ut vinsten med hjälp av recursion
    static double RäknaVinst(int vuxna, int barn, int pensionärer) {
        //Om det inte finns någon som bokat eller att metoden har gått igenom alla så retuneras 0
        if (vuxna == 0 && barn == 0 && pensionärer == 0) {
            return 0;
        } 

        double total = 0;
        if (vuxna > 0) {
            total += VUXEN_PRIS;
            vuxna--;
        } 

        else if(barn > 0){
            total += BARN_PRIS;
            barn--;
        }
        
        else if(pensionärer > 0) {
            total += PENSIONAR_PRIS;
            pensionärer--;
        }
        //metoden returnerar vinsten och kallar sedan sig själv
        return total + RäknaVinst(vuxna, barn, pensionärer);
    }
    //metod som tar bort en bokning med personnummer och namn
    static void ta_bort_bokning(int[] p_fält, String n_fält[], String k_fält[]) {
        
        //samma funktion som i metoden boka_plats
        boolean fortsätt = true;
        System.out.print("Vad är ditt personnummer? ");
        int personnummer = try_catch_int();
        System.out.print("Namnet som är kopplat till bokningen? ");
        scan.nextLine();
        String namn = scan.nextLine();
        
        //går igenom personnummersfältet och namn fältet        
        for(int i = 0; i<p_fält.length ;i++){
            //om den får en matchning med det angivna personnummret och namnet avbokar den platen
            if(personnummer == p_fält[i] && n_fält[i].equalsIgnoreCase(namn)) {
                p_fält[i] = 0;
                n_fält[i] = "";
                k_fält[i] = "";
                System.out.println("Din platts är nu avbokad");
                //fortsätt blit false så att if-satsen i slutet inte utförs
                fortsätt = false;
                break;
            }
        }
        
        //om metoden söker igenom hela fältet och inte får en matchning är fortsätt true och det printas att det personnumret eller namnet inte fanns i systemet
        if(fortsätt) {
            System.out.println("personnummret eller namnet du angav finns inte i systemet");
        }
    }
            
    public static void main(String[] args) {
        /*
        //Fyllda fält som kan användas till testning
        int[] perssonummer_bokning = {19980426, 19870611, 0, 19890318, 19880606, 19880402, 19850807, 19860423, 19971023, 19931023, 19910723, 19870623, 0, 19890223, 19900323, 19870323, 0, 19910623, 19840823, 0,19870423};
        String[] namn_bokning = {"Ronald Taylor", "Mark Brown", null, "David Hernandez", "Steven Smith", "Paul Lee", "Joseph Davis", "Richard Thompson", "Christopher Rodriguez", "Donald Jones", "Ronald Lee", "Mark Jackson", null, "Thomas White", "Robert Wilson", "Brian Lopez", null, "Kenneth Davis", "George Rodriguez",null , "Ronald Smith"};
        */
        int[] perssonummer_bokning = new int[21];
        String[] namn_bokning = new String[21];
        String[] kön_bokning = new String[21];
        boolean fortsätt=true;
        System.out.println("Väkommen!\nDetta är ett bokningssystem till en buss med 21 platser. Nedan har du en lista över vad du kan göra.\n");
        System.out.println("1. Lägga till en passagerare – boka en obokad plats");
        System.out.println("2. Skriv ut lediga platser");
        System.out.println("3. Hitta bokning");
        System.out.println("4. Lista alla personer som är över och under 18 år");
        System.out.println("5. Beräkna vinsten av antalet sålda biljetter (recursion)");
        System.out.println("6. Ta bort bokning: ange personnumret och bokning tas bort.");
        System.out.println("7. Sorterad utskrift av alla boknignar");
        System.out.println("8. Avsluta programmet");
        
        while(fortsätt) {
            switch(bokningslista()) {

                case 1: 
                    boka_plats(perssonummer_bokning, namn_bokning, kön_bokning);             
                    break;
                case 2:
                    skriv_ut_lediga_platser(perssonummer_bokning);
                    break;
                case 3:
                    hitta_bokning(perssonummer_bokning, namn_bokning);
                    break;
                case 4:
                    över_under_18(perssonummer_bokning);
                    break;
                case 5:
                    System.out.println("Vinst: "+ (int)RäknaVinst(antalVuxna(perssonummer_bokning), antalBarn(perssonummer_bokning), antalPensionärer(perssonummer_bokning))+" Sek");
                    break;
                case 6:
                    ta_bort_bokning(perssonummer_bokning, namn_bokning, kön_bokning);
                    break;
                case 7:
                    skriv_ut_sorterad_utskrift(perssonummer_bokning, namn_bokning, kön_bokning);
                    break;
                case 8:
                    System.out.println("Hejdå!");
                    fortsätt = false;
                    break;
                default:
                    System.out.println("Fel svar!\nDitt svar måste vara mellan 1-8");
            }           
        }
    }
}
