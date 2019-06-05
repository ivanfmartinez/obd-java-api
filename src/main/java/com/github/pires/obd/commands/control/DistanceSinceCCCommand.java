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
package com.github.pires.obd.commands.control;

import com.github.pires.obd.commands.DistanceCommand;
import com.github.pires.obd.commands.SystemOfUnits;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Distance traveled since codes cleared-up.
 *
 */
public class DistanceSinceCCCommand extends DistanceCommand implements SystemOfUnits {

	/**
	 * Default ctor.
	 */
	public DistanceSinceCCCommand() {
		super("01 31");
	}

	/**
	 * Copy ctor.
	 *
	 * @param other a
	 *              {@link com.github.pires.obd.commands.control.DistanceSinceCCCommand}
	 *              object.
	 */
	public DistanceSinceCCCommand(DistanceSinceCCCommand other) {
		super(other);
	}

	/** {@inheritDoc} */
	@Override
	protected void performCalculations() {
		// ignore first two bytes [01 31] of the response
		km = buffer.get(2) * 256 + buffer.get(3);
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return AvailableCommandNames.DISTANCE_TRAVELED_AFTER_CODES_CLEARED.getValue();
	}

}
