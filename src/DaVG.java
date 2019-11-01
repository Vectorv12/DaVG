
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



    // \/SIDEARMS\/
    private static File TESTWEAP;
    private static File TESTWEAPAMMO;
    private static File TESTWEAPTAG;
    private static File TESTWEAPAMMOTAG;

    private static File FtA_45;
    private static File FtA_45AMMO;
    private static File FtA_45TAG;
    private static File FtA_45AMMOTAG;

    private static File LP61;
    private static File LP61AMMO;
    private static File LP61TAG;
    private static File LP61AMMOTAG;

    private static File MP60;
    private static File MP60AMMO;
    private static File MP60TAG;
    private static File MP60AMMOTAG;

    private static File P1885;
    private static File P1885AMMO;
    private static File P1885TAG;
    private static File P1885AMMOTAG;

    private static File P60;
    private static File P60AMMO;
    private static File P60TAG;
    private static File P60AMMOTAG;
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
            LongDelay();
            System.out.println( "//FEHLER: KEINE PROFIL GEFUNDEN IM FESTPLATTE " ); //ERROR: NO PROFILES FOUND IN HARD DRIVE
            ShortDelay();
            System.out.println( "//ERSTELLEN NEU PROFIL? J/N" ); //CREATE NEW PROFILE? J/N (Like German, "Ja" is "Yes" and "Nein" is "No")
            ShortDelay();
            System.out.println( "//WARNUNG: DRÜCKEN \"N\" WIRD DEAKTIVIERT EINHEIT ZU VERHINDERN MANIPULATION" ); //WARNING: PRESSING "N" WILL DEACTIVATE THE DEVICE TO PREVENT TAMPERING
            boolean optionNotChosen = true;
            while (optionNotChosen) {
                if (debugMode) {
                    System.out.println(profileList);
                }
                switch (input.nextLine()) {
                    case "J": case "j":
                        optionNotChosen = false;
                        System.out.println("//BITTE EINGEBEN PROFIL-BENENNUNG"); //PLEASE ENTER PROFILE NAME
                        ShortDelay();
                        System.out.println("//WARNUNG: PROFIL-BENENNUNGEN SIND GROß-UND-KLEINSCHREIBUNG"); //WARNING: PROFILE NAMES ARE CASE-SENSITIVE
                        player.setIsEmpty( false );
                        player.setUserName(input.nextLine());
                        break;
                    case "N": case "n":
                        optionNotChosen = false;
                        shutdown( player );
                        break;
                    case "#KVSEDBM": //enables debug mode
                        debugMode = true;
                        System.out.println("//MAINTENANCE MODE ENABLED");
                        break;
                    case "#KVSDDBM": //disables debug mode
                        debugMode = false;
                        System.out.println("//MAINTENANCE MODE DISABLED");
                        break;
                    case "#KVSECS": // disables cutscenes
                        cutscenesEnabled = true;
                        System.out.println("//ACCELERATED READ/WRITE DISABLED");
                        break;
                    case "#KVSDCS": //enables cutscenes
                        cutscenesEnabled = false;
                        System.out.println("//ACCELERATED READ/WRITE ENABLED");
                        break;
                    case "#KVSCLV": //changes language to Volkshavenish
                        player.setUserLanguage("VOLKSHAVENISH");
                        System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                        break;
                    case "#KVSCLE": //changes language to English
                        player.setUserLanguage("ENGLISH");
                        System.out.println("//LANGUAGE PREFERENCE UPDATED");
                        break;
                    default:
                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
                String profileInput = input.nextLine();
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
                    case "":
                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                        } else if (player.getUserLanguage().equals("ENGLISH")){
                            System.out.println("//ERROR: INVALID INPUT.");
                        }
                        break;
                    default:
                        profileNotChosen = false;
                        player.setUserName(profileInput);
                        break;
                }
            }
        }
        File requestedProfile = new File( player.getDocPath() + VersionNo + "_Data\\" + player.getUserName() + "\\" );
        if (!requestedProfile.isDirectory()){
            if (!veryFirstBoot){
                LongDelay();
                System.out.println( "//FEHLER: SPEZIFIZIERTEN BENUTZER-PROFIL NICHT GEFUNDEN" ); //WARNING: SPECIFIED USER PROFILE NOT FOUND
                ShortDelay();
            }
            System.out.println( "//SCHAFFEN NEU BENUTZER-PROFIL MIT DIESER NAME? J/N" ); //CREATE NEW PROFILE WITH THIS NAME? J/N
            ShortDelay();
            System.out.println( "//WARNUNG: DRÜCKEN \"N\" WIRD DEAKTIVIERT EINHEIT ZU VERHINDERN MANIPULATION" ); //WARNING: PRESSING "N" WILL DEACTIVATE THE DEVICE TO PREVENT TAMPERING
            boolean equipOptionNotChosen = true;
            while ( equipOptionNotChosen ) {
                switch ( input.nextLine() ) {
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
                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
                    switch (input.nextLine()) {
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
                System.out.println( "//FEHLER: KEINE DATEN IM SPEZIFIZIERTEN FESTPLATTE-SEKTOR" ); //ERROR: NO DATA IN SPECIFIED HARD DRIVE SECTOR
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
            LongDelay();
            System.out.println( "//FEHLER: EINHEIT NICHT VERBUNDEN MIT BESATZUNG-DATENBANK" ); //ERROR: UNIT NOT LINKED WITH PERSONNEL DATABASE
            NoDelay();
            System.out.println( "//ÜBERSCHREIBE? J/N" ); //OVERRIDE? Y/N
            ShortDelay();
            System.out.println( "//WARNUNG: DRÜCKEN \"N\" WIRD DEAKTIVIERT EINHEIT ZU VERHINDERN MANIPULATION" ); //WARNING: PRESSING "N" WILL DEACTIVATE UNIT TO PREVENT TAMPERING
            boolean optionNotChosen = true;
            while ( optionNotChosen ) {
                switch ( input.nextLine() ) {
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
                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
        System.out.println( "//FEHLER: KEINE DATEN ZUM GEGENWÄRTIG-BENUTZER GEFUNDEN." );//ERROR: NO DATA ON CURRENT USER FOUND.
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
            switch ( input.nextLine() ) {
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
        System.out.println( "//BITTE EINGEBEN NEU BENUTZERNAME" );//PLEASE INPUT NEW USER NAME.
        String userName = input.nextLine();
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
        System.out.println( "//DRÜCKEN EINGABE/RÜCKKEHR ZU FORTSETZEN" ); //PRESS ENTER/RETURN TO CONTINUE
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
            System.out.println( ">REFRESH (N/NULD) ");
            NoDelay();
            System.out.println( ">SHUTDOWN (A/ASCH) " );
            NoDelay();
        }
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
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
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr":
                    mmain(player);
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }

    }

    private static void roll ( playerCharacter player ) throws InterruptedException {

        boolean strMissing = player.getUserStrength().equals("StM");
        boolean dexMissing = player.getUserDexterity().equals("DeM");
        boolean conMissing = player.getUserConstitution().equals("CoM");
        boolean intMissing = player.getUserIntelligence().equals("InM");
        boolean wisMissing = player.getUserWisdom().equals("WiM");
        boolean chaMissing = player.getUserCharisma().equals("CaM");

        System.out.println("//PLEASE ENTER VALUE OF DIE ROLL");
        int roll = 0;
        try {
            roll = input.nextInt();
        } catch ( Exception e){
            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
            } else if (player.getUserLanguage().equals("ENGLISH")){
                System.out.println("//ERROR: INVALID INPUT.");
            }
            input = new Scanner(System.in);
            while (!input.hasNextInt()){
                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                } else if (player.getUserLanguage().equals("ENGLISH")){
                    System.out.println("//ERROR: INVALID INPUT.");
                }
                input = new Scanner(System.in);
            }
            roll = input.nextInt();
        }
        System.out.println("//PLEASE ENTER SKILL TO BE CHECKED (IF APPLICABLE)");
        ShortDelay();
        System.out.println("//IF NO SKILL IS BEING CHECKED ENTER \"N/A\"");
        input = new Scanner (System.in);
        String mode = input.nextLine();
        switch (mode) {
            // \/ CORE STATS \/
            case "STRENGTH": case "strength": case "STRN": case "strn":
                if (strMissing){
                    System.out.println("//ERROR: USER STRENGTH DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserStrength())) + sftwrCalc(player, "STRENGTH");
                }
                break;
            case "DEXTERITY": case "dexterity": case "DEXT": case "dext": case "INITIATIVE": case "initiative": case "INIT": case "init":
                if (dexMissing){
                    System.out.println("//ERROR: USER DEXTERITY DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc(Integer.parseInt(player.getUserDexterity())) + sftwrCalc(player, "DEXTERITY") ;
                }
                break;
            case "CONSTITUTION": case "constitution": case "CNST": case "cnst":
                if (conMissing){
                    System.out.println("//ERROR: USER CONSTITUTION DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserConstitution())) + sftwrCalc(player, "CONSTITUTION") ;
                }
                break;
            case "INTELLIGENCE": case "intelligence": case "INTL": case "intl":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + sftwrCalc(player, "INTELLIGENCE") ;
                }
                break;
            case "WISDOM": case "wisdom": case "WSDM": case "wsdm":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + sftwrCalc(player, "WISDOM") ;
                }
                break;
            case "CHARISMA": case "charisma": case "CHRM": case "chrm":
                if (chaMissing){
                    System.out.println("//ERROR: USER CHARISMA DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserCharisma())) + sftwrCalc(player, "CHARISMA");
                }
                break;
            // /\ CORE STATS /\

            // \/ STRENGTH SKILLS \/
            case "ATHLETICS": case "athletics": case "STR0": case "str0":
                if (strMissing){
                    System.out.println("//ERROR: USER STRENGTH DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserStrength())) + sftwrCalc(player, "ATHLETICS") + profiCalc(player, "ATHLETICS");
                }
                break;
            // /\ STRENGTH SKILLS /\

            // \/ DEXTERITY SKILLS \/
            case "ACROBATICS": case "acrobatics": case "DEX0": case "dex0":
                if (dexMissing){
                    System.out.println("//ERROR: USER DEXTERITY DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc(Integer.parseInt(player.getUserDexterity())) + sftwrCalc(player, "ACROBATICS") + profiCalc(player, "ACROBATICS");
                }
                break;
            case "SLEIGHT-OF-HAND": case "sleight-of-hand": case "DEX1": case "dex1":
                if (dexMissing){
                    System.out.println("//ERROR: USER DEXTERITY DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc(Integer.parseInt(player.getUserDexterity())) + sftwrCalc(player, "SLEIGHT-OF-HAND") + profiCalc(player, "SLEIGHT-OF-HAND");
                }
                break;
            case "STEALTH": case "stealth": case "DEX2": case "dex2":
                if (dexMissing){
                    System.out.println("//ERROR: USER DEXTERITY DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc(Integer.parseInt(player.getUserDexterity())) + sftwrCalc(player, "STEALTH") + profiCalc(player, "STEALTH");
                }
                break;
            // /\ DEXTERITY SKILLS /\

            // \/ INTELLIGENCE SKILLS \/
            case "ARCANA": case "arcana": case "INT0": case "int0":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + sftwrCalc(player, "ARCANA") + profiCalc(player, "ARCANA");
                }
                break;
            case "HISTORY": case "history": case "INT1": case "int1":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + sftwrCalc(player, "HISTORY") + profiCalc(player, "HISTORY");
                }
                break;
            case "INVESTIGATION": case "investigation": case "INT2": case "int2":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + sftwrCalc(player, "INVESTIGATION") + profiCalc(player, "INVESTIGATION");
                }
                break;
            case "NATURE": case "nature": case "INT3": case "int3":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + sftwrCalc(player, "NATURE") + profiCalc(player, "NATURE");
                }
                break;
            case "RELIGION": case "religion": case "INT4": case "int4":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + sftwrCalc(player, "RELIGION") + profiCalc(player, "RELIGION");
                }
                break;
            // /\ INTELLIGENCE SKILLS /\

            // \/ WISDOM SKILLS \/
            case "ANIMAL_HANDLING": case "animal_handling": case "ANIMALHANDLING": case "animalhandling": case "WSD0": case "wsd0":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + sftwrCalc(player, "ANIMAL HANDLING") + profiCalc(player, "ANIMAL HANDLING");
                }
                break;
            case "INSIGHT": case "insight": case "WSD1": case "wsd1":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + sftwrCalc(player, "INSIGHT") + profiCalc(player, "INSIGHT");
                }
                break;
            case "MEDICINE": case "medicine": case "WSD2": case "wsd2":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + sftwrCalc(player, "MEDICINE") + profiCalc(player, "MEDICINE");
                }
                break;
            case "PERCEPTION": case "perception": case "WSD3": case "wsd3":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + sftwrCalc(player, "PERCEPTION") + profiCalc(player, "PERCEPTION");
                }
                break;
            case "SURVIVAL": case "survival": case "WSD4": case "wsd4":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + sftwrCalc(player, "SURVIVAL") + profiCalc(player, "SURVIVAL");
                }
                break;
            // /\ WISDOM SKILLS /\

            // \/ CHARISMA SKILLS \/
            case "DECEPTION": case "deception": case "CHR0": case "chr0":
                if (chaMissing){
                    System.out.println("//ERROR: USER CHARISMA DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserCharisma())) + sftwrCalc(player, "DECEPTION") + profiCalc(player, "DECEPTION");
                }
                break;
            case "INTIMIDATION": case "intimidation": case "CHR1": case "chr1":
                if (chaMissing){
                    System.out.println("//ERROR: USER CHARISMA DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserCharisma())) + sftwrCalc(player, "INTIMIDATION") + profiCalc(player, "INTIMIDATION");
                }
                break;
            case "PERFORMANCE": case "performance": case "CHR2": case "chr2":
                if (chaMissing){
                    System.out.println("//ERROR: USER CHARISMA DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserCharisma())) + sftwrCalc(player, "PERFORMANCE") + profiCalc(player, "PERFORMANCE");
                }
                break;
            case "PERSUASION": case "persuasion": case "CHR3": case "chr3":
                if (chaMissing){
                    System.out.println("//ERROR: USER CHARISMA DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserCharisma())) + sftwrCalc(player, "PERSUASION") + profiCalc(player, "PERSUASION");
                }
                break;
            // /\ CHARISMA SKILLS /\
            default:
                roll += 0;
                break;
        }
        System.out.println( "//RESULT: " + roll );
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
        System.out.println();
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

        System.out.println( "//CURRENT HEALTH (HP): " + player.getUserCurrentHealth() + "/" + player.getUserMaxHealth() + " HP" );
        NoDelay();
        System.out.println( "//OVERALL VITAL STATUS (STAT): " + player.getUserStatus() );
        NoDelay();
        System.out.println( "//SYNCHRONIZATION LEVEL (LEVL): " + player.getUserLevel() );
        NoDelay();
        System.out.println( "//LIFETIME USER PERFORMANCE DATA GATHERED (DATA): " + player.getUserXP() + " DB");
        System.out.println();
        NoDelay();
        if (player.getUserClass1().equals("C1M")){
            System.out.println( "//CLASS 1 (CLS1): [DATA MISSING]" );
        } else {
            System.out.println( "//CLASS 1 (CLS1): " + player.getUserClass1() );
        }
        NoDelay();

        if (!player.getUserClass2().equals("C2M")){
            System.out.println( "//CLASS 2 (CLS2): " + player.getUserClass2() );
            NoDelay();
        }

        if (!player.getUserClass3().equals("C3M")){
            System.out.println( "//CLASS 3 (CLS3): " + player.getUserClass3() );
            NoDelay();
        }

        if (!player.getUserClass4().equals("C4M")){
            System.out.println( "//CLASS 4 (CLS4): " + player.getUserClass4() );
            NoDelay();
        }

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
        System.out.println();
        NoDelay();

        boolean claMissing = player.getUserClass1().equals("C1M");
        boolean strMissing = player.getUserStrength().equals("StM");
        boolean dexMissing = player.getUserDexterity().equals("DeM");
        boolean conMissing = player.getUserConstitution().equals("CoM");
        boolean intMissing = player.getUserIntelligence().equals("InM");
        boolean wisMissing = player.getUserWisdom().equals("WiM");
        boolean chaMissing = player.getUserCharisma().equals("CaM");

        if ( claMissing || strMissing || dexMissing || conMissing || intMissing || wisMissing || chaMissing ){
            MedDelay();
            System.out.println( "//WARNING: DATA MISSING ON ONE OR MORE USER ATTRIBUTES" );
            ShortDelay();
            System.out.println( "//PLEASE USE \"EDIT\" FUNCTION TO COMPLETE MISSING ENTRIES");
            MedDelay();
        }

        System.out.println( ">MEDICAL (1/MEDS)" );
        NoDelay();
        System.out.println( ">SKILLS (2/SKLS)" );
        NoDelay();
        System.out.println( ">EDIT (3/EDIT)" );
        NoDelay();
        System.out.println( ">REFRESH (N/NULD)" );
        NoDelay();
        System.out.println( ">BACK (0/BACK)" );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "MEDICAL": case "MEDS": case "medical": case "meds": case "1":
                    ShortDelay();
                    System.out.println("//ERROR: DRIVE SECTOR CORRUPTED.");
                    ShortDelay();
                    System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU");
                    ShortDelay();
                    System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
                    boolean medOptionNotChosen = true;
                    while ( medOptionNotChosen ) {
                        switch ( input.nextLine() ) {
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
                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
                    boolean editOptionNotChosen = true;
                    while ( editOptionNotChosen ) {
                        switch ( input.nextLine() ) {
                            case "STATUS": case "status": case "STAT": case "stat":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR STATUS (STAT)");
                                player.setUserStatus(input.nextLine());
                                System.out.println("//STATUS (STAT) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "DATA": case "data": case "EXP": case "exp": case "XP": case "xp":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR PERFORMANCE DATA (DATA)");
                                player.setUserXP(input.nextLine());
                                System.out.println("//PERFORMANCE DATA (DATA) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CLASS1": case "class1": case "CLS1": case "cls1":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR CLASS 1 (CLS1)");
                                player.setUserClass1(input.nextLine());
                                System.out.println("//CLASS 1 (CLS1) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CLASS2": case "class2": case "CLS2": case "cls2":
                                if (player.getUserClass1().equals("C1M")){
                                    System.out.println("//ERROR: PRIMARY CLASS MISSING!");
                                } else {
                                    System.out.println("//PLEASE ENTER NEW VALUE FOR CLASS 2 (CLS2)");
                                    player.setUserClass2(input.nextLine());
                                    System.out.println("//CLASS 2 (CLS2) VALUE UPDATED");
                                }
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CLASS3": case "class3": case "CLS3": case "cls3":
                                if (player.getUserClass1().equals("C1M")){
                                    System.out.println("//ERROR: PRIMARY CLASS MISSING!");
                                } else {
                                    System.out.println("//PLEASE ENTER NEW VALUE FOR CLASS 3 (CLS3)");
                                    player.setUserClass3(input.nextLine());
                                    System.out.println("//CLASS 3 (CLS3) VALUE UPDATED");
                                }
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CLASS4": case "class4": case "CLS4": case "cls4":
                                if (player.getUserClass1().equals("C1M")){
                                    System.out.println("//ERROR: PRIMARY CLASS MISSING!");
                                } else {
                                    System.out.println("//PLEASE ENTER NEW VALUE FOR CLASS 4 (CLS4)");
                                    player.setUserClass4(input.nextLine());
                                    System.out.println("//CLASS 4 (CLS4) VALUE UPDATED");
                                }
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "STRENGTH": case "strength": case "STRN": case "strn":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR STRENGTH (STRN)");
                                player.setUserStrength(input.nextLine());
                                System.out.println("//STRENGTH (STRN) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "DEXTERITY": case "dexterity": case "DEXT": case "dext":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR DEXTERITY (DEXT)");
                                player.setUserDexterity(input.nextLine());
                                System.out.println("//DEXTERITY (DEXT) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CONSTITUTION": case "constitution": case "CNST": case "cnst":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR CONSTITUTION (CNST)");
                                player.setUserConstitution(input.nextLine());
                                int newCurrentHealth = Integer.parseInt(player.getUserCurrentHealth()) + (modCalc(Integer.parseInt(player.getUserConstitution()))*10);
                                int newMaxHealth = Integer.parseInt(player.getUserMaxHealth()) + (modCalc(Integer.parseInt(player.getUserConstitution()))*10);
                                player.setUserCurrentHealth(Integer.toString(newCurrentHealth));
                                player.setUserMaxHealth(Integer.toString(newMaxHealth));
                                System.out.println("//CONSTITUTION (CNST) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "INTELLIGENCE": case "intelligence": case "INTL": case "intl":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR INTELLIGENCE (INTL)");
                                player.setUserIntelligence(input.nextLine());
                                System.out.println("//INTELLIGENCE (INTL) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "WISDOM": case "wisdom": case "WSDM": case "wsdm":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR WISDOM (WSDM)");
                                player.setUserWisdom(input.nextLine());
                                System.out.println("//WISDOM (WSDM) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CHARISMA": case "charisma": case "CHRM": case "chrm":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR CHARISMA (CHRM)");
                                player.setUserCharisma(input.nextLine());
                                System.out.println("//CHARISMA (CHRM) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//PRESS \"N\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr":
                                player.writePlayerData();
                                vitals( player );
                                break;
                            case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                                roll( player );
                                break;
                            case "#KVSEDBM": //enables debug mode
                                editOptionNotChosen = true;
                                debugMode = true;
                                System.out.println("//MAINTENANCE MODE ENABLED");
                                break;
                            case "#KVSDDBM": //disables debug mode
                                editOptionNotChosen = true;
                                debugMode = false;
                                System.out.println("//MAINTENANCE MODE DISABLED");
                                break;
                            case "#KVSECS": // disables cutscenes
                                editOptionNotChosen = true;
                                cutscenesEnabled = true;
                                System.out.println("//ACCELERATED READ/WRITE DISABLED");
                                break;
                            case "#KVSDCS": //enables cutscenes
                                editOptionNotChosen = true;
                                cutscenesEnabled = false;
                                System.out.println("//ACCELERATED READ/WRITE ENABLED");
                                break;
                            case "#KVSCLV": //changes language to Volkshavenish
                                editOptionNotChosen = true;
                                player.setUserLanguage("VOLKSHAVENISH");
                                System.out.println("//SPRACHE-PRÄFERENZ AKTUALISIERTE");
                                break;
                            case "#KVSCLE": //changes language to English
                                editOptionNotChosen = true;
                                player.setUserLanguage("ENGLISH");
                                System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                break;
                            default:
                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                    System.out.println("//ERROR: INVALID INPUT.");
                                }
                                break;
                        }
                    }
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr":
                    player.writePlayerData();
                    vitals( player );
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
                    break;
                case "AUSSCHALTEN": case "ausschalten": case "ASCH": case "asch": case "A": case "a": case "SHUTDOWN": case "shutdown": case "POWR": case "powr": case "P": case "p":
                    shutdown( player );
                    break;
                case "BACK": case "back": case "0":
                    mmain( player );
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
            System.out.println( "//STRENGTH (STRN) SKILLS" );
            ShortDelay();
            System.out.println( ">ATHLETICS (STR0) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserStrength())) + profiCalc( player,"ATHLETICS" ) + sftwrCalc( player, "ATHLETICS" ) ) + " )");
            ShortDelay();
            System.out.println();

            System.out.println( "//DEXTERITY (DEXT) SKILLS" );
            ShortDelay();
            System.out.println( ">ACROBATICS (DEX0) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserDexterity())) + profiCalc( player,"ACROBATICS" ) + sftwrCalc( player, "ACROBATICS" ) ) + " )");
            NoDelay();
            System.out.println( ">SLEIGHT-OF-HAND (DEX1) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserDexterity())) + profiCalc( player,"SLEIGHT-OF-HAND" ) + sftwrCalc( player, "SLEIGHT-OF-HAND" ) ) + " )");
            NoDelay();
            System.out.println( ">STEALTH (DEX2) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserDexterity())) + profiCalc( player,"STEALTH" ) ) + sftwrCalc( player, "STEALTH" ) + " )");
            ShortDelay();
            System.out.println();

            System.out.println( "//INTELLIGENCE (INTL) SKILLS" );
            ShortDelay();
            System.out.println( ">ARCANA (INT0) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserIntelligence())) + profiCalc( player,"ARCANA" ) ) + sftwrCalc( player, "ARCANA" )  +" )");
            NoDelay();
            System.out.println( ">HISTORY (INT1) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserIntelligence())) + profiCalc( player,"HISTORY" ) )  + sftwrCalc( player, "HISTORY" ) +" )");
            NoDelay();
            System.out.println( ">INVESTIGATION (INT2) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserIntelligence())) + profiCalc( player,"INVESTIGATION" ) ) + sftwrCalc( player, "INVESTIGATION" )  +" )");
            NoDelay();
            System.out.println( ">NATURE (INT3) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserIntelligence())) + profiCalc( player,"NATURE" ) ) + sftwrCalc( player, "NATURE" )  +" )");
            NoDelay();
            System.out.println( ">RELIGION (INT4) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserIntelligence())) + profiCalc( player,"RELIGION" ) ) + sftwrCalc( player, "RELIGION" )  +" )");
            ShortDelay();
            System.out.println();

            System.out.println( "//WISDOM (WSDM) SKILLS" );
            ShortDelay();
            System.out.println( ">ANIMAL HANDLING (WSD0) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserWisdom())) + profiCalc( player,"ANIMAL HANDLING" ) ) + sftwrCalc( player, "ANIMAL HANDLING" )  +" )");
            NoDelay();
            System.out.println( ">INSIGHT (WSD1) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserWisdom())) + profiCalc( player,"INSIGHT" ) ) + sftwrCalc( player, "INSIGHT" )  +" )");
            NoDelay();
            System.out.println( ">MEDICINE (WSD2) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserWisdom())) + profiCalc( player,"MEDICINE" ) ) + sftwrCalc( player, "MEDICINE" )  +" )");
            NoDelay();
            System.out.println( ">PERCEPTION (WSD3) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserWisdom())) + profiCalc( player,"PERCEPTION" ) ) + sftwrCalc( player, "PERCEPTION" )  +" )");
            NoDelay();
            System.out.println( ">SURVIVAL (WSD4) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserWisdom())) + profiCalc( player,"SURVIVAL" ) ) + sftwrCalc( player, "SURVIVAL" )  +" )");
            ShortDelay();
            System.out.println();

            System.out.println( "//CHARISMA (CHRM) SKILLS" );
            ShortDelay();
            System.out.println( ">DECEPTION (CHR0) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserCharisma())) + profiCalc( player,"DECEPTION" ) ) + sftwrCalc( player, "DECEPTION" )  +" )");
            NoDelay();
            System.out.println( ">INTIMIDATION (CHR1) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserCharisma())) + profiCalc( player,"INTIMIDATION" ) ) + sftwrCalc( player, "INTIMIDATION" )  +" )");
            NoDelay();
            System.out.println( ">PERFORMANCE (CHR2) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserCharisma())) + profiCalc( player,"PERFORMANCE" ) ) + sftwrCalc( player, "PERFORMANCE" )  +" )");
            NoDelay();
            System.out.println( ">PERSUASION (CHR3) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserCharisma())) + profiCalc( player,"PERSUASION" ) ) + sftwrCalc( player, "PERSUASION" )  +" )");
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
        System.out.println( ">REFRESH (N/NULD)" );
        NoDelay();
        System.out.println( ">BACK (0/BACK)");
        boolean skillOptionNotChosen = true;
        while ( skillOptionNotChosen ) {
            switch ( input.nextLine() ) {
                case "EDIT": case "edit": case "1":
                    System.out.println("// PLEASE SPECIFY VALUE TO EDIT");
                    boolean profiOptionNotChosen = true;
                    while ( profiOptionNotChosen ){
                        switch ( input.nextLine() ){
                            case "BACKGROUND1": case "background1": case "BGN1": case "bgn1":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR BACKGROUND PROFICIENCY 1 (BGN1)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserBackgroundProficiency1( input.nextLine() );
                                System.out.println( "//BACKGROUND PROFICIENCY 1 (BGN1) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//PRESS \"N\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "BACKGROUND2": case "background2": case "BGN2": case "bgn2":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR BACKGROUND PROFICIENCY 2 (BGN2)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserBackgroundProficiency2( input.nextLine() );
                                System.out.println( "//BACKGROUND PROFICIENCY 2 (BGN2) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//PRESS \"N\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "SKILL1": case "skill1": case "SKL1": case "skl1":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 1 (SKL1)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserSkillProficiency1( input.nextLine() );
                                System.out.println( "//SKILL PROFICIENCY 1 (SKL1) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//PRESS \"N\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "SKILL2": case "skill2": case "SKL2": case "skl2":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 2 (SKL2)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserSkillProficiency2( input.nextLine() );
                                System.out.println( "//SKILL PROFICIENCY 2 (SKL2) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//PRESS \"N\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "SKILL3": case "skill3": case "SKL3": case "skl3":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 3 (SKL3)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserSkillProficiency3( input.nextLine() );
                                System.out.println( "//SKILL PROFICIENCY 3 (SKL3) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//PRESS \"N\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "SKILL4": case "skill4": case "SKL4": case "skl4":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 4 (SKL4)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserSkillProficiency4( input.nextLine() );
                                System.out.println( "//SKILL PROFICIENCY 4 (SKL4) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//PRESS \"N\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;
                            case "WEAPON": case "weapon": case "WPNP": case "wpnp":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR WEAPON PROFICIENCY (WPNP)" );
                                ShortDelay();
                                System.out.println( "//WARNING: PROFICIENCIES MUST BE ENTERED IN ALL CAPS" );
                                player.setUserWeaponProficiency( input.nextLine() );
                                System.out.println( "//WEAPON PROFICIENCY (WPNP) UPDATED");
                                ShortDelay();
                                System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                                ShortDelay();
                                System.out.println( "//PRESS \"N\" TO REFRESH SKILLS MENU" );
                                ShortDelay();
                                break;

                            case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr":
                                skills( player );
                                break;
                            case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                                roll( player );
                                break;
                        }
                    }
                    break;
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr":
                    skills( player );
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
            }
        } else if ( 4 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 8){
            if ( player.getUserBackgroundProficiency1().equals(skill) || player.getUserBackgroundProficiency2().equals(skill) ){
                profiBonus = 3;
                return profiBonus;
            } else if ( player.getUserSkillProficiency1().equals(skill) || player.getUserSkillProficiency2().equals(skill) || player.getUserSkillProficiency3().equals(skill) || player.getUserSkillProficiency4().equals(skill) ){
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
            }
        } else if ( 12 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 16){
            if ( player.getUserBackgroundProficiency1().equals(skill) || player.getUserBackgroundProficiency2().equals(skill) ){
                profiBonus = 5;
                return profiBonus;
            } else if ( player.getUserSkillProficiency1().equals(skill) || player.getUserSkillProficiency2().equals(skill) || player.getUserSkillProficiency3().equals(skill) || player.getUserSkillProficiency4().equals(skill) ){
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
            }
        } else if ( Integer.parseInt(player.getUserLevel()) < 20){
            if ( player.getUserBackgroundProficiency1().equals(skill) || player.getUserBackgroundProficiency2().equals(skill) ){
                profiBonus = 6 + ( ( Integer.parseInt(player.getUserLevel()) - 20 ) / 4 );
                return profiBonus;
            } else if ( player.getUserSkillProficiency1().equals(skill) || player.getUserSkillProficiency2().equals(skill) || player.getUserSkillProficiency3().equals(skill) || player.getUserSkillProficiency4().equals(skill) ){
                profiBonus = 6 + ( ( Integer.parseInt(player.getUserLevel()) - 20 ) / 4 );
                return profiBonus;
            }
        }

        return profiBonus;
    }

    private static int sftwrCalc ( playerCharacter player, String skill ){
        int softwareBonus = 0;

        if ( Integer.parseInt(player.getUserLevel()) <= 4 ){
            if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ) {
                softwareBonus = 2;
                return softwareBonus;
            }
        } else if ( 4 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 8){
            if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ) {
                softwareBonus = 3;
                return softwareBonus;
            }
        } else if ( 8 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 12){
            if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ) {
                softwareBonus = 4;
                return softwareBonus;
            }
        } else if ( 12 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 16){
            if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ) {
                softwareBonus = 5;
                return softwareBonus;
            }
        } else if ( 16 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 20){
            if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ) {
                softwareBonus = 6;
                return softwareBonus;
            }
        } else if ( Integer.parseInt(player.getUserLevel()) < 20){
            if ( player.getSoftware1().equals(skill + "+") || player.getSoftware2().equals(skill + "+") || player.getSoftware3().equals(skill + "+") || player.getSoftware4().equals(skill + "+") || player.getSoftware5().equals(skill + "+") ) {
                softwareBonus = 6 + ( ( Integer.parseInt(player.getUserLevel()) - 20 ) / 4 );
                return softwareBonus;
            }
        }

        return softwareBonus;
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
            switch ( input.nextLine() ) {
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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

        //SIDEARMS
        {
            TESTWEAP = new File(inventory[1]+"\\TESTWEAP");
            TESTWEAPTAG = new File(inventory[1]+"\\TESTWEAP\\ItemID.TAG");

            FtA_45 = new File(inventory[1]+"\\FtA-45");
            FtA_45TAG = new File(inventory[1]+"\\FtA-45\\ItemID.TAG");

            LP61 = new File(inventory[1]+"\\LP-61");
            LP61TAG = new File(inventory[1]+"\\LP-61\\ItemID.TAG");

            MP60 = new File(inventory[1]+"\\MP-60");
            MP60TAG = new File(inventory[1]+"\\MP-60\\ItemID.TAG");

            P1885 = new File(inventory[1]+"\\P.1885");
            P1885TAG = new File(inventory[1]+"\\P.1885\\ItemID.TAG");

            P60 = new File(inventory[1]+"\\P-60");
            P60TAG = new File(inventory[1]+"\\P-60\\ItemID.TAG");
        }

        if (weaponsCheck()){
            ShortDelay();
            System.out.println("COMPLETE");
            ShortDelay();
            System.out.println("// ENTER A WEAPON ID TO SELECT A WEAPON");
            ShortDelay();

            //SIDEARMS
            {
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
                            System.out.println( ">No. 002 | FtA-45 [CW]" );
                        } else {
                            System.out.println( ">No. 002 | FtA-45" );
                        }
                    } else {
                        System.out.println(">No. 002 | FtA-45 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                    }
                }
                if (LP61.isDirectory()){
                    if (LP61TAG.isFile()){
                        if (player.getPrimaryWeapon().equals("003")){
                            System.out.println( ">No. 003 | LP-61 [CW]" );
                        } else {
                            System.out.println( ">No. 003 | LP-61" );
                        }
                    } else {
                        System.out.println(">No. 003 | LP-61 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                    }
                }
                if (MP60.isDirectory()){
                    if (MP60TAG.isFile()){
                        if (player.getPrimaryWeapon().equals("004")){
                            System.out.println( ">No. 004 | MP-60 [CW]" );
                        } else {
                            System.out.println( ">No. 004 | MP-60" );
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
                            System.out.println( ">No. 006 | P-60 [CW]" );
                        } else {
                            System.out.println( ">No. 006 | P-60" );
                        }
                    } else {
                        System.out.println(">No. 006 | P60 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                    }
                }
            }
        }
        else{
            LongDelay();
            System.out.println("ERROR");
            NoDelay();
            System.out.println("//NO ITEMS MATCHING FILTER FOUND IN INVENTORY!");
        }
        NoDelay();
        System.out.println(">REFRESH (N/NULD)");
        NoDelay();
        System.out.println(">BACK (0/BACK)");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
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
                case "FtA-45": case "fta-45": case "002":
                    if (FtA_45.isDirectory()){
                        if (FtA_45TAG.isFile()){
                            weaponLoad( player, "FtA-45" );
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
                case "LP-61": case "lp-61": case "003":
                    if (LP61.isDirectory()){
                        if (LP61TAG.isFile()){
                            weaponLoad( player, "LP-61" );
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
                case "MP-60": case "mp-60": case "004":
                    if (MP60.isDirectory()){
                        if (MP60TAG.isFile()){
                            weaponLoad( player, "MP-60" );
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
                case "P-60": case "p-60": case "006":
                    if (P60.isDirectory()){
                        if (P60TAG.isFile()){
                            weaponLoad( player, "P-60");
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
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr":
                    weapons( player );
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }

    }

    private static boolean weaponsCheck(){

        boolean[] sidearmCheck = new boolean[6];
        {
            sidearmCheck[0] = TESTWEAP.isDirectory();
            sidearmCheck[1] = FtA_45.isDirectory();
            sidearmCheck[2] = LP61.isDirectory();
            sidearmCheck[3] = MP60.isDirectory();
            sidearmCheck[4] = P1885.isDirectory();
            sidearmCheck[5] = P60.isDirectory();
        }


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
        String [] weaponData = new String[30];
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
        NoDelay();
        if (debugMode) {
            System.out.print( "[INDEX 1] " + weaponData[1] + " | " );
        } else {
            System.out.print( weaponData[1] + " | " );
        }
        NoDelay();
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
            System.out.println( "[INDEX 4]  MANUFACTURER: "+weaponData[4] );
        } else {
            System.out.println( "  MANUFACTURER: "+weaponData[4] );
        }
        NoDelay();
        if (debugMode) {
            System.out.println( "[INDEX 5]  SCPA: " + weaponData[5] );
        } else {
            System.out.println( "  SCPA: " + weaponData[5] );
        }
        NoDelay();
        if (debugMode){
            System.out.println( "[INDEX 6]  GNKS: " + weaponData[6] );
        } else {
            System.out.println( "  GNKS: " + weaponData[6] );
        }
        NoDelay();
        if (debugMode){
            System.out.println( "[INDEX 7]  KTFK: " + weaponData[7] );
        } else {
            System.out.println( "  KTFK: " + weaponData[7] );
        }
        NoDelay();
        if (debugMode){
            System.out.println( "[INDEX 8]  KTFS: " + weaponData[8] );
        } else {
            System.out.println( "  KTFS: " + weaponData[8] );
        }
        NoDelay();
        if (debugMode){
            System.out.println( "[INDEX 9]  KTFM: " + weaponData[9] );
        } else {
            System.out.println( "  KTFM: " + weaponData[9] );
        }
        NoDelay();
        if (debugMode){
            System.out.println( "[INDEX 10]  WTYP: " + weaponData[10] );
        } else {
            System.out.println( "  WTYP: " + weaponData[10] );
        }
        if (weaponData[10].equals("RANGED")) {
            if (debugMode){
                System.out.println( "[INDEX 11]  EFKR: " + weaponData[11] );
            } else {
                System.out.println( "  EFKR: " + weaponData[11] );
            }
            NoDelay();
            if (debugMode){
                System.out.println( "[INDEX 12]  MNSK: " + weaponData[12] );
            } else {
                System.out.println( "  MNSK: " + weaponData[12] );
            }
            NoDelay();
            if (debugMode){
                System.out.println("[INDEX 13]  MGZT: " + weaponData[13]);
            } else {
                System.out.println("  MGZT: " + weaponData[13]);
            }
            NoDelay();
            if (debugMode){
                System.out.println( "[INDEX 14]  MGZL: " + weaponData[14] + "/[INDEX 15] " +weaponData[15] + "[INDEX 16] " + weaponData[16] + "[INDEX 17] " + weaponData[17] );
            } else {
                System.out.println( "  MGZL: " + weaponData[14] + "/" +weaponData[15] + " " + weaponData[16] + " " + weaponData[17] );
            }
            NoDelay();
            if (debugMode){
                System.out.println( "[INDEX 18]  MNSV: " + weaponData[18] );
            } else {
                System.out.println( "  MNSV: " + weaponData[18] );
            }
            NoDelay();
            if (debugMode){
                System.out.println( "[INDEX 19]  FRMM: " + weaponData[19] );
            } else {
                System.out.println( "  FRMM: " + weaponData[19] );
            }
            NoDelay();
            if (debugMode){
                System.out.println( "[INDEX 20]  FRMA:" + weaponData[20] );
            } else {
                System.out.println( "  FRMA: " + weaponData[20] );
            }
            NoDelay();
            if (debugMode){
                System.out.println( "[INDEX 21]  DKAM:" + weaponData[21] );
            } else {
                System.out.println( "  CHAM: " + weaponData[21] );
            }
        }
        System.out.println();
        ShortDelay();
        if (debugMode){
            System.out.print( "[INDEX 22]  INFA: " );
            ShortDelay();
            System.out.println( weaponData[22] );
        } else {
            System.out.print( "  INFA: " );
            ShortDelay();
            System.out.println( weaponData[22] );
        }
        System.out.println();
        ShortDelay();
        if (debugMode) {
            System.out.println( "[INDEX 23]  INFG: " );
            ShortDelay();
            String weaponINFG = weaponData[23].replaceAll("\\\\n", "\n");
            System.out.println( weaponINFG );
        } else {
            System.out.println( "  INFG: " );
            ShortDelay();
            String weaponINFG = weaponData[23].replaceAll("\\\\n", "\n");
            weaponINFG = weaponINFG.replaceAll("\\\\t", "\t");
            System.out.println( weaponINFG );
        }
        System.out.println();
        ShortDelay();
        if (!weaponData[24].equals("N/A")){
            if (debugMode){
                System.out.println( "//WEAPON-SPECIFIC TRAITS:" );
                ShortDelay();
                System.out.println( "> [INDEX 24] " + weaponData[24] + " - " + "[INDEX 25] " + weaponData[25] );
                ShortDelay();
            } else {
                System.out.println( "//WEAPON-SPECIFIC TRAITS:" );
                ShortDelay();
                System.out.println( ">" + weaponData[24] + " - " + weaponData[25] );
                ShortDelay();
            }
            if (!weaponData[26].equals("N/A")){
                if (debugMode){
                    System.out.println( "> [INDEX 26] " + weaponData[26] + " - " + "[INDEX 27] " + weaponData[27] );
                    ShortDelay();
                } else {
                    System.out.println( ">" + weaponData[26] + " - " + weaponData[27] );
                    ShortDelay();
                }
            if (!weaponData[28].equals("N/A")){
                if (debugMode){
                    System.out.println( "> [INDEX 28] " + weaponData[28] + " - " + "[INDEX 29] " + weaponData[29] );
                } else {
                    System.out.println( ">" + weaponData[28] + " - " + weaponData[29] );
                    }
                }
            }
        }
        System.out.println();
        ShortDelay();
        System.out.println("//PLEASE CHOOSE A COURSE OF ACTION");
        System.out.println(">EQUIP (1/EQUP)");
        NoDelay();
        System.out.println(">UNEQUIP (2/UEQP)");
        NoDelay();
        System.out.println( ">REFRESH (N/NULD)" );
        NoDelay();
        System.out.println( ">BACK (0/BACK)" );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr":
                    weaponLoad( player, weaponName );
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
                    break;
                case "EQUIP": case "equip": case "EQUP": case "equp": case "1":
                    if (player.getPrimaryWeapon().equals(weaponData[3])){
                        System.out.println("//ERROR: WEAPON ALREADY EQUIPPED!");
                        optionNotChosen = true;
                        break;
                    } else {
                        System.out.println("//ARE YOU SURE YOU WANT TO EQUIP THIS WEAPON? J/N");
                        boolean equipOptionNotChosen = true;
                        while ( equipOptionNotChosen ) {
                            switch ( input.nextLine() ) {
                                case "J": case "j": case "1":
                                    player.setPrimaryWeapon( weaponData[3] );
                                    System.out.println("//CURRENT WEAPON CHANGED");
                                    equipOptionNotChosen = false;
                                    break;
                                case "N": case "n": case "0":
                                    System.out.println( "//WEAPON CHANGE CANCELLED" );
                                    equipOptionNotChosen = false;
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
                                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
                        System.out.println("//ARE YOU SURE YOU WANT TO UNEQUIP THIS WEAPON? J/N");
                        boolean equipOptionNotChosen = true;
                        while ( equipOptionNotChosen ) {
                            switch ( input.nextLine() ) {
                                case "J": case "j": case "1":
                                    player.setPrimaryWeapon( "001" );
                                    System.out.println("//CURRENT WEAPON CHANGED");
                                    break;
                                case "N": case "n": case "0":
                                    System.out.println( "//WEAPON CHANGE CANCELLED" );
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
                                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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

        File[] inventory = player.getPlayerInventory();

        //SIDEARMS
        {
            TESTWEAPAMMO = new File(inventory[2]+"\\TESTWEAP");
            TESTWEAPAMMOTAG = new File(inventory[2]+"\\TESTWEAP\\ItemID.TAG");

            FtA_45AMMO = new File(inventory[2]+"\\FtA-45");
            FtA_45AMMOTAG = new File(inventory[2]+"\\FtA-45\\ItemID.TAG");

            LP61AMMO = new File(inventory[2]+"\\LP-61");
            LP61AMMOTAG = new File(inventory[2]+"\\LP-61\\ItemID.TAG");

            MP60AMMO = new File(inventory[2]+"\\MP-60");
            MP60AMMOTAG = new File(inventory[2]+"\\MP-60\\ItemID.TAG");

            P1885AMMO = new File(inventory[2]+"\\P.1885");
            P1885AMMOTAG = new File(inventory[2]+"\\P.1885\\ItemID.TAG");

            P60AMMO = new File(inventory[2]+"\\P-60");
            P60AMMOTAG = new File(inventory[2]+"\\P-60\\ItemID.TAG");
        }

        if (ammoCheck()){
            ShortDelay();
            System.out.println("COMPLETE");
            ShortDelay();


        }
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT.");
                    }
                    break;
            }
        }
    }

    private static boolean ammoCheck(){
        boolean[] sidearmAmmoCheck = new boolean[6];
        {
            sidearmAmmoCheck[0] = TESTWEAPAMMO.isDirectory();
            sidearmAmmoCheck[1] = FtA_45AMMO.isDirectory();
            sidearmAmmoCheck[2] = LP61AMMO.isDirectory();
            sidearmAmmoCheck[3] = MP60.isDirectory();
            sidearmAmmoCheck[4] = P1885AMMO.isDirectory();
            sidearmAmmoCheck[5] = P60AMMO.isDirectory();
        }

        for (int i = 0; i < sidearmAmmoCheck.length; i++){
            if(sidearmAmmoCheck[i]){
                return true;
            }
        }

        return false;
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
            switch ( input.nextLine() ) {
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
        System.out.print( player.getUserName() );
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
            switch ( input.nextLine() ) {
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
            switch ( input.nextLine() ) {
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
        System.out.print( player.getUserName() );
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
            switch ( input.nextLine() ) {
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
            switch ( input.nextLine() ) {
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
        System.out.println("//INITIALIZING TARGETING ASSISTANCE SYSTEM...");
        LongDelay();
        boolean claMissing = player.getUserClass1().equals("C1M");
        boolean strMissing = player.getUserStrength().equals("StM");
        boolean dexMissing = player.getUserDexterity().equals("DeM");
        boolean conMissing = player.getUserConstitution().equals("CoM");
        boolean intMissing = player.getUserIntelligence().equals("InM");
        boolean wisMissing = player.getUserWisdom().equals("WiM");
        boolean chaMissing = player.getUserCharisma().equals("CaM");
        if ( claMissing || strMissing || dexMissing || conMissing || intMissing || wisMissing || chaMissing ){
            System.out.println( "//ERROR: INSUFFICIENT DATA ON CURRENT USER" );
            ShortDelay();
            System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU" );
            ShortDelay();
            System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN" );
            boolean optionNotChosen = true;
            while ( optionNotChosen ) {
                switch ( input.nextLine() ) {
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
                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                        } else if (player.getUserLanguage().equals("ENGLISH")){
                            System.out.println("//ERROR: INVALID INPUT.");
                        }
                        break;
                }
            }
        }
        System.out.println("//CURRENT HP: " + player.getUserCurrentHealth() + "/" + player.getUserMaxHealth());
        NoDelay();
        System.out.println("//CURRENT STATUS: " + player.getUserStatus());
        System.out.println();
        MedDelay();
        String currentWeapon = "";
        switch (player.getPrimaryWeapon()){
            case "000":
                currentWeapon = "TESTWEAP";
                break;
            case "001":
                currentWeapon = "FISTS";
                break;
            case "002":
                currentWeapon = "FtA-45";
                break;
            case "003":
                currentWeapon = "LP-61";
                break;
            case "004":
                currentWeapon = "MP60";
                break;
            case "005":
                currentWeapon = "P.1885";
                break;
            case "006":
                currentWeapon = "P60";
        }
        File[] inventory = player.getPlayerInventory();
        String [] currentWeaponData = new String[30];
        if (player.getPrimaryWeapon().equals("001")){
            currentWeaponData[0] = "FISTS";
            currentWeaponData[1] = "ENVIRONMENTAL MANIPULATOR/TRADITIONAL COMBAT IMPLEMENT";
            currentWeaponData[2] = Integer.toString(modCalc(Integer.parseInt(player.getUserStrength())));
            currentWeaponData[3] = "001";
            currentWeaponData[4] = "N/A";
            currentWeaponData[5] = player.getUserStrength();
            if ((20-Integer.parseInt(player.getUserDexterity())) < 0){
                currentWeaponData [6] = "1";
            } else {
                currentWeaponData [6] = Integer.toString(20-Integer.parseInt(player.getUserDexterity()));
            }
            if (Integer.parseInt(currentWeaponData[2]) >= 2){
                currentWeaponData[7] = "J";
            } else {
                currentWeaponData[7] = "N";
            }
            if ((20-Integer.parseInt(player.getUserStrength())) < 0){
                currentWeaponData [8] = "1";
            } else {
                currentWeaponData [8] = Integer.toString(20-Integer.parseInt(player.getUserStrength()));
            }
            currentWeaponData[9] = Integer.toString(modCalc(Integer.parseInt(player.getUserDexterity())));
            currentWeaponData[10] = "MELEE";
            currentWeaponData[11] = "PB";
            currentWeaponData[12] = "N/A";
            currentWeaponData[13] = "N/A";
            currentWeaponData[14] = "N/A";
            currentWeaponData[15] = "N/A";
            currentWeaponData[16] = "N/A";
            currentWeaponData[17] = "N/A";
            currentWeaponData[18] = "N/A";
            currentWeaponData[19] = "N/A";
            currentWeaponData[20] = "N/A";
            currentWeaponData[21] = "N/A";
            currentWeaponData[22] = "Your fists. For when you need a hands-on approach.";
            currentWeaponData[23] = "Hand-to-hand combat is an art that is as ancient as life itself. Perhaps, with enough practice, you'll be able to master it in your own right.";
            currentWeaponData[24] = "SILENT";
            currentWeaponData[25] = "This weapon is almost completely noiseless and can be used at any range with minimal risk of detection.";
            currentWeaponData[26] = "N/A";
            currentWeaponData[27] = "N/A";
            currentWeaponData[28] = "N/A";
            currentWeaponData[29] = "N/A";
        } else {
            try {
                BufferedReader weaponScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+currentWeapon+"\\ItemID.TAG"));
                try {
                    currentWeaponData = weaponScanner.readLine().split("[~]");
                    weaponScanner.close();
                } catch ( IOException tagError ) {
                    tagError.printStackTrace();
                }
            } catch (FileNotFoundException noWeapon){
                System.out.println("//ERROR: EQUIPPED WEAPON MISSING FROM INVENTORY");
                ShortDelay();
                System.out.println("//SWITCHING TO UNARMED COMBAT");
                player.setPrimaryWeapon("001");
                currentWeaponData[0] = "FISTS";
                currentWeaponData[1] = "ENVIRONMENTAL MANIPULATOR/TRADITIONAL COMBAT IMPLEMENT";
                currentWeaponData[2] = Integer.toString(modCalc(Integer.parseInt(player.getUserStrength())));
                currentWeaponData[3] = "001";
                currentWeaponData[4] = "N/A";
                currentWeaponData[5] = player.getUserStrength();
                if ((20-Integer.parseInt(player.getUserDexterity())) < 0){
                    currentWeaponData [6] = "1";
                } else {
                    currentWeaponData [6] = Integer.toString(20-Integer.parseInt(player.getUserDexterity()));
                }
                if (Integer.parseInt(currentWeaponData[2]) >= 2){
                    currentWeaponData[7] = "J";
                } else {
                    currentWeaponData[7] = "N";
                }
                if ((20-Integer.parseInt(player.getUserStrength())) < 0){
                    currentWeaponData [8] = "1";
                } else {
                    currentWeaponData [8] = Integer.toString(20-Integer.parseInt(player.getUserStrength()));
                }
                currentWeaponData[9] = Integer.toString(modCalc(Integer.parseInt(player.getUserDexterity())));
                currentWeaponData[10] = "MELEE";
                currentWeaponData[11] = "PB";
                currentWeaponData[12] = "N/A";
                currentWeaponData[13] = "N/A";
                currentWeaponData[14] = "N/A";
                currentWeaponData[15] = "N/A";
                currentWeaponData[16] = "N/A";
                currentWeaponData[17] = "N/A";
                currentWeaponData[18] = "N/A";
                currentWeaponData[19] = "N/A";
                currentWeaponData[20] = "N/A";
                currentWeaponData[21] = "N/A";
                currentWeaponData[22] = "Your fists. For when you need a hands-on approach.";
                currentWeaponData[23] = "Hand-to-hand combat is an art that is as ancient as life itself. Perhaps, with enough practice, you'll be able to master it in your own right.";
                currentWeaponData[24] = "SILENT";
                currentWeaponData[25] = "This weapon is almost completely noiseless and can be used at any range with minimal risk of detection.";
                currentWeaponData[26] = "N/A";
                currentWeaponData[27] = "N/A";
                currentWeaponData[28] = "N/A";
                currentWeaponData[29] = "N/A";
            }

        }
        ShortDelay();
        System.out.println("//CURRENT WEAPON: " + currentWeaponData[0] + " | FR " + currentWeaponData[2] );
        NoDelay();
        System.out.println("//MAXIMUM POSSIBLE DAMAGE: "+ currentWeaponData[5]);
        NoDelay();
        System.out.println("//ACCURACY THRESHOLD: " +currentWeaponData[6]);
        NoDelay();
        if (currentWeaponData[10].equals("RANGED") || currentWeaponData[10].equals("HYBRID")) {
            System.out.println("//CURRENT MAGAZINE TYPE: "+currentWeaponData[13]);
            if (currentWeaponData[12].equals("Electricity")){
                System.out.println("//MAGAZINE : " + Double.parseDouble(currentWeaponData[14]) + " / " + Double.parseDouble(currentWeaponData[15]) + " " + currentWeaponData[17]);
            } else {
                System.out.println("//MAGAZINE : " + (int)Double.parseDouble(currentWeaponData[14]) + " / " + currentWeaponData[15] + " " + currentWeaponData[17]);
            }
            }
        System.out.println();
        MedDelay();
        System.out.println("//PLEASE CHOOSE COURSE OF ACTION");
        ShortDelay();
        System.out.println(">ATTACK (1/ANGF)");
        NoDelay();
        System.out.println(">ACTION (2/AKTN)");
        NoDelay();
        System.out.println(">WEAPONS (3/WFFN)");
        NoDelay();
        System.out.println(">ITEMS (4/ATKL)");
        NoDelay();
        System.out.println(">STATUS (5/STAT)");
        NoDelay();
        System.out.println(">BACK (0/BACK)");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "ANGREIFEN": case "angreifen": case "ANGF": case "angf": case "ATTACK": case "attack": case "1":
                    if (currentWeaponData[10].equals("RANGED") && currentWeaponData[14].equals("0")){
                        System.out.println("//ATTACK FAILED - MAGAZINE IS EMPTY");
                    } else if (currentWeaponData[20].equals("Bolt-Action") && currentWeaponData[21].equals("N")){
                        System.out.println("//ATTACK FAILED - WEAPON NOT CHAMBERED");
                    } else {
                        System.out.println("//PLEASE INPUT ATTACK ROLL RESULT");
                        ShortDelay();
                        System.out.println("//ENTER \'0\' TO CANCEL ATTACK");
                        boolean attackCancelled = false;
                        double hitRoll = 0;
                        try {
                            hitRoll = input.nextInt();
                        } catch ( Exception e ){
                            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                            } else if (player.getUserLanguage().equals("ENGLISH")){
                                System.out.println("//ERROR: INVALID INPUT.");
                            }
                            input = new Scanner(System.in);
                            while (!input.hasNextInt()){
                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                    System.out.println("//ERROR: INVALID INPUT.");
                                }
                                input = new Scanner(System.in);
                            }
                            hitRoll = input.nextInt();
                        }
                        if (hitRoll == 0){
                            System.out.println("//ATTACK CANCELLED");
                            attackCancelled = true;
                            break;
                        }
                        input = new Scanner(System.in);
                        System.out.println("//PLEASE ENTER ESTIMATE OF DISTANCE TO TARGET");
                        boolean rangeNotDefined = true;
                        while (rangeNotDefined){
                            switch( currentWeaponData[11] ){
                                case "LANGE": case "lange":
                                case "LONG": case "long": case "4":
                                    switch ( input.nextLine() ){
                                        case "LONG":
                                            hitRoll += 0;
                                            break;
                                        case "MEDIUM":
                                            hitRoll += 1;
                                            break;
                                        case "SHORT":
                                            hitRoll += 2;
                                            break;
                                        case "PB":
                                            hitRoll += 3;
                                            break;
                                        default:
                                            hitRoll += 0;
                                            break;
                                    }
                                    rangeNotDefined= false;
                                    break;
                                case "MITTEL": case "mittel":
                                case "MEDIUM": case "medium": case "3":
                                    switch ( input.nextLine() ){
                                        case "LONG":
                                            if ((hitRoll-1)>=0){
                                                hitRoll -= 1;
                                            } else {
                                                hitRoll = 0;
                                            }
                                            break;
                                        case "MEDIUM":
                                            hitRoll += 0;
                                            break;
                                        case "SHORT":
                                            hitRoll += 1;
                                            break;
                                        case "PB":
                                            hitRoll += 2;
                                            break;
                                        default:
                                            hitRoll += 0;
                                            break;
                                    }
                                    rangeNotDefined= false;
                                    break;
                                case "KURZ": case "kurz":
                                case "SHORT": case "short": case "2":
                                    switch ( input.nextLine() ){
                                        case "LONG":
                                            if ((hitRoll-2)>=0){
                                                hitRoll -= 2;
                                            } else {
                                                hitRoll = 0;
                                            }
                                            break;
                                        case "MEDIUM":
                                            if ((hitRoll-1)>=0){
                                                hitRoll -= 0;
                                            } else {
                                                hitRoll = 0;
                                            }
                                            break;
                                        case "SHORT":
                                            hitRoll += 0;
                                            break;
                                        case "PB":
                                            hitRoll += 1;
                                            break;
                                        default:
                                            hitRoll += 0;
                                            break;
                                    }
                                    rangeNotDefined= false;
                                    break;
                                case "KÜRZESTER ENTFERNUNG": case "KUERZESTER ENTFERNUNG": case "KURZESTER ENTFERNUNG": case "kürzester entfernung": case "kuerzester entfernung": case "kurzester entfernung": case "KE": case "ke":
                                case "POINT BLANK": case "point blank": case "PB": case "pb": case "1":
                                    if (player.getPrimaryWeapon().equals("001")){
                                        switch ( input.nextLine() ){
                                            case "LONG": case "MEDIUM": case "SHORT":
                                                hitRoll = 0;
                                                break;
                                            case "PB":
                                                hitRoll += 0;
                                                break;
                                            default:
                                                hitRoll += 0;
                                                break;
                                        }
                                    } else {
                                        switch ( input.nextLine() ){
                                            case "LONG":
                                                if ((hitRoll-3)>=0){
                                                    hitRoll -= 3;
                                                } else {
                                                    hitRoll = 0;
                                                }
                                                break;
                                            case "MEDIUM":
                                                if ((hitRoll-2)>=0){
                                                    hitRoll -= 2;
                                                } else {
                                                    hitRoll = 0;
                                                }
                                                break;
                                            case "SHORT":
                                                if ((hitRoll-1)>=0){
                                                    hitRoll -= 1;
                                                } else {
                                                    hitRoll = 0;
                                                }
                                                break;
                                            case "PB":
                                                hitRoll += 0;
                                                break;
                                            default:
                                                hitRoll += 0;
                                                break;
                                        }
                                    }
                                    rangeNotDefined= false;
                                    break;
                                default:
                                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                                    } else if (player.getUserLanguage().equals("ENGLISH")){
                                        System.out.println("//ERROR: INVALID INPUT.");
                                    }
                                    break;
                            }
                        }
                        double baseDamage = 0;
                        double hitAccuracy;
                        if (hitRoll >= Integer.parseInt(currentWeaponData[6])){
                            hitAccuracy = 1;
                        } else {
                            hitAccuracy = (hitRoll/Double.parseDouble(currentWeaponData[6]));
                        }
                        if (currentWeaponData[10].equals("RANGED")){
                            if ((int)Double.parseDouble(currentWeaponData[14]) < Integer.parseInt(currentWeaponData[18])){
                                baseDamage = (Integer.parseInt(currentWeaponData[5])*Integer.parseInt(currentWeaponData[14])) * hitAccuracy;
                            } else {
                                baseDamage = (Integer.parseInt(currentWeaponData[5])*Integer.parseInt(currentWeaponData[18])) * hitAccuracy;
                            }
                        } else {
                            baseDamage = Integer.parseInt(currentWeaponData[5])*(hitAccuracy);
                        }
                        double totalDamage = 0;
                        if (currentWeaponData[7].equals("J")){
                            System.out.println("//PLEASE INPUT CRIT ROLL RESULT");
                            int critRoll = 0;
                            try {
                                critRoll = input.nextInt();
                            } catch ( Exception e){
                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                    System.out.println("//ERROR: INVALID INPUT.");
                                }
                                input = new Scanner(System.in);
                                while (!input.hasNextInt()){
                                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                                    } else if (player.getUserLanguage().equals("ENGLISH")){
                                        System.out.println("//ERROR: INVALID INPUT.");
                                    }
                                    input = new Scanner(System.in);
                                }
                                critRoll = input.nextInt();
                            }
                            if (critRoll >= Integer.parseInt(currentWeaponData[8])){
                                totalDamage = baseDamage*Double.parseDouble(currentWeaponData[9]);
                            } else {
                                totalDamage = baseDamage;
                            }
                        } else {
                            totalDamage = baseDamage;
                        }
                        System.out.print("//CALCULATING DAMAGE ESTIMATE...");
                        MedDelay();
                        System.out.println("COMPLETE");
                        ShortDelay();
                        System.out.println("//TOTAL DAMAGE DONE TO TARGET THIS ATTACK: " + Math.ceil(totalDamage) + "( " + totalDamage + " )" );
                        input = new Scanner(System.in);
                        if (currentWeaponData[10].equals("RANGED")){
                            int currentMag = (int)Double.parseDouble(currentWeaponData[14]);
                            int ammoUsedPerShot = Integer.parseInt(currentWeaponData[18]);
                            if (currentMag < ammoUsedPerShot){
                                currentMag = 0;
                            } else {
                                currentMag -= ammoUsedPerShot;
                            }
                            currentWeaponData[14] = Integer.toString(currentMag);
                            if (((currentWeaponData[10].equals("RANGED") || currentWeaponData[10].equals("HYBRID")) && (currentWeaponData[20].equals("Bolt-Action") || currentWeaponData[20].equals("Pump-Action"))) || currentWeaponData[14].equals(0)){
                                currentWeaponData[21] = "N";
                            }
                            writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+currentWeapon+"\\ItemID.TAG"));
                            for (int i = 0; i < 30; i++ ){
                                writer.write (currentWeaponData[i] + "~");
                            }
                            writer.flush();
                            writer.close();
                        }
                    }
                    break;
                case "AKTION": case "aktion": case "AKTN": case "aktn": case "ACTION": case "action": case "2":
                    System.out.println("//PLEASE CHOOSE ACTION TO PERFORM");
                    ShortDelay();
                    System.out.println(">RELOAD (1/NCLD)");
                    NoDelay();
                    System.out.println(">CHAMBER (2/KMMR)");
                    NoDelay();
                    System.out.println(">BACK (0/BACK)");
                    boolean invalidAction = true;
                    while (invalidAction) {
                        switch (input.nextLine()){
                            case "NACHLADEN": case "nachladen": case "NCLD": case "ncld": case "RELOAD": case "reload": case "1":
                                if (currentWeaponData[10].equals("RANGED") || currentWeaponData[10].equals("HYBRID")) {
                                    System.out.println("//CURRENT MAGAZINE TYPE: " + currentWeaponData[13]);
                                    ShortDelay();
                                    System.out.println("//MAGAZINE : " + currentWeaponData[14] + " / " + currentWeaponData[15] + " " + currentWeaponData[17]);
                                    ShortDelay();
                                    System.out.println("//PLEASE ENTER NEW AMMUNITION VALUE");
                                    boolean invalidAmmo = true;
                                    while (invalidAmmo) {
                                        try {
                                            if (currentWeaponData[12].equals("Electricity")){
                                                Double newAmmo = Double.parseDouble(input.nextLine());
                                                if (newAmmo > Double.parseDouble(currentWeaponData[15])) {
                                                    System.out.println("//ERROR: BATTERY CAPACITY EXCEEDED");
                                                } else {
                                                    currentWeaponData[14] = (Double.toString(newAmmo));
                                                    writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+currentWeapon+"\\ItemID.TAG"));
                                                    for (int i = 0; i < 30; i++ ){
                                                        writer.write (currentWeaponData[i] + "~");
                                                    }
                                                    writer.flush();
                                                    writer.close();
                                                    System.out.println("//CURRENT AMMO UPDATED");
                                                    invalidAmmo = false;
                                                    break;
                                                }
                                            } else {
                                                int newAmmo = Integer.parseInt(input.nextLine());
                                                if (newAmmo > Integer.parseInt(currentWeaponData[15])) {
                                                    System.out.println("//ERROR: MAGAZINE CAPACITY EXCEEDED");
                                                } else {
                                                    currentWeaponData[14] = (Double.toString(newAmmo));
                                                    writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+currentWeapon+"\\ItemID.TAG"));
                                                    for (int i = 0; i < 30; i++ ){
                                                        writer.write (currentWeaponData[i] + "~");
                                                    }
                                                    writer.flush();
                                                    writer.close();
                                                    System.out.println("//CURRENT AMMO UPDATED");
                                                    invalidAmmo = false;
                                                    break;
                                                }
                                            }
                                        } catch (NumberFormatException exception) {
                                            System.out.println("//ERROR: INVALID INPUT FORMAT.");
                                        }
                                    }
                                } else {
                                    System.out.println("//ERROR: CURRENT WEAPON DOES NOT USE AMMUNITION");
                                    invalidAction = false;
                                    break;
                                }
                                break;
                            case "KAMMER": case "kammer": case "KMMR": case "kmmr": case "CHAMBER": case "chamber": case "2":
                                if (currentWeaponData[10].equals("RANGED") || currentWeaponData[10].equals("HYBRID")){
                                    if (currentWeaponData[21].equals("N")){
                                        if ((int)Double.parseDouble(currentWeaponData[14]) > 0){
                                            currentWeaponData[21] = "J";
                                            writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+currentWeapon+"\\ItemID.TAG"));
                                            for (int i = 0; i < 30; i++ ){
                                                writer.write (currentWeaponData[i] + "~");
                                            }
                                            writer.flush();
                                            writer.close();
                                            System.out.println("//WEAPON CHAMBERED");
                                            invalidAction = false;
                                            break;
                                        } else {
                                            System.out.println("//ERROR: MAGAZINE IS EMPTY");
                                            invalidAction = false;
                                            break;
                                        }
                                    } else {
                                        System.out.println("//ERROR: WEAPON ALREADY CHAMBERED");
                                        invalidAction = false;
                                        break;
                                    }
                                } else {
                                    System.out.println("//ERROR: CURRENT WEAPON CANNOT BE CHAMBERED");
                                    invalidAction = false;
                                    break;
                                }
                            case "BACK": case "back": case "0":
                                System.out.println("//ACTION SUBMENU EXITED");
                                invalidAction = false;
                                break;
                            default:
                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                    System.out.println("//ERROR: INVALID INPUT.");
                                }
                                break;
                        }
                    }
                    break;
                case "WAFFEN": case "waffen": case "WFFN": case "wffn": case "WEAPONS": case "weapons": case "3":
                    {
                    TESTWEAP = new File(inventory[1]+"\\TESTWEAP");
                    TESTWEAPTAG = new File(inventory[1]+"\\TESTWEAP\\ItemID.TAG");

                    FtA_45 = new File(inventory[1]+"\\FtA-45");
                    FtA_45TAG = new File(inventory[1]+"\\FtA-45\\ItemID.TAG");

                    LP61 = new File(inventory[1]+"\\LP-61");
                    LP61TAG = new File(inventory[1]+"\\LP-61\\ItemID.TAG");

                    MP60 = new File(inventory[1]+"\\MP-60");
                    MP60TAG = new File(inventory[1]+"\\MP-60\\ItemID.TAG");

                    P1885 = new File(inventory[1]+"\\P.1885");
                    P1885TAG = new File(inventory[1]+"\\P.1885\\ItemID.TAG");

                    P60 = new File(inventory[1]+"\\P-60");
                    P60TAG = new File(inventory[1]+"\\P-60\\ItemID.TAG");
                    }
                System.out.print("//ACCESSING LOCAL ARSENAL...");
                if (weaponsCheck()){
                    ShortDelay();
                    System.out.println("COMPLETE");
                    ShortDelay();
                    System.out.println("//SIDEARMS:");
                    ShortDelay();
                    if (TESTWEAP.isDirectory()){
                        if (TESTWEAPTAG.isFile()){
                            if (player.getPrimaryWeapon().equals("000")){
                                System.out.println( ">No. 000 | TESTWEAP [CW]" );
                                NoDelay();
                            } else {
                                System.out.println( ">No. 000 | TESTWEAP" );
                                NoDelay();
                            }
                        } else {
                            System.out.println(">No. 000 | TESTWEAP [ERROR: CANNOT READ IDENTIFICATION TAG]");
                            NoDelay();
                        }
                    }
                    if (player.getPrimaryWeapon().equals("001")){
                        System.out.println( ">No. 001 | FISTS [CW]" );
                        NoDelay();
                    } else {
                        System.out.println( ">No. 001 | FISTS" );
                        NoDelay();
                    }
                    if (FtA_45.isDirectory()){
                        if (FtA_45TAG.isFile()){
                            if (player.getPrimaryWeapon().equals("002")){
                                System.out.println( ">No. 002 | FtA-45 [CW]" );
                                NoDelay();
                            } else {
                                System.out.println( ">No. 002 | FtA-45" );
                                NoDelay();
                            }
                        } else {
                            System.out.println(">No. 002 | FtA-45 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                            NoDelay();
                        }
                    }
                    if (LP61.isDirectory()){
                        if (LP61TAG.isFile()){
                            if (player.getPrimaryWeapon().equals("003")){
                                System.out.println( ">No. 003 | LP-61 [CW]" );
                                NoDelay();
                            } else {
                                System.out.println( ">No. 003 | LP-61" );
                                NoDelay();
                            }
                        } else {
                            System.out.println(">No. 003 | LP-61 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                            NoDelay();
                        }
                    }
                    if (MP60.isDirectory()){
                        if (MP60TAG.isFile()){
                            if (player.getPrimaryWeapon().equals("004")){
                                System.out.println( ">No. 004 | MP60 [CW]" );
                                NoDelay();
                            } else {
                                System.out.println( ">No. 004 | MP60" );
                                NoDelay();
                            }
                        } else {
                            System.out.println(">No. 004 | MP60 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                            NoDelay();
                        }
                    }
                    if (P1885.isDirectory()){
                        if (P1885TAG.isFile()){
                            if (player.getPrimaryWeapon().equals("005")){
                                System.out.println( ">No. 005 | P.1885 [CW]" );
                                NoDelay();
                            } else {
                                System.out.println( ">No. 005 | P.1885" );
                                NoDelay();
                            }
                        } else {
                            System.out.println(">No. 005 | P.1885 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                            NoDelay();
                        }
                    }
                    if (P60.isDirectory()){
                        if (P60TAG.isFile()){
                            if (player.getPrimaryWeapon().equals("006")){
                                System.out.println( ">No. 006 | P60 [CW]" );
                                NoDelay();
                            } else {
                                System.out.println( ">No. 006 | P60" );
                                NoDelay();
                            }
                        } else {
                            System.out.println(">No. 006 | P60 [ERROR: CANNOT READ IDENTIFICATION TAG]");
                            NoDelay();
                        }
                    }
                } else {
                    LongDelay();
                    System.out.println("ERROR");
                    ShortDelay();
                    System.out.println("//NO ITEMS MATCHING FILTER FOUND IN INVENTORY!");
                    break;
                }
                System.out.println(">BACK (0/BACK)");
                boolean weaponNotChosen = true;
                while ( weaponNotChosen ) {
                    switch (input.nextLine()) {
                        case "TESTWEAP": case "testweap": case "000":
                            if (TESTWEAP.isDirectory()) {
                                if (TESTWEAPTAG.isFile()) {
                                    if (!player.getPrimaryWeapon().equals("000")){
                                        System.out.println("//SWITCH WEAPON? J/N");
                                        switch (input.nextLine()){
                                            case "J": case "j":
                                                player.setPrimaryWeapon("000");
                                                System.out.println("//WEAPON SUCCESSFULLY SWITCHED");
                                                ShortDelay();
                                                System.out.println("//INPUT \'N\' TO REFRESH INTERFACE");
                                                break;
                                            case "N": case "n":
                                                System.out.println("//WEAPON SWITCH CANCELLED");
                                                break;
                                            default:
                                                System.out.println("//ERROR: INVALID INPUT");
                                                break;
                                        }
                                    } else {
                                        System.out.println("//ERROR: WEAPON ALREADY EQUIPPED");
                                    }
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
                        case "FISTS": case "fists": case "001":
                                    if (!player.getPrimaryWeapon().equals("001")){
                                        System.out.println("//SWITCH WEAPON? J/N");
                                        switch (input.nextLine()){
                                            case "J": case "j":
                                                player.setPrimaryWeapon("001");
                                                System.out.println("//WEAPON SUCCESSFULLY SWITCHED");
                                                ShortDelay();
                                                System.out.println("//INPUT \'N\' TO REFRESH INTERFACE");
                                                break;
                                            case "N": case "n":
                                                System.out.println("//WEAPON SWITCH CANCELLED");
                                                break;
                                            default:
                                                System.out.println("//ERROR: INVALID INPUT");
                                                break;
                                        }
                                    }
                            break;
                        case "FtA-45": case "fta-45": case "002":
                            if (FtA_45.isDirectory()) {
                                if (FtA_45TAG.isFile()) {
                                    if (!player.getPrimaryWeapon().equals("002")){
                                        System.out.println("//SWITCH WEAPON? J/N");
                                        switch (input.nextLine()){
                                            case "J": case "j":
                                                player.setPrimaryWeapon("002");
                                                System.out.println("//WEAPON SUCCESSFULLY SWITCHED");
                                                ShortDelay();
                                                System.out.println("//INPUT \'N\' TO REFRESH INTERFACE");
                                                break;
                                            case "N": case "n":
                                                System.out.println("//WEAPON SWITCH CANCELLED");
                                                break;
                                            default:
                                                System.out.println("//ERROR: INVALID INPUT");
                                                break;
                                        }
                                    } else {
                                        System.out.println("//ERROR: WEAPON ALREADY EQUIPPED");
                                    }
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
                        case "LP-61": case "lp-61": case "003":
                            if (LP61.isDirectory()) {
                                if (LP61TAG.isFile()) {
                                    if (!player.getPrimaryWeapon().equals("003")){
                                        System.out.println("//SWITCH WEAPON? J/N");
                                        switch (input.nextLine()){
                                            case "J": case "j":
                                                player.setPrimaryWeapon("003");
                                                System.out.println("//WEAPON SUCCESSFULLY SWITCHED");
                                                ShortDelay();
                                                System.out.println("//INPUT \'N\' TO REFRESH INTERFACE");
                                                break;
                                            case "N": case "n":
                                                System.out.println("//WEAPON SWITCH CANCELLED");
                                                break;
                                            default:
                                                System.out.println("//ERROR: INVALID INPUT");
                                                break;
                                        }
                                    } else {
                                        System.out.println("//ERROR: WEAPON ALREADY EQUIPPED");
                                        break;
                                    }
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
                            if (MP60.isDirectory()) {
                                if (MP60TAG.isFile()) {
                                    if (!player.getPrimaryWeapon().equals("004")){
                                        System.out.println("//SWITCH WEAPON? J/N");
                                        switch (input.nextLine()){
                                            case "J": case "j":
                                                player.setPrimaryWeapon("004");
                                                System.out.println("//WEAPON SUCCESSFULLY SWITCHED");
                                                ShortDelay();
                                                System.out.println("//INPUT \'N\' TO REFRESH INTERFACE");
                                                break;
                                            case "N": case "n":
                                                System.out.println("//WEAPON SWITCH CANCELLED");
                                                break;
                                            default:
                                                System.out.println("//ERROR: INVALID INPUT");
                                                break;
                                        }
                                    } else {
                                        System.out.println("//ERROR: WEAPON ALREADY EQUIPPED");
                                    }
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
                            if (P1885.isDirectory()) {
                                if (P1885TAG.isFile()) {
                                    if (!player.getPrimaryWeapon().equals("005")){
                                        System.out.println("//SWITCH WEAPON? J/N");
                                        switch (input.nextLine()){
                                            case "J": case "j":
                                                player.setPrimaryWeapon("005");
                                                System.out.println("//WEAPON SUCCESSFULLY SWITCHED");
                                                ShortDelay();
                                                System.out.println("//INPUT \'N\' TO REFRESH INTERFACE");
                                                break;
                                            case "N": case "n":
                                                System.out.println("//WEAPON SWITCH CANCELLED");
                                                break;
                                            default:
                                                System.out.println("//ERROR: INVALID INPUT");
                                                break;
                                        }
                                    } else {
                                        System.out.println("//ERROR: WEAPON ALREADY EQUIPPED");
                                    }
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
                            if (P60.isDirectory()) {
                                if (P60TAG.isFile()) {
                                    if (!player.getPrimaryWeapon().equals("006")){
                                        System.out.println("//SWITCH WEAPON? J/N");
                                        switch (input.nextLine()){
                                            case "J": case "j":
                                                player.setPrimaryWeapon("006");
                                                System.out.println("//WEAPON SUCCESSFULLY SWITCHED");
                                                ShortDelay();
                                                System.out.println("//INPUT \'N\' TO REFRESH INTERFACE");
                                                break;
                                            case "N": case "n":
                                                System.out.println("//WEAPON SWITCH CANCELLED");
                                                break;
                                            default:
                                                System.out.println("//ERROR: INVALID INPUT");
                                                break;
                                        }
                                    } else {
                                        System.out.println("//ERROR: WEAPON ALREADY EQUIPPED");
                                    }
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
                        case "BACK": case "back": case "0":
                            System.out.println("//ARSENAL SUBMENU EXITED");
                            weaponNotChosen = false;
                            break;
                    }
                }
                    break;
                case "ARTIKEL": case "artikel": case "ATKL": case "atkl": case "ITEMS": case "items": case "4":
                    System.out.println("//ERROR: DRIVE SECTOR CORRUPTED");
                    ShortDelay();
                    System.out.println("//UNABLE TO ACCESS ITEM INVENTORY");
                    break;
                case "STATUS": case "status": case "STAT": case "stat": case "5":
                    System.out.println("//PLEASE SELECT FIELD TO UPDATE");
                    ShortDelay();
                    System.out.println(">VITALS (1/VITL)");
                    NoDelay();
                    System.out.println(">STATUS (2/STAT)");
                    NoDelay();
                    System.out.println(">AMMUNITION (3/MNTN)");
                    NoDelay();
                    System.out.println(">BACK (0/BACK)");
                    switch (input.nextLine()){
                        case "HEALTH": case "health": case "1":
                            System.out.println("//CURRENT HP: " + player.getUserCurrentHealth() + "/" + player.getUserMaxHealth());
                            ShortDelay();
                            System.out.println("//PLEASE ENTER NEW HEALTH VALUE");
                            boolean InvalidHealth = true;
                            while (InvalidHealth){
                                try {
                                    double newHealth = Double.parseDouble(input.nextLine());
                                    if (newHealth > Double.parseDouble(player.getUserMaxHealth())){
                                        System.out.println("//ERROR: MAXIMUM HEALTH EXCEEDED");
                                    } else {
                                        player.setUserCurrentHealth(Double.toString(newHealth));
                                        System.out.println("//CURRENT HEALTH UPDATED");
                                        break;
                                    }
                                } catch (NumberFormatException exception) {
                                    System.out.println("//ERROR: INVALID INPUT FORMAT.");
                                }
                            }
                            break;
                        case "STATUS": case "status": case "2":
                            System.out.println("//PLEASE ENTER NEW STATUS");
                            player.setUserStatus(input.nextLine());
                            System.out.println("//CURRENT STATUS UPDATED");
                            break;
                        case "AMMUNITION": case "ammunition": case "AMMO": case "ammo": case "3":
                            if (currentWeaponData[10].equals("RANGED") || currentWeaponData[10].equals("HYBRID")) {
                                System.out.println("//CURRENT MAGAZINE TYPE: " + currentWeaponData[13]);
                                ShortDelay();
                                System.out.println("//MAGAZINE : " + currentWeaponData[14] + " / " + currentWeaponData[15] + " " + currentWeaponData[17]);
                                ShortDelay();
                                System.out.println("//PLEASE ENTER NEW AMMUNITION VALUE");
                                boolean invalidAmmo = true;
                                while (invalidAmmo) {
                                    try {
                                        if (currentWeaponData[12].equals("Electricity")){
                                            Double newAmmo = Double.parseDouble(input.nextLine());
                                            if (newAmmo > Double.parseDouble(currentWeaponData[15])) {
                                                System.out.println("//ERROR: MAXIMUM AMMUNITION EXCEEDED");
                                            } else {
                                                currentWeaponData[14] = (Double.toString(newAmmo));
                                                writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+currentWeapon+"\\ItemID.TAG"));
                                                for (int i = 0; i < 30; i++ ){
                                                    writer.write (currentWeaponData[i] + "~");
                                                }
                                                writer.flush();
                                                writer.close();
                                                System.out.println("//CURRENT AMMO UPDATED");
                                                invalidAmmo = false;
                                                break;
                                            }
                                        } else {
                                            int newAmmo = Integer.parseInt(input.nextLine());
                                            if (newAmmo > Integer.parseInt(currentWeaponData[15])) {
                                                System.out.println("//ERROR: MAXIMUM AMMUNITION EXCEEDED");
                                            } else {
                                                currentWeaponData[14] = (Double.toString(newAmmo));
                                                writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+currentWeapon+"\\ItemID.TAG"));
                                                for (int i = 0; i < 30; i++ ){
                                                    writer.write (currentWeaponData[i] + "~");
                                                }
                                                writer.flush();
                                                writer.close();
                                                System.out.println("//CURRENT AMMO UPDATED");
                                                invalidAmmo = false;
                                                break;
                                            }
                                        }
                                    } catch (NumberFormatException exception) {
                                        System.out.println("//ERROR: INVALID INPUT FORMAT.");
                                    }
                                }
                            } else {
                                System.out.println("//ERROR: CURRENT WEAPON DOES NOT USE AMMUNITION");
                                break;
                            }
                            break;
                        case "BACK": case "back": case "0":
                            System.out.println("//STATUS SUBMENU EXITED");
                            break;
                        default:
                            System.out.println("//ERROR: INVALID INPUT");
                            break;
                    }
                    break;
                case "NEU-LADEN": case "neu-laden": case "NEULADEN": case "neuladen": case "NULD": case "nuld": case "N": case "n": case "REFRESH": case "refresh": case "REFR": case "refr":
                    combat( player );
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE."); //ERROR: INVALID INPUT.
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
        System.out.println("COMPLETE");
        ShortDelay();
        System.out.println("//THIS SUBROUTINE IS DESIGNED TO WORK WITH THE VERSION OF \"HELLO WORLD\" BY LOUIE ZONG LISTED ON YOUTUBE");
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
        System.out.print("//HEL-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("WORLD...");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//PRO-");
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
        System.out.print("E-");
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
        System.out.print("//HEL-");
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
        System.out.print("//AL-");
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
        System.out.print("CIR-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("CUI-");
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
        System.out.print("//HEL-");
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
        System.out.print("DI-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("GI-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("TAL-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LY ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("I-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("SO-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LA-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("TED, ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("OH, ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("CRE-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("A-");
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
        System.out.print("WAI-");
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
        System.out.print("//HEL-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("WORLD...");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//PRO-");
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
        System.out.print("E-");
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
        System.out.print("//HEL-");
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
