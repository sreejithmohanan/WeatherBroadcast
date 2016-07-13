package com.cbi.weatherprediction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.cbi.simulator.IweatherbroadCast;
import com.cbi.simulator.WeatherStationManager;
import com.cbi.simulator.bean.WeatherInfo;

public class WeatherMonitor {

	private static Map<String, WeatherPredictor> weatherPredictorMaper = new HashMap<String, WeatherPredictor>();

	public static void main(String[] args) {

		WeatherStationManager stationManager = WeatherStationManager
				.getInstance();
		mapWeatherStationWithPredictor(stationManager);
		stationManager.registerForAllWeatherUpdate(iweatherBroadCast);
		

	}

	private static IweatherbroadCast iweatherBroadCast = new IweatherbroadCast() {

		@Override
		public void onWeatherBroadCast(WeatherInfo weatherInfo) {

			System.out.println(weatherPredictorMaper.get(
					weatherInfo.getStationId()).processWeatherForecast(
					weatherInfo));
		}
	};

	private static void mapWeatherStationWithPredictor(
			WeatherStationManager stationManager) {
		Set<String> weatherStationIds = stationManager.getWeatherStationMaper()
				.keySet();
		for (String id : weatherStationIds) {
			weatherPredictorMaper.put(id, new WeatherPredictor());
		}
	}

}
