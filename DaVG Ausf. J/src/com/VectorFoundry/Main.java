package com.VectorFoundry;


import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class Main {

    public static BufferedReader reader;
    public static PrintWriter printer;
    public static BufferedWriter writer;
    public static StringBuilder builder;
    public static Scanner input = new Scanner(System.in);
    public static boolean debugMode;
    public static String VersionNo;
    public static String userName;
    public static int LongDelay;
    public static int MedDelay;
    public static int ShortDelay;
    public static int NoDelay;
    public static String[] daVGDir;
    public static File[] daVGDrive;
    public static String DirPath;
    public static String userProfile;

    public static void main( String[] args ) throws InterruptedException, IOException {
        VersionNo = "V0.1";
        debugMode = false;
        LongDelay = 3;
        MedDelay = 2;
        ShortDelay = 1;
        NoDelay = 100;
        userProfile = System.getenv( "USERPROFILE" );
        DirPath = userProfile + "\\Documents\\DaVG_AusfJ_";
        input = new Scanner( System.in );
        /** Welcome to DaVG Ausf. J V0.1!
         *
         * This program is meant to emulate the functions of the Anser-Lindhurst DatenVerarbeitungsGer&auml;t, or DaVG.
         * In House of Mirrors, the player is issued the DaVG Ausf. J, a durable (  if bulky  ) wrist-mounted information
         * processor developed by the Volkshavenish firm Anser-Lindhurst Instrumentenfirma and issued to Volkshavenish
         * troops during the 1960s.
         *
         * This emulation of the DaVG has been given the designation of Ausf&uuml;hrung J (  "Execution J"  ) to
         * distinguish it from its sibling, Ausf&uuml;hrung B, which takes the form of a batch file.
         *
         */

        /**The program begins by initializing the DaVG's operating system, known as KRIEGSGEIST, or "War Mind". In the
         * context of the Multiverse, KRIEGSGEIST is a reliable ( if perhaps a bit spartan ) operating system built
         * specifically for the DaVG platform.
         *
         * DaVG Ausf. J begins by stating that KRIEGSGEIST V2 is being initialized.
         */
        ClearScreen();
        System.out.println( "//Initializing KRIEGSGEIST V2..." );
        TimeUnit.SECONDS.sleep( MedDelay );
        System.out.print( "//CHECKING RAM... " );
        TimeUnit.SECONDS.sleep( MedDelay );
        System.out.println( "128K RAM AVAILABLE." );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.print( "//CHECKING MEMORY..." );
        TimeUnit.SECONDS.sleep( MedDelay );
        System.out.println( "77822 BYTES AVAILABLE." );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//READING HARD DRIVE..." );

        /** At this point, the program will attempt to locate a folder labeled "DaVG_AusfJ_(Version No.)_Data
         * in the user's Documents folder. This folder will only exist if the user has run the program previously.
         * If it does not exist, then the program will then move directly into a small cinematic sequence in which it
         * completes a series of "diagnostic" tests.
         *
         * However, if the folder does exist, the program will list all the profiles contained within them.
         */
        File DataDirectory = new File(DirPath + VersionNo + "_Data\\");
        String[] profileList = DataDirectory.list();
        System.out.println (profileList);
        if (profileList.length == 0){
            System.out.println( "//ERROR: NO PROFILES FOUND IN HARD DRIVE" );
            TimeUnit.SECONDS.sleep(ShortDelay);
            System.out.println( "//CREATE NEW PROFILE? Y/N" );
            TimeUnit.SECONDS.sleep(ShortDelay);
            System.out.println( "//WARNING: CHOOSING \"N\" WILL DEACTIVATE UNIT TO PREVENT TAMPERING");
            boolean optionNotChosen = true;
            while ( optionNotChosen ) {
                switch ( input.next() ) {
                    case "Y": case "y":
                        optionNotChosen = false;
                        System.out.println( "//PLEASE INPUT PROFILE NAME." );
                        TimeUnit.SECONDS.sleep(ShortDelay);
                        System.out.println( "//WARNING: PROFILE NAMES ARE CASE SENSITIVE" );
                        userName = input.next();
                        break;
                    case "N": case "n":
                        optionNotChosen = false;
                        shutdown();
                        break;
                    default:
                        System.out.println( "//ERROR: INVALID INPUT." );
                        break;
                }
            }
        } else {
            for (String profileName : profileList){
                System.out.println( ">" + profileName );
            }
            System.out.println( "//PLEASE SELECT A PROFILE" );
            TimeUnit.SECONDS.sleep( ShortDelay );
            System.out.println( "//WARNING: PROFILE NAMES ARE CASE SENSITIVE" );
            userName = input.next();
            File requestedProfile = new File( DirPath + VersionNo + "_Data\\" + userName + "\\" );
            if (!requestedProfile.isDirectory()){
                System.out.println( "//ERROR: SPECIFIED PROFILE NOT FOUND" );
                TimeUnit.SECONDS.sleep( ShortDelay );
                System.out.println( "//CREATE NEW PROFILE WITH THIS NAME? Y/N" );
                TimeUnit.SECONDS.sleep( ShortDelay );
                System.out.println( "//WARNING: CHOOSING \"NO\" WILL DEACTIVATE UNIT TO PREVENT TAMPERING");
                boolean optionNotChosen = true;
                while ( optionNotChosen ) {
                    switch ( input.next() ) {
                        case "Y": case "y":
                            optionNotChosen = false;
                            break;
                        case "N": case "n":
                            optionNotChosen = false;
                            shutdown();
                            break;
                        default:
                            System.out.println( "//ERROR: INVALID INPUT." );
                            break;
                    }
                }
            }
        }

        daVGDir = new String[6];
        daVGDrive = new File[6];

        daVGDir[0] = DirPath + VersionNo + "_Data\\" + userName + "\\";
        daVGDir[1] = DirPath + VersionNo + "_Data\\" + userName + "\\inv\\weap";
        daVGDir[2] = DirPath + VersionNo + "_Data\\" + userName + "\\inv\\ammo";
        daVGDir[3] = DirPath + VersionNo + "_Data\\" + userName + "\\inv\\equp";
        daVGDir[4] = DirPath + VersionNo + "_Data\\" + userName + "\\inv\\meds";
        daVGDir[5] = DirPath + VersionNo + "_Data\\" + userName + "\\inv\\misc";
        daVGDrive[0] = new File( daVGDir[0] );
        daVGDrive[1] = new File( daVGDir[1] );
        daVGDrive[2] = new File( daVGDir[2] );
        daVGDrive[3] = new File( daVGDir[3] );
        daVGDrive[4] = new File( daVGDir[4] );
        daVGDrive[5] = new File( daVGDir[5] );

        /**Here, the program creates a pair of arrays, daVGDir and daVGDrive, both of which house several parameters.
         *
         * daVGDir contains the names of the various directories that the program makes use of within the user's
         * Documents directory.
         *
         * daVGDrive stores these as Files so that these directories are more easily accessed later in the program.
         *
         * Both of these are defined early on to make the program's structure easier to modify in the future.
         *
         * For future reference, the slots each category of a player's inventory are allocated as follows:
         *
         * 0 - Player data text file. Contains the player's name, current and max HP, status, speed and strength ratings.
         * 1 - Inventory slot for weapons.
         * 2 - Inventory slot for ammunition.
         * 3 - Inventory slot for equipment.
         * 4 - Inventory slot for medicine.
         * 5 - Inventory slot for misc. items.
         */
        
        File playerSheet = new File ( daVGDir[0] + "playerData.txt" );
        boolean dataExists = playerSheet.exists() && playerSheet.isFile();
        /**If the program successfully locates playerData.txt and all of the inventory directories, the user will be prompted with the option of skipping the
         *boot-up cinematic that plays when DaVG Ausf. J is first loaded. Otherwise, the program will automatically play
         * the intro cinematic and walk the user through the process of creating a new user profile.
         */
        if ( dataExists ) {
            System.out.println(playerSheet);
            reader = new BufferedReader(new FileReader(playerSheet));
            builder = new StringBuilder();
            String playerData;
            while ((playerData = reader.readLine()) != null) {
                builder.append(playerData);
            }
            playerData = builder.toString();
            System.out.println(playerData);
            boolean retUser = dataExists && !playerData.isEmpty();
            if (retUser) {
                ClearScreen();
                System.out.println(System.getProperty( "os.name" ));
                System.out.println("*Please choose a boot sequence.");
                TimeUnit.SECONDS.sleep( ShortDelay );
                System.out.println(">FULL (  Full boot sequence  )");
                TimeUnit.MILLISECONDS.sleep( NoDelay );
                System.out.println(">SHORT (  Shortened boot sequence  )");
                TimeUnit.MILLISECONDS.sleep( NoDelay );
                System.out.println(">SKIP (  Skips boot sequence entirely  )");
                boolean optionNotChosen = true;
                while (optionNotChosen) {
                    switch (input.next()) {
                        case "FULL": case "full": case "1":
                            optionNotChosen = false;
                            intro(userProfile, retUser, daVGDrive, daVGDir, playerSheet);
                            break;
                        case "SHORT": case "short": case "2":
                            optionNotChosen = false;
                            intro2(userProfile, retUser, daVGDrive, daVGDir, playerSheet);
                            break;
                        case "SKIP": case "skip": case "3":
                            optionNotChosen = false;
                            mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                            break;
                        default:
                            System.out.println("*That's not a valid option.");
                            break;
                    }
                }

            } else {
                System.out.println( "//ERROR: NO DATA IN SPECIFIED DRIVE SECTOR" );
                intro(userProfile, retUser, daVGDrive, daVGDir, playerSheet);
            }
        } else {
            boolean retUser = false;
            intro(userProfile, retUser, daVGDrive, daVGDir, playerSheet);
        }
    }

    public static void ClearScreen() throws IOException, InterruptedException {
        /**This section of the program is special because what it does specifically changes based on the operating
         * system that it's running on.
         * In any case, the end result is the same - the command console that the program is running on is cleared.
         * However, the means by which this is accomplished varies from system to system due to proprietary command
         * consoles.
         *
         * The program first detects the user's OS via System.getProperty, and then plugs the result into a switch
         * statement, as shown below. Depending on the OS it's running on, the program will use ProcessBuilder to
         * construct the appropriate command.
         */
        String os = System.getProperty( "os.name" );
        switch ( os ){
            case "Windows 10": case "Windows 8.1": case "Windows 8": case "Windows 7":
                new ProcessBuilder( "cmd", "/c", "cls" ).inheritIO().start().waitFor();
                break;
            case "MacOS Sierra":
                new ProcessBuilder( "clear" ).inheritIO().start().waitFor();
                break;
            default:
                System.out.println( "*If you're seeing this, it means that something has gone wrong - most likely that your OS is not supported yet." );
                System.out.println( "*The program will exit in 10 seconds." );
                TimeUnit.SECONDS.sleep( 10 );
                System.exit( 1 );
                break;
        }
    }

    public static void intro(String userProfile, boolean retUser, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        System.out.println( "//PERFORMING DIAGNOSTIC TEST BATTERY." );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( "//CORE 1..." );
        TimeUnit.SECONDS.sleep( MedDelay );
        System.out.println( "OK" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.print( "//CORE 2..." );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "OK" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.print( "//CORE 3..." );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "OK" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.print( "//CORE 4..." );
        TimeUnit.SECONDS.sleep( MedDelay );
        System.out.println( "OK" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.print( "//CORE 5..." );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "OK" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.print( "//REACTOR CORE..." );
        TimeUnit.SECONDS.sleep( MedDelay );
        System.out.println( "OK" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.print( "//FUEL LEVELS..." );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "OK" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.print( "//COOLANT LEVELS..." );
        TimeUnit.SECONDS.sleep( LongDelay );
        System.out.println( "OK" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        intro2( userProfile, retUser, daVGDrive, daVGDir, playerSheet );

    }

    public static void intro2(String userProfile, boolean retUser, File[] daVGDrive, String[] daVGDir,  File playerSheet) throws InterruptedException, IOException {
        System.out.print( "//CNMI CONNECTION..." );
        if ( retUser ) {
            System.out.println( "OK" );
            TimeUnit.SECONDS.sleep( ShortDelay );
            System.out.println( "//USER MATCHES 1 ENTRY IN REGISTRY." );
            TimeUnit.MILLISECONDS.sleep( NoDelay );
            System.out.println( "//VERIFYING USER IDENTITY..." );
            String[] playerData = new String[6];
            reader = new BufferedReader(new FileReader(playerSheet));
            try {
                playerData = reader.readLine().split("~");
                reader.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            System.out.println( "//USER IDENTITY CONFIRMED." );
            TimeUnit.MILLISECONDS.sleep( NoDelay );
            System.out.println( "//WELCOME BACK, " + playerData[0] );
            TimeUnit.MILLISECONDS.sleep( NoDelay );
            System.out.println( "//PRESS ENTER TO CONTINUE");
            input.nextLine();
            mmain(userProfile, daVGDrive, daVGDir, playerSheet);

        } else {
            System.out.println( "FAILED" );
            TimeUnit.SECONDS.sleep( ShortDelay );
            System.out.println( "//WARNING: NO CNMI DETECTED WITHIN USER." );
            TimeUnit.MILLISECONDS.sleep( NoDelay );
            System.out.println( "//OVERRIDE? Y/N" );
            TimeUnit.SECONDS.sleep(ShortDelay);
            System.out.println( "//WARNING: CHOOSING \"N\" WILL DEACTIVATE UNIT TO PREVENT TAMPERING");
            boolean optionNotChosen = true;
            while ( optionNotChosen ) {
                switch ( input.next() ) {
                    case "Y": case "y":
                        System.out.println( "//OVERRIDING..." );
                        TimeUnit.SECONDS.sleep( LongDelay );
                        optionNotChosen = false;
                        newuser( userProfile, daVGDrive, daVGDir, playerSheet, input);
                        break;
                    case "N": case "n":
                        optionNotChosen = false;
                        shutdown();
                        break;
                    default:
                        System.out.println( "//ERROR: INVALID INPUT." );
                        break;
                }
            }
        }
    }
    public static void newuser(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet, Scanner input) throws InterruptedException, IOException {
        System.out.println( "//DIAGNOSTIC TESTS COMPLETED." );
        TimeUnit.SECONDS.sleep( LongDelay );
        System.out.println( "//ERROR: USER DOES NOT MATCH ANY ENTRIES IN USER REGISTRY." );
        TimeUnit.SECONDS.sleep( LongDelay );
        System.out.println( "//NO ADMINISTRATORS IN USER REGISTRY." );
        TimeUnit.SECONDS.sleep( LongDelay );
        System.out.println( "//NO SECURITY MEASURES DEFINED BY PRIOR USERS." );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//CREATE NEW USER? Y/N" );
        TimeUnit.SECONDS.sleep(ShortDelay);
        System.out.println( "//WARNING: CHOOSING \"N\" WILL DEACTIVATE UNIT TO PREVENT TAMPERING");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "Y": case "y":
                    optionNotChosen = false;
                    break;
                case "N": case "n":
                    optionNotChosen = false;
                    shutdown();
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
        String [] playerData = new String[6];
        System.out.println( "//INPUT NEW USER NAME." );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//WARNING: GAPS MUST BE INDICATED WITH UNDERSCORES.");
        playerData[0] = input.next();
        System.out.println( "//WRITING TO HARD DRIVE...");
        daVGDrive[0].mkdir();
        daVGDrive[1].mkdirs();
        daVGDrive[2].mkdirs();
        daVGDrive[3].mkdirs();
        daVGDrive[4].mkdirs();
        daVGDrive[5].mkdirs();
        playerSheet.createNewFile();
        reader = new BufferedReader(new FileReader(playerSheet));
        writer = new BufferedWriter(new FileWriter(playerSheet));
        writer.write(playerData[0]+"~100~100~NORMAL~MODERATE~NORMAL~001");
        writer.flush();
        writer.close();
        TimeUnit.SECONDS.sleep( LongDelay );
        System.out.println( "//DATA WRITING COMPLETE." );
        TimeUnit.MILLISECONDS.sleep( ShortDelay );
        System.out.println( "//NEW ENTRY CREATED IN USER REGISTRY." );
        TimeUnit.MILLISECONDS.sleep( ShortDelay );
        System.out.println( "//GREETINGS, "+playerData[0] );
        TimeUnit.MILLISECONDS.sleep( ShortDelay);
        System.out.println( "//PRESS ENTER TO CONTINUE");
        input.nextLine();
        mmain(userProfile, daVGDrive, daVGDir, playerSheet);
    }

    public static void mmain(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws InterruptedException, IOException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        System.out.print( "//USER | " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( playerData[0] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( ">VITALS (1/VITL)" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">INVENTORY (2/INVT)" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">JOURNAL (3/JRNL)" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">UTILITY (4/UTIL) " );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">COMBAT (5/CMBT) " );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">SHUTDOWN (0/POWR) " );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "VITALS": case "vitals": case "VITL": case "vitl": case "1":
                    vitals( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "INVENTORY": case "inventory": case "INVT": case "invt": case "2":
                    inventory( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "JOURNAL": case "journal": case "JRNL": case "jrnl": case "3":
                    journal ( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "UTILITY": case "utility": case "UTIL": case "util": case "4":
                    utility ( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "COMBAT": case "combat": case "CMBT": case "cmbt": case "5":
                    combat( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "0":
                    shutdown();
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }

    }
    public static void vitals(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        System.out.print( "//USER | " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( playerData[0] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( " > VITALS" );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//INITIALIZING VITAL SIGNS ANALYSIS");
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.print( "//VITALS ANALYSIS..." );
        TimeUnit.SECONDS.sleep(3);
        System.out.println( "COMPLETE" );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//CURRENT HEALTH: " + playerData[1] + "/" + playerData[2] + " HP" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( "//OVERALL VITAL STATUS: " + playerData[3] );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( "//MOVEMENT SPEED CLASSIFICATION: " + playerData[4] );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( "//OVERALL STRENGTH CLASSIFICATION: "+ playerData[5] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( ">MEDICAL (1/MEDS)" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">SKILLS (2/SKLS)" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">BACK (0/BACK)" );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "MEDICAL": case "MEDS": case "medical": case "meds": case "1":
                    TimeUnit.SECONDS.sleep( ShortDelay );
                    System.out.println("//ERROR: DRIVE SECTOR CORRUPTED.");
                    TimeUnit.SECONDS.sleep( ShortDelay );
                    System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU");
                    TimeUnit.SECONDS.sleep( ShortDelay );
                    System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
                    boolean medOptionNotChosen = true;
                    while ( medOptionNotChosen ) {
                        switch ( input.next() ) {
                            case "1":
                                mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                                break;
                            case "2":
                                shutdown();
                            default:
                                System.out.println( "//ERROR: INVALID INPUT." );
                                break;
                        }
                    }
                    break;
                case "SKILLS": case "SKLS": case "skills": case "skls": case "2":
                    System.out.println("//ERROR: DRIVE SECTOR CORRUPTED.");
                    TimeUnit.SECONDS.sleep( ShortDelay );
                    System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU");
                    TimeUnit.SECONDS.sleep( ShortDelay );
                    System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
                    boolean skillOptionNotChosen = true;
                    while ( skillOptionNotChosen ) {
                        switch ( input.next() ) {
                            case "1":
                                mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                                break;
                            case "2":
                                shutdown();
                            default:
                                System.out.println( "//ERROR: INVALID INPUT." );
                                break;
                        }
                    }
                case "BACK": case "back": case "0":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }

    }

    public static void inventory(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        System.out.print( "//USER | " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( playerData[0] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( " > INVENTORY" );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( ">WEAPONS (1/WEAP)" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">AMMUNITION (2/AMMO)" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">EQUIPMENT (3/EQUP)" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">MEDICAL (4/MEDS) " );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">MISCELLANEOUS (5/MISC) " );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">BACK (0/BACK) " );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "WEAPONS": case "weapons": case "WEAP": case "weap": case "1":
                    weapons( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "AMMUNITION": case "ammunition": case "AMMO": case "ammo": case "2":
                    ammunition( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "EQUIPMENT": case "equipment": case "EQUP": case "equp": case "3":
                    equipment ( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "MEDICAL": case "medical": case "MEDS": case "meds": case "4":
                    medical ( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "MISCELLANEOUS": case "miscellaneous": case "MISC": case "misc": case "5":
                    miscellaneous( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "BACK": case "back": case "0":
                    mmain( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
        
    }



    public static void weapons(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
    ClearScreen();
        String[] playerData = new String[7];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        System.out.print( "//USER | " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( playerData[0] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( " > INVENTORY" );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( " > WEAPONS" );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( "//SCANNING ITEM REGISTRY..." );
        File TESTWEAP = new File(daVGDrive[1]+"\\TESTWEAP");
        File AB_45 = new File(daVGDrive[1]+"\\AB_45");
        File LP61 = new File(daVGDrive[1]+"\\LP61");
        File MP60 = new File(daVGDrive[1]+"\\MP60");
        File P1885 = new File(daVGDrive[1]+"\\P.1885");
        File P60 = new File(daVGDrive[1]+"\\P60");
        if (weaponsCheck( userProfile, daVGDrive, daVGDir, playerSheet )){
            TimeUnit.SECONDS.sleep( ShortDelay );
            System.out.println("COMPLETE");
            TimeUnit.SECONDS.sleep( ShortDelay );
            if (TESTWEAP.isDirectory()){
                if (playerData[6].equals("000")){
                    System.out.println( ">TESTWEAP [CW]" );
                }
                else{
                    System.out.println( ">TESTWEAP" );
                }
            }
            if (AB_45.isDirectory()){
                if (playerData[6].equals("000")){
                    System.out.println( ">AB_45 [CW]" );
                }
                else{
                    System.out.println( ">AB_45" );
                }
            }
            if (LP61.isDirectory()){
                if (playerData[6].equals("000")){
                    System.out.println( ">LP61 [CW]" );
                }
                else{
                    System.out.println( ">LP61" );
                }
            }
            if (MP60.isDirectory()){
                if (playerData[6].equals("000")){
                    System.out.println( ">MP60 [CW]" );
                }
                else{
                    System.out.println( ">MP60" );
                }
            }
            if (P1885.isDirectory()){
                if (playerData[6].equals("000")){
                    System.out.println( ">P.1885 [CW]" );
                }
                else{
                    System.out.println( ">P.1885" );
                }
            }
            if (P60.isDirectory()){
                if (playerData[6].equals("000")){
                    System.out.println( ">P60 [CW]" );
                }
                else{
                    System.out.println( ">P60" );
                }
            }
        }
        else{
            TimeUnit.SECONDS.sleep(3);
            System.out.println("ERROR");
            TimeUnit.MILLISECONDS.sleep( NoDelay );
            System.out.println("//NO ITEMS MATCHING FILTER FOUND IN INVENTORY!");
        }
        System.out.println(">BACK (0/BACK)");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "TESTWEAP": case "testweap":
                    if (TESTWEAP.isDirectory()){
                        weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "TESTWEAP");
                    }
                    else{
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "AB_45": case "ab_45":
                    if (AB_45.isDirectory()){
                        weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "AB_45");
                    }
                    else{
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "LP61": case "lp61":
                    if (LP61.isDirectory()){
                        weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "LP61");
                    }
                    else{
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "MP60": case "mp60":
                    if (MP60.isDirectory()){
                        weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "mp60");
                    }
                    else{
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "P.1885": case "p.1885":
                    if (P1885.isDirectory()){
                        weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "P.1885");
                    }
                    else{
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "P60": case "p60":
                    if (P60.isDirectory()){
                        weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "P60");
                    }
                    else{
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "BACK": case "back": case "0":
                    inventory( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }

    }
    
    public static boolean weaponsCheck(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet){
        File testweap = new File(daVGDrive[1]+"\\TESTWEAP");
        if (testweap.isDirectory()){
            return true;
        }
        File AB_45 = new File(daVGDrive[1]+"\\AB_45");
        if (AB_45.isDirectory()){
            return true;
        }
        File LP61 = new File(daVGDrive[1]+"\\LP61");
        if (LP61.isDirectory()){
            return true;
        }
        File MP60 = new File(daVGDrive[1]+"\\MP60");
        if (MP60.isDirectory()){
            return true;
        }
        File P1885 = new File(daVGDrive[1]+"\\P.1885");
        if (P1885.isDirectory()){
            return true;
        }
        File P60 = new File(daVGDrive[1]+"\\P60");
        if (P60.isDirectory()){
            return true;
        }
        else{
            return false;
        }
    }

    public static void weaponLoad(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet, String weaponName) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        System.out.print( "//USER | " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( playerData[0] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( " > INVENTORY" );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( " > WEAPONS" );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( " > "+weaponName );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print("//ANALYZING ITEM IDENTIFICATION TAG...");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("COMPLETE");
        String [] weaponData = new String[18];
        BufferedReader weaponScanner = new BufferedReader(new FileReader(daVGDrive[1]+"\\"+weaponName+"\\weaponData.txt"));
        try {
            weaponData = weaponScanner.readLine().split("[~]");
            weaponScanner.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode) {
            System.out.println(Arrays.toString(weaponData));
            TimeUnit.SECONDS.sleep(10);
        }
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( "  " + weaponData[0] + " | ");
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( weaponData[1] + " | " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( " FR " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( weaponData[3] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( " / " + "ID No. " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println(weaponData[17]);
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//MANUFACTURER: "+weaponData[4] );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( "//DAMG: " + weaponData[5] );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( "//ACCT: " + weaponData[6] );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( "//CRHC: " + weaponData[7] );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( "//CRHT: " + weaponData[8] );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( "//CRHM: " + weaponData[9] );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        if (weaponData[2].equals("RANGED")) {
            System.out.println("//EFFR: " + weaponData[10]);
            TimeUnit.MILLISECONDS.sleep( NoDelay );
            System.out.println("//AMMO: " + weaponData[11]);
            TimeUnit.MILLISECONDS.sleep( NoDelay );
            System.out.println("//MGZN: " + weaponData[12]);
            TimeUnit.MILLISECONDS.sleep( NoDelay );
            System.out.println("//AUPS: " + weaponData[13]);
            TimeUnit.MILLISECONDS.sleep( NoDelay );
            System.out.println("//FRMD: " + weaponData[14]);
        }
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( "//TOOT: " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( weaponData[15] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//DESC: " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( weaponData[16] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( ">REFRESH" );
        TimeUnit.MILLISECONDS.sleep( NoDelay );
        System.out.println( ">BACK" );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "REFRESH": case "refresh": case "R": case "r":
                    weaponLoad( userProfile, daVGDrive, daVGDir, playerSheet, weaponName );
                    break;
                case "EQUIP": case "equip": case "1":
                    System.out.println("//ARE YOU SURE YOU WANT TO EQUIP THIS WEAPON?");
                    boolean equipOptionNotChosen = true;
                    while ( equipOptionNotChosen ) {
                        switch ( input.next() ) {
                            case "Y": case "y": case "1":

                                weapons(userProfile, daVGDrive, daVGDir, playerSheet);
                                break;
                            case "N": case "n": case "0":
                                System.out.println("//RETURNING TO WEAPONS MENU.");
                                TimeUnit.SECONDS.sleep(LongDelay);

                            default:
                                System.out.println( "//ERROR: INVALID INPUT." );
                                break;
                        }
                    }
                case "BACK": case "back": case "0":
                    weapons( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }

    }

    public static void ammunition(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) {

    }

    public static void equipment(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) {

    }

    public static void medical(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) {

    }

    public static void miscellaneous(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) {

    }


    public static void journal(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        System.out.print( "//USER | " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( playerData[0] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( " > JOURNAL" );
        TimeUnit.SECONDS.sleep(2);
        System.out.println("//ERROR: DRIVE SECTOR CORRUPTED.");
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU");
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
        boolean OptionNotChosen = true;
        while ( OptionNotChosen ) {
            switch ( input.next() ) {
                case "1":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                case "2":
                    shutdown();
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
    }

    public static void utility(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        System.out.print( "//USER | " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( playerData[0] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( " > UTILITY" );
        TimeUnit.SECONDS.sleep(2);
        System.out.println("//ERROR: DRIVE SECTOR CORRUPTED.");
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU");
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
        boolean OptionNotChosen = true;
        while ( OptionNotChosen ) {
            switch ( input.next() ) {
                case "1":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                case "2":
                    shutdown();
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
    }

    public static void combat(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        System.out.print( "//USER | " );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.print( playerData[0] );
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( " > COMBAT" );
        TimeUnit.SECONDS.sleep(2);
        System.out.println("//ERROR: DRIVE SECTOR CORRUPTED.");
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU");
        TimeUnit.SECONDS.sleep( ShortDelay );
        System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
        boolean OptionNotChosen = true;
        while ( OptionNotChosen ) {
            switch ( input.next() ) {
                case "1":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                case "2":
                    shutdown();
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
    }

    public static void shutdown() throws InterruptedException, IOException {
        System.out.println( "INITIATING SHUTDOWN..." );
        TimeUnit.SECONDS.sleep( 5 );
        System.exit( 0 );
        ClearScreen();
    }
}
