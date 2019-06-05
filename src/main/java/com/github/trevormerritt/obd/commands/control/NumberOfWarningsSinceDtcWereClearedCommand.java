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

public class NumberOfWarningsSinceDtcWereClearedCommand extends ObdCommand {

	private int count = 0;
	
	public NumberOfWarningsSinceDtcWereClearedCommand() {
		super("01 30");
	}

	@Override
	protected void performCalculations() {
		
		this.count = this.buffer.get(2);
		
	}

	@Override
	public String getFormattedResult() {
		return String.valueOf(this.count);
	}

	@Override
	public String getCalculatedResult() {
		return String.valueOf(this.count);
	}

	@Override
	public String getName() {
		return AvailableCommandNames.NUMBER_OF_WARNINGS_SINCE_DTC_WERE_CLEARED.getValue();
	}
	
	
}
