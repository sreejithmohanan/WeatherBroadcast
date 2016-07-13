package com.cbi.simulator;

import java.util.concurrent.CopyOnWriteArrayList;

import com.cbi.simulator.bean.WeatherInfo;
import com.cbi.simulator.common.WeatherConfig;

/**
 * Weather data simulation class.Class generate weather data for a weather
 * station.
 * 
 * @author Sreejith
 *
 */
public class WeatherStation implements Runnable {

	private final CopyOnWriteArrayList<IweatherbroadCast> weatherbroadCast;
	private final int frequency;
	private int stationStatus;
	private WeatherInfo weatherInfo;
	private WeatherConfig weatherStationConfiguration;

	public WeatherStation(WeatherConfig weatherConfig) {
		weatherbroadCast = new CopyOnWriteArrayList<IweatherbroadCast>();
		weatherInfo = new WeatherInfo(weatherConfig);
		this.frequency = weatherConfig.getUpdateFrequency();
		this.weatherStationConfiguration = weatherConfig;

	}

	/**
	 * Function add call back listener for weather update. Weather station will
	 * give back weather update to registered listener.
	 * 
	 * @param iweatherbroadCast
	 */
	public void addWeatherBroadCast(IweatherbroadCast iweatherbroadCast) {
		weatherbroadCast.add(iweatherbroadCast);
	}

	public int getStationStatus() {
		return stationStatus;
	}

	public void setStationStatus(int stationStatus) {
		this.stationStatus = stationStatus;
	}

	/**
	 * Remove weather broad cast update.
	 * 
	 * @param iweatherbroadCast
	 */

	public void removeWeatherBroadCast(IweatherbroadCast iweatherbroadCast) {
		if (weatherbroadCast.contains(iweatherbroadCast)) {
			weatherbroadCast.remove(iweatherbroadCast);
		}
	}

	/**
	 * Function will start weather station if it is not started.
	 */
	public void startWeatherStation() {
		if (stationStatus == weatherStationMode.STOPED) {
			stationStatus = weatherStationMode.RUNNING;
		}
	}

	/**
	 * Function will stop the current station
	 */
	public void stopWeatherStation() {
		stationStatus = weatherStationMode.STOPED;

	}

	@Override
	public void run() {

		while (stationStatus == weatherStationMode.RUNNING) {

			WeatherInfo weatherInfo = getWeatherInfo();
			for (IweatherbroadCast iweatherbroadCast : weatherbroadCast) {
				iweatherbroadCast.onWeatherBroadCast(weatherInfo);
			}

			try {
				Thread.sleep(frequency * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Function generate dummy weather data.
	 * 
	 * @return WeatherInfo
	 */

	private WeatherInfo getWeatherInfo() {

		float temperature = weatherInfo.getTemperature()
				+ weatherStationConfiguration.getChangeInTemp();
		float pressure = weatherInfo.getPressure()
				+ weatherStationConfiguration.getChangeInPressure();
		float humidity = weatherInfo.getHumidity()
				+ weatherStationConfiguration.getChangeInHumidity();

		weatherInfo.setTemperature(temperature);
		weatherInfo.setPressure(pressure);
		weatherInfo.setHumidity(humidity);

		return weatherInfo;
	}

	public interface weatherStationMode {
		int RUNNING = 1;
		int STOPED = 0;
	}
}
