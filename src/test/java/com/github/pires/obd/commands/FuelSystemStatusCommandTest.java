/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.pires.obd.commands;

import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.pires.obd.commands.control.FuelSystemStatusCommand;

/**
 * Tests for FuelSystemStatusCommand class
 * @author jakubpycha
 *
 */
@PrepareForTest(InputStream.class)
public class FuelSystemStatusCommandTest {

	private FuelSystemStatusCommand command;
    private InputStream mockIn;
    
    private enum SystemType {
    	FIRST, SECOND, BOTH;
    }

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new FuelSystemStatusCommand();
    }
    
    @Test
    public void testFirstSystemCode1() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        prepareResponse(SystemType.FIRST, "01");

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getFormattedResult(), "System 1: Open loop due to insufficient engine temperature");

        verifyAll();
    }
    
    @Test
    public void testFirstSystemCode2() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        prepareResponse(SystemType.FIRST, "02");

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getFormattedResult(), "System 1: Closed loop, using oxygen sensor feedback to determine fuel mix");

        verifyAll();
    }
    
    @Test
    public void testFirstSystemCode4() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        prepareResponse(SystemType.FIRST, "04");

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getFormattedResult(), "System 1: Open loop due to engine load OR fuel cut due to deceleration");

        verifyAll();
    }
    
    @Test
    public void testFirstSystemCode8() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        prepareResponse(SystemType.FIRST, "08");

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getFormattedResult(), "System 1: Open loop due to system failure");

        verifyAll();
    }
    
    @Test
    public void testFirstSystemCode16() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        prepareResponse(SystemType.FIRST, "16");

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getFormattedResult(), "System 1: Closed loop, using at least one oxygen sensor but there is a fault in the feedback system");

        verifyAll();
    }
    
    @Test
    public void testSecondSystemCode16() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        prepareResponse(SystemType.SECOND, "16");

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getFormattedResult(), "System 1: \nSystem 2: Closed loop, using at least one oxygen sensor but there is a fault in the feedback system");

        verifyAll();
    }
    
    @Test
    public void testBothSystemCode16() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        prepareResponse(SystemType.BOTH, "16");

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getFormattedResult(), "System 1: Closed loop, using at least one oxygen sensor but there is a fault in the feedback system\nSystem 2: Closed loop, using at least one oxygen sensor but there is a fault in the feedback system");

        verifyAll();
    }

    
    private void prepareResponse(SystemType type, String responseNum) {
    	expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '3');
        expectLastCall().andReturn((byte) ' ');
        
        if(type.equals(SystemType.FIRST) || type.equals(SystemType.BOTH)) {
            expectLastCall().andReturn((byte) responseNum.charAt(0));
            expectLastCall().andReturn((byte) responseNum.charAt(1));
        } else {
        	expectLastCall().andReturn((byte) '0');
            expectLastCall().andReturn((byte) '0');
        }
        expectLastCall().andReturn((byte) ' ');
        if(type.equals(SystemType.SECOND) || type.equals(SystemType.BOTH)) {
        	expectLastCall().andReturn((byte) responseNum.charAt(0));
            expectLastCall().andReturn((byte) responseNum.charAt(1));
        } else {
        	expectLastCall().andReturn((byte) '0');
            expectLastCall().andReturn((byte) '0');
        }

        expectLastCall().andReturn((byte) '>');
    }
    
    /**
     * Clear resources.
     */
    @AfterClass
    public void tearDown() {
        command = null;
        mockIn = null;
    }
}
