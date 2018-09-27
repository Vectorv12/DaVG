package com.VectorFoundry;


import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class DaVG {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StringBuilder builder;
    private static Scanner input;
    private static boolean debugMode;
    private static String VersionNo;
    private static String userName;

    private static Boolean cutscenesEnabled;
    private static int LongDelay;
    private static int MedDelay;
    private static int ShortDelay;
    private static int NoDelay;
    private static String[] daVGDir;
    private static File[] daVGDrive;
    private static String DirPath;
    private static String userProfile;
    private static Scanner petc;

    private static String cWNAME;
    private static String cWROLE;
    private static String cWTYPE;
    private static int cWFR;
    private static String cWMFGR;
    private static int cWDAMG;
    private static int cWACCT;
    private static String cWCRHC;
    private static int cWCRHT;
    private static double cWCRHM;
    private static String cWEFFR;
    private static String cWAMMO;
    private static int cWMGZN;
    private static int cWAUPS;
    private static String cWFRMD;
    private static int cWID;

    // \/SIDEARMS\/
    private static File TESTWEAP;
    private static File TESTWEAPTAG;

    private static File FtA_45;
    private static File FtA_45TAG;

    private static File LP61;
    private static File LP61TAG;

    private static File MP60;
    private static File MP60TAG;

    private static File P1885;
    private static File P1885TAG;

    private static File P60;
    private static File P60TAG;
    // /\ SIDEARMS /\


    public static void main( String[] args ) throws InterruptedException, IOException {
        VersionNo = "V0.1";
        debugMode = false;
        cutscenesEnabled = true;
        LongDelay = 3;
        MedDelay = 2;
        ShortDelay = 1;
        NoDelay = 100;
        userProfile = System.getenv("USERPROFILE");
        DirPath = userProfile + "\\Documents\\DaVG_AusfJ_";
        input = new Scanner(System.in);
        petc = new Scanner(System.in);
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

        /**The program begins by initializing the DaVG's operating system, known as KRIEGSVERSTAND, or "War Mind". In the
         * context of the Multiverse, KRIEGSVERSTAND is a reliable ( if perhaps a bit spartan ) operating system built
         * specifically for the DaVG platform.
         *
         * DaVG Ausf. J begins by stating that its built-in operating system, KRIEGSVERSTAND V2, is being initialized.
         */

        ClearScreen();
        System.out.println(">PRESS ENTER TO BEGIN BOOT PROCESS<");
        petc.nextLine();
        ClearScreen();
        System.out.println("//Initializing KRIEGSVERSTAND V2...");
        MedDelay();
        System.out.print("//CHECKING RAM... ");
        MedDelay();
        System.out.println("128K RAM AVAILABLE.");
        ShortDelay();
        System.out.print("//CHECKING MEMORY... ");
        MedDelay();
        System.out.println("77822 BYTES AVAILABLE.");
        ShortDelay();
        System.out.print("//READING HARD DRIVE... ");

        /** At this point, the program will attempt to locate a folder labeled "DaVG_AusfJ_(Version No.)_Data
         * in the user's Documents folder. This folder contains the various profiles the user has created, and
         * will only exist if the user has run the program previously. If it does not exist, then the program will
         * create the folder itself and then state that there is a (fictitious) error in the DaVG's hard drive,
         * and prompt the user to create a new user profile.
         *
         * However, if the folder does exist, the program will list all the profiles contained within them, and prompt
         * the user to select one.
         *
         * The user also has the (not stated) option of entering several parameters to enable or disable certain
         * features, such as the "loading" screens.
         */

        File DataDirectory = new File(DirPath + VersionNo + "_Data\\");
        String[] profileList = DataDirectory.list();
        if (!DataDirectory.isDirectory()) {
            DataDirectory.mkdir();
        }
        if (profileList.length == 0) {
            LongDelay();
            System.out.println("FAILED");
            ShortDelay();
            System.out.println("//ERROR: NO PROFILES FOUND IN HARD DRIVE");
            ShortDelay();
            System.out.println("//CREATE NEW PROFILE? Y/N");
            ShortDelay();
            System.out.println("//WARNING: CHOOSING \"N\" WILL DEACTIVATE UNIT TO PREVENT TAMPERING");
            boolean optionNotChosen = true;
            while (optionNotChosen) {
                if (debugMode) {
                    System.out.println(profileList);
                }
                switch (input.next()) {
                    case "Y": case "y":
                        optionNotChosen = false;
                        System.out.println("//PLEASE INPUT PROFILE NAME.");
                        ShortDelay();
                        System.out.println("//WARNING: PROFILE NAMES ARE CASE SENSITIVE");
                        userName = input.next();
                        break;
                    case "N": case "n":
                        optionNotChosen = false;
                        shutdown();
                        break;
                    case "#KVSEDBM":
                        optionNotChosen = true;
                        debugMode = true;
                        System.out.println("//MAINTENANCE MODE ENABLED");
                        break;
                    case "#KVSDDBM":
                        optionNotChosen = true;
                        debugMode = false;
                        System.out.println("//MAINTENANCE MODE DISABLED");
                        break;
                    case "#KVSECS":
                        optionNotChosen = true;
                        cutscenesEnabled = true;
                        System.out.println("//ACCELERATED READ/WRITE DISABLED");
                        break;
                    case "#KVSDCS":
                        optionNotChosen = true;
                        cutscenesEnabled = false;
                        System.out.println("//ACCELERATED READ/WRITE ENABLED");
                        break;
                    default:
                        System.out.println("//ERROR: INVALID INPUT.");
                        break;
                }
            }
        } else {
            ShortDelay();
            System.out.println("SUCCESSFUL");
            ShortDelay();
            for (String profileName : profileList) {
                System.out.println(">" + profileName);
            }
            System.out.println("//PLEASE SELECT A USER PROFILE");
            ShortDelay();
            System.out.println("//WARNING: PROFILE NAMES ARE CASE SENSITIVE");
            boolean profileNotChosen = true;
            while (profileNotChosen) {
                String profileInput = input.next();
                switch (profileInput) {
                    case "#KVSEDBM":
                        profileNotChosen = true;
                        debugMode = true;
                        System.out.println("//MAINTENANCE MODE ENABLED");
                        break;
                    case "#KVSDDBM":
                        profileNotChosen = true;
                        debugMode = false;
                        System.out.println("//MAINTENANCE MODE DISABLED");
                        break;
                    case "#KVSECS":
                        profileNotChosen = true;
                        cutscenesEnabled = true;
                        System.out.println("//ACCELERATED READ/WRITE DISABLED");
                        break;
                    case "#KVSDCS":
                        profileNotChosen = true;
                        cutscenesEnabled = false;
                        System.out.println("//ACCELERATED READ/WRITE ENABLED");
                        break;
                    default:
                        profileNotChosen = false;
                        userName = profileInput;
                }
            }
        }
        File requestedProfile = new File( DirPath + VersionNo + "_Data\\" + userName + "\\" );
        if (!requestedProfile.isDirectory()){
            System.out.println( "//ERROR: SPECIFIED USER PROFILE NOT FOUND" );
            ShortDelay();
            System.out.println( "//CREATE NEW USER PROFILE WITH THIS NAME? Y/N" );
            ShortDelay();
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
                    case "#KVSEDBM":
                        optionNotChosen = true;
                        debugMode = true;
                        System.out.println( "//MAINTENANCE MODE ENABLED" );
                        break;
                    case "#KVSDDBM":
                        optionNotChosen = true;
                        debugMode = false;
                        System.out.println( "//MAINTENANCE MODE DISABLED" );
                        break;
                    case ">delays_enable":
                        optionNotChosen = true;
                        debugMode = true;
                        System.out.println( "//MAINTENANCE MODE ENABLED" );
                        break;
                    case ">delays_disable":
                        optionNotChosen = true;
                        debugMode = false;
                        System.out.println( "//MAINTENANCE MODE DISABLED" );
                        break;
                    case "#KVSECS":
                        optionNotChosen = true;
                        cutscenesEnabled = true;
                        System.out.println("//ACCELERATED READ/WRITE DISABLED");
                        break;
                    case "#KVSDCS":
                        optionNotChosen = true;
                        cutscenesEnabled = false;
                        System.out.println("//ACCELERATED READ/WRITE ENABLED");
                        break;
                    default:
                        System.out.println( "//ERROR: INVALID INPUT." );
                        break;
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
            if (debugMode){
              System.out.println(playerSheet);
            }
            reader = new BufferedReader(new FileReader(playerSheet));
            builder = new StringBuilder();
            String playerData;
            while ((playerData = reader.readLine()) != null) {
                builder.append(playerData);
            }
            playerData = builder.toString();
            boolean retUser = dataExists && !playerData.isEmpty();
            if (retUser) {
                ClearScreen();
                if (debugMode) {
                    System.out.println(playerData);
                    System.out.println(System.getProperty("os.name"));
                }
                System.out.print( "//LOADING PROFILE..." );
                LongDelay();
                System.out.println( "COMPLETE" );
                ShortDelay();
                System.out.println( "//PLEASE CHOOSE BOOT SEQUENCE" );
                ShortDelay();
                System.out.println(">FULL ( INCLUDES FULL DIAGNOSTIC BATTERY )");
                NoDelay();
                System.out.println(">SHORT ( INCLUDES TRUNCATED DIAGNOSTIC BATTERY )");
                NoDelay();
                System.out.println(">SKIP ( DIAGNOSTICS ARE NOT RUN )");
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
                        case "#KVSEDBM":
                            optionNotChosen = true;
                            debugMode = true;
                            System.out.println( "//MAINTENANCE MODE ENABLED" );
                            break;
                        case "#KVSDDBM":
                            optionNotChosen = true;
                            debugMode = false;
                            System.out.println( "//MAINTENANCE MODE DISABLED" );
                            break;
                        case "#KVSECS":
                            optionNotChosen = true;
                            cutscenesEnabled = true;
                            System.out.println("//ACCELERATED READ/WRITE DISABLED");
                            break;
                        case "#KVSDCS":
                            optionNotChosen = true;
                            cutscenesEnabled = false;
                            System.out.println("//ACCELERATED READ/WRITE ENABLED");
                            break;
                        default:
                            System.out.println("//ERROR: INVALID INPUT");
                            break;
                    }
                }

            } else {
                LongDelay();
                System.out.println( "//ERROR: NO DATA IN SPECIFIED DRIVE SECTOR" );
                ShortDelay();
                System.out.println( "//FAULT IN HARD DRIVE SUSPECTED" );
                ShortDelay();
                intro(userProfile, retUser, daVGDrive, daVGDir, playerSheet);
            }
        } else {
            boolean retUser = false;
            intro(userProfile, retUser, daVGDrive, daVGDir, playerSheet);
        }
    }

    private static void ClearScreen() throws IOException, InterruptedException {
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
                TenSecondDelay();
                System.exit( 1 );
                break;
        }
    }

    private static void TenSecondDelay() throws InterruptedException{
        if (cutscenesEnabled){
            TimeUnit.SECONDS.sleep( 10 );
        }
    }
    private static void ShutoffDelay() throws InterruptedException{
        if (cutscenesEnabled){
            TimeUnit.SECONDS.sleep( 5 );
        }
    }
    private static void LongDelay() throws InterruptedException {
        if (cutscenesEnabled) {
            TimeUnit.SECONDS.sleep(LongDelay);
        }
    }
    private static void MedDelay() throws InterruptedException {
        if (cutscenesEnabled) {
            TimeUnit.SECONDS.sleep( MedDelay );
        }
    }
    private static void ShortDelay() throws InterruptedException {
        if (cutscenesEnabled){
            TimeUnit.SECONDS.sleep( ShortDelay );
        }
    }
    private static void NoDelay() throws InterruptedException {
        if (cutscenesEnabled){
            TimeUnit.MILLISECONDS.sleep( NoDelay );
        }
    }

    private static void intro(String userProfile, boolean retUser, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        System.out.println( "//INITIATING DIAGNOSTIC TEST BATTERY..." );
        ShortDelay();
        System.out.print( "//CORE 1..." );
        MedDelay();
        System.out.println( "OK" );
        NoDelay();
        System.out.print( "//CORE 2..." );
        ShortDelay();
        System.out.println( "OK" );
        NoDelay();
        System.out.print( "//CORE 3..." );
        ShortDelay();
        System.out.println( "OK" );
        NoDelay();
        System.out.print( "//CORE 4..." );
        MedDelay();
        System.out.println( "OK" );
        NoDelay();
        System.out.print( "//CORE 5..." );
        ShortDelay();
        System.out.println( "OK" );
        NoDelay();
        System.out.print( "//REACTOR CORE..." );
        MedDelay();
        System.out.println( "OK" );
        NoDelay();
        System.out.print( "//FUEL LEVELS..." );
        ShortDelay();
        System.out.println( "OK" );
        NoDelay();
        System.out.print( "//COOLANT LEVELS..." );
        LongDelay();
        System.out.println( "OK" );
        NoDelay();
        intro2( userProfile, retUser, daVGDrive, daVGDir, playerSheet );

    }

    private static void intro2(String userProfile, boolean retUser, File[] daVGDrive, String[] daVGDir,  File playerSheet) throws InterruptedException, IOException {
        System.out.print( "//CNMI CONNECTION..." );
        if ( retUser ) {
            System.out.println( "OK" );
            ShortDelay();
            System.out.println( "//USER MATCHES 1 ENTRY IN REGISTRY." );
            NoDelay();
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
            ShortDelay();
            System.out.println( "//WELCOME BACK, " + playerData[0] );
            ShortDelay();
            System.out.println( "//PRESS ENTER TO CONTINUE");
            petc.nextLine();
            mmain(userProfile, daVGDrive, daVGDir, playerSheet);

        } else {
            System.out.println( "FAILED" );
            ShortDelay();
            System.out.println( "//WARNING: NO CNMI DETECTED WITHIN USER." );
            NoDelay();
            System.out.println( "//OVERRIDE? Y/N" );
            ShortDelay();
            System.out.println( "//WARNING: CHOOSING \"N\" WILL DEACTIVATE UNIT TO PREVENT TAMPERING");
            boolean optionNotChosen = true;
            while ( optionNotChosen ) {
                switch ( input.next() ) {
                    case "Y": case "y":
                        System.out.println( "//OVERRIDING..." );
                        LongDelay();
                        optionNotChosen = false;
                        newuser( userProfile, daVGDrive, daVGDir, playerSheet);
                        break;
                    case "N": case "n": case"SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                        shutdown();
                        break;
                    case "#KVSEDBM":
                        optionNotChosen = true;
                        debugMode = true;
                        System.out.println( "//MAINTENANCE MODE ENABLED" );
                        break;
                    case "#KVSDDBM":
                        optionNotChosen = true;
                        debugMode = false;
                        System.out.println( "//MAINTENANCE MODE DISABLED" );
                        break;
                    case "#KVSECS":
                        optionNotChosen = true;
                        cutscenesEnabled = true;
                        System.out.println("//ACCELERATED READ/WRITE DISABLED");
                        break;
                    case "#KVSDCS":
                        optionNotChosen = true;
                        cutscenesEnabled = false;
                        System.out.println("//ACCELERATED READ/WRITE ENABLED");
                        break;
                    default:
                        System.out.println( "//ERROR: INVALID INPUT." );
                        break;
                }
            }
        }
    }

    private static void newuser(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws InterruptedException, IOException {
        System.out.println( "//DIAGNOSTIC TESTS COMPLETED." );
        LongDelay();
        System.out.println( "//ERROR: USER DOES NOT MATCH ANY ENTRIES IN USER REGISTRY." );
        LongDelay();
        System.out.println( "//NO ADMINISTRATORS IN USER REGISTRY." );
        LongDelay();
        System.out.println( "//NO SECURITY MEASURES DEFINED BY PRIOR USERS." );
        ShortDelay();
        System.out.println( "//CREATE NEW USER? Y/N" );
        ShortDelay();
        System.out.println( "//WARNING: CHOOSING \"N\" WILL DEACTIVATE UNIT TO PREVENT TAMPERING");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "Y": case "y":
                    optionNotChosen = false;
                    break;
                case "N": case "n": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown();
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
        String [] playerData = new String[6];
        System.out.println( "//INPUT NEW USER NAME." );
        ShortDelay();
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
        LongDelay();
        System.out.println( "//DATA WRITING COMPLETE." );
        ShortDelay();
        System.out.println( "//NEW ENTRY CREATED IN USER REGISTRY." );
        ShortDelay();
        System.out.println( "//GREETINGS, "+playerData[0] );
        ShortDelay();
        System.out.println( "//PRESS ENTER TO CONTINUE");
        petc.nextLine();
        mmain(userProfile, daVGDrive, daVGDir, playerSheet);
    }

    private static void mmain(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws InterruptedException, IOException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.println( playerData[0] );
        ShortDelay();
        System.out.println( ">VITALS (1/VITL)" );
        NoDelay();
        System.out.println( ">INVENTORY (2/INVT)" );
        NoDelay();
        System.out.println( ">JOURNAL (3/JRNL)" );
        NoDelay();
        System.out.println( ">UTILITY (4/UTIL) " );
        NoDelay();
        System.out.println( ">COMBAT (5/CMBT) " );
        NoDelay();
        System.out.println( ">SHUTDOWN (P/POWR) " );
        NoDelay();
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
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown();
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }

    }

    private static void vitals(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.println( " > VITALS" );
        ShortDelay();
        System.out.println( "//INITIALIZING VITAL SIGNS ANALYSIS");
        NoDelay();
        System.out.print( "//VITALS ANALYSIS..." );
        LongDelay();
        System.out.println( "COMPLETE" );
        ShortDelay();
        System.out.println( "//CURRENT HEALTH: " + playerData[1] + "/" + playerData[2] + " HP" );
        NoDelay();
        System.out.println( "//OVERALL VITAL STATUS: " + playerData[3] );
        NoDelay();
        System.out.println( "//MOVEMENT SPEED CLASSIFICATION: " + playerData[4] );
        NoDelay();
        System.out.println( "//OVERALL STRENGTH CLASSIFICATION: "+ playerData[5] );
        ShortDelay();
        System.out.println( ">MEDICAL (1/MEDS)" );
        NoDelay();
        System.out.println( ">SKILLS (2/SKLS)" );
        NoDelay();
        System.out.println( ">BACK (0/BACK)" );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "MEDICAL": case "MEDS": case "medical": case "meds": case "1":
                    ShortDelay();
                    System.out.println("//ERROR: DRIVE SECTOR CORRUPTED.");
                    ShortDelay();
                    System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU");
                    ShortDelay();
                    System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
                    boolean medOptionNotChosen = true;
                    while ( medOptionNotChosen ) {
                        switch ( input.next() ) {
                            case "1":
                                mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                                break;
                            case "2":
                                shutdown();
                            case "#KVSEDBM":
                                medOptionNotChosen = true;
                                debugMode = true;
                                System.out.println( "//MAINTENANCE MODE ENABLED" );
                                break;
                            case "#KVSDDBM":
                                medOptionNotChosen = true;
                                debugMode = false;
                                System.out.println( "//MAINTENANCE MODE DISABLED" );
                                break;
                            case "#KVSECS":
                                medOptionNotChosen = true;
                                cutscenesEnabled = true;
                                System.out.println("//ACCELERATED READ/WRITE DISABLED");
                                break;
                            case "#KVSDCS":
                                medOptionNotChosen = true;
                                cutscenesEnabled = false;
                                System.out.println("//ACCELERATED READ/WRITE ENABLED");
                                break;
                            default:
                                System.out.println( "//ERROR: INVALID INPUT." );
                                break;
                        }
                    }
                    break;
                case "SKILLS": case "SKLS": case "skills": case "skls": case "2":
                    System.out.println("//ERROR: DRIVE SECTOR CORRUPTED.");
                    ShortDelay();
                    System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU");
                    ShortDelay();
                    System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
                    boolean skillOptionNotChosen = true;
                    while ( skillOptionNotChosen ) {
                        switch ( input.next() ) {
                            case "1":
                                mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                                break;
                            case "2":
                                shutdown();
                            case "#KVSEDBM":
                                skillOptionNotChosen = true;
                                debugMode = true;
                                System.out.println( "//MAINTENANCE MODE ENABLED" );
                                break;
                            case "#KVSDDBM":
                                skillOptionNotChosen = true;
                                debugMode = false;
                                System.out.println( "//MAINTENANCE MODE DISABLED" );
                                break;
                            case "#KVSECS":
                                skillOptionNotChosen = true;
                                cutscenesEnabled = true;
                                System.out.println("//ACCELERATED READ/WRITE DISABLED");
                                break;
                            case "#KVSDCS":
                                skillOptionNotChosen = true;
                                cutscenesEnabled = false;
                                System.out.println("//ACCELERATED READ/WRITE ENABLED");
                                break;
                            default:
                                System.out.println( "//ERROR: INVALID INPUT." );
                                break;
                        }
                    }
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown();
                    break;
                case "BACK": case "back": case "0":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }

    }

    private static void inventory(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.println( " > INVENTORY" );
        ShortDelay();
        System.out.println( ">WEAPONS (1/WEAP)" );
        NoDelay();
        System.out.println( ">AMMUNITION (2/AMMO)" );
        NoDelay();
        System.out.println( ">EQUIPMENT (3/EQUP)" );
        NoDelay();
        System.out.println( ">MEDICAL (4/MEDS) " );
        NoDelay();
        System.out.println( ">MISCELLANEOUS (5/MISC) " );
        NoDelay();
        System.out.println( ">BACK (0/BACK) " );
        NoDelay();
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
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown();
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }

    }

    private static void weapons(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
    ClearScreen();
        String[] playerData = new String[7];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.print( " > INVENTORY" );
        NoDelay();
        System.out.println( " > WEAPONS" );
        NoDelay();
        System.out.print( "//SCANNING ITEM REGISTRY..." );

        TESTWEAP = new File(daVGDrive[1]+"\\TESTWEAP");
        TESTWEAPTAG = new File(daVGDrive[1]+"\\TESTWEAP\\ItemID.TAG");

        FtA_45 = new File(daVGDrive[1]+"\\FtA_45");
        FtA_45TAG = new File(daVGDrive[1]+"\\FtA_45\\ItemID.TAG");

        LP61 = new File(daVGDrive[1]+"\\LP61");
        LP61TAG = new File(daVGDrive[1]+"\\LP61\\ItemID.TAG");

        MP60 = new File(daVGDrive[1]+"\\MP60");
        MP60TAG = new File(daVGDrive[1]+"\\MP60\\ItemID.TAG");

        P1885 = new File(daVGDrive[1]+"\\P.1885");
        P1885TAG = new File(daVGDrive[1]+"\\P.1885\\ItemID.TAG");

        P60 = new File(daVGDrive[1]+"\\P60");
        P60TAG = new File(daVGDrive[1]+"\\P60\\ItemID.TAG");

        if (weaponsCheck( userProfile, daVGDrive, daVGDir, playerSheet )){
            ShortDelay();
            System.out.println("COMPLETE");
            ShortDelay();
            if (TESTWEAP.isDirectory()){
                if (TESTWEAPTAG.isFile()){
                    if (playerData[6].equals("000")){
                        System.out.println( ">No. 000 | TESTWEAP [CW]" );
                    } else {
                        System.out.println( ">No. 000 | TESTWEAP" );
                    }
                } else {
                    System.out.println(">No. 000 | TESTWEAP [ERROR: CANNOT READ IDENTIFICATION TAG]");
                }
            }
            if (FtA_45.isDirectory()){
                if (FtA_45TAG.isFile()){
                    if (playerData[6].equals("002")){
                        System.out.println( ">No. 002 | FtA_45 [CW]" );
                    } else {
                        System.out.println( ">No. 002 | FtA_45" );
                    }
                } else {
                    System.out.println(">No. 002 | FtA_45 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                }
            }
            if (LP61.isDirectory()){
                if (LP61TAG.isFile()){
                    if (playerData[6].equals("003")){
                        System.out.println( ">No. 003 | LP61 [CW]" );
                    } else {
                        System.out.println( ">No. 003 | LP61" );
                    }
                } else {
                    System.out.println(">No. 003 | LP61 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                }
            }
            if (MP60.isDirectory()){
                if (MP60TAG.isFile()){
                    if (playerData[6].equals("004")){
                        System.out.println( ">No. 004 | MP60 [CW]" );
                    } else {
                        System.out.println( ">No. 004 | MP60" );
                    }
                } else {
                    System.out.println(">No. 004 | MP60 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                }
            }
            if (P1885.isDirectory()){
                if (P1885TAG.isFile()){
                    if (playerData[6].equals("005")){
                        System.out.println( ">No. 005 | P.1885 [CW]" );
                    } else {
                        System.out.println( ">No. 005 | P.1885" );
                    }
                } else {
                    System.out.println(">No. 005 | P.1885 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                }
            }
            if (P60.isDirectory()){
                if (P60TAG.isFile()){
                    if (playerData[6].equals("006")){
                        System.out.println( ">No. 006 | P60 [CW]" );
                    } else {
                        System.out.println( ">No. 006 | P60" );
                    }
                } else {
                    System.out.println(">No. 006 | P60 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                }
            }
        }
        else{
            LongDelay();
            System.out.println("ERROR");
            NoDelay();
            System.out.println("//NO ITEMS MATCHING FILTER FOUND IN INVENTORY!");
        }
        System.out.println(">REFRESH (R/REFR)");
        ShortDelay();
        System.out.println(">BACK (0/BACK)");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "TESTWEAP": case "testweap": case "000":
                    if (TESTWEAP.isDirectory()){
                        if (TESTWEAPTAG.isFile()){
                            weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "TESTWEAP");
                        } else {
                            LongDelay();
                            System.out.println("//ERROR: ITEM TAG UNREADABLE");
                            ShortDelay();
                            System.out.println("//SYSTEM UNABLE TO LOAD ITEM DATA");
                            ShortDelay();
                            System.out.println("//ITEM IDENTIFICATION TAG MAY BE DAMAGED OR MISSING");
                        }
                    } else {
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "FtA_45": case "fta_45": case "002":
                    if (FtA_45.isDirectory()){
                        if (FtA_45TAG.isFile()){
                            weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "FtA_45");
                        } else {
                            LongDelay();
                            System.out.println("//ERROR: ITEM TAG UNREADABLE");
                            ShortDelay();
                            System.out.println("//SYSTEM UNABLE TO LOAD ITEM DATA");
                            ShortDelay();
                            System.out.println("//ITEM IDENTIFICATION TAG MAY BE DAMAGED OR MISSING");
                        }
                    } else {
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "LP61": case "lp61": case "003":
                    if (LP61.isDirectory()){
                        if (LP61TAG.isFile()){
                            weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "LP61");
                        } else {
                            LongDelay();
                            System.out.println("//ERROR: ITEM TAG UNREADABLE");
                            ShortDelay();
                            System.out.println("//SYSTEM UNABLE TO LOAD ITEM DATA");
                            ShortDelay();
                            System.out.println("//ITEM IDENTIFICATION TAG MAY BE DAMAGED OR MISSING");
                        }
                    } else {
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "MP60": case "mp60": case "004":
                    if (MP60.isDirectory()){
                        if (MP60TAG.isFile()){
                            weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "MP60");
                        } else {
                            LongDelay();
                            System.out.println("//ERROR: ITEM TAG UNREADABLE");
                            ShortDelay();
                            System.out.println("//SYSTEM UNABLE TO LOAD ITEM DATA");
                            ShortDelay();
                            System.out.println("//ITEM IDENTIFICATION TAG MAY BE DAMAGED OR MISSING");
                        }
                    } else {
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "P.1885": case "p.1885": case "005":
                    if (P1885.isDirectory()){
                        if (P1885TAG.isFile()){
                            weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "P.1885");
                        } else {
                            LongDelay();
                            System.out.println("//ERROR: ITEM TAG UNREADABLE");
                            ShortDelay();
                            System.out.println("//SYSTEM UNABLE TO LOAD ITEM DATA");
                            ShortDelay();
                            System.out.println("//ITEM IDENTIFICATION TAG MAY BE DAMAGED OR MISSING");
                        }
                    } else {
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "P60": case "p60": case "006":
                    if (P60.isDirectory()){
                        if (P60TAG.isFile()){
                            weaponLoad(userProfile, daVGDrive, daVGDir, playerSheet, "P60");
                        } else {
                            LongDelay();
                            System.out.println("//ERROR: ITEM TAG UNREADABLE");
                            ShortDelay();
                            System.out.println("//SYSTEM UNABLE TO LOAD ITEM DATA");
                            ShortDelay();
                            System.out.println("//ITEM IDENTIFICATION TAG MAY BE DAMAGED OR MISSING");
                        }
                    } else {
                        System.out.println("//ERROR: SPECIFIED WEAPON MISSING!");
                    }
                    break;
                case "REFRESH": case "refresh": case "REFR": case "refr": case "R": case "r":
                    weapons(userProfile, daVGDrive, daVGDir, playerSheet);
                case "BACK": case "back": case "0":
                    inventory( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown();
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }

    }

    private static boolean weaponsCheck(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet){

        if (TESTWEAP.isDirectory()){
            return true;
        }
        if (FtA_45.isDirectory()){
            return true;
        }
        if (LP61.isDirectory()){
            return true;
        }
        if (MP60.isDirectory()){
            return true;
        }
        if (P1885.isDirectory()){
            return true;
        }
        if (P60.isDirectory()){
            return true;
        }
        else{
            return false;
        }
    }

    private static void weaponLoad(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet, String weaponName) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[7];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.print( " > INVENTORY" );
        NoDelay();
        System.out.print( " > WEAPONS" );
        NoDelay();
        System.out.println( " > "+weaponName );
        ShortDelay();
        System.out.print("//ANALYZING ITEM IDENTIFICATION TAG...");
        LongDelay();
        System.out.println("COMPLETE");
        String [] weaponData = new String[27];
        BufferedReader weaponScanner = new BufferedReader(new FileReader(daVGDrive[1]+"\\"+weaponName+"\\ItemID.TAG"));
        try {
            weaponData = weaponScanner.readLine().split("[~]");
            weaponScanner.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode) {
            System.out.println(Arrays.toString(weaponData));
            TenSecondDelay();
        }
        ShortDelay();
        if (debugMode){
            System.out.print( "[INDEX 0]  " + weaponData[0] + " | ");
        } else {
            System.out.print( "  " + weaponData[0] + " | ");
        }
        ShortDelay();
        if (debugMode) {
            System.out.print( "[INDEX 1] " + weaponData[1] + " | " );
        } else {
            System.out.print( weaponData[1] + " | " );
        }
        ShortDelay();
        if (debugMode) {
            System.out.print( "[INDEX 2] FR " );
            NoDelay();
            System.out.print( weaponData[2] );
        } else {
            System.out.print( " FR " );
            NoDelay();
            System.out.print( weaponData[2] );
        }
        NoDelay();
        if (debugMode){
            System.out.print( " / " + "[INDEX 3] ID No. " );
            NoDelay();
            System.out.println(weaponData[3]);
        } else {
            System.out.print( " / " + "ID No. " );
            NoDelay();
            System.out.println(weaponData[3]);
        }
        ShortDelay();
        if (debugMode){
            System.out.println( "//[INDEX 4] MANUFACTURER: "+weaponData[4] );
        } else {
            System.out.println( "//MANUFACTURER: "+weaponData[4] );
        }
        NoDelay();
        if (debugMode) {
            System.out.println( "//[INDEX 5] DAMG: " + weaponData[5] );
        } else {
            System.out.println( "//DAMG: " + weaponData[5] );
        }
        NoDelay();
        if (debugMode){
            System.out.println( "//[INDEX 6] ACCT: " + weaponData[6] );
        } else {
            System.out.println( "//ACCT: " + weaponData[6] );
        }
        NoDelay();
        if (debugMode){
            System.out.println( "//[INDEX 7] CRHC: " + weaponData[7] );
        } else {
            System.out.println( "//CRHC: " + weaponData[7] );
        }
        NoDelay();
        if (debugMode){
            System.out.println( "//[INDEX 8] CRHT: " + weaponData[8] );
        } else {
            System.out.println( "//CRHT: " + weaponData[8] );
        }
        NoDelay();
        if (debugMode){
            System.out.println( "//[INDEX 9] CRHM: " + weaponData[9] );
        } else {
            System.out.println( "//CRHM: " + weaponData[9] );
        }
        NoDelay();
        if (debugMode){
            System.out.println( "//[INDEX 10] WEAPON TYPE: " + weaponData[10] );
        } else {
            System.out.println( "//WEAPON TYPE: " + weaponData[10] );
        }
        if (weaponData[10].equals("RANGED")) {
            if (debugMode){
                System.out.println( "//[INDEX 11] EFFR: " + weaponData[11] );
            } else {
                System.out.println( "//EFFR: " + weaponData[11] );
            }
            NoDelay();
            if (debugMode){
                System.out.println( "//[INDEX 12] AMMO: " + weaponData[12] );
            } else {
                System.out.println( "//AMMO: " + weaponData[12] );

            }
            NoDelay();
            if (debugMode){
                System.out.println( "//[INDEX 13] MGZN: " + weaponData[13] + "/[INDEX 14] " +weaponData[14] + " " + weaponData[15] );
            } else {
                System.out.println( "//MGZN: " + weaponData[13] + "/" +weaponData[14] + " " + weaponData[15] );
            }
            NoDelay();
            if (debugMode){
                System.out.println( "//[INDEX 16] AUPS: " + weaponData[16] );
            } else {
                System.out.println( "//AUPS: " + weaponData[16] );
            }
            NoDelay();
            if (debugMode){
                System.out.println( "//[INDEX 17] FRMD: " + weaponData[17] );
            } else {
                System.out.println( "//FRMD: " + weaponData[17] );
            }
            NoDelay();
            if (debugMode){
                System.out.println( "//[INDEX 18] CHAM:" + weaponData[18] );
            } else {
                System.out.println( "//CHAM: " + weaponData[18] );
            }
        }
        ShortDelay();
        if (debugMode){
            System.out.print( "//[INDEX 19] TOOT: " );
            ShortDelay();
            System.out.println( weaponData[19] );
        } else {
            System.out.print( "//TOOT: " );
            ShortDelay();
            System.out.println( weaponData[19] );
        }
        ShortDelay();
        if (debugMode) {
            System.out.println( "//[INDEX 20] DESC: " );
            ShortDelay();
            System.out.println( weaponData[20] );
        } else {
            System.out.println( "//DESC: " );
            ShortDelay();
            System.out.println( weaponData[20] );
        }
        ShortDelay();
        if (!weaponData[21].equals("N/A")){
            if (debugMode){
                System.out.println( "//WEAPON-SPECIFIC TRAITS:" );
                ShortDelay();
                System.out.println( "// [INDEX 21] " + weaponData[21] + " - " + "[INDEX 22] " + weaponData[21] );
                NoDelay();
            } else {
                System.out.println( "//WEAPON-SPECIFIC TRAITS:" );
                ShortDelay();
                System.out.println( "//" + weaponData[21] + " - " + weaponData[22] );
                NoDelay();
            }
            if (!weaponData[23].equals("N/A")){
                if (debugMode){
                    System.out.println( "// [INDEX 23] " + weaponData[23] + " - " + "[INDEX 24] " + weaponData[24] );
                    NoDelay();
                } else {
                    System.out.println( "//" + weaponData[23] + " - " + weaponData[24] );
                    NoDelay();
                }
                if (!weaponData[25].equals("N/A")){
                    if (debugMode){
                        System.out.println( "// [INDEX 25] " + weaponData[25] + " - " + "[INDEX 26] " + weaponData[26] );
                    } else {
                        System.out.println( "//" + weaponData[23] + " - " + weaponData[24] );
                    }
                }
            }
        }
        ShortDelay();
        System.out.println(">EQUIP (1/EQUP)");
        NoDelay();
        System.out.println(">UNEQUIP (2/UEQP)");
        NoDelay();
        System.out.println( ">REFRESH (R/REFR)" );
        NoDelay();
        System.out.println( ">BACK (0/BACK)" );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "REFRESH": case "refresh": case "REFR": case "refr": case "R": case "r":
                    weaponLoad( userProfile, daVGDrive, daVGDir, playerSheet, weaponName );
                    break;
                case "EQUIP": case "equip": case "EQUP": case "equp": case "1":
                    if (playerData[6].equals(weaponData[3])){
                        System.out.println("//ERROR: WEAPON ALREADY EQUIPPED!");
                        optionNotChosen = true;
                        break;
                    } else {
                        System.out.println("//ARE YOU SURE YOU WANT TO EQUIP THIS WEAPON? Y/N");
                        boolean equipOptionNotChosen = true;
                        while ( equipOptionNotChosen ) {
                            switch ( input.next() ) {
                                case "Y": case "y": case "1":
                                    writer = new BufferedWriter(new FileWriter(playerSheet));
                                    writer.write(playerData[0]+"~"+playerData[1]+"~"+playerData[2]+"~"+playerData[3]+"~"+playerData[4]+"~"+playerData[5]+"~"+weaponData[3]);
                                    writer.flush();
                                    writer.close();
                                    System.out.println("//CURRENT WEAPON CHANGED");
                                    System.out.println("//PRESS ENTER TO RETURN TO MAIN MENU");
                                    petc.nextLine();
                                    weapons(userProfile, daVGDrive, daVGDir, playerSheet);
                                    break;
                                case "N": case "n": case "0":
                                    System.out.println( "//RETURNING TO WEAPONS MENU." );
                                    LongDelay();
                                    weapons(userProfile, daVGDrive, daVGDir, playerSheet);
                                    break;
                                case "#KVSEDBM":
                                    equipOptionNotChosen = true;
                                    debugMode = true;
                                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                                    break;
                                case "#KVSDDBM":
                                    equipOptionNotChosen = true;
                                    debugMode = false;
                                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                                    break;
                                case "#KVSECS":
                                    equipOptionNotChosen = true;
                                    cutscenesEnabled = true;
                                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                                    break;
                                case "#KVSDCS":
                                    equipOptionNotChosen = true;
                                    cutscenesEnabled = false;
                                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                                    break;
                                default:
                                    System.out.println( "//ERROR: INVALID INPUT." );
                                    break;
                            }
                        }
                    }
                    break;
                case "UNEQUIP": case "unequip": case "UEQP": case "ueqp": case "2":
                    if (!playerData[6].equals(weaponData[3])){
                        System.out.println("//ERROR: WEAPON NOT EQUIPPED!");
                        optionNotChosen = true;
                        break;
                    } else {
                        System.out.println("//ARE YOU SURE YOU WANT TO UNEQUIP THIS WEAPON? Y/N");
                        boolean equipOptionNotChosen = true;
                        while ( equipOptionNotChosen ) {
                            switch ( input.next() ) {
                                case "Y": case "y": case "1":
                                    writer = new BufferedWriter(new FileWriter(playerSheet));
                                    writer.write(playerData[0]+"~"+playerData[1]+"~"+playerData[2]+"~"+playerData[3]+"~"+playerData[4]+"~"+playerData[5]+"~001");
                                    writer.flush();
                                    writer.close();
                                    System.out.println("//CURRENT WEAPON CHANGED");
                                    System.out.println("//PRESS ENTER TO RETURN TO MAIN MENU");
                                    petc.nextLine();
                                    weapons(userProfile, daVGDrive, daVGDir, playerSheet);
                                    break;
                                case "N": case "n": case "0":
                                    System.out.println( "//RETURNING TO WEAPONS MENU." );
                                    LongDelay();
                                    weapons(userProfile, daVGDrive, daVGDir, playerSheet);
                                    break;
                                case "#KVSEDBM":
                                    equipOptionNotChosen = true;
                                    debugMode = true;
                                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                                    break;
                                case "#KVSDDBM":
                                    equipOptionNotChosen = true;
                                    debugMode = false;
                                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                                    break;
                                case "#KVSECS":
                                    equipOptionNotChosen = true;
                                    cutscenesEnabled = true;
                                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                                    break;
                                case "#KVSDCS":
                                    equipOptionNotChosen = true;
                                    cutscenesEnabled = false;
                                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                                    break;
                                default:
                                    System.out.println( "//ERROR: INVALID INPUT." );
                                    break;
                            }
                        }
                    }
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown();
                    break;
                case "BACK": case "back": case "0":
                    weapons( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }

    }

    private static void ammunition(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.print( " > INVENTORY" );
        NoDelay();
        System.out.println( " > AMMUNITION" );
        MedDelay();
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED." );
        ShortDelay();
        System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "1":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                case "2":
                    shutdown();
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown();
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
    }

    private static void equipment(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.print( " > INVENTORY" );
        NoDelay();
        System.out.println( " > EQUIPMENT" );
        MedDelay();
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED." );
        ShortDelay();
        System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "1":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                case "2":
                    shutdown();
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown();
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
    }

    private static void medical(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.print( " > INVENTORY" );
        NoDelay();
        System.out.println( " > MEDICINE" );
        MedDelay();
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED." );
        ShortDelay();
        System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "1":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                case "2":
                    shutdown();
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown();
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
    }

    private static void miscellaneous(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.print( " > INVENTORY" );
        NoDelay();
        System.out.println( " > MISCELLANEOUS" );
        MedDelay();
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED." );
        ShortDelay();
        System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "1":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                case "2":
                    shutdown();
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown();
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
    }

    private static void journal(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.println( " > JOURNAL" );
        MedDelay();
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED." );
        ShortDelay();
        System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "1":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                case "2":
                    shutdown();
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown();
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
    }

    private static void utility(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[6];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.println( " > UTILITY" );
        MedDelay();
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED." );
        ShortDelay();
        System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN" );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "1":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                case "2":
                    shutdown();
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown();
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
    }

    private static void combat(String userProfile, File[] daVGDrive, String[] daVGDir, File playerSheet) throws IOException, InterruptedException {
        ClearScreen();
        String[] playerData = new String[7];
        reader = new BufferedReader(new FileReader(playerSheet));
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }        ShortDelay();
        System.out.print( playerData[0] );
        NoDelay();
        System.out.println( " > COMBAT" );
        ShortDelay();
        System.out.print( "//INITIALIZING Anser-Lindhurst ZHS V1... " );
        LongDelay();
        System.out.println( "COMPLETE" );
        System.out.println( "//CURRENT HEALTH: " + playerData[1] + "/" + playerData[2] + " HP" );
        if (playerData[6].equals("001")){
            System.out.print( "//CURRENT WEAPON:  FISTS | ");
            ShortDelay();
            System.out.print( "MULTIPURPOSE EXTREMITY | " );
            ShortDelay();
            System.out.print( " FR " );
            NoDelay();
            System.out.print( 0 );
            ShortDelay();
            System.out.print( " / " + "ID No. " );
            NoDelay();
            System.out.println( "001" );
            ShortDelay();
            System.out.println( "//MANUFACTURER: N/A" );
            NoDelay();
            switch (playerData[5]){
                case "HIGH":
                    System.out.println( "//DAMG: " + 5 );
                    break;
                case "NORMAL":
                    System.out.println( "//DAMG: " + 4 );
                    break;
                case "LOW":
                    System.out.println( "//DAMG: " + 3 );
                    break;
            }
            NoDelay();
            System.out.println( "//ACCT: "+3 );
            NoDelay();
            System.out.println( "//CRHC: Y" );
            NoDelay();
            System.out.println( "//CRHT: "+6 );
            NoDelay();
            System.out.println( "//CRHM: "+2 );
            NoDelay();
        } else {
            switch (playerData[6]){
                case "000":
                    cWNAME = "TESTWEAP";
                    break;
                case "002":
                    cWNAME = "FtA_45";
                    break;
            }
            String [] weaponData = new String[18];
            BufferedReader weaponScanner = new BufferedReader(new FileReader(daVGDrive[1]+"\\"+cWNAME+"\\ItemID.TAG"));
            try {
                weaponData = weaponScanner.readLine().split("[~]");
                weaponScanner.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            if (debugMode) {
                System.out.println(Arrays.toString(weaponData));
                TenSecondDelay();
            }
            cWNAME = weaponData[0];
            System.out.print( "  " + cWNAME + " | ");
            ShortDelay();
            cWROLE = weaponData[1];
            System.out.print( cWROLE + " | " );
            ShortDelay();
            System.out.print( " FR " );
            ShortDelay();
            cWFR = Integer.parseInt(weaponData[3]);
            System.out.print( cWFR );
            ShortDelay();
            System.out.print( " / " + "ID No. " );
            ShortDelay();
            cWID = Integer.parseInt(weaponData[17]);
            System.out.println( cWID );
            ShortDelay();
            cWMFGR = weaponData[4];
            System.out.println( "//MANUFACTURER: "+cWMFGR );
            NoDelay();
            cWDAMG = Integer.parseInt(weaponData[5]);
            System.out.println( "//DAMG: " + cWDAMG );
            NoDelay();
            cWACCT = Integer.parseInt(weaponData[6]);
            System.out.println( "//ACCT: " + cWACCT );
            NoDelay();
            cWCRHC = weaponData[7];
            System.out.println( "//CRHC: " + cWCRHC );
            NoDelay();
            cWCRHT = Integer.parseInt(weaponData[7]);
            System.out.println( "//CRHT: " + cWCRHT );
            NoDelay();
            cWCRHM = Double.parseDouble(weaponData[9]);
            System.out.println( "//CRHM: " + cWCRHM );
            NoDelay();
            if (weaponData[2].equals("RANGED")||weaponData[2].equals("HYBRID")) {
                cWEFFR = weaponData[10];
                System.out.println("//EFFR: " + cWEFFR);
                NoDelay();
                cWAMMO = weaponData[11];
                System.out.println("//AMMO: " + cWAMMO);
                NoDelay();
                cWMGZN = Integer.parseInt(weaponData[12]);
                System.out.println("//MGZN: " + cWMGZN);
                NoDelay();
                cWAUPS = Integer.parseInt(weaponData[13]);
                System.out.println("//AUPS: " + cWAUPS);
                NoDelay();
                cWFRMD = weaponData[14];
                System.out.println("//FRMD: " + cWFRMD);
            }
        }
        System.out.println("//AIM ASSISTANCE SYSTEM IS NOW ACTIVE");
        ShortDelay();
        System.out.println(">ATTACK (1/FIRE)");
        NoDelay();
        System.out.println(">RELOAD (2/RELD)");
        NoDelay();
        System.out.println(">ITEMS (3/ITEM)");
        NoDelay();
        System.out.println(">REFRESH (R/REFR)");
        NoDelay();
        System.out.println(">BACK (0/BACK)");
        ShortDelay();
        System.out.println("//PLEASE INPUT A COURSE OF ACTION");
        ShortDelay();
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "ATTACK": case "attack": case "FIRE": case "fire": case "1":
                    System.out.println("//PLEASE ENTER ACCURACY SCORE");
                    input.next();
                    break;
                case "2":
                    mmain(userProfile, daVGDrive, daVGDir, playerSheet);
                    break;
                case "3":
                    break;
                case "REFRESH": case "refresh": case "REFR": case "refr": case "R": case "r":
                    combat(userProfile, daVGDrive, daVGDir, playerSheet);
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown();
                    break;
                case "BACK": case "back": case "0":
                    weapons( userProfile, daVGDrive, daVGDir, playerSheet );
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM":
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println( "//MAINTENANCE MODE DISABLED" );
                    break;
                case "#KVSECS":
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS":
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    System.out.println( "//ERROR: INVALID INPUT." );
                    break;
            }
        }
    }

    private static void shutdown() throws InterruptedException, IOException {
        System.out.println( "//INITIATING SHUTDOWN..." );
        ShutoffDelay();
        System.exit( 0 );
        ClearScreen();
    }
}
