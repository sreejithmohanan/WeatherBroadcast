package com.cbi.simulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cbi.simulator.common.WeatherConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WeatherStationManager {
	private Map<String, WeatherStation> weatherStationMaper;
	private ExecutorService executorService;
	private static WeatherStationManager stationManager;

	public Map<String, WeatherStation> getWeatherStationMaper() {
		return weatherStationMaper;
	}

	public void setWeatherStationMaper(
			Map<String, WeatherStation> weatherStationMaper) {
		this.weatherStationMaper = weatherStationMaper;
	}

	private WeatherStationManager() {
		weatherStationMaper = new HashMap<String, WeatherStation>();
		executorService = Executors.newFixedThreadPool(10);
		loadWeatherStations();
	}

	public static WeatherStationManager getInstance() {
		if (stationManager == null) {
			stationManager = new WeatherStationManager();
		}
		return stationManager;
	}

	public void registerForWeatherUpdate(String StationId,
			IweatherbroadCast weatherbroadCast) {
		WeatherStation weatherStation = weatherStationMaper.get(StationId);
		if (weatherStation != null) {
			weatherStation.addWeatherBroadCast(weatherbroadCast);
			if (weatherStation.getStationStatus() != WeatherStation.weatherStationMode.RUNNING) {
				startWeatherStations(weatherStation);
			}
		}
	}

	public void registerForAllWeatherUpdate(IweatherbroadCast weatherbroadCast) {
		
		Collection<WeatherStation> weatherStations = weatherStationMaper
				.values();

		for (WeatherStation weatherStation : weatherStations) {

			if (weatherStation != null) {
				weatherStation.addWeatherBroadCast(weatherbroadCast);
				if (weatherStation.getStationStatus() != WeatherStation.weatherStationMode.RUNNING) {
					startWeatherStations(weatherStation);
				}
			}
		}
	}

	public void unRegisterForWeatherUpdate(String StationId,
			IweatherbroadCast weatherbroadCast) {
		WeatherStation weatherStation = weatherStationMaper.get(StationId);
		if (weatherStation != null) {
			weatherStation.removeWeatherBroadCast(weatherbroadCast);
		}
	}

	public void loadWeatherStations() {
		Gson gson = new Gson();
		BufferedReader br;
		try {
			Type listType = new TypeToken<ArrayList<WeatherConfig>>() {
			}.getType();
			br = new BufferedReader(new FileReader("config/config.json"));
			List<WeatherConfig> weatherConfigList = gson.fromJson(br, listType);

			for (WeatherConfig weatherConfig : weatherConfigList) {
				weatherStationMaper.put(weatherConfig.getStationId(),
						new WeatherStation(weatherConfig));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void startWeatherStations() {
		synchronized (weatherStationMaper) {

			Collection<WeatherStation> weatherStations = weatherStationMaper
					.values();
			for (WeatherStation weatherStation : weatherStations) {
				if (weatherStation.getStationStatus() != WeatherStation.weatherStationMode.RUNNING) {
					weatherStation.startWeatherStation();
					executorService.execute(weatherStation);
				}
			}
		}
	}

	private void startWeatherStations(WeatherStation weatherStation) {
		synchronized (weatherStationMaper) {
			weatherStation.startWeatherStation();
			executorService.execute(weatherStation);
		}
	}

	public void stopWeatherstation(String StationId,
			IweatherbroadCast weatherbroadCast) {
		WeatherStation weatherStation = weatherStationMaper.get(StationId);
		if (weatherStation != null) {
			if (weatherStation.getStationStatus() == WeatherStation.weatherStationMode.RUNNING) {
				weatherStation.stopWeatherStation();
			}
		}
	}

	public void shouldDownWeatherStation() {
		if (!executorService.isTerminated()) {

			Collection<WeatherStation> weatherStations = weatherStationMaper
					.values();
			for (WeatherStation weatherStation : weatherStations) {
				if (weatherStation.getStationStatus() == WeatherStation.weatherStationMode.RUNNING) {
					weatherStation.stopWeatherStation();
				}
			}
			executorService.shutdown();
		}
	}

}
