#include "read.h"
#include "digicam.h"
#include "ReadBmp_aux.h"


// Read a 8x8 block  out of the image
void readblock(unsigned char ScanBuffer[IMG_HEIGHT_MDU*8][IMG_WIDTH_MDU*8],
               int block[64])
{
        int i, j;
	int x, y;

        static unsigned int blockNr = 0;

	x = (blockNr % MDU(IMG_WIDTH)) << 3;
	y = (blockNr / MDU(IMG_WIDTH)) << 3;
	
	for (i=0; i<8; i++) {
	  for (j=0; j<8; j++) {
	    block[i*8+j] = ScanBuffer[y+i][x+j];
	  } 
	} 

        blockNr++;
} 

