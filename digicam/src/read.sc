//
// Read behavior 
//

#include <stdio.h>
#include "digicam.sh"

import "c_queue";
import "c_handshake";
import "i_receive";
import "i_sender";

behavior Read(in unsigned char ScanBuffer[IMG_HEIGHT_MDU*8][IMG_WIDTH_MDU*8],
	 	i_receive start,
        i_sender data)
{
	// Local data
  unsigned int blockNr = 0;
    int i, j;
    int x, y;
    
  	// from function readblock
  	void main(void)
  	{
    	while (1)
    	{
      		// Wait for the trigger to start
      		start.receive();
      		printf("READ::Got a shutter!\n");
			
			blockNr = 0;
			while (blockNr < IMG_BLOCKS)
			{
    
    x = (blockNr % MDU(IMG_WIDTH)) << 3;
    y = (blockNr / MDU(IMG_WIDTH)) << 3;
    
      			for (i=0; i<8; i++) 
      			{
        			for (j=0; j<8; j++) 
        			{
          				data.send(&ScanBuffer[y+i][x+j], sizeof(char));
      } 
    } 
    
    blockNr++;
			}  // end while(blockNr < IMG_BLOCKS)
  		
    	}  // end while(1)
    
  	}  // end void main void

}; // end behavior
