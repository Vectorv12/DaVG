import java.io.*;

public class playerCharacter {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StringBuilder builder;

    String[] daVGDir;
    File[] playerInventory;
    String docPath;
    String userProfile;
    String[] playerData;
    File playerDataLocation;

    boolean isEmpty;

    String userName; //00
    String userLanguage; //01
    String userLevel; //02
    String userXP; //03
    String userCurrentHealth; //04
    String userMaxHealth; //05
    String userStatus; //06
    String userClass1; //07
    String userClass2; //08
    String userClass3; //09
    String userClass4; //10
    String userStrength; //11
    String userDexterity; //12
    String userConstitution; //13
    String userIntelligence; //14
    String userWisdom; //15
    String userCharisma; //16
    String userBackground; //17
    String userBackgroundProficiency1; //18
    String userBackgroundProficiency2; //19
    String userSkillProficiency1; //20
    String userSkillProficiency2; //21
    String userSkillProficiency3; //22
    String userSkillProficiency4; //23
    String userWeaponProficiency; //24
    String software1; //25
    String software2; //26
    String software3; //27
    String software4; //28
    String software5; //29



    String cWNAME; //30a
    String cWROLE; //30b
    String cWTYPE; //30c
    int cWFR; //30d
    String cWMFGR; //30e
    int cWDAMG; //30f
    int cWACCT; //30g
    String cWCRHC; //30h
    int cWCRHT; //30i
    double cWCRHM; //30j
    String cWEFFR; //30k
    String cWAMMO; //30l
    int cWMGZN; //30m
    int cWAUPS; //30n
    String cWFRMD; //30o
    String cW1ID; //30p

    String cW2NAME; //31a
    String cW2ROLE; //31b
    String cW2TYPE; //31c
    int cW2FR; //31d
    String cW2MFGR; //31e
    int cW2DAMG; //31f
    int cW2ACCT; //31g
    String cW2CRHC; //31h
    int cW2CRHT; //31i
    double cW2CRHM; //31j
    String cW2EFFR; //31k
    String cW2AMMO; //31l
    int cW2MGZN; //31m
    int cW2AUPS; //31n
    String cW2FRMD; //31o
    String cW2ID; //31p

    // \/SIDEARMS\/
    File TESTWEAP; //Test Weapon, ID 000
    File TESTWEAPTAG;

    //NOTE: Fists are ID 001

    File FtA_45; //FtA 45 Crossbow Pistol, ID 002
    File FtA_45TAG;

    File LP61; //LP61 Laser Pistol, ID 003
    File LP61TAG;

    File MP60; //MP60 Machine Pistol, ID 004
    File MP60TAG;

    File P1885; // P1885 Pistol, ID 005
    File P1885TAG;

    File P60; // P60 Pistol, ID 006
    File P60TAG;
    // /\ SIDEARMS /\

    public playerCharacter(){
        this.daVGDir = null;
        this.playerInventory = null;
        this.docPath = null;
        this.userProfile = null;
        this.playerData = null;
        this.playerDataLocation = null;

        this.isEmpty = true;
        this.userName = null; //00
        this.userLanguage = null; //01, "VOLKSHAVENISH" by default
        this.userLevel = null; //02, 001 by default
        this.userXP = null; //03, 00 by default
        this.userCurrentHealth = null; //04, 100 by default
        this.userMaxHealth = null; //05, 100 by default
        this.userStatus = null; //06, NORMAL by default
        this.userClass1 = null; //07, C1M (Class 1 Missing) by default
        this.userClass2 = null; //08, C2M (Class 2 Missing) by default
        this.userClass3 = null; //09, C3M (Class 3 Missing) by default
        this.userClass4 = null; //10, C4M (Class 4 Missing) by default
        this.userStrength = null; //11, StM (Strength Missing) by default
        this.userDexterity = null; //12, DeM (Dexterity Missing) by default
        this.userConstitution = null; //13, CoM (Constitution Missing) by default
        this.userIntelligence = null; //14, InM (Intelligence Missing) by default
        this.userWisdom = null; //15, WiM (Wisdom Missing) by default
        this.userCharisma = null; //16, CaM (Charisma Missing) by default
        this.userBackground = null; //17, BaM (Background Missing) by default
        this.userBackgroundProficiency1 = null; //18, B1M (Background Proficiency 1 Missing) by default
        this.userBackgroundProficiency2 = null; //19, B2M (Background Proficiency 2 Missing) by default
        this.userSkillProficiency1 = null; //20, S1M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency2 = null; //21, S2M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency3 = null; //22, S3M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency4 = null; //23, S4M (Skill Proficiency 1 Missing) by default
        this.userWeaponProficiency = null; //24, WpM (Weapon Proficiency Missing) by default
        this.software1 = null; //25, T1M (Tape 1 Missing) by default
        this.software2 = null; //26, T2M (Tape 2 Missing) by default
        this.software3 = null; //27, T3M (Tape 3 Missing) by default
        this.software4 = null; //28, T4M (Tape 4 Missing) by default
        this.software5 = null; //29, T5M (Tape 5 Missing) by default
        this.cW1ID = null; //30, 001 (Fists) by default
        this.cW2ID = null; //31, DwM (Dual-Wield Missing) by default
    }



    public String[] getDaVGDir() {
        return daVGDir;
    }

    public void setDaVGDir( String[] daVGDir ) {
        this.daVGDir = daVGDir;
    }

    public File[] getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory( File[] playerInventory ) {
        this.playerInventory = playerInventory;
    }

    public String getDocPath(){
        return docPath;
    }

    public void setDocPath ( String docPath ){
        this.docPath = docPath;
    }

    public String getUserProfile(){
        return userProfile;
    }

    public void setUserProfile( String userProfile ){
        this.userProfile = userProfile;
    }

    public File getPlayerDataLocation() {
        return playerDataLocation;
    }

    public void setPlayerDataLocation( File playerDataLocation){
        this.playerDataLocation = playerDataLocation;
    }

    /* The information on a character - the Player Data - is stored in an index of 32 values, shown below:
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
     * 30 - Primary Weapon ID
     * 31 - Secondary Weapon ID (if applicable)
     */

    public boolean getIsEmpty(){
        return isEmpty;
    }

    public void setIsEmpty( boolean state ){
        this.isEmpty = state;
    }

    //Index 00

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //Index 01

    public String getUserLanguage(){
        return userLanguage;
    }

    public void setUserLanguage( String language ){
        this.userLanguage = language;
    }

    //Index 02

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel( String level ) {
        this.userLevel = level;
    }

    //Index 03

    public String getUserXP(){
        return userXP;
    }

    public void setUserXP (String xp){
        this.userXP = xp;
    }

    //Index 04

    public String getUserCurrentHealth(){
        return userCurrentHealth;
    }

    public void setUserCurrentHealth( String health ){
        this.userCurrentHealth = health;
    }

    //Index 05

    public String getUserMaxHealth(){
        return userMaxHealth;
    }

    public void setUserMaxHealth( String health ) {
        this.userMaxHealth = health;
    }

    //Index 06

    public String getUserStatus(){
        return userStatus;
    }

    public void setUserStatus( String status ){
        this.userStatus = status;
    }

    //Index 07

    public String getUserClass1(){
        return userClass1;
    }

    public void setUserClass1( String inputClass ){
        this.userClass1 = inputClass;
    }

    //Index 08

    public String getUserClass2(){
        return userClass2;
    }

    public void setUserClass2 ( String inputClass ){
        this.userClass2 = inputClass;
    }

    //Index 09

    public String getUserClass3(){
        return userClass3;
    }

    public void setUserClass3( String inputClass ){
        this.userClass3 = inputClass;
    }

    //Index 10

    public String getUserClass4(){
        return userClass4;
    }

    public void setUserClass4( String inputClass ){
        this.userClass4 = inputClass;
    }

    //Index 11

    public String getUserStrength(){
        return userStrength;
    }

    public void setUserStrength( String strength ){
        this.userStrength = strength;
    }

    //Index 12

    public String getUserDexterity(){
        return userDexterity;
    }

    public void setUserDexterity( String dexterity ){
        this.userDexterity = dexterity;
    }

    //Index 13

    public String getUserConstitution(){
        return userConstitution;
    }

    public void setUserConstitution( String constitution ){
        this.userConstitution = constitution;
    }

    //Index 14

    public String getUserIntelligence(){
        return userIntelligence;
    }

    public void setUserIntelligence( String intelligence ) {
        this.userIntelligence = intelligence;
    }

    //Index 15

    public String getUserWisdom(){
        return userWisdom;
    }

    public void setUserWisdom( String wisdom ) {
        this.userWisdom = wisdom;
    }

    //Index 16

    public String getUserCharisma(){
        return userCharisma;
    }

    public void setUserCharisma( String charisma ){
        this.userCharisma = charisma;
    }

    //Index 17

    public String getUserBackground(){
        return userBackground;
    }

    public void setUserBackground( String background ){
        this.userBackground = background;
    }

    //Index 18

    public String getUserBackgroundProficiency1(){
        return userBackgroundProficiency1;
    }

    public void setUserBackgroundProficiency1( String proficiency ){
        this.userBackgroundProficiency1 = proficiency;
    }

    //Index 19

    public String getUserBackgroundProficiency2(){
        return userBackgroundProficiency2;
    }

    public void setUserBackgroundProficiency2( String proficiency ){
        this.userBackgroundProficiency2 = proficiency;
    }

    //Index 20

    public String getUserSkillProficiency1(){
        return userSkillProficiency1;
    }

    public void setUserSkillProficiency1( String proficiency ){
        this.userSkillProficiency1 = proficiency;
    }

    //Index 21

    public String getUserSkillProficiency2(){
        return userSkillProficiency2;
    }

    public void setUserSkillProficiency2( String proficiency ){
        this.userSkillProficiency2 = proficiency;
    }

    //Index 22

    public String getUserSkillProficiency3(){
        return userSkillProficiency3;
    }

    public void setUserSkillProficiency3( String proficiency ){
        this.userSkillProficiency3 = proficiency;
    }

    //Index 23

    public String getUserSkillProficiency4(){
        return userSkillProficiency4;
    }

    public void setUserSkillProficiency4( String proficiency ){
        this.userSkillProficiency4 = proficiency;
    }

    //Index 24

    public String getUserWeaponProficiency(){
        return userWeaponProficiency;
    }

    public void setUserWeaponProficiency ( String weaponClass ){
        this.userWeaponProficiency = weaponClass;
    }

    //Index 25

    public String getSoftware1(){
        return software1;
    }

    public void setSoftware1( String software ){
        this.software1 = software;
    }

    //Index 26

    public String getSoftware2(){
        return software2;
    }

    public void setSoftware2( String software ){
        this.software2 = software;
    }

    //Index 27

    public String getSoftware3(){
        return software3;
    }

    public void setSoftware3( String software ){
        this.software3 = software;
    }

    //Index 28

    public String getSoftware4(){
        return software4;
    }

    public void setSoftware4( String software ){
        this.software4 = software;
    }

    //Index 29

    public String getSoftware5(){
        return software5;
    }

    public void setSoftware5( String software ){
        this.software5 = software;
    }

    //Index 30

    public String getPrimaryWeapon(){
        return cW1ID;
    }

    public void setPrimaryWeapon( String W1ID ){
        this.cW1ID = W1ID;
    }

    //Index 31

    public String getSecondaryWeapon(){return cW2ID;}

    public void setSecondaryWeapon( String W2ID ){
        this.cW2ID = W2ID;
    }



    public void readPlayerData( File playerDataLocation ){
        String[] playerData = new String[32];
        try {
            reader = new BufferedReader(new FileReader(playerDataLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            playerData = reader.readLine().split("~");
            reader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        this.userName = playerData[0]; //00
        this.userLanguage = playerData[1]; //01, "VOLKSHAVENISH" by default
        this.userLevel = playerData[2]; //02, 001 by default
        this.userXP = playerData[3]; //03, 000 by default
        this.userCurrentHealth = playerData[4]; //04, 100 by default
        this.userMaxHealth = playerData[5]; //05, 100 by default
        this.userStatus = playerData[6]; //06, NORMAL by default
        this.userClass1 = playerData[7]; //07, C1M (Class 1 Missing) by default
        this.userClass2 = playerData[8]; //08, C2M (Class 2 Missing) by default
        this.userClass3 = playerData[9]; //09, C3M (Class 3 Missing) by default
        this.userClass4 = playerData[10]; //10, C4M (Class 4 Missing) by default
        this.userStrength = playerData[11]; //11, StM (Strength Missing) by default
        this.userDexterity = playerData[12]; //12, DeM (Dexterity Missing) by default
        this.userConstitution = playerData[13]; //13, CoM (Constitution Missing) by default
        this.userIntelligence = playerData[14]; //14, InM (Intelligence Missing) by default
        this.userWisdom = playerData[15]; //15, WiM (Wisdom Missing) by default
        this.userCharisma = playerData[16]; //16, CaM (Charisma Missing) by default
        this.userBackground = playerData[17]; //17, BaM (Background Missing) by default
        this.userBackgroundProficiency1 = playerData[18]; //18, B1M (Background Proficiency 1 Missing) by default
        this.userBackgroundProficiency2 = playerData[19]; //19, B2M (Background Proficiency 2 Missing) by default
        this.userSkillProficiency1 = playerData[20]; //20, S1M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency2 = playerData[21]; //21, S2M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency3 = playerData[22]; //22, S3M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency4 = playerData[23]; //23, S4M (Skill Proficiency 1 Missing) by default
        this.userWeaponProficiency = playerData[24]; //24, WpM (Weapon Proficiency Missing) by default
        this.software1 = playerData[25]; //25, T1M (Tape 1 Missing) by default
        this.software2 = playerData[26]; //26, T2M (Tape 2 Missing) by default
        this.software3 = playerData[27]; //27, T3M (Tape 3 Missing) by default
        this.software4 = playerData[28]; //28, T4M (Tape 4 Missing) by default
        this.software5 = playerData[29]; //29, T5M (Tape 5 Missing) by default
        this.cW1ID = playerData[30]; //30, 001 (Fists) by default
        this.cW2ID = playerData[31]; //31, DwM (Dual-Wield Missing) by default
    }

    public void writePlayerData () throws IOException {
        String [] playerData = new String[32];
        playerData[0] = getUserName()+"~";
        playerData[1] = getUserLanguage()+"~";
        playerData[2] = getUserLevel()+"~";
        playerData[3] = getUserXP()+"~";
        playerData[4] = getUserCurrentHealth()+"~";
        playerData[5] = getUserMaxHealth()+"~";
        playerData[6] = getUserStatus()+"~";
        playerData[7] = getUserClass1()+"~";
        playerData[8] = getUserClass2()+"~";
        playerData[9] = getUserClass3()+"~";
        playerData[10] = getUserClass4()+"~";
        playerData[11] = getUserStrength()+"~";
        playerData[12] = getUserDexterity()+"~";
        playerData[13] = getUserConstitution()+"~";
        playerData[14] = getUserIntelligence()+"~";
        playerData[15] = getUserWisdom()+"~";
        playerData[16] = getUserCharisma()+"~";
        playerData[17] = getUserBackground()+"~";
        playerData[18] = getUserBackgroundProficiency1()+"~";
        playerData[19] = getUserBackgroundProficiency2()+"~";
        playerData[20] = getUserSkillProficiency1()+"~";
        playerData[21] = getUserSkillProficiency2()+"~";
        playerData[22] = getUserSkillProficiency3()+"~";
        playerData[23] = getUserSkillProficiency4()+"~";
        playerData[24] = getUserWeaponProficiency()+"~";
        playerData[25] = getSoftware1()+"~";
        playerData[26] = getSoftware2()+"~";
        playerData[27] = getSoftware3()+"~";
        playerData[28] = getSoftware4()+"~";
        playerData[29] = getSoftware5()+"~";
        playerData[30] = getPrimaryWeapon()+"~";
        playerData[31] = getSecondaryWeapon()+"~";

        writer = new BufferedWriter(new FileWriter(getPlayerDataLocation()));
        for (int i = 0; i < 32; i++){
            writer.write (playerData[i]);
        }
        writer.flush();
        writer.close();
    }
}
