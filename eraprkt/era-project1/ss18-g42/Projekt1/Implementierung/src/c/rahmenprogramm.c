#include <stdio.h>
#include <stdlib.h>
#include <math.h>

extern float doit(float);

int main(int argc, char **argv) {
	float input = 1.0;
	if(argc > 1){
		input = atof(argv[1]);	
	}
	printf("Input: %f\nRoutine output: %f\nLibrary output: %f\n", input, doit(input), asin(input));
}
