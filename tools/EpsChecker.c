// Generic floating point checker, for UofC Programming Contest Control Centre
// Expects output file to have single lines containing floating point values
// Compares them with absolute or relative error
// Note: If the program output has more lines than the judge output, the extra lines are ignored
//
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

char comm[1024];

int main(int argc, char* argv[]){
int ret_code;
  comm[0] = '\0'; 
  strcat(comm,"java -cp ./checkers/ -Xss16M EpsChecker ");
  strcat(comm,argv[1]);
  strcat(comm," ");
  strcat(comm,argv[2]);
  strcat(comm," ");
  strcat(comm,argv[3]);
  ret_code = system(comm);
  exit(ret_code?-1:0);
}

