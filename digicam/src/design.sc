// Digital Camera Example
//
// Lab 3
// Group Members: 
//   Brian Compter, 001986259
//

#include <stdio.h>
#include <sim.sh>
#include "digicam.sh"

import "read";
import "jpegencoder";
import "write";

import "c_queue";
import "c_handshake";
import "i_send";
import "i_sender";

behavior Design(unsigned char ScanBuffer[IMG_HEIGHT_MDU*8][IMG_WIDTH_MDU*8], 
				i_receive start, 
				i_sender bytesToMonitor) 
{    
  	// Queues
  	const unsigned long qSize = 512;
  	c_queue bytesOut(qSize);
  	c_queue bytesIn(qSize);

	Read read(ScanBuffer, start, bytesIn);
  	JpegEncoder jpegEncoder(bytesIn, bytesOut);
  	Write write(bytesOut, bytesToMonitor);

  	void main(void) 
  	{
    	par
    	{
      		read;
      		jpegEncoder;
      		write;
    	}
    
  	}  // end void main void
  
};  // end behavior
