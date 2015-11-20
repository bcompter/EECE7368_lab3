//
// Jpegencoder
//   -- encodes data to jpeg

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sim.sh>
#include "digicam.sh"

import "dct";
import "quantize";
import "huff";

import "i_receiver";
import "i_sender";

behavior JpegEncoder(i_receiver bytesIn, i_sender bytesOut) {

  	int read2dct[64];
  	int dct2quant[64];
  	int quant2huff[64];
  
  	DCT dct(read2dct, dct2quant);
  	Quantize quantize(dct2quant, quant2huff);
  	Huff  huff(quant2huff, bytesOut);
  
  	void main(void) 
  	{
    	int iter;
    	int blockIndex;
    
    	while (1) 
    	{
      		for (iter = 0; iter < IMG_BLOCKS; iter++) 
      		{
      			// Wait for a full block to be received from bytesIn
      			blockIndex = 0;
      			while (blockIndex < 64)
      			{
      				bytesIn.receive(&read2dct[blockIndex], sizeof(char));
      				//printf("JPEGENDCODER::Got a Byte! %d\n", blockIndex);
      				blockIndex++;
      			}
      			//printf("JPEGENCODER:: Rceived block %d.\n", iter);
      
      			// Process the block
        		dct;
        		quantize;
        		huff;
      		}  // end for
      
    	}  // end while
    
  	}  // end main
  
}; // end behavior
