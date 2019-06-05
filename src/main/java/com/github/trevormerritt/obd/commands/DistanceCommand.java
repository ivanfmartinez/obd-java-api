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

public abstract class DistanceCommand extends ObdCommand implements SystemOfUnits {

	protected int km = 0;

	public DistanceCommand(String command) {
		super(command);
	}

	public DistanceCommand(ObdCommand other) {
		super(other);
	}

	@Override
	public float getImperialUnit() {
		return km * 0.621371192F;
	}

	@Override
	public String getFormattedResult() {
		return useImperialUnits ? String.format("%.2f%s", getImperialUnit(), getResultUnit())
				: String.format("%d%s", km, getResultUnit());
	}

	@Override
	public String getCalculatedResult() {
		return useImperialUnits ? String.valueOf(getImperialUnit()) : String.valueOf(km);
	}

	/** {@inheritDoc} */
	@Override
	public String getResultUnit() {
		return useImperialUnits ? "m" : "km";
	}

	/**
	 * <p>
	 * Getter for the field <code>km</code>.
	 * </p>
	 *
	 * @return a int.
	 */
	public int getKm() {
		return km;
	}

}
