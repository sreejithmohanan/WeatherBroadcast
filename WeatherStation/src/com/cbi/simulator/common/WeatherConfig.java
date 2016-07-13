package com.cbi.simulator.common;

public class WeatherConfig {

		
	private String stationId;
	private String stationName;
	private int updateFrequency;
	private float temperature;
	private float pressure;
	private float humidity;
	private float latitude;
	private float longitude;
	private float altitude;
	private float changeInTemp;
	private float changeInPressure;
	private float changeInHumidity;

	public float getChangeInTemp() {
		return changeInTemp;
	}

	public void setChangeInTemp(float changeInTemp) {
		this.changeInTemp = changeInTemp;
	}

	public float getChangeInPressure() {
		return changeInPressure;
	}

	public void setChangeInPressure(float changeInPressure) {
		this.changeInPressure = changeInPressure;
	}

	public float getChangeInHumidity() {
		return changeInHumidity;
	}

	public void setChangeInHumidity(float changeInHumidity) {
		this.changeInHumidity = changeInHumidity;
	}

	/**
	 * @return the stationId
	 */
	public String getStationId() {
		return stationId;
	}

	/**
	 * @return the pressure
	 */
	public float getPressure() {
		return pressure;
	}

	/**
	 * @param pressure the pressure to set
	 */
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	/**
	 * @return the humidity
	 */
	public float getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	/**
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the altitude
	 */
	public float getAltitude() {
		return altitude;
	}

	/**
	 * @param altitude the altitude to set
	 */
	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	/**
	 * @param stationId
	 *            the stationId to set
	 */
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	/**
	 * @return the stationName
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * @param stationName
	 *            the stationName to set
	 */
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	/**
	 * @return the updateFrequency
	 */
	public int getUpdateFrequency() {
		return updateFrequency;
	}

	/**
	 * @param updateFrequency
	 *            the updateFrequency to set
	 */
	public void setUpdateFrequency(int updateFrequency) {
		this.updateFrequency = updateFrequency;
	}

	/**
	 * @return the temperature
	 */
	public float getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

}
