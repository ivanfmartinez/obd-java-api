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
package com.github.pires.obd.commands.pressure;

import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * 
 * <p>
 * RelativeFuelRailPressureCommand class
 * </p>
 * 
 * @author jakubpycha
 *
 */
public class RelativeFuelRailPressureCommand extends PressureCommand {

	public RelativeFuelRailPressureCommand() {
		super("01 22");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return AvailableCommandNames.RELATIVE_FUEL_RAIL_PRESSURE.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int preparePressureValue() {
		int a = this.buffer.get(2);
		int b = this.buffer.get(3);
		return (int) (0.079 * (256 * a + b));
	}

}
