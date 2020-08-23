
import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DaVG {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StringBuilder builder;
    private static Scanner input;
    private static Scanner input2;

    private static boolean debugMode;
    private static String VersionNo;


    private static Boolean cutscenesEnabled;
    private static int LongDelay;
    private static int MedDelay;
    private static int ShortDelay;
    private static int NoDelay;
    private static int currentCycle;
    private static Scanner petc;
    private static boolean veryFirstBoot;


    public static void main( String[] args ) throws InterruptedException, IOException {
        VersionNo = "R082320"; //Version Number
        debugMode = false; //Debug Mode (true/false)
        cutscenesEnabled = true; //Cutscenes (enabled = true, disabled = false)
        LongDelay = 3; //length of LongDelay in seconds
        MedDelay = 2; //length of MedDelay in seconds
        ShortDelay = 1; //length of ShortDelay in seconds
        NoDelay = 100; //length of NoDelay in milliseconds

        currentCycle = 0; // current turn/round number; used in Combat method

        input = new Scanner(System.in);
        input2 = new Scanner(System.in);
        petc = new Scanner(System.in);

        /* Welcome to DaVG Ausf. J!
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
        int chosenProfile = -1;

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
        System.out.println("//Initialisierung KRIEGSVERSTAND V2... "); //Initializing KRIEGSVERSTAND V2...
        MedDelay();
        System.out.print("//ÜBERPRUFUNG ZAE... "); //CHECKING ZAE (Zufällig-Abruf-Erinnerung, or Random Retrieval Memory - known in our universe as Random Access Memory or RAM)
        MedDelay();
        System.out.println("128/128 KDB ZAE ZUGÄNGLICH"); //128/128 KDB ZAE AVAILABLE (Kilo-Datenbytes, known to us simply as Kilobytes)
        ShortDelay();
        System.out.print("//ÜBERPRUFUNG DATENSPEICHER... "); //CHECKING STORAGE...
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
        if (profileList == null){
            profileList = new String[0];
        }
        if (profileList.length == 0) {
            veryFirstBoot=true;
            LongDelay();
            System.out.println( "KOMPLETT"); // COMPLETE
            LongDelay();
            System.out.println( "//FEHLER: KEINE PROFIL GEFUNDEN IM FESTPLATTE " ); //ERROR: NO PROFILES FOUND IN HARD DRIVE
            ShortDelay();
            System.out.println( "//ERSTELLEN NEU PROFIL? J/N" ); //CREATE NEW PROFILE? J/N (Like German, "Ja" is "Yes" and "Nein" is "No")
            ShortDelay();
            System.out.println( "//WARNUNG: EINGABE \"N\" WIRD DEAKTIVIERT EINHEIT ZU VERHINDERN MANIPULATION" ); //WARNING: INPUTTING "N" WILL DEACTIVATE THE DEVICE TO PREVENT TAMPERING
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
                        ShortDelay();
                        break;
                    case "#KVSCLE": //changes language to English
                        player.setUserLanguage("ENGLISH");
                        System.out.println("//LANGUAGE PREFERENCE UPDATED");
                        ShortDelay();
                        break;
                    default:
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                        break;
                }
            }
        } else {
            ShortDelay();
            System.out.println( "KOMPLETT" ); //COMPLETE
            ShortDelay();
            for (int i = 0; i < profileList.length; i++) {
                System.out.println("> " + i + "_ " + profileList[i]);
                NoDelay();
            }
            System.out.println("> E_ ERSTELLEN NEUES BENUTZER-PROFIL");
            ShortDelay();
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE");
                        break;
                    case "E":
                        veryFirstBoot = true;
                        break;
                    default:
                        profileNotChosen = false;
                        player.setUserName(profileInput);
                        if (profileInput.length() == 1){
                            try{
                                player.setUserName(profileList[Integer.parseInt(profileInput)]);
                            } catch ( Exception e ){
                                veryFirstBoot = false;
                            }
                        } else {
                            player.setUserName(profileInput);
                        }
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
            System.out.println( "//SCHAFFEN NEU BENUTZER-PROFIL? J/N" ); //CREATE NEW PROFILE? J/N
            ShortDelay();
            System.out.println( "//WARNUNG: DRÜCKEN \"N\" WIRD DEAKTIVIERT EINHEIT ZU VERHINDERN MANIPULATION" ); //WARNING: PRESSING "N" WILL DEACTIVATE THE DEVICE TO PREVENT TAMPERING
            boolean profileOptionNotChosen = true;
            while ( profileOptionNotChosen ) {
                switch ( input.nextLine() ) {
                    case "J": case "j":
                        profileOptionNotChosen = false;
                        System.out.print( "//ZUWEISEN FESTPLATTE-RAUM... " );
                        LongDelay();
                        System.out.println( "KOMPLETT");
                        player.setIsEmpty( false );
                        ShortDelay();
                        break;
                    case "N": case "n":
                        profileOptionNotChosen = false;
                        shutdown( player );
                        break;
                    case "#KVSEDBM":
                        profileOptionNotChosen = true;
                        debugMode = true;
                        System.out.println( "//MAINTENANCE MODE ENABLED" );
                        break;
                    case "#KVSDDBM":
                        profileOptionNotChosen = true;
                        debugMode = false;
                        System.out.println( "//MAINTENANCE MODE DISABLED" );
                        break;
                    case "#KVSECS":
                        profileOptionNotChosen = true;
                        cutscenesEnabled = true;
                        System.out.println("//ACCELERATED READ/WRITE DISABLED");
                        break;
                    case "#KVSDCS":
                        profileOptionNotChosen = true;
                        cutscenesEnabled = false;
                        System.out.println("//ACCELERATED READ/WRITE ENABLED");
                        break;
                    default:
                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                        } else if (player.getUserLanguage().equals("ENGLISH")){
                            System.out.println("//ERROR: INVALID INPUT");
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
                System.out.print( "//LADEN PROFIL... " ); //LOADING PROFILE...
                player.readPlayerData( playerDataLocation );
                LongDelay();
                System.out.println( "KOMPLETT" ); //COMPLETE
                ShortDelay();
                System.out.println( "//BITTE AUSWÄHLEN DIAGNOSTISCH-PRÜFUNG-BATTERIE AUSFÜHRUNG" ); //PLEASE SELECT DIAGNOSTIC TEST BATTERY TYPE
                ShortDelay();
                System.out.println("> 0_ STANDARD ( VOLLSTÄNDIG DIAGNOSTISCH-PRÜFUNG-BATTERIE )"); //STANDARD (FULL DIAGNOSTIC TEST BATTERY)
                NoDelay();
                System.out.println("> 1_ KURZ ( VERKÜRZT DIAGNOSTISCH-PRÜFUNG-BATTERIE )"); //SHORT (SHORTENED DIAGNOSTIC TEST BATTERY)
                NoDelay();
                System.out.println("> 2_ AUSLASSEN ( DIAGNOSTICH-PRÜFUNG-BATTERIE NICHT HINGERICHTET )"); //SKIP (DIAGNOSTIC TEST BATTERY NOT RUN)
                boolean equipOptionNotChosen = true;
                while (equipOptionNotChosen) {
                    switch (input.nextLine()) {
                        case "STANDARD": case "standard": case "0":
                            equipOptionNotChosen = false;
                            intro(player, retUser);
                            break;
                        case "KURZ": case "kurz": case "1":
                            equipOptionNotChosen = false;
                            intro2(player, retUser);
                            break;
                        case "AUSLASSEN": case "auslassen": case "2":
                            equipOptionNotChosen = false;
                            mainMenu(player);
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
        System.out.println( "//EINLITEN DIAGNOSTISCH-PRÜFUNG-BATTERIE... " ); //INITIALIZING DIAGNOSTIC TEST BATTERY...
        LongDelay();
        System.out.print( "//PROZESSOR-ADER 1... " ); //PROCESSOR CORE 1...
        MedDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//PROZESSOR-ADER 2... " ); //PROCESSOR CORE 2...
        ShortDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//PROZESSOR-ADER 3... " ); //PROCESSOR CORE 3...
        ShortDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//PROZESSOR-ADER 4... " ); //PROCESSOR CORE 4...
        MedDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//PROZESSOR-ADER 5... " ); //PROCESSOR CORE 5...
        ShortDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//REAKTOR-ADER... " ); //REACTOR CORE...
        MedDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//TANKFÜLLSTAND... " ); //FUEL LEVELS...
        ShortDelay();
        System.out.println( "O.K." );
        NoDelay();
        System.out.print( "//KÜHLMITTELSTAND... " ); //COOLANT LEVELS...
        LongDelay();
        System.out.println( "O.K." );
        NoDelay();
        intro2( player, retUser);

    }

    private static void intro2(playerCharacter player , boolean retUser) throws InterruptedException, IOException {
        System.out.print( "//ABRUFEN BENUTZERPROFIL... " ); //RETRIEVING USER PROFILE...
        if ( retUser ) {
            LongDelay();
            System.out.println( "KOMPLETT" ); //COMPLETE
            ShortDelay();
            System.out.print( "//VERIFIZIEREN BENUTZERIDENTITÄT... " ); //VERIFYING USER IDENTITY...
            LongDelay();
            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                System.out.println( "BESTÄTIGT" ); //CONFIRMED.
                ShortDelay();
                System.out.println( "//BENUTZER ENTSPRECHEN 1 EINGANG IM EINTRAGUNG" ); //USER MATCHES 1 ENTRY IN REGISTRY.
                ShortDelay();
                System.out.println( "//WILLKOMMEN ZURÜCK, " + player.getUserName() ); //WELCOME BACK, <PLAYER NAME>.
                ShortDelay();
                System.out.println( "//DRÜCKEN EINGEBEN/RÜCKKEHR ZU FORTSETZEN" ); //PRESS ENTER/RETURN TO CONTINUE
            } else if (player.getUserLanguage().equals("ENGLISH")){
                System.out.println( "CONFIRMED" );
                ShortDelay();
                System.out.println( "//USER MATCHES 1 ENTRY IN REGISTRY" );
                ShortDelay();
                System.out.println( "//WELCOME BACK, " + player.getUserName() );
                ShortDelay();
                System.out.println( "//PRESS ENTER/RETURN TO CONTINUE" );
            }
            petc.nextLine();
            mainMenu( player );

        } else {
            LongDelay();
            System.out.println( "GESCHEITERT" ); //FAILED
            LongDelay();
            System.out.println( "//FEHLER: EINHEIT NICHT VERBUNDEN MIT BESATZUNG-DATENBANK" ); //ERROR: UNIT NOT LINKED WITH PERSONNEL DATABASE
            ShortDelay();
            System.out.println( "//ÜBERSCHREIBE? J/N" ); //OVERRIDE? Y/N
            ShortDelay();
            System.out.println( "//WARNUNG: DRÜCKEN \"N\" WIRD DEAKTIVIERT EINHEIT ZU VERHINDERN MANIPULATION" ); //WARNING: PRESSING "N" WILL DEACTIVATE UNIT TO PREVENT TAMPERING
            boolean optionNotChosen = true;
            while ( optionNotChosen ) {
                switch ( input.nextLine() ) {
                    case "J": case "j":
                        System.out.print( "//ÜBERSCHREIBEN... " ); //OVERRIDING...
                        LongDelay();
                        System.out.println( "KOMPLETT");
                        ShortDelay();
                        optionNotChosen = false;
                        newuser( player );
                        break;
                    case "N": case "n": case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                        } else if (player.getUserLanguage().equals("ENGLISH")){
                            System.out.println("//ERROR: INVALID INPUT");
                        }
                        break;
                }
            }
        }
    }

    private static void newuser( playerCharacter player ) throws InterruptedException, IOException {
        System.out.println( "//DIAGNOSTISCH-PRÜFUNG-BATTERIE KOMPLETT" );//DIAGNOSTIC TESTS COMPLETED.
        LongDelay();
        System.out.println( "//FEHLER: KEINE DATEN ZUM GEGENWÄRTIG-BENUTZER GEFUNDEN" );//ERROR: NO DATA ON CURRENT USER FOUND.
        LongDelay();
        System.out.println( "//KEINE ADMINISTRATOREN IM BENUTZER-EINTRAGUNG" );//NO ADMINISTRATORS IN USER REGISTRY.
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
                case "N": case "n": case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
                    }
                    break;
            }
        }
        System.out.println( "//BITTE EINGEBEN NEU BENUTZERNAME" );//PLEASE INPUT NEW USER NAME.
        String userName = input.nextLine();
        System.out.print( "//SCHREIBEN ZU FESTPLATTE... " );//WRITING TO HARD DRIVE...
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
         * The information on a character - the Player Data - is stored in an index of 38 values, shown below:
         * 00 - Player Name
         * 01 - Preferred Language (This will be set to the DaVG's native Volkshavenish by default)
         * 02 - Synchronization Level (Starts at 1)
         * 03 - Lifetime Field Data (000 by default, Measured in DB)
         * 04 - Current HP (Set at 100 by default)
         * 05 - Maximum HP (Set at 100 by default)
         * 06 - Current Status (Normal, Poisoned, etc.)
         * 07 - Class 0
         * 08 - Class 1 (if applicable)
         * 09 - Class 2 (if applicable)
         * 10 - Class 3 (if applicable)
         * 11 - Strength score
         * 12 - Dexterity score
         * 13 - Constitution Score
         * 14 - Intelligence Score
         * 15 - Wisdom Score
         * 16 - Charisma Score
         * 17 - Background
         * 18 - Background Proficiency 0
         * 19 - Background Proficiency 1
         * 20 - Skill Proficiency 0
         * 21 - Skill Proficiency 1
         * 22 - Skill Proficiency 2 (If applicable)
         * 23 - Skill Proficiency 3 (If applicable)
         * 24 - Software Slot 00
         * 25 - Software Slot 01
         * 26 - Software Slot 02
         * 27 - Software Slot 03
         * 28 - Software Slot 04
         * 29 - Software Slot 05
         * 30 - Software Slot 06
         * 31 - Software Slot 07
         * 32 - Software Slot 08
         * 33 - Software Slot 09
         * 34 - Primary Weapon ID
         * 35 - Secondary Weapon ID (if applicable)
         * 36 - Auxiliary DaVG Module
         * 37 - Special Properties
         */

        player.setIsEmpty(false);
        player.setUserName( userName ); //00
        player.setUserLanguage("VOLKSHAVENISH"); //01
        player.setUserLevel("0"); //02
        player.setUserXP("000"); //03
        player.setUserCurrentHealth("100"); //04
        player.setUserMaxHealth("100"); //05
        player.setUserStatus("NORMAL"); //06
        player.setUserClass0("KL0M"); //07
        player.setUserClass1("KL1M"); //08
        player.setUserClass2("KL2M"); //09
        player.setUserClass3("KL3M"); //10
        player.setUserStrength("STKM"); //11
        player.setUserDexterity("GSKM"); //12
        player.setUserConstitution("VFSM"); //13
        player.setUserIntelligence("INTM"); //14
        player.setUserWisdom("WSHM"); //15
        player.setUserCharisma("CHRM"); //16
        player.setUserBackground("HNTM"); //17
        player.setUserBackgroundProficiency0("HK0M"); //18
        player.setUserBackgroundProficiency1("HK1M"); //19
        player.setUserSkillProficiency0("FK0M"); //20
        player.setUserSkillProficiency1("FK1M"); //21
        player.setUserSkillProficiency2("FK2M"); //22
        player.setUserSkillProficiency3("FK3M"); //23
        player.setSoftware00("T00M"); //24
        player.setSoftware01("T01M"); //25
        player.setSoftware02("T02M"); //26
        player.setSoftware03("T03M"); //27
        player.setSoftware04("T04M"); //28
        player.setSoftware05("T05M"); //29
        player.setSoftware06("T06M"); //30
        player.setSoftware07("T07M"); //31
        player.setSoftware08("T08M"); //32
        player.setSoftware09("T09M"); //33
        player.setPrimaryWeapon("PWFM"); //34
        player.setSecondaryWeapon("SWFM"); //35
        player.setAuxModule("N/A"); //36
        player.setSpecialProperties("$"); //37
        player.writePlayerData();
        LongDelay();
        System.out.println( "KOMPLETT" ); //COMPLETE
        ShortDelay();
        System.out.println( "//NEU EINGANG ERSTELLT IM BENUTZER-EINTRAGUNG" ); //NEW ENTRY CREATED IN USER REGISTRY.
        ShortDelay();
        System.out.println( "//WILKOMMEN, " + player.getUserName() ); //GREETINGS, <PLAYER NAME>.
        ShortDelay();
        System.out.println( "//DRÜCKEN EINGABE/RÜCKKEHR ZU FORTSETZEN" ); //PRESS ENTER/RETURN TO CONTINUE
        petc.nextLine();
        mainMenu( player );
    }

    private static void mainMenu( playerCharacter player ) throws InterruptedException, IOException {
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
            System.out.println( "> 0_ VITALS [VITL]" );
            NoDelay();
            System.out.println( "> 1_ VORRÄTE [VRRT]" );
            NoDelay();
            System.out.println( "> 2_ TAGEBUCH [TAGB]" );
            NoDelay();
            System.out.println( "> 3_ WERKZEUG [WKZG] " );
            NoDelay();
            System.out.println( "> 4_ KAMPF [KMPF] " );
            NoDelay();
            System.out.println( "> A_ AKTUALISIEREN [AKTL] ");
            NoDelay();
            System.out.println( "> H_ HERUNTERFAHREN [HNTF] " );
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
            System.out.println( "> 0_ VITALS [VITL]" );
            NoDelay();
            System.out.println( "> 1_ INVENTORY [VRRT]" );
            NoDelay();
            System.out.println( "> 2_ JOURNAL [TAGB]" );
            NoDelay();
            System.out.println( "> 3_ UTILITY [WKZG] " );
            NoDelay();
            System.out.println( "> 4_ COMBAT [KMPF] " );
            NoDelay();
            System.out.println( "> A_ REFRESH [AKTL] ");
            NoDelay();
            System.out.println( "> H_ SHUTDOWN [HNTF] " );
            NoDelay();
        }
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "VITALS": case "vitals": case "VITL": case "vitl": case "0":
                    vitals( player );
                    break;
                case "VORRÄTE": case "VORRATE": case "VORRAETE": case "vorrate": case "INVENTORY": case "inventory": case "VRRT": case "vrrt": case "1":
                    inventory( player );
                    break;
                case "TAGEBUCH": case "tagebuch": case "JOURNAL": case "journal": case "TAGB": case "tagb":  case "2":
                    journal ( player );
                    break;
                case "WERKZEUG": case "werkzeug": case "UTILITY": case "utility": case "WKZG": case "wrkzg": case "3":
                    utility ( player );
                    break;
                case "KAMPF": case "kampf": case "COMBAT": case "combat": case "KMPF": case "kmpf":  case "4":
                    combat( player );
                    break;
                case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                    mainMenu(player);
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.print("//TRANSLATING MENU... ");
                    MedDelay();
                    System.out.println("COMPLETE");
                    ShortDelay();
                    System.out.println( "> 0_ VITALS [VITL]" );
                    NoDelay();
                    System.out.println( "> 1_ INVENTORY [VRRT]" );
                    NoDelay();
                    System.out.println( "> 2_ JOURNAL [TAGB]" );
                    NoDelay();
                    System.out.println( "> 3_ UTILITY [WKZG] " );
                    NoDelay();
                    System.out.println( "> 4_ COMBAT [KMPF] " );
                    NoDelay();
                    System.out.println( "> A_ REFRESH [AKTL] ");
                    NoDelay();
                    System.out.println( "> H_ SHUTDOWN [HNTF] " );
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                case "sing":
                    sing( player );
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
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
        int roll;
        while (!input.hasNextInt()){
            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
            } else if (player.getUserLanguage().equals("ENGLISH")){
                System.out.println("//ERROR: INVALID INPUT");
            }
            input = new Scanner(System.in);
        }
        roll = input.nextInt();

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
                    roll += modCalc( Integer.parseInt(player.getUserStrength())) + softwareCalc(player, "STRENGTH");
                }
                break;
            case "DEXTERITY": case "dexterity": case "DEXT": case "dext": case "INITIATIVE": case "initiative": case "INIT": case "init":
                if (dexMissing){
                    System.out.println("//ERROR: USER DEXTERITY DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc(Integer.parseInt(player.getUserDexterity())) + softwareCalc(player, "DEXTERITY") ;
                }
                break;
            case "CONSTITUTION": case "constitution": case "CNST": case "cnst":
                if (conMissing){
                    System.out.println("//ERROR: USER CONSTITUTION DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserConstitution())) + softwareCalc(player, "CONSTITUTION") ;
                }
                break;
            case "INTELLIGENCE": case "intelligence": case "INTL": case "intl":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + softwareCalc(player, "INTELLIGENCE") ;
                }
                break;
            case "WISDOM": case "wisdom": case "WSDM": case "wsdm":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + softwareCalc(player, "WISDOM") ;
                }
                break;
            case "CHARISMA": case "charisma": case "CHRM": case "chrm":
                if (chaMissing){
                    System.out.println("//ERROR: USER CHARISMA DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserCharisma())) + softwareCalc(player, "CHARISMA");
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
                    roll += modCalc( Integer.parseInt(player.getUserStrength())) + softwareCalc(player, "ATHLETICS") + proficiencyCalc(player, "ATHLETICS");
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
                    roll += modCalc(Integer.parseInt(player.getUserDexterity())) + softwareCalc(player, "ACROBATICS") + proficiencyCalc(player, "ACROBATICS");
                }
                break;
            case "SLEIGHT-OF-HAND": case "sleight-of-hand": case "DEX1": case "dex1":
                if (dexMissing){
                    System.out.println("//ERROR: USER DEXTERITY DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc(Integer.parseInt(player.getUserDexterity())) + softwareCalc(player, "SLEIGHT-OF-HAND") + proficiencyCalc(player, "SLEIGHT-OF-HAND");
                }
                break;
            case "STEALTH": case "stealth": case "DEX2": case "dex2":
                if (dexMissing){
                    System.out.println("//ERROR: USER DEXTERITY DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc(Integer.parseInt(player.getUserDexterity())) + softwareCalc(player, "STEALTH") + proficiencyCalc(player, "STEALTH");
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
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + softwareCalc(player, "ARCANA") + proficiencyCalc(player, "ARCANA");
                }
                break;
            case "HISTORY": case "history": case "INT1": case "int1":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + softwareCalc(player, "HISTORY") + proficiencyCalc(player, "HISTORY");
                }
                break;
            case "INVESTIGATION": case "investigation": case "INT2": case "int2":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + softwareCalc(player, "INVESTIGATION") + proficiencyCalc(player, "INVESTIGATION");
                }
                break;
            case "NATURE": case "nature": case "INT3": case "int3":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + softwareCalc(player, "NATURE") + proficiencyCalc(player, "NATURE");
                }
                break;
            case "RELIGION": case "religion": case "INT4": case "int4":
                if (intMissing){
                    System.out.println("//ERROR: USER INTELLIGENCE DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserIntelligence())) + softwareCalc(player, "RELIGION") + proficiencyCalc(player, "RELIGION");
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
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + softwareCalc(player, "ANIMAL HANDLING") + proficiencyCalc(player, "ANIMAL HANDLING");
                }
                break;
            case "INSIGHT": case "insight": case "WSD1": case "wsd1":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + softwareCalc(player, "INSIGHT") + proficiencyCalc(player, "INSIGHT");
                }
                break;
            case "MEDICINE": case "medicine": case "WSD2": case "wsd2":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + softwareCalc(player, "MEDICINE") + proficiencyCalc(player, "MEDICINE");
                }
                break;
            case "PERCEPTION": case "perception": case "WSD3": case "wsd3":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + softwareCalc(player, "PERCEPTION") + proficiencyCalc(player, "PERCEPTION");
                }
                break;
            case "SURVIVAL": case "survival": case "WSD4": case "wsd4":
                if (wisMissing){
                    System.out.println("//ERROR: USER WISDOM DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserWisdom())) + softwareCalc(player, "SURVIVAL") + proficiencyCalc(player, "SURVIVAL");
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
                    roll += modCalc( Integer.parseInt(player.getUserCharisma())) + softwareCalc(player, "DECEPTION") + proficiencyCalc(player, "DECEPTION");
                }
                break;
            case "INTIMIDATION": case "intimidation": case "CHR1": case "chr1":
                if (chaMissing){
                    System.out.println("//ERROR: USER CHARISMA DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserCharisma())) + softwareCalc(player, "INTIMIDATION") + proficiencyCalc(player, "INTIMIDATION");
                }
                break;
            case "PERFORMANCE": case "performance": case "CHR2": case "chr2":
                if (chaMissing){
                    System.out.println("//ERROR: USER CHARISMA DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserCharisma())) + softwareCalc(player, "PERFORMANCE") + proficiencyCalc(player, "PERFORMANCE");
                }
                break;
            case "PERSUASION": case "persuasion": case "CHR3": case "chr3":
                if (chaMissing){
                    System.out.println("//ERROR: USER CHARISMA DATA MISSING");
                    ShortDelay();
                    System.out.println("//UNABLE TO CALCULATE MODIFIER");
                    roll += 0;
                } else {
                    roll += modCalc( Integer.parseInt(player.getUserCharisma())) + softwareCalc(player, "PERSUASION") + proficiencyCalc(player, "PERSUASION");
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
        System.out.print( "//VITALS ANALYSIS... " );
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
         * 02 - Synchronization Level
         * 03 - Lifetime Field Data
         * 04 - Current HP
         * 05 - Maximum HP
         * 06 - Current Status
         * 07 - Class 0
         * 08 - Class 1 (if applicable)
         * 09 - Class 2 (if applicable)
         * 10 - Class 3 (if applicable)
         * 11 - Strength score
         * 12 - Dexterity score
         * 13 - Constitution Score
         * 14 - Intelligence Score
         * 15 - Wisdom Score
         * 16 - Charisma Score
         * 17 - Background
         * 18 - Background Proficiency 0
         * 19 - Background Proficiency 1
         * 20 - Skill Proficiency 0
         * 21 - Skill Proficiency 1
         * 22 - Skill Proficiency 2 (If applicable)
         * 23 - Skill Proficiency 3 (If applicable)
         * 24 - Software Slot 00
         * 25 - Software Slot 01
         * 26 - Software Slot 02
         * 27 - Software Slot 03
         * 28 - Software Slot 04
         * 29 - Software Slot 05
         * 30 - Software Slot 06
         * 31 - Software Slot 07
         * 32 - Software Slot 08
         * 33 - Software Slot 09
         */

        System.out.println( "//CURRENT HEALTH (HP): " + player.getUserCurrentHealth() + "/" + player.getUserMaxHealth() + " HP" );
        NoDelay();
        System.out.println( "//OVERALL VITAL STATUS (STAT): " + player.getUserStatus() );
        NoDelay();
        System.out.println( "//SYNCHRONIZATION LEVEL (NIVU): " + player.getUserLevel() );
        NoDelay();
        System.out.println( "//LIFETIME USER PERFORMANCE DATA GATHERED (DATA): " + player.getUserXP() + " DB");
        System.out.println();
        NoDelay();
        if (player.getUserClass0().equals("KL0M")){
            System.out.println( "//CLASS 0 (KLS0): [DATA MISSING]" );
        } else {
            System.out.println( "//CLASS 0 (KLS0): " + player.getUserClass0() );
        }
        NoDelay();

        if (!player.getUserClass1().equals("KL1M")){
            System.out.println( "//CLASS 1 (KLS1): " + player.getUserClass1() );
            NoDelay();
        }

        if (!player.getUserClass2().equals("KL2M")){
            System.out.println( "//CLASS 2 (KLS2): " + player.getUserClass2() );
            NoDelay();
        }

        if (!player.getUserClass3().equals("KL3M")){
            System.out.println( "//CLASS 3 (KLS3): " + player.getUserClass3() );
            NoDelay();
        }

        if (player.getUserStrength().equals("STKM")){
            System.out.println( "//STRENGTH (STRK): [DATA MISSING]" );
        } else {
            System.out.println( "//STRENGTH (STRK): " + player.getUserStrength() );
        }
        NoDelay();
        if (player.getUserDexterity().equals("GSKM")){
            System.out.println( "//DEXTERITY (GSKT): [DATA MISSING]" );
        } else {
            System.out.println( "//DEXTERITY (GSKT): " + player.getUserDexterity() );
        }
        NoDelay();
        if (player.getUserConstitution().equals("VFSM")){
            System.out.println( "//CONSTITUTION (VFSM): [DATA MISSING]" );
        } else {
            System.out.println( "//CONSTITUTION (VFSM): " + player.getUserConstitution() );
        }
        NoDelay();
        if (player.getUserIntelligence().equals("INTM")){
            System.out.println( "//INTELLIGENCE (INTG): [DATA MISSING]" );
        } else {
            System.out.println( "//INTELLIGENCE (INTG): " + player.getUserIntelligence() );
        }
        NoDelay();
        if (player.getUserWisdom().equals("WSHM")){
            System.out.println( "//WISDOM (WSHT): [DATA MISSING]" );
        } else {
            System.out.println( "//WISDOM (WSHT): " + player.getUserWisdom() );
        }
        NoDelay();
        if (player.getUserCharisma().equals("CHRM")){
            System.out.println( "//CHARISMA (CHRM): [DATA MISSING]" );
        } else {
            System.out.println( "//CHARISMA (CHRM): " + player.getUserCharisma() );
        }
        System.out.println();
        NoDelay();

        boolean claMissing = player.getUserClass0().equals("KL0M");
        boolean strMissing = player.getUserStrength().equals("STKM");
        boolean dexMissing = player.getUserDexterity().equals("GSKM");
        boolean conMissing = player.getUserConstitution().equals("VFSM");
        boolean intMissing = player.getUserIntelligence().equals("INTM");
        boolean wisMissing = player.getUserWisdom().equals("WSHM");
        boolean chaMissing = player.getUserCharisma().equals("CHRM");

        if ( claMissing || strMissing || dexMissing || conMissing || intMissing || wisMissing || chaMissing ){
            MedDelay();
            System.out.println( "//WARNING: DATA MISSING ON ONE OR MORE USER ATTRIBUTES" );
            ShortDelay();
            System.out.println( "//PLEASE USE \"EDIT\" FUNCTION TO COMPLETE MISSING ENTRIES");
            MedDelay();
        }

        System.out.println( "> 0_ MEDICAL [MEDS]" );
        NoDelay();
        System.out.println( "> 1_ SKILLS [SKLS]" );
        NoDelay();
        System.out.println( "> 2_ EDIT [EDIT]" );
        NoDelay();
        System.out.println( "> A_ REFRESH [AKTL]" );
        NoDelay();
        System.out.println( "> ._ EXIT [AUSF]" );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "MEDICAL": case "MEDS": case "medical": case "meds": case "0":
                    ShortDelay();
                    System.out.println("//ERROR: DRIVE SECTOR CORRUPTED");
                    ShortDelay();
                    System.out.println( "//INPUT 0 TO RETURN TO MAIN MENU");
                    ShortDelay();
                    System.out.println( "//INPUT 1 TO INITIATE SHUTDOWN");
                    boolean medOptionNotChosen = true;
                    while ( medOptionNotChosen ) {
                        switch ( input.nextLine() ) {
                            case "0":
                                mainMenu( player );
                                break;
                            case "1":
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
                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                    System.out.println("//ERROR: INVALID INPUT");
                                }
                                break;
                        }
                    }
                    break;
                case "SKILLS": case "SKLS": case "skills": case "skls": case "1":
                    skills(player);
                    break;
                case "EDIT": case "edit": case "2":

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
                    String inputValue;
                    boolean secondTierEditCancel = false;
                    boolean editOptionNotChosen = true;
                    while ( editOptionNotChosen ) {
                        switch ( input.nextLine() ) {
                            case "STATUS": case "status": case "STAT": case "stat":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR STATUS (STAT)");
                                ShortDelay();
                                System.out.println("//ENTER \".\" TO CANCEL EDIT");
                                inputValue = input.nextLine();
                                if (inputValue.equals(".")){
                                    System.out.println("//EDIT CANCELLED");
                                    break;
                                } else {
                                    player.setUserStatus(inputValue);
                                    System.out.println("//STATUS [STAT] VALUE UPDATED");
                                    ShortDelay();
                                    System.out.println("//INPUT \"A\" TO REFRESH VITALS MENU");
                                    ShortDelay();
                                    break;
                                }
                            case "DATA": case "data": case "EXP": case "exp": case "XP": case "xp":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR PERFORMANCE DATA (DATA)");
                                ShortDelay();
                                System.out.println("//ENTER \".\" TO CANCEL EDIT");
                                inputValue = input.nextLine();
                                int newXP;
                                if (inputValue.equals(".")){
                                    System.out.println("//EDIT CANCELLED");
                                    break;
                                } else {
                                    try {
                                        newXP = Integer.parseInt(inputValue);
                                    } catch (NumberFormatException e){
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        input2 = new Scanner(System.in);
                                        while (!input2.hasNextInt()){
                                            if (input2.nextLine().equals(".")){
                                                System.out.println("//EDIT CANCELLED");
                                                secondTierEditCancel = true;
                                                break;
                                            } else {
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                            }
                                            input2 = new Scanner(System.in);
                                        }
                                        if (secondTierEditCancel){
                                            break;
                                        } else {
                                        newXP = input2.nextInt();
                                        }
                                    }
                                    player.setUserXP(Integer.toString(newXP));
                                }
                                System.out.println("//PERFORMANCE DATA [DATA] VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//INPUT \"A\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CLASS 0": case "class 0": case "CLASS0": case "class0": case "KLS0": case "kls0":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR CLASS 0 (KLS0)");
                                ShortDelay();
                                System.out.println("//ENTER \".\" TO CANCEL EDIT");
                                inputValue = input.nextLine();
                                if (inputValue.equals(".")){
                                    System.out.println("//EDIT CANCELLED");
                                    break;
                                } else {
                                    player.setUserClass0(inputValue);
                                    System.out.println("//CLASS 0 (KLS0) VALUE UPDATED");
                                    ShortDelay();
                                    System.out.println("//INPUT \"A\" TO REFRESH VITALS MENU");
                                    ShortDelay();
                                    break;
                                }
                            case "CLASS 1": case "class 1": case "CLASS1": case "class1": case "KLS1": case "kls1":
                                if (player.getUserClass0().equals("KL0M")){
                                    System.out.println("//ERROR: PRIMARY CLASS MISSING");
                                } else {
                                    System.out.println("//PLEASE ENTER NEW VALUE FOR CLASS 1 (KLS1)");
                                    ShortDelay();
                                    System.out.println("//ENTER \".\" TO CANCEL EDIT");
                                    inputValue = input.nextLine();
                                    if (inputValue.equals(".")){
                                        System.out.println("//EDIT CANCELLED");
                                        break;
                                    } else {
                                        player.setUserClass1(inputValue);
                                        System.out.println("//CLASS 1 (KLS1) VALUE UPDATED");
                                        break;
                                    }
                                }
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//ENTER \"A\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CLASS 2": case "class 2": case "CLASS2": case "class2": case "KLS2": case "kls2":
                                if (player.getUserClass0().equals("KL0M")){
                                    System.out.println("//ERROR: PRIMARY CLASS MISSING");
                                } else if (player.getUserClass1().equals("KL1M")){
                                    System.out.println("//ERROR: SECONDARY CLASS MISSING");
                                } else {
                                    System.out.println("//PLEASE ENTER NEW VALUE FOR CLASS 2 (KLS2)");
                                    ShortDelay();
                                    System.out.println("//ENTER \".\" TO CANCEL EDIT");
                                    inputValue = input.nextLine();
                                    if (inputValue.equals(".")){
                                        System.out.println("//EDIT CANCELLED");
                                        break;
                                    } else {
                                        player.setUserClass2(inputValue);
                                        System.out.println("//CLASS 2 (KLS2) VALUE UPDATED");
                                        break;
                                    }
                                }
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//ENTER \"A\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CLASS 3": case "class 3": case "CLASS3": case "class3": case "KLS3": case "kls3":
                                if (player.getUserClass0().equals("KL0M")){
                                    System.out.println("//ERROR: PRIMARY CLASS MISSING");
                                } else if (player.getUserClass1().equals("KL1M")){
                                    System.out.println("//ERROR: SECONDARY CLASS MISSING");
                                } else if (player.getUserClass2().equals("KL2M")){
                                    System.out.println("//ERROR: TERTIARY CLASS MISSING");
                                } else {
                                    System.out.println("//PLEASE ENTER NEW VALUE FOR CLASS 3 (KLS2)");
                                    ShortDelay();
                                    System.out.println("//ENTER \".\" TO CANCEL EDIT");
                                    inputValue = input.nextLine();
                                    if (inputValue.equals(".")){
                                        System.out.println("//EDIT CANCELLED");
                                        break;
                                    } else {
                                        player.setUserClass3(inputValue);
                                        System.out.println("//CLASS 3 (KLS2) VALUE UPDATED");
                                        break;
                                    }
                                }
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//ENTER \"A\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "STRENGTH": case "strength": case "STRK": case "strk":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR STRENGTH (STRK)");
                                inputValue = input.nextLine();
                                int newStrength;
                                if (inputValue.equals(".")){
                                    System.out.println("//EDIT CANCELLED");
                                    break;
                                } else {
                                    try {
                                        newStrength = Integer.parseInt(inputValue);
                                    } catch (NumberFormatException e){
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        input2 = new Scanner(System.in);
                                        while (!input2.hasNextInt()){
                                            if (input2.nextLine().equals(".")){
                                                System.out.println("//EDIT CANCELLED");
                                                secondTierEditCancel = true;
                                                break;
                                            } else {
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                            }
                                            input2 = new Scanner(System.in);
                                        }
                                        if (secondTierEditCancel){
                                            break;
                                        } else {
                                            newStrength = input2.nextInt();
                                        }
                                    }
                                    player.setUserStrength(Integer.toString(newStrength));
                                }
                                System.out.println("//STRENGTH (STRK) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//ENTER \"A\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "DEXTERITY": case "dexterity": case "GSKT": case "gskt":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR DEXTERITY (GSKM)");
                                inputValue = input.nextLine();
                                int newDexterity;
                                if (inputValue.equals(".")){
                                    System.out.println("//EDIT CANCELLED");
                                    break;
                                } else {
                                    try {
                                        newDexterity = Integer.parseInt(inputValue);
                                    } catch (NumberFormatException e){
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        input2 = new Scanner(System.in);
                                        while (!input2.hasNextInt()){
                                            if (input2.nextLine().equals(".")){
                                                System.out.println("//EDIT CANCELLED");
                                                secondTierEditCancel = true;
                                                break;
                                            } else {
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                            }
                                            input2 = new Scanner(System.in);
                                        }
                                        if (secondTierEditCancel){
                                            break;
                                        } else {
                                            newDexterity = input2.nextInt();
                                        }
                                    }
                                    player.setUserDexterity(Integer.toString(newDexterity));
                                }
                                System.out.println("//DEXTERITY (GSKT) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//ENTER \"A\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CONSTITUTION": case "constitution": case "VFSM": case "vfsm":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR CONSTITUTION (VFSM)");
                                inputValue = input.nextLine();
                                int newConstitution;
                                if (inputValue.equals(".")){
                                    System.out.println("//EDIT CANCELLED");
                                    break;
                                } else {
                                    try {
                                        newConstitution = Integer.parseInt(inputValue);
                                    } catch (NumberFormatException e){
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        input2 = new Scanner(System.in);
                                        while (!input2.hasNextInt()){
                                            if (input2.nextLine().equals(".")){
                                                System.out.println("//EDIT CANCELLED");
                                                secondTierEditCancel = true;
                                                break;
                                            } else {
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                            }
                                            input2 = new Scanner(System.in);
                                        }
                                        if (secondTierEditCancel){
                                            break;
                                        } else {
                                            newConstitution = input2.nextInt();
                                        }
                                    }
                                    player.setUserConstitution(Integer.toString(newConstitution));
                                }
                                int newCurrentHealth = Integer.parseInt(player.getUserCurrentHealth()) + (modCalc(Integer.parseInt(player.getUserConstitution()))*10);
                                int newMaxHealth = Integer.parseInt(player.getUserMaxHealth()) + (modCalc(Integer.parseInt(player.getUserConstitution()))*10);
                                player.setUserCurrentHealth(Integer.toString(newCurrentHealth));
                                player.setUserMaxHealth(Integer.toString(newMaxHealth));
                                System.out.println("//CONSTITUTION (VFSM) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//ENTER \"A\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "INTELLIGENCE": case "intelligence": case "INTG": case "intg":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR INTELLIGENCE (INTG)");
                                inputValue = input.nextLine();
                                int newIntelligence;
                                if (inputValue.equals(".")){
                                    System.out.println("//EDIT CANCELLED");
                                    break;
                                } else {
                                    try {
                                        newIntelligence = Integer.parseInt(inputValue);
                                    } catch (NumberFormatException e){
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        input2 = new Scanner(System.in);
                                        while (!input2.hasNextInt()){
                                            if (input2.nextLine().equals(".")){
                                                System.out.println("//EDIT CANCELLED");
                                                secondTierEditCancel = true;
                                                break;
                                            } else {
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                            }
                                            input2 = new Scanner(System.in);
                                        }
                                        if (secondTierEditCancel){
                                            break;
                                        } else {
                                            newIntelligence = input2.nextInt();
                                        }
                                    }
                                    player.setUserIntelligence(Integer.toString(newIntelligence));
                                }
                                System.out.println("//INTELLIGENCE (INTG) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//ENTER \"A\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "WISDOM": case "wisdom": case "WSHT": case "wsht":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR WISDOM (WSHT)");
                                inputValue = input.nextLine();
                                int newWisdom;
                                if (inputValue.equals(".")){
                                    System.out.println("//EDIT CANCELLED");
                                    break;
                                } else {
                                    try {
                                        newWisdom = Integer.parseInt(inputValue);
                                    } catch (NumberFormatException e){
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        input2 = new Scanner(System.in);
                                        while (!input2.hasNextInt()){
                                            if (input2.nextLine().equals(".")){
                                                System.out.println("//EDIT CANCELLED");
                                                secondTierEditCancel = true;
                                                break;
                                            } else {
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                            }
                                            input2 = new Scanner(System.in);
                                        }
                                        if (secondTierEditCancel){
                                            break;
                                        } else {
                                            newWisdom = input2.nextInt();
                                        }
                                    }
                                    player.setUserWisdom(Integer.toString(newWisdom));
                                }
                                System.out.println("//WISDOM (WSHT) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//ENTER \"A\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "CHARISMA": case "charisma": case "CHRM": case "chrm":
                                System.out.println("//PLEASE ENTER NEW VALUE FOR CHARISMA (CHRM)");
                                inputValue = input.nextLine();
                                int newCharisma;
                                if (inputValue.equals(".")){
                                    System.out.println("//EDIT CANCELLED");
                                    break;
                                } else {
                                    try {
                                        newCharisma = Integer.parseInt(inputValue);
                                    } catch (NumberFormatException e){
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        input2 = new Scanner(System.in);
                                        while (!input2.hasNextInt()){
                                            if (input2.nextLine().equals(".")){
                                                System.out.println("//EDIT CANCELLED");
                                                secondTierEditCancel = true;
                                                break;
                                            } else {
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                            }
                                            input2 = new Scanner(System.in);
                                        }
                                        if (secondTierEditCancel){
                                            break;
                                        } else {
                                            newCharisma = input2.nextInt();
                                        }
                                    }
                                    player.setUserCharisma(Integer.toString(newCharisma));
                                }
                                System.out.println("//CHARISMA (CHRM) VALUE UPDATED");
                                ShortDelay();
                                System.out.println("//ENTER NEW FIELD TO CONTINUE EDITING");
                                ShortDelay();
                                System.out.println("//ENTER \"A\" TO REFRESH VITALS MENU");
                                ShortDelay();
                                break;
                            case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                                player.writePlayerData();
                                vitals( player );
                                break;
                            case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                                roll( player );
                                break;
                            case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                                mainMenu( player );
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
                                ShortDelay();
                                System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                                break;
                            case "#KVSCLE": //changes language to English
                                editOptionNotChosen = true;
                                player.setUserLanguage("ENGLISH");
                                System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                ShortDelay();
                                System.out.println("//INPUT \"A\" TO REFRESH");
                                break;
                            default:
                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                    System.out.println("//ERROR: INVALID INPUT");
                                }
                                break;
                        }
                    }
                case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                    player.writePlayerData();
                    vitals( player );
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
                    shutdown( player );
                    break;
                case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                    mainMenu( player );
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
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
        System.out.print("//ANALYZING USER DATA... ");
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

        boolean strMissing = player.getUserStrength().equals("STKM");
        boolean dexMissing = player.getUserDexterity().equals("GSKM");
        boolean conMissing = player.getUserConstitution().equals("VFSM");
        boolean intMissing = player.getUserIntelligence().equals("INTM");
        boolean wisMissing = player.getUserWisdom().equals("WSHM");
        boolean chaMissing = player.getUserCharisma().equals("CHRM");

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
            System.out.print("//COMPILING RESULTS... ");
            LongDelay();
            System.out.println("COMPLETE");
            ShortDelay();
            System.out.println( "//STRENGTH (STRN) SKILLS" );
            ShortDelay();
            System.out.println( ">ATHLETICS (STR0) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserStrength())) + proficiencyCalc( player,"ATHLETICS" ) + softwareCalc( player, "ATHLETICS" ) ) + " )");
            ShortDelay();
            System.out.println();

            System.out.println( "//DEXTERITY (DEXT) SKILLS" );
            ShortDelay();
            System.out.println( ">ACROBATICS (DEX0) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserDexterity())) + proficiencyCalc( player,"ACROBATICS" ) + softwareCalc( player, "ACROBATICS" ) ) + " )");
            NoDelay();
            System.out.println( ">SLEIGHT-OF-HAND (DEX1) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserDexterity())) + proficiencyCalc( player,"SLEIGHT-OF-HAND" ) + softwareCalc( player, "SLEIGHT-OF-HAND" ) ) + " )");
            NoDelay();
            System.out.println( ">STEALTH (DEX2) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserDexterity())) + proficiencyCalc( player,"STEALTH" ) + softwareCalc( player, "STEALTH" ) )  + " )");
            ShortDelay();
            System.out.println();

            System.out.println( "//INTELLIGENCE (INTL) SKILLS" );
            ShortDelay();
            System.out.println( ">ARCANA (INT0) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserIntelligence())) + proficiencyCalc( player,"ARCANA" )  + softwareCalc( player, "ARCANA" ) )  +" )");
            NoDelay();
            System.out.println( ">HISTORY (INT1) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserIntelligence())) + proficiencyCalc( player,"HISTORY" )   + softwareCalc( player, "HISTORY" ) ) +" )");
            NoDelay();
            System.out.println( ">INVESTIGATION (INT2) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserIntelligence())) + proficiencyCalc( player,"INVESTIGATION" )  + softwareCalc( player, "INVESTIGATION" ) )  +" )");
            NoDelay();
            System.out.println( ">NATURE (INT3) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserIntelligence())) + proficiencyCalc( player,"NATURE" )  + softwareCalc( player, "NATURE" ) )  +" )");
            NoDelay();
            System.out.println( ">RELIGION (INT4) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserIntelligence())) + proficiencyCalc( player,"RELIGION" )  + softwareCalc( player, "RELIGION" ) )  +" )");
            ShortDelay();
            System.out.println();

            System.out.println( "//WISDOM (WSDM) SKILLS" );
            ShortDelay();
            System.out.println( ">ANIMAL HANDLING (WSD0) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserWisdom())) + proficiencyCalc( player,"ANIMAL HANDLING" ) + softwareCalc( player, "ANIMAL HANDLING" ) )  +" )");
            NoDelay();
            System.out.println( ">INSIGHT (WSD1) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserWisdom())) + proficiencyCalc( player,"INSIGHT" ) + softwareCalc( player, "INSIGHT" ) )  +" )");
            NoDelay();
            System.out.println( ">MEDICINE (WSD2) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserWisdom())) + proficiencyCalc( player,"MEDICINE" ) + softwareCalc( player, "MEDICINE" ) )  +" )");
            NoDelay();
            System.out.println( ">PERCEPTION (WSD3) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserWisdom())) + proficiencyCalc( player,"PERCEPTION" ) + softwareCalc( player, "PERCEPTION" ) )  +" )");
            NoDelay();
            System.out.println( ">SURVIVAL (WSD4) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserWisdom())) + proficiencyCalc( player,"SURVIVAL" ) + softwareCalc( player, "SURVIVAL" ) )  +" )");
            ShortDelay();
            System.out.println();

            System.out.println( "//CHARISMA (CHRM) SKILLS" );
            ShortDelay();
            System.out.println( ">DECEPTION (CHR0) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserCharisma())) + proficiencyCalc( player,"DECEPTION" ) + softwareCalc( player, "DECEPTION" ) )  +" )");
            NoDelay();
            System.out.println( ">INTIMIDATION (CHR1) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserCharisma())) + proficiencyCalc( player,"INTIMIDATION" ) + softwareCalc( player, "INTIMIDATION" ) )  +" )");
            NoDelay();
            System.out.println( ">PERFORMANCE (CHR2) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserCharisma())) + proficiencyCalc( player,"PERFORMANCE" ) + softwareCalc( player, "PERFORMANCE" ) )  +" )");
            NoDelay();
            System.out.println( ">PERSUASION (CHR3) MODIFIER: R + ( " + ( modCalc(Integer.parseInt(player.getUserCharisma())) + proficiencyCalc( player,"PERSUASION" ) + softwareCalc( player, "PERSUASION" ) )  +" )");
            ShortDelay();
            System.out.println();

            System.out.println("//PROFICIENCIES");
            ShortDelay();
            if ( player.getUserBackgroundProficiency0().equals("HK0M") ){
                System.out.println(">BACKGROUND PROFICIENCY 0 (HKU0): [DATA MISSING]" );
            } else {
                System.out.println(">BACKGROUND PROFICIENCY 0 (HKU0): " + player.getUserBackgroundProficiency0());
            }
            NoDelay();
            if ( player.getUserBackgroundProficiency1().equals("HK1M") ){
                System.out.println(">BACKGROUND PROFICIENCY 1 (HKU1): [DATA MISSING]" );
            } else {
                System.out.println(">BACKGROUND PROFICIENCY 1 (HKU2): " + player.getUserBackgroundProficiency1());
            }
            NoDelay();
            if ( player.getUserSkillProficiency0().equals("FK0M") ) {
                System.out.println(">SKILL PROFICIENCY 0 (FTK0) : [DATA MISSING]" );
            } else {
                System.out.println(">SKILL PROFICIENCY 0 (FTK0) : " + player.getUserSkillProficiency0());
            }
            NoDelay();
            if ( player.getUserSkillProficiency1().equals("FK1M") ) {
                System.out.println(">SKILL PROFICIENCY 1 (FTK1) : [DATA MISSING]" );
            } else {
                System.out.println(">SKILL PROFICIENCY 1 (FTK1) : " + player.getUserSkillProficiency1());
            }
            NoDelay();
            if ( player.getUserSkillProficiency2().equals("FK2M") ){
                System.out.println(">SKILL PROFICIENCY 2 (FTK2) : [DATA MISSING]" );
            } else {
                System.out.println(">SKILL PROFICIENCY 2 (FTK2) : " + player.getUserSkillProficiency2());
            }
            NoDelay();
            if ( player.getUserSkillProficiency3().equals("FK3M") ){
                System.out.println(">SKILL PROFICIENCY 3 (FTK3) : [DATA MISSING]" );
            } else {
                System.out.println(">SKILL PROFICIENCY 3 (FTK3) : " + player.getUserSkillProficiency3());
            }
            ShortDelay();


        }
        boolean background0Missing = player.getUserBackgroundProficiency0().equals("HK0M");
        boolean background1Missing = player.getUserBackgroundProficiency1().equals("HK1M");
        boolean skill0Missing = player.getUserSkillProficiency0().equals("FK0M");
        boolean skill1Missing = player.getUserSkillProficiency1().equals("FK1M");
        boolean skill2Missing = player.getUserSkillProficiency2().equals("FK2M");
        boolean skill3Missing = player.getUserSkillProficiency3().equals("FK3M");

        if ( background0Missing || background1Missing || skill0Missing || skill1Missing || skill2Missing || skill3Missing ){
            MedDelay();
            System.out.println( "//WARNING: DATA MISSING ON ONE OR MORE USER PROFICIENCIES" );
            ShortDelay();
            System.out.println( "//PLEASE USE \"EDIT\" FUNCTION TO COMPLETE MISSING ENTRIES" );
            ShortDelay();
        }

        System.out.println( "> 0_ EDIT DATA [EDIT]" );
        NoDelay();
        System.out.println( "> A_ REFRESH [AKTL]" );
        NoDelay();
        System.out.println( "> ._ EXIT [AUSF]" );
        boolean skillOptionNotChosen = true;
        while ( skillOptionNotChosen ) {
            switch ( input.nextLine() ) {
                case "EDIT DATA": case "edit data": case "EDIT": case "edit": case "0":
                    System.out.println("// PLEASE SPECIFY VALUE TO EDIT");
                    boolean profiOptionNotChosen = true;
                    while ( profiOptionNotChosen ){
                        switch ( input.nextLine() ){
                            case "BACKGROUND0": case "background0": case "HKU0": case "hku0":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR BACKGROUND PROFICIENCY 0 (HKU0)" );
                                player.setUserBackgroundProficiency0( input.nextLine().toUpperCase() );
                                System.out.println( "//BACKGROUND PROFICIENCY 0 (HKU0) UPDATED");
                                ShortDelay();
                                break;
                            case "BACKGROUND1": case "background1": case "HKU1": case "hku1":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR BACKGROUND PROFICIENCY 1 (HKU1)" );
                                player.setUserBackgroundProficiency1( input.nextLine().toUpperCase() );
                                System.out.println( "//BACKGROUND PROFICIENCY 1 (HKU1) UPDATED");
                                ShortDelay();
                                break;
                            case "SKILL0": case "skill0": case "FTK0": case "ftk0":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 0 (FTK0)" );
                                player.setUserSkillProficiency0( input.nextLine().toUpperCase() );
                                System.out.println( "//SKILL PROFICIENCY 0 (FTK0) UPDATED");
                                ShortDelay();
                                break;
                            case "SKILL1": case "skill1": case "FTK1": case "ftk1":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 1 (FTK1)" );
                                player.setUserSkillProficiency1( input.nextLine().toUpperCase() );
                                System.out.println( "//SKILL PROFICIENCY 1 (FTK1) UPDATED");
                                ShortDelay();
                                break;
                            case "SKILL2": case "skill2": case "FTK2": case "ftk2":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 2 (FTK2)" );
                                player.setUserSkillProficiency2( input.nextLine().toUpperCase() );
                                System.out.println( "//SKILL PROFICIENCY 2 (FTK2) UPDATED");
                                ShortDelay();
                                break;
                            case "SKILL3": case "skill3": case "FTK3": case "ftk3":
                                System.out.println( "//PLEASE ENTER NEW VALUE FOR SKILL PROFICIENCY 3 (FTK3)" );
                                player.setUserSkillProficiency3( input.nextLine().toUpperCase() );
                                System.out.println( "//SKILL PROFICIENCY 3 (FTK3) UPDATED");
                                ShortDelay();
                                break;
                            case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                                skills( player );
                                break;
                            case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                                roll( player );
                                break;
                            case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                                System.out.println( "//SKILLS MENU EXITED" );
                                profiOptionNotChosen = false;
                                break;
                        }
                        System.out.println( "//ENTER NEW PROFICIENCY TO CONTINUE EDITING" );
                        ShortDelay();
                        System.out.println( "//ENTER \"A\" TO REFRESH SKILLS MENU" );
                        ShortDelay();
                        System.out.println( "//ENTER \".\" TO EXIT SKILLS EDITOR" );
                    }
                    break;
                case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                    skills( player );
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
                    break;
                case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                    vitals( player );
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    skillOptionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
                    }
                    break;
            }
        }
    }

    private static int modCalc( int abilityScore ) {
        int modifier;
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

    private static int proficiencyCalc( playerCharacter player, String skill ){
        int profiBonus = 0;

        boolean backgroundProficiency = player.getUserBackgroundProficiency0().equals(skill) || player.getUserBackgroundProficiency1().equals(skill);
        boolean skillProficiency = player.getUserSkillProficiency0().equals(skill) || player.getUserSkillProficiency1().equals(skill) || player.getUserSkillProficiency2().equals(skill) || player.getUserSkillProficiency3().equals(skill);

        if ( Integer.parseInt(player.getUserLevel()) <= 4 ){
            if ( backgroundProficiency || skillProficiency ){
                profiBonus = 2;
                return profiBonus;
            }
        } else if ( 4 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 8){
            if ( backgroundProficiency || skillProficiency ){
                profiBonus = 3;
                return profiBonus;
            }
        } else if ( 8 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 12){
            if ( backgroundProficiency || skillProficiency ){
                profiBonus = 4;
                return profiBonus;
            }
        } else if ( 12 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 16){
            if ( backgroundProficiency || skillProficiency ){
                profiBonus = 5;
                return profiBonus;
            }
        } else if ( 16 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 20){
            if ( backgroundProficiency || skillProficiency ){
                profiBonus = 6;
                return profiBonus;
            }
        } else if ( Integer.parseInt(player.getUserLevel()) < 20){
            if ( backgroundProficiency || skillProficiency ){
                profiBonus = 6 + ( ( Integer.parseInt(player.getUserLevel()) - 20 ) / 4 );
                return profiBonus;
            }
        }
        return profiBonus;
    }

    private static int softwareCalc ( playerCharacter player, String skill ){
        int softwareBonus = 0;

        boolean hasSoftwareEnhancement = player.getSoftware00().equals(skill + "+") || player.getSoftware01().equals(skill + "+") || player.getSoftware02().equals(skill + "+") || player.getSoftware03().equals(skill + "+") || player.getSoftware04().equals(skill + "+") || player.getSoftware05().equals(skill + "+") || player.getSoftware06().equals(skill + "+") || player.getSoftware07().equals(skill + "+") || player.getSoftware08().equals(skill + "+") || player.getSoftware09().equals(skill + "+");

        if ( Integer.parseInt(player.getUserLevel()) <= 4 ){
            if ( hasSoftwareEnhancement ) {
                softwareBonus = 2;
                return softwareBonus;
            }
        } else if ( 4 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 8){
            if ( hasSoftwareEnhancement ) {
                softwareBonus = 3;
                return softwareBonus;
            }
        } else if ( 8 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 12){
            if ( hasSoftwareEnhancement ) {
                softwareBonus = 4;
                return softwareBonus;
            }
        } else if ( 12 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 16){
            if ( hasSoftwareEnhancement ) {
                softwareBonus = 5;
                return softwareBonus;
            }
        } else if ( 16 < Integer.parseInt(player.getUserLevel()) && Integer.parseInt(player.getUserLevel()) <= 20){
            if ( hasSoftwareEnhancement ) {
                softwareBonus = 6;
                return softwareBonus;
            }
        } else if ( Integer.parseInt(player.getUserLevel()) < 20){
            if ( hasSoftwareEnhancement ) {
                softwareBonus = 6 + ( ( Integer.parseInt(player.getUserLevel()) - 20 ) / 4 );
                return softwareBonus;
            }
        }

        return softwareBonus;
    }

    private static int softwareCheck( playerCharacter player, String software ){
        int softwareIndex = -1;

        if (player.getSoftware00().equals(software)){
            softwareIndex = 0;
        }

        if (player.getSoftware01().equals(software)){
            softwareIndex = 1;
        }

        if (player.getSoftware02().equals(software)){
            softwareIndex = 2;
        }

        if (player.getSoftware03().equals(software)){
            softwareIndex = 3;
        }

        if (player.getSoftware04().equals(software)){
            softwareIndex = 4;
        }

        if (player.getSoftware05().equals(software)){
            softwareIndex = 5;
        }

        if (player.getSoftware06().equals(software)){
            softwareIndex = 6;
        }

        if (player.getSoftware07().equals(software)){
            softwareIndex = 7;
        }

        if (player.getSoftware08().equals(software)){
            softwareIndex = 8;
        }

        if (player.getSoftware09().equals(software)){
            softwareIndex = 9;
        }

        return softwareIndex;
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
        System.out.println( "> 0_ WEAPONS [WEAP]" );
        NoDelay();
        System.out.println( "> 1_ AMMUNITION [AMMO]" );
        NoDelay();
        System.out.println( "> 2_ EQUIPMENT [EQUP]" );
        NoDelay();
        System.out.println( "> 3_ MEDICAL [MEDS] " );
        NoDelay();
        System.out.println( "> 4_ MISCELLANEOUS [MISC] " );
        NoDelay();
        System.out.println( "> ._ EXIT [AUSF] " );
        NoDelay();
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "WEAPONS": case "weapons": case "WEAP": case "weap": case "0":
                    weapons( player );
                    break;
                case "AMMUNITION": case "ammunition": case "AMMO": case "ammo": case "1":
                    ammunition( player );
                    break;
                case "EQUIPMENT": case "equipment": case "EQUP": case "equp": case "2":
                    equipment ( player );
                    break;
                case "MEDICAL": case "medical": case "MEDS": case "meds": case "3":
                    medical ( player );
                    break;
                case "MISCELLANEOUS": case "miscellaneous": case "MISC": case "misc": case "4":
                    miscellaneous( player );
                    break;
                case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                    mainMenu( player );
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
                    shutdown ( player );
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
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
        ShortDelay();
        System.out.print( "//RETRIEVING WEAPON REGISTRY... " );

        File[] inventory = player.getPlayerInventory();
        File[] weapons = new File(String.valueOf(inventory[1])).listFiles(File::isDirectory);

        String[] tempWeaponData;
        File frameTag;
        if (weapons.length != 0) {
            MedDelay();
            System.out.println("COMPLETE");
            ShortDelay();
            for (int i = 0; i < weapons.length; i++) {
                System.out.print("> "+ i + "_ " + weapons[i].getName());
                frameTag = new File(weapons[i]+"\\weaponFrame.TAG");
                if (frameTag.exists()){
                    tempWeaponData = weaponRead(player, weapons[i].getName());
                    if (player.getPrimaryWeapon().equals(weapons[i].getName())){
                        System.out.print(" [EQUIPPED (PRIMARY)]");
                    } else {
                        System.out.print("");
                    }
                    if (player.getSecondaryWeapon().equals(weapons[i].getName())){
                        System.out.print(" [EQUIPPED (SECONDARY)]");
                    } else {
                        System.out.print("");
                    }
                    if (tempWeaponData[8].equals("INCOMPLETE")){
                        System.out.print(" [WARNING: ESSENTIAL COMPONENTS MISSING]");
                    } else {
                        System.out.print("");
                    }
                } else {
                    System.out.print(" [WARNING: FRAME TAG UNREADABLE]");
                }
                System.out.println();
                NoDelay();
            }
        }else{
            LongDelay();
            System.out.println("ERROR");
            ShortDelay();
            System.out.println("//NO ITEMS MATCHING FILTER FOUND IN INVENTORY");
        }
        ShortDelay();
        System.out.println("> A_ REFRESH [AKTL]");
        NoDelay();
        System.out.println("> ._ EXIT [AUSF]");
        String inputWeapon = input.nextLine();
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( inputWeapon ) {
                case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                    weapons (player);
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
                    shutdown( player );
                    break;
                case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                    inventory (player);
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    boolean weaponNotChosen = true;
                    int chosenWeaponIndex;
                    int lastInstanceOfQueriedWeapon = -1;
                    int instancesOfQueriedWeapon = 0;
                    while (weaponNotChosen){
                        try {
                            chosenWeaponIndex = Integer.parseInt(inputWeapon);
                        } catch ( Exception e0 ) {
                            lastInstanceOfQueriedWeapon = -1;
                            instancesOfQueriedWeapon = 0;
                            for (int i = 0; i < weapons.length; i++){
                                if (weapons[i].getName().contains(inputWeapon)){
                                    instancesOfQueriedWeapon ++;
                                    lastInstanceOfQueriedWeapon = i;
                                }
                            }
                            if (instancesOfQueriedWeapon > 1){
                                System.out.println("//ERROR: MULTIPLE INSTANCES OF SPECIFIED WEAPON IN INVENTORY");
                                ShortDelay();
                                for (int i = 0; i < weapons.length; i++){
                                    File itemTag = new File(weapons[i]+"\\weaponFrame.TAG");
                                    if (itemTag.exists()){
                                        System.out.println("> "+ i + "_ " + weapons[i].getName());
                                    } else {
                                        System.out.println("> "+ i + "_ " + weapons[i].getName() + " [WARNING: ITEM TAG UNREADABLE]");
                                    }
                                    NoDelay();
                                }
                                ShortDelay();
                                System.out.println("//PLEASE SPECIFY SINGLE INSTANCE OF WEAPON");
                                inputWeapon = input.nextLine();
                                break;
                            } else if (instancesOfQueriedWeapon == 0){
                                System.out.println("//ERROR: WEAPON NOT FOUND");
                                inputWeapon = input.nextLine();
                                break;
                            } else {
                                chosenWeaponIndex = lastInstanceOfQueriedWeapon;
                            }
                        }
                        if (chosenWeaponIndex < weapons.length && chosenWeaponIndex != -1){
                            frameTag = new File(weapons[chosenWeaponIndex]+"\\weaponFrame.TAG");
                            if (frameTag.exists()){
                                weaponNotChosen = false;
                                weaponDisp(player, weapons[chosenWeaponIndex].getName());
                            } else {
                                System.out.println("//ERROR: CANNOT READ WEAPON FRAME TAG");
                                ShortDelay();
                                System.out.println("//PLEASE SELECT ANOTHER WEAPON");
                                inputWeapon = input.nextLine();
                            }
                        } else {
                            System.out.println("//ERROR: WEAPON NOT FOUND");
                            inputWeapon = input.nextLine();
                        }
                    }

                    break;
            }
        }
    }

    private static void weaponDisp( playerCharacter player, String weaponName ) throws IOException, InterruptedException{
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
        System.out.print("//ANALYZING ITEM IDENTIFICATION TAG... ");
        String [] weaponData = weaponRead(player, weaponName);
        LongDelay();
        System.out.println("COMPLETE");
        ShortDelay();

        String weaponDisplayName = weaponData[0];
        weaponDisplayName = weaponDisplayName.replace("\\AE\\", "\u00C4");
        weaponDisplayName = weaponDisplayName.replace("\\OE\\", "\u00D6");
        weaponDisplayName = weaponDisplayName.replace("\\UE\\", "\u00DC");

        weaponDisplayName = weaponDisplayName.replace("\\ae\\", "\u00E4");
        weaponDisplayName = weaponDisplayName.replace("\\ee\\", "\u00EB");
        weaponDisplayName = weaponDisplayName.replace("\\ie\\", "\u00EF");
        weaponDisplayName = weaponDisplayName.replace("\\oe\\", "\u00F6");
        weaponDisplayName = weaponDisplayName.replace("\\ue\\", "\u00FC");

        for (int i = 0; i < weaponData.length; i++){
            if (weaponData[i] == null){
                weaponData[i] = "-1";
            }
        }

        System.out.print(weaponDisplayName);
        NoDelay();
        System.out.print(" | ");
        NoDelay();
        System.out.print("No. " + weaponData[1]);
        NoDelay();
        System.out.print(" | ");
        NoDelay();
        System.out.print(weaponData[2]);
        NoDelay();
        System.out.print(" | ");
        NoDelay();
        System.out.println("FR " + weaponData[3]);
        NoDelay();

        System.out.println();
        MedDelay();


        String manufacturerDisplayName = weaponData[4];
        manufacturerDisplayName = manufacturerDisplayName.replace("\\AE\\", "\u00C4");
        manufacturerDisplayName = manufacturerDisplayName.replace("\\OE\\", "\u00D6");
        manufacturerDisplayName = manufacturerDisplayName.replace("\\UE\\", "\u00DC");

        manufacturerDisplayName = manufacturerDisplayName.replace("\\ae\\", "\u00E4");
        manufacturerDisplayName = manufacturerDisplayName.replace("\\ee\\", "\u00EB");
        manufacturerDisplayName = manufacturerDisplayName.replace("\\ie\\", "\u00EF");
        manufacturerDisplayName = manufacturerDisplayName.replace("\\oe\\", "\u00F6");
        manufacturerDisplayName = manufacturerDisplayName.replace("\\ue\\", "\u00FC");
        System.out.println("MANUFACTURER: " + manufacturerDisplayName);
        NoDelay();

        String typeDisplayName = weaponData[6];
        typeDisplayName = typeDisplayName.replace("\\AE\\", "\u00C4");
        typeDisplayName = typeDisplayName.replace("\\OE\\", "\u00D6");
        typeDisplayName = typeDisplayName.replace("\\UE\\", "\u00DC");

        typeDisplayName = typeDisplayName.replace("\\ae\\", "\u00E4");
        typeDisplayName = typeDisplayName.replace("\\ee\\", "\u00EB");
        typeDisplayName = typeDisplayName.replace("\\ie\\", "\u00EF");
        typeDisplayName = typeDisplayName.replace("\\oe\\", "\u00F6");
        typeDisplayName = typeDisplayName.replace("\\ue\\", "\u00FC");
        System.out.println("WEAPON TYPE: " + typeDisplayName);
        NoDelay();

        String classDisplayName = weaponData[7];
        classDisplayName = classDisplayName.replace("\\AE\\", "\u00C4");
        classDisplayName = classDisplayName.replace("\\OE\\", "\u00D6");
        classDisplayName = classDisplayName.replace("\\UE\\", "\u00DC");

        classDisplayName = classDisplayName.replace("\\ae\\", "\u00E4");
        classDisplayName = classDisplayName.replace("\\ee\\", "\u00EB");
        classDisplayName = classDisplayName.replace("\\ie\\", "\u00EF");
        classDisplayName = classDisplayName.replace("\\oe\\", "\u00F6");
        classDisplayName = classDisplayName.replace("\\ue\\", "\u00FC");
        System.out.println("WEAPON CLASS: " + classDisplayName);
        NoDelay();

        String statusDisplayName = weaponData[8];
        statusDisplayName = statusDisplayName.replace("\\AE\\", "\u00C4");
        statusDisplayName = statusDisplayName.replace("\\OE\\", "\u00D6");
        statusDisplayName = statusDisplayName.replace("\\UE\\", "\u00DC");

        statusDisplayName = statusDisplayName.replace("\\ae\\", "\u00E4");
        statusDisplayName = statusDisplayName.replace("\\ee\\", "\u00EB");
        statusDisplayName = statusDisplayName.replace("\\ie\\", "\u00EF");
        statusDisplayName = statusDisplayName.replace("\\oe\\", "\u00F6");
        statusDisplayName = statusDisplayName.replace("\\ue\\", "\u00FC");
        System.out.println("WEAPON STATUS: " + statusDisplayName);
        NoDelay();
        System.out.println();
        MedDelay();

        // \/ DAMAGE OUTPUT / ACCURACY THRESHOLD INFO \/
        if (weaponData[17].equals("N/A")){
            System.out.println("MAXIMUM DAMAGE OUTPUT: " + weaponData[11]);
            NoDelay();
            System.out.println("EFFECTIVE RANGE: " + weaponData[12]);
            NoDelay();
            System.out.println("ACCURACY THRESHOLD: " + weaponData[13]);
            NoDelay();
            System.out.println("CRITICAL HIT CAPABILITY: " + weaponData[14]);
            NoDelay();
            if (weaponData[14].equals("J")){
                System.out.println("CRITICAL HIT THRESHOLD: " + weaponData[15]);
                NoDelay();
                System.out.println("CRITICAL HIT MULTIPLIER: " + weaponData[16]);
                NoDelay();
            }
        } else {
            System.out.println("MAXIMUM PRIMARY ATTACK DAMAGE OUTPUT: " + weaponData[11]);
            NoDelay();
            System.out.println("PRIMARY ATTACK EFFECTIVE RANGE: " + weaponData[12]);
            NoDelay();
            System.out.println("PRIMARY ATTACK ACCURACY THRESHOLD: " + weaponData[13]);
            NoDelay();
            System.out.println("PRIMARY ATTACK CRITICAL HIT CAPABILITY: " + weaponData[14]);
            if (weaponData[14].equals("J")){
                System.out.println("PRIMARY ATTACK CRITICAL HIT THRESHOLD: " + weaponData[15]);
                NoDelay();
                System.out.println("PRIMARY ATTACK CRITICAL HIT MULTIPLIER: " + weaponData[16]);
                NoDelay();
            }
            System.out.println("MAXIMUM SECONDARY ATTACK DAMAGE OUTPUT: " + weaponData[18]);
            NoDelay();
            System.out.println("SECONDARY ATTACK EFFECTIVE RANGE: " + weaponData[19]);
            NoDelay();
            System.out.println("SECONDARY ATTACK ACCURACY THRESHOLD: " + weaponData[20]);
            NoDelay();
            System.out.println("SECONDARY ATTACK CRITICAL HIT CAPABILITY: " + weaponData[21]);
            NoDelay();
            if (weaponData[20].equals("J")){
                System.out.println("SECONDARY ATTACK CRITICAL HIT THRESHOLD: " + weaponData[22]);
                NoDelay();
                System.out.println("SECONDARY ATTACK CRITICAL HIT MULTIPLIER: " + weaponData[23]);
                NoDelay();
            }
        }

        // /\ DAMAGE OUTPUT / ACCURACY THRESHOLD INFO /\

        System.out.println();
        MedDelay();

        // \/ RANGED / HYBRID WEAPONS ONLY \/
        if (weaponData[6].equals("RANGED") || weaponData[6].equals("HYBRID")){
            // \/ AMMUNITION / MAGAZINE INFO \/
            if (!weaponData[17].equals("RANGED")){
                System.out.println("AMMUNITION CALIBER: " + weaponData[24]);
                NoDelay();
                if (weaponData[24].equals("Electricity")){
                    System.out.println("CURRENT CAPACITOR STATUS: " + weaponData[32]);
                    NoDelay();
                    System.out.println("CURRENT CAPACITOR LOAD: " + Double.parseDouble(weaponData[33]) + " / " + Double.parseDouble(weaponData[34]) + " " + weaponData[29]);
                    NoDelay();
                    System.out.println("CURRENT BATTERY TYPE: " + weaponData[26]);
                    NoDelay();
                    System.out.println("CURRENT BATTERY CHARGE: " + Double.parseDouble(weaponData[27]) + " / " + Double.parseDouble(weaponData[28]) + " " + weaponData[29]);
                    NoDelay();
                } else {
                    System.out.println("CURRENT CHAMBER STATUS: " + weaponData[32]);
                    NoDelay();
                    System.out.println("CURRENT CHAMBER LOAD: " + (int)Double.parseDouble(weaponData[33]) + " / " + (int)Double.parseDouble(weaponData[34]) + " " + weaponData[25] + " " + weaponData[29]);
                    NoDelay();
                    System.out.println("CURRENT MAGAZINE TYPE: " + weaponData[26]);
                    NoDelay();
                    System.out.println("CURRENT MAGAZINE LOAD: " + (int)Double.parseDouble(weaponData[27]) + " / " + (int)Double.parseDouble(weaponData[28]) + " " + weaponData[25] + " " + weaponData[29]);
                    NoDelay();
                }
            } else {
                System.out.println("PRIMARY AMMUNITION CALIBER: " + weaponData[24]);
                NoDelay();
                if (weaponData[24].equals("Electricity")){
                    System.out.println("CURRENT PRIMARY CAPACITOR STATUS: " + weaponData[32]);
                    NoDelay();
                    System.out.println("CURRENT PRIMARY CAPACITOR LOAD: " + Double.parseDouble(weaponData[33]) + " / " + Double.parseDouble(weaponData[34]) + " " + weaponData[29]);
                    NoDelay();
                    System.out.println("CURRENT PRIMARY BATTERY TYPE: " + weaponData[26]);
                    NoDelay();
                    System.out.println("CURRENT PRIMARY BATTERY CHARGE: " + Double.parseDouble(weaponData[27]) + " / " + Double.parseDouble(weaponData[28]) + " " + weaponData[29]);
                    NoDelay();
                } else {
                    System.out.println("CURRENT PRIMARY CHAMBER STATUS: " + weaponData[32]);
                    NoDelay();
                    System.out.println("CURRENT PRIMARY CHAMBER LOAD: " + (int)Double.parseDouble(weaponData[33]) + " / " + (int)Double.parseDouble(weaponData[34]) + " " + weaponData[25] + " " + weaponData[29]);
                    NoDelay();
                    System.out.println("CURRENT PRIMARY MAGAZINE TYPE: " + weaponData[26]);
                    NoDelay();
                    System.out.println("CURRENT PRIMARY MAGAZINE LOAD: " + (int)Double.parseDouble(weaponData[27]) + " / " + (int)Double.parseDouble(weaponData[28]) + " " + weaponData[25] + " " + weaponData[29]);
                    NoDelay();
                }
                System.out.println("SECONDARY AMMUNITION CALIBER: " + weaponData[37]);
                NoDelay();
                if (weaponData[24].equals("Electricity")){
                    System.out.println("CURRENT SECONDARY CAPACITOR STATUS: " + weaponData[45]);
                    NoDelay();
                    System.out.println("CURRENT SECONDARY CAPACITOR LOAD: " + Double.parseDouble(weaponData[46]) + " / " + Double.parseDouble(weaponData[47]) + " " + weaponData[42]);
                    NoDelay();
                    System.out.println("CURRENT SECONDARY BATTERY TYPE: " + weaponData[39]);
                    NoDelay();
                    System.out.println("CURRENT SECONDARY BATTERY CHARGE: " + Double.parseDouble(weaponData[40]) + " / " + Double.parseDouble(weaponData[41]) + " " + weaponData[42]);
                    NoDelay();
                } else {
                    System.out.println("CURRENT SECONDARY CHAMBER STATUS: " + weaponData[45]);
                    NoDelay();
                    System.out.println("CURRENT SECONDARY CHAMBER LOAD: " + (int)Double.parseDouble(weaponData[46]) + " / " + (int)Double.parseDouble(weaponData[47]) + " " + weaponData[38] + " " + weaponData[42]);
                    NoDelay();
                    System.out.println("CURRENT SECONDARY MAGAZINE TYPE: " + weaponData[39]);
                    NoDelay();
                    System.out.println("CURRENT SECONDARY MAGAZINE LOAD: " + (int)Double.parseDouble(weaponData[40]) + " / " + (int)Double.parseDouble(weaponData[41]) + " " + weaponData[38] + " " + weaponData[42]);
                    NoDelay();
                }
            }
            // /\ AMMUNITION / MAGAZINE INFO /\

            System.out.println();
            MedDelay();

            // \/ TRIGGER GROUP INFO \/

            if (!weaponData[5].contains("[RECEIVER (SECONDARY)]")){
                System.out.println("POSSIBLE FIREMODES: " + weaponData[30]);
                NoDelay();
                System.out.println("CURRENT FIREMODE: " + weaponData[31]);
                NoDelay();
                if (weaponData[30].contains("[FULL-AUTO]") || weaponData[30].contains("[SEMI-AUTO]") || weaponData[30].contains("[BURST]")){
                    System.out.println("MINIMUM BURST LENGTH: " + weaponData[35]);
                    NoDelay();
                    System.out.println("MAXIMUM BURST LENGTH: " + weaponData[36]);
                    NoDelay();
                }
            } else {
                System.out.println("POSSIBLE PRIMARY FIREMODES: " + weaponData[30]);
                NoDelay();
                System.out.println("CURRENT PRIMARY FIREMODE: " + weaponData[31]);
                NoDelay();
                if (weaponData[30].contains("[FULL-AUTO]") || weaponData[30].contains("[SEMI-AUTO]") || weaponData[30].contains("[BURST]")){
                    System.out.println("MINIMUM PRIMARY BURST LENGTH: " + weaponData[35]);
                    NoDelay();
                    System.out.println("MAXIMUM PRIMARY BURST LENGTH: " + weaponData[36]);
                    NoDelay();
                }
                System.out.println("POSSIBLE SECONDARY FIREMODES: " + weaponData[43]);
                NoDelay();
                System.out.println("CURRENT SECONDARY FIREMODE: " + weaponData[44]);
                NoDelay();
                if (weaponData[43].contains("[FULL-AUTO]") || weaponData[43].contains("[SEMI-AUTO]") || weaponData[43].contains("[BURST]")){
                    System.out.println("MINIMUM SECONDARY BURST LENGTH: " + weaponData[48]);
                    NoDelay();
                    System.out.println("MAXIMUM SECONDARY BURST LENGTH: " + weaponData[49]);
                    NoDelay();
                }
            }
        }
        // /\ RANGED / HYBRID WEAPONS ONLY /\

        String weaponDisplayGeneralDescription = weaponData[50];
        weaponDisplayGeneralDescription = weaponDisplayGeneralDescription.replace("\\AE\\", "\u00C4");
        weaponDisplayGeneralDescription = weaponDisplayGeneralDescription.replace("\\OE\\", "\u00D6");
        weaponDisplayGeneralDescription = weaponDisplayGeneralDescription.replace("\\UE\\", "\u00DC");

        weaponDisplayGeneralDescription = weaponDisplayGeneralDescription.replace("\\ae\\", "\u00E4");
        weaponDisplayGeneralDescription = weaponDisplayGeneralDescription.replace("\\ee\\", "\u00EB");
        weaponDisplayGeneralDescription = weaponDisplayGeneralDescription.replace("\\ie\\", "\u00EF");
        weaponDisplayGeneralDescription = weaponDisplayGeneralDescription.replace("\\oe\\", "\u00F6");
        weaponDisplayGeneralDescription = weaponDisplayGeneralDescription.replace("\\ue\\", "\u00FC");

        weaponDisplayGeneralDescription = weaponDisplayGeneralDescription.replace("\\t\\", "\t");

        String weaponDisplayDetailedDescription = weaponData[51];
        weaponDisplayDetailedDescription = weaponDisplayDetailedDescription.replace("\\AE\\", "\u00C4");
        weaponDisplayDetailedDescription = weaponDisplayDetailedDescription.replace("\\OE\\", "\u00D6");
        weaponDisplayDetailedDescription = weaponDisplayDetailedDescription.replace("\\UE\\", "\u00DC");

        weaponDisplayDetailedDescription = weaponDisplayDetailedDescription.replace("\\ae\\", "\u00E4");
        weaponDisplayDetailedDescription = weaponDisplayDetailedDescription.replace("\\ee\\", "\u00EB");
        weaponDisplayDetailedDescription = weaponDisplayDetailedDescription.replace("\\ie\\", "\u00EF");
        weaponDisplayDetailedDescription = weaponDisplayDetailedDescription.replace("\\oe\\", "\u00F6");
        weaponDisplayDetailedDescription = weaponDisplayDetailedDescription.replace("\\ue\\", "\u00FC");

        weaponDisplayDetailedDescription = weaponDisplayDetailedDescription.replace("\\t\\", "\t");
        weaponDisplayDetailedDescription = weaponDisplayDetailedDescription.replace("\\n\\", "\n\n");

        NoDelay();
        System.out.println();
        NoDelay();
        System.out.println("GENERAL DESCRIPTION: ");
        NoDelay();
        System.out.println();
        ShortDelay();

        System.out.println(weaponDisplayGeneralDescription);

        NoDelay();
        System.out.println();
        NoDelay();
        System.out.println("DETAILED DESCRIPTION: ");
        NoDelay();
        System.out.println();
        ShortDelay();

        System.out.println(weaponDisplayDetailedDescription);
        System.out.println();

        if (weaponData[17].equals("N/A")){
            if (!weaponData[52].equals("$")){
                System.out.println("SPECIAL PROPERTIES: ");
                if (weaponData[52].contains("[SILENT]")) {
                    System.out.println(">SILENT | This weapon is near-silent and can be used with minimal risk of detection.");
                    NoDelay();
                    if (weaponData[52].contains("[SILENT]") && (player.getSpecialProperties().contains("[CLOAK AND DAGGER]") || softwareCheck(player, "[CLOAK AND DAGGER]") != -1)) {
                        System.out.println("\t>CLOAK AND DAGGER | Any attacks made with this weapon that have a hit roll at/above ACCT instantly kill while the user is undetected.");
                        NoDelay();
                    }
                }
                if (weaponData[52].contains("[LASER]")) {
                    System.out.println(">LASER | This weapon fires a laser beam capable of penetrating thin metal sheets.");
                    NoDelay();
                }
            }
        } else {
            if (!weaponData[52].equals("$")){
                // \/ PRIMARY ATTACK SPECIAL PROPERTIES \/
                System.out.println("PRIMARY ATTACK SPECIAL PROPERTIES: ");
                if (weaponData[52].contains("[SILENT]")){
                    System.out.println(">SILENT | This weapon's primary fire is near-silent and can be used with minimal risk of detection.");
                    NoDelay();
                    if (weaponData[52].contains("[SILENT]") && (player.getSpecialProperties().contains("[CLOAK AND DAGGER]") || softwareCheck(player, "[CLOAK AND DAGGER]") != -1)) {
                        System.out.println("\t>CLOAK AND DAGGER | Any attacks made with this weapon's primary fire that have a hit roll at/above ACCT instantly kill while the user is undetected.");
                        NoDelay();
                    }
                }
                if (weaponData[52].contains("[LASER]")) {
                    System.out.println(">LASER | This weapon fires a laser beam capable of penetrating thin metal sheets.");
                    NoDelay();
                }

                // /\ PRIMARY ATTACK SPECIAL PROPERTIES /\
            }
            if (!weaponData[53].equals("$")){
                // \/ SECONDARY ATTACK SPECIAL PROPERTIES \/
                System.out.println();
                NoDelay();
                System.out.println("SECONDARY ATTACK SPECIAL PROPERTIES: ");
                if (weaponData[53].contains("[SILENT]")){
                    System.out.println(">SILENT | This weapon is near-silent and can be used with minimal risk of detection.");
                    NoDelay();
                    if (weaponData[53].contains("[SILENT]") && (player.getSpecialProperties().contains("[CLOAK AND DAGGER]") || softwareCheck(player, "[CLOAK AND DAGGER]") != -1)) {
                        System.out.println("\t>CLOAK AND DAGGER | Any attacks made with this weapon's secondary fire that have a hit roll at/above ACCT instantly kill while the user is undetected.");
                        NoDelay();
                    }
                }
                if (weaponData[53].contains("[LASER]")) {
                    System.out.println(">LASER | This weapon fires a laser beam capable of penetrating thin metal sheets.");
                    NoDelay();
                }
                // /\ SECONDARY ATTACK SPECIAL PROPERTIES /\
            }
        }

        System.out.println();

        ShortDelay();
        System.out.println("//PLEASE CHOOSE COURSE OF ACTION");
        ShortDelay();
        System.out.println( "> 0_ EQUIP [ASRÜ]" );
        NoDelay();
        System.out.println( "> 1_ UNEQUIP [ENTF]" );
        NoDelay();
        System.out.println( "> 2_ RENAME [RNAM]");
        NoDelay();
        System.out.println( "> A_ REFRESH [AKTL]" );
        NoDelay();
        System.out.println( "> ._ EXIT [AUSF]" );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                    optionNotChosen = false;
                    weaponDisp( player, weaponName );
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
                    break;
                case "AUSRÜSTEN": case "ausrüsten": case "AUSRUSTEN": case "ausrusten": case "AUSRUESTEN": case "ausruesten": case "EQUIP": case "equip": case "ASRÜ": case "asrü": case"ASRUE": case "asrue": case "ASRU": case "asru": case "0":
                    if (player.getPrimaryWeapon().equals(weaponName) || player.getSecondaryWeapon().equals(weaponName)){
                        if (player.getPrimaryWeapon().equals(weaponName)){
                            System.out.println("//ERROR: WEAPON ALREADY EQUIPPED AS PRIMARY");
                            ShortDelay();
                            System.out.println("//UNEQUIP WEAPON? J/N");
                            boolean equipOptionNotChosen = true;
                            while ( equipOptionNotChosen ){
                                switch (input.nextLine()){
                                    case "J": case "j":
                                        player.setPrimaryWeapon("PWFM");
                                        System.out.println("//WEAPON UNEQUIPPED");
                                        ShortDelay();
                                        System.out.println("//INPUT \"A\" TO REFRESH MENU");
                                        equipOptionNotChosen = false;
                                        break;
                                    case "N": case "n":
                                        System.out.println("//WEAPON CHANGE CANCELLED");
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
                                        ShortDelay();
                                        System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                                        break;
                                    case "#KVSCLE": //changes language to English
                                        equipOptionNotChosen = true;
                                        player.setUserLanguage("ENGLISH");
                                        System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                        ShortDelay();
                                        System.out.println("//INPUT \"A\" TO REFRESH");
                                        break;
                                    default:
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        break;
                                }
                            }
                        } else {
                            System.out.println("//ERROR: WEAPON ALREADY EQUIPPED AS SECONDARY");
                            ShortDelay();
                            System.out.println("//UNEQUIP WEAPON? J/N");
                            boolean equipOptionNotChosen = true;
                            while ( equipOptionNotChosen ){
                                switch (input.nextLine()){
                                    case "J": case "j":
                                        player.setPrimaryWeapon("SWFM");
                                        System.out.println("//WEAPON UNEQUIPPED");
                                        ShortDelay();
                                        System.out.println("//INPUT \"A\" TO REFRESH MENU");
                                        equipOptionNotChosen = false;
                                        break;
                                    case "N": case "n":
                                        System.out.println("//WEAPON CHANGE CANCELLED");
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
                                        ShortDelay();
                                        System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                                        break;
                                    case "#KVSCLE": //changes language to English
                                        equipOptionNotChosen = true;
                                        player.setUserLanguage("ENGLISH");
                                        System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                        ShortDelay();
                                        System.out.println("//INPUT \"A\" TO REFRESH");
                                        break;
                                    default:
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        break;
                                }
                            }
                        }
                        break;
                    } else {
                        System.out.println("//ARE YOU SURE YOU WANT TO EQUIP THIS WEAPON? J/N");
                        boolean equipOptionNotChosen = true;
                        while ( equipOptionNotChosen ) {
                            switch ( input.nextLine() ) {
                                case "J": case "j":
                                    System.out.println("//SELECT SLOT TO EQUIP WEAPON");
                                    ShortDelay();
                                    System.out.println("> 0_ PRIMARY WEAPON [PRMR] ");
                                    NoDelay();
                                    System.out.println("> 1_ SECONDARY WEAPON [SKDR] ");
                                    boolean weaponNotEquipped = true;
                                    while (weaponNotEquipped){
                                        switch ( input.nextLine() ){
                                            case "PRIMÄR": case "primär": case "PRIMAR": case "primar": case "PRIMAER": case "primaer": case "PRMR": case "prmr": case "0":
                                                weaponNotEquipped = false;
                                                player.setPrimaryWeapon( weaponName );
                                                System.out.println("// WEAPON EQUIPPED AS PRIMARY WEAPON");
                                                ShortDelay();
                                                System.out.println("// INPUT \"A\" TO REFRESH MENU");
                                                break;
                                            case "SEKUNDÄR": case "sekundär": case "SEKUNDAR": case "sekundar": case "SEKUNDAER": case "sekundaer": case "SKDR": case "skdr": case "1":
                                                weaponNotEquipped = false;
                                                player.setSecondaryWeapon( weaponName );
                                                System.out.println("// WEAPON EQUIPPED AS SECONDARY WEAPON");
                                                ShortDelay();
                                                System.out.println("// INPUT \"A\" TO REFRESH MENU");
                                                break;
                                            default:
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                                break;
                                        }
                                    }
                                    equipOptionNotChosen = false;
                                    break;
                                case "N": case "n":
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
                                    ShortDelay();
                                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                                    break;
                                case "#KVSCLE": //changes language to English
                                    equipOptionNotChosen = true;
                                    player.setUserLanguage("ENGLISH");
                                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                    ShortDelay();
                                    System.out.println("//INPUT \"A\" TO REFRESH");
                                    break;
                                default:
                                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                    } else if (player.getUserLanguage().equals("ENGLISH")){
                                        System.out.println("//ERROR: INVALID INPUT");
                                    }
                                    break;
                            }
                        }
                    }
                    break;
                case "UNEQUIP": case "unequip": case "UEQP": case "ueqp": case "1":
                    if (!player.getPrimaryWeapon().equals(weaponName)){
                        System.out.println("//ERROR: WEAPON NOT EQUIPPED");
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
                                case "N": case "n": case ".":
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
                                    ShortDelay();
                                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                                    break;
                                case "#KVSCLE": //changes language to English
                                    equipOptionNotChosen = true;
                                    player.setUserLanguage("ENGLISH");
                                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                    ShortDelay();
                                    System.out.println("//INPUT \"A\" TO REFRESH");
                                    break;
                                default:
                                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                    } else if (player.getUserLanguage().equals("ENGLISH")){
                                        System.out.println("//ERROR: INVALID INPUT");
                                    }
                                    break;
                            }
                        }
                    }
                    break;
                case "RENAME": case "rename": case "2":
                    System.out.println("//PLEASE INPUT NEW NAME");
                    ShortDelay();
                    System.out.println("//REPLACE SPECIAL CHARACTERS WITH THE FOLLOWING");
                    ShortDelay();
                    System.out.println("\t > \u00C4 -> \\AE\\");
                    NoDelay();
                    System.out.println("\t > \u00D6 -> \\OE\\");
                    NoDelay();
                    System.out.println("\t > \u00DC -> \\UE\\");
                    NoDelay();
                    System.out.println("\t > \u00E4 -> \\ae\\");
                    NoDelay();
                    System.out.println("\t > \u00EB -> \\ee\\");
                    NoDelay();
                    System.out.println("\t > \u00EF -> \\ie\\");
                    NoDelay();
                    System.out.println("\t > \u00F6 -> \\oe\\");
                    NoDelay();
                    System.out.println("\t > \u00FC -> \\ue\\");
                    weaponData[0] = input.nextLine();
                    weaponWrite (player, weaponName, weaponData);
                    ShortDelay();
                    System.out.println("//WEAPON RENAMED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
                    optionNotChosen = false;
                    shutdown( player );
                    break;
                case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                    optionNotChosen = false;
                    weapons( player );
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
                    }
                    break;
            }
        }
    }



    private static String[] weaponRead( playerCharacter player, String weaponName ) throws IOException {
        /*
        weaponRead is used to pull information from the weapon directories within a player's inventory.

        This subroutine is called during weapon inspection, but also when a user wishes to switch their equipped weapon.

        Before anything else, the program locates the player's inventory.
         */

        File[] inventory = player.getPlayerInventory();

        /*
        Each weapon in the House of Mirrors TTRPG system is determined by a list of 55 attributes whose values are determined by the frame of the weapon itself as well as the components mounted to it.

        The list of attributes of a weapon (as well as their indices in the weaponData array) are as follows:

        00 - Name
        01 - Weapon Frame Database ID Number
        02 - Role
        03 - Familiarity Rating
        04 - Manufacturer
        05 - Component Slots
        06 - Weapon Type
        07 - Weapon Class
        08 - Weapon Status
        09 - Custom Name Flag
        10 - Primary Attack Type
        11 - Primary Base Damage
        12 - Primary Effective Range
        13 - Primary Accuracy Threshold
        14 - Primary Critical Hit Capability
        15 - Primary Critical Hit Threshold
        16 - Primary Critical Hit Multiplier
        17 - Secondary Attack Type
        18 - Secondary Base Damage
        19 - Secondary Effective Range
        20 - Secondary Accuracy Threshold
        21 - Secondary Critical Hit Capability
        22 - Secondary Critical Hit Threshold
        23 - Secondary Critical Hit Multiplier

        \/ RANGED / HYBRID ATTACKS ONLY \/
        24 - Primary Ammunition Caliber
        25 - Primary Ammunition Current Subtype
        26 - Primary Magazine Type
        27 - Primary Current Magazine Load
        28 - Primary Magazine Capacity
        29 - Primary Ammo Unit of Measurement
        30 - Primary Firemodes
        31 - Primary Current Firemode
        32 - Primary Chamber Current Status
        33 - Primary Chamber Current Load
        34 - Primary Chamber Capacity
        35 - Primary Minimum Burst Length
        36 - Primary Maximum Burst Length

        37 - Secondary Ammunition Caliber
        38 - Secondary Ammunition Current Subtype
        39 - Secondary Magazine Type
        40 - Secondary Magazine Current Load
        41 - Secondary Magazine Capacity
        42 - Secondary Ammo Unit of Measurement
        43 - Secondary Firemodes
        44 - Secondary Current Firemode
        45 - Secondary Chamber Current Status
        46 - Secondary Chamber Current Load
        47 - Secondary Chamber Capacity
        48 - Secondary Minimum Burst Length
        49 - Secondary Maximum Burst Length
        /\ RANGED / HYBRID ATTACKS ONLY /\

        50 - General Weapon Information
        51 - Detailed Weapon Information
        52 - Primary Special Properties
        53 - Secondary Special Properties

         */


        /*
        Before anything else, the DaVG will attempt to read the .TAG file of the Weapon Frame.

        The Weapon Frame determines the following attributes:
        00 - Name
        01 - Weapon Frame Database ID Number
        02 - Role
        03 - Familiarity Rating
        04 - Manufacturer
        05 - Component Slots
        06 - Weapon Type
        07 - Weapon Class
        08 - Weapon Status
        09 - Custom Name Flag
        10 - Primary Attack Type
        17 - Secondary Attack Type
        50 - General Weapon Information
        51 - Detailed Weapon Information

        Weapon Frames are defined by the following attributes:
        FR00 - Name
        FR01 - Weapon Frame Database ID Number
        FR02 - Role
        FR03 - Familiarity Rating
        FR04 - Manufacturer
        FR05 - Component Slots
        FR06 - Weapon Type
        FR07 - Weapon Class
        FR08 - Weapon Status
        FR09 - Custom Name Flag
        FR10 - Primary Attack Type
        FR11 - Secondary Attack Type
        FR12 - General Description
        FR13 - Detailed Description
         */
        String[] frameData = new String[14];

        BufferedReader frameScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\weaponFrame.TAG"));
        try {
            frameData = frameScanner.readLine().split("[~]");
            frameScanner.close();
        } catch ( IOException tagError ) {
            tagError.printStackTrace();
        }

        /*
        A weapon frame on its own, however, is entirely useless unless components are installed. The components themselves are what determine the attributes of the weapon.

        After scanning the weapon frame, the DaVG will then attempt to locate the various components of the weapon. Any components within the weapon's folder that do not match any of its attachment slots will be ignored.
         */

        File[] weaponParts = new File(inventory[1]+"\\"+weaponName).listFiles(File::isFile);
        if (weaponParts == null){
            weaponParts = new File[1];
        }
        BufferedReader componentScanner;

        // \/ RANGED / HYBRID WEAPON COMPONENTS \/


        // \/ FIREARMS \/
        /*
        Receivers are components of firearms and hybrids that act as the base of a firearm's operating mechanisms.

        The Primary Receiver determines the following attributes:
        24 - Primary Ammunition Caliber
        29 - Primary Ammo Unit of Measurement
        30 - Primary Firemodes
        32 - Primary Chamber Current Status
        33 - Primary Chamber Current Load
        34 - Primary Chamber Capacity

        The Primary Receiver also gives bonuses/penalties to the following attributes:
        11 - Primary Base Damage

        The Primary Receiver can also add properties to the following attributes:
        52 - Primary Special Properties

        If the weapon has a secondary fire function, the Secondary Receiver determines the following attributes:
        37 - Secondary Ammunition Caliber
        38 - Secondary Ammo Unit of Measurement
        43 - Secondary Firemodes
        45 - Secondary Chamber Current Status
        46 - Secondary Chamber Current Load
        47 - Secondary Chamber Capacity

        The Secondary Receiver also gives bonuses/penalties to the following attributes:
        18 - Secondary Base Damage

        The Secondary Receiver can also add properties to the following attributes:
        53 - Secondary Special Properties

        Receivers are determined by the following attributes:
        RC00 - Name
        RC01 - Weapon Compatibility List
        RC02 - Ammunition Caliber
        RC03 - Ammo Unit of Measurement
        RC04 - Chamber Current Status
        RC05 - Chamber Current Load
        RC06 - Chamber Capacity
        RC07 - Damage Modifier
        RC08 - Possible Firemodes
        RC09 - Special Properties
        RC10 - General Receiver Description
        RC11 - Detailed Receiver Description
         */

        boolean primaryReceiverMissing = false;
        String[] primaryReceiverData = new String[12];
        if (frameData[5].contains("[RECEIVER (PRIMARY)]")){
            int primaryReceiverIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("REC0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primaryReceiverData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryReceiverData[1].contains(frameData[1])){
                        primaryReceiverIndex = i;
                    }
                }
            }
            if (primaryReceiverIndex == -1){
                primaryReceiverMissing = true;
            }
        } else {
            primaryReceiverMissing = true;
        }
        //Some weapons have secondary receivers, such as the Eulengewehr, which combines a bolt-action rifle and a pump-action shotgun into one weapon.
        boolean secondaryReceiverMissing = false;
        String[] secondaryReceiverData = new String[12];
        if (frameData[5].contains("[RECEIVER (SECONDARY)]")){
            int secondaryReceiverIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("REC1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondaryReceiverData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondaryReceiverData[1].contains(frameData[1])){
                        secondaryReceiverIndex = i;
                    }
                }
            }
            if (secondaryReceiverIndex == -1){
                secondaryReceiverMissing = true;
            }
        } else {
            secondaryReceiverMissing = true;
        }

        /*
        Barrels are of firearms and hybrids that direct fired projectiles and help to control their flight.

        The Primary Barrel determines the following attributes:
        12 - Primary Effective Range
        13 - Primary Accuracy Threshold

        The Primary Barrel also gives bonuses/penalties to the following attributes:
        11 - Primary Base Damage

        The Primary Barrel can also add properties to the following attributes:
        52 - Primary Special Properties

        If the weapon has a secondary fire function, the Secondary Barrel also determines the following attributes:
        19 - Secondary Effective Range
        20 - Secondary Accuracy Threshold

        The Secondary Barrel also gives bonuses/penalties to the following attributes:
        18 - Secondary Base Damage

        The Secondary Barrel can also add properties to the following attributes:
        53 - Secondary Special Properties

        Barrels are determined by the following attributes:
        BR00 - Barrel Name
        BR01 - Weapon Compatibility List
        BR02 - Effective Range
        BR03 - Accuracy Threshold
        BR04 - Damage Modifier
        BR05 - Special Properties
        BR06 - General Barrel Description
        BR07 - Detailed Barrel Description
         */

        boolean primaryBarrelMissing = false;
        String[] primaryBarrelData = new String[8];
        if (frameData[5].contains("[BARREL (PRIMARY)]")){
            int primaryBarrelIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("BRL0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primaryBarrelData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryBarrelData[1].contains(frameData[1])){
                        primaryBarrelIndex = i;
                    }
                }
            }
            if (primaryBarrelIndex == -1){
                primaryBarrelMissing = true;
            }
        } else {
            primaryBarrelMissing = true;
        }
        //Some weapons have secondary barrels, such as the Eulengewehr, which combines a bolt-action rifle and a pump-action shotgun into one weapon.
        boolean secondaryBarrelMissing = false;
        String[] secondaryBarrelData = new String[8];
        if (frameData[5].contains("[BARREL (SECONDARY)]")){
            int secondaryBarrelIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("BRL1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondaryBarrelData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondaryBarrelData[1].contains(frameData[1])){
                        secondaryBarrelIndex = i;
                    }
                }
            }
            if (secondaryBarrelIndex == -1){
                secondaryBarrelMissing = true;
            }
        } else {
            secondaryBarrelMissing = true;
        }

        /*
        Magazines are components of firearms and hybrids that hold extra ammunition that is loaded when the weapon cycles its action.

        The Primary Magazine determines the following attributes:
        25 - Primary Ammunition Current Subtype
        26 - Primary Magazine Type
        27 - Primary Current Magazine Load
        28 - Primary Magazine Capacity

        The Primary Magazine can also add properties to the following attributes:
        52 - Primary Special Properties

        If the weapon has a secondary fire function, the Secondary Magazine determines the following attributes:
        38 - Secondary Ammunition Current Subtype
        39 - Secondary Magazine Type
        40 - Secondary Magazine Current Load
        41 - Secondary Magazine Capacity

        The Secondary Magazine can also add properties to the following attributes:
        53 - Secondary Special Properties

        Magazines are determined by the following attributes:
        MG00 - Name
        MG01 - Weapon Compatibility List
        MG02 - Current Ammo Subtype
        MG03 - Current Magazine Load
        MG04 - Magazine Capacity
        MG05 - General Magazine Description
        MG06 - Detailed Magazine Description
        */

        boolean primaryMagazineMissing = false;
        String[] primaryMagazineData = new String[7];
        if (frameData[5].contains("[MAGAZINE (PRIMARY)]")){
            int primaryMagazineIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("MGZ0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primaryMagazineData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryMagazineData[1].contains(frameData[1])){
                        primaryMagazineIndex = i;
                    }
                }
            }
            if (primaryMagazineIndex == -1){
                primaryMagazineMissing = true;
            }
        } else {
            primaryMagazineMissing = true;
        }

        boolean secondaryMagazineMissing = false;
        String[] secondaryMagazineData = new String[7];
        if (frameData[5].contains("[MAGAZINE (SECONDARY)]")){
            int secondaryMagazineIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("MGZ1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondaryMagazineData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondaryMagazineData[1].contains(frameData[1])){
                        secondaryMagazineIndex = i;
                    }
                }
            }
            if (secondaryMagazineIndex == -1){
                secondaryMagazineMissing = true;
            }
        } else {
            secondaryMagazineMissing = true;
        }

        /*
        Fire Control Systems consist of the trigger mechanisms of firearms and hybrids.

        The Fire Control System determines the following attributes:
        31 - Primary Current Firemode

        If the weapon has a secondary fire mechanism, the Fire Control System also determines the following:
        44 - Secondary Current Firemode

        Fire Control Systems are defined by the following attributes:
        FC00 - Name
        FC01 - Weapon Compatibility List
        FC02 - Possible Firemodes
        FC03 - Current Firemode
        FC04 - General Fire Control System Description
        FC05 - Detailed Fire Control System Description
         */

        boolean primaryFireControlSystemMissing = false;
        String[] primaryFireControlSystemData = new String[6];
        if (frameData[5].contains("[FIRE CONTROL SYSTEM (PRIMARY)]")){
            int primaryFireControlSystemIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("FCS0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primaryFireControlSystemData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryFireControlSystemData[1].contains(frameData[1])){
                        primaryFireControlSystemIndex = i;
                    }
                }
            }
            if (primaryFireControlSystemIndex == -1){
                primaryFireControlSystemMissing = true;
            }
        } else {
            primaryFireControlSystemMissing = true;
        }
        //Some weapons have secondary fire control systems, such as the Eulengewehr, which combines a bolt-action rifle and a pump-action shotgun into one weapon.
        boolean secondaryFireControlSystemMissing = false;
        String[] secondaryFireControlSystemData = new String[6];
        if (frameData[5].contains("[FIRE CONTROL SYSTEM (SECONDARY)]")){
            int secondaryFireControlSystemIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("FCS1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondaryFireControlSystemData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondaryFireControlSystemData[1].contains(frameData[1])){
                        secondaryFireControlSystemIndex = i;
                    }
                }
            }
            if (secondaryFireControlSystemIndex == -1){
                secondaryFireControlSystemMissing = true;
            }
        } else {
            secondaryFireControlSystemMissing = true;
        }

        /*
        Bolts travel back and forth within the receiver of a firearm or hybrid weapon and help extract spent cartridges while reinserting fresh ones.

        The Primary Bolt determines the following attributes:
        35 - Primary Minimum Burst Length
        36 - Primary Maximum Burst Length

        The Primary Bolt also gives bonuses/penalties to the following attributes:
        13 - Primary Accuracy Threshold

        If the weapon has a secondary fire function, the Secondary Bolt determines the following attributes:
        48 - Secondary Minimum Burst Length
        49 - Secondary Maximum Burst Length

        The Secondary Bolt also gives bonuses/penalties to the following attributes:
        20 - Secondary Accuracy Threshold

        Bolts are determined by the following attributes:
        BT00 - Name
        BT01 - Weapon Compatibility List
        BT02 - Minimum Burst Length
        BT03 - Maximum Burst Length
        BT04 - Accuracy Threshold Modifier
        BT05 - General Bolt Description
        BT06 - Detailed Bolt Description
         */

        boolean primaryBoltMissing = false;
        String[] primaryBoltData = new String[7];
        if (frameData[5].contains("[BOLT (PRIMARY)]")){
            int primaryBoltIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("BLT0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primaryBoltData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryBoltData[1].contains(frameData[1])){
                        primaryBoltIndex = i;
                    }
                }
            }
            if (primaryBoltIndex == -1){
                primaryBoltMissing = true;
            }
        } else {
            primaryBoltMissing = true;
        }
        //Some weapons have secondary bolts, such as the Eulengewehr, which combines a bolt-action rifle and a pump-action shotgun into one weapon.
        boolean secondaryBoltMissing = false;
        String[] secondaryBoltData = new String[7];
        if (frameData[5].contains("[BOLT (SECONDARY)]")){
            int secondaryBoltIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("BLT1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondaryBoltData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondaryBoltData[1].contains(frameData[1])){
                        secondaryBoltIndex = i;
                    }
                }
            }
            if (secondaryBoltIndex == -1){
                secondaryBoltMissing = true;
            }
        } else {
            secondaryBoltMissing = true;
        }

        /*
        Heat Dispersion Systems are found on energy weapons such as lasers and serve to dissipate the heat generated by the weapon during firing.

        The Primary Heat Dispersion System determines the following attributes:
        35 - Primary Minimum Burst Length
        36 - Primary Maximum Burst Length

        If the weapon has a secondary fire function, the Secondary Heat Dispersion System determines the following attributes:
        48 - Secondary Minimum Burst Length
        49 - Secondary Maximum Burst Length

        Bolts are determined by the following attributes:
        HD00 - Name
        HD01 - Weapon Compatibility List
        HD02 - Minimum Burst Length
        HD03 - Maximum Burst Length
        HD04 - General Bolt Description
        HD05 - Detailed Bolt Description
         */

        boolean primaryHeatDispersionSystemMissing = false;
        String[] primaryHeatDispersionSystemData = new String[6];
        if (frameData[5].contains("[HEAT DISPERSION SYSTEM (PRIMARY)]")){
            int primaryHeatDispersionSystemIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("HDS0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primaryHeatDispersionSystemData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryHeatDispersionSystemData[1].contains(frameData[1])){
                        primaryHeatDispersionSystemIndex = i;
                    }
                }
            }
            if (primaryHeatDispersionSystemIndex == -1){
                primaryHeatDispersionSystemMissing = true;
            }
        } else {
            primaryHeatDispersionSystemMissing = true;
        }
        //Some weapons have secondary heat dispersion systems, such as the DkG-61, which combines an assault rifle and laser rifle into one weapon.
        boolean secondaryHeatDispersionSystemMissing = false;
        String[] secondaryHeatDispersionSystemData = new String[6];
        if (frameData[5].contains("[HEAT DISPERSION SYSTEM (SECONDARY)]")){
            int secondaryHeatDispersionSystemIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("HDS1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondaryHeatDispersionSystemData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondaryHeatDispersionSystemData[1].contains(frameData[1])){
                        secondaryHeatDispersionSystemIndex = i;
                    }
                }
            }
            if (secondaryHeatDispersionSystemIndex == -1){
                secondaryHeatDispersionSystemMissing = true;
            }
        } else {
            secondaryHeatDispersionSystemMissing = true;
        }

        /*
        Grips are the primary points of contact for the user's hands and serve to give the user a means by which they can securely hold the weapon.

        The Grip gives bonuses/penalties to the following attributes:
        13 - Primary Accuracy Threshold

        If the weapon has a secondary fire function, the Grip also gives bonuses/penalties to the following attributes:
        20 - Secondary Accuracy Threshold

        Grips are defined by the following attributes:
        GR00 - Name
        GR01 - Weapon Compatibiilty List
        GR02 - Primary Accuracy Threshold Modifier
        GR03 - Secondary Accuracy Threshold Modifier (If Applicable)
        GR04 - General Grip Description
        GR05 - Detailed Grip Description

        SPECIAL NOTES:
        > Grips work a bit differently than other components, as primary AND secondary grips affect BOTH accuracy thresholds.
         */

        boolean primaryGripMissing = false;
        String[] primaryGripData = new String[6];
        if (frameData[5].contains("[GRIP (PRIMARY)]")){
            int primaryGripIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("GRP0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primaryGripData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryGripData[1].contains(frameData[1])){
                        primaryGripIndex = i;
                    }
                }
            }
            if (primaryGripIndex == -1){
                primaryGripMissing = true;
            }
        } else {
            primaryGripMissing = true;
        }

        boolean secondaryGripMissing = false;
        String[] secondaryGripData = new String[6];
        if (frameData[5].contains("[GRIP (SECONDARY)]")){
            int secondaryGripIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("GRP1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondaryGripData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondaryGripData[1].contains(frameData[1])){
                        secondaryGripIndex = i;
                    }
                }
            }
            if (secondaryGripIndex == -1){
                secondaryGripMissing = true;
            }
        } else {
            secondaryGripMissing = true;
        }

        /*
        Buttstocks, commonly known as Stocks, give a firearm/hybrid wielder the ability to brace the weapon against their shoulder for better aiming.

        The Stock gives bonuses/penalties to the following attributes:
        13 - Primary Accuracy Threshold

        If the weapon has a secondary fire function, the Grip also gives bonuses/penalties to the following attributes:
        20 - Secondary Accuracy Threshold

        Stocks are defined by the following attributes:
        ST00 - Name
        ST01 - Weapon Compatibiilty List
        ST02 - Primary Accuracy Threshold Modifier
        ST03 - Secondary Accuracy Threshold Modifier (If Applicable)
        ST04 - General Stock Description
        ST05 - Detailed Stock Description

        SPECIAL NOTES:
        > Stocks work a bit differently than other components, as the stock affects BOTH accuracy thresholds.
        > Weapons can only have 1 stock.
         */

        boolean stockMissing = false;
        String[] stockData = new String[6];
        if (frameData[5].contains("[STOCK]")){
            int stockIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("STCK")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        stockData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryGripData[1].contains(frameData[1])){
                        stockIndex = i;
                    }
                }
            }
            if (stockIndex == -1){
                stockMissing = true;
            }
        } else {
            stockMissing = true;
        }

        /*
        Sights help the wielder of a firearm or hybrid to aim the projectile being fired, and can consist of something as simple as iron sights to as complex as a holographic projection sight.

        The Primary Sights determine the following attributes:
        14 - Primary Critical Hit Capability
        15 - Primary Critical Hit Threshold
        16 - Primary Critical Hit Multiplier

        The Primary Sights also give bonuses/penalties to the following attributes:
        13 - Primary Accuracy Threshold

        Primary Sights can also add properties to the following attributes:
        52 - Primary Special Properties

        If the weapon has a secondary fire function, the Secondary Sights determine the following attributes:
        21 - Secondary Critical Hit Capability
        22 - Secondary Critical Hit Threshold
        23 - Secondary Critical Hit Multiplier

        The Secondary Sights also give bonuses/penalties to the following attributes:
        20 - Secondary Accuracy Threshold

        Secondary Sights can also add properties to the following attributes:
        53 - Primary Special Properties

        Sights are defined by the following attributes:
        SG00 - Name
        SG01 - Weapon Compatibility List
        SG02 - Critical Hit Capability
        SG03 - Critical Hit Threshold
        SG04 - Critical Hit Multiplier
        SG05 - Accuracy Threshold Modifier
        SG06 - Special Properties
        SG07 - General Sights Description
        SG08 - Detailed Sights Description
         */

        boolean primarySightsMissing = false;
        String[] primarySightsData = new String[9];
        if (frameData[5].contains("[SIGHTS (PRIMARY)]")){
            int primarySightsIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("SGT0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primarySightsData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primarySightsData[1].contains(frameData[1])){
                        primarySightsIndex = i;
                    }
                }
            }
            if (primarySightsIndex == -1){
                primarySightsMissing = true;
            }
        } else {
            primarySightsMissing = true;
        }

        boolean secondarySightsMissing = false;
        String[] secondarySightsData = new String[9];
        if (frameData[5].contains("[SIGHTS (SECONDARY)]")){
            int secondarySightsIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("SGT1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondarySightsData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondarySightsData[1].contains(frameData[1])){
                        secondarySightsIndex = i;
                    }
                }
            }
            if (secondarySightsIndex == -1){
                secondarySightsMissing = true;
            }
        } else {
            secondarySightsMissing = true;
        }

        /*
        Muzzle devices are special devices that can drastically alter the behavior of the weapon, and are fitted to the end of the barrel.

        the Primary Muzzle Device can give bonuses/penalties to the following attributes:
        11 - Primary Base Damage
        13 - Primary Accuracy Threshold

        If the weapon has a secondary fire function, the Secondary Muzzle can give bonuses/penalties to the following attributes:
        18 - Secondary Base Damage
        20 - Secondary Accuracy Threshold

        Muzzle Devices can also add properties to the following attributes:
        52 - Special Properties

        Muzzle Devices are defined by the following attributes:
        MZ00 - Name
        MZ01 - Weapon Compatibility List
        MZ02 - Damage Modifier
        MZ03 - Accuracy Threshold Modifier
        MZ04 - Special Properties
        MZ05 - General Muzzle Description
        MZ06 - Detailed Muzzle Description
         */

        boolean primaryMuzzleMissing = false;
        String[] primaryMuzzleData = new String[7];
        if (frameData[5].contains("[MUZZLE (PRIMARY)]")){
            int primaryMuzzleIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("MZL0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primaryMuzzleData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryMuzzleData[1].contains(frameData[1])){
                        primaryMuzzleIndex = i;
                    }
                }
            }
            if (primaryMuzzleIndex == -1){
                primaryMuzzleMissing = true;
            }
        } else {
            primaryMuzzleMissing = true;
        }

        boolean secondaryMuzzleMissing = false;
        String[] secondaryMuzzleData = new String[7];
        if (frameData[5].contains("[MUZZLE (SECONDARY)]")){
            int secondaryMuzzleIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("MZL1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondaryMuzzleData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondaryMuzzleData[1].contains(frameData[1])){
                        secondaryMuzzleIndex = i;
                    }
                }
            }
            if (secondaryMuzzleIndex == -1){
                secondaryMuzzleMissing = true;
            }
        } else {
            secondaryMuzzleMissing = true;
        }

        /*
        Limbs are components of bow weapons that add tension to the bowstring via torsion, and are the primary means upon which a bow weapon imparts kinetic energy onto a projectile.

        The Primary Limbs determine the following attributes:
        12 - Primary Effective Range
        13 - Primary Accuracy Threshold

        The Primary Limbs also give bonuses/penalties to the following attributes:
        11 - Primary Base Damage

        If the weapon has a secondary fire function, the Secondary Limbs also determine the following:
        19 - Secondary Effective Range
        20 - Secondary Accuracy Threshold

        The Secondary Limbs also give bonuses/penalties to the following attributes:
        18 - Secondary Base Damage

        Limbs are defined by the following attributes:
        LM00 - Name
        LM01 - Weapon Compatibility List
        LM02 - Effective Range
        LM03 - Accuracy Threshold
        LM04 - Damage Modifier
        LM05 - General Limbs Description
        LM06 - Detailed Limbs Description
         */

        boolean primaryLimbsMissing = false;
        String[] primaryLimbsData = new String[7];
        if (frameData[5].contains("[LIMBS (PRIMARY)]")){
            int primaryLimbsIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("LMB0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primaryLimbsData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryLimbsData[1].contains(frameData[1])){
                        primaryLimbsIndex = i;
                    }
                }
            }
            if (primaryLimbsIndex == -1){
                primaryLimbsMissing = true;
            }
        } else {
            primaryLimbsMissing = true;
        }

        boolean secondaryLimbsMissing = false;
        String[] secondaryLimbsData = new String[7];
        if (frameData[5].contains("[LIMBS (SECONDARY)]")){
            int secondaryLimbsIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("LMB1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondaryLimbsData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondaryLimbsData[1].contains(frameData[1])){
                        secondaryLimbsIndex = i;
                    }
                }
            }
            if (secondaryLimbsIndex == -1){
                secondaryLimbsMissing = true;
            }
        } else {
            secondaryLimbsMissing = true;
        }

        /*
        Bowstrings are an essential component of any bow weapon, and use tension provided by the limbs to propel the projectile forwards.

        The Primary Bowstring gives bonuses/penalties to the following attributes:
        11 - Primary Base Damage
        13 - Primary Accuracy Threshold

        If the weapon has a secondary fire function, the Secondary Bowstring gives bonuses/penalties to the following attributes:
        18 - Secondary Base Damage
        20 - Secondary Accuracy Threshold

        Bowstrings are defined by the following attributes:
        BS00 - Name
        BS01 - Weapon Compatibility Range
        BS02 - Damage Modifier
        BS03 - Accuracy Threshold Modifier
        BS04 - General Bowstring Description
        BS05 - Detailed Bowstring Description
         */

        boolean primaryBowstringMissing = false;
        String[] primaryBowstringData = new String[6];
        if (frameData[5].contains("[BOWSTRING (PRIMARY)]")){
            int primaryBowstringIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("BWS0")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        primaryBowstringData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (primaryBowstringData[1].contains(frameData[1])){
                        primaryBowstringIndex = i;
                    }
                }
            }
            if (primaryBowstringIndex == -1){
                primaryBowstringMissing = true;
            }
        } else {
            primaryBowstringMissing = true;
        }

        boolean secondaryBowstringMissing = false;
        String[] secondaryBowstringData = new String[6];
        if (frameData[5].contains("[BOWSTRING (SECONDARY)]")){
            int secondaryBowstringIndex = -1;
            for (int i = 0; i < weaponParts.length; i++){
                if (weaponParts[i].getName().contains("BWS1")){
                    componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                    try {
                        secondaryBowstringData = componentScanner.readLine().split("[~]");
                        componentScanner.close();
                    } catch ( IOException tagError ) {
                        tagError.printStackTrace();
                    }
                    if (secondaryBowstringData[1].contains(frameData[1])){
                        secondaryBowstringIndex = i;
                    }
                }
            }
            if (secondaryBowstringIndex == -1){
                secondaryBowstringMissing = true;
            }
        } else {
            secondaryBowstringMissing = true;
        }



        // /\ RANGED / HYBRID WEAPON COMPONENTS /\

        /*
        Once all components have been located, the DaVG will then attempt to put the weapon's information together.
         */

        String[] weaponData = new String[55];

        // \/ FRAME ATTRIBUTES \/
        weaponData[0] = frameData[0]; //Name
        weaponData[1] = frameData[1]; //Weapon Frame Database ID Number
        weaponData[2] = frameData[2]; //Role
        weaponData[3] = frameData[3]; //Familiarity Rating
        weaponData[4] = frameData[4]; //Manufacturer
        weaponData[5] = frameData[5]; //Component Slots
        weaponData[6] = frameData[6]; //Weapon Type
        weaponData[7] = frameData[7]; //Weapon Class
        weaponData[8] = frameData[8]; //Weapon Status
        weaponData[9] = frameData[9]; //Custom Name Flag
        weaponData[10] = frameData[10]; //Primary Attack Type
        weaponData[17] = frameData[11]; //Secondary Attack Type;
        weaponData[50] = frameData[12]; //General Description
        weaponData[51] = frameData[13]; // Detailed Description
        weaponData[52] = "$";
        weaponData[53] = "$";
        // /\ FRAME ATTRIBUTES

        if (frameData[6].equals("RANGED") || frameData[6].equals("HYBRID")){
            // \/ PRIMARY RECEIVER ATTRIBUTES \/
            int primaryReceiverDamageModifier = 0;
            int basePrimaryCaliberDamage = -1;
            if (frameData[10].equals("RANGED") && frameData[5].contains("[RECEIVER (PRIMARY)]")){
                if (!primaryReceiverMissing){
                    weaponData[24] = primaryReceiverData[2]; // Ammunition Caliber
                    weaponData[29] = primaryReceiverData[3]; // Ammo Unit of Measurement
                    weaponData[30] = primaryReceiverData[8]; // Possible Firemodes
                    weaponData[32] = primaryReceiverData[4]; // Chamber Current Status
                    weaponData[33] = primaryReceiverData[5]; // Chamber Current Load
                    weaponData[34] = primaryReceiverData[6]; // Chamber Capacity
                    if (!weaponData[52].contains(primaryReceiverData[9])){
                        weaponData[52] += primaryReceiverData[9]; // Special Properties
                    }
                    primaryReceiverDamageModifier = Integer.parseInt(primaryReceiverData[7]); // Damage Modifier
                } else {
                    weaponData[24] = "[PRIMARY RECEIVER MISSING]";
                    weaponData[29] = "[PRIMARY RECEIVER MISSING]";
                    weaponData[30] = "[PRIMARY RECEIVER MISSING]";
                    weaponData[32] = "[PRIMARY RECEIVER MISSING]";
                    weaponData[33] = "0";
                    weaponData[34] = "0";
                    weaponData[52] += "";
                    primaryReceiverDamageModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }

                switch (weaponData[24]){
                    case "8mm Flechette":
                        basePrimaryCaliberDamage = 20;
                        break;
                    case "Electricity":
                        basePrimaryCaliberDamage = 23;
                        break;
                    case "10mm":
                        basePrimaryCaliberDamage = 25;
                        break;
                    case "12.5mm Katzer Kurzpatrone":
                        basePrimaryCaliberDamage = 27;
                        break;
                    case ".308":
                        basePrimaryCaliberDamage = 40;
                        break;
                    case "12.5mm Katzer Langpatrone":
                        basePrimaryCaliberDamage = 45;
                        break;
                    case "12-Gauge":
                        basePrimaryCaliberDamage = 75;
                        break;
                    default:
                        basePrimaryCaliberDamage = 0;
                        break;
                }
            } else {
                weaponData[24] = "N/A";
                weaponData[29] = "N/A";
                weaponData[30] = "N/A";
                weaponData[32] = "N/A";
                weaponData[33] = "-1";
                weaponData[34] = "-1";
            }
            // /\ PRIMARY RECEIVER ATTRIBUTES /\

            // \/ SECONDARY RECEIVER ATTRIBUTES \/
            int secondaryReceiverDamageModifier = 0;
            int baseSecondaryCaliberDamage = -1;
            if (frameData[11].equals("RANGED") && frameData[5].contains("[RECEIVER (SECONDARY)]")){
                if (!secondaryReceiverMissing){
                    weaponData[37] = secondaryReceiverData[2]; // Ammunition Caliber
                    weaponData[42] = secondaryReceiverData[3]; // Ammo Unit of Measurement
                    weaponData[43] = secondaryReceiverData[8]; // Possible Firemodes
                    weaponData[45] = secondaryReceiverData[4]; // Chamber Current Status
                    weaponData[46] = secondaryReceiverData[5]; // Chamber Current Load
                    weaponData[47] = secondaryReceiverData[6]; // Chamber Capacity
                    if (!weaponData[53].contains(secondaryReceiverData[9])){
                        weaponData[53] += secondaryReceiverData[9]; // Special Properties
                    }
                    secondaryReceiverDamageModifier = Integer.parseInt(secondaryReceiverData[7]); // Damage Modifier
                } else {
                    weaponData[37] = "[SECONDARY RECEIVER MISSING]";
                    weaponData[42] = "[SECONDARY RECEIVER MISSING]";
                    weaponData[43] = "[SECONDARY RECEIVER MISSING]";
                    weaponData[45] = "[SECONDARY RECEIVER MISSING]";
                    weaponData[46] = "0";
                    weaponData[47] = "0";
                    weaponData[53] += "";
                    weaponData[8] = "INCOMPLETE";
                    secondaryReceiverDamageModifier = 0;
                }

                switch (weaponData[37]){
                    case "8mm Flechette":
                        baseSecondaryCaliberDamage = 20;
                        break;
                    case "Electricity":
                        baseSecondaryCaliberDamage = 23;
                        break;
                    case "10mm":
                        baseSecondaryCaliberDamage = 25;
                        break;
                    case "12.5mm Katzer Kurzpatrone":
                        baseSecondaryCaliberDamage = 27;
                        break;
                    case ".308":
                        baseSecondaryCaliberDamage = 40;
                        break;
                    case "12.5mm Katzer Langpatrone":
                        baseSecondaryCaliberDamage = 45;
                        break;
                    case "12-Gauge":
                        baseSecondaryCaliberDamage = 75;
                        break;
                    default:
                        baseSecondaryCaliberDamage = 0;
                        break;
                }
            } else {
                weaponData[37] = "N/A";
                weaponData[42] = "N/A";
                weaponData[43] = "N/A";
                weaponData[45] = "N/A";
                weaponData[46] = "-1";
                weaponData[47] = "-1";
            }
            // /\ SECONDARY RECEIVER ATTRIBUTES /\

            // \/ PRIMARY BARREL ATTRIBUTES \/
            int primaryBarrelDamageModifier = -1;
            int basePrimaryBarrelAccuracyThreshold = -1;
            if (frameData[10].equals("RANGED") && frameData[5].contains("[BARREL (PRIMARY)]")){
                if (!primaryBarrelMissing){
                    weaponData[12] = primaryBarrelData[2]; // Effective Range
                    basePrimaryBarrelAccuracyThreshold = Integer.parseInt(primaryBarrelData[3]); // Accuracy Threshold
                    if (!weaponData[52].contains(primaryBarrelData[5])){
                        weaponData[52] += primaryBarrelData[5]; // Special Properties
                    }
                    primaryBarrelDamageModifier = Integer.parseInt(primaryBarrelData[4]); // Damage Modifier
                } else {
                    weaponData[12] = "[PRIMARY BARREL MISSING]";
                    basePrimaryBarrelAccuracyThreshold = 0;
                    weaponData[52] += "";
                    primaryBarrelDamageModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            } else {
                weaponData[12] = "N/A";
            }
            // /\ PRIMARY BARREL ATTRIBUTES /\

            // \/ SECONDARY BARREL ATTRIBUTES \/
            int secondaryBarrelDamageModifier = -1;
            int baseSecondaryBarrelAccuracyThreshold = -1;
            if (frameData[11].equals("RANGED") && frameData[5].contains("[BARREL (SECONDARY)]")){
                if (!secondaryBarrelMissing){
                    weaponData[19] = secondaryBarrelData[2]; // Effective Range
                    baseSecondaryBarrelAccuracyThreshold = Integer.parseInt(secondaryBarrelData[3]); // Accuracy Threshold
                    if (!weaponData[53].contains(secondaryBarrelData[5])){
                        weaponData[53] += secondaryBarrelData[5]; // Special Properties
                    }
                    secondaryBarrelDamageModifier = Integer.parseInt(secondaryBarrelData[4]); // Damage Modifier
                } else {
                    weaponData[19] = "[SECONDARY BARREL MISSING]";
                    baseSecondaryBarrelAccuracyThreshold =0;
                    weaponData[53] += "";
                    secondaryBarrelDamageModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            } else {
                weaponData[19] = "N/A";
            }
            // /\ SECONDARY BARREL ATTRIBUTES /\

            // \/ PRIMARY MAGAZINE ATTRIBUTES \/
            if (frameData[10].equals("RANGED") && frameData[5].contains("[MAGAZINE (PRIMARY)]")){
                if (!primaryMagazineMissing){
                    weaponData[25] = primaryMagazineData[2]; // Current Subtype
                    weaponData[26] = primaryMagazineData[0]; // Name (Primary Magazine Type)
                    weaponData[27] = primaryMagazineData[3]; // Current Magazine Load
                    weaponData[28] = primaryMagazineData[4]; // Current Magazine Capacity
                } else {
                    weaponData[25] = "[EMPTY]";
                    weaponData[26] = "[PRIMARY MAGAZINE MISSING]";
                    weaponData[27] = "0";
                    weaponData[28] = "0";
                }
            } else {
                weaponData[25] = "N/A";
                weaponData[26] = "N/A";
                weaponData[27] = "-1";
                weaponData[28] = "-1";
            }
            // /\ PRIMARY MAGAZINE ATTRIBUTES /\

            // \/ SECONDARY MAGAZINE ATTRIBUTES \/
            if (frameData[11].equals("RANGED") && frameData[5].contains("[MAGAZINE (SECONDARY)]")){
                if (!secondaryMagazineMissing){
                    weaponData[38] = secondaryMagazineData[2]; // Current Subtype
                    weaponData[39] = secondaryMagazineData[0]; // Name (Primary Magazine Type)
                    weaponData[40] = secondaryMagazineData[3]; // Current Magazine Load
                    weaponData[41] = secondaryMagazineData[4]; // Current Magazine Capacity
                } else {
                    weaponData[38] = "N/A";
                    weaponData[39] = "[SECONDARY MAGAZINE MISSING]";
                    weaponData[40] = "0";
                    weaponData[41] = "0";
                }
            } else {
                weaponData[38] = "N/A";
                weaponData[39] = "N/A";
                weaponData[40] = "-1";
                weaponData[41] = "-1";
            }
            // /\ SECONDARY MAGAZINE ATTRIBUTES /\

            // \/ PRIMARY FIRE CONTROL SYSTEM \/
            if (frameData[10].equals("RANGED") && frameData[5].contains("[FIRE CONTROL SYSTEM (PRIMARY)]")){
                if (!primaryFireControlSystemMissing){
                    String[] primaryFireControlSystemFiremodes;
                    primaryFireControlSystemFiremodes = primaryFireControlSystemData[2].split("/"); // Possible Firemodes
                    for (int i = 0; i < primaryFireControlSystemFiremodes.length; i++){
                        if (!weaponData[30].contains(primaryFireControlSystemFiremodes[i])){
                            weaponData[30] += "/" + primaryFireControlSystemFiremodes[i];
                        }
                    }
                    weaponData[31] = primaryFireControlSystemData[3]; // Current Firemode
                } else {
                    weaponData[31] = "[PRIMARY FIRE CONTROL SYSTEM MISSING]";
                    weaponData[8] = "INCOMPLETE";
                }
            } else {
                weaponData[31] = "N/A";
            }
            // /\ PRIMARY FIRE CONTROL SYSTEM /\

            // \/ SECONDARY FIRE CONTROL SYSTEM \/
            if (frameData[11].equals("RANGED") && frameData[5].contains("[FIRE CONTROL SYSTEM (SECONDARY)]")){
                if (!secondaryFireControlSystemMissing){
                    String[] secondaryFireControlSystemFiremodes;
                    secondaryFireControlSystemFiremodes = secondaryFireControlSystemData[2].split("/"); // Possible Firemodes
                    for (int i = 0; i < secondaryFireControlSystemFiremodes.length; i++){
                        if (!weaponData[43].contains(secondaryFireControlSystemFiremodes[i])){
                            weaponData[43] += secondaryFireControlSystemFiremodes[i];
                        }
                    }
                    weaponData[44] = secondaryFireControlSystemData[3]; // Current Firemode
                } else {
                    weaponData[44] = "[SECONDARY FIRE CONTROL SYSTEM MISSING]";
                    weaponData[8] = "INCOMPLETE";
                }
            } else {
                weaponData[44] = "N/A";
            }
            // /\ SECONDARY FIRE CONTROL SYSTEM /\

            // \/ PRIMARY BOLT \/
            int primaryBoltAccuracyModifier = -1;
            if (frameData[10].equals("RANGED") && frameData[5].contains("[BOLT (PRIMARY)]")){
                if (!primaryBoltMissing){
                    weaponData[35] = primaryBoltData[2]; // Minimum Burst Length
                    weaponData[36] = primaryBoltData[3]; // Maximum Burst Length
                    primaryBoltAccuracyModifier = Integer.parseInt(primaryBoltData[4]); // Accuracy Modifier
                } else {
                    weaponData[35] = "[PRIMARY BOLT MISSING]";
                    weaponData[36] = "[PRIMARY BOLT MISSING]";
                    primaryBoltAccuracyModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            } else {
                if (weaponData[35] == null){
                    weaponData[35] = "N/A";
                }
                if (weaponData[36] == null){
                    weaponData[36] = "N/A";
                }
            }
            // /\ PRIMARY BOLT /\

            // \/ SECONDARY BOLT \/
            int secondaryBoltAccuracyModifier = -1;
            if (frameData[11].equals("RANGED") && frameData[5].contains("[BOLT (SECONDARY)]")){
                if (!secondaryBoltMissing){
                    weaponData[48] = secondaryBoltData[2]; // Minimum Burst Length
                    weaponData[49] = secondaryBoltData[3]; // Maximum Burst Length
                    secondaryBoltAccuracyModifier = Integer.parseInt(secondaryBoltData[4]);
                } else {
                    weaponData[48] = "[SECONDARY BOLT MISSING]";
                    weaponData[49] = "[SECONDARY BOLT MISSING]";
                    secondaryBoltAccuracyModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            } else {
                if (weaponData[48] == null){
                    weaponData[48] = "N/A";
                }
                if (weaponData[49] == null){
                    weaponData[49] = "N/A";
                }
            }
            // /\ SECONDARY BOLT /\

            // \/ PRIMARY HEAT DISPERSION SYSTEM \/
            if (frameData[10].equals("RANGED") && frameData[5].contains("[HEAT DISPERSION SYSTEM (PRIMARY)]")){
                if (!primaryHeatDispersionSystemMissing){
                    weaponData[35] = primaryHeatDispersionSystemData[2]; // Minimum Burst Length
                    weaponData[36] = primaryHeatDispersionSystemData[3]; // Maximum Burst Length
                } else {
                    weaponData[35] = "[PRIMARY HEAT DISPERSION SYSTEM MISSING]";
                    weaponData[36] = "[PRIMARY HEAT DISPERSION SYSTEM MISSING]";
                    weaponData[8] = "INCOMPLETE";
                }
            } else {
                if (weaponData[35] == null){
                    weaponData[35] = "N/A";
                }
                if (weaponData[36] == null){
                    weaponData[36] = "N/A";

                }
            }
            // /\ PRIMARY HEAT DISPERSION SYSTEM /\

            // \/ SECONDARY HEAT DISPERSION SYSTEM \/
            if (frameData[10].equals("RANGED") && frameData[5].contains("[HEAT DISPERSION SYSTEM (SECONDARY)]")){
                if (!secondaryHeatDispersionSystemMissing){
                    weaponData[48] = secondaryHeatDispersionSystemData[2]; // Minimum Burst Length
                    weaponData[49] = secondaryHeatDispersionSystemData[3]; // Maximum Burst Length
                } else {
                    weaponData[48] = "[PRIMARY HEAT DISPERSION SYSTEM MISSING]";
                    weaponData[49] = "[PRIMARY HEAT DISPERSION SYSTEM MISSING]";
                    weaponData[8] = "INCOMPLETE";
                }
            } else {
                if (weaponData[48] == null){
                    weaponData[49] = "N/A";
                }
                if (weaponData[48] == null){
                    weaponData[49] = "N/A";
                }
            }
            // /\ SECONDARY HEAT DISPERSION SYSTEM /\

            // \/ PRIMARY GRIP \/
            int primaryGripPrimaryAccuracyModifier = -1;
            int primaryGripSecondaryAccuracyModifier = -1;
            if (frameData[10].equals("RANGED") && frameData[5].contains("[GRIP (PRIMARY)]")){
                if (!primaryGripMissing){
                    primaryGripPrimaryAccuracyModifier = Integer.parseInt(primaryGripData[2]); // Primary Accuracy Threshold Modifier
                    primaryGripSecondaryAccuracyModifier = Integer.parseInt(primaryGripData[3]); // Secondary Accuracy Threshold Modifier
                } else {
                    primaryGripPrimaryAccuracyModifier = 0;
                    primaryGripSecondaryAccuracyModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            }
            // /\ PRIMARY GRIP /\

            // \/ SECONDARY GRIP \/
            int secondaryGripPrimaryAccuracyModifier = 0;
            int secondaryGripSecondaryAccuracyModifier = 0;
            if (frameData[11].equals("RANGED") && frameData[5].contains("[GRIP (SECONDARY)]")){
                if (!secondaryGripMissing){
                    secondaryGripPrimaryAccuracyModifier = Integer.parseInt(secondaryGripData[2]); // Primary Accuracy Threshold Modifier
                    secondaryGripSecondaryAccuracyModifier = Integer.parseInt(secondaryGripData[3]); // Secondary Accuracy Threshold Modifier
                }
            }
            // /\ SECONDARY GRIP /\

            // \/ STOCK \/
            int stockPrimaryAccuracyThresholdModifier = 0;
            int stockSecondaryAccuracyThresholdModifier = 0;
            if (frameData[10].equals("RANGED") && frameData[5].contains("[STOCK]")){
                if (!stockMissing){
                    stockPrimaryAccuracyThresholdModifier = Integer.parseInt(stockData[2]); // Primary Accuracy Threshold Modifier
                    stockSecondaryAccuracyThresholdModifier = Integer.parseInt(stockData[3]); // Secondary Accuracy Threshold Modifier
                }
            }
            // /\ STOCK /\

            // \/ PRIMARY SIGHTS \/
            int primarySightsAccuracyThresholdModifier = -1;
            if (frameData[10].equals("RANGED") && frameData[5].contains("[SIGHTS (PRIMARY)]")){
                if (!primarySightsMissing){
                    weaponData[14] = primarySightsData[2]; // Critical Hit Capability
                    weaponData[15] = primarySightsData[3]; // Critical Hit Threshold
                    weaponData[16] = primarySightsData[4]; // Critical Hit Multiplier
                    if (!weaponData[52].contains(primarySightsData[6])){
                        weaponData[52] += primarySightsData[6]; // Special Properties
                    }
                    primarySightsAccuracyThresholdModifier = Integer.parseInt(primarySightsData[5]); // Accuracy Threshold Modifier
                } else {
                    weaponData[14] = "[PRIMARY SIGHTS MISSING]";
                    weaponData[15] = "[PRIMARY SIGHTS MISSING]";
                    weaponData[16] = "[PRIMARY SIGHTS MISSING]";
                    weaponData[52] += "";
                    primarySightsAccuracyThresholdModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            } else {
                weaponData[14] = "N/A";
                weaponData[15] = "N/A";
                weaponData[16] = "N/A";
            }
            // /\ PRIMARY SIGHTS /\

            // \/ SECONDARY SIGHTS \/
            int secondarySightsAccuracyThresholdModifier = -1;
            if (frameData[11].equals("RANGED") && frameData[5].contains("[SIGHTS (SECONDARY)]")){
                if (!secondarySightsMissing){
                    weaponData[21] = secondarySightsData[2]; // Critical Hit Capability
                    weaponData[22] = secondarySightsData[3]; // Critical Hit Threshold
                    weaponData[23] = secondarySightsData[4]; // Critical Hit Multiplier
                    if (!weaponData[53].contains(secondarySightsData[6])){
                        weaponData[53] += secondarySightsData[6]; // Special Properties
                    }
                    secondarySightsAccuracyThresholdModifier = Integer.parseInt(secondarySightsData[5]);
                } else {
                    weaponData[21] = "[PRIMARY SIGHTS MISSING]";
                    weaponData[22] = "[PRIMARY SIGHTS MISSING]";
                    weaponData[23] = "[PRIMARY SIGHTS MISSING]";
                    weaponData[53] += "";
                    secondarySightsAccuracyThresholdModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            } else {
                weaponData[21] = "N/A";
                weaponData[22] = "N/A";
                weaponData[23] = "N/A";
            }
            // /\ SECONDARY SIGHTS /\

            // \/ PRIMARY MUZZLE DEVICE \/
            int primaryMuzzleDamageModifier = -1;
            int primaryMuzzleAccuracyThresholdModifier = -1;
            if (frameData[10].equals("RANGED") && frameData[5].contains("[MUZZLE (PRIMARY)]")){
                if (!primaryMuzzleMissing){
                    primaryMuzzleDamageModifier = Integer.parseInt(primaryMuzzleData[2]); // Damage Modifier
                    primaryMuzzleAccuracyThresholdModifier = Integer.parseInt(primaryMuzzleData[3]); // Accuracy Threshold Modifier
                    if (!weaponData[52].contains(primaryMuzzleData[4])){
                        weaponData[52] += primaryMuzzleData[4]; // Special Properties
                    }
                } else {
                    primaryMuzzleDamageModifier = 0;
                    primaryMuzzleAccuracyThresholdModifier = 0;
                    weaponData[52] += "";
                }
            }
            // /\ PRIMARY MUZZLE DEVICE /\

            // \/ SECONDARY MUZZLE DEVICE \/
            int secondaryMuzzleDamageModifier = -1;
            int secondaryMuzzleAccuracyThresholdModifier = -1;
            if (frameData[11].equals("RANGED") && frameData[5].contains("[MUZZLE (SECONDARY)]")){
                if (!secondaryMuzzleMissing){
                    secondaryMuzzleDamageModifier = Integer.parseInt(secondaryMuzzleData[2]); // Damage Modifier
                    secondaryMuzzleAccuracyThresholdModifier = Integer.parseInt(secondaryMuzzleData[3]); // Accuracy Threshold Modifier
                    if (!weaponData[53].contains(secondaryMuzzleData[4])){
                        weaponData[53] += secondaryMuzzleData[4]; // Special Properties
                    }
                } else {
                    secondaryMuzzleDamageModifier = 0;
                    secondaryMuzzleAccuracyThresholdModifier = 0;
                    weaponData[53] += "";
                }
            }
            // /\ SECONDARY MUZZLE DEVICE /\

            // \/ PRIMARY LIMBS ATTRIBUTES \/
            int primaryLimbsDamageModifier = -1;
            int basePrimaryLimbsAccuracyThreshold = -1;
            if (frameData[10].equals("RANGED") && frameData[5].contains("[LIMBS (PRIMARY)]")){
                if (!primaryLimbsMissing){
                    weaponData[12] = primaryLimbsData[2]; // Effective Range
                    basePrimaryLimbsAccuracyThreshold = Integer.parseInt(primaryLimbsData[3]); // Accuracy Threshold
                    primaryLimbsDamageModifier = Integer.parseInt(primaryLimbsData[4]); // Damage Modifier
                } else {
                    weaponData[12] = "[PRIMARY LIMBS MISSING]";
                    basePrimaryLimbsAccuracyThreshold = 0;
                    primaryLimbsDamageModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            }
            // /\ PRIMARY LIMBS ATTRIBUTES /\

            // \/ SECONDARY LIMBS ATTRIBUTES \/
            int secondaryLimbsDamageModifier = -1;
            int baseSecondaryLimbsAccuracyThreshold = -1;
            if (frameData[11].equals("RANGED") && frameData[5].contains("[LIMBS (SECONDARY)]")){
                if (!secondaryLimbsMissing){
                    weaponData[19] = secondaryLimbsData[2]; // Effective Range
                    baseSecondaryLimbsAccuracyThreshold = Integer.parseInt(secondaryLimbsData[3]); //Accuracy Threshold
                    secondaryLimbsDamageModifier = Integer.parseInt(secondaryLimbsData[4]); // Damage Modifier
                } else {
                    weaponData[19] = "[SECONDARY LIMBS MISSING]";
                    baseSecondaryLimbsAccuracyThreshold = 0;
                    secondaryLimbsDamageModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            }
            // /\ SECONDARY LIMBS ATTRIBUTES /\

            // \/ PRIMARY BOWSTRING ATTRIBUTES \/
            int primaryBowstringDamageModifier = -1;
            int primaryBowstringAccuracyThresholdModifier = -1;
            if (frameData[5].contains("[BOWSTRING (PRIMARY)]")){
                if (!primaryBowstringMissing){
                    primaryBowstringDamageModifier = Integer.parseInt(primaryBowstringData[2]); // Damage Modifier
                    primaryBowstringAccuracyThresholdModifier = Integer.parseInt(primaryBowstringData[3]); // Accuracy Threshold Modifier
                } else {
                    primaryBowstringDamageModifier = 0;
                    primaryBowstringAccuracyThresholdModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            }
            // /\ PRIMARY BOWSTRING ATTRIBUTES /\

            // \/ SECONDARY BOWSTRING ATTRIBUTES \/
            int secondaryBowstringDamageModifier = -1;
            int secondaryBowstringAccuracyThresholdModifier = -1;
            if (frameData[5].contains("[BOWSTRING (SECONDARY)]")){
                if (!secondaryBowstringMissing){
                    secondaryBowstringDamageModifier = Integer.parseInt(secondaryBowstringData[2]); // Damage Modifier
                    secondaryBowstringAccuracyThresholdModifier = Integer.parseInt(secondaryBowstringData[3]); // Accuracy Threshold Modifier
                } else {
                    secondaryBowstringDamageModifier = 0;
                    secondaryBowstringAccuracyThresholdModifier = 0;
                    weaponData[8] = "INCOMPLETE";
                }
            }
            // /\ SECONDARY BOWSTRING ATTRIBUTES /\

            // /\ FIREARMS /\

            // /\ RANGED / HYBRID WEAPONS ONLY /\

            // \/ TOTAL PRIMARY FIRE DAMAGE CALCULATION \/
            int primaryTotalDamage = 0;

            if (weaponData[6].equals("RANGED") || weaponData[6].equals("HYBRID")){
                primaryTotalDamage = basePrimaryCaliberDamage;
                primaryTotalDamage += primaryReceiverDamageModifier;
                if (frameData[5].contains("[BARREL (PRIMARY)]")){
                    primaryTotalDamage += primaryBarrelDamageModifier;
                }
                if (frameData[5].contains("[MUZZLE (PRIMARY)]")){
                    primaryTotalDamage += primaryMuzzleDamageModifier;
                }
                if (frameData[5].contains("[LIMBS (PRIMARY)]")){
                    primaryTotalDamage += primaryLimbsDamageModifier;
                }
                if (frameData[5].contains("[BOWSTRING (PRIMARY)]")){
                    primaryTotalDamage += primaryBowstringDamageModifier;
                }
            } else if (weaponData[6].equals("MELEE") || weaponData[6].equals("HYBRID")){

            }
            weaponData[11] = Integer.toString(primaryTotalDamage);
            // /\ TOTAL PRIMARY FIRE DAMAGE CALCULATION /\

            // \/ TOTAL SECONDARY FIRE DAMAGE CALCULATION \/
            int secondaryTotalDamage = 0;

            if (weaponData[6].equals("RANGED") || weaponData[6].equals("HYBRID")){
                secondaryTotalDamage = baseSecondaryCaliberDamage;
                secondaryTotalDamage += secondaryReceiverDamageModifier;
                if (frameData[5].contains("[BARREL (SECONDARY)]")){
                    secondaryTotalDamage += secondaryBarrelDamageModifier;
                }
                if (frameData[5].contains("[MUZZLE (SECONDARY)]")){
                    secondaryTotalDamage += secondaryMuzzleDamageModifier;
                }
                if (frameData[5].contains("[LIMBS (SECONDARY)]")){
                    secondaryTotalDamage += secondaryLimbsDamageModifier;
                }
                if (frameData[5].contains("[BOWSTRING (SECONDARY)]")){
                    secondaryTotalDamage += secondaryBowstringDamageModifier;
                }
            } else if (weaponData[6].equals("MELEE") || weaponData[6].equals("HYBRID")){

            }
            weaponData[18] = Integer.toString(secondaryTotalDamage);
            // /\ TOTAL SECONDARY FIRE DAMAGE CALCULATION /\

            // \/ TOTAL PRIMARY FIRE ACCURACY THRESHOLD CALCULATION \/
            int primaryTotalAccuracyThreshold = 0;

            if (weaponData[6].equals("RANGED") || weaponData[6].equals("HYBRID")){
                if (frameData[5].contains("[LIMBS (PRIMARY)]")){
                    primaryTotalAccuracyThreshold = basePrimaryLimbsAccuracyThreshold;
                }
                if (frameData[5].contains("[BARREL (PRIMARY)]")){
                    primaryTotalAccuracyThreshold= basePrimaryBarrelAccuracyThreshold;
                }
                if (frameData[5].contains("[BOLT (PRIMARY)]")){
                    primaryTotalAccuracyThreshold += primaryBoltAccuracyModifier;
                }
                if (frameData[5].contains("[GRIP (PRIMARY)]")){
                    primaryTotalAccuracyThreshold += primaryGripPrimaryAccuracyModifier;
                }
                if (frameData[5].contains("[GRIP (SECONDARY)]")){
                    primaryTotalAccuracyThreshold += secondaryGripPrimaryAccuracyModifier;
                }
                if (frameData[5].contains("[STOCK]")){
                    primaryTotalAccuracyThreshold += stockPrimaryAccuracyThresholdModifier;
                }
                if (frameData[5].contains("[SIGHTS (PRIMARY)]")){
                    primaryTotalAccuracyThreshold += primarySightsAccuracyThresholdModifier;
                }
                if (frameData[5].contains("[MUZZLE (PRIMARY)]")){
                    primaryTotalAccuracyThreshold += primaryMuzzleAccuracyThresholdModifier;
                }
                if (frameData[5].contains("[BOWSTRING (PRIMARY)]")){
                    primaryTotalAccuracyThreshold += primaryBowstringAccuracyThresholdModifier;
                }
            } else if (weaponData[6].equals("MELEE") || weaponData[6].equals("HYBRID")){

            }
            weaponData[13] = Integer.toString(primaryTotalAccuracyThreshold);
            // /\ TOTAL PRIMARY FIRE ACCURACY THRESHOLD CALCULATION /\

            // \/ TOTAL SECONDARY FIRE ACCURACY THRESHOLD CALCULATION \/
            int secondaryTotalAccuracyThreshold = 0;

            if (weaponData[6].equals("RANGED") || weaponData[6].equals("HYBRID")){
                if (frameData[5].contains("[LIMBS (SECONDARY)]")){
                    secondaryTotalAccuracyThreshold = baseSecondaryLimbsAccuracyThreshold;
                }
                if (frameData[5].contains("[BARREL (SECONDARY)]")){
                    secondaryTotalAccuracyThreshold = baseSecondaryBarrelAccuracyThreshold;
                }
                if (frameData[5].contains("[BOLT (SECONDARY)]")){
                    secondaryTotalAccuracyThreshold += secondaryBoltAccuracyModifier;
                }
                if (frameData[5].contains("[GRIP (PRIMARY)]")){
                    secondaryTotalAccuracyThreshold += primaryGripSecondaryAccuracyModifier;
                }
                if (frameData[5].contains("[GRIP (SECONDARY)]")){
                    secondaryTotalAccuracyThreshold += secondaryGripSecondaryAccuracyModifier;
                }
                if (frameData[5].contains("[STOCK]")){
                    secondaryTotalAccuracyThreshold += stockSecondaryAccuracyThresholdModifier;
                }
                if (frameData[5].contains("[SIGHTS (SECONDARY)]")){
                    secondaryTotalAccuracyThreshold += secondarySightsAccuracyThresholdModifier;
                }
                if (frameData[5].contains("[MUZZLE (SECONDARY)]")){
                    secondaryTotalAccuracyThreshold += secondaryMuzzleAccuracyThresholdModifier;
                }
                if (frameData[5].contains("[BOWSTRING (SECONDARY)]")){
                    secondaryTotalAccuracyThreshold += secondaryBowstringAccuracyThresholdModifier;
                }
            } else if (weaponData[6].equals("MELEE") || weaponData[6].equals("HYBRID")){

            }
            weaponData[20] = Integer.toString(secondaryTotalAccuracyThreshold);
            // /\ TOTAL SECONDARY FIRE ACCURACY THRESHOLD CALCULATION /\

        }

        return weaponData;
    }

    private static void weaponWrite(playerCharacter player, String weaponName, String[] weaponData) throws IOException{
        /*
        desc here
         */

        File[] inventory = player.getPlayerInventory();

        String[] frameData = new String[14];

        frameData[0] = weaponData[0]; // Name
        frameData[1] = weaponData[1]; // Weapon Frame Database ID Number
        frameData[2] = weaponData[2]; // Role
        frameData[3] = weaponData[3]; // Familiarity Rating
        frameData[4] = weaponData[4]; // Manufacturer
        frameData[5] = weaponData[5]; // Component Slots
        frameData[6] = weaponData[6]; // Weapon Type
        frameData[7] = weaponData[7]; // Weapon Class
        frameData[8] = weaponData[8]; // Weapon Status
        frameData[9] = weaponData[9]; // Custom Name Flag
        frameData[10] = weaponData[10]; // Primary Attack Type
        frameData[11] = weaponData[17]; // Secondary Attack Type
        frameData[12] = weaponData[50]; // General Weapon Information
        frameData[13] = weaponData[51]; // Detailed Weapon Information

        writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+weaponName+"\\weaponFrame.TAG"));
        for (int i = 0; i < frameData.length; i++){
            writer.write (frameData[i]+"~");
        }
        writer.flush();
        writer.close();

        File[] weaponParts = new File(inventory[1]+"\\"+weaponName).listFiles(File::isFile);
        if (weaponParts == null){
            weaponParts = new File[1];
        }
        BufferedReader componentScanner;

        if (frameData[6].equals("RANGED") || frameData[6].equals("HYBRID")){
            boolean primaryReceiverMissing = false;
            String[] primaryReceiverData = new String[12];
            int primaryReceiverIndex = -1;
            if (frameData[5].contains("[RECEIVER (PRIMARY)]")){
                for (int i = 0; i < weaponParts.length; i++){
                    if (weaponParts[i].getName().contains("REC0")){
                        componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                        try {
                            primaryReceiverData = componentScanner.readLine().split("[~]");
                            componentScanner.close();
                        } catch ( IOException tagError ) {
                            tagError.printStackTrace();
                        }
                        if (primaryReceiverData[1].contains(frameData[1])){
                            primaryReceiverIndex = i;
                        }
                    }
                }
                if (primaryReceiverIndex == -1){
                    primaryReceiverMissing = true;
                }
            } else {
                primaryReceiverMissing = true;
            }
            if (!primaryReceiverMissing){
                primaryReceiverData[2] = weaponData[24]; // Ammunition Caliber
                primaryReceiverData[3] = weaponData[29]; // Ammo Unit of Measurement
                primaryReceiverData[8] = weaponData[30]; // Possible Firemodes
                primaryReceiverData[4] = weaponData[32]; // Chamber Current Status
                primaryReceiverData[5] = weaponData[33]; // Chamber Current Load
                primaryReceiverData[6] = weaponData[34]; // Chamber Capacity
                writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+weaponName+"\\"+weaponParts[primaryReceiverIndex].getName()));
                for (int i = 0; i < primaryReceiverData.length; i++){
                    writer.write (primaryReceiverData[i]+"~");
                }
                writer.flush();
                writer.close();
            }

            boolean secondaryReceiverMissing = false;
            String[] secondaryReceiverData = new String[12];
            int secondaryReceiverIndex = -1;
            if (frameData[5].contains("[RECEIVER (SECONDARY)]")){
                for (int i = 0; i < weaponParts.length; i++){
                    if (weaponParts[i].getName().contains("REC1")){
                        componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                        try {
                            secondaryReceiverData = componentScanner.readLine().split("[~]");
                            componentScanner.close();
                        } catch ( IOException tagError ) {
                            tagError.printStackTrace();
                        }
                        if (secondaryReceiverData[1].contains(frameData[1])){
                            secondaryReceiverIndex = i;
                        }
                    }
                }
                if (secondaryReceiverIndex == -1){
                    secondaryReceiverMissing = true;
                }
            } else {
                secondaryReceiverMissing = true;
            }
            if (!secondaryReceiverMissing){
                secondaryReceiverData[2] = weaponData[37]; // Ammunition Caliber
                secondaryReceiverData[3] = weaponData[42]; // Ammo Unit of Measurement
                secondaryReceiverData[8] = weaponData[43]; // Possible Firemodes
                secondaryReceiverData[4] = weaponData[45]; // Chamber Current Status
                secondaryReceiverData[5] = weaponData[46]; // Chamber Current Load
                secondaryReceiverData[6] = weaponData[47]; // Chamber Capacity
                writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+weaponName+"\\"+weaponParts[secondaryReceiverIndex].getName()));
                for (int i = 0; i < secondaryReceiverData.length; i++){
                    writer.write (secondaryReceiverData[i]+"~");
                }
                writer.flush();
                writer.close();
            }

            boolean primaryMagazineMissing = false;
            String[] primaryMagazineData = new String[7];
            int primaryMagazineIndex = -1;
            if (frameData[5].contains("[MAGAZINE (PRIMARY)]")){
                for (int i = 0; i < weaponParts.length; i++){
                    if (weaponParts[i].getName().contains("MGZ0")){
                        componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                        try {
                            primaryMagazineData = componentScanner.readLine().split("[~]");
                            componentScanner.close();
                        } catch ( IOException tagError ) {
                            tagError.printStackTrace();
                        }
                        if (primaryMagazineData[1].contains(frameData[1])){
                            primaryMagazineIndex = i;
                        }
                    }
                }
                if (primaryMagazineIndex == -1){
                    primaryMagazineMissing = true;
                }
            } else {
                primaryMagazineMissing = true;
            }
            if (!primaryMagazineMissing){
                primaryMagazineData[2] = weaponData[25]; // Current Ammo Subtype
                primaryMagazineData[0] = weaponData[26]; // Name (Magazine Type)
                primaryMagazineData[3] = weaponData[27]; // Current Magazine Load
                primaryMagazineData[4] = weaponData[28]; // Magazine Capacity
                writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+weaponName+"\\"+weaponParts[primaryMagazineIndex].getName()));
                for (int i = 0; i < primaryMagazineData.length; i++){
                    writer.write (primaryMagazineData[i]+"~");
                }
                writer.flush();
                writer.close();
            }

            boolean secondaryMagazineMissing = false;
            String[] secondaryMagazineData = new String[7];
            int secondaryMagazineIndex = -1;
            if (frameData[5].contains("[MAGAZINE (SECONDARY)]")){
                for (int i = 0; i < weaponParts.length; i++){
                    if (weaponParts[i].getName().contains("MGZ1")){
                        componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                        try {
                            secondaryMagazineData = componentScanner.readLine().split("[~]");
                            componentScanner.close();
                        } catch ( IOException tagError ) {
                            tagError.printStackTrace();
                        }
                        if (secondaryMagazineData[1].contains(frameData[1])){
                            secondaryMagazineIndex = i;
                        }
                    }
                }
                if (secondaryMagazineIndex == -1){
                    secondaryMagazineMissing = true;
                }
            } else {
                secondaryMagazineMissing = true;
            }
            if (!secondaryMagazineMissing){
                secondaryMagazineData[2] = weaponData[38]; // Current Ammo Subtype
                secondaryMagazineData[0] = weaponData[39]; // Name (Magazine Type)
                secondaryMagazineData[3] = weaponData[40]; // Current Magazine Load
                secondaryMagazineData[4] = weaponData[41]; // Magazine Capacity
                writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+weaponName+"\\"+weaponParts[secondaryMagazineIndex].getName()));
                for (int i = 0; i < secondaryMagazineData.length; i++){
                    writer.write (secondaryMagazineData[i]+"~");
                }
                writer.flush();
                writer.close();
            }

            boolean primaryFireControlSystemMissing = false;
            String[] primaryFireControlSystemData = new String[6];
            int primaryFireControlSystemIndex = -1;
            if (frameData[5].contains("[FIRE CONTROL SYSTEM (PRIMARY)]")){
                for (int i = 0; i < weaponParts.length; i++){
                    if (weaponParts[i].getName().contains("FCS0")){
                        componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                        try {
                            primaryFireControlSystemData = componentScanner.readLine().split("[~]");
                            componentScanner.close();
                        } catch ( IOException tagError ) {
                            tagError.printStackTrace();
                        }
                        if (primaryFireControlSystemData[1].contains(frameData[1])){
                            primaryFireControlSystemIndex = i;
                        }
                    }
                }
                if (primaryFireControlSystemIndex == -1){
                    primaryFireControlSystemMissing = true;
                }
            } else {
                primaryFireControlSystemMissing = true;
            }
            if (!primaryFireControlSystemMissing){
                primaryFireControlSystemData[3] = weaponData[31]; // Current Firemode
                writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+weaponName+"\\"+weaponParts[primaryFireControlSystemIndex].getName()));
                for (int i = 0; i < primaryFireControlSystemData.length; i++){
                    writer.write (primaryFireControlSystemData[i]+"~");
                }
                writer.flush();
                writer.close();
            }

            boolean secondaryFireControlSystemMissing = false;
            String[] secondaryFireControlSystemData = new String[6];
            int secondaryFireControlSystemIndex = -1;
            if (frameData[5].contains("[FIRE CONTROL SYSTEM (SECONDARY)]")){
                for (int i = 0; i < weaponParts.length; i++){
                    if (weaponParts[i].getName().contains("FCS1")){
                        componentScanner = new BufferedReader(new FileReader(inventory[1]+"\\"+weaponName+"\\"+weaponParts[i].getName()));
                        try {
                            secondaryFireControlSystemData = componentScanner.readLine().split("[~]");
                            componentScanner.close();
                        } catch ( IOException tagError ) {
                            tagError.printStackTrace();
                        }
                        if (secondaryFireControlSystemData[1].contains(frameData[1])){
                            secondaryFireControlSystemIndex = i;
                        }
                    }
                }
                if (secondaryFireControlSystemIndex == -1){
                    secondaryFireControlSystemMissing = true;
                }
            } else {
                secondaryFireControlSystemMissing = true;
            }
            if (!secondaryFireControlSystemMissing){
                secondaryFireControlSystemData[3] = weaponData[44]; // Current Firemode
                writer = new BufferedWriter(new FileWriter(inventory[1]+"\\"+weaponName+"\\"+weaponParts[secondaryFireControlSystemIndex].getName()));
                for (int i = 0; i < secondaryFireControlSystemData.length; i++){
                    writer.write (secondaryFireControlSystemData[i]+"~");
                }
                writer.flush();
                writer.close();
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

        ShortDelay();
        System.out.print("//RETRIEVING AMMUNITION REGISTRY... ");

        File[] inventory = player.getPlayerInventory();
        File[] ammunition = new File(String.valueOf(inventory[2])).listFiles(File::isDirectory);

        int chosenAmmoIndex;

        if (ammunition.length == 0) {
            LongDelay();
            System.out.println("ERROR");
            ShortDelay();
            System.out.println("//NO AMMUNITION IN INVENTORY");
        } else {
            MedDelay();
            System.out.println("COMPLETE");
            ShortDelay();
            for (int i = 0; i < ammunition.length; i++){
                System.out.println("> " + i + "_ " + ammunition[i].getName());
                NoDelay();
            }
        }
        System.out.println();
        ShortDelay();
        System.out.println("> A_ REFRESH");
        NoDelay();
        System.out.println("> ._ EXIT");

        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            String ammunitionInput = input.nextLine();
            switch ( ammunitionInput ) {
                case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                    input = new Scanner(System.in);
                    ammunition(player);
                    return;
                case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                    System.out.println("//ARSENAL SUBMENU EXITED");
                    return;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    try {
                        chosenAmmoIndex = Integer.parseInt(ammunitionInput);
                        if (0 <= chosenAmmoIndex && chosenAmmoIndex < ammunition.length){
                            ammoDisp( player, ammunition[chosenAmmoIndex].getName());
                        }
                    } catch (NumberFormatException notNumber){
                        chosenAmmoIndex = -1;
                        for (int i = 0; i < ammunition.length; i++){
                            if (ammunition[i].getName().equals(ammunitionInput)){
                                chosenAmmoIndex = i;
                            }
                        }
                        if (chosenAmmoIndex == -1){
                            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                            } else if (player.getUserLanguage().equals("ENGLISH")){
                                System.out.println("//ERROR: INVALID INPUT");
                            }
                        } else {
                            ammoDisp (player, ammunition[chosenAmmoIndex].getName());
                        }
                    }
                    break;
            }
        }
    }

    private static void ammoDisp ( playerCharacter player, String ammoName ) throws IOException, InterruptedException{
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
        System.out.print( " > AMMUNITION" );
        NoDelay();
        System.out.println(" > " + ammoName);

        ShortDelay();

        System.out.print("//RETRIEVING AMMUNITION RESERVE MANIFEST... ");

        File[] inventory = player.getPlayerInventory();
        File[] ammoTypes = new File(inventory[2]+"\\"+ammoName).listFiles(File::isFile);

        String[] tempAmmoData;
        String[] chosenAmmoData = new String[2];
        File ammoTypeTag;
        double ammoCount;
        String unitOfMeasurement;
        String ammoDispName;
        int chosenSubtypeIndex = -1;

        switch (ammoName){
            case "Electricity":
                unitOfMeasurement = "kWh";
                break;
            case "8mm Flechette":
                unitOfMeasurement = "8mm Flechette";
                break;
            case "10mm":
                unitOfMeasurement = "10mm";
                break;
            case "12.5mm Katzer Kurzpatrone":
                unitOfMeasurement = "12.5mm K/K";
                break;
            case "12.5mm Katzer Langpatrone":
                unitOfMeasurement = "12.5mm K/L";
                break;
            case ".308":
                unitOfMeasurement = ".308";
                break;
            case "12-Gauge":
                unitOfMeasurement = "12 Ga.";
                break;
            default:
                unitOfMeasurement = "U";
                break;
        }

        if (ammoTypes.length != 0){
            MedDelay();
            System.out.println("COMPLETE");
            for (int i = 0; i < ammoTypes.length; i++){
                ammoTypeTag = new File( ammoTypes[i].toString() );
                if (ammoTypeTag.exists()){
                    tempAmmoData = ammoRead ( player, ammoName, ammoTypes[i].getName());
                    ammoDispName = tempAmmoData[0];
                    ammoCount = Double.parseDouble(tempAmmoData[1]);
                    if (ammoName.equals("Electricity")){
                        System.out.println("> " + i + "_ " + ammoCount + "x " + unitOfMeasurement);
                    } else {
                        System.out.println("> " + i + "_ " + (int)ammoCount + "x " + ammoDispName + unitOfMeasurement);
                    }
                    NoDelay();
                }
            }
        } else {
            LongDelay();
            System.out.println("ERROR");
            ShortDelay();
            System.out.println("//UNABLE TO READ DATA ON SUBTYPE RESERVES");
        }
        System.out.println();
        ShortDelay();
        System.out.println("> 0_ EDIT");
        NoDelay();
        System.out.println("> A_ REFRESH");
        NoDelay();
        System.out.println("> ._ EXIT");

        boolean ammoActionNotChosen = true;
        while (ammoActionNotChosen){
            switch (input.nextLine()){
                case "EDIT": case "edit": case "0":
                    System.out.println("// PLEASE SELECT SUBTYPE RESERVE TO EDIT");
                    ShortDelay();
                    for (int i = 0; i < ammoTypes.length; i++){
                        ammoTypeTag = new File( ammoTypes[i].toString() );
                        if (ammoTypeTag.exists()){
                            tempAmmoData = ammoRead ( player, ammoName, ammoTypes[i].getName());
                            ammoDispName = tempAmmoData[0];
                            ammoCount = Double.parseDouble(tempAmmoData[1]);
                            if (ammoName.equals("Electricity")){
                                System.out.println("> " + i + "_ " + ammoCount + "x " + unitOfMeasurement);
                            } else {
                                System.out.println("> " + i + "_ " + (int)ammoCount + "x " + ammoDispName + unitOfMeasurement);
                            }
                            NoDelay();
                        }
                    }
                    System.out.println();
                    ShortDelay();
                    System.out.println("> ._ EXIT");
                    boolean subtypeReserveNotChosen = true;
                    boolean ammoEditCancelled = false;
                    String inputSubtype;
                    while ( subtypeReserveNotChosen ){
                        inputSubtype = input.nextLine();
                        switch (inputSubtype){
                            case ".":
                                subtypeReserveNotChosen = false;
                                ammoEditCancelled = true;
                                break;
                            default:
                                try {
                                    chosenSubtypeIndex = Integer.parseInt(inputSubtype);
                                } catch ( Exception e0 ) {
                                    for (int i = 0; i < ammoTypes.length; i++){
                                        if (ammoTypes[i].getName().contains(inputSubtype)){
                                            chosenSubtypeIndex = i;
                                        }
                                    }
                                }

                                if (chosenSubtypeIndex != -1){
                                    subtypeReserveNotChosen = false;
                                } else {
                                    System.out.println("//ERROR: REQUESTED SUBTYPE NOT LOCATED");
                                }
                                break;
                        }
                    }

                    if (ammoEditCancelled){
                        System.out.println("//SUBTYPE RESERVE EDIT CANCELLED");
                    } else {
                        chosenAmmoData = ammoRead ( player, ammoName, ammoTypes[chosenSubtypeIndex].getName());

                        System.out.println("//PLEASE ENTER NEW SUBTYPE RESERVE VALUE");

                        double newAmmoValue;
                        while (!input.hasNextDouble() || !input.hasNextInt()){
                            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                            } else if (player.getUserLanguage().equals("ENGLISH")){
                                System.out.println("//ERROR: INVALID INPUT");
                            }
                            input = new Scanner(System.in);
                        }
                        newAmmoValue = input.nextDouble();

                        chosenAmmoData[1] = Double.toString(newAmmoValue);

                        ammoWrite (player, ammoName, chosenSubtypeIndex, chosenAmmoData);

                        System.out.println("//SUBTYPE RESERVE VALUE UPDATED");
                        input = new Scanner (System.in);
                        ammoActionNotChosen = false;
                    }
                    break;
                case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                    ammoDisp( player, ammoName );
                    return;
                case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                    inventory( player );
                    return;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
                    }
                    break;
            }
        }

    }

    private static String[] ammoRead (playerCharacter player, String ammoName, String specifiedSubtype ) throws IOException{
        File[] inventory = player.getPlayerInventory();
        String[] ammoData = new String[3];

        /*
        00 - Subtype Name
        01 - Subtype Amount
         */

        BufferedReader ammoScanner = new BufferedReader(new FileReader(inventory[2]+"\\"+ammoName+"\\" + specifiedSubtype));
        try {
            ammoData = ammoScanner.readLine().split("[~]");
            ammoScanner.close();
        } catch ( IOException tagError ) {
            tagError.printStackTrace();
        }

        return ammoData;
    }

    private static void ammoWrite (playerCharacter player, String ammoName, int subtypeIndex, String[] ammoData) throws IOException{
        File[] inventory = player.getPlayerInventory();
        File[] ammoTypes = new File(inventory[2]+"\\"+ammoName).listFiles(File::isFile);

        if (ammoTypes == null){
            ammoTypes = new File[1];
        }

        writer = new BufferedWriter(new FileWriter(ammoTypes[subtypeIndex]));
        for (int i = 0; i < ammoData.length; i++){
            writer.write (ammoData[i]+"~");
        }
        writer.flush();
        writer.close();
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
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED" );
        ShortDelay();
        System.out.println( "//INPUT 0 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 1 TO INITIATE SHUTDOWN");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "0":
                    mainMenu( player );
                    break;
                case "1":
                    shutdown( player );
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
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
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED" );
        ShortDelay();
        System.out.println( "//INPUT 1 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 2 TO INITIATE SHUTDOWN");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "0":
                    mainMenu( player );
                    break;
                case "1":
                    shutdown( player );
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
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
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED" );
        ShortDelay();
        System.out.println( "//INPUT 0 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 1 TO INITIATE SHUTDOWN");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "0":
                    mainMenu( player );
                    break;
                case "1":
                    shutdown( player );
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
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
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED" );
        ShortDelay();
        System.out.println( "//INPUT 0 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 1 TO INITIATE SHUTDOWN");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "0":
                    mainMenu( player );
                    break;
                case "1":
                    shutdown( player );
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
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
        System.out.println( "//ERROR: DRIVE SECTOR CORRUPTED" );
        ShortDelay();
        System.out.println( "//INPUT 0 TO RETURN TO MAIN MENU" );
        ShortDelay();
        System.out.println( "//INPUT 1 TO INITIATE SHUTDOWN" );
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "0":
                    mainMenu( player );
                    break;
                case "1":
                    shutdown( player );
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
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
        System.out.print("//INITIALIZING TARGETING ASSISTANCE SYSTEM... ");
        boolean claMissing = player.getUserClass0().equals("KL0M");
        boolean strMissing = player.getUserStrength().equals("STKM");
        boolean dexMissing = player.getUserDexterity().equals("GSKM");
        boolean conMissing = player.getUserConstitution().equals("VFSM");
        boolean intMissing = player.getUserIntelligence().equals("INTM");
        boolean wisMissing = player.getUserWisdom().equals("WSHM");
        boolean chaMissing = player.getUserCharisma().equals("CHRM");
        if ( claMissing || strMissing || dexMissing || conMissing || intMissing || wisMissing || chaMissing ){
            LongDelay();
            System.out.println( "//ERROR: INSUFFICIENT DATA ON CURRENT USER" );
            ShortDelay();
            System.out.println( "//INPUT 0 TO RETURN TO MAIN MENU" );
            ShortDelay();
            System.out.println( "//INPUT 1 TO INITIATE SHUTDOWN" );
            boolean optionNotChosen = true;
            while ( optionNotChosen ) {
                switch ( input.nextLine() ) {
                    case "0":
                        mainMenu( player );
                        break;
                    case "1":
                        shutdown( player );
                        break;
                    case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                        ShortDelay();
                        System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                        break;
                    case "#KVSCLE": //changes language to English
                        optionNotChosen = true;
                        player.setUserLanguage("ENGLISH");
                        System.out.println("//LANGUAGE PREFERENCE UPDATED");
                        ShortDelay();
                        System.out.println("//INPUT \"A\" TO REFRESH");
                        break;
                    default:
                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                        } else if (player.getUserLanguage().equals("ENGLISH")){
                            System.out.println("//ERROR: INVALID INPUT");
                        }
                        break;
                }
            }
        } else {
            MedDelay();
            System.out.println("COMPLETE");
        }
        System.out.println();
        ShortDelay();
        System.out.println("//CURRENT CYCLE: " + currentCycle);
        System.out.println();
        ShortDelay();
        System.out.println("//CURRENT HP: " + player.getUserCurrentHealth() + "/" + player.getUserMaxHealth());
        NoDelay();
        System.out.println("//CURRENT STATUS: " + player.getUserStatus());
        System.out.println();
        ShortDelay();

        File[] inventory = player.getPlayerInventory();
        String [] currentPrimaryWeaponData = new String[54];
        String [] currentSecondaryWeaponData = new String[54];
        File currentlyEquippedPrimary = new File(inventory[1]+"\\"+player.getPrimaryWeapon());
        File currentlyEquippedSecondary = new File(inventory[1]+"\\"+player.getSecondaryWeapon());

        boolean hasCurrentPrimary = currentlyEquippedPrimary.isDirectory();
        boolean hasCurrentSecondary = currentlyEquippedSecondary.isDirectory();

        if (!hasCurrentPrimary){
            player.setPrimaryWeapon("PWFM");
        }

        if (!hasCurrentSecondary) {
            player.setSecondaryWeapon("SWFM");
        }

        if (player.getPrimaryWeapon().equals("PWFM")){
            currentPrimaryWeaponData[0] = "FISTS"; // Name
            currentPrimaryWeaponData[1] = "001"; // Weapon Frame Database ID Number
            currentPrimaryWeaponData[2] = "ENVIRONMENTAL MANIPULATOR/TRADITIONAL COMBAT IMPLEMENT"; // Role
            currentPrimaryWeaponData[3] = Integer.toString(modCalc(Integer.parseInt(player.getUserDexterity()))); // Familiarity Rating
            currentPrimaryWeaponData[4] = "N/A"; // Manufacturer
            currentPrimaryWeaponData[5] = "$"; // Component Slots
            currentPrimaryWeaponData[6] = "MELEE"; // Weapon Type
            currentPrimaryWeaponData[7] = "UNARMED"; // Weapon Class
            currentPrimaryWeaponData[8] = "NORMAL"; // Weapon Status
            currentPrimaryWeaponData[9] = "N"; // Custom Name Flag
            currentPrimaryWeaponData[10] = "MELEE"; // Primary Attack Type
            currentPrimaryWeaponData[11] = player.getUserStrength(); // Primary Base Damage
            currentPrimaryWeaponData[12] = "PB"; // Primary Effective Range
            if ((20-Integer.parseInt(player.getUserDexterity())) < 0){
                currentPrimaryWeaponData [13] = "1"; // Primary Accuracy Threshold
            } else {
                currentPrimaryWeaponData [13] = Integer.toString(20-Integer.parseInt(player.getUserDexterity()));
            }
            if (Integer.parseInt(player.getUserDexterity()) >= 2){
                currentPrimaryWeaponData[14] = "J"; // Primary Critical Hit Capability
            } else {
                currentPrimaryWeaponData[14] = "N";
            }
            if ((20-Integer.parseInt(player.getUserDexterity())) < 0){
                currentPrimaryWeaponData [15] = "1"; // Primary Crit Threshold
            } else {
                currentPrimaryWeaponData [15] = Integer.toString(20-Integer.parseInt(player.getUserDexterity()));
            }
            currentPrimaryWeaponData[16] = Integer.toString(modCalc(Integer.parseInt(player.getUserDexterity()))+1); // Primary Crit Multiplier
            currentPrimaryWeaponData[17] = "N/A"; // Secondary Attack Type
            currentPrimaryWeaponData[18] = "-1"; // Secondary Base Damage
            currentPrimaryWeaponData[19] = "N/A"; // Secondary Effective Range
            currentPrimaryWeaponData[20] = "-1"; // Secondary Accuracy Threshold
            currentPrimaryWeaponData[21] = "N/A"; // Secondary Critical Hit Capability
            currentPrimaryWeaponData[22] = "-1"; // Secondary Critical Hit Threshold
            currentPrimaryWeaponData[23] = "-1"; // Secondary Critical Hit Multiplier
            currentPrimaryWeaponData[24] = "N/A"; // Primary Ammunition Caliber
            currentPrimaryWeaponData[25] = "N/A"; //Primary Ammunition Current Subtype
            currentPrimaryWeaponData[26] = "N/A"; // Primary Magazine Type
            currentPrimaryWeaponData[27] = "-1"; // Primary Magazine Current Load
            currentPrimaryWeaponData[28] = "-1"; // Primary Magazine Capacity
            currentPrimaryWeaponData[29] = "N/A"; // Primary Ammo Unit of Measurement
            currentPrimaryWeaponData[30] = "N/A"; // Primary Firemodes
            currentPrimaryWeaponData[31] = "N/A"; // Primary Current Firemode
            currentPrimaryWeaponData[32] = "N/A"; // Primary Chamber Current Status
            currentPrimaryWeaponData[33] = "-1"; // Primary Chamber Current Load
            currentPrimaryWeaponData[34] = "-1"; // Primary Chamber Capacity
            currentPrimaryWeaponData[35] = "-1"; // Primary Minimum Burst Length
            currentPrimaryWeaponData[36] = "-1"; // Primary Maximum Burst Length
            currentPrimaryWeaponData[37] = "N/A"; // Secondary Ammunition Caliber
            currentPrimaryWeaponData[38] = "N/A"; // Secondary Ammunition Current Subtype
            currentPrimaryWeaponData[39] = "N/A"; // Secondary Magazine Type
            currentPrimaryWeaponData[40] = "-1"; // Secondary Magazine Current Load
            currentPrimaryWeaponData[41] = "-1"; // Secondary Magazine Capacity
            currentPrimaryWeaponData[42] = "N/A"; // Secondary Ammo Unit of Measurement
            currentPrimaryWeaponData[43] = "N/A"; // Secondary Firemodes
            currentPrimaryWeaponData[44] = "N/A"; // Secondary Current Firemode
            currentPrimaryWeaponData[45] = "N/A"; // Secondary Chamber Current Status
            currentPrimaryWeaponData[46] = "-1"; // Secondary Chamber Current Load
            currentPrimaryWeaponData[47] = "-1"; // Secondary Chamber Capacity
            currentPrimaryWeaponData[48] = "-1"; // Secondary Minimum Burst Length
            currentPrimaryWeaponData[49] = "-1"; // Secondary Minimum Burst Length

            currentPrimaryWeaponData[50] = "Your fists. For when you need a hands-on approach.";
            currentPrimaryWeaponData[51] = "Hand-to-hand combat is an art that is as ancient as life itself. Perhaps, with enough practice, you'll be able to master it in your own right.";
            currentPrimaryWeaponData[52] = "[SILENT]";
            currentPrimaryWeaponData[53] = "N/A";
        } else {
            currentPrimaryWeaponData = weaponRead(player, player.getPrimaryWeapon());
        }

        if (player.getSecondaryWeapon().equals("SWFM")){
            currentSecondaryWeaponData[0] = "FISTS"; // Name
            currentSecondaryWeaponData[1] = "001"; // Weapon Frame Database ID Number
            currentSecondaryWeaponData[2] = "ENVIRONMENTAL MANIPULATOR/TRADITIONAL COMBAT IMPLEMENT"; // Role
            currentSecondaryWeaponData[3] = Integer.toString(modCalc(Integer.parseInt(player.getUserDexterity()))); // Familiarity Rating
            currentSecondaryWeaponData[4] = "N/A"; // Manufacturer
            currentSecondaryWeaponData[5] = "$"; // Component Slots
            currentSecondaryWeaponData[6] = "MELEE"; // Weapon Type
            currentSecondaryWeaponData[7] = "UNARMED"; // Weapon Class
            currentSecondaryWeaponData[8] = "NORMAL"; // Weapon Status
            currentSecondaryWeaponData[9] = "N"; // Custom Name Flag
            currentSecondaryWeaponData[10] = "MELEE"; // Primary Attack Type
            currentSecondaryWeaponData[11] = player.getUserStrength(); // Primary Base Damage
            currentSecondaryWeaponData[12] = "PB"; // Primary Effective Range
            if ((20-Integer.parseInt(player.getUserDexterity())) < 0){
                currentSecondaryWeaponData [13] = "1"; // Primary Accuracy Threshold
            } else {
                currentSecondaryWeaponData [13] = Integer.toString(20-Integer.parseInt(player.getUserDexterity()));
            }
            if (Integer.parseInt(player.getUserDexterity()) >= 2){
                currentSecondaryWeaponData[14] = "J"; // Primary Critical Hit Capability
            } else {
                currentSecondaryWeaponData[14] = "N";
            }
            if ((20-Integer.parseInt(player.getUserDexterity())) < 0){
                currentSecondaryWeaponData [15] = "1"; // Primary Crit Threshold
            } else {
                currentSecondaryWeaponData [15] = Integer.toString(20-Integer.parseInt(player.getUserDexterity()));
            }
            currentSecondaryWeaponData[16] = Integer.toString(modCalc(Integer.parseInt(player.getUserDexterity()))+1); // Primary Crit Multiplier
            currentSecondaryWeaponData[17] = "N/A"; // Secondary Attack Type
            currentSecondaryWeaponData[18] = "-1"; // Secondary Base Damage
            currentSecondaryWeaponData[19] = "N/A"; // Secondary Effective Range
            currentSecondaryWeaponData[20] = "-1"; // Secondary Accuracy Threshold
            currentSecondaryWeaponData[21] = "N/A"; // Secondary Critical Hit Capability
            currentSecondaryWeaponData[22] = "-1"; // Secondary Critical Hit Threshold
            currentSecondaryWeaponData[23] = "-1"; // Secondary Critical Hit Multiplier
            currentSecondaryWeaponData[24] = "N/A"; // Primary Ammunition Caliber
            currentSecondaryWeaponData[25] = "N/A"; //Primary Ammunition Current Subtype
            currentSecondaryWeaponData[26] = "N/A"; // Primary Magazine Type
            currentSecondaryWeaponData[27] = "-1"; // Primary Magazine Current Load
            currentSecondaryWeaponData[28] = "-1"; // Primary Magazine Capacity
            currentSecondaryWeaponData[29] = "N/A"; // Primary Ammo Unit of Measurement
            currentSecondaryWeaponData[30] = "N/A"; // Primary Firemodes
            currentSecondaryWeaponData[31] = "N/A"; // Primary Current Firemode
            currentSecondaryWeaponData[32] = "N/A"; // Primary Chamber Current Status
            currentSecondaryWeaponData[33] = "-1"; // Primary Chamber Current Load
            currentSecondaryWeaponData[34] = "-1"; // Primary Chamber Capacity
            currentSecondaryWeaponData[35] = "-1"; // Primary Minimum Burst Length
            currentSecondaryWeaponData[36] = "-1"; // Primary Maximum Burst Length
            currentSecondaryWeaponData[37] = "N/A"; // Secondary Ammunition Caliber
            currentSecondaryWeaponData[38] = "N/A"; // Secondary Ammunition Current Subtype
            currentSecondaryWeaponData[39] = "N/A"; // Secondary Magazine Type
            currentSecondaryWeaponData[40] = "-1"; // Secondary Magazine Current Load
            currentSecondaryWeaponData[41] = "-1"; // Secondary Magazine Capacity
            currentSecondaryWeaponData[42] = "N/A"; // Secondary Ammo Unit of Measurement
            currentSecondaryWeaponData[43] = "N/A"; // Secondary Firemodes
            currentSecondaryWeaponData[44] = "N/A"; // Secondary Current Firemode
            currentSecondaryWeaponData[45] = "N/A"; // Secondary Chamber Current Status
            currentSecondaryWeaponData[46] = "-1"; // Secondary Chamber Current Load
            currentSecondaryWeaponData[47] = "-1"; // Secondary Chamber Capacity
            currentSecondaryWeaponData[48] = "-1"; // Secondary Minimum Burst Length
            currentSecondaryWeaponData[49] = "-1"; // Secondary Minimum Burst Length

            currentSecondaryWeaponData[50] = "Your fists. For when you need a hands-on approach.";
            currentSecondaryWeaponData[51] = "Hand-to-hand combat is an art that is as ancient as life itself. Perhaps, with enough practice, you'll be able to master it in your own right.";
            currentSecondaryWeaponData[52] = "[SILENT]";
            currentSecondaryWeaponData[53] = "N/A";
        } else {
            currentSecondaryWeaponData = weaponRead(player, player.getSecondaryWeapon());
        }

        // \/ PRIMARY WEAPON \/
        System.out.println("//CURRENT PRIMARY WEAPON: " + currentPrimaryWeaponData[0] + " ( " + player.getPrimaryWeapon() + " )" + " | FR " + currentPrimaryWeaponData[3]);
        NoDelay();
        if (currentPrimaryWeaponData[17].equals("N/A")){
            System.out.println("\t > MAXIMUM POSSIBLE DAMAGE: " + currentPrimaryWeaponData[11]);
            NoDelay();
            System.out.print("\t > EFFECTIVE RANGE: ");
            switch (currentPrimaryWeaponData[12]){
                case "PB":
                    System.out.println("POINT BLANK");
                    break;
                case "SHORT":
                    System.out.println("SHORT RANGE");
                    break;
                case "MEDIUM":
                    System.out.println("MEDIUM RANGE");
                    break;
                case "LONG":
                    System.out.println("LONG RANGE");
                    break;
            }
            System.out.println("\t > ACCURACY THRESHOLD: " + currentPrimaryWeaponData[13]);
            NoDelay();
            if (currentPrimaryWeaponData[14].equals("J")){
                System.out.println("\t > CRITICAL HIT THRESHOLD: " + currentPrimaryWeaponData[15]);
                NoDelay();
                System.out.println("\t > CRITICAL HIT MULTIPLIER: " + currentPrimaryWeaponData[16] + "x");
            }
            if (currentPrimaryWeaponData[10].equals("RANGED")){
                if (currentPrimaryWeaponData[24].equals("Electricity")){
                    System.out.println("\t > CURRENT CAPACITOR STATUS: " + currentPrimaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > CURRENT CAPACITOR LOAD: " + Double.parseDouble(currentPrimaryWeaponData[33]) + " / " + Double.parseDouble(currentPrimaryWeaponData[34]) + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > CURRENT BATTERY TYPE: " + currentPrimaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > CURRENT BATTERY CHARGE: " + Double.parseDouble(currentPrimaryWeaponData[27]) + " / " + Double.parseDouble(currentPrimaryWeaponData[28]) + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > POSSIBLE FIREMODES: " + currentPrimaryWeaponData[30]);
                    NoDelay();
                    System.out.println("\t > CURRENT FIREMODE: " + currentPrimaryWeaponData[31]);
                    NoDelay();
                } else {
                    System.out.println("\t > CHAMBER CURRENT STATUS: " + currentPrimaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > CHAMBER CURRENT LOAD: " + (int)Double.parseDouble(currentPrimaryWeaponData[33]) + " / " + (int)Double.parseDouble(currentPrimaryWeaponData[34]) + " " + currentPrimaryWeaponData[25] + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > MAGAZINE TYPE: " + currentPrimaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > CURRENT MAGAZINE LOAD: " + (int)Double.parseDouble(currentPrimaryWeaponData[27]) + " / " + (int)Double.parseDouble(currentPrimaryWeaponData[28]) + " " + currentPrimaryWeaponData[25] + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > POSSIBLE FIREMODES: " + currentPrimaryWeaponData[30]);
                    NoDelay();
                    System.out.println("\t > CURRENT FIREMODE: " + currentPrimaryWeaponData[31]);
                    NoDelay();
                }
            }
            System.out.println("\t > SPECIAL PROPERTIES: " + currentPrimaryWeaponData[52].replace("$",""));
        } else {
            // \/ PRIMARY FIRE \/
            System.out.println("\t > PRIMARY ATTACK MAXIMUM POSSIBLE DAMAGE: " + currentPrimaryWeaponData[11]);
            NoDelay();
            System.out.print("\t > PRIMARY ATTACK EFFECTIVE RANGE: ");
            switch (currentPrimaryWeaponData[12]){
                case "PB":
                    System.out.println("POINT BLANK");
                    break;
                case "SHORT":
                    System.out.println("SHORT RANGE");
                    break;
                case "MEDIUM":
                    System.out.println("MEDIUM RANGE");
                    break;
                case "LONG":
                    System.out.println("LONG RANGE");
                    break;
            }
            System.out.println("\t > PRIMARY ATTACK ACCURACY THRESHOLD: " + currentPrimaryWeaponData[13]);
            NoDelay();
            if (currentPrimaryWeaponData[14].equals("J")){
                System.out.println("\t > PRIMARY ATTACK CRITICAL HIT THRESHOLD: " + currentPrimaryWeaponData[15]);
                NoDelay();
                System.out.println("\t > PRIMARY ATTACK CRITICAL HIT MULTIPLIER: " + currentPrimaryWeaponData[16] + "x");
            }
            if (currentPrimaryWeaponData[10].equals("RANGED")){
                if (currentPrimaryWeaponData[24].equals("Electricity")){
                    System.out.println("\t > PRIMARY CAPACITOR CURRENT STATUS: " + currentPrimaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > PRIMARY CAPACITOR CURRENT LOAD: " + Double.parseDouble(currentPrimaryWeaponData[33]) + " / " + Double.parseDouble(currentPrimaryWeaponData[34]) + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > PRIMARY BATTERY TYPE: " + currentPrimaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > PRIMARY BATTERY CURRENT CHARGE: " + Double.parseDouble(currentPrimaryWeaponData[27]) + " / " + Double.parseDouble(currentPrimaryWeaponData[28]) + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + currentPrimaryWeaponData[30]);
                    NoDelay();
                    System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + currentPrimaryWeaponData[31]);
                    NoDelay();
                } else {
                    System.out.println("\t > PRIMARY CHAMBER CURRENT STATUS: " + currentPrimaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > PRIMARY CHAMBER CURRENT LOAD: " + (int)Double.parseDouble(currentPrimaryWeaponData[33]) + " / " + (int)Double.parseDouble(currentPrimaryWeaponData[34]) + " " + currentPrimaryWeaponData[25] + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > PRIMARY MAGAZINE TYPE: " + currentPrimaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > PRIMARY MAGAZINE CURRENT LOAD: " + (int)Double.parseDouble(currentPrimaryWeaponData[27]) + " / " + (int)Double.parseDouble(currentPrimaryWeaponData[28]) + " " + currentPrimaryWeaponData[25] + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + currentPrimaryWeaponData[30]);
                    NoDelay();
                    System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + currentPrimaryWeaponData[31]);
                    NoDelay();
                }
            }
            System.out.println("\t > PRIMARY ATTACK SPECIAL PROPERTIES: " + currentPrimaryWeaponData[52].replace("$",""));
            // /\ PRIMARY FIRE /\
            System.out.println();
            ShortDelay();
            // \/ SECONDARY FIRE \/
            System.out.println("\t > SECONDARY ATTACK MAXIMUM POSSIBLE DAMAGE: " + currentPrimaryWeaponData[11]);
            NoDelay();
            System.out.print("\t > SECONDARY ATTACK EFFECTIVE RANGE: ");
            switch (currentPrimaryWeaponData[12]){
                case "PB":
                    System.out.println("POINT BLANK");
                    break;
                case "SHORT":
                    System.out.println("SHORT RANGE");
                    break;
                case "MEDIUM":
                    System.out.println("MEDIUM RANGE");
                    break;
                case "LONG":
                    System.out.println("LONG RANGE");
                    break;
            }
            System.out.println("\t > SECONDARY ATTACK ACCURACY THRESHOLD: " + currentPrimaryWeaponData[13]);
            NoDelay();
            if (currentPrimaryWeaponData[14].equals("J")){
                System.out.println("\t > SECONDARY ATTACK CRITICAL HIT THRESHOLD: " + currentPrimaryWeaponData[15]);
                NoDelay();
                System.out.println("\t > SECONDARY ATTACK CRITICAL HIT MULTIPLIER: " + currentPrimaryWeaponData[16] + "x");
            }
            if (currentPrimaryWeaponData[17].equals("RANGED")){
                if (currentPrimaryWeaponData[24].equals("Electricity")){
                    System.out.println("\t > SECONDARY CAPACITOR CURRENT STATUS: " + currentPrimaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > SECONDARY CAPACITOR CURRENT LOAD: " + Double.parseDouble(currentPrimaryWeaponData[33]) + " / " + Double.parseDouble(currentPrimaryWeaponData[34]) + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > SECONDARY BATTERY TYPE: " + currentPrimaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > SECONDARY BATTERY CURRENT CHARGE: " + Double.parseDouble(currentPrimaryWeaponData[27]) + " / " + Double.parseDouble(currentPrimaryWeaponData[28]) + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + currentPrimaryWeaponData[43]);
                    NoDelay();
                    System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + currentPrimaryWeaponData[44]);
                    NoDelay();
                } else {
                    System.out.println("\t > SECONDARY CHAMBER CURRENT STATUS: " + currentPrimaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > SECONDARY CHAMBER CURRENT LOAD: " + Integer.parseInt(currentPrimaryWeaponData[33]) + " / " + Integer.parseInt(currentPrimaryWeaponData[34]) + " " + currentPrimaryWeaponData[25] + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > SECONDARY MAGAZINE TYPE: " + currentPrimaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > SECONDARY MAGAZINE CURRENT LOAD: " + Integer.parseInt(currentPrimaryWeaponData[27]) + " / " + Integer.parseInt(currentPrimaryWeaponData[28]) + " " + currentPrimaryWeaponData[25] + " " + currentPrimaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + currentPrimaryWeaponData[43]);
                    NoDelay();
                    System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + currentPrimaryWeaponData[44]);
                    NoDelay();
                }
            }
            System.out.println("\t > SECONDARY ATTACK SPECIAL PROPERTIES: " + currentPrimaryWeaponData[53].replace("$",""));
            // /\ SECONDARY FIRE /\
        }
        // /\ PRIMARY WEAPON /\
        System.out.println();
        ShortDelay();
        // \/ SECONDARY WEAPON \/
        System.out.println("//CURRENT SECONDARY WEAPON: " + currentSecondaryWeaponData[0] + " ( " + player.getSecondaryWeapon() + " )" + " | FR " + currentSecondaryWeaponData[3]);
        NoDelay();
        if (currentSecondaryWeaponData[17].equals("N/A")){
            System.out.println("\t > MAXIMUM POSSIBLE DAMAGE: " + currentSecondaryWeaponData[11]);
            NoDelay();
            System.out.print("\t > EFFECTIVE RANGE: ");
            switch (currentSecondaryWeaponData[12]){
                case "PB":
                    System.out.println("POINT BLANK");
                    break;
                case "SHORT":
                    System.out.println("SHORT RANGE");
                    break;
                case "MEDIUM":
                    System.out.println("MEDIUM RANGE");
                    break;
                case "LONG":
                    System.out.println("LONG RANGE");
                    break;
            }
            System.out.println("\t > ACCURACY THRESHOLD: " + currentSecondaryWeaponData[13]);
            NoDelay();
            if (currentSecondaryWeaponData[14].equals("J")){
                System.out.println("\t > CRITICAL HIT THRESHOLD: " + currentSecondaryWeaponData[15]);
                NoDelay();
                System.out.println("\t > CRITICAL HIT MULTIPLIER: " + currentSecondaryWeaponData[16] + "x");
            }
            if (currentSecondaryWeaponData[10].equals("RANGED")){
                if (currentSecondaryWeaponData[24].equals("Electricity")){
                    System.out.println("\t > CURRENT CAPACITOR STATUS: " + currentSecondaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > CURRENT CAPACITOR LOAD: " + Double.parseDouble(currentSecondaryWeaponData[33]) + " / " + Double.parseDouble(currentSecondaryWeaponData[34]) + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > CURRENT BATTERY TYPE: " + currentSecondaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > CURRENT BATTERY CHARGE: " + Double.parseDouble(currentSecondaryWeaponData[27]) + " / " + Double.parseDouble(currentSecondaryWeaponData[28]) + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > POSSIBLE FIREMODES: " + currentSecondaryWeaponData[30]);
                    NoDelay();
                    System.out.println("\t > CURRENT FIREMODE: " + currentSecondaryWeaponData[31]);
                    NoDelay();
                } else {
                    System.out.println("\t > CHAMBER CURRENT STATUS: " + currentSecondaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > CHAMBER CURRENT LOAD: " + Integer.parseInt(currentSecondaryWeaponData[33]) + " / " + Integer.parseInt(currentSecondaryWeaponData[34]) + " " + currentSecondaryWeaponData[25] + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > MAGAZINE TYPE: " + currentSecondaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > MAGAZINE CURRENT LOAD: " + Integer.parseInt(currentSecondaryWeaponData[27]) + " / " + Integer.parseInt(currentSecondaryWeaponData[28]) + " " + currentSecondaryWeaponData[25] + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > POSSIBLE FIREMODES: " + currentSecondaryWeaponData[30]);
                    NoDelay();
                    System.out.println("\t > CURRENT FIREMODE: " + currentSecondaryWeaponData[31]);
                    NoDelay();
                }
            }
            System.out.println("\t > SPECIAL PROPERTIES: " + currentSecondaryWeaponData[52].replace("$",""));
        } else {
            // \/ PRIMARY FIRE \/
            System.out.println("\t > PRIMARY ATTACK MAXIMUM POSSIBLE DAMAGE: " + currentSecondaryWeaponData[11]);
            NoDelay();
            System.out.print("\t > PRIMARY ATTACK EFFECTIVE RANGE: ");
            switch (currentSecondaryWeaponData[12]){
                case "PB":
                    System.out.println("POINT BLANK");
                    break;
                case "SHORT":
                    System.out.println("SHORT RANGE");
                    break;
                case "MEDIUM":
                    System.out.println("MEDIUM RANGE");
                    break;
                case "LONG":
                    System.out.println("LONG RANGE");
                    break;
            }
            System.out.println("\t > PRIMARY ATTACK ACCURACY THRESHOLD: " + currentSecondaryWeaponData[13]);
            NoDelay();
            if (currentSecondaryWeaponData[14].equals("J")){
                System.out.println("\t > PRIMARY ATTACK CRITICAL HIT THRESHOLD: " + currentSecondaryWeaponData[15]);
                NoDelay();
                System.out.println("\t > PRIMARY ATTACK CRITICAL HIT MULTIPLIER: " + currentSecondaryWeaponData[16] + "x");
            }
            if (currentSecondaryWeaponData[10].equals("RANGED")){
                if (currentSecondaryWeaponData[24].equals("Electricity")){
                    System.out.println("\t > PRIMARY CAPACITOR CURRENT STATUS: " + currentSecondaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > PRIMARY CAPACITOR CURRENT LOAD: " + Double.parseDouble(currentSecondaryWeaponData[33]) + " / " + Double.parseDouble(currentSecondaryWeaponData[34]) + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > PRIMARY BATTERY TYPE: " + currentSecondaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > PRIMARY BATTERY CURRENT CHARGE: " + Double.parseDouble(currentSecondaryWeaponData[27]) + " / " + Double.parseDouble(currentSecondaryWeaponData[28]) + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + currentSecondaryWeaponData[30]);
                    NoDelay();
                    System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + currentSecondaryWeaponData[31]);
                    NoDelay();
                } else {
                    System.out.println("\t > PRIMARY CHAMBER CURRENT STATUS: " + currentSecondaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > PRIMARY CHAMBER CURRENT LOAD: " + Integer.parseInt(currentSecondaryWeaponData[33]) + " / " + Integer.parseInt(currentSecondaryWeaponData[34]) + " " + currentSecondaryWeaponData[25] + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > PRIMARY MAGAZINE TYPE: " + currentSecondaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > PRIMARY MAGAZINE CURRENT LOAD: " + Integer.parseInt(currentSecondaryWeaponData[27]) + " / " + Integer.parseInt(currentSecondaryWeaponData[28]) + " " + currentSecondaryWeaponData[25] + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + currentSecondaryWeaponData[30]);
                    NoDelay();
                    System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + currentSecondaryWeaponData[31]);
                    NoDelay();
                }
            }
            System.out.println("\t > PRIMARY ATTACK SPECIAL PROPERTIES: " + currentSecondaryWeaponData[52].replace("$",""));
            // /\ PRIMARY FIRE /\
            System.out.println();
            ShortDelay();
            // \/ SECONDARY FIRE \/
            System.out.println("\t > SECONDARY ATTACK MAXIMUM POSSIBLE DAMAGE: " + currentSecondaryWeaponData[11]);
            NoDelay();
            System.out.print("\t > SECONDARY ATTACK EFFECTIVE RANGE: ");
            switch (currentSecondaryWeaponData[12]){
                case "PB":
                    System.out.println("POINT BLANK");
                    break;
                case "SHORT":
                    System.out.println("SHORT RANGE");
                    break;
                case "MEDIUM":
                    System.out.println("MEDIUM RANGE");
                    break;
                case "LONG":
                    System.out.println("LONG RANGE");
                    break;
            }
            System.out.println("\t > SECONDARY ATTACK ACCURACY THRESHOLD: " + currentSecondaryWeaponData[13]);
            NoDelay();
            if (currentSecondaryWeaponData[14].equals("J")){
                System.out.println("\t > SECONDARY ATTACK CRITICAL HIT THRESHOLD: " + currentSecondaryWeaponData[15]);
                NoDelay();
                System.out.println("\t > SECONDARY ATTACK CRITICAL HIT MULTIPLIER: " + currentSecondaryWeaponData[16] + "x");
            }
            if (currentSecondaryWeaponData[17].equals("RANGED")){
                if (currentSecondaryWeaponData[24].equals("Electricity")){
                    System.out.println("\t > SECONDARY CAPACITOR CURRENT STATUS: " + currentSecondaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > SECONDARY CAPACITOR CURRENT LOAD: " + Double.parseDouble(currentSecondaryWeaponData[33]) + " / " + Double.parseDouble(currentSecondaryWeaponData[34]) + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > SECONDARY BATTERY TYPE: " + currentSecondaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > SECONDARY BATTERY CURRENT CHARGE: " + Double.parseDouble(currentSecondaryWeaponData[27]) + " / " + Double.parseDouble(currentSecondaryWeaponData[28]) + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + currentSecondaryWeaponData[43]);
                    NoDelay();
                    System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + currentSecondaryWeaponData[44]);
                    NoDelay();
                } else {
                    System.out.println("\t > SECONDARY CHAMBER CURRENT STATUS: " + currentSecondaryWeaponData[32]);
                    NoDelay();
                    System.out.println("\t > SECONDARY CHAMBER CURRENT LOAD: " + Integer.parseInt(currentSecondaryWeaponData[33]) + " / " + Integer.parseInt(currentSecondaryWeaponData[34]) + " " + currentSecondaryWeaponData[25] + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > SECONDARY MAGAZINE TYPE: " + currentSecondaryWeaponData[26]);
                    NoDelay();
                    System.out.println("\t > SECONDARY MAGAZINE CURRENT LOAD: " + Integer.parseInt(currentSecondaryWeaponData[27]) + " / " + Integer.parseInt(currentSecondaryWeaponData[28]) + " " + currentSecondaryWeaponData[25] + " " + currentSecondaryWeaponData[29]);
                    NoDelay();
                    System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + currentSecondaryWeaponData[43]);
                    NoDelay();
                    System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + currentSecondaryWeaponData[44]);
                    NoDelay();
                }
            }
            System.out.println("\t > SECONDARY ATTACK SPECIAL PROPERTIES: " + currentPrimaryWeaponData[53].replace("$",""));
            // /\ SECONDARY FIRE /\
        }
        // /\ SECONDARY WEAPON /\

        System.out.println();
        MedDelay();
        System.out.println("//PLEASE CHOOSE COURSE OF ACTION");
        ShortDelay();
        System.out.println("> 0_ ATTACK [ANGF]");
        NoDelay();
        System.out.println("> 1_ ACTION [AKTN]");
        NoDelay();
        System.out.println("> 2_ WEAPONS [WFFN]");
        NoDelay();
        System.out.println("> 3_ ITEMS [ATKL]");
        NoDelay();
        System.out.println("> 4_ EDIT [BRBT]");
        NoDelay();
        System.out.println("> 5_ NEXT CYCLE [NZYK]");
        NoDelay();
        System.out.println("> 6_ RESET CYCLES [ZZYK]");
        NoDelay();
        System.out.println("> A_ REFRESH [AKTL]");
        NoDelay();
        System.out.println("> ._ EXIT [AUSF]");
        boolean optionNotChosen = true;
        while ( optionNotChosen ) {
            switch ( input.nextLine() ) {
                case "ANGREIFEN": case "angreifen": case "ANGF": case "angf": case "ATTACK": case "attack": case "0":
                    System.out.println("//PLEASE CHOOSE WEAPON TO ATTACK WITH");
                    ShortDelay();
                    if (player.getPrimaryWeapon().equals("PWFM")){
                        System.out.println("> 0_ LEFT FIST");
                    } else {
                        System.out.println("> 0_ " + player.getPrimaryWeapon());
                    }
                    NoDelay();
                    if (player.getSecondaryWeapon().equals("SWFM")){
                        System.out.println("> 1_ RIGHT FIST");
                    } else {
                        System.out.println("> 1_ " + player.getSecondaryWeapon());
                    }
                    NoDelay();
                    System.out.println("> 2_ BOTH");

                    String[] outgoingPrimaryDamageData = new String[2];
                    String[] outgoingSecondaryDamageData = new String[2];
                    outgoingPrimaryDamageData[0] = "-1";
                    outgoingPrimaryDamageData[1] = "N/A";
                    outgoingSecondaryDamageData[0] = "-1";
                    outgoingSecondaryDamageData[1] = "N/A";
                    double totalOutgoingDamage = 0;
                    boolean weaponNotSelectedForAttack = true;
                    boolean attackCancelled = false;

                    while (weaponNotSelectedForAttack){
                        switch (input.nextLine()){
                            case "0":
                                outgoingPrimaryDamageData = damageCalc(player, player.getPrimaryWeapon(), currentPrimaryWeaponData);
                                weaponNotSelectedForAttack = false;
                                break;
                            case "1":
                                outgoingSecondaryDamageData = damageCalc(player, player.getSecondaryWeapon(), currentSecondaryWeaponData);
                                weaponNotSelectedForAttack = false;
                                break;
                            case "2":
                                outgoingPrimaryDamageData = damageCalc(player, player.getPrimaryWeapon(), currentPrimaryWeaponData);
                                outgoingSecondaryDamageData = damageCalc(player, player.getSecondaryWeapon(), currentSecondaryWeaponData);
                                weaponNotSelectedForAttack = false;
                                break;
                            case ".":
                                weaponNotSelectedForAttack = false;
                                attackCancelled = true;
                                break;
                            default:
                                if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                } else if (player.getUserLanguage().equals("ENGLISH")) {
                                    System.out.println("//ERROR: INVALID INPUT");
                                }
                                break;
                        }
                    }
                    if (attackCancelled){
                        System.out.println("//ATTACK SUBMENU EXITED");
                        break;
                    }
                    if (!outgoingPrimaryDamageData[0].equals("-1")){
                        if (!outgoingPrimaryDamageData.equals("N/A")){
                            System.out.print("//PRIMARY WEAPON DAMAGE OUTPUT: " + outgoingPrimaryDamageData[0] + " " + outgoingPrimaryDamageData[1] + " DAMAGE");
                        } else {
                            System.out.print("//PRIMARY WEAPON DAMAGE OUTPUT: " + outgoingPrimaryDamageData[0] + " DAMAGE");
                        }
                        totalOutgoingDamage += Double.parseDouble(outgoingPrimaryDamageData[0]);
                        if (outgoingPrimaryDamageData[2].equals("J")){
                            ShortDelay();
                            System.out.print("\t");
                            for (int i = 0; i < 4; i++){
                                System.out.print("/!\\ CRITICAL HIT /!\\");
                                NoDelay();
                                System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
                                System.out.print("                    ");
                                System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
                                NoDelay();
                            }
                            System.out.println("/!\\ CRITICAL HIT /!\\");
                        } else {
                            System.out.println();
                        }
                        ShortDelay();
                    }
                    if (!outgoingSecondaryDamageData[0].equals("-1")){
                        if (!outgoingSecondaryDamageData.equals("N/A")){
                            System.out.print("//SECONDARY WEAPON DAMAGE OUTPUT: " + outgoingSecondaryDamageData[0] + " " + outgoingSecondaryDamageData[1] + " DAMAGE");
                        } else {
                            System.out.print("//SECONDARY WEAPON DAMAGE OUTPUT: " + outgoingSecondaryDamageData[0] + " DAMAGE");
                        }
                        totalOutgoingDamage += Double.parseDouble(outgoingSecondaryDamageData[0]);
                        if (outgoingSecondaryDamageData[2].equals("J")){
                            ShortDelay();
                            System.out.print("\t");
                            for (int i = 0; i < 4; i++){
                                System.out.print("/!\\ CRITICAL HIT /!\\");
                                NoDelay();
                                System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
                                System.out.print("                    ");
                                System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
                                NoDelay();
                            }
                            System.out.println("/!\\ CRITICAL HIT /!\\");
                        } else {
                            System.out.println();
                        }
                        ShortDelay();
                    }
                    System.out.println("//TOTAL DAMAGE OUTPUT: " + Math.ceil(totalOutgoingDamage) + " ( " + totalOutgoingDamage + " ) " );
                    ShortDelay();
                    System.out.println("//USER RETURNED TO COMBAT MAIN MENU");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                case "AKTION": case "aktion": case "AKTN": case "aktn": case "ACTION": case "action": case "1":
                    System.out.println("//PLEASE CHOOSE ACTION TO PERFORM");
                    ShortDelay();
                    System.out.println("> 0_ RELOAD [NCLD]");
                    NoDelay();
                    System.out.println("> 1_ CHAMBER [KMMR]");
                    NoDelay();
                    System.out.println("> 2_ FIREMODE [FWMD]");
                    NoDelay();
                    System.out.println("> ._ EXIT [AUSF]");
                    boolean invalidAction = true;
                    while (invalidAction) {
                        switch (input.nextLine()) {
                            case "NACHLADEN": case "nachladen": case "NCLD": case "ncld": case "RELOAD": case "reload": case "0":
                                if ( ( currentPrimaryWeaponData[10].equals("MELEE") || currentPrimaryWeaponData[10].equals("N/A") ) && ( currentPrimaryWeaponData[17].equals("MELEE") || currentPrimaryWeaponData[17].equals("N/A") ) && ( currentSecondaryWeaponData[10].equals("MELEE") || currentSecondaryWeaponData[10].equals("N/A") ) && ( currentSecondaryWeaponData[17].equals("MELEE") || currentSecondaryWeaponData[17].equals("N/A") ) ){
                                    System.out.println("//ERROR: CURRENTLY EQUIPPED WEAPONS DO NOT REQUIRE AMMUNITION");
                                    ShortDelay();
                                    System.out.println("//RELOAD SUBMENU EXITED");
                                    invalidAction = false;
                                    break;
                                } else {
                                    System.out.println("//PLEASE SELECT A WEAPON TO RELOAD");
                                    ShortDelay();
                                    if ( currentPrimaryWeaponData[10].equals("RANGED") || currentPrimaryWeaponData[10].equals("HYBRID") ){
                                        System.out.println("> 0_ " + player.getPrimaryWeapon());
                                        NoDelay();
                                    }
                                    if ( currentSecondaryWeaponData[10].equals("RANGED") || currentSecondaryWeaponData[10].equals("HYBRID") ){
                                        System.out.println("> 1_ " + player.getSecondaryWeapon());
                                        NoDelay();
                                    }
                                }
                                System.out.println("> ._ EXIT [AUSF]");
                                String[] reloadChosenWeaponData = new String[54];
                                String reloadChosenWeaponSlot = "";
                                boolean weaponReloadNotChosen = true;
                                boolean weaponReloadCancelled = false;
                                while (weaponReloadNotChosen){
                                    switch (input.nextLine()){
                                        case "0":
                                            if ( (currentPrimaryWeaponData[10].equals("MELEE") || currentPrimaryWeaponData[10].equals("N/A") ) && (currentPrimaryWeaponData[17].equals("MELEE") || currentPrimaryWeaponData[17].equals("N/A") ) ){
                                                System.out.println("//ERROR: WEAPON DOES NOT USE AMMUNITION");
                                            } else {
                                                reloadChosenWeaponData = currentPrimaryWeaponData;
                                                reloadChosenWeaponSlot = "PRIMARY";
                                                weaponReloadNotChosen = false;
                                            }
                                            break;
                                        case "1":
                                            if ( (currentSecondaryWeaponData[10].equals("MELEE") || currentSecondaryWeaponData[10].equals("N/A") ) && (currentSecondaryWeaponData[17].equals("MELEE") || currentSecondaryWeaponData[17].equals("N/A") ) ){
                                                System.out.println("//ERROR: WEAPON DOES NOT USE AMMUNITION");
                                            } else {
                                                reloadChosenWeaponData = currentSecondaryWeaponData;
                                                reloadChosenWeaponSlot = "SECONDARY";
                                                weaponReloadNotChosen = false;
                                            }
                                            break;
                                        case ".":
                                            weaponReloadCancelled = true;
                                            weaponReloadNotChosen = false;
                                            break;
                                        default:
                                            if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                                                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                            } else if (player.getUserLanguage().equals("ENGLISH")) {
                                                System.out.println("//ERROR: INVALID INPUT");
                                            }
                                            break;
                                    }
                                }

                                if (weaponReloadCancelled){
                                    System.out.println("//RELOAD SUBMENU EXITED");
                                    break;
                                }

                                if (!reloadChosenWeaponData[17].equals("RANGED") || reloadChosenWeaponData[10].equals("RANGED")){
                                    System.out.println("AMMUNITION CALIBER: " + reloadChosenWeaponData[24]);
                                    NoDelay();
                                    if (reloadChosenWeaponData[22].equals("Electricity")){
                                        System.out.println("\t > CURRENT CHAMBER STATUS: " + reloadChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT CHAMBER LOAD: " + Double.parseDouble(reloadChosenWeaponData[33]) + " / " + Double.parseDouble(reloadChosenWeaponData[34]) + " " + reloadChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT BATTERY TYPE: " + reloadChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT BATTERY CHARGE: " + Double.parseDouble(reloadChosenWeaponData[27]) + " / " + Double.parseDouble(reloadChosenWeaponData[28]) + " " + reloadChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > POSSIBLE FIREMODES: " + reloadChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT FIREMODE: " + reloadChosenWeaponData[31]);
                                        NoDelay();
                                    } else {
                                        System.out.println("\t > CURRENT CHAMBER STATUS: " + reloadChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT CHAMBER LOAD: " + (int)Double.parseDouble(reloadChosenWeaponData[33]) + " / " + (int)Double.parseDouble(reloadChosenWeaponData[34]) + " " + reloadChosenWeaponData[25] + " " + reloadChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT MAGAZINE TYPE: " + reloadChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT MAGAZINE LOAD: " + (int)Double.parseDouble(reloadChosenWeaponData[27]) + " / " + (int)Double.parseDouble(reloadChosenWeaponData[28]) + " " + reloadChosenWeaponData[25] + " " + reloadChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > POSSIBLE FIREMODES: " + reloadChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT FIREMODE: " + reloadChosenWeaponData[31]);
                                        NoDelay();
                                    }
                                } else {
                                    System.out.println("PRIMARY AMMUNITION CALIBER: " + reloadChosenWeaponData[26]);
                                    NoDelay();
                                    if (reloadChosenWeaponData[24].equals("Electricity")){
                                        System.out.println("\t > CURRENT PRIMARY CAPACITOR STATUS: " + reloadChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY CAPACITOR LOAD: " + Double.parseDouble(reloadChosenWeaponData[33]) + " / " + Double.parseDouble(reloadChosenWeaponData[34]) + " " + reloadChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY BATTERY TYPE: " + reloadChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY BATTERY CHARGE: " + Double.parseDouble(reloadChosenWeaponData[27]) + " / " + Double.parseDouble(reloadChosenWeaponData[28]) + " " + reloadChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + reloadChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + reloadChosenWeaponData[31]);
                                        NoDelay();
                                    } else {
                                        System.out.println("\t > CURRENT PRIMARY CHAMBER STATUS: " + reloadChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY CHAMBER LOAD: " + (int)Double.parseDouble(reloadChosenWeaponData[33]) + " / " + (int)Double.parseDouble(reloadChosenWeaponData[34]) + " " + reloadChosenWeaponData[25] + " " + reloadChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY MAGAZINE TYPE: " + reloadChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY MAGAZINE LOAD: " + (int)Double.parseDouble(reloadChosenWeaponData[27]) + " / " + (int)Double.parseDouble(reloadChosenWeaponData[28]) + " " + reloadChosenWeaponData[25] + " " + reloadChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + reloadChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + reloadChosenWeaponData[31]);
                                        NoDelay();
                                    }
                                    System.out.println("SECONDARY AMMUNITION CALIBER: " + reloadChosenWeaponData[37]);
                                    NoDelay();
                                    if (reloadChosenWeaponData[24].equals("Electricity")){
                                        System.out.println("\t > CURRENT SECONDARY CAPACITOR STATUS: " + reloadChosenWeaponData[45]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY CAPACITOR LOAD: " + Double.parseDouble(reloadChosenWeaponData[46]) + " / " + Double.parseDouble(reloadChosenWeaponData[47]) + " " + reloadChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY BATTERY TYPE: " + reloadChosenWeaponData[39]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY BATTERY CHARGE: " + Double.parseDouble(reloadChosenWeaponData[40]) + " / " + Double.parseDouble(reloadChosenWeaponData[41]) + " " + reloadChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + currentPrimaryWeaponData[43]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + currentPrimaryWeaponData[44]);
                                        NoDelay();
                                    } else {
                                        System.out.println("\t > CURRENT SECONDARY CHAMBER STATUS: " + reloadChosenWeaponData[45]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY CHAMBER LOAD: " + (int)Double.parseDouble(reloadChosenWeaponData[46]) + " / " + (int)Double.parseDouble(reloadChosenWeaponData[47]) + " " + reloadChosenWeaponData[38] + " " + reloadChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY MAGAZINE TYPE: " + reloadChosenWeaponData[39]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY MAGAZINE LOAD: " + (int)Double.parseDouble(reloadChosenWeaponData[40]) + " / " + (int)Double.parseDouble(reloadChosenWeaponData[41]) + " " + reloadChosenWeaponData[38] + " " + reloadChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + reloadChosenWeaponData[43]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + reloadChosenWeaponData[44]);
                                        NoDelay();
                                    }
                                }
                                String reloadChosenSubsystem = "";
                                boolean reloadSubsystemCancel = false;
                                if (reloadChosenWeaponData[10].equals("RANGED") && reloadChosenWeaponData[17].equals("RANGED")){
                                    ShortDelay();
                                    System.out.println("//PLEASE SELECT SUBSYSTEM OF " + reloadChosenWeaponData[0] + " FOR RELOAD");
                                    ShortDelay();
                                    System.out.println("> 0_ PRIMARY [PRMR]");
                                    NoDelay();
                                    System.out.println("> 1_ SECONDARY [SKDR]");
                                    NoDelay();
                                    boolean weaponSubsystemNotChosen = true;
                                    while (weaponSubsystemNotChosen){
                                        switch (input.nextLine()){
                                            case "0":
                                                reloadChosenSubsystem = "PRIMARY";
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            case "1":
                                                reloadChosenSubsystem = "SECONDARY";
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            case ".":
                                                reloadSubsystemCancel = true;
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            default:
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")) {
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                                break;
                                        }
                                    }
                                } else {
                                    if (reloadChosenWeaponData[10].equals("RANGED")){
                                        reloadChosenSubsystem = "PRIMARY";
                                    } else if (reloadChosenWeaponData[17].equals("RANGED")){
                                        reloadChosenSubsystem = "SECONDARY";
                                    }
                                }

                                if (reloadSubsystemCancel){
                                    System.out.println("//RELOAD SUBMENU EXITED");
                                    break;
                                }

                                File[] ammoTypes;
                                File ammoTypeTag;
                                String chosenAmmoCaliber = "";
                                String unitOfMeasurement = "";
                                String[] tempAmmoData;

                                double reloadCurrentChamberLoad = 0;
                                double reloadCurrentMagLoad = 0;
                                double reloadCurrentMagCap = 0;
                                double magAmmoToLoad = 0;
                                double ammoToUnloadReserve = 0;
                                double ammoToLoadReserve = 0;

                                if (reloadChosenSubsystem.equals("PRIMARY")){
                                    ammoTypes = new File(inventory[2]+"\\"+reloadChosenWeaponData[24]).listFiles(File::isFile);
                                    chosenAmmoCaliber = reloadChosenWeaponData[24];
                                    unitOfMeasurement = reloadChosenWeaponData[29];
                                    reloadCurrentChamberLoad = Double.parseDouble(reloadChosenWeaponData[33]);
                                    reloadCurrentMagLoad = Double.parseDouble(reloadChosenWeaponData[27]);
                                    reloadCurrentMagCap = Double.parseDouble(reloadChosenWeaponData[28]);
                                } else if (reloadChosenSubsystem.equals("SECONDARY")){
                                    ammoTypes = new File(inventory[2]+"\\"+reloadChosenWeaponData[37]).listFiles(File::isFile);
                                    chosenAmmoCaliber = reloadChosenWeaponData[37];
                                    unitOfMeasurement = reloadChosenWeaponData[42];
                                    reloadCurrentChamberLoad = Double.parseDouble(reloadChosenWeaponData[46]);
                                    reloadCurrentMagLoad = Double.parseDouble(reloadChosenWeaponData[40]);
                                    reloadCurrentMagCap = Double.parseDouble(reloadChosenWeaponData[41]);
                                } else {
                                    ammoTypes = new File[0];
                                    chosenAmmoCaliber = "AMMO FOLDER MISSING";
                                    unitOfMeasurement = "u";
                                }

                                NoDelay();
                                System.out.println();
                                ShortDelay();
                                System.out.println("//PLEASE ENTER AMMO SUBTYPE TO BE LOADED");
                                ShortDelay();

                                String ammoDispName;
                                double ammoCount;
                                if (ammoTypes.length != 0){
                                    for (int i = 0; i < ammoTypes.length; i++){
                                        ammoTypeTag = new File( ammoTypes[i].toString() );
                                        if (ammoTypeTag.exists()){
                                            tempAmmoData = ammoRead ( player, chosenAmmoCaliber, ammoTypes[i].getName());
                                            ammoDispName = tempAmmoData[0];
                                            ammoCount = Double.parseDouble(tempAmmoData[1]);
                                            if (chosenAmmoCaliber.equals("Electricity")){
                                                System.out.println("> " + i + "_ " + ammoCount + "x " + unitOfMeasurement);
                                            } else {
                                                System.out.println("> " + i + "_ " + (int)ammoCount + "x " + ammoDispName + " " + unitOfMeasurement);
                                            }
                                            NoDelay();
                                        }
                                    }
                                } else {
                                    LongDelay();
                                    System.out.println("ERROR");
                                    ShortDelay();
                                    System.out.println("//UNABLE TO READ DATA ON SUBTYPE RESERVES");
                                    ShortDelay();
                                }

                                boolean subtypeNotChosen = true;
                                String subtypeToLoad = "";
                                String inputSubtype = "";
                                int chosenAmmoIndex = -1;
                                while (subtypeNotChosen){
                                    inputSubtype = input.nextLine();
                                    switch (inputSubtype){
                                        case ".":
                                            subtypeToLoad = inputSubtype;
                                            subtypeNotChosen = false;
                                            break;
                                        default:
                                            try {
                                                chosenAmmoIndex = Integer.parseInt(inputSubtype);
                                                if (0 <= chosenAmmoIndex && chosenAmmoIndex < ammoTypes.length){
                                                    subtypeToLoad = ammoTypes[chosenAmmoIndex].getName();
                                                    subtypeNotChosen = false;
                                                }
                                            } catch (NumberFormatException notNumber){
                                                chosenAmmoIndex = -1;
                                                for (int i = 0; i < ammoTypes.length; i++){
                                                    if (ammoTypes[i].getName().equals(inputSubtype+".TAG")){
                                                        chosenAmmoIndex = i;
                                                    }
                                                }
                                                if (chosenAmmoIndex == -1){
                                                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                    } else if (player.getUserLanguage().equals("ENGLISH")){
                                                        System.out.println("//ERROR: INVALID INPUT");
                                                    }
                                                } else {
                                                    subtypeToLoad = ammoTypes[chosenAmmoIndex].getName();
                                                    subtypeNotChosen = false;
                                                }
                                            }
                                            break;

                                    }
                                }

                                if (subtypeToLoad.equals(".")){
                                    System.out.println("//RELOAD SUBMENU EXITED");
                                    break;
                                }

                                if ( (reloadCurrentMagCap - reloadCurrentMagLoad == 0) || (reloadCurrentMagLoad == 0) ){
                                    magAmmoToLoad = reloadCurrentMagCap;
                                } else {
                                    if ( (reloadChosenSubsystem.equals("PRIMARY") && reloadChosenWeaponData[25].equals(subtypeToLoad)) || (reloadChosenSubsystem.equals("SECONDARY") && reloadChosenWeaponData[38].equals(subtypeToLoad)) ) {
                                        magAmmoToLoad = reloadCurrentMagCap-reloadCurrentMagLoad;
                                    } else {
                                        magAmmoToLoad = reloadCurrentMagCap;
                                    }
                                }

                                int loadedAmmoIndex = -1;
                                for (int i = 0; i < ammoTypes.length; i++){
                                    if (reloadChosenSubsystem.equals("PRIMARY")){
                                        if (ammoTypes[i].getName().equals(reloadChosenWeaponData[25]+".TAG")){
                                            loadedAmmoIndex = i;
                                        }
                                    } else {
                                        if (ammoTypes[i].getName().equals(reloadChosenWeaponData[38]+".TAG")){
                                            loadedAmmoIndex = i;
                                        }
                                    }
                                }

                                String[] ammoToUnloadData = ammoRead( player, chosenAmmoCaliber, reloadChosenWeaponData[25]+".TAG");
                                ammoToUnloadReserve = Double.parseDouble(ammoToUnloadData[1]);
                                ammoToUnloadReserve += reloadCurrentChamberLoad + reloadCurrentMagLoad;
                                reloadCurrentMagLoad = 0;
                                ammoToUnloadData[1] = Double.toString(ammoToUnloadReserve);
                                ammoWrite( player, chosenAmmoCaliber, loadedAmmoIndex ,ammoToUnloadData);

                                String[] ammoToLoadData = ammoRead( player, chosenAmmoCaliber, subtypeToLoad);
                                ammoToLoadReserve = Double.parseDouble(ammoToLoadData[1]);
                                if (ammoToLoadReserve - magAmmoToLoad <= 0){
                                    magAmmoToLoad = ammoToLoadReserve;
                                    ammoToLoadReserve = 0;
                                } else {
                                    ammoToLoadReserve -= magAmmoToLoad;
                                }
                                ammoToLoadData[1] = Double.toString(ammoToLoadReserve);
                                ammoWrite ( player, chosenAmmoCaliber, chosenAmmoIndex, ammoToLoadData);



                                if ( reloadChosenWeaponSlot.equals("PRIMARY") ){
                                    if (reloadChosenSubsystem.equals("PRIMARY")){
                                        reloadChosenWeaponData[27] = Double.toString(reloadCurrentMagLoad+magAmmoToLoad);
                                        if (!(reloadChosenWeaponData[25]).equals(ammoToLoadData[0])){
                                            reloadChosenWeaponData[32] = "EMPTY";
                                            reloadChosenWeaponData[33] = "0";
                                            reloadChosenWeaponData[25] = ammoToLoadData[0];
                                        }
                                        weaponWrite( player, player.getPrimaryWeapon(), reloadChosenWeaponData);
                                    } else {
                                        reloadChosenWeaponData[40] = Double.toString(reloadCurrentMagLoad+magAmmoToLoad);
                                        if (!(reloadChosenWeaponData[25]).equals(ammoToLoadData[0])){
                                            reloadChosenWeaponData[45] = "EMPTY";
                                            reloadChosenWeaponData[46] = "0";
                                            reloadChosenWeaponData[38] = ammoToLoadData[0];
                                        }
                                        weaponWrite( player, player.getPrimaryWeapon(), reloadChosenWeaponData);
                                    }
                                } else {
                                    if (reloadChosenSubsystem.equals("PRIMARY")){
                                        reloadChosenWeaponData[27] = Double.toString(reloadCurrentMagLoad+magAmmoToLoad);
                                        if (!(reloadChosenWeaponData[25]).equals(ammoToLoadData[0])){
                                            reloadChosenWeaponData[32] = "EMPTY";
                                            reloadChosenWeaponData[33] = "0";
                                            reloadChosenWeaponData[25] = ammoToLoadData[0];
                                        }
                                        weaponWrite( player, player.getSecondaryWeapon(), reloadChosenWeaponData);
                                    } else {
                                        reloadChosenWeaponData[40] = Double.toString(reloadCurrentMagLoad+magAmmoToLoad);
                                        if (!(reloadChosenWeaponData[25]).equals(ammoToLoadData[0])){
                                            reloadChosenWeaponData[45] = "EMPTY";
                                            reloadChosenWeaponData[46] = "0";
                                            reloadChosenWeaponData[38] = ammoToLoadData[0];
                                        }
                                        weaponWrite( player, player.getSecondaryWeapon(), reloadChosenWeaponData);
                                    }
                                }
                                ShortDelay();
                                System.out.println("//WEAPON RELOADED");
                                ShortDelay();
                                System.out.println("//USER RETURNED TO COMBAT MAIN MENU");
                                ShortDelay();
                                System.out.println("//INPUT \"A\" TO REFRESH");
                                invalidAction = false;

                                break;
                            case "KAMMER": case "kammer": case "KMMR": case "kmmr": case "CHAMBER": case "chamber": case "1":
                                if ( ( currentPrimaryWeaponData[10].equals("MELEE") || currentPrimaryWeaponData[10].equals("N/A") ) && ( currentPrimaryWeaponData[17].equals("MELEE") || currentPrimaryWeaponData[17].equals("N/A") ) && ( currentSecondaryWeaponData[10].equals("MELEE") || currentSecondaryWeaponData[10].equals("N/A") ) && ( currentSecondaryWeaponData[17].equals("MELEE") || currentSecondaryWeaponData[17].equals("N/A") ) ){
                                    System.out.println("//ERROR: CURRENTLY EQUIPPED WEAPONS CANNOT BE CHAMBERED");
                                    ShortDelay();
                                    System.out.println("//CHAMBER SUBMENU EXITED");
                                    invalidAction = false;
                                    break;
                                } else {
                                    System.out.println("//PLEASE SELECT A WEAPON TO CHAMBER");
                                    ShortDelay();
                                    if ( currentPrimaryWeaponData[10].equals("RANGED") || currentPrimaryWeaponData[10].equals("HYBRID") ){
                                        System.out.println("> 0_ " + player.getPrimaryWeapon());
                                        NoDelay();
                                    }
                                    if ( currentSecondaryWeaponData[10].equals("RANGED") || currentSecondaryWeaponData[10].equals("HYBRID") ){
                                        System.out.println("> 1_ " + player.getSecondaryWeapon());
                                        NoDelay();
                                    }
                                }
                                System.out.println("> ._ EXIT [AUSF]");

                                String[] chamberChosenWeaponData = new String[54];
                                String chamberChosenWeaponSlot = "";
                                boolean weaponChamberNotChosen = true;
                                boolean weaponChamberCancelled = false;
                                while (weaponChamberNotChosen){
                                    switch (input.nextLine()){
                                        case "0":
                                            if ( (currentPrimaryWeaponData[10].equals("MELEE") || currentPrimaryWeaponData[10].equals("N/A") ) && (currentPrimaryWeaponData[17].equals("MELEE") || currentPrimaryWeaponData[17].equals("N/A") ) ){
                                                System.out.println("//ERROR: WEAPON DOES NOT USE AMMUNITION");
                                            } else {
                                                chamberChosenWeaponData = currentPrimaryWeaponData;
                                                chamberChosenWeaponSlot = "PRIMARY";
                                                weaponChamberNotChosen = false;
                                            }
                                            break;
                                        case "1":
                                            if ( (currentSecondaryWeaponData[10].equals("MELEE") || currentSecondaryWeaponData[10].equals("N/A") ) && (currentSecondaryWeaponData[17].equals("MELEE") || currentSecondaryWeaponData[17].equals("N/A") ) ){
                                                System.out.println("//ERROR: WEAPON DOES NOT USE AMMUNITION");
                                            } else {
                                                chamberChosenWeaponData = currentSecondaryWeaponData;
                                                chamberChosenWeaponSlot = "SECONDARY";
                                                weaponChamberNotChosen = false;
                                            }
                                            break;
                                        case ".":
                                            weaponChamberCancelled = true;
                                            weaponChamberNotChosen = false;
                                            break;
                                        default:
                                            if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                                                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                            } else if (player.getUserLanguage().equals("ENGLISH")) {
                                                System.out.println("//ERROR: INVALID INPUT");
                                            }
                                            break;
                                    }
                                }

                                if (weaponChamberCancelled){
                                    System.out.println("//CHAMBER SUBMENU EXITED");
                                    break;
                                }

                                String chamberChosenSubsystem = "";
                                boolean chamberSubsystemCancel = false;
                                if (chamberChosenWeaponData[10].equals("RANGED") && chamberChosenWeaponData[17].equals("RANGED")){
                                    System.out.println();
                                    System.out.println("PRIMARY AMMUNITION CALIBER: " + chamberChosenWeaponData[26]);
                                    NoDelay();
                                    if (chamberChosenWeaponData[24].equals("Electricity")){
                                        System.out.println("\t > CURRENT PRIMARY CAPACITOR STATUS: " + chamberChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY CAPACITOR LOAD: " + Double.parseDouble(chamberChosenWeaponData[33]) + " / " + Double.parseDouble(chamberChosenWeaponData[34]) + " " + chamberChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY BATTERY TYPE: " + chamberChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY BATTERY CHARGE: " + Double.parseDouble(chamberChosenWeaponData[27]) + " / " + Double.parseDouble(chamberChosenWeaponData[28]) + " " + chamberChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + chamberChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + chamberChosenWeaponData[31]);
                                        NoDelay();
                                    } else {
                                        System.out.println("\t > CURRENT PRIMARY CHAMBER STATUS: " + chamberChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY CHAMBER LOAD: " + (int)Double.parseDouble(chamberChosenWeaponData[33]) + " / " + (int)Double.parseDouble(chamberChosenWeaponData[34]) + " " + chamberChosenWeaponData[25] + " " + chamberChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY MAGAZINE TYPE: " + chamberChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY MAGAZINE LOAD: " + (int)Double.parseDouble(chamberChosenWeaponData[27]) + " / " + (int)Double.parseDouble(chamberChosenWeaponData[28]) + " " + chamberChosenWeaponData[25] + " " + chamberChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + chamberChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + chamberChosenWeaponData[31]);
                                        NoDelay();
                                    }
                                    System.out.println("SECONDARY AMMUNITION CALIBER: " + chamberChosenWeaponData[37]);
                                    NoDelay();
                                    if (chamberChosenWeaponData[24].equals("Electricity")){
                                        System.out.println("\t > CURRENT SECONDARY CAPACITOR STATUS: " + chamberChosenWeaponData[45]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY CAPACITOR LOAD: " + Double.parseDouble(chamberChosenWeaponData[46]) + " / " + Double.parseDouble(chamberChosenWeaponData[47]) + " " + chamberChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY BATTERY TYPE: " + chamberChosenWeaponData[39]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY BATTERY CHARGE: " + Double.parseDouble(chamberChosenWeaponData[40]) + " / " + Double.parseDouble(chamberChosenWeaponData[41]) + " " + chamberChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + chamberChosenWeaponData[43]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + chamberChosenWeaponData[44]);
                                        NoDelay();
                                    } else {
                                        System.out.println("\t > CURRENT SECONDARY CHAMBER STATUS: " + chamberChosenWeaponData[45]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY CHAMBER LOAD: " + (int)Double.parseDouble(chamberChosenWeaponData[46]) + " / " + (int)Double.parseDouble(chamberChosenWeaponData[47]) + " " + chamberChosenWeaponData[38] + " " + chamberChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY MAGAZINE TYPE: " + chamberChosenWeaponData[39]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY MAGAZINE LOAD: " + (int)Double.parseDouble(chamberChosenWeaponData[40]) + " / " + (int)Double.parseDouble(chamberChosenWeaponData[41]) + " " + chamberChosenWeaponData[38] + " " + chamberChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + chamberChosenWeaponData[43]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + chamberChosenWeaponData[44]);
                                        NoDelay();
                                    }
                                    ShortDelay();
                                    System.out.println("//PLEASE SELECT SUBSYSTEM OF " + chamberChosenWeaponData[0] + " FOR CHAMBER");
                                    ShortDelay();
                                    System.out.println("> 0_ PRIMARY [PRMR]");
                                    NoDelay();
                                    System.out.println("> 1_ SECONDARY [SKDR]");
                                    NoDelay();
                                    boolean weaponSubsystemNotChosen = true;
                                    while (weaponSubsystemNotChosen){
                                        switch (input.nextLine()){
                                            case "0":
                                                chamberChosenSubsystem = "PRIMARY";
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            case "1":
                                                chamberChosenSubsystem = "SECONDARY";
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            case ".":
                                                chamberSubsystemCancel = true;
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            default:
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")) {
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                                break;
                                        }
                                    }
                                } else {
                                    if (chamberChosenWeaponData[10].equals("RANGED")){
                                        chamberChosenSubsystem = "PRIMARY";
                                    } else if (chamberChosenWeaponData[17].equals("RANGED")){
                                        chamberChosenSubsystem = "SECONDARY";
                                    }
                                }

                                if (chamberSubsystemCancel){
                                    System.out.println("//CHAMBER SUBMENU EXITED");
                                    break;
                                }

                                double chamberCurrentMagazineLoad = 0;
                                double chamberCurrentChamberCap = 0;
                                double chamberCurrentChamberLoad = 0;
                                double chamberAmmoToChamber = 0;

                                if (chamberChosenSubsystem.equals("PRIMARY")){
                                    chamberCurrentMagazineLoad = Double.parseDouble(chamberChosenWeaponData[27]);
                                    chamberCurrentChamberCap = Double.parseDouble(chamberChosenWeaponData[34]);
                                    chamberCurrentChamberLoad = Double.parseDouble(chamberChosenWeaponData[33]);
                                } else {
                                    chamberCurrentMagazineLoad = Double.parseDouble(chamberChosenWeaponData[27]);
                                    chamberCurrentChamberCap = Double.parseDouble(chamberChosenWeaponData[34]);
                                    chamberCurrentChamberLoad = Double.parseDouble(chamberChosenWeaponData[33]);
                                }

                                if (chamberChosenWeaponData[31].equals("[CHARGE]")){
                                    chamberAmmoToChamber = 1;
                                } else {
                                    chamberAmmoToChamber = chamberCurrentChamberCap - chamberCurrentChamberLoad;
                                }

                                if (chamberCurrentMagazineLoad - chamberAmmoToChamber<= 0){
                                    chamberCurrentChamberLoad = chamberCurrentMagazineLoad;
                                    chamberCurrentMagazineLoad = 0;
                                } else {
                                    chamberCurrentChamberLoad = chamberCurrentChamberCap;
                                    chamberCurrentMagazineLoad -= chamberAmmoToChamber;
                                }

                                if ( chamberChosenWeaponSlot.equals("PRIMARY") ){
                                    if (chamberChosenSubsystem.equals("PRIMARY")){
                                        chamberChosenWeaponData[27] = Double.toString(chamberCurrentMagazineLoad);
                                        chamberChosenWeaponData[33] = Double.toString(chamberCurrentChamberLoad);
                                        chamberChosenWeaponData[32] = "LOADED";
                                        weaponWrite( player, player.getPrimaryWeapon(), chamberChosenWeaponData);
                                    } else {
                                        chamberChosenWeaponData[40] = Double.toString(chamberCurrentMagazineLoad);
                                        chamberChosenWeaponData[45] = Double.toString(chamberCurrentChamberLoad);
                                        chamberChosenWeaponData[46] = "LOADED";
                                        weaponWrite( player, player.getPrimaryWeapon(), chamberChosenWeaponData);
                                    }
                                } else {
                                    if (chamberChosenSubsystem.equals("PRIMARY")){
                                        chamberChosenWeaponData[27] = Double.toString(chamberCurrentMagazineLoad);
                                        chamberChosenWeaponData[33] = Double.toString(chamberCurrentChamberLoad);
                                        chamberChosenWeaponData[32] = "LOADED";
                                        weaponWrite( player, player.getSecondaryWeapon(), chamberChosenWeaponData);
                                    } else {
                                        chamberChosenWeaponData[40] = Double.toString(chamberCurrentMagazineLoad);
                                        chamberChosenWeaponData[45] = Double.toString(chamberCurrentChamberLoad);
                                        chamberChosenWeaponData[46] = "LOADED";
                                        weaponWrite( player, player.getSecondaryWeapon(), chamberChosenWeaponData);
                                    }
                                }

                                ShortDelay();
                                System.out.println("//WEAPON CHAMBERED");
                                ShortDelay();
                                System.out.println("//USER RETURNED TO COMBAT MAIN MENU");
                                ShortDelay();
                                System.out.println("//INPUT \"A\" TO REFRESH");
                                invalidAction = false;
                                break;

                            case "FEUERMODUS:": case "feuermodus": case "FIREMODE": case "firemode": case "FWMD": case "fwmd": case "2":
                                if ( ( currentPrimaryWeaponData[10].equals("MELEE") || currentPrimaryWeaponData[10].equals("N/A") ) && ( currentPrimaryWeaponData[17].equals("MELEE") || currentPrimaryWeaponData[17].equals("N/A") ) && ( currentSecondaryWeaponData[10].equals("MELEE") || currentSecondaryWeaponData[10].equals("N/A") ) && ( currentSecondaryWeaponData[17].equals("MELEE") || currentSecondaryWeaponData[17].equals("N/A") ) ){
                                    System.out.println("//ERROR: CURRENTLY EQUIPPED WEAPONS DO NOT REQUIRE AMMUNITION");
                                    ShortDelay();
                                    System.out.println("//RELOAD SUBMENU EXITED");
                                    invalidAction = false;
                                    break;
                                } else {
                                    System.out.println("//PLEASE SELECT A WEAPON TO CHANGE FIREMODE");
                                    ShortDelay();
                                    if ( currentPrimaryWeaponData[10].equals("RANGED") || currentPrimaryWeaponData[10].equals("HYBRID") ){
                                        System.out.println("> 0_ " + player.getPrimaryWeapon());
                                        NoDelay();
                                    }
                                    if ( currentSecondaryWeaponData[10].equals("RANGED") || currentSecondaryWeaponData[10].equals("HYBRID") ){
                                        System.out.println("> 1_ " + player.getSecondaryWeapon());
                                        NoDelay();
                                    }
                                }
                                System.out.println("> ._ EXIT [AUSF]");
                                String[] firemodeChosenWeaponData = new String[54];
                                String firemodeChosenWeaponSlot = "";
                                boolean weaponFiremodeNotChosen = true;
                                boolean weaponFiremodeCancelled = false;
                                while (weaponFiremodeNotChosen){
                                    switch (input.nextLine()){
                                        case "0":
                                            if ( (currentPrimaryWeaponData[10].equals("MELEE") || currentPrimaryWeaponData[10].equals("N/A") ) && (currentPrimaryWeaponData[17].equals("MELEE") || currentPrimaryWeaponData[17].equals("N/A") ) ){
                                                System.out.println("//ERROR: WEAPON DOES NOT USE AMMUNITION");
                                            } else {
                                                firemodeChosenWeaponData = currentPrimaryWeaponData;
                                                firemodeChosenWeaponSlot = "PRIMARY";
                                                weaponFiremodeNotChosen = false;
                                            }
                                            break;
                                        case "1":
                                            if ( (currentSecondaryWeaponData[10].equals("MELEE") || currentSecondaryWeaponData[10].equals("N/A") ) && (currentSecondaryWeaponData[17].equals("MELEE") || currentSecondaryWeaponData[17].equals("N/A") ) ){
                                                System.out.println("//ERROR: WEAPON DOES NOT USE AMMUNITION");
                                            } else {
                                                firemodeChosenWeaponData = currentSecondaryWeaponData;
                                                firemodeChosenWeaponSlot = "SECONDARY";
                                                weaponFiremodeNotChosen = false;
                                            }
                                            break;
                                        case ".":
                                            weaponFiremodeCancelled = true;
                                            weaponFiremodeNotChosen = false;
                                            break;
                                        default:
                                            if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                                                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                            } else if (player.getUserLanguage().equals("ENGLISH")) {
                                                System.out.println("//ERROR: INVALID INPUT");
                                            }
                                            break;
                                    }
                                }

                                if (weaponFiremodeCancelled){
                                    System.out.println("//FIREMODE SUBMENU EXITED");
                                    break;
                                }

                                if (!firemodeChosenWeaponData[17].equals("RANGED") || firemodeChosenWeaponData[10].equals("RANGED")){
                                    System.out.println("AMMUNITION CALIBER: " + firemodeChosenWeaponData[24]);
                                    NoDelay();
                                    if (firemodeChosenWeaponData[22].equals("Electricity")){
                                        System.out.println("\t CURRENT CHAMBER STATUS: " + firemodeChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t CURRENT CHAMBER LOAD: " + Double.parseDouble(firemodeChosenWeaponData[33]) + " / " + Double.parseDouble(firemodeChosenWeaponData[34]) + " " + firemodeChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t CURRENT BATTERY TYPE: " + firemodeChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t CURRENT BATTERY CHARGE: " + Double.parseDouble(firemodeChosenWeaponData[27]) + " / " + Double.parseDouble(firemodeChosenWeaponData[28]) + " " + firemodeChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > POSSIBLE FIREMODES: " + firemodeChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT FIREMODE: " + firemodeChosenWeaponData[31]);
                                        NoDelay();
                                    } else {
                                        System.out.println("\t > CURRENT CHAMBER STATUS: " + firemodeChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT CHAMBER LOAD: " + (int)Double.parseDouble(firemodeChosenWeaponData[33]) + " / " + (int)Double.parseDouble(firemodeChosenWeaponData[34]) + " " + firemodeChosenWeaponData[25] + " " + firemodeChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT MAGAZINE TYPE: " + firemodeChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT MAGAZINE LOAD: " + (int)Double.parseDouble(firemodeChosenWeaponData[27]) + " / " + (int)Double.parseDouble(firemodeChosenWeaponData[28]) + " " + firemodeChosenWeaponData[25] + " " + firemodeChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + firemodeChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + firemodeChosenWeaponData[31]);
                                        NoDelay();
                                    }
                                } else {
                                    System.out.println("PRIMARY AMMUNITION CALIBER: " + firemodeChosenWeaponData[26]);
                                    NoDelay();
                                    if (firemodeChosenWeaponData[24].equals("Electricity")){
                                        System.out.println("\t > CURRENT PRIMARY CAPACITOR STATUS: " + firemodeChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY CAPACITOR LOAD: " + Double.parseDouble(firemodeChosenWeaponData[33]) + " / " + Double.parseDouble(firemodeChosenWeaponData[34]) + " " + firemodeChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY BATTERY TYPE: " + firemodeChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY BATTERY CHARGE: " + Double.parseDouble(firemodeChosenWeaponData[27]) + " / " + Double.parseDouble(firemodeChosenWeaponData[28]) + " " + firemodeChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + firemodeChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + firemodeChosenWeaponData[31]);
                                        NoDelay();
                                    } else {
                                        System.out.println("\t > CURRENT PRIMARY CHAMBER STATUS: " + firemodeChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY CHAMBER LOAD: " + (int)Double.parseDouble(firemodeChosenWeaponData[33]) + " / " + (int)Double.parseDouble(firemodeChosenWeaponData[34]) + " " + firemodeChosenWeaponData[25] + " " + firemodeChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY MAGAZINE TYPE: " + firemodeChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY MAGAZINE LOAD: " + (int)Double.parseDouble(firemodeChosenWeaponData[27]) + " / " + (int)Double.parseDouble(firemodeChosenWeaponData[28]) + " " + firemodeChosenWeaponData[25] + " " + firemodeChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + firemodeChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + firemodeChosenWeaponData[31]);
                                        NoDelay();
                                    }
                                    System.out.println("SECONDARY AMMUNITION CALIBER: " + firemodeChosenWeaponData[37]);
                                    NoDelay();
                                    if (firemodeChosenWeaponData[24].equals("Electricity")){
                                        System.out.println("\t > CURRENT SECONDARY CAPACITOR STATUS: " + firemodeChosenWeaponData[45]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY CAPACITOR LOAD: " + Double.parseDouble(firemodeChosenWeaponData[46]) + " / " + Double.parseDouble(firemodeChosenWeaponData[47]) + " " + firemodeChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY BATTERY TYPE: " + firemodeChosenWeaponData[39]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY BATTERY CHARGE: " + Double.parseDouble(firemodeChosenWeaponData[40]) + " / " + Double.parseDouble(firemodeChosenWeaponData[41]) + " " + firemodeChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + firemodeChosenWeaponData[43]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + firemodeChosenWeaponData[44]);
                                        NoDelay();
                                    } else {
                                        System.out.println("CURRENT SECONDARY CHAMBER STATUS: " + firemodeChosenWeaponData[45]);
                                        NoDelay();
                                        System.out.println("CURRENT SECONDARY CHAMBER LOAD: " + (int)Double.parseDouble(firemodeChosenWeaponData[46]) + " / " + (int)Double.parseDouble(firemodeChosenWeaponData[47]) + " " + firemodeChosenWeaponData[38] + " " + firemodeChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("CURRENT SECONDARY MAGAZINE TYPE: " + firemodeChosenWeaponData[39]);
                                        NoDelay();
                                        System.out.println("CURRENT SECONDARY MAGAZINE LOAD: " + (int)Double.parseDouble(firemodeChosenWeaponData[40]) + " / " + (int)Double.parseDouble(firemodeChosenWeaponData[41]) + " " + firemodeChosenWeaponData[38] + " " + firemodeChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + firemodeChosenWeaponData[43]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + firemodeChosenWeaponData[44]);
                                        NoDelay();
                                    }
                                }

                                String firemodeChosenSubsystem = "";
                                boolean firemodeSubsystemCancel = false;
                                if (firemodeChosenWeaponData[10].equals("RANGED") && firemodeChosenWeaponData[17].equals("RANGED")){
                                    ShortDelay();
                                    System.out.println("//PLEASE SELECT SUBSYSTEM OF " + firemodeChosenWeaponData[0] + " FOR RELOAD");
                                    ShortDelay();
                                    System.out.println("> 0_ PRIMARY [PRMR]");
                                    NoDelay();
                                    System.out.println("> 1_ SECONDARY [SKDR]");
                                    NoDelay();
                                    boolean weaponSubsystemNotChosen = true;
                                    while (weaponSubsystemNotChosen){
                                        switch (input.nextLine()){
                                            case "0":
                                                firemodeChosenSubsystem = "PRIMARY";
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            case "1":
                                                firemodeChosenSubsystem = "SECONDARY";
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            case ".":
                                                firemodeSubsystemCancel = true;
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            default:
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")) {
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                                break;
                                        }
                                    }
                                } else {
                                    if (firemodeChosenWeaponData[10].equals("RANGED")){
                                        firemodeChosenSubsystem = "PRIMARY";
                                    } else if (firemodeChosenWeaponData[17].equals("RANGED")){
                                        firemodeChosenSubsystem = "SECONDARY";
                                    }
                                }

                                if (firemodeSubsystemCancel){
                                    System.out.println("//RELOAD SUBMENU EXITED");
                                    break;
                                }

                                String[] firemodes;

                                if (firemodeChosenSubsystem.equals("PRIMARY")){
                                    firemodes = firemodeChosenWeaponData[30].split("/");
                                } else {
                                    firemodes = firemodeChosenWeaponData[43].split("/");
                                }

                                ShortDelay();
                                System.out.println("//PLEASE SELECT NEW FIREMODE");

                                for (int i = 0; i < firemodes.length; i++){
                                    System.out.println("\t > " + i + "_ " + firemodes[i]);
                                    NoDelay();
                                }

                                String inputFiremode;
                                String firemodeToSwitch = "";
                                int chosenFiremodeIndex;
                                boolean firemodeNotChosen = true;
                                while (firemodeNotChosen){
                                    inputFiremode = input.nextLine();
                                    switch (inputFiremode){
                                        case ".":
                                            firemodeToSwitch = inputFiremode;
                                            firemodeNotChosen = false;
                                            break;
                                        default:
                                            try {
                                                chosenFiremodeIndex = Integer.parseInt(inputFiremode);
                                                if (0 <= chosenFiremodeIndex && chosenFiremodeIndex < firemodes.length){
                                                    firemodeToSwitch = firemodes[chosenFiremodeIndex];
                                                    firemodeNotChosen = false;
                                                }
                                            } catch (NumberFormatException notNumber){
                                                chosenFiremodeIndex = -1;
                                                for (int i = 0; i < firemodes.length; i++){
                                                    if ( (firemodes[i].contains(inputFiremode)) || (firemodes[i].contains(inputFiremode.toLowerCase())) ){
                                                        chosenFiremodeIndex = i;
                                                    }
                                                }
                                                if (chosenFiremodeIndex == -1){
                                                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                    } else if (player.getUserLanguage().equals("ENGLISH")){
                                                        System.out.println("//ERROR: INVALID INPUT");
                                                    }
                                                } else {
                                                    firemodeToSwitch = firemodes[chosenFiremodeIndex];
                                                    firemodeNotChosen = false;
                                                }
                                            }
                                            break;
                                    }
                                }

                                if (firemodeToSwitch.equals(".")){
                                    System.out.println("//FIREMODE SUBMENU EXITED");
                                    break;
                                }

                                if (firemodeChosenWeaponSlot.equals("PRIMARY")){
                                    if (firemodeChosenSubsystem.equals("PRIMARY")){
                                        firemodeChosenWeaponData[31] = firemodeToSwitch;
                                        weaponWrite( player, player.getPrimaryWeapon(), firemodeChosenWeaponData);
                                    } else {
                                        firemodeChosenWeaponData[44] = firemodeToSwitch;
                                        weaponWrite( player, player.getPrimaryWeapon(), firemodeChosenWeaponData);
                                    }
                                } else {
                                    if (firemodeChosenSubsystem.equals("PRIMARY")){
                                        firemodeChosenWeaponData[31] = firemodeToSwitch;
                                        weaponWrite( player, player.getSecondaryWeapon(), firemodeChosenWeaponData);
                                    } else {
                                        firemodeChosenWeaponData[44] = firemodeToSwitch;
                                        weaponWrite( player, player.getSecondaryWeapon(), firemodeChosenWeaponData);
                                    }
                                }

                                System.out.println("//CURRENT FIREMODE UPDATED");
                                ShortDelay();
                                System.out.println("//USER RETURNED TO COMBAT MAIN MENU");
                                ShortDelay();
                                System.out.println("//INPUT \"A\" TO REFRESH");
                                invalidAction = false;
                                break;
                            case ".":
                                System.out.println("//ACTION SUBMENU EXITED");
                                invalidAction = false;
                                break;
                        }
                    }
                    break;
                case "WAFFEN": case "waffen": case "WFFN": case "wffn": case "WEAPONS": case "weapons": case "2":
                    weaponEquip(player);
                    break;
                case "ARTIKEL": case "artikel": case "ATKL": case "atkl": case "ITEMS": case "items": case "3":
                    System.out.println("//ERROR: DRIVE SECTOR CORRUPTED");
                    ShortDelay();
                    System.out.println("//UNABLE TO ACCESS ITEM INVENTORY");
                    break;
                case "BEARBEITEN": case "bearbeiten": case "BRBT": case "brbt": case "4":
                    System.out.println("//PLEASE SELECT FIELD TO UPDATE");
                    ShortDelay();
                    System.out.println("> 0_ HEALTH [GSDH] ");
                    NoDelay();
                    System.out.println("> 1_ STATUS [STAT]");
                    NoDelay();
                    System.out.println("> 2_ AMMUNITION [MNTN]");
                    NoDelay();
                    System.out.println("> ._ EXIT [AUSF]");
                    boolean invalidStatus = true;
                    while (invalidStatus){
                        switch (input.nextLine()){
                            case "HEALTH": case "health": case"GSDH": case"gsdh": case "0":
                                System.out.println("//CURRENT HP: " + player.getUserCurrentHealth() + "/" + player.getUserMaxHealth());
                                ShortDelay();
                                System.out.println("//PLEASE ENTER NEW HEALTH VALUE");
                                boolean invalidHealth = true;
                                while (invalidHealth){
                                    try {
                                        double newHealth = Double.parseDouble(input.nextLine());
                                        if (newHealth > Double.parseDouble(player.getUserMaxHealth())){
                                            System.out.println("//ERROR: MAXIMUM HEALTH EXCEEDED");
                                        } else {
                                            player.setUserCurrentHealth(Double.toString(newHealth));
                                            System.out.println("//CURRENT HEALTH UPDATED");
                                            invalidHealth = false;
                                            break;
                                        }
                                    } catch (NumberFormatException exception) {
                                        System.out.println("//ERROR: INVALID INPUT FORMAT");
                                    }
                                }
                                invalidStatus = false;
                                break;
                            case "STATUS": case "status": case "STAT": case "stat": case "1":
                                System.out.println("//PLEASE ENTER NEW STATUS");
                                player.setUserStatus(input.nextLine());
                                System.out.println("//CURRENT STATUS UPDATED");
                                invalidStatus = false;
                                break;
                            case "AMMUNITION": case "ammunition": case "AMMO": case "ammo": case "2":
                                if ( ( currentPrimaryWeaponData[10].equals("MELEE") || currentPrimaryWeaponData[10].equals("N/A") ) && ( currentPrimaryWeaponData[17].equals("MELEE") || currentPrimaryWeaponData[17].equals("N/A") ) && ( currentSecondaryWeaponData[10].equals("MELEE") || currentSecondaryWeaponData[10].equals("N/A") ) && ( currentSecondaryWeaponData[17].equals("MELEE") || currentSecondaryWeaponData[17].equals("N/A") ) ){
                                    System.out.println("//ERROR: CURRENTLY EQUIPPED WEAPONS DO NOT REQUIRE AMMUNITION");
                                    ShortDelay();
                                    System.out.println("//RELOAD SUBMENU EXITED");
                                    invalidAction = false;
                                    break;
                                } else {
                                    System.out.println("//PLEASE SELECT A WEAPON TO EDIT AMMO");
                                    ShortDelay();
                                    if ( currentPrimaryWeaponData[10].equals("RANGED") || currentPrimaryWeaponData[10].equals("HYBRID") ){
                                        System.out.println("> 0_ " + player.getPrimaryWeapon());
                                        NoDelay();
                                    }
                                    if ( currentSecondaryWeaponData[10].equals("RANGED") || currentSecondaryWeaponData[10].equals("HYBRID") ){
                                        System.out.println("> 1_ " + player.getSecondaryWeapon());
                                        NoDelay();
                                    }
                                }

                                System.out.println("> ._ EXIT [AUSF]");
                                String[] ammoEditChosenWeaponData = new String[54];
                                String ammoEditChosenWeaponSlot = "";
                                boolean weaponAmmoEditNotChosen = true;
                                boolean weaponAmmoEditCancelled = false;
                                while (weaponAmmoEditNotChosen){
                                    switch (input.nextLine()){
                                        case "0":
                                            if ( (currentPrimaryWeaponData[10].equals("MELEE") || currentPrimaryWeaponData[10].equals("N/A") ) && (currentPrimaryWeaponData[17].equals("MELEE") || currentPrimaryWeaponData[17].equals("N/A") ) ){
                                                System.out.println("//ERROR: WEAPON DOES NOT USE AMMUNITION");
                                            } else {
                                                ammoEditChosenWeaponData = currentPrimaryWeaponData;
                                                ammoEditChosenWeaponSlot = "PRIMARY";
                                                weaponAmmoEditNotChosen = false;
                                            }
                                            break;
                                        case "1":
                                            if ( (currentSecondaryWeaponData[10].equals("MELEE") || currentSecondaryWeaponData[10].equals("N/A") ) && (currentSecondaryWeaponData[17].equals("MELEE") || currentSecondaryWeaponData[17].equals("N/A") ) ){
                                                System.out.println("//ERROR: WEAPON DOES NOT USE AMMUNITION");
                                            } else {
                                                ammoEditChosenWeaponData = currentSecondaryWeaponData;
                                                ammoEditChosenWeaponSlot = "SECONDARY";
                                                weaponAmmoEditNotChosen = false;
                                            }
                                            break;
                                        case ".":
                                            weaponAmmoEditCancelled = true;
                                            weaponAmmoEditNotChosen = false;
                                            break;
                                        default:
                                            if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                                                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                            } else if (player.getUserLanguage().equals("ENGLISH")) {
                                                System.out.println("//ERROR: INVALID INPUT");
                                            }
                                            break;
                                    }
                                }

                                if (weaponAmmoEditCancelled){
                                    System.out.println("//AMMO EDIT SUBMENU EXITED");
                                    break;
                                }

                                if (!ammoEditChosenWeaponData[17].equals("RANGED") || ammoEditChosenWeaponData[10].equals("RANGED")){
                                    System.out.println("AMMUNITION CALIBER: " + ammoEditChosenWeaponData[24]);
                                    NoDelay();
                                    if (ammoEditChosenWeaponData[22].equals("Electricity")){
                                        System.out.println("\t > CURRENT CHAMBER STATUS: " + ammoEditChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT CHAMBER LOAD: " + Double.parseDouble(ammoEditChosenWeaponData[33]) + " / " + Double.parseDouble(ammoEditChosenWeaponData[34]) + " " + ammoEditChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT BATTERY TYPE: " + ammoEditChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT BATTERY CHARGE: " + Double.parseDouble(ammoEditChosenWeaponData[27]) + " / " + Double.parseDouble(ammoEditChosenWeaponData[28]) + " " + ammoEditChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > POSSIBLE FIREMODES: " + ammoEditChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT FIREMODE: " + ammoEditChosenWeaponData[31]);
                                        NoDelay();
                                    } else {
                                        System.out.println("\t > CURRENT CHAMBER STATUS: " + ammoEditChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT CHAMBER LOAD: " + (int)Double.parseDouble(ammoEditChosenWeaponData[33]) + " / " + (int)Double.parseDouble(ammoEditChosenWeaponData[34]) + " " + ammoEditChosenWeaponData[25] + " " + ammoEditChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT MAGAZINE TYPE: " + ammoEditChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT MAGAZINE LOAD: " + (int)Double.parseDouble(ammoEditChosenWeaponData[27]) + " / " + (int)Double.parseDouble(ammoEditChosenWeaponData[28]) + " " + ammoEditChosenWeaponData[25] + " " + ammoEditChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > POSSIBLE FIREMODES: " + ammoEditChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT FIREMODE: " + ammoEditChosenWeaponData[31]);
                                        NoDelay();
                                    }
                                } else {
                                    System.out.println("PRIMARY AMMUNITION CALIBER: " + ammoEditChosenWeaponData[26]);
                                    NoDelay();
                                    if (ammoEditChosenWeaponData[24].equals("Electricity")){
                                        System.out.println("\t > CURRENT PRIMARY CAPACITOR STATUS: " + ammoEditChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY CAPACITOR LOAD: " + Double.parseDouble(ammoEditChosenWeaponData[33]) + " / " + Double.parseDouble(ammoEditChosenWeaponData[34]) + " " + ammoEditChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY BATTERY TYPE: " + ammoEditChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY BATTERY CHARGE: " + Double.parseDouble(ammoEditChosenWeaponData[27]) + " / " + Double.parseDouble(ammoEditChosenWeaponData[28]) + " " + ammoEditChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + ammoEditChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + ammoEditChosenWeaponData[31]);
                                        NoDelay();
                                    } else {
                                        System.out.println("\t > CURRENT PRIMARY CHAMBER STATUS: " + ammoEditChosenWeaponData[32]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY CHAMBER LOAD: " + (int)Double.parseDouble(ammoEditChosenWeaponData[33]) + " / " + (int)Double.parseDouble(ammoEditChosenWeaponData[34]) + " " + ammoEditChosenWeaponData[25] + " " + ammoEditChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY MAGAZINE TYPE: " + ammoEditChosenWeaponData[26]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT PRIMARY MAGAZINE LOAD: " + (int)Double.parseDouble(ammoEditChosenWeaponData[27]) + " / " + (int)Double.parseDouble(ammoEditChosenWeaponData[28]) + " " + ammoEditChosenWeaponData[25] + " " + ammoEditChosenWeaponData[29]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK POSSIBLE FIREMODES: " + ammoEditChosenWeaponData[30]);
                                        NoDelay();
                                        System.out.println("\t > PRIMARY ATTACK CURRENT FIREMODE: " + ammoEditChosenWeaponData[31]);
                                        NoDelay();
                                    }
                                    System.out.println("SECONDARY AMMUNITION CALIBER: " + ammoEditChosenWeaponData[37]);
                                    NoDelay();
                                    if (ammoEditChosenWeaponData[24].equals("Electricity")){
                                        System.out.println("\t > CURRENT SECONDARY CAPACITOR STATUS: " + ammoEditChosenWeaponData[45]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY CAPACITOR LOAD: " + Double.parseDouble(ammoEditChosenWeaponData[46]) + " / " + Double.parseDouble(ammoEditChosenWeaponData[47]) + " " + ammoEditChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY BATTERY TYPE: " + ammoEditChosenWeaponData[39]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY BATTERY CHARGE: " + Double.parseDouble(ammoEditChosenWeaponData[40]) + " / " + Double.parseDouble(ammoEditChosenWeaponData[41]) + " " + ammoEditChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + currentPrimaryWeaponData[43]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + currentPrimaryWeaponData[44]);
                                        NoDelay();
                                    } else {
                                        System.out.println("\t > CURRENT SECONDARY CHAMBER STATUS: " + ammoEditChosenWeaponData[45]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY CHAMBER LOAD: " + (int)Double.parseDouble(ammoEditChosenWeaponData[46]) + " / " + (int)Double.parseDouble(ammoEditChosenWeaponData[47]) + " " + ammoEditChosenWeaponData[38] + " " + ammoEditChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY MAGAZINE TYPE: " + ammoEditChosenWeaponData[39]);
                                        NoDelay();
                                        System.out.println("\t > CURRENT SECONDARY MAGAZINE LOAD: " + (int)Double.parseDouble(ammoEditChosenWeaponData[40]) + " / " + (int)Double.parseDouble(ammoEditChosenWeaponData[41]) + " " + ammoEditChosenWeaponData[38] + " " + ammoEditChosenWeaponData[42]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK POSSIBLE FIREMODES: " + ammoEditChosenWeaponData[43]);
                                        NoDelay();
                                        System.out.println("\t > SECONDARY ATTACK CURRENT FIREMODE: " + ammoEditChosenWeaponData[44]);
                                        NoDelay();
                                    }
                                }

                                String ammoEditChosenSubsystem = "";
                                boolean ammoEditSubsystemCancel = false;
                                if (ammoEditChosenWeaponData[10].equals("RANGED") && ammoEditChosenWeaponData[17].equals("RANGED")){
                                    ShortDelay();
                                    System.out.println("//PLEASE SELECT SUBSYSTEM OF " + ammoEditChosenWeaponData[0] + " FOR RELOAD");
                                    ShortDelay();
                                    System.out.println("> 0_ PRIMARY [PRMR]");
                                    NoDelay();
                                    System.out.println("> 1_ SECONDARY [SKDR]");
                                    NoDelay();
                                    boolean weaponSubsystemNotChosen = true;
                                    while (weaponSubsystemNotChosen){
                                        switch (input.nextLine()){
                                            case "0":
                                                ammoEditChosenSubsystem = "PRIMARY";
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            case "1":
                                                ammoEditChosenSubsystem = "SECONDARY";
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            case ".":
                                                ammoEditSubsystemCancel = true;
                                                weaponSubsystemNotChosen = false;
                                                break;
                                            default:
                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                } else if (player.getUserLanguage().equals("ENGLISH")) {
                                                    System.out.println("//ERROR: INVALID INPUT");
                                                }
                                                break;
                                        }
                                    }
                                } else {
                                    if (ammoEditChosenWeaponData[10].equals("RANGED")){
                                        ammoEditChosenSubsystem = "PRIMARY";
                                    } else if (ammoEditChosenWeaponData[17].equals("RANGED")){
                                        ammoEditChosenSubsystem = "SECONDARY";
                                    }
                                }

                                if (ammoEditSubsystemCancel){
                                    System.out.println("//RELOAD SUBMENU EXITED");
                                    break;
                                }

                                String currentAmmoType = "";
                                double currentMagLoad = 0;
                                double currentMagCap = 0;
                                if (ammoEditChosenSubsystem.equals("PRIMARY")){
                                    currentAmmoType = ammoEditChosenWeaponData[25];
                                    currentMagLoad = Double.parseDouble(ammoEditChosenWeaponData[27]);
                                    currentMagCap = Double.parseDouble(ammoEditChosenWeaponData[28]);
                                } else {
                                    currentAmmoType = ammoEditChosenWeaponData[38];
                                    currentMagLoad = Double.parseDouble(ammoEditChosenWeaponData[40]);
                                    currentMagCap = Double.parseDouble(ammoEditChosenWeaponData[41]);
                                }

                                System.out.println("//ENTER NEW VALUE FOR CURRENT AMMUNITION");
                                boolean invalidAmmoValue = true;
                                double inputAmmoEdit = 0;
                                while (invalidAmmoValue){
                                    while (!input.hasNextDouble()){
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        input = new Scanner(System.in);
                                    }
                                    inputAmmoEdit = input.nextDouble();
                                    if (0 <= inputAmmoEdit && inputAmmoEdit <= currentMagCap){
                                        invalidAmmoValue = false;
                                    } else {
                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                            System.out.println("//ERROR: INVALID INPUT");
                                        }
                                        input = new Scanner(System.in);
                                    }
                                }
                                currentMagLoad = inputAmmoEdit;

                                input = new Scanner(System.in);
                                System.out.println("//ENTER NEW VALUE FOR CURRENT AMMO SUBTYPE");
                                ShortDelay();
                                System.out.println("//WARNING: AMMO NAMES ARE CASE-SENSITIVE");
                                currentAmmoType = input.nextLine();

                                if (ammoEditChosenWeaponSlot.equals("PRIMARY")){
                                    if (ammoEditChosenSubsystem.equals("PRIMARY")){
                                        ammoEditChosenWeaponData[25] = currentAmmoType;
                                        ammoEditChosenWeaponData[27] = Double.toString( currentMagLoad );
                                        ammoEditChosenWeaponData[28] = Double.toString( currentMagCap );
                                    } else {
                                        ammoEditChosenWeaponData[38] = currentAmmoType;
                                        ammoEditChosenWeaponData[40] = Double.toString( currentMagLoad );
                                        ammoEditChosenWeaponData[41] = Double.toString( currentMagCap );
                                    }
                                    weaponWrite ( player, player.getPrimaryWeapon(), ammoEditChosenWeaponData);
                                } else {
                                    if (ammoEditChosenSubsystem.equals("PRIMARY")){
                                        ammoEditChosenWeaponData[25] = currentAmmoType;
                                        ammoEditChosenWeaponData[27] = Double.toString( currentMagLoad );
                                        ammoEditChosenWeaponData[28] = Double.toString( currentMagCap );
                                    } else {
                                        ammoEditChosenWeaponData[38] = currentAmmoType;
                                        ammoEditChosenWeaponData[40] = Double.toString( currentMagLoad );
                                        ammoEditChosenWeaponData[41] = Double.toString( currentMagCap );
                                    }
                                    weaponWrite ( player, player.getSecondaryWeapon(), ammoEditChosenWeaponData);
                                }

                                ShortDelay();
                                System.out.println("//AMMO VALUES UPDATED");
                                ShortDelay();
                                System.out.println("//USER RETURNED TO COMBAT MAIN MENU");
                                ShortDelay();
                                System.out.println("//INPUT \"A\" TO REFRESH");
                                invalidStatus = false;
                                break;
                            case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                                System.out.println("//STATUS SUBMENU EXITED");
                                invalidStatus = false;
                                break;
                            default:
                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                    System.out.println("//ERROR: INVALID INPUT");
                                }
                                break;
                        }
                    }
                    break;
                case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                    combat( player );
                    break;
                case "NÄCHSTER ZYKLUS": case "nächster zyklus": case "NAECHSTER ZYKLUS": case "naechster zyklus": case "NZYK": case "nzyk": case "NZ": case "nz": case "NEXT CYCLE": case "next cycle": case "NCYC": case "ncyc": case "5":
                    currentCycle++;
                    combat( player );
                    break;
                case "ZYKLEN ZURÜCKSETZEN": case "zyklen zurücksetzen": case "ZYKLEN ZURUECKSETZEN": case "zyklen zuruecksetzen": case "ZZYK": case "zzyk": case "ZZ": case "zz": case "RESET CYCLES": case "reset cycles": case "RCYC": case "rcyc": case "6":
                    currentCycle = 0;
                    combat( player );
                    break;
                case "ROLLEN": case "rollen": case "ROLL": case "roll": case "R": case "r":
                    roll( player );
                    break;
                case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                    mainMenu( player );
                    break;
                case "HERUNTERFAHREN": case "herunterfahren": case "SHUTDOWN": case "shutdown": case "HNTF": case "hntf": case "H": case "h":
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
                    ShortDelay();
                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                    break;
                case "#KVSCLE": //changes language to English
                    optionNotChosen = true;
                    player.setUserLanguage("ENGLISH");
                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                    ShortDelay();
                    System.out.println("//INPUT \"A\" TO REFRESH");
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")){
                        System.out.println("//ERROR: INVALID INPUT");
                    }
                    break;
            }
        }
    }

    private static String[] damageCalc( playerCharacter player, String weaponName, String[] weaponData ) throws IOException, InterruptedException{
        String[] damageData = new String[3];
        double totalOutgoingDamage = 0;

        String chosenWeaponSubsystem = "";
        String chosenWeaponAttackType = "";
        String chosenWeaponBaseDamage = "";
        String chosenWeaponEffectiveRange = "";
        String chosenWeaponAccuracyThreshold = "";
        String chosenWeaponCritHitCapability = "";
        String chosenWeaponCritHitThreshold = "";
        String chosenWeaponCritHitMultiplier = "";
        String chosenAmmunitionCaliber = "";
        String chosenAmmunitionSubtype = "";
        String chosenMagazineLoad = "";
        String chosenAmmoUnitOfMeasurement = "";
        String chosenCurrentFiremode = "";
        String chosenChamberStatus = "";
        String chosenChamberLoad = "";
        String chosenChamberCapacity = "";
        String chosenMinBurstLength = "";
        String chosenMaxBurstLength = "";
        String chosenSpecialProperties = "";

        if (!weaponData[17].equals("N/A")){
            System.out.println("//PLEASE SELECT SUBSYSTEM OF " + weaponName + " FOR ATTACK");
            ShortDelay();
            System.out.println("> 0_ PRIMARY");
            NoDelay();
            System.out.println("> 1_ SECONDARY");
            NoDelay();
            boolean weaponSubsystemNotChosen = true;
            while (weaponSubsystemNotChosen){
                switch (input.nextLine()){
                    case "0":
                        chosenWeaponSubsystem = "PRIMARY";
                        chosenWeaponAttackType = weaponData[10];
                        chosenWeaponBaseDamage = weaponData[11];
                        chosenWeaponEffectiveRange = weaponData[12];
                        chosenWeaponAccuracyThreshold = weaponData[13];
                        chosenWeaponCritHitCapability = weaponData[14];
                        chosenWeaponCritHitThreshold = weaponData[15];
                        chosenWeaponCritHitMultiplier = weaponData[16];
                        chosenAmmunitionCaliber = weaponData[24];
                        chosenAmmunitionSubtype = weaponData[25];
                        chosenMagazineLoad = weaponData[27];
                        chosenAmmoUnitOfMeasurement = weaponData[29];
                        chosenCurrentFiremode = weaponData[31];
                        chosenChamberStatus = weaponData[32];
                        chosenChamberLoad = weaponData[33];
                        chosenChamberCapacity = weaponData[34];
                        chosenMinBurstLength = weaponData[35];
                        chosenMaxBurstLength = weaponData[36];
                        chosenSpecialProperties = weaponData[52];
                        weaponSubsystemNotChosen = false;
                        break;
                    case "1":
                        chosenWeaponSubsystem = "SECONDARY";
                        chosenWeaponAttackType = weaponData[17];
                        chosenWeaponBaseDamage = weaponData[18];
                        chosenWeaponEffectiveRange = weaponData[19];
                        chosenWeaponAccuracyThreshold = weaponData[20];
                        chosenWeaponCritHitCapability = weaponData[21];
                        chosenWeaponCritHitThreshold = weaponData[22];
                        chosenWeaponCritHitMultiplier = weaponData[23];
                        chosenAmmunitionCaliber = weaponData[37];
                        chosenAmmunitionSubtype = weaponData[38];
                        chosenMagazineLoad = weaponData[40];
                        chosenAmmoUnitOfMeasurement = weaponData[42];
                        chosenCurrentFiremode = weaponData[44];
                        chosenChamberStatus = weaponData[45];
                        chosenChamberLoad = weaponData[46];
                        chosenChamberCapacity = weaponData[47];
                        chosenMinBurstLength = weaponData[48];
                        chosenMaxBurstLength = weaponData[49];
                        chosenSpecialProperties = weaponData[53];
                        weaponSubsystemNotChosen = false;
                        break;
                    default:
                        if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                        } else if (player.getUserLanguage().equals("ENGLISH")) {
                            System.out.println("//ERROR: INVALID INPUT");
                        }
                        break;
                }
            }
        } else {
            chosenWeaponSubsystem = "PRIMARY";
            chosenWeaponAttackType = weaponData[10];
            chosenWeaponBaseDamage = weaponData[11];
            chosenWeaponEffectiveRange = weaponData[12];
            chosenWeaponAccuracyThreshold = weaponData[13];
            chosenWeaponCritHitCapability = weaponData[14];
            chosenWeaponCritHitThreshold = weaponData[15];
            chosenWeaponCritHitMultiplier = weaponData[16];
            chosenAmmunitionCaliber = weaponData[24];
            chosenAmmunitionSubtype = weaponData[25];
            chosenMagazineLoad = weaponData[27];
            chosenAmmoUnitOfMeasurement = weaponData[29];
            chosenCurrentFiremode = weaponData[31];
            chosenChamberStatus = weaponData[32];
            chosenChamberLoad = weaponData[33];
            chosenChamberCapacity = weaponData[34];
            chosenMinBurstLength = weaponData[35];
            chosenMaxBurstLength = weaponData[36];
            chosenSpecialProperties = weaponData[52];
        }

        if (chosenWeaponAttackType.equals("RANGED") && chosenMagazineLoad.equals("0")){
            if (chosenAmmunitionCaliber.equals("Electricity")){
                System.out.println("//ERROR: BATTERY CHARGE DEPLETED");
            } else {
                System.out.println("//ERROR: MAGAZINE EMPTY");
            }
            damageData[0] = "0";
            damageData[1] = "N/A";
            damageData[2] = "N";
            return damageData;
        }
        if (chosenWeaponAttackType.equals("RANGED") && chosenChamberStatus.equals("EMPTY")){
            if (chosenAmmunitionCaliber.equals("Electricity")){
                System.out.println("//ERROR: CAPACITOR CHARGE DEPLETED");
            } else {
                System.out.println("//ERROR: WEAPON NOT CHAMBERED");
            }
            damageData[0] = "0";
            damageData[1] = "N/A";
            damageData[2] = "N";
            return damageData;
        }
        if (chosenWeaponAttackType.equals("RANGED") && chosenChamberStatus.equals("JAMMED")){
            if (chosenAmmunitionCaliber.equals("Electricity")){
                System.out.println("//ERROR: CAPACITOR MALFUNCTION");
            } else {
                System.out.println("//ERROR: WEAPON MALFUNCTION");
            }
            damageData[0] = "0";
            damageData[1] = "N/A";
            damageData[2] = "N";
            return damageData;
        }
        if (chosenWeaponAttackType.equals("RANGED") && chosenCurrentFiremode.equals("[SAFE]")){
            System.out.println("//ERROR: SAFETY ENGAGED");
            damageData[0] = "0";
            damageData[1] = "N/A";
            damageData[2] = "N";
            return damageData;
        }

        System.out.println("//PLEASE ENTER VALUE OF HIT ROLL");
        double hitRoll;
        while (!input.hasNextDouble()){
            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
            } else if (player.getUserLanguage().equals("ENGLISH")){
                System.out.println("//ERROR: INVALID INPUT");
            }
            input = new Scanner(System.in);
        }
        hitRoll = input.nextDouble();

        System.out.println("//PLEASE ENTER VALUE OF CRIT ROLL");
        double critRoll;
        while (!input.hasNextDouble()){
            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
            } else if (player.getUserLanguage().equals("ENGLISH")){
                System.out.println("//ERROR: INVALID INPUT");
            }
            input = new Scanner(System.in);
        }
        critRoll = input.nextDouble();
        input = new Scanner(System.in);
        System.out.println("//PLEASE ENTER ESTIMATE OF DISTANCE TO TARGET");
        ShortDelay();
        System.out.println("> 0_ POINT BLANK");
        NoDelay();
        System.out.println("> 1_ SHORT RANGE");
        NoDelay();
        System.out.println("> 2_ MEDIUM RANGE");
        NoDelay();
        System.out.println("> 3_ LONG RANGE");
        NoDelay();

        boolean rangeNotFound = true;
        while (rangeNotFound){
            switch (input.nextLine()){
                case "POINT BLANK": case "point blank": case "0":
                    switch (chosenWeaponEffectiveRange){
                        case "PB":
                            hitRoll += 0;
                            break;
                        case "SHORT":
                            hitRoll += -1;
                            break;
                        case "MEDIUM":
                            hitRoll += -2;
                            break;
                        case "LONG":
                            hitRoll += -3;
                            break;
                    }
                    rangeNotFound = false;
                    break;
                case "SHORT RANGE": case "short range": case "1":
                    switch (chosenWeaponEffectiveRange){
                        case "PB":
                            hitRoll += 1;
                            break;
                        case "SHORT":
                            hitRoll += 0;
                            break;
                        case "MEDIUM":
                            hitRoll += -1;
                            break;
                        case "LONG":
                            hitRoll += -2;
                            break;
                    }
                    rangeNotFound = false;
                    break;
                case "MEDIUM RANGE": case "medium range": case "2":
                    switch (chosenWeaponEffectiveRange){
                        case "PB":
                            hitRoll += 2;
                            break;
                        case "SHORT":
                            hitRoll += 1;
                            break;
                        case "MEDIUM":
                            hitRoll += 0;
                            break;
                        case "LONG":
                            hitRoll += 1;
                            break;
                    }
                    rangeNotFound = false;
                    break;
                case "LONG RANGE": case "long range": case "3":
                    switch (chosenWeaponEffectiveRange){
                        case "PB":
                            hitRoll += 3;
                            break;
                        case "SHORT":
                            hitRoll += 2;
                            break;
                        case "MEDIUM":
                            hitRoll += 1;
                            break;
                        case "LONG":
                            hitRoll += 0;
                            break;
                    }
                    rangeNotFound = false;
                    break;
                default:
                    if (player.getUserLanguage().equals("VOLKSHAVENISH")) {
                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                    } else if (player.getUserLanguage().equals("ENGLISH")) {
                        System.out.println("//ERROR: INVALID INPUT");
                    }
                    input = new Scanner(System.in);
                    break;
            }
        }

        double baseDamage = Double.parseDouble(chosenWeaponBaseDamage); // Base Damage
        double accuracyThreshold = Double.parseDouble(chosenWeaponAccuracyThreshold); // Accuracy Threshold
        double critThreshold = Double.parseDouble(chosenWeaponCritHitThreshold); // Crit Threshold
        double critMultiplier = Double.parseDouble(chosenWeaponCritHitMultiplier); // Crit Multiplier
        double minBurstLength = Double.parseDouble (chosenMinBurstLength); // Minimum Burst Length
        double maxBurstLength = Double.parseDouble (chosenMaxBurstLength); // Maximum Burst Length
        double chamberLoad = Double.parseDouble(chosenChamberLoad); // Chamber Load
        double chamberCap = Double.parseDouble(chosenChamberCapacity); // Chamber Capacity
        double magazineLoad = Double.parseDouble(chosenMagazineLoad); // Magazine Load
        double spentShots = 0;
        double shotsFired = 0;
        double loadedAmmo = 0;
        double caliberRecoil = 0;
        double recoilTotal = 0;
        double accuracyPenalty = 0;

        int userStrength = Integer.parseInt(player.getUserStrength());

        if (chosenWeaponAttackType.equals("RANGED")){
            switch (chosenCurrentFiremode){
                case "[FULL-AUTO]": case "[BEAM]":
                    System.out.println("//PLEASE ENTER BURST RANGE BETWEEN " + chosenMinBurstLength + " AND " + chosenMaxBurstLength + " " + chosenAmmoUnitOfMeasurement);
                    if (chosenAmmunitionCaliber.equals("Electricity")){
                        while (!input.hasNextDouble()){
                            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                            } else if (player.getUserLanguage().equals("ENGLISH")){
                                System.out.println("//ERROR: INVALID INPUT");
                            }
                            input = new Scanner(System.in);
                        }
                        shotsFired = input.nextDouble();
                    } else {
                        while (!input.hasNextInt()){
                            if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                            } else if (player.getUserLanguage().equals("ENGLISH")){
                                System.out.println("//ERROR: INVALID INPUT");
                            }
                            input = new Scanner(System.in);
                        }
                        shotsFired = input.nextInt();
                    }

                    if (shotsFired < minBurstLength){
                        shotsFired = minBurstLength;
                    }
                    if (shotsFired > maxBurstLength){
                        shotsFired = maxBurstLength;
                    }

                    break;
                case "[3-ROUND BURST]":
                    shotsFired = 3;
                    break;
                case "[2-ROUND BURST]":
                    shotsFired = 2;
                    break;
                case "[SEMI-AUTO]": case "[BREAK-ACTION]":
                    shotsFired = 1;
                    break;
                case "[CHARGE]":
                    shotsFired = chamberLoad;
                    break;
                case "[BOLT-ACTION]": case "[PUMP-ACTION]":
                    shotsFired = 1;
                    chosenChamberStatus = "EMPTY";
                    break;

            }

            spentShots = chamberCap * shotsFired;
            loadedAmmo = chamberLoad + magazineLoad;

            if (spentShots > loadedAmmo){
                spentShots = loadedAmmo;
                chamberLoad = 0;
                magazineLoad = 0;
                chosenChamberStatus = "EMPTY";
            } else {
                loadedAmmo -= spentShots;
                if ( (loadedAmmo - chamberCap) < 0){
                    if (!chosenCurrentFiremode.equals("[BOLT-ACTION]")){
                        chamberLoad = loadedAmmo;
                        magazineLoad = 0;
                    } else {
                        chamberLoad = 0;
                    }
                } else {
                    if (!chosenCurrentFiremode.equals("[BOLT-ACTION]")){
                        chamberLoad = chamberCap;
                        loadedAmmo -= chamberCap;
                        magazineLoad = loadedAmmo;
                    } else {
                        chamberLoad = 0;
                    }

                }
            }

            switch (chosenAmmunitionCaliber){
                case "Electricity": case "8mm Flechette":
                    caliberRecoil = 0;
                    break;
                case "10mm":
                    caliberRecoil = 1;
                    break;
                case ".308": case "12.5mm Katzer Kurzpatrone":
                    caliberRecoil = 2;
                    break;
                case "12.5mm Katzer Langpatrone":
                    caliberRecoil = 3;
                case "12-Gauge":
                    caliberRecoil = 4;
                    break;
                default:
                    caliberRecoil = 0;
                    break;
            }

            recoilTotal = caliberRecoil * spentShots;


            if (modCalc(userStrength) == 0){
                accuracyPenalty = userStrength - recoilTotal;
            } else {
                accuracyPenalty = (userStrength*modCalc(userStrength)) - recoilTotal;
            }

            if (accuracyPenalty < 0){
                if ( (hitRoll+accuracyPenalty) < 1 ){
                    hitRoll = 1;
                } else {
                    hitRoll += accuracyPenalty;
                }
            }

            totalOutgoingDamage = (baseDamage * (hitRoll/accuracyThreshold)) * spentShots;

            if (chosenWeaponSubsystem.equals("PRIMARY")){
                weaponData[27] = Double.toString(magazineLoad);
                weaponData[32] = chosenChamberStatus;
                weaponData[33] = Double.toString(chamberLoad);
            } else {
                weaponData[40] = Double.toString(magazineLoad);
                weaponData[45] = chosenChamberStatus;
                weaponData[46] = Double.toString(chamberLoad);
            }
            String[] ammoData = ammoRead(player, chosenAmmunitionCaliber, chosenAmmunitionSubtype+".TAG");
            damageData[1] = ammoData[2];
        } else {
            totalOutgoingDamage = baseDamage * (hitRoll/accuracyThreshold);
            damageData[1] = "KINETIC";
        }

        if (chosenWeaponCritHitCapability.equals("J") && critRoll >= critThreshold){
            damageData[2] = "J";
            totalOutgoingDamage *= critMultiplier;
        } else {
            damageData[2] = "N";
        }

        if (!weaponName.equals("PWFM") && !weaponName.equals("SWFM")){
            weaponWrite(player, weaponName, weaponData);
        }
        if (chosenSpecialProperties.contains("[LASER]")){
            damageData[1] = "LASER";
        }
        damageData[0] = Double.toString(totalOutgoingDamage);

        return damageData;
    }

    private static void weaponEquip (playerCharacter player) throws IOException, InterruptedException{
        File[] inventory = player.getPlayerInventory();
        System.out.print("//ACCESSING LOCAL ARSENAL... ");
        File[] weapons = new File(String.valueOf(inventory[1])).listFiles(File::isDirectory);
        String[] tempWeaponData;
        if (weapons.length != 0){
            ShortDelay();
            System.out.println("COMPLETE");
            ShortDelay();
            for (int i = 0; i < weapons.length; i++) {
                System.out.print("> "+ i + ". " + weapons[i].getName());
                File frameTag = new File(weapons[i]+"\\weaponFrame.TAG");
                if (frameTag.exists()){
                    tempWeaponData = weaponRead(player, weapons[i].getName());
                    if (player.getPrimaryWeapon().equals(weapons[i].getName())){
                        System.out.print(" [EQUIPPED (PRIMARY)]");
                    } else {
                        System.out.print("");
                    }
                    if (player.getSecondaryWeapon().equals(weapons[i].getName())){
                        System.out.print(" [EQUIPPED (SECONDARY)]");
                    } else {
                        System.out.print("");
                    }
                    if (tempWeaponData[8].equals("INCOMPLETE")){
                        System.out.print(" [WARNING: ESSENTIAL COMPONENTS MISSING]");
                    } else {
                        System.out.print("");
                    }
                } else {
                    System.out.print(" [WARNING: FRAME TAG UNREADABLE]");
                }
                System.out.println();
                NoDelay();
            }
        } else {
            LongDelay();
            System.out.println("ERROR");
            ShortDelay();
            System.out.println("//NO ITEMS MATCHING FILTER FOUND IN INVENTORY");
        }
        System.out.println();
        ShortDelay();
        System.out.println("> A_ REFRESH");
        NoDelay();
        System.out.println("> ._ EXIT");
        String inputString = input.nextLine();
        boolean weaponNotChosen = true;
        while ( weaponNotChosen ) {
            switch (inputString) {
                case "AKTUALISIEREN": case "aktualisieren": case "REFRESH": case "refresh": case "AKTL": case "aktl": case "A": case "a":
                    input = new Scanner(System.in);
                    weaponEquip(player);
                    return;
                case "EXIT": case "exit": case "AUSF": case "ausf": case ".":
                    System.out.println("//ARSENAL SUBMENU EXITED");
                    return;
                default:
                    boolean weaponInstanceNotChosen = true;
                    int chosenWeaponIndex;
                    int lastInstanceOfQueriedWeapon = -1;
                    int instancesOfQueriedWeapon = 0;
                    while (weaponInstanceNotChosen){
                        try {
                            chosenWeaponIndex = Integer.parseInt(inputString);
                        } catch (Exception e0){
                            lastInstanceOfQueriedWeapon = -1;
                            instancesOfQueriedWeapon = 0;
                            for (int i = 0; i < weapons.length; i++){
                                if (weapons[i].getName().contains(inputString)){
                                    instancesOfQueriedWeapon ++;
                                    lastInstanceOfQueriedWeapon = i;
                                }
                            }
                            if (instancesOfQueriedWeapon > 1){
                                System.out.println("//ERROR: MULTIPLE INSTANCES OF SPECIFIED WEAPON IN INVENTORY");
                                ShortDelay();
                                for (int i = 0; i < weapons.length; i ++){
                                    if (weapons[i].getName().contains(inputString)){
                                        System.out.println("> " + i + "_ " + weapons[i].getName() );
                                        NoDelay();
                                    }
                                }
                                ShortDelay();
                                System.out.println("//PLEASE SPECIFY SINGLE INSTANCE OF WEAPON");
                                inputString = input.nextLine();
                                break;
                            } else if (instancesOfQueriedWeapon == 0){
                                System.out.println("//ERROR: WEAPON NOT FOUND");
                                inputString = input.nextLine();
                                break;
                            } else {
                                chosenWeaponIndex = lastInstanceOfQueriedWeapon;
                            }
                        }
                        if (chosenWeaponIndex < weapons.length && chosenWeaponIndex != -1){
                            File frameTag = new File(weapons[chosenWeaponIndex]+"\\weaponFrame.TAG");
                            if (frameTag.exists()){
                                tempWeaponData = weaponRead(player, weapons[chosenWeaponIndex].getName());
                                if (tempWeaponData[8].equals("INCOMPLETE")){
                                    System.out.println("//ERROR: ESSENTIAL COMPONENTS MISSING");
                                    ShortDelay();
                                    System.out.println("//WEAPON NOT EQUIPPED");
                                    weaponInstanceNotChosen = false;
                                    weaponNotChosen = false;
                                } else {
                                    weaponInstanceNotChosen = false;
                                    weaponNotChosen = false;
                                    String weaponName = weapons[chosenWeaponIndex].getName();
                                    if (player.getPrimaryWeapon().equals(weaponName) || player.getSecondaryWeapon().equals(weaponName)){
                                        if (player.getPrimaryWeapon().equals(weaponName)){
                                            System.out.println("//ERROR: WEAPON ALREADY EQUIPPED AS PRIMARY");
                                            ShortDelay();
                                            System.out.println("//UNEQUIP WEAPON? J/N");
                                            boolean equipOptionNotChosen = true;
                                            while ( equipOptionNotChosen ){
                                                switch (input.nextLine()){
                                                    case "J": case "j":
                                                        player.setPrimaryWeapon("PWFM");
                                                        System.out.println("//WEAPON UNEQUIPPED");
                                                        ShortDelay();
                                                        System.out.println("//INPUT \"A\" TO REFRESH MENU");
                                                        equipOptionNotChosen = false;
                                                        break;
                                                    case "N": case "n":
                                                        System.out.println("//WEAPON CHANGE CANCELLED");
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
                                                        ShortDelay();
                                                        System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                                                        break;
                                                    case "#KVSCLE": //changes language to English
                                                        equipOptionNotChosen = true;
                                                        player.setUserLanguage("ENGLISH");
                                                        System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                                        ShortDelay();
                                                        System.out.println("//INPUT \"A\" TO REFRESH");
                                                        break;
                                                    default:
                                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                                            System.out.println("//ERROR: INVALID INPUT");
                                                        }
                                                        break;
                                                }
                                            }
                                        } else {
                                            weaponInstanceNotChosen = false;
                                            weaponNotChosen = false;
                                            System.out.println("//ERROR: WEAPON ALREADY EQUIPPED AS SECONDARY");
                                            ShortDelay();
                                            System.out.println("//UNEQUIP WEAPON? J/N");
                                            boolean equipOptionNotChosen = true;
                                            while ( equipOptionNotChosen ){
                                                switch (input.nextLine()){
                                                    case "J": case "j":
                                                        player.setPrimaryWeapon("SWFM");
                                                        System.out.println("//WEAPON UNEQUIPPED");
                                                        ShortDelay();
                                                        System.out.println("//INPUT \"A\" TO REFRESH MENU");
                                                        equipOptionNotChosen = false;
                                                        break;
                                                    case "N": case "n":
                                                        System.out.println("//WEAPON CHANGE CANCELLED");
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
                                                        ShortDelay();
                                                        System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                                                        break;
                                                    case "#KVSCLE": //changes language to English
                                                        equipOptionNotChosen = true;
                                                        player.setUserLanguage("ENGLISH");
                                                        System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                                        ShortDelay();
                                                        System.out.println("//INPUT \"A\" TO REFRESH");
                                                        break;
                                                    default:
                                                        if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                            System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                        } else if (player.getUserLanguage().equals("ENGLISH")){
                                                            System.out.println("//ERROR: INVALID INPUT");
                                                        }
                                                        break;
                                                }
                                            }
                                        }
                                        break;
                                    } else {
                                        weaponInstanceNotChosen = false;
                                        weaponNotChosen = false;
                                        System.out.println("//ARE YOU SURE YOU WANT TO EQUIP THIS WEAPON? J/N");
                                        boolean equipOptionNotChosen = true;
                                        while ( equipOptionNotChosen ) {
                                            switch ( input.nextLine() ) {
                                                case "J": case "j":
                                                    System.out.println("//SELECT SLOT TO EQUIP WEAPON");
                                                    ShortDelay();
                                                    System.out.println("> 0_ PRIMARY [PRMR] ");
                                                    NoDelay();
                                                    System.out.println("> 1_ SECONDARY [SKDR] ");
                                                    boolean weaponNotEquipped = true;
                                                    while (weaponNotEquipped){
                                                        switch ( input.nextLine() ){
                                                            case "PRIMÄR": case "primär": case "PRIMAR": case "primar": case "PRIMAER": case "primaer": case "PRMR": case "prmr": case "0":
                                                                weaponNotEquipped = false;
                                                                player.setPrimaryWeapon( weaponName );
                                                                player.writePlayerData();
                                                                System.out.println("// WEAPON EQUIPPED AS PRIMARY WEAPON");
                                                                ShortDelay();
                                                                System.out.println("// INPUT \"A\" TO REFRESH MENU");
                                                                break;
                                                            case "SEKUNDÄR": case "sekundär": case "SEKUNDAR": case "sekundar": case "SEKUNDAER": case "sekundaer": case "SKDR": case "skdr": case "1":
                                                                weaponNotEquipped = false;
                                                                player.setSecondaryWeapon( weaponName );
                                                                player.writePlayerData();
                                                                System.out.println("// WEAPON EQUIPPED AS SECONDARY WEAPON");
                                                                ShortDelay();
                                                                System.out.println("// INPUT \"A\" TO REFRESH MENU");
                                                                break;
                                                            default:
                                                                if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                                    System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                                } else if (player.getUserLanguage().equals("ENGLISH")){
                                                                    System.out.println("//ERROR: INVALID INPUT");
                                                                }
                                                                break;
                                                        }
                                                    }
                                                    equipOptionNotChosen = false;
                                                    break;
                                                case "N": case "n":
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
                                                    ShortDelay();
                                                    System.out.println("//EINGABE \"A\" ZUR ERFRISCHUNG");
                                                    break;
                                                case "#KVSCLE": //changes language to English
                                                    equipOptionNotChosen = true;
                                                    player.setUserLanguage("ENGLISH");
                                                    System.out.println("//LANGUAGE PREFERENCE UPDATED");
                                                    ShortDelay();
                                                    System.out.println("//INPUT \"A\" TO REFRESH");
                                                    break;
                                                default:
                                                    if (player.getUserLanguage().equals("VOLKSHAVENISH")){
                                                        System.out.println("//FEHLER: UNGÜLTIGE EINGABE"); //ERROR: INVALID INPUT
                                                    } else if (player.getUserLanguage().equals("ENGLISH")){
                                                        System.out.println("//ERROR: INVALID INPUT");
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                System.out.println("//ERROR: UNABLE TO READ WEAPON FRAME INFORMATION");
                                ShortDelay();
                                System.out.println("//WEAPON NOT EQUIPPED");
                                return;
                            }
                        } else {
                            System.out.println("//ERROR: WEAPON NOT FOUND");
                            inputString = input.nextLine();
                        }
                    }
            }
        }
    }

    private static void sing( playerCharacter player ) throws IOException, InterruptedException {
        ClearScreen();
        System.out.print("//LOADING VOCAL PERFORMANCE SUBROUTINE... ");
        LongDelay();
        System.out.println("COMPLETE");
        ShortDelay();
        System.out.println("//THIS SUBROUTINE IS DESIGNED TO WORK WITH THE VERSION OF \"HELLO WORLD\" BY LOUIE ZONG LISTED ON YOUTUBE");
        ShortDelay();
        System.out.println("//PLEASE PRESS ENTER WHEN YOU ARE READY");
        petc.nextLine();
        System.out.println("//3... ");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("//2... ");
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
        System.out.println("WORLD... ");
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
        System.out.println("WORLD... ");
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
        System.out.println("MITES... ");
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
        System.out.println("WORLD... ");
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
        System.out.println("PLUG... ");
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
        System.out.println("TING... ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("//HEL-");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.print("LO ");
        TimeUnit.MILLISECONDS.sleep( 545 );
        System.out.println("WORLD... ");
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
        System.out.println("WORLD... ");
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
        mainMenu( player );




    }

    private static void shutdown( playerCharacter player ) throws InterruptedException, IOException, NullPointerException {
        if (!player.isEmpty){
            System.out.print( "//SCHREIBEN DATEN AUF FESTPLATTE... " );
            player.writePlayerData();
            ShutoffDelay();
            System.out.println( "KOMPLETT" );
        }
        ShortDelay();
        System.out.println( "//IST DAS JETZT SICHER ZU DEAKTIVIEREN EINHEIT");
        ShortDelay();
        System.out.println ( "//DRÜCKEN EINGEBEN/RÜCKKEHR ZU FORTSETZEN" );
        petc.nextLine();
        ClearScreen();
        System.exit( 0 );
    }
}
