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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

public class ReadVoltageCommand extends ObdCommand {

    // Equivalent ratio (V)
    private double voltage = 0.00;

    public ReadVoltageCommand() {
		super("ATRV");
	}

	private static final Pattern p = Pattern.compile("([0-9]+\\.[0-9]+)V?", Pattern.CASE_INSENSITIVE);
	
	@Override
    protected void fillBuffer() {
		final Matcher m = p.matcher(rawData);
		if (m.matches()) {
			buffer.clear();
			rawData = m.group(1);
		} else {
			super.fillBuffer();
			rawData = "";
		}
    }
    
    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        voltage = (rawData.length() > 0) ? Double.valueOf(rawData) : 0;
    }

	
    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return (rawData.length() > 0) ? String.format("%.1f%s", voltage, getResultUnit()) : "";
    }

    /** {@inheritDoc} */
    @Override
    public String getResultUnit() {
        return "V";
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return (rawData.length() > 0) ? String.valueOf(voltage) : "";
    }

    /**
     * <p>Getter for the field <code>voltage</code>.</p>
     *
     * @return a double.
     */
    public double getVoltage() {
        return voltage;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.READ_VOLTAGE.getValue();
    }


}
