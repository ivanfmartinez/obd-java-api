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

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

public class SecondaryIntakeStatusCommand extends ObdCommand {

	private String status;

	public SecondaryIntakeStatusCommand() {
		super("01 12");
	}

	@Override
	protected void performCalculations() {

		// ignore first two bytes
		String hexCode = Integer.toHexString(this.buffer.get(2));
		switch (hexCode) {
		case "1":
			status = "Upstream";
			break;
		case "2":
			status = "Downstream of catalytic converter";
			break;
		case "4":
			status = "From the outside atmosphere or off";
			break;
		case "8":
			status = "Pump commanded on for diagnostics";
			break;
		default:
			status = "";
			break;
		}

	}

	@Override
	public String getFormattedResult() {
		return status;
	}

	@Override
	public String getCalculatedResult() {
		return status;
	}

	@Override
	public String getName() {
		return AvailableCommandNames.SECONDARY_INTAKE_STATUS.getValue();
	}

	public String getStatus() {
		return status;
	}

}
