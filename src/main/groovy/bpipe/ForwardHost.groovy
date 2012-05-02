/*
* Copyright (c) 2012 MCRI, authors
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use,
* copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following
* conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
* THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package bpipe

import java.io.OutputStream;
import java.util.List;
import java.util.Timer;

class ForwardHost {
    
    static Timer forwardingTimer
    
    transient List<Forwarder> forwarders = []
    
    public void forward(String fileName, OutputStream s) {
    
        // Start the forwarding timer task if it is not already running
        synchronized(ForwardHost.class) {
            if(forwardingTimer == null) {
                forwardingTimer = new  Timer(true)
            }
        }
    
        Forwarder f = new Forwarder(new File(fileName), s)
        forwardingTimer.schedule(f, 0, 2000)
    
        this.forwarders << f
    }
}
