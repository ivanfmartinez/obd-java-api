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

import com.github.pires.obd.commands.control.ObdComputerSpecificationCommand;
import com.github.pires.obd.consts.ObdComputerConsts;

/**
 * Tests for ObdComputerSpecificationCommand class.
 */
@PrepareForTest(InputStream.class)
public class ObdComputerSpecificationCommandTest {

	private ObdComputerSpecificationCommand command;
	private InputStream mockIn;

	/**
	 * @throws Exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		command = new ObdComputerSpecificationCommand();
	}

	@Test
	public void testUndefinedResponse() throws IOException {
		// mock InputStream read
		mockIn = createMock(InputStream.class);
		mockIn.read();

		expectLastCall().andReturn((byte) '4');
		expectLastCall().andReturn((byte) '1');
		expectLastCall().andReturn((byte) ' ');
		expectLastCall().andReturn((byte) '1');
		expectLastCall().andReturn((byte) 'C');
		expectLastCall().andReturn((byte) ' ');
		expectLastCall().andReturn((byte) '0');
		expectLastCall().andReturn((byte) '0');
		expectLastCall().andReturn((byte) '>');

		replayAll();

		// call the method to test
		command.readResult(mockIn);

		assertEquals(command.getCalculatedResult(), "Other");

		verifyAll();
	}

	@Test
	public void testSpecMap() throws IOException {

		for (final String key : ObdComputerConsts.SPEC_MAP.keySet()) {
			// mock InputStream read
			mockIn = createMock(InputStream.class);
			mockIn.read();

			String tempKey = key;
			if (key.length() == 1) {
				tempKey = "0" + key;
			}

			expectLastCall().andReturn((byte) '4');
			expectLastCall().andReturn((byte) '1');
			expectLastCall().andReturn((byte) ' ');
			expectLastCall().andReturn((byte) '1');
			expectLastCall().andReturn((byte) 'C');
			expectLastCall().andReturn((byte) ' ');
			expectLastCall().andReturn((byte) tempKey.charAt(0));
			expectLastCall().andReturn((byte) tempKey.charAt(1));
			expectLastCall().andReturn((byte) '>');

			replayAll();

			// call the method to test
			command.readResult(mockIn);

			assertEquals(command.getCalculatedResult(), ObdComputerConsts.SPEC_MAP.get(key));

			verifyAll();
		}

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
