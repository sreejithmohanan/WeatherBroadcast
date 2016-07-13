package com.cbi.simulator.bean;

import com.cbi.simulator.common.WeatherConfig;

public class WeatherInfo {

	private String stationId;
	private String StationName;
	private float temperature;
	private float pressure;
	private float humidity;
	private float latitude;
	private float longitude;
	private float altitude;

	public WeatherInfo() {
	}

	public WeatherInfo(WeatherConfig weatherConfig) {
		super();
		this.stationId = weatherConfig.getStationId();
		StationName = weatherConfig.getStationName();
		this.temperature = weatherConfig.getTemperature();
		this.pressure = weatherConfig.getPressure();
		this.humidity = weatherConfig.getHumidity();
		this.latitude = weatherConfig.getLatitude();
		this.longitude = weatherConfig.getLatitude();
		this.altitude = weatherConfig.getAltitude();
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	/**
	 * @return the stationId
	 */
	public String getStationId() {
		return stationId;
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
		return StationName;
	}

	/**
	 * @param stationName
	 *            the stationName to set
	 */
	public void setStationName(String stationName) {
		StationName = stationName;
	}

	/**
	 * @return the pressure
	 */
	public float getPressure() {
		return pressure;
	}

	/**
	 * @param pressure
	 *            the pressure to set
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
	 * @param humidity
	 *            the humidity to set
	 */
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	/**
	 * @return the temperature
	 */
	public float getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature
	 *            the temperature to set
	 */
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

}
