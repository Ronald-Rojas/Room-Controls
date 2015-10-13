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
#define cmdA 'A'      //UART-command for the air conditioner
#define cmdH 'H'      //UART-command for the time of hours
#define cmdM 'M'      //UART-command for the time of minutes 
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

void(setup(){
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


