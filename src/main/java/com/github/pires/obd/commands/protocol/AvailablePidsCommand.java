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
package com.github.pires.obd.commands.protocol;

import java.util.Set;

import com.github.pires.obd.commands.PersistentCommand;

/**
 * Retrieve available PIDs ranging from 21 to 40.
 *
 */
public abstract class AvailablePidsCommand extends PersistentCommand {

	private int start;
	
    /**
     * Default ctor.
     *
     * @param command a {@link java.lang.String} object.
     */
    public AvailablePidsCommand(String command) {
        super(command);
        start = Integer.valueOf(getCleanCommand().substring(2,4),16)+1;
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link com.github.pires.obd.commands.protocol.AvailablePidsCommand} object.
     */
    public AvailablePidsCommand(AvailablePidsCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {

    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return getCalculatedResult();
    }
    
    public boolean updatePids(Set<Integer> pids) {
		// As vezes vem pontos no inicio...
		String r = rawData.replaceAll("[.]", "");
		long l = 0;
		boolean valid = false;
		// Chevy Bolt returns many values , consider all of them as valid
		// Combine all bits set
		// 410080000001 410080000001 410080080013 410000000001 410080000001 410080000001
		// 410080080013 410080000001 410080000001 410080000001 410000000001 410080000001
		final String expectedStart = getExpectedResponsePrefix();
		while ((r.length() >= 12) && r.startsWith(expectedStart)) {
			valid  = true;
			l |= Long.valueOf(r.substring(4, 12), 16);
			r = r.substring(12);
		}
		if (valid) {
			// https://en.wikipedia.org/wiki/OBD-II_PIDs#Service_01_PID_00
			final String bin = Long.toBinaryString(l + 0x100000000l).substring(1);
			int pid = start;
			for (int idx = 0; idx < 32; idx++) {
				if (bin.charAt(idx) == '0') {
					pids.remove(pid);
				} else {
					pids.add(pid);
				}
				pid++;
			}
			return true;
		} else {
			return false;
		}
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        //First 4 characters are a copy of the command code, don't return those
        return String.valueOf(rawData).substring(4);
    }
}
