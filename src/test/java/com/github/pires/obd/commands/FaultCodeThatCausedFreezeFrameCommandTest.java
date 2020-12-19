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

import com.github.pires.obd.commands.control.FaultCodeThatCausedFreezeFrameCommand;

/**
 * Tests for FaultCodeThatCausedFreezeFrameCommand class
 * @author jakub
 *
 */
@PrepareForTest(InputStream.class)
public class FaultCodeThatCausedFreezeFrameCommandTest {

	private FaultCodeThatCausedFreezeFrameCommand command;
    private InputStream mockIn;

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new FaultCodeThatCausedFreezeFrameCommand();
    }
    
    /**
     * Test for empty Freeze DTC
     * @throws IOException 
     */
    @Test
    public void testEmpty() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getFormattedResult(), "");
        assertEquals(command.getObdCode(), "0000");

        verifyAll();
    }
    
    
    /**
     * Test for P0001 Freeze DTC
     * @throws IOException 
     */
    @Test
    public void testP0001() throws IOException {
    	// mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getFormattedResult(), "P0001");
        assertEquals(command.getObdCode(), "0001");

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