/******************************************************************************

Copyright (c) 2010, Cormac Flanagan (University of California, Santa Cruz)
                    and Stephen Freund (Williams College) 

All rights reserved.  

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the following
      disclaimer in the documentation and/or other materials provided
      with the distribution.

 * Neither the names of the University of California, Santa Cruz
      and Williams College nor the names of its contributors may be
      used to endorse or promote products derived from this software
      without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 ******************************************************************************/

package test;

public class Test implements Runnable {

	private Counter counter;

	private Test(Counter c) {
		this.counter = c;
	}

	final static int ITERS = 16;

	public void run() {
		for (int i = 0; i < ITERS; i++) {
			// synchronized (this.counter) {
			this.counter.inc();
			// }
		}
	}

	public static void main(String args[]) throws Exception {
		Counter c = new Counter();
		Thread[] tests = new Thread[ITERS];
		for (int i = 0; i < ITERS; i++) {
			tests[i] = new Thread(new Test(c));
			tests[i].start();
		}
		for (int i = 0; i < ITERS; i++) {
			tests[i].join();
		}
		System.out.println("Is it " + (ITERS * ITERS) + "? " + c.get());
	}
}