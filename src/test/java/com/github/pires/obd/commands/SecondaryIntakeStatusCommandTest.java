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

import java.io.IOException;
import java.io.InputStream;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.pires.obd.commands.control.SecondaryIntakeStatusCommand;
import static org.powermock.api.easymock.PowerMock.*;
import static org.testng.Assert.assertEquals;

/**
 * Secondary intake circuit status test
 */
@PrepareForTest(InputStream.class)
public class SecondaryIntakeStatusCommandTest {

	private SecondaryIntakeStatusCommand command;
    private InputStream mockIn;

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new SecondaryIntakeStatusCommand();
    }
    
    
    @Test
    public void testStatus1() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getCalculatedResult(), "Upstream");

        verifyAll();
    }
    
    @Test
    public void testStatus2() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getCalculatedResult(), "Downstream of catalytic converter");

        verifyAll();
    }
    
    @Test
    public void testStatus4() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getCalculatedResult(), "From the outside atmosphere or off");

        verifyAll();
    }
    
    @Test
    public void testStatus8() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '8');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getCalculatedResult(), "Pump commanded on for diagnostics");

        verifyAll();
    }
    
    @Test
    public void testInvalidResponse() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '6');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getCalculatedResult(), "");

        verifyAll();
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
