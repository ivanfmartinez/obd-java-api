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

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.pires.obd.commands.control.EcuNameCommand;

/**
 * Tests for EcuNameCommand class
 * @author jakub
 *
 */
public class EcuNameCommandTest {
	
	private EcuNameCommand command;
    private InputStream mockIn;

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new EcuNameCommand();
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
    }

    /**
     * Clear resources.
     */
    @AfterClass
    public void tearDown() {
        command = null;
        mockIn = null;
    }
    
    /**
     * Test Ecu name ISO9141-2, KWP2000 Fast and KWP2000 5Kbps (ISO15031) format
     *
     * @throws IOException
     */
    @Test
    public void ecuName() throws IOException {
    	
    	byte[] v = new byte[]{
                '4', '9', ' ', '0', 'A', ' ', '0', '0', ' ', '4', '5', ' ', '0', '0', ' ', '4', '3', ' ', '0', '0', '\n',
                '5', '5', ' ', '0', '0', ' ', '2', '0', ' ', '0', '0', ' ', '5', '3', ' ', '0', '0', ' ', '6', '9', '\n',
                '0', '0', ' ', '6', 'D', ' ', '0', '0', ' ', '7', '5', ' ', '0', '0', ' ', '6', 'C', ' ', '0', '0', '\n',
                '6', '1', ' ', '0', '0', ' ', '7', '4', ' ', '0', '0', ' ', '6', 'F', ' ', '0', '0', ' ', '7', '2', '>'
        };
        for (byte b : v) {
            expectLastCall().andReturn(b);
        }

        replayAll();
        String res = "ECU Simulator";

        // call the method to test
        command.readResult(mockIn);

        assertEquals(command.getFormattedResult(), res);

        verifyAll();
    }
    
    /**
     * Test Ecu name can format
     *
     * @throws IOException
     */
    @Test
    public void ecuNameCan() throws IOException {
    	
    	//0170:490A0145434D1:002D456E67696E2:65436F6E74726F3:6C000000000000
    	
    	byte[] v = new byte[]{
    			'0', '1', '7', '0', ':',
                '4', '9', '0', 'A', '0', '1', '4', '5', '4', '3', '4', 'D', '1', ':',
                '0', '0', '2', 'D', '4', '5', '6', 'E', '6', '7', '6', '9', '6', 'E', '2', ':',
                '6', '5', '4', '3', '6', 'F', '6', 'E', '7', '4', '7', '2', '6', 'F', '3', ':',
                '6', 'C', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '>'
        };
        for (byte b : v) {
            expectLastCall().andReturn(b);
        }

        replayAll();
        String res = "ECM-EngineControl";

        // call the method to test
        command.readResult(mockIn);

        assertEquals(command.getFormattedResult(), res);

        verifyAll();
    }

}
