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
//        byte[] v = byteStringToByteArray("49 0A 00 45 00 43 00 55 00 20 00 53 00 69 00 6D 00 75 00 6C 00 61 00 74 00 6F 00 72");
//        for (byte b : v) {
//        	System.out.println(b);
//            expectLastCall().andReturn(b);
//        }
//
//        replayAll();
//        String res = "ECU Simulator";
//
//        // call the method to test
//        command.readResult(mockIn);
//
//        assertEquals(command.getFormattedResult(), res);

//        verifyAll();
    	
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

}
