// Digital Camera Example
//
// Lab 3
// Group Members: 
//   Brian Compter, 001986259
//
//

#include <stdio.h>
#include <sim.sh>
#include "digicam.sh"

import "i_receiver";
import "i_sender";

behavior Write(i_receiver bytesFromJpeg, i_sender bytesToMonitor) 
{    
	// Some buffers
	unsigned char bytes[1];
	
	int byteCount = 0;

  	void main(void) 
  	{
		while(1)
		{
			// Receive
			bytesFromJpeg.receive(&bytes[0], sizeof(char));
			//printf("WRITE::Fwd a byte, %d.\n", byteCount++);
			
			// And forward 
			bytesToMonitor.send(&bytes[0], sizeof(char));
		}
    
  	}  // end void main void
  
};  // end behavior
