char val;
int ledpin = 13;

//a basic setup for connecting to bluetooth
void setup() {
  
  char val;
  pinMode(ledpin, OUTPUT);
  Serial.begin(115200);

}

void loop() {
  if(Serial.available() ){
    val = Serial.read();
    if(val == '0'){
      digitalWrite(ledpin, LOW);
      delay(1000);
      Serial.println("13 is on");
    }
    else if (val == '1') {
      digitalWrite(ledpin, HIGH);
      delay(100);
      Serial.println("13 is off");
    }
  }
}
