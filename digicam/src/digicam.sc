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

import "stimulus";
import "design";
import "monitor";

import "c_queue";
import "c_handshake";
import "c_double_handshake";

behavior Main 
{  
	// Scan buffer from our file reader
  	unsigned char ScanBuffer[IMG_HEIGHT_MDU*8][IMG_WIDTH_MDU*8];

  	// Trigger Read to start
  c_handshake start;

	// Handshake to the monitor to deliver the finished data
  	c_double_handshake bytesToMonitor;

  	// Timing variables
  	unsigned long long startTime;

  	Stimulus stimulus(ScanBuffer, start, startTime);
	Design design(ScanBuffer, start, bytesToMonitor);
  	Monitor monitor(bytesToMonitor, startTime);

  // Main application entry point
  int main(void) 
  {
    par
    {
      stimulus;
      	design;
      monitor;
    }

    return 0;
  }  // end int main void
};  // end behavior
