
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


    private static Boolean cutscenesEnabled;
    private static int LongDelay;
    private static int MedDelay;
    private static int ShortDelay;
    private static int NoDelay;
    private static Scanner petc;
    private static boolean veryFirstBoot;

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
    private static String cW1ID;

    private static String cW2NAME;
    private static String cW2ROLE;
    private static String cW2TYPE;
    private static int cW2FR;
    private static String cW2MFGR;
    private static int cW2DAMG;
    private static int cW2ACCT;
    private static String cW2CRHC;
    private static int cW2CRHT;
    private static double cW2CRHM;
    private static String cW2EFFR;
    private static String cW2AMMO;
    private static int cW2MGZN;
    private static int cW2AUPS;
    private static String cW2FRMD;
    private static String cW2ID;

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
        VersionNo = "V0.1"; //Version Number
        debugMode = false; //Debug Mode (true/false)
        cutscenesEnabled = true; //Cutscenes (enabled = true, disabled = false)
        LongDelay = 3; //length of LongDelay in seconds
        MedDelay = 2; //length of MedDelay in seconds
        ShortDelay = 1; //length of ShortDelay in seconds
        NoDelay = 100; //length of NoDelay in milliseconds




        input = new Scanner(System.in);
        petc = new Scanner(System.in);

        /* Welcome to DaVG Ausf. J V0.1!
         *
         * This program is meant to emulate the functions of the Anser-Lindhurst DatenVerarbeitungsGerät, or DaVG.
         * In House of Mirrors, the player is issued the DaVG Ausf. J, a durable (  if bulky  ) wrist-mounted information
         * processor developed by the Volkshavenish firm Anser-Lindhurst Instrumentenfirma and issued to Volkshavenish
         * troops during the 1960s.
         *
         * This emulation of the DaVG has been given the designation of Ausführung J (  "Execution J"  ) to
         * distinguish it from its predecessor, Ausführung B, which took the form of a batch file.
         *
         * The first thing that DaVG does is create a new instance of the playerCharacter class, which is a class
         * that contains all the information about the player.
         *
         * For further documentation on the playerCharacter class, please see playerCharacter.java.
         */

        playerCharacter player = new playerCharacter ();
        player.setUserProfile(System.getenv("USERPROFILE"));
        player.setDocPath( player.getUserProfile() + "\\Documents\\DaVG_AusfJ_" );

        /* The program then continues by initializing the DaVG's operating system, known as KRIEGSVERSTAND, or "War Mind". In the
         * context of the Multiverse, KRIEGSVERSTAND is a reliable ( if perhaps a bit spartan ) operating system built
         * specifically for the DaVG platform.
         *
         * KRIEGSVERSTAND V2 begins by asking the user to press "Enter" to initialize the DaVG's boot sequence.
         *
         * Something worth noting is that KRIEGSVERSTAND - being an operating system of Volkshavenish origin - is written in Volkshavenish, and thus the entire boot log appears in Volkshavenish.
         * However, user preferences can be set to change the DaVG's language to English within a profile.
         *
         * Volkshavenish is a fictional language that uses German vocabulary combined with English grammar, so German-English translators do not work properly and may provide nonsensical responses.
         * This is for immersion purposes, but to aid in debugging, English translations of the machine's outputs will be provided in comments.
         */

        ClearScreen();
        System.out.println(">PRESS [ENTER] TO INITIALIZE BOOT SEQUENCE<");
        petc.nextLine();
        ClearScreen();
        System.out.println("//Initialisierung KRIEGSVERSTAND V2..."); //Initializing KRIEGSVERSTAND V2...
        MedDelay();
        System.out.print("//ÜBERPRUFUNG ZAE... "); //CHECKING ZAE (Zufällig-Abruf-Erinnerung, or Random Retrieval Memory - known in our universe as Random Access Memory or RAM)
        MedDelay();
        System.out.println("128/128 KDB AZS ZUGÄNGLICH"); //128/128 KDB AZS AVAILABLE (Kilo-Datenbytes, known to us simply as Kilobytes)
        ShortDelay();
        System.out.print("//ÜBERPRUFUNG DATENSPEICHER.. "); //CHECKING STORAGE...
        MedDelay();
        System.out.println("778220/778220 DB ZUGÄNGLICH"); // 778220/778220 DB AVAILABLE (Datenbytes, known to us simply as Bytes)
        ShortDelay();
        System.out.print("//LESEN FESTPLATTE... "); //READING HARD DRIVE...

        /* At this point, the program will attempt to locate a folder labeled "DaVG_AusfJ_(Version No.)_Data
         * in the user's Documents folder. This folder contains the various profiles the user has created, and
         * will only exist if the user has run the program previously. If it does not exist, then the program will
         * create the folder itself and then state that there is a (fictitious) error in the DaVG's hard drive,
         * and prompt the user to create a new user profile.
         *
         * However, if the folder does exist, the program will list all the profiles contained within the folder, and prompt
         * the user to select one.
         *
         * The user also has the (not stated) option of entering several parameters to enable or disable certain
         * features, such as the "loading" screens.
         */

        File workingDirectory = new File(player.getDocPath() + VersionNo + "_Data\\");
        if (!workingDirectory.isDirectory()) {
            workingDirectory.mkdir();
        }
        String[] profileList = workingDirectory.list();
        if (profileList.length == 0 || profileList.equals(null)) {
            veryFirstBoot=true;
            LongDelay();
            System.out.println( "KOMPLETT"); // COMPLETE
            ShortDelay();
            System.out.println( "//ACHTUNG: KEINE PROFIL GEFUNDEN IM FESTPLATTE " ); //ERROR: NO PROFILES FOUND IN HARD DRIVE
            ShortDelay();
            System.out.println( "//ERSTELLEN NEU PROFIL? J/N" ); //CREATE NEW PROFILE? J/N (Like German, "Ja" is "Yes" and "Nein" is "No")
            ShortDelay();
            System.out.println( "//WARNUNG: DRÜCKEN \"N\" WIRD DEAKTIVIERT EINHEIT ZU VERHINDERN MANIPULATION" ); //WARNING: PRESSING "N" WILL DEACTIVATE THE DEVICE TO PREVENT TAMPERING
            boolean equipOptionNotChosen = true;
            while (equipOptionNotChosen) {
                if (debugMode) {
                    System.out.println(profileList);
                }
                switch (input.next()) {
                    case "J": case "j":
                        equipOptionNotChosen = false;
                        System.out.println("//BITTE EINGEBEN PROFIL-BENENNUNG"); //PLEASE ENTER PROFILE NAME
                        ShortDelay();
                        System.out.println("//WARNUNG: PROFIL-BENENNUNGEN SIND GROß-UND-KLEINSCHREIBUNG"); //WARNING: PROFILE NAMES ARE CASE-SENSITIVE
                        player.setIsEmpty( false );
                        player.setUserName(input.next());
                        break;
                    case "N": case "n":
                        equipOptionNotChosen = false;
                        shutdown( player );
                        break;
                    case "#KVSEDBM": //enables debug mode
                        equipOptionNotChosen = true;
                        debugMode = true;
                        System.out.println("//MAINTENANCE MODE ENABLED");
                        break;
                    case "#KVSDDBM": //disables debug mode
                        equipOptionNotChosen = true;
                        debugMode = false;
                        System.out.println("//MAINTENANCE MODE DISABLED");
                        break;
                    case "#KVSECS": // disables cutscenes
                        equipOptionNotChosen = true;
                        cutscenesEnabled = true;
                        System.out.println("//ACCELERATED READ/WRITE DISABLED");
                        break;
                    case "#KVSDCS": //enables cutscenes
                        equipOptionNotChosen = true;
                        cutscenesEnabled = false;
                        System.out.println("//ACCELERATED READ/WRITE ENABLED");
                        break;
                    case "#KVSCLV": //changes language to Volkshavenish
                        equipOptionNotChosen = true;
                        player.setUserLanguage("VOLKSHAVENISH");
                        System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                        break;
                    case "#KVSCLE": //changes language to English
                        equipOptionNotChosen = true;
                        player.setUserLanguage("ENGLISH");
                        System.out.println("//LANGUAGE PREFERENCE UPDATED");
                        break;
                    default:
                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                            System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                        } else if (player.getUserLanguage().equals("ENGLISH")){
                            System.out.println("//ERROR: INVALID INPUT.");
                        }
                        break;
                }
            }
        } else {
            ShortDelay();
            System.out.println( "KOMPLETT" ); //COMPLETE
            ShortDelay();
            for (String profileName : profileList) {
                System.out.println(">" + profileName);
            }
            System.out.println( "//BITTE AUSWÄHLEN EIN BENUTZER-PROFIL" ); //PLEASE CHOOSE A USER PROFILE
            ShortDelay();
            System.out.println( "//WARNUNG: PROFIL-BENENNUNG SIND GROß-UND-KLEINSCHREIBUNG" ); //WARNING: PROFILE NAMES ARE CASE-SENSITIVE
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
                        player.setUserName(profileInput);
                }
            }
        }
        File requestedProfile = new File( player.getDocPath() + VersionNo + "_Data\\" + player.getUserName() + "\\" );
        if (!requestedProfile.isDirectory()){
            if (!veryFirstBoot){
              System.out.println( "//ACHTUNG: SPEZIFIZIERTEN BENUTZER-PROFIL NICHT GEFUNDEN" ); //WARNING: SPECIFIED USER PROFILE NOT FOUND
              ShortDelay();
            }
            System.out.println( "//SCHAFFEN NEU BENUTZER-PROFIL MIT DIESER NAME? J/N" ); //CREATE NEW PROFILE WITH THIS NAME? J/N
            ShortDelay();
            System.out.println( "//WARNUNG: DRÜCKEN \"N\" WIRD DEAKTIVIERT EINHEIT ZU VERHINDERN MANIPULATION" ); //WARNING: PRESSING "N" WILL DEACTIVATE THE DEVICE TO PREVENT TAMPERING
            boolean equipOptionNotChosen = true;
            while ( equipOptionNotChosen ) {
                switch ( input.next() ) {
                    case "J": case "j":
                        equipOptionNotChosen = false;
                        player.setIsEmpty( false );
                        break;
                    case "N": case "n":
                        equipOptionNotChosen = false;
                        shutdown( player );
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
                    case ">delays_enable":
                        equipOptionNotChosen = true;
                        debugMode = true;
                        System.out.println( "//MAINTENANCE MODE ENABLED" );
                        break;
                    case ">delays_disable":
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
                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                            System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                        } else if (player.getUserLanguage().equals("ENGLISH")){
                            System.out.println("//ERROR: INVALID INPUT.");
                        }
                        break;
                    }
                }
            }

        String [] daVGDir = new String[6];
        File [] inventory = new File[6];

        daVGDir[0] = player.getDocPath() + VersionNo + "_Data\\" + player.getUserName() + "\\";
        daVGDir[1] = player.getDocPath() + VersionNo + "_Data\\" + player.getUserName() + "\\inv\\weap";
        daVGDir[2] = player.getDocPath() + VersionNo + "_Data\\" + player.getUserName() + "\\inv\\ammo";
        daVGDir[3] = player.getDocPath() + VersionNo + "_Data\\" + player.getUserName() + "\\inv\\equp";
        daVGDir[4] = player.getDocPath() + VersionNo + "_Data\\" + player.getUserName() + "\\inv\\meds";
        daVGDir[5] = player.getDocPath() + VersionNo + "_Data\\" + player.getUserName() + "\\inv\\misc";
        inventory[0] = new File( daVGDir[0] );
        inventory[1] = new File( daVGDir[1] );
        inventory[2] = new File( daVGDir[2] );
        inventory[3] = new File( daVGDir[3] );
        inventory[4] = new File( daVGDir[4] );
        inventory[5] = new File( daVGDir[5] );

        player.setDaVGDir( daVGDir );
        player.setPlayerInventory( inventory );

        /* Here, the program creates a pair of arrays, daVGDir and inventory, both of which house several parameters.
         *
         * daVGDir contains the names of the various directories that the program makes use of within the user's
         * Documents directory.
         *
         * inventory stores these as Files so that these directories are more easily accessed later in the program.
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

        File playerDataLocation = new File ( daVGDir[0] + "playerData.txt" );

        player.setPlayerDataLocation( playerDataLocation );

        boolean dataExists = playerDataLocation.exists() && playerDataLocation.isFile();

        /* If the program successfully locates playerData.txt and all of the inventory directories, the user will be prompted with the option of skipping the
         * boot-up cinematic that plays when DaVG Ausf. J is first loaded. Otherwise, the program will automatically play
         * the intro cinematic and walk the user through the process of creating a new user profile.
         */

        if ( dataExists ) {
            player.setIsEmpty( false );
            if (debugMode){
              System.out.println(playerDataLocation);
            }
            reader = new BufferedReader(new FileReader(playerDataLocation));
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
                System.out.print( "//LADEN PROFIL..." ); //LOADING PROFILE...
                player.readPlayerData( playerDataLocation );
                LongDelay();
                System.out.println( "KOMPLETT" ); //COMPLETE
                ShortDelay();
                System.out.println( "//BITTE AUSWÄHLEN DIAGNOSTISCH-PRÜFUNG-BATTERIE AUSFÜHRUNG" ); //PLEASE SELECT DIAGNOSTIC TEST BATTERY TYPE
                ShortDelay();
                System.out.println(">STANDARD ( VOLLSTÄNDIG DIAGNOSTISCH-PRÜFUNG-BATTERIE )"); //STANDARD (FULL DIAGNOSTIC TEST BATTERY)
                NoDelay();
                System.out.println(">KURZ ( VERKÜRZT DIAGNOSTISCH-PRÜFUNG-BATTERIE )"); //SHORT (SHORTENED DIAGNOSTIC TEST BATTERY)
                NoDelay();
                System.out.println(">AUSLASSEN ( DIAGNOSTICH-PRÜFUNG-BATTERIE NICHT HINGERICHTET )"); //SKIP (DIAGNOSTIC TEST BATTERY NOT RUN)
                boolean equipOptionNotChosen = true;
                while (equipOptionNotChosen) {
                    switch (input.next()) {
                        case "STANDARD": case "standard": case "1":
                            equipOptionNotChosen = false;
                            intro(player, retUser);
                            break;
                        case "KURZ": case "kurz": case "2":
                            equipOptionNotChosen = false;
                            intro2(player, retUser);
                            break;
                        case "AUSLASSEN": case "auslassen": case "3":
                            equipOptionNotChosen = false;
                            mmain(player);
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
                            System.out.println("//ERROR: INVALID INPUT");
                            break;
                    }
                }

            } else {
                LongDelay();
                System.out.println( "//ACHTUNG: KEINE DATEN IM SPEZIFIZIERTEN FESTPLATTE-SEKTOR" ); //ERROR: NO DATA IN SPECIFIED HARD DRIVE SECTOR
                ShortDelay();
                System.out.println( "//DEFEKT IM FESTPLATTE VERMUTLICH" ); //FAULT IN HARD DRIVE SUSPECTED
                ShortDelay();
                intro(player, retUser);
            }
        } else {
            boolean retUser = false;
            intro(player, retUser);
        }
    }

    private static void ClearScreen() throws IOException, InterruptedException {
        /* This section of the program is special because what it does specifically changes based on the operating
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

    private static void intro(playerCharacter player, boolean retUser) throws IOException, InterruptedException {
        System.out.println( "//EINLITEN DIAGNOSTISCH-PRÜFUNG-BATTERIE..." ); //INITIALIZING DIAGNOSTIC TEST BATTERY...
        ShortDelay();
        System.out.print( "//PROZESSOR-ADER 1..." ); //PROCESSOR CORE 1...
        MedDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//PROZESSOR-ADER 2..." ); //PROCESSOR CORE 2...
        ShortDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//PROZESSOR-ADER 3..." ); //PROCESSOR CORE 3...
        ShortDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//PROZESSOR-ADER 4..." ); //PROCESSOR CORE 4...
        MedDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//PROZESSOR-ADER 5..." ); //PROCESSOR CORE 5...
        ShortDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//REAKTOR-ADER..." ); //REACTOR CORE...
        MedDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//TANKFÜLLSTAND..." ); //FUEL LEVELS...
        ShortDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//KÜHLMITTELSTAND..." ); //COOLANT LEVELS...
        LongDelay();
        System.out.println( "O.K." );
        NoDelay();
        intro2( player, retUser);

    }

    private static void intro2(playerCharacter player , boolean retUser) throws InterruptedException, IOException {
        System.out.print( "//ABRUFEN BENUTZERPROFIL..." ); //RETRIEVING USER PROFILE...
        if ( retUser ) {
            LongDelay();
            System.out.println( "KOMPLETT" ); //COMPLETE
            ShortDelay();
            System.out.print( "//VERIFIZIEREN BENUTZERIDENTITÄT..." ); //VERIFYING USER IDENTITY...
            LongDelay();
            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                System.out.println( "BESTÄTIGT." ); //CONFIRMED.
                ShortDelay();
                System.out.println( "//BENUTZER ENTSPRECHEN 1 EINGANG IM EINTRAGUNG." ); //USER MATCHES 1 ENTRY IN REGISTRY.
                ShortDelay();
                System.out.println( "//WILLKOMMEN ZURÜCK, " + player.getUserName() ); //WELCOME BACK, <PLAYER NAME>.
                ShortDelay();
                System.out.println( "//DRÜCKEN EINGEBEN/RÜCKKEHR ZU FORTSETZEN" ); //PRESS ENTER/RETURN TO CONTINUE
            } else if (player.getUserLanguage().equals("ENGLISH")){
                System.out.println( "CONFIRMED." );
                ShortDelay();
                System.out.println( "//USER MATCHES 1 ENTRY IN REGISTRY." );
                ShortDelay();
                System.out.println( "//WELCOME BACK, " + player.getUserName() );
                ShortDelay();
                System.out.println( "//PRESS ENTER/RETURN TO CONTINUE" );
            }
            petc.nextLine();
            mmain( player );

        } else {
            System.out.println( "GESCHEITERT" ); //FAILED
            ShortDelay();
            System.out.println( "//ACHTUNG: EINHEIT NICHT VERBUNDEN MIT BESATZUNG-DATENBANK" ); //ERROR: UNIT NOT LINKED WITH PERSONNEL DATABASE
            NoDelay();
            System.out.println( "//ÜBERSCHREIBE? J/N" ); //OVERRIDE? Y/N
            ShortDelay();
            System.out.println( "//WARNUNG: DRÜCKEN \"N\" WIRD DEAKTIVIERT EINHEIT ZU VERHINDERN MANIPULATION" ); //WARNING: PRESSING "N" WILL DEACTIVATE UNIT TO PREVENT TAMPERING
            boolean optionNotChosen = true;
            while ( optionNotChosen ) {
                switch ( input.next() ) {
                    case "J": case "j":
                        System.out.println( "//ÜBERSCHREIBEN..." ); //OVERRIDING...
                        LongDelay();
                        optionNotChosen = false;
                        newuser( player );
                        break;
                    case "N": case "n": case"SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                        shutdown( player );
                        break;
                    case "#KVSEDBM": //enables debug mode
                        optionNotChosen = true;
                        debugMode = true;
                        System.out.println("//MAINTENANCE MODE ENABLED");
                        break;
                    case "#KVSDDBM": //disables debug mode
                        optionNotChosen = true;
                        debugMode = false;
                        System.out.println("//MAINTENANCE MODE DISABLED");
                        break;
                    case "#KVSECS": // disables cutscenes
                        optionNotChosen = true;
                        cutscenesEnabled = true;
                        System.out.println("//ACCELERATED READ/WRITE DISABLED");
                        break;
                    case "#KVSDCS": //enables cutscenes
                        optionNotChosen = true;
                        cutscenesEnabled = false;
                        System.out.println("//ACCELERATED READ/WRITE ENABLED");
                        break;
                    default:
                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                            System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                        } else if (player.getUserLanguage().equals("ENGLISH")){
                            System.out.println("//ERROR: INVALID INPUT.");
                        }
                        break;
                }
            }
        }
    }

    private static void newuser( playerCharacter player ) throws InterruptedException, IOException {
        System.out.println( "//DIAGNOSTISCH-PRÜFUNG-BATTERIE KOMPLETT." );//DIAGNOSTIC TESTS COMPLETED.
        LongDelay();
        System.out.println( "//ACHTUNG: KEINE DATEN ZUM GEGENWÄRTIG-BENUTZER GEFUNDEN." );//ERROR: NO DATA ON CURRENT USER FOUND.
        LongDelay();
        System.out.println( "//KEINE ADMINISTRATOREN IM BENUTZER-EINTRAGUNG." );//NO ADMINISTRATORS IN USER REGISTRY.
        LongDelay();
        System.out.println( "//KEINE SICHERHEITSMAßNAHMEN DEFINIERT DURCH VORHERIG BENUTZEREN" );//NO SECURITY MEASURES DEFINED BY PRIOR USERS.
        ShortDelay();
        System.out.println( "//SCHAFFEN NEU BENUTZER? J/N" );//CREATE NEW USER? J/N
        ShortDelay();
        System.out.println( "//WARNUNG: DRÜCKEN \"N\" WIRD DEAKTIVIERT EINHEIT ZU VERHINDERN MANIPULATION" ); //WARNING: PRESSING "N" WILL DEACTIVATE UNIT TO PREVENT TAMPERING
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "J": case "j":
                    optionNotChosen = false;
                    break;
                case "N": case "n": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
        System.out.println( "//BITTE EINGANG NEU BENUTZERNAME" );//PLEASE INPUT NEW USER NAME.
        ShortDelay();
        System.out.println( "//WARNUNG: LÜCKEN MUSS SEIN ANGEBEN MIT UNTERSTREICHEN." );//WARNING: GAPS MUST BE INDICATED WITH UNDERSCORES.
        String userName = input.next();
        System.out.print( "//SCHREIBEN ZU FESTPLATTE..." );//WRITING TO HARD DRIVE...
        File[] inventory = player.getPlayerInventory();
        inventory[0].mkdir();
        inventory[1].mkdirs();
        inventory[2].mkdirs();
        inventory[3].mkdirs();
        inventory[4].mkdirs();
        inventory[5].mkdirs();
        player.getPlayerDataLocation().createNewFile();
        reader = new BufferedReader(new FileReader(player.getPlayerDataLocation()));
        writer = new BufferedWriter(new FileWriter(player.getPlayerDataLocation()));

        /* At this point, the program will then create a critical part of a profile: the Player Sheet.
         *
         * A character's Player Sheet contains all the information that defines the character themselves.
         *
         * The information on a character - the Player Data - is stored in an index of 32 values, shown below:
         * 00 - Player Name
         * 01 - Preferred Language (This will be set to the DaVG's native Volkshavenish by default)
         * 02 - Synchronization Level (Starts at 1)
         * 03 - Lifetime Field Data (000 by default, Measured in DB)
         * 04 - Current HP (Set at 100 by default)
         * 05 - Maximum HP (Set at 100 by default)
         * 06 - Current Status (Normal, Poisoned, etc.)
         * 07 - Class 1
         * 08 - Class 2 (if applicable)
         * 09 - Class 3 (if applicable)
         * 10 - Class 4 (if applicable)
         * 11 - Strength score
         * 12 - Dexterity score
         * 13 - Constitution Score
         * 14 - Intelligence Score
         * 15 - Wisdom Score
         * 16 - Charisma Score
         * 17 - Background
         * 18 - Background Proficiency 1
         * 19 - Background Proficiency 2
         * 20 - Skill Proficiency 1
         * 21 - Skill Proficiency 2
         * 22 - Skill Proficiency 3 (If applicable)
         * 23 - Skill Proficiency 4 (If applicable)
         * 24 - Weapon Proficiency
         * 25 - Software Slot 1
         * 26 - Software Slot 2
         * 27 - Software Slot 3
         * 28 - Software Slot 4
         * 29 - Software Slot 5
         * 30 - Primary Weapon ID
         * 31 - Secondary Weapon ID (if applicable)
         */
        player.setIsEmpty(false);
        player.setUserName( userName ); //00
        player.setUserLanguage("VOLKSHAVENISH"); //01
        player.setUserLevel("1"); //02
        player.setUserXP("000"); //03
        player.setUserCurrentHealth("100"); //04
        player.setUserMaxHealth("100"); //05
        player.setUserStatus("NORMAL"); //06
        player.setUserClass1("C1M"); //07
        player.setUserClass2("C2M"); //08
        player.setUserClass3("C3M"); //09
        player.setUserClass4("C4M"); //10
        player.setUserStrength("StM"); //11
        player.setUserDexterity("DeM"); //12
        player.setUserConstitution("CoM"); //13
        player.setUserIntelligence("InM"); //14
        player.setUserWisdom("WiM"); //15
        player.setUserCharisma("CaM"); //16
        player.setUserBackground("BaM"); //17
        player.setUserBackgroundProficiency1("B1M"); //18
        player.setUserBackgroundProficiency2("B2M"); //19
        player.setUserSkillProficiency1("S1M"); //20
        player.setUserSkillProficiency2("S2M"); //21
        player.setUserSkillProficiency3("S3M"); //22
        player.setUserSkillProficiency4("S4M"); //23
        player.setUserWeaponProficiency("WpM"); //24
        player.setSoftware1("T1M"); //25
        player.setSoftware2("T2M"); //26
        player.setSoftware3("T3M"); //27
        player.setSoftware4("T4M"); //28
        player.setSoftware5("T5M"); //29
        player.setPrimaryWeapon("001"); //30
        player.setSecondaryWeapon("DwM"); //31
        player.writePlayerData();
        LongDelay();
        System.out.println( "KOMPLETT" ); //COMPLETE
        ShortDelay();
        System.out.println( "//NEU EINGANG ERSTELLT IM BENUTZER-EINTRAGUNG." ); //NEW ENTRY CREATED IN USER REGISTRY.
        ShortDelay();
        System.out.println( "//WILKOMMEN, " + player.getUserName() ); //GREETINGS, <PLAYER NAME>.
        ShortDelay();
        System.out.println( "//DRÜCKEN EINGEBEN/RÜCKKEHR ZU FORTSETZEN" ); //PRESS ENTER/RETURN TO CONTINUE
        petc.nextLine();
        mmain( player );
    }

    private static void mmain( playerCharacter player ) throws InterruptedException, IOException {
        ClearScreen();
        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
            if (debugMode){
                System.out.print( "//BENUTZER [DEBUG] | " );
            } else {
                System.out.print( "//BENUTZER | " );
            }
            ShortDelay();
            System.out.println( player.getUserName() );
            ShortDelay();
            System.out.println( ">VITALS (1/VITL)" );
            NoDelay();
            System.out.println( ">VORRÄTE (2/VRRT)" );
            NoDelay();
            System.out.println( ">TAGEBUCH (3/TAGB)" );
            NoDelay();
            System.out.println( ">WERKZEUG (4/WKZG) " );
            NoDelay();
            System.out.println( ">KAMPF (5/KMPF) " );
            NoDelay();
            System.out.println( ">NEU-LADEN (N/NULD) ");
            NoDelay();
            System.out.println( ">AUSSCHALTEN (A/ASCH) " );
            NoDelay();
        } else {
            if (debugMode){
                System.out.print( "//USER [DEBUG] | " );
            } else {
                System.out.print( "//USER | " );
            }
            ShortDelay();
            System.out.println( player.getUserName() );
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
            System.out.println( ">REFRESH (R/REFR) ");
            NoDelay();
            System.out.println( ">SHUTDOWN (P/POWR) " );
            NoDelay();
        }
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.next() ) {
                case "VITALS": case "vitals": case "VITL": case "vitl": case "1":
                    vitals( player );
                    break;
                case "VORRATE": case "VORRAETE": case "vorrate": case "VRRT": case "vrrt": case "INVENTORY": case "inventory": case "INVT": case "invt": case "2":
                    inventory( player );
                    break;
                case "TAGEBUCH": case "tagebuch": case "TAGB": case "tagb": case "JOURNAL": case "journal": case "JRNL": case "jrnl": case "3":
                    journal ( player );
                    break;
                case "WERKZEUG": case "werkzeug": case "WKZG": case "wrkzg": case "UTILITY": case "utility": case "UTIL": case "util": case "4":
                    utility ( player );
                    break;
                case "KAMPF": case "kampf": case "KMPF": case "kmpf": case "COMBAT": case "combat": case "CMBT": case "cmbt": case "5":
                    combat( player );
                    break;
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr": case "R": case "r":
                    mmain(player);
                    break;
                case "AUSSCHALTEN": case "ausschalten": case "ASCH": case "asch": case "A": case "a": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                case "sing":
                    sing( player );
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }

    }

    private static void vitals( playerCharacter player) throws IOException, InterruptedException {

        /* Vitals, like most of the methods in this program, does exactly what it says on the tin: measure the current character's vital signs.
         * The Vitals subroutine monitors the player's HP, and provides detailed info on their stats.
         *
         * The player starts by initializing the Arzttasche™ Vital Signs Analysis System.
         */

        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player.getUserName() );
        NoDelay();
        System.out.println( " > VITALS" );
        ShortDelay();
        System.out.println( "//INITIALIZING Arzttasche(tm) VITAL SIGNS ANALYSIS SYSTEM");
        ShortDelay();
        System.out.print( "//VITALS ANALYSIS..." );
        LongDelay();
        System.out.println( "COMPLETE" );
        ShortDelay();

        /* Here, the program will then list the player's vital signs.
         *
         *
         * The Vitals method makes use of the following indexes:
         * 00 - Player Name
         * 01 - Preferred Language
         * 02 - Current HP
         * 03 - Maximum HP
         * 04 - Current Status (Normal, Poisoned, etc.)
         * 05 - Class 1
         * 06 - Class 2 (if applicable)
         * 07 - Class 3 (if applicable)
         * 08 - Class 4 (if applicable)
         * 09 - Strength score
         * 10 - Dexterity score
         * 11 - Constitution Score
         * 12 - Intelligence Score
         * 13 - Wisdom Score
         * 14 - Charisma Score
         * 15 - Background
         * 16 - Background Proficiency 1
         * 17 - Background Proficiency 2
         * 18 - Skill Proficiency 1
         * 19 - Skill Proficiency 2
         * 20 - Skill Proficiency 3 (If applicable)
         * 21 - Skill Proficiency 4 (If applicable)
         * 22 - Weapon Proficiency
         * 23 - Software Slot 1
         * 24 - Software Slot 2
         * 25 - Software Slot 3
         * 26 - Software Slot 4
         * 27 - Software Slot 5
         */

        System.out.println( "//CURRENT HEALTH: " + player.getUserCurrentHealth() + "/" + player.getUserMaxHealth() + " HP" );
        NoDelay();
        System.out.println( "//OVERALL VITAL STATUS: " + player.getUserStatus() );
        NoDelay();
        System.out.println( "//SYNCHRONIZATION LEVEL: " + player.getUserLevel() );
        NoDelay();
        System.out.println( "//LIFETIME USER DATA GATHERED: " + player.getUserXP() + " DB");
        NoDelay();
        if (player.getUserStrength().equals("StM")){
            System.out.println( "//STRENGTH (STRN): [DATA MISSING]" );
        } else {
            System.out.println( "//STRENGTH (STRN): " + player.getUserStrength() );
        }
        NoDelay();
        if (player.getUserDexterity().equals("DeM")){
            System.out.println( "//DEXTERITY (DEXT): [DATA MISSING]" );
        } else {
            System.out.println( "//DEXTERITY (DEXT): " + player.getUserDexterity() );
        }
        NoDelay();
        if (player.getUserConstitution().equals("CoM")){
            System.out.println( "//CONSTITUTION (CNST): [DATA MISSING]" );
        } else {
            System.out.println( "//CONSTITUTION (CNST): " + player.getUserConstitution() );
        }
        NoDelay();
        if (player.getUserIntelligence().equals("InM")){
            System.out.println( "//INTELLIGENCE (INTL): [DATA MISSING]" );
        } else {
            System.out.println( "//INTELLIGENCE (INTL): " + player.getUserIntelligence() );
        }
        NoDelay();
        if (player.getUserWisdom().equals("WiM")){
            System.out.println( "//WISDOM (WSDM): [DATA MISSING]" );
        } else {
            System.out.println( "//WISDOM (WSDM): " + player.getUserWisdom() );
        }
        NoDelay();
        if (player.getUserCharisma().equals("CaM")){
            System.out.println( "//CHARISMA (CHRM): [DATA MISSING]" );
        } else {
            System.out.println( "//CHARISMA (CHRM): " + player.getUserCharisma() );
        }
        NoDelay();

        if (player.getUserStrength().equals("StM") || player.getUserDexterity().equals("DeM") || player.getUserConstitution().equals("CoM") || player.getUserIntelligence().equals("InM") || player.getUserWisdom().equals("WiM") || player.getUserCharisma().equals("CaM")){
            MedDelay();
            System.out.println( "//WARNING: DATA MISSING ON ONE OR MORE USER ATTRIBUTES" );
            ShortDelay();
            System.out.println( "//PLEASE USE \"EDIT\" FUNCTION TO COMPLETE MISSING ENTRIES");
            NoDelay();
        }

        System.out.println( ">MEDICAL (1/MEDS)" );
        NoDelay();
        System.out.println( ">SKILLS (2/SKLS)" );
        NoDelay();
        System.out.println( ">EDIT (3/EDIT)" );
        NoDelay();
        System.out.println( ">REFRESH (R/REFR)" );
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
                                mmain( player );
                                break;
                            case "2":
                                shutdown( player );
                                break;
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
                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                    System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                    System.out.println("//ERROR: INVALID INPUT.");
                                }
                                break;
                        }
                    }
                    break;
                case "SKILLS": case "SKLS": case "skills": case "skls": case "2":
                    skills(player);
                    break;
                case "EDIT": case "edit": case "3":

                    /* The Edit section allows the user to edit the DaVG's information on their stats.
                     *
                     * The Vitals method makes use of the following indexes:
                     * 09 - Strength score
                     * 10 - Dexterity score
                     * 11 - Constitution Score
                     * 12 - Intelligence Score
                     * 13 - Wisdom Score
                     * 14 - Charisma Score
                     */
                    System.out.println("// PLEASE SPECIFY VALUE TO EDIT");
                    boolean statOptionNotChosen = true;
                    while ( statOptionNotChosen ) {
                        switch ( input.next() ) {
                            case "STRENGTH": case "strength": case "STRN": case "strn":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR STRENGTH (STRN)");
                                player.setUserStrength(input.next());
                                System.out.println("//STRENGTH (STRN) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW ABILITY TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"R\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "DEXTERITY": case "dexterity": case "DEXT": case "dext":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR DEXTERITY (DEXT)");
                                player.setUserDexterity(input.next());
                                System.out.println("//DEXTERITY (DEXT) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW ABILITY TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"R\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CONSTITUTION": case "constitution": case "CNST": case "cnst":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR CONSTITUTION (CNST)");
                                player.setUserConstitution(input.next());
                                int newCurrentHealth = Integer.parseInt(player.getUserCurrentHealth()) + (modCalc(Integer.parseInt(player.getUserConstitution()))*10);
                                int newMaxHealth = Integer.parseInt(player.getUserMaxHealth()) + (modCalc(Integer.parseInt(player.getUserConstitution()))*10);
                                player.setUserCurrentHealth(Integer.toString(newCurrentHealth));
                                player.setUserMaxHealth(Integer.toString(newMaxHealth));
                                System.out.println("//CONSTITUTION (CNST) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW ABILITY TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"R\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "INTELLIGENCE": case "intelligence": case "INTL": case "intl":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR INTELLIGENCE (INTL)");
                                player.setUserIntelligence(input.next());
                                System.out.println("//INTELLIGENCE (INTL) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW ABILITY TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"R\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "WISDOM": case "wisdom": case "WSDM": case "wsdm":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR WISDOM (WSDM)");
                                player.setUserWisdom(input.next());
                                System.out.println("//WISDOM (WSDM) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW ABILITY TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"R\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CHARISMA": case "charisma": case "CHRM": case "chrm":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR CHARISMA (CHRM)");
                                player.setUserCharisma(input.next());
                                System.out.println("//CHARISMA (CHRM) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW ABILITY TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"R\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr": case "R": case "r":
                                player.writePlayerData();
                                vitals( player );
                                break;
                            case "#KVSEDBM": //enables debug mode
                                statOptionNotChosen = true;
                                debugMode = true;
                                System.out.println("//MAINTENANCE MODE ENABLED");
                                break;
                            case "#KVSDDBM": //disables debug mode
                                statOptionNotChosen = true;
                                debugMode = false;
                                System.out.println("//MAINTENANCE MODE DISABLED");
                                break;
                            case "#KVSECS": // disables cutscenes
                                statOptionNotChosen = true;
                                cutscenesEnabled = true;
                                System.out.println("//ACCELERATED READ/WRITE DISABLED");
                                break;
                            case "#KVSDCS": //enables cutscenes
                                statOptionNotChosen = true;
                                cutscenesEnabled = false;
                                System.out.println("//ACCELERATED READ/WRITE ENABLED");
                                break;
                            case "#KVSCLV": //changes language to Volkshavenish
                                statOptionNotChosen = true;
                                player.setUserLanguage("VOLKSHAVENISH");
                                System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                                break;
                            case "#KVSCLE": //changes language to English
                                statOptionNotChosen = true;
                                player.setUserLanguage("ENGLISH");
                                System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                break;
                            default:
                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                    System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                    System.out.println("//ERROR: INVALID INPUT.");
                                }
                                break;
                        }
                    }
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr": case "R": case "r":
                    player.writePlayerData();
                    vitals( player );
                    break;
                case "AUSSCHALTEN": case "ausschalten": case "ASCH": case "asch": case "A": case "a": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown( player );
                    break;
                case "BACK": case "back": case "0":
                    mmain( player );
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }

    }

    private static void skills ( playerCharacter player ) throws IOException, InterruptedException  {
        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player.getUserName() );
        NoDelay();
        System.out.print( " > VITALS" );
        NoDelay();
        System.out.println( " > SKILLS" );
        ShortDelay();
        System.out.print("//ANALYZING USER DATA...");
        LongDelay();



        /* Here, the program will then list the player's modifiers for skill checks.
         *
         *
         * The Vitals method makes use of the following indexes:
         * 00 - Player Name
         * 01 - Preferred Language (This will be set to the DaVG's native Volkshavenish by default)
         * 02 - Synchronization Level (Starts at 1)
         * 03 - Lifetime Field Data (Measured in DB)
         * 04 - Current HP (Set at 100 by default)
         * 05 - Maximum HP (Set at 100 by default)
         * 06 - Current Status (Normal, Poisoned, etc.)
         * 07 - Class 1
         * 08 - Class 2 (if applicable)
         * 09 - Class 3 (if applicable)
         * 10 - Class 4 (if applicable)
         * 11 - Strength score
         * 12 - Dexterity score
         * 13 - Constitution Score
         * 14 - Intelligence Score
         * 15 - Wisdom Score
         * 16 - Charisma Score
         * 17 - Background
         * 18 - Background Proficiency 1
         * 19 - Background Proficiency 2
         * 20 - Skill Proficiency 1
         * 21 - Skill Proficiency 2
         * 22 - Skill Proficiency 3 (If applicable)
         * 23 - Skill Proficiency 4 (If applicable)
         * 24 - Weapon Proficiency
         * 25 - Software Slot 1
         * 26 - Software Slot 2
         * 27 - Software Slot 3
         * 28 - Software Slot 4
         * 29 - Software Slot 5
         */

        boolean strMissing = player.getUserStrength().equals("StM");
        boolean dexMissing = player.getUserDexterity().equals("DeM");
        boolean conMissing = player.getUserConstitution().equals("CoM");
        boolean intMissing = player.getUserIntelligence().equals("InM");
        boolean wisMissing = player.getUserWisdom().equals("WiM");
        boolean chaMissing = player.getUserCharisma().equals("CaM");

        if ( strMissing || dexMissing || conMissing || intMissing || wisMissing || chaMissing ){
            System.out.println( "ERROR");
            ShortDelay();
            System.out.println( "//INSUFFICIENT DATA ON USER TO PROCEED" );
            ShortDelay();
            System.out.println( "//PLEASE ENTER USER DATA IN \"EDIT\" MENU" );
            ShortDelay();
            System.out.println( "//PRESS ENTER TO RETURN TO PREVIOUS MENU" );
            petc.nextLine();
            vitals( player );
        } else {
            System.out.println("COMPLETE");
            ShortDelay();
            System.out.print("//COMPILING RESULTS...");
            LongDelay();
            System.out.println("COMPLETE");
            ShortDelay();
            System.out.println( "//STRENGTH SKILLS" );
            ShortDelay();
            System.out.println( ">ATHLETICS MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserStrength())), profiCalc( player,"ATHLETICS" ) ) + " )");
            ShortDelay();
            System.out.println();

            System.out.println( "//DEXTERITY SKILLS" );
            ShortDelay();
            System.out.println( ">ACROBATICS MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserDexterity())), profiCalc( player,"ACROBATICS" ) ) + " )");
            NoDelay();
            System.out.println( ">SLEIGHT-OF-HAND MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserDexterity())), profiCalc( player,"SLEIGHT-OF-HAND" ) ) + " )");
            NoDelay();
            System.out.println( ">STEALTH MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserDexterity())), profiCalc( player,"STEALTH" ) ) +" )");
            ShortDelay();
            System.out.println();

            System.out.println( "//INTELLIGENCE SKILLS" );
            ShortDelay();
            System.out.println( ">ARCANA MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserIntelligence())), profiCalc( player,"ARCANA" ) ) +" )");
            NoDelay();
            System.out.println( ">HISTORY MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserIntelligence())), profiCalc( player,"HISTORY" ) ) +" )");
            NoDelay();
            System.out.println( ">INVESTIGATION MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserIntelligence())), profiCalc( player,"INVESTIGATION" ) ) +" )");
            NoDelay();
            System.out.println( ">NATURE MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserIntelligence())), profiCalc( player,"NATURE" ) ) +" )");
            NoDelay();
            System.out.println( ">RELIGION MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserIntelligence())), profiCalc( player,"RELIGION" ) ) +" )");
            ShortDelay();
            System.out.println();

            System.out.println( "//WISDOM SKILLS" );
            ShortDelay();
            System.out.println( ">ANIMAL HANDLING MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserWisdom())), profiCalc( player,"ANIMAL HANDLING" ) ) +" )");
            NoDelay();
            System.out.println( ">INSIGHT MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserWisdom())), profiCalc( player,"INSIGHT" ) ) +" )");
            NoDelay();
            System.out.println( ">MEDICINE MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserWisdom())), profiCalc( player,"MEDICINE" ) ) +" )");
            NoDelay();
            System.out.println( ">PERCEPTION MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserWisdom())), profiCalc( player,"PERCEPTION" ) ) +" )");
            NoDelay();
            System.out.println( ">SURVIVAL MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserWisdom())), profiCalc( player,"SURVIVAL" ) ) +" )");
            ShortDelay();
            System.out.println();

            System.out.println( "//CHARISMA SKILLS" );
            ShortDelay();
            System.out.println( ">DECEPTION MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserCharisma())), profiCalc( player,"DECEPTION" ) ) +" )");
            NoDelay();
            System.out.println( ">INTIMIDATION MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserCharisma())), profiCalc( player,"INTIMIDATION" ) ) +" )");
            NoDelay();
            System.out.println( ">PERFORMANCE MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserCharisma())), profiCalc( player,"PERFORMANCE" ) ) +" )");
            NoDelay();
            System.out.println( ">PERSUASION MODIFIER: R + ( " + Math.addExact( modCalc(Integer.parseInt(player.getUserCharisma())), profiCalc( player,"PERSUASION" ) ) +" )");
            ShortDelay();
            System.out.println();

            System.out.println("//PROFICIENCIES");
            ShortDelay();
            if ( player.getUserBackgroundProficiency1().equals("B1M") ){
                System.out.println(">BACKGROUND PROFICIENCY 1 (BGN1): [DATA MISSING]" );
            } else {
                System.out.println(">BACKGROUND PROFICIENCY 1 (BGN1): " + player.getUserBackgroundProficiency1());
            }
            NoDelay();
            if ( player.getUserBackgroundProficiency2().equals("B2M") ){
                System.out.println(">BACKGROUND PROFICIENCY 2 (BGN2): [DATA MISSING]" );
            } else {
                System.out.println(">BACKGROUND PROFICIENCY 2 (BGN2): " + player.getUserBackgroundProficiency2());
            }
            NoDelay();
            if ( player.getUserSkillProficiency1().equals("S1M") ) {
                System.out.println(">SKILL PROFICIENCY 1 (SKL1) : [DATA MISSING]" );
            } else {
                System.out.println(">SKILL PROFICIENCY 1 (SKL1) : " + player.getUserSkillProficiency1());
            }
            NoDelay();
            if ( player.getUserSkillProficiency2().equals("S2M") ) {
                System.out.println(">SKILL PROFICIENCY 2 (SKL2) : [DATA MISSING]" );
            } else {
                System.out.println(">SKILL PROFICIENCY 2 (SKL2) : " + player.getUserSkillProficiency2());
            }
            NoDelay();
            if ( player.getUserSkillProficiency3().equals("S3M") ){
                System.out.println(">SKILL PROFICIENCY 3 (SKL3) : [DATA MISSING]" );
            } else {
                System.out.println(">SKILL PROFICIENCY 3 (SKL3) : " + player.getUserSkillProficiency3());
            }
            NoDelay();
            if ( player.getUserSkillProficiency4().equals("S4M") ){
                System.out.println(">SKILL PROFICIENCY 4 (SKL4) : [DATA MISSING]" );
            } else {
                System.out.println(">SKILL PROFICIENCY 4 (SKL4) : " + player.getUserSkillProficiency4());
            }
            NoDelay();
            if ( player.getUserWeaponProficiency().equals("WpM") ){
                System.out.println(">WEAPON PROFICIENCY (WPNP) : [DATA MISSING]" );
            } else {
                System.out.println(">WEAPON PROFICIENCY (WPNP) : " + player.getUserWeaponProficiency());
            }
            ShortDelay();


        }
        boolean background1Missing = player.getUserBackgroundProficiency1().equals("B1M");
        boolean background2Missing = player.getUserBackgroundProficiency2().equals("B2M");
        boolean skill1Missing = player.getUserSkillProficiency1().equals("S1M");
        boolean skill2Missing = player.getUserSkillProficiency2().equals("S2M");
        boolean skill3Missing = player.getUserSkillProficiency3().equals("S3M");
        boolean skill4Missing = player.getUserSkillProficiency4().equals("S4M");
        boolean weaponMissing = player.getUserWeaponProficiency().equals("WpM");

        if (background1Missing || background2Missing || skill1Missing || skill2Missing || skill3Missing || skill4Missing || weaponMissing ){
            MedDelay();
            System.out.println( "//WARNING: DATA MISSING ON ONE OR MORE USER PROFICIENCIES" );
            ShortDelay();
            System.out.println( "//PLEASE USE \"EDIT\" FUNCTION TO COMPLETE MISSING ENTRIES" );
            ShortDelay();
        }

        System.out.println( ">EDIT (1/EDIT)");
        NoDelay();
        System.out.println( ">REFRESH (R/REFR)" );
        NoDelay();
        System.out.println( ">BACK (0/BACK)");
        boolean skillOptionNotChosen = true;
        while ( skillOptionNotChosen ) {
            switch ( input.next() ) {
                case "EDIT": case "edit": case "1":
                    System.out.println("// PLEASE SPECIFY VALUE TO EDIT");
                    boolean profiOptionNotChosen = true;
                    while ( profiOptionNotChosen ){
                        switch ( input.next() ){
                            case "BACKGROUND1": case "background1": case "BGN1": case "bgn1":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR BACKGROUND PROFICIENCY 1 (BGN1)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserBackgroundProficiency1( input.next() );
                                System.out.println( "//BACKGROUND PROFICIENCY 1 (BGN1) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//PRESS \"R\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "BACKGROUND2": case "background2": case "BGN2": case "bgn2":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR BACKGROUND PROFICIENCY 2 (BGN2)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserBackgroundProficiency2( input.next() );
                                System.out.println( "//BACKGROUND PROFICIENCY 2 (BGN2) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//PRESS \"R\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "SKILL1": case "skill1": case "SKL1": case "skl1":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 1 (SKL1)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserSkillProficiency1( input.next() );
                                System.out.println( "//SKILL PROFICIENCY 1 (SKL1) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                ShortDelay();
                                System.out.println( "//PRESS \"R\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "SKILL2": case "skill2": case "SKL2": case "skl2":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 2 (SKL2)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserSkillProficiency2( input.next() );
                                System.out.println( "//SKILL PROFICIENCY 2 (SKL2) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                ShortDelay();
                                System.out.println( "//PRESS \"R\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "SKILL3": case "skill3": case "SKL3": case "skl3":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 3 (SKL3)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserSkillProficiency3( input.next() );
                                System.out.println( "//SKILL PROFICIENCY 3 (SKL3) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                ShortDelay();
                                System.out.println( "//PRESS \"R\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "SKILL4": case "skill4": case "SKL4": case "skl4":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 4 (SKL4)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserSkillProficiency4( input.next() );
                                System.out.println( "//SKILL PROFICIENCY 4 (SKL4) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                ShortDelay();
                                System.out.println( "//PRESS \"R\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "WEAPON": case "weapon": case "WPNP": case "wpnp":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR WEAPON PROFICIENCY (WPNP)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                ShortDelay();
                                System.out.println( "//WARNING: GAPS IN NAME MUST BE INDICATED WITH UNDERSCORES" );
                                player.setUserWeaponProficiency( input.next() );
                                System.out.println( "//WEAPON PROFICIENCY (WPNP) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                ShortDelay();
                                System.out.println( "//PRESS \"R\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr": case "R": case "r":
                                skills( player );
                                break;
                        }
                    }
                    break;
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr": case "R": case "r":
                    skills( player );
                    break;
                case "BACK": case "back": case "0":
                    vitals( player );
                    break;
                case "AUSSCHALTEN": case "ausschalten": case "ASCH": case "asch": case "A": case "a": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    skillOptionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    skillOptionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    skillOptionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    skillOptionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    skillOptionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    skillOptionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
    }

    private static int modCalc( int abilityScore ) {
        int modifier = 0;
        switch ( abilityScore ){
            case 1:
                modifier =  -5;
                break;
            case 2: case 3:
                modifier =  -4;
                break;
            case 4: case 5:
                modifier =  -3;
                break;
            case 6: case 7:
                modifier =  -2;
                break;
            case 8: case 9:
                modifier =  -1;
                break;
            case 10: case 11:
                modifier =  0;
                break;
            case 12: case 13:
                modifier =  1;
                break;
            case 14: case 15:
                modifier =  2;
                break;
            case 16: case 17:
                modifier =  3;
                break;
            case 18: case 19:
                modifier =  4;
                break;
            case 20: case 21:
                modifier =  5;
                break;
            case 22: case 23:
                modifier =  6;
                break;
            case 24: case 25:
                modifier =  7;
                break;
            case 26: case 27:
                modifier =  8;
                break;
            case 28: case 29:
                modifier =  9;
                break;
            case 30:
                modifier =  10;
                break;
            default:
                modifier =  0;
                break;
        }

        return modifier;
    }

    private static int profiCalc( playerCharacter player, String skill ){
        int profiBonus = 0;

        if ( Integer.parseInt(player.getUserLevel()) <= 4 ){
            if ( player.getUserBackgroundProficiency1().equals(skill) || player.getUserBackgroundProficiency2().equals(skill) ){
                profiBonus = 2;
                return profiBonus;
            } else if ( player.getUserSkillProficiency1().equals(skill) || player.getUserSkillProficiency2().equals(skill) || player.getUserSkillProficiency3().equals(skill) || player.getUserSkillProficiency4().equals(skill) ){
                profiBonus = 2;
                return profiBonus;
            } else if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ){
                profiBonus = 2;
                return profiBonus;
            }
        } else if ( 4 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 8){
            if ( player.getUserBackgroundProficiency1().equals(skill) || player.getUserBackgroundProficiency2().equals(skill) ){
                profiBonus = 3;
                return profiBonus;
            } else if ( player.getUserSkillProficiency1().equals(skill) || player.getUserSkillProficiency2().equals(skill) || player.getUserSkillProficiency3().equals(skill) || player.getUserSkillProficiency4().equals(skill) ){
                profiBonus = 3;
                return profiBonus;
            } else if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ){
                profiBonus = 3;
                return profiBonus;
            }
        } else if ( 8 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 12){
            if ( player.getUserBackgroundProficiency1().equals(skill) || player.getUserBackgroundProficiency2().equals(skill) ){
                profiBonus = 4;
                return profiBonus;
            } else if ( player.getUserSkillProficiency1().equals(skill) || player.getUserSkillProficiency2().equals(skill) || player.getUserSkillProficiency3().equals(skill) || player.getUserSkillProficiency4().equals(skill) ){
                profiBonus = 4;
                return profiBonus;
            } else if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ){
                profiBonus = 4;
                return profiBonus;
            }
        } else if ( 12 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 16){
            if ( player.getUserBackgroundProficiency1().equals(skill) || player.getUserBackgroundProficiency2().equals(skill) ){
                profiBonus = 5;
                return profiBonus;
            } else if ( player.getUserSkillProficiency1().equals(skill) || player.getUserSkillProficiency2().equals(skill) || player.getUserSkillProficiency3().equals(skill) || player.getUserSkillProficiency4().equals(skill) ){
                profiBonus = 5;
                return profiBonus;
            } else if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ){
                profiBonus = 5;
                return profiBonus;
            }
        } else if ( 16 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 20){
            if ( player.getUserBackgroundProficiency1().equals(skill) || player.getUserBackgroundProficiency2().equals(skill) ){
                profiBonus = 6;
                return profiBonus;
            } else if ( player.getUserSkillProficiency1().equals(skill) || player.getUserSkillProficiency2().equals(skill) || player.getUserSkillProficiency3().equals(skill) || player.getUserSkillProficiency4().equals(skill) ){
                profiBonus = 6;
                return profiBonus;
            } else if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ){
                profiBonus = 6;
                return profiBonus;
            }
        } else if ( Integer.parseInt(player.getUserLevel()) < 20){
            if ( player.getUserBackgroundProficiency1().equals(skill) || player.getUserBackgroundProficiency2().equals(skill) ){
                profiBonus = 6 + ( ( Integer.parseInt(player.getUserLevel()) - 20 ) / 4 );
                return profiBonus;
            } else if ( player.getUserSkillProficiency1().equals(skill) || player.getUserSkillProficiency2().equals(skill) || player.getUserSkillProficiency3().equals(skill) || player.getUserSkillProficiency4().equals(skill) ){
                profiBonus = 6 + ( ( Integer.parseInt(player.getUserLevel()) - 20 ) / 4 );
                return profiBonus;
            } else if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ){
                profiBonus = 6 + ( ( Integer.parseInt(player.getUserLevel()) - 20 ) / 4 );
                return profiBonus;
            }
        }

        return profiBonus;
    }


    private static void inventory( playerCharacter player ) throws IOException, InterruptedException {
        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player.getUserName() );
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
                    weapons( player );
                    break;
                case "AMMUNITION": case "ammunition": case "AMMO": case "ammo": case "2":
                    ammunition( player );
                    break;
                case "EQUIPMENT": case "equipment": case "EQUP": case "equp": case "3":
                    equipment ( player );
                    break;
                case "MEDICAL": case "medical": case "MEDS": case "meds": case "4":
                    medical ( player );
                    break;
                case "MISCELLANEOUS": case "miscellaneous": case "MISC": case "misc": case "5":
                    miscellaneous( player );
                    break;
                case "BACK": case "back": case "0":
                    mmain( player );
                    break;
                case "AUSSCHALTEN": case "ausschalten": case "ASCH": case "asch": case "A": case "a": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }

    }

    private static void weapons( playerCharacter player ) throws IOException, InterruptedException {
    ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player.getUserName() );
        NoDelay();
        System.out.print( " > INVENTORY" );
        NoDelay();
        System.out.println( " > WEAPONS" );
        NoDelay();
        System.out.print( "//SCANNING ITEM REGISTRY..." );

        File[] inventory = player.getPlayerInventory();

        TESTWEAP = new File(inventory[1]+"\\TESTWEAP");
        TESTWEAPTAG = new File(inventory[1]+"\\TESTWEAP\\ItemID.TAG");

        FtA_45 = new File(inventory[1]+"\\FtA_45");
        FtA_45TAG = new File(inventory[1]+"\\FtA_45\\ItemID.TAG");

        LP61 = new File(inventory[1]+"\\LP61");
        LP61TAG = new File(inventory[1]+"\\LP61\\ItemID.TAG");

        MP60 = new File(inventory[1]+"\\MP60");
        MP60TAG = new File(inventory[1]+"\\MP60\\ItemID.TAG");

        P1885 = new File(inventory[1]+"\\P.1885");
        P1885TAG = new File(inventory[1]+"\\P.1885\\ItemID.TAG");

        P60 = new File(inventory[1]+"\\P60");
        P60TAG = new File(inventory[1]+"\\P60\\ItemID.TAG");

        if (weaponsCheck()){
            ShortDelay();
            System.out.println("COMPLETE");
            ShortDelay();
            if (TESTWEAP.isDirectory()){
                if (TESTWEAPTAG.isFile()){
                    if (player.getPrimaryWeapon().equals("000")){
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
                    if (player.getPrimaryWeapon().equals("002")){
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
                    if (player.getPrimaryWeapon().equals("003")){
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
                    if (player.getPrimaryWeapon().equals("004")){
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
                    if (player.getPrimaryWeapon().equals("005")){
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
                    if (player.getPrimaryWeapon().equals("006")){
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
                            weaponLoad( player, "TESTWEAP" );
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
                            weaponLoad( player, "FtA_45" );
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
                            weaponLoad( player, "LP61" );
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
                            weaponLoad( player, "MP60" );
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
                            weaponLoad( player, "P.1885" );
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
                            weaponLoad( player, "P60");
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
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr": case "R": case "r":
                    weapons( player );
                    break;
                case "BACK": case "back": case "0":
                    inventory( player );
                    break;
                case "AUSSCHALTEN": case "ausschalten": case "ASCH": case "asch": case "A": case "a": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }

    }

    private static boolean weaponsCheck(){

        boolean[] sidearmCheck = new boolean[6];
        sidearmCheck[0] = TESTWEAP.isDirectory();
        sidearmCheck[1] = FtA_45.isDirectory();
        sidearmCheck[2] = LP61.isDirectory();
        sidearmCheck[3] = MP60.isDirectory();
        sidearmCheck[4] = P1885.isDirectory();
        sidearmCheck[5] = P60.isDirectory();

        for (int i = 0; i < sidearmCheck.length; i++){
            if (sidearmCheck[i]){
                return true;
            }
        }

        return false;
    }

    private static void weaponLoad( playerCharacter player, String weaponName ) throws IOException, InterruptedException {
        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        File[] inventory = player.getPlayerInventory();
        ShortDelay();
        System.out.print( player.getUserName() );
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
        BufferedReader weaponScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\ItemID.TAG"));
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
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr": case "R": case "r":
                    weaponLoad( player, weaponName );
                    break;
                case "EQUIP": case "equip": case "EQUP": case "equp": case "1":
                    if (player.getPrimaryWeapon().equals(weaponData[3])){
                        System.out.println("//ERROR: WEAPON ALREADY EQUIPPED!");
                        optionNotChosen = true;
                        break;
                    } else {
                        System.out.println("//ARE YOU SURE YOU WANT TO EQUIP THIS WEAPON? Y/N");
                        boolean equipOptionNotChosen = true;
                        while ( equipOptionNotChosen ) {
                            switch ( input.next() ) {
                                case "J": case "j": case "1":
                                    player.setPrimaryWeapon( weaponData[3] );
                                    System.out.println("//CURRENT WEAPON CHANGED");
                                    ShortDelay();
                                    System.out.println("//PRESS ENTER TO RETURN TO WEAPONS MENU");
                                    petc.nextLine();
                                    weapons( player );
                                    break;
                                case "N": case "n": case "0":
                                    System.out.println( "//RETURNING TO WEAPONS MENU." );
                                    LongDelay();
                                    weapons( player );
                                    break;
                                case "#KVSEDBM": //enables debug mode
                                    equipOptionNotChosen = true;
                                    debugMode = true;
                                    System.out.println("//MAINTENANCE MODE ENABLED");
                                    break;
                                case "#KVSDDBM": //disables debug mode
                                    equipOptionNotChosen = true;
                                    debugMode = false;
                                    System.out.println("//MAINTENANCE MODE DISABLED");
                                    break;
                                case "#KVSECS": // disables cutscenes
                                    equipOptionNotChosen = true;
                                    cutscenesEnabled = true;
                                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                                    break;
                                case "#KVSDCS": //enables cutscenes
                                    equipOptionNotChosen = true;
                                    cutscenesEnabled = false;
                                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                                    break;
                                case "#KVSCLV": //changes language to Volkshavenish
                                    equipOptionNotChosen = true;
                                    player.setUserLanguage("VOLKSHAVENISH");
                                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                                    break;
                                case "#KVSCLE": //changes language to English
                                    equipOptionNotChosen = true;
                                    player.setUserLanguage("ENGLISH");
                                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                    break;
                                default:
                                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                                    } else if (player.getUserLanguage().equals("ENGLISH")){
                                        System.out.println("//ERROR: INVALID INPUT.");
                                    }
                                    break;
                            }
                        }
                    }
                    break;
                case "UNEQUIP": case "unequip": case "UEQP": case "ueqp": case "2":
                    if (!player.getPrimaryWeapon().equals(weaponData[3])){
                        System.out.println("//ERROR: WEAPON NOT EQUIPPED!");
                        optionNotChosen = true;
                        break;
                    } else {
                        System.out.println("//ARE YOU SURE YOU WANT TO UNEQUIP THIS WEAPON? Y/N");
                        boolean equipOptionNotChosen = true;
                        while ( equipOptionNotChosen ) {
                            switch ( input.next() ) {
                                case "J": case "j": case "1":
                                    player.setPrimaryWeapon( "001" );
                                    System.out.println("//CURRENT WEAPON CHANGED");
                                    ShortDelay();
                                    System.out.println("//PRESS ENTER TO RETURN TO MAIN MENU");
                                    petc.nextLine();
                                    weapons( player );
                                    break;
                                case "N": case "n": case "0":
                                    System.out.println( "//RETURNING TO WEAPONS MENU." );
                                    LongDelay();
                                    weapons( player );
                                    break;
                                case "#KVSEDBM": //enables debug mode
                                    equipOptionNotChosen = true;
                                    debugMode = true;
                                    System.out.println("//MAINTENANCE MODE ENABLED");
                                    break;
                                case "#KVSDDBM": //disables debug mode
                                    equipOptionNotChosen = true;
                                    debugMode = false;
                                    System.out.println("//MAINTENANCE MODE DISABLED");
                                    break;
                                case "#KVSECS": // disables cutscenes
                                    equipOptionNotChosen = true;
                                    cutscenesEnabled = true;
                                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                                    break;
                                case "#KVSDCS": //enables cutscenes
                                    equipOptionNotChosen = true;
                                    cutscenesEnabled = false;
                                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                                    break;
                                case "#KVSCLV": //changes language to Volkshavenish
                                    equipOptionNotChosen = true;
                                    player.setUserLanguage("VOLKSHAVENISH");
                                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                                    break;
                                case "#KVSCLE": //changes language to English
                                    equipOptionNotChosen = true;
                                    player.setUserLanguage("ENGLISH");
                                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                    break;
                                default:
                                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                                    } else if (player.getUserLanguage().equals("ENGLISH")){
                                        System.out.println("//ERROR: INVALID INPUT.");
                                    }
                                    break;
                            }
                        }
                    }
                    break;
                case "AUSSCHALTEN": case "ausschalten": case "ASCH": case "asch": case "A": case "a": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown( player );
                    break;
                case "BACK": case "back": case "0":
                    weapons( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }

    }

    private static void ammunition( playerCharacter player ) throws IOException, InterruptedException {
        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player.getUserName() );
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
                    mmain( player );
                    break;
                case "2":
                    shutdown( player );
                    break;
                case "AUSSCHALTEN": case "ausschalten": case "ASCH": case "asch": case "A": case "a": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown( player );
                    break;
                case "#KVSEDBM":
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println( "//MAINTENANCE MODE ENABLED" );
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
    }

    private static void equipment( playerCharacter player ) throws IOException, InterruptedException {
        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player.getUserName() );
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
                    mmain( player );
                    break;
                case "2":
                    shutdown( player );
                    break;
                case "AUSSCHALTEN": case "ausschalten": case "ASCH": case "asch": case "A": case "a": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
    }

    private static void medical( playerCharacter player ) throws IOException, InterruptedException {
        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player );
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
                    mmain( player );
                    break;
                case "2":
                    shutdown( player );
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
    }

    private static void miscellaneous( playerCharacter player ) throws IOException, InterruptedException {
        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player.getUserName() );
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
                    mmain( player );
                    break;
                case "2":
                    shutdown( player );
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
    }

    private static void journal( playerCharacter player ) throws IOException, InterruptedException {
        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player );
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
                    mmain( player );
                    break;
                case "2":
                    shutdown( player );
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
    }

    private static void utility( playerCharacter player) throws IOException, InterruptedException {
        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player.getUserName() );
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
                    mmain( player );
                    break;
                case "2":
                    shutdown( player );
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
    }

    private static void combat( playerCharacter player ) throws IOException, InterruptedException {
        ClearScreen();
        if (debugMode){
            System.out.print( "//USER [DEBUG] | " );
        } else {
            System.out.print( "//USER | " );
        }
        ShortDelay();
        System.out.print( player.getUserName() );
        NoDelay();
        System.out.println( " > COMBAT" );
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
                    mmain( player );
                    break;
                case "2":
                    shutdown( player );
                    break;
                case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P":
                    shutdown( player );
                    break;
                case "#KVSEDBM": //enables debug mode
                    optionNotChosen = true;
                    debugMode = true;
                    System.out.println("//MAINTENANCE MODE ENABLED");
                    break;
                case "#KVSDDBM": //disables debug mode
                    optionNotChosen = true;
                    debugMode = false;
                    System.out.println("//MAINTENANCE MODE DISABLED");
                    break;
                case "#KVSECS": // disables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = true;
                    System.out.println("//ACCELERATED READ/WRITE DISABLED");
                    break;
                case "#KVSDCS": //enables cutscenes
                    optionNotChosen = true;
                    cutscenesEnabled = false;
                    System.out.println("//ACCELERATED READ/WRITE ENABLED");
                    break;
                case "#KVSCLV": //changes language to Volkshavenish
                    optionNotChosen = true;
                    player.setUserLanguage("VOLKSHAVENISH");
                    System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//ACHTUNG: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
    }

    private static void sing( playerCharacter player ) throws IOException, InterruptedException {
        ClearScreen();
        System.out.print("//LOADING VOCAL PERFORMANCE SUBROUTINE...");
        LongDelay();
        System.out.println("//COMPLETE");
        ShortDelay();
        System.out.println("//THIS SUBROUTINE IS DESIGNED TO WORK WITH \"HELLO WORLD\" BY LOUIE ZONG PLAYING FROM A DIFFERENT SOURCE");
        ShortDelay();
        System.out.println("//PLEASE PRESS ENTER WHEN YOU ARE READY");
        petc.nextLine();
        System.out.println("//3...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("//2...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("//1...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("//GO");
        TimeUnit.SECONDS.sleep(4);
        ClearScreen();
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//HEL");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("WORLD...");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//PRO");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("GRAMMED ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("TO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("WORK ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("AND ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("NOT ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("TO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("FEEL... ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//NOT ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("E");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("VEN ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("SURE ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("THAT ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("THIS ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("IS ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("REAL... ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//HEL");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("WORLD...");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//FIND ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("MY ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("VOICE... ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//AL");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("THOUGH ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("IT ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("SOUNDS ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LIKE ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("BITS ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("AND ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("BYTES... ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//MY ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("CIR");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("CUI");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("TRY ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("IS ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("FILLED ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("WITH ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("MITES...");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//HEL");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("WORLD...");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//OH, ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("WILL ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("I ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("FIND ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("A ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("LOVE?");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//OH, ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("OR ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("A ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("PO");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("WER ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("PLUG...");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//OH, ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("DI");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("GI");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("TAL");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LY ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("I");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("SO");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LA");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("TED, ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("OH, ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("CRE");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("A");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("TOR, ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("PLEASE, ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("DON'T ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LEAVE ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("ME ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("WAI");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("TING...");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//HEL");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("WORLD...");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//PRO");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("GRAMMED ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("TO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("WORK ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("AND ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("NOT ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("TO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("FEEL... ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//NOT ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("E");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("VEN ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("SURE ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("THAT ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("THIS ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("IS ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("REAL... ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//HEL");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("WORLD...");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );

        System.out.println("//VOCAL PERFORMANCE SUBROUTINE COMPLETE");
        ShortDelay();
        System.out.println("//THANK YOU FOR LISTENING TO ME SING");
        ShortDelay();
        System.out.println("//PLEASE PRESS ENTER/RETURN TO RETURN TO MAIN MENU");
        petc.nextLine();
        mmain( player );




    }

    private static void shutdown( playerCharacter player ) throws InterruptedException, IOException, NullPointerException {
        if (!player.isEmpty){
            System.out.println( "//SCHREIBEN DATEN AUF FESTPLATTE..." );
            player.writePlayerData();
            ShutoffDelay();
            System.out.println( "//SCHREIBEN KOMPLETT" );
        }
        ShortDelay();
        System.out.println( "//IST DAS JETZT SICHER ZU DEAKTIVIEREN EINHEIT");
        ShortDelay();
        System.out.println ( "//DRÜCKEN EINGEBEN/RÜCKKEHR ZU FORTSETZEN" );
        petc.nextLine();
        System.exit( 0 );
        ClearScreen();
    }
}
