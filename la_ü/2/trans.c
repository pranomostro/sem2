#include <stdlib.h>
#include <stdio.h>

float pm[6][6]=
{
	{ 0, 0.25, 0.25, 0, 0.25, 0.25 },
	{ 0, 0, 0, 0.5, 0, 0.5 },
	{ 0, 0.5, 0, 0, 0.5, 0 },
	{ 0.5, 0, 0, 0, 0.5, 0 },
	{ 0, 0.3333333, 0.333333, 0.33333, 0, 0},
	{ 0, 1, 0, 0, 0, 0 }
};

void printmatrix(float f[6][6])
{
	int i, j;

	for(i=0; i<6; i++)
	{
		for(j=0; j<6; j++)
			printf("%f ", f[i][j]);

		putchar('\n');
	}
	putchar('\n');
}

int main(int argc, char** argv)
{
	int i, j, k, c;
	float f[6][6], g[6][6];

	for(i=0; i<6; i++)
		for(j=0; j<6; j++)
			f[i][j]=pm[i][j];

	for(c=0; c<1024; c++)
	{
		printmatrix(f);

		/* g=pm*f */
		/* f=g */

		for(i=0; i<6; i++)
			for(j=0; j<6; j++)
			{
				g[i][j]=0;
				for(k=0; k<6; k++)
					g[i][j]+=pm[i][k]*f[k][j];
			}

		for(i=0; i<6; i++)
			for(j=0; j<6; j++)
				f[i][j]=g[i][j];
	}

	printmatrix(f);

	return 0;
}
