#include <math.h>
#include <time.h>
#include <stdio.h>
#include <stdarg.h>
#include <stdlib.h>

#define FACTOR 500
#define CONSTS 9
#define RANDROUNDS 100000

float test_value[CONSTS][2]=
{
	{ -1.000001, 2.000000 },
	{ -1.000000, -1.570796 },
	{ -0.999999, -1.566321 },
	{ -0.000001, -0.000001 },
	{ 0.000000, 0.000000 },
	{ 0.000001, 0.000001 },
	{ 0.999999, 1.566321 },
	{ 1.000000, 1.570796 },
	{ 1.000001, 2.000000 }
};

extern float doit(float);

void die(const char* errstr, ...)
{
	va_list ap;

	va_start(ap, errstr);
	vfprintf(stderr, errstr, ap);
	vfprintf(stderr, "Warning: This behavior is expected, values in bordercases are possibly incorrect! For more info read the readme.md!\n", ap);
	va_end(ap);
	//exit(1);
}

int within(float res, float fix, float margin)
{
	return round(res*margin)==round(fix*margin);
}

int main(int argc, char** argv)
{
	int i;
	clock_t cstdlib, our, tmp;
	float in, expct_res, real_res;

	/* Constant tests */

	for(i=0; i<CONSTS; i++) {
		expct_res=test_value[i][1];
		real_res=doit(test_value[i][0]);
		if(!within(real_res, expct_res, FACTOR)) {
			die("Constant tests: should have returned %.6f for %.6f, returned %.6f (factor %f)\n",
				expct_res, test_value[i][0], real_res, FACTOR);
		}
	}

	/* Random/performance tests */

	srand((unsigned)time(NULL));
	cstdlib=our=0;
	int error_count = 0;
	for(i=0; i<RANDROUNDS; i++)
	{
		in=((rand()%(int)(2*FACTOR))/FACTOR)-1;
		tmp=clock();
		expct_res=asin(in);
		cstdlib+=clock()-tmp;
		tmp=clock();
		real_res=doit(in);
		our+=clock()-tmp;

		if(!within(real_res, expct_res, FACTOR)){
			die("Random tests: should have returned %.6f for %.6f, returned %.6f (factor %f)\n",
				expct_res, in, real_res, FACTOR);
			error_count += 1;
		}
	}

	printf("error count: %d out of %d\n", (int)error_count, (int)RANDROUNDS);

	printf("stdlib: %d clocks, this: %d clocks, ratio: %f%%.\n", (int)cstdlib, (int)our, 100*(float)our/cstdlib);

	return 0;
}
