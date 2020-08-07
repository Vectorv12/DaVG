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
    String userClass0; //07
    String userClass1; //08
    String userClass2; //09
    String userClass3; //10
    String userStrength; //11
    String userDexterity; //12
    String userConstitution; //13
    String userIntelligence; //14
    String userWisdom; //15
    String userCharisma; //16
    String userBackground; //17
    String userBackgroundProficiency0; //18
    String userBackgroundProficiency1; //19
    String userSkillProficiency0; //20
    String userSkillProficiency1; //21
    String userSkillProficiency2; //22
    String userSkillProficiency3; //23
    String software00; //25
    String software01; //26
    String software02; //27
    String software03; //28
    String software04; //29
    String software05; //30
    String software06; //31
    String software07; //32
    String software08; //33
    String software09; //34

    String cW1ID; //35
    String cW2ID; //36

    String auxModule; //37

    String specialProperties; //38

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
        this.userClass0 = null; //07, C1M (Class 1 Missing) by default
        this.userClass1 = null; //08, C2M (Class 2 Missing) by default
        this.userClass2 = null; //09, C3M (Class 3 Missing) by default
        this.userClass3 = null; //10, C4M (Class 4 Missing) by default
        this.userStrength = null; //11, StM (Strength Missing) by default
        this.userDexterity = null; //12, DeM (Dexterity Missing) by default
        this.userConstitution = null; //13, CoM (Constitution Missing) by default
        this.userIntelligence = null; //14, InM (Intelligence Missing) by default
        this.userWisdom = null; //15, WiM (Wisdom Missing) by default
        this.userCharisma = null; //16, CaM (Charisma Missing) by default
        this.userBackground = null; //17, BaM (Background Missing) by default
        this.userBackgroundProficiency0 = null; //18, B1M (Background Proficiency 1 Missing) by default
        this.userBackgroundProficiency1 = null; //19, B2M (Background Proficiency 2 Missing) by default
        this.userSkillProficiency0 = null; //20, S1M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency1 = null; //21, S2M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency2 = null; //22, S3M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency3 = null; //23, S4M (Skill Proficiency 1 Missing) by default
        this.software00 = null; //24, T00M (Tape 00 Missing) by default
        this.software01 = null; //25, T01M (Tape 01 Missing) by default
        this.software02 = null; //26, T02M (Tape 02 Missing) by default
        this.software03 = null; //27, T03M (Tape 03 Missing) by default
        this.software04 = null; //28, T04M (Tape 04 Missing) by default
        this.software05 = null; //29, T05M (Tape 05 Missing) by default
        this.software06 = null; //30, T06M (Tape 06 Missing) by default
        this.software07 = null; //31, T07M (Tape 07 Missing) by default
        this.software08 = null; //32, T08M (Tape 08 Missing) by default
        this.software09 = null; //33, T09M (Tape 09 Missing) by default
        this.cW1ID = null; //34, N/A (Fists) by default
        this.cW2ID = null; //35, DwM (Dual-Wield Missing) by default
        this.auxModule = null; //36
        this.specialProperties = null; //37
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

    public String getUserClass0(){
        return userClass0;
    }

    public void setUserClass0( String inputClass ){
        this.userClass0 = inputClass;
    }

    //Index 08

    public String getUserClass1(){
        return userClass1;
    }

    public void setUserClass1( String inputClass ){
        this.userClass1 = inputClass;
    }

    //Index 09

    public String getUserClass2(){
        return userClass2;
    }

    public void setUserClass2( String inputClass ){
        this.userClass2 = inputClass;
    }

    //Index 10

    public String getUserClass3(){
        return userClass3;
    }

    public void setUserClass3( String inputClass ){
        this.userClass3 = inputClass;
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

    public String getUserBackgroundProficiency0(){
        return userBackgroundProficiency0;
    }

    public void setUserBackgroundProficiency0( String proficiency ){
        this.userBackgroundProficiency0 = proficiency;
    }

    //Index 19

    public String getUserBackgroundProficiency1(){
        return userBackgroundProficiency1;
    }

    public void setUserBackgroundProficiency1( String proficiency ){
        this.userBackgroundProficiency1 = proficiency;
    }

    //Index 20

    public String getUserSkillProficiency0(){
        return userSkillProficiency0;
    }

    public void setUserSkillProficiency0( String proficiency ){
        this.userSkillProficiency0 = proficiency;
    }

    //Index 21

    public String getUserSkillProficiency1(){
        return userSkillProficiency1;
    }

    public void setUserSkillProficiency1( String proficiency ){
        this.userSkillProficiency1 = proficiency;
    }

    //Index 22

    public String getUserSkillProficiency2(){
        return userSkillProficiency2;
    }

    public void setUserSkillProficiency2( String proficiency ){
        this.userSkillProficiency2 = proficiency;
    }

    //Index 23

    public String getUserSkillProficiency3(){
        return userSkillProficiency3;
    }

    public void setUserSkillProficiency3( String proficiency ){
        this.userSkillProficiency3 = proficiency;
    }


    //Index 25

    public String getSoftware00(){
        return software00;
    }

    public void setSoftware00( String software ){
        this.software00 = software;
    }

    //Index 26

    public String getSoftware01(){
        return software01;
    }

    public void setSoftware01( String software ){
        this.software01 = software;
    }

    //Index 27

    public String getSoftware02(){
        return software02;
    }

    public void setSoftware02( String software ){
        this.software02 = software;
    }

    //Index 28

    public String getSoftware03(){
        return software03;
    }

    public void setSoftware03( String software ){
        this.software03 = software;
    }

    //Index 29

    public String getSoftware04(){
        return software04;
    }

    public void setSoftware04( String software ){
        this.software04 = software;
    }

    //Index 25

    public String getSoftware05(){
        return software05;
    }

    public void setSoftware05( String software ){
        this.software05 = software;
    }

    //Index 26

    public String getSoftware06(){
        return software06;
    }

    public void setSoftware06( String software ){
        this.software06 = software;
    }

    //Index 27

    public String getSoftware07(){
        return software07;
    }

    public void setSoftware07( String software ){
        this.software07 = software;
    }

    //Index 28

    public String getSoftware08(){
        return software08;
    }

    public void setSoftware08( String software ){
        this.software08 = software;
    }

    //Index 29

    public String getSoftware09(){
        return software09;
    }

    public void setSoftware09( String software ){
        this.software09 = software;
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

    public String getAuxModule(){
        return auxModule;
    }

    public void setAuxModule( String auxModule ){
        this.auxModule = auxModule;
    }

    public String getSpecialProperties(){
        return specialProperties;
    }

    public void setSpecialProperties( String specialProperties ){
        this.specialProperties = specialProperties;
    }



    public void readPlayerData( File playerDataLocation ){
        String[] playerData = new String[38];
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
        this.userLevel = playerData[2]; //02, 000 by default
        this.userXP = playerData[3]; //03, 000 by default
        this.userCurrentHealth = playerData[4]; //04, 100 by default
        this.userMaxHealth = playerData[5]; //05, 100 by default
        this.userStatus = playerData[6]; //06, NORMAL by default
        this.userClass0 = playerData[7]; //07, C1M (Class 1 Missing) by default
        this.userClass1 = playerData[8]; //08, C2M (Class 2 Missing) by default
        this.userClass2 = playerData[9]; //09, C3M (Class 3 Missing) by default
        this.userClass3 = playerData[10]; //10, C4M (Class 4 Missing) by default
        this.userStrength = playerData[11]; //11, StM (Strength Missing) by default
        this.userDexterity = playerData[12]; //12, DeM (Dexterity Missing) by default
        this.userConstitution = playerData[13]; //13, CoM (Constitution Missing) by default
        this.userIntelligence = playerData[14]; //14, InM (Intelligence Missing) by default
        this.userWisdom = playerData[15]; //15, WiM (Wisdom Missing) by default
        this.userCharisma = playerData[16]; //16, CaM (Charisma Missing) by default
        this.userBackground = playerData[17]; //17, BaM (Background Missing) by default
        this.userBackgroundProficiency0 = playerData[18]; //18, B1M (Background Proficiency 1 Missing) by default
        this.userBackgroundProficiency1 = playerData[19]; //19, B2M (Background Proficiency 2 Missing) by default
        this.userSkillProficiency0 = playerData[20]; //20, S1M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency1 = playerData[21]; //21, S2M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency2 = playerData[22]; //22, S3M (Skill Proficiency 1 Missing) by default
        this.userSkillProficiency3 = playerData[23]; //23, S4M (Skill Proficiency 1 Missing) by default
        this.software00 = playerData[24]; //25, T1M (Tape 1 Missing) by default
        this.software01 = playerData[25]; //26, T2M (Tape 2 Missing) by default
        this.software02 = playerData[26]; //27, T3M (Tape 3 Missing) by default
        this.software03 = playerData[27]; //28, T4M (Tape 4 Missing) by default
        this.software04 = playerData[28]; //29, T5M (Tape 5 Missing) by default
        this.software05 = playerData[29]; //25, T1M (Tape 1 Missing) by default
        this.software06 = playerData[30]; //26, T2M (Tape 2 Missing) by default
        this.software07 = playerData[31]; //27, T3M (Tape 3 Missing) by default
        this.software08 = playerData[32]; //28, T4M (Tape 4 Missing) by default
        this.software09 = playerData[33]; //29, T5M (Tape 5 Missing) by default
        this.cW1ID = playerData[34]; //30, N/A (Fists) by default
        this.cW2ID = playerData[35]; //31, DwM (Dual-Wield Missing) by default
        this.auxModule = playerData[36];
        this.specialProperties = playerData[37];
    }

    public void writePlayerData () throws IOException {
        playerData  = new String[38];
        playerData[0] = getUserName()+"~";
        playerData[1] = getUserLanguage()+"~";
        playerData[2] = getUserLevel()+"~";
        playerData[3] = getUserXP()+"~";
        playerData[4] = getUserCurrentHealth()+"~";
        playerData[5] = getUserMaxHealth()+"~";
        playerData[6] = getUserStatus()+"~";
        playerData[7] = getUserClass0()+"~";
        playerData[8] = getUserClass1()+"~";
        playerData[9] = getUserClass2()+"~";
        playerData[10] = getUserClass3()+"~";
        playerData[11] = getUserStrength()+"~";
        playerData[12] = getUserDexterity()+"~";
        playerData[13] = getUserConstitution()+"~";
        playerData[14] = getUserIntelligence()+"~";
        playerData[15] = getUserWisdom()+"~";
        playerData[16] = getUserCharisma()+"~";
        playerData[17] = getUserBackground()+"~";
        playerData[18] = getUserBackgroundProficiency0()+"~";
        playerData[19] = getUserBackgroundProficiency1()+"~";
        playerData[20] = getUserSkillProficiency0()+"~";
        playerData[21] = getUserSkillProficiency1()+"~";
        playerData[22] = getUserSkillProficiency2()+"~";
        playerData[23] = getUserSkillProficiency3()+"~";
        playerData[24] = getSoftware00()+"~";
        playerData[25] = getSoftware01()+"~";
        playerData[26] = getSoftware02()+"~";
        playerData[27] = getSoftware03()+"~";
        playerData[28] = getSoftware04()+"~";
        playerData[29] = getSoftware05()+"~";
        playerData[30] = getSoftware06()+"~";
        playerData[31] = getSoftware07()+"~";
        playerData[32] = getSoftware08()+"~";
        playerData[33] = getSoftware09()+"~";
        playerData[34] = getPrimaryWeapon()+"~";
        playerData[35] = getSecondaryWeapon()+"~";
        playerData[36] = getAuxModule()+"~";
        playerData[37] = getSpecialProperties();

        writer = new BufferedWriter(new FileWriter(getPlayerDataLocation()));
        for (int i = 0; i < playerData.length; i++){
            writer.write (playerData[i]);
        }
        writer.flush();
        writer.close();
    }
}
