package com.cbi.weatherprediction;

import com.cbi.simulator.bean.WeatherInfo;
import com.cbi.weatherprediction.common.Utils;
/**
 * Class responsible for predict the weather based on weather prediction algorithm.
 * @author 777849ehn
 *
 */
public class WeatherPredictor {

	// Humidity
	private static int MINUM_HUMIDITY_FOR_RAIN = 75;
	private static int MINUM_HUMIDITY_FOR_SNOW = 40;
	private static final String FIELD_SEPARATER = "|";

	private float avgTemp;
	private float avgPressure;
	private float avghumidity;
	private long updateCounter;
	private int previousWeatherType = 0;

	public String processWeatherForecast(WeatherInfo weatherInfo) {
		updateCounter++;

		avgTemp = ((updateCounter - 1) * avgTemp + weatherInfo.getTemperature())
				/ updateCounter;

		avgPressure = ((updateCounter - 1) * avgPressure + weatherInfo
				.getPressure()) / updateCounter;

		avghumidity = ((updateCounter - 1) * avghumidity + weatherInfo
				.getHumidity()) / updateCounter;

		return genarateWeatherForcat(weatherInfo);

	}
/**
 * Function format the weather prediction data.
 * @param weatherInfo
 * @return
 */
	private String genarateWeatherForcat(WeatherInfo weatherInfo) {
		String weatherForcast = "";

		int weatherType = appleyWeatherPredictionRule(weatherInfo);

		weatherForcast = weatherInfo.getStationName()
				+ FIELD_SEPARATER
				+ weatherInfo.getLatitude()
				+ ","
				+ weatherInfo.getLongitude()
				+ ","
				+ weatherInfo.getAltitude()
				+ FIELD_SEPARATER
				+ Utils.getTimeW3C(System.currentTimeMillis(),
						Utils.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SSZ)
				+ FIELD_SEPARATER + getWeatherType(weatherType)
				+ FIELD_SEPARATER + Utils.getDecimalFormat(avgTemp, 2)
				+ FIELD_SEPARATER + Utils.getDecimalFormat(avgPressure, 2)
				+ FIELD_SEPARATER + Utils.getDecimalFormat(avghumidity, 2)
				+ "\n";

		return weatherForcast;

	}

	private int appleyWeatherPredictionRule(WeatherInfo weatherInfo) {

		float changeInTemp = weatherInfo.getTemperature() - avgTemp;
		float changeInPressure = weatherInfo.getPressure() - avgPressure;
		float changeInHumidity = weatherInfo.getHumidity() - avghumidity;

		int currentWeather = WeatherType.UNKNOWN;
		if (avghumidity > MINUM_HUMIDITY_FOR_RAIN) {
			currentWeather = weatherPredictionOnHighHumidity(changeInTemp,
					changeInPressure, changeInHumidity, currentWeather);
		} else if (avghumidity < MINUM_HUMIDITY_FOR_RAIN
				&& avghumidity > MINUM_HUMIDITY_FOR_SNOW) {
			currentWeather = weatherPredictionOnMediumHumidity(changeInTemp,
					changeInPressure, changeInHumidity, currentWeather);
		} else {
			currentWeather = WeatherType.SUNNY;
		}
		return currentWeather;
	}

	private int weatherPredictionOnHighHumidity(float changeInTemp,
			float changeInPressure, float changeInHumidity, int currentWeather) {
		if (changeInPressure > 0 || changeInTemp > 0) {

			currentWeather = WeatherType.RAIN;
		} else if (changeInPressure <= 0 || changeInTemp <= 0) {
			if (previousWeatherType != WeatherType.UNKNOWN) {
				currentWeather = previousWeatherType;
			} else if (changeInHumidity <= 0) {
				currentWeather = WeatherType.SUNNY;
			} else {
				currentWeather = WeatherType.SNOW;
			}
		}
		return currentWeather;
	}

	private int weatherPredictionOnMediumHumidity(float changeInTemp,
			float changeInPressure, float changeInHumidity, int currentWeather) {
		if (changeInPressure > 0 || changeInTemp > 0) {

			currentWeather = WeatherType.SNOW;
		} else if (changeInPressure <= 0 || changeInTemp <= 0) {
			if (previousWeatherType != WeatherType.UNKNOWN) {
				currentWeather = previousWeatherType;
			} else if (changeInHumidity <= 0) {
				currentWeather = WeatherType.SUNNY;
			} else {
				currentWeather = WeatherType.RAIN;
			}
		}
		return currentWeather;
	}

	public interface WeatherType {
		int RAIN = 1;
		int SUNNY = 2;
		int SNOW = 3;
		int UNKNOWN = 0;
	}

	private String getWeatherType(int type) {
		switch (type) {
		case WeatherType.RAIN:
			return "RAIN";
		case WeatherType.SUNNY:
			return "SUNNY";
		case WeatherType.SNOW:
			return "SNOW";
		default:
			return "UNKNOWN";
		}
	}
}
