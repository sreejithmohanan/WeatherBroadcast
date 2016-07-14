package com.cbi.weatherprediction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.cbi.simulator.IweatherbroadCast;
import com.cbi.simulator.WeatherStationManager;
import com.cbi.simulator.bean.WeatherInfo;

/**
 * Main class to predict the weather based on the value get from weather
 * station.
 * 
 * @author Sreejith
 *
 */
public class WeatherMonitor {

	private static Map<String, WeatherPredictor> weatherPredictorMaper = new HashMap<String, WeatherPredictor>();

	public static void main(String[] args) {

		WeatherStationManager stationManager = WeatherStationManager
				.getInstance();
		mapWeatherStationWithPredictor(stationManager);
		stationManager.registerForAllWeatherUpdate(iweatherBroadCast);

	}

	/**
	 * Broad cast listener for weather update.
	 */

	private static IweatherbroadCast iweatherBroadCast = new IweatherbroadCast() {

		@Override
		public void onWeatherBroadCast(WeatherInfo weatherInfo) {

			System.out.println(weatherPredictorMaper.get(
					weatherInfo.getStationId()).processWeatherForecast(
					weatherInfo));
		}
	};

	/**
	 * Function register weather predictor with each weather station so that it
	 * can process the weather update independently
	 * 
	 * @param stationManager
	 */
	private static void mapWeatherStationWithPredictor(
			WeatherStationManager stationManager) {
		Set<String> weatherStationIds = stationManager.getWeatherStationMaper()
				.keySet();
		for (String id : weatherStationIds) {
			weatherPredictorMaper.put(id, new WeatherPredictor());
		}
	}

}
