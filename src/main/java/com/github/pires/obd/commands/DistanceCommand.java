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
