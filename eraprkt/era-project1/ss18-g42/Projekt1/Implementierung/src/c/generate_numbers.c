#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

#define AMOUNT 1000
#define HIGH_PREC_AMOUNT 1000

int main() {
  int i;
  float m_currentNumber, h_currentNumber;
  float m_incrementAmount, h_incrementAmount;
  m_currentNumber = 0.0;
  m_incrementAmount = 1.0 / AMOUNT;
  h_currentNumber = 0.9;
  h_incrementAmount = 0.1 / HIGH_PREC_AMOUNT;

  char fileMain[32];
  sprintf(fileMain, "asin_%d", AMOUNT);
  FILE *fMain = fopen(fileMain, "w");

  char fileHigh[32];
  sprintf(fileHigh, "asin_high_%d", HIGH_PREC_AMOUNT);
  FILE *fHigh = fopen(fileHigh, "w");

  
  

  if(fMain == NULL || fHigh == NULL) {
    printf("Couldn't open file!\n");
    return 1;
  }

  float h_out;
  float m_out;
  for(i=0; i < AMOUNT; i++){
    
    if(m_currentNumber >= 1.0){
      break;
    }

    m_out = asin(m_currentNumber);
    
    fprintf(fMain, "%.7f", m_out);
    fprintf(fMain, ", ");

    m_currentNumber = m_currentNumber + m_incrementAmount;
    
  }
  for(i=0; i < HIGH_PREC_AMOUNT; i++){
    if(h_currentNumber >= 1.0){
      continue;
    }

    h_out = asin(h_currentNumber);
    
    fprintf(fHigh, "%.7f", h_out);
    fprintf(fHigh, ", ");
    h_currentNumber = h_currentNumber + h_incrementAmount;
  }
  m_out = asin(1.0);
  fprintf(fMain, "%.7f", m_out);
  fprintf(fMain, "\n");
  fprintf(fHigh, "%.7f", m_out);
  fprintf(fHigh, "\n");

  fclose(fMain);
  fclose(fHigh);

  return(0);
}
