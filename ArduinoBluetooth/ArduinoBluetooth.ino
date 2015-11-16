#include "EEPROM.h"

#define L1 2 // light 1
#define L2 4 // light 2
#define L3 7 // light 3 
#define L4 8 // light 4
#define AC 12 // Air Conditioner control
#define TH 3 // Alarm hours information
#define TM 5 // Alarm minutes information
#define AL 13 // Turning alarm on/off

#define cmdL 'L'      //UART-command for the lights
#define cmdA 'A'      //UART-command for the air conditioner or heater
#define cmdH 'H'      //UART-command for the time of hours for the alarm
#define cmdM 'M'      //UART-command for the time of minutes for the alarm
#define cmdF 'F'      // UART-command for EEPROM operation
#define cmdr 'r'      // UART-command for EEPROM operation (read)
#define cmdw 'w'      // UART-command for EEPROM operation (write)

char incomingByte; // incoming data

char L_Data[4];    //array for light data
byte L_index = 0;  // index of array L
char A_Data[1];    //array for AC data
byte A_index =0;   // index of array A
char H_Data[8];    //array for Hours data
byte H_index =0;   // index of array H
char M_Data[8];    // array for Minutes data
byte M_index = 0;  // index of array M
char F_Data[8];    // array for EEPROM
byte F_index = 0;  // index of F data
char command;      // command 

unsigned long currentTime, lastTimeCommand, autoOFF; //timekeeping variable 
// TODO: change last time command to daily time reseter

void setup(){
  Serial.begin(115200);   //Initialization of UART, the number represents the baud number that the bluetooth device runs at 
  // initializing of all output 
  pinMode(L1, OUTPUT);
  pinMode(L2, OUTPUT);
  pinMode(L3, OUTPUT);
  pinMode(L4, OUTPUT);
  pinMode(AC, OUTPUT);
  pinMode(AL, OUTPUT); // TODO change AL to be the whether the alarm can get set off, it is currently set to turn the alarm off/on
  timer_init();        // Initialise timer
  
}
void timer_init(){
 uint8_t sw_autoOFF = EEPROM.read(0); // read EEPROM whether the device is connected or not.
 if( sw_autoOFF == '1'){
   char var_Data[3];
   var_Data[0] = EEPROM.read(1);
   var_Data[1] = EEPROM.read(2);
   var_Data[2] = EEPROM.read(3);
   autoOFF = atoi(var_Data)*100; // variable autoOFF ms 
 }
 else if (sw_autoOFF == '0'){
  autoOFF = 999999; 
 }
 else if (sw_autoOFF == 255){
  autoOFF = 2500; //  
 }
  currentTime = millis(); // reads eclapsed time 
}

void loop(){
  if(Serial.available() > 0){   //if data is being transmitted 
   incomingByte = Serial.read(); // then read the data
   if(incomingByte == cmdL){ // read the data if it was for the light
     command = cmdL;
     memset(L_Data,0,sizeof(L_Data));
     L_index = 0;
   
   }
   else if (incomingByte == cmdA){ // read air conditioner or heater data
     command = cmdA;
     memset(A_Data,0,sizeof(A_Data));
     A_index = 0;
   
   }
   else if( incomingByte == cmdH){  // read hours data
    command = cmdH;
    memset(H_Data,0,sizeof(H_Data));
    H_index = 0; 
   }
   else if( incomingByte == cmdM){ // read minutes data
    command = cmdM; 
    memset(F_Data,0,sizeof(F_Data));
    F_index = 0;
   }
   else if(incomingByte == cmdF){
    command = cmdF;
    memset(F_Data,0,sizeof(F_Data));
    F_index = 0; 
   }
   else if(incomingByte  == '\r')command = 'e'; // end of line
   else if(incomingByte == '\t') command = 't'; // end of line for EEPROM 
   
   if( command ==cmdL && incomingByte != cmdL){
    L_Data[L_index] = incomingByte;
    L_index++; 
   }
   else if( command = cmdA && incomingByte != cmdA){
     A_Data[A_index] = incomingByte;   
     A_index++;  
   }
   else if( command = cmdH && incomingByte != cmdH){
     H_Data[H_index] = incomingByte;
     H_index++;
   }
   else if ( command = cmdM && incomingByte != cmdM){
     M_Data[M_index] = incomingByte;
     M_index++;
   }
   else if ( command = cmdF && incomingByte != cmdF){
     F_Data[F_index] = incomingByte;
     F_index++;
   }
   else if ( command == 'e'){ 
    RoomControl(atoi(L_Data), atoi(A_Data));
    delay(10);
   }
   else if( command == 't'){
     Flash_Op(F_Data[0],F_Data[1],F_Data[2],F_Data[3],F_Data[4]);

     
   }   
   
   lastTimeCommand = millis();
  }
  if(millis() >= (lastTimeCommand + autoOFF)){
    //TODO create hardware command
  }
  
}

void Flash_Op(char FCMD, uint8_t z1, uint8_t z2, uint8_t z3, uint8_t z4){

  if(FCMD == cmdr){           // if EEPROM data read command
    Serial.print("FData:");       // send EEPROM data
    Serial.write(EEPROM.read(0));     // read value from the memory with 0 address and print it to UART
    Serial.write(EEPROM.read(1));
    Serial.write(EEPROM.read(2));
    Serial.write(EEPROM.read(3));
    Serial.print("\r\n");         // mark the end of the transmission of data EEPROM
  }
  else if(FCMD == cmdw){          // if EEPROM data write command
    EEPROM.write(0,z1);               // z1 record to a memory with 0 address
    EEPROM.write(1,z2);
    EEPROM.write(2,z3);
    EEPROM.write(3,z4);
    timer_init();             // reinitialize the timer
    Serial.print("FWOK\r\n");         // send a message that the data is successfully written to EEPROM
  }
}

void RoomControl(int light,boolean acval){
  boolean light1 = false,light2 = false,light3 = false,light4 = false;
  
  if( light %2 == 1 ) {
    light1 = true;
  }

  if( light >= 8){
    light4 = true;
  }
  if( light/8 >= 4){
    light3 = true;
  }
  if( light/32 >= 2){
    light2 = true;
  }
  digitalWrite(L1, light1);
  digitalWrite(L2, light2);
  digitalWrite(L3, light3);
  digitalWrite(L4, light4);
  digitalWrite(AC, acval);
}


