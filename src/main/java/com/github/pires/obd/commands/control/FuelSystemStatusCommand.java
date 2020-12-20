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

public class FuelSystemStatusCommand extends ObdCommand {

	private String firstSystemStatus;
	private String secondSystemStatus;

	public FuelSystemStatusCommand() {
		super("01 03");
	}

	@Override
	protected void performCalculations() {
		firstSystemStatus = this.getStatusFromByte(this.buffer.get(2));
		secondSystemStatus = this.getStatusFromByte(this.buffer.get(3));
	}

	@Override
	public String getFormattedResult() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("System 1: ");
		sb.append(this.firstSystemStatus);
		if(!this.secondSystemStatus.isEmpty()) {
			sb.append("\nSystem 2: ");
			sb.append(this.secondSystemStatus);
		}

		return sb.toString();
	}

	@Override
	public String getCalculatedResult() {
		return this.getFormattedResult();
	}

	@Override
	public String getName() {
		return AvailableCommandNames.FUEL_SYSTEM_STATUS.getValue();
	}

	public String getFirstSystemStatus() {
		return firstSystemStatus;
	}

	public String getSecondSystemStatus() {
		return secondSystemStatus;
	}

	private String getStatusFromByte(Integer code) {
		if(code == null) {
			return "";
		}
		
		final String hexCode = Integer.toHexString(code);
		switch (hexCode) {
		case "16":
			return "Closed loop, using at least one oxygen sensor but there is a fault in the feedback system";
		case "8":
			return "Open loop due to system failure";
		case "4":
			return "Open loop due to engine load OR fuel cut due to deceleration";
		case "2":
			return "Closed loop, using oxygen sensor feedback to determine fuel mix";
		case "1":
			return "Open loop due to insufficient engine temperature";
		default:
			return "";
		}

	}

}
