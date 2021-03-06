package com.cbi.simulator;

import com.cbi.simulator.bean.WeatherInfo;

public class MainClass {
	public static void main(String[] args) {
		WeatherStationManager weatherStationManager = WeatherStationManager
				.getInstance();
		weatherStationManager.loadWeatherStations();
		System.out.println("size :"
				+ weatherStationManager.getWeatherStationMaper().size());

		weatherStationManager.registerForAllWeatherUpdate(iweatherbroadCast);
	}

	private static int count;

	private static IweatherbroadCast iweatherbroadCast = new IweatherbroadCast() {

		@Override
		public void onWeatherBroadCast(WeatherInfo weatherInfo) {
			System.out.println(weatherInfo.getStationId());
			count = count + 1;
			if (count == 10)
				WeatherStationManager.getInstance().unRegisterForWeatherUpdate(
						"1001", this);
			if (count == 14){
				WeatherStationManager.getInstance().unRegisterForWeatherUpdate(
						"1002", this);
				
			}
			if (count == 20){
				WeatherStationManager.getInstance().unRegisterForWeatherUpdate(
						"1003", this);
				
			}
			if (count == 25){
				WeatherStationManager.getInstance().unRegisterForWeatherUpdate(
						"1004", this);
				
			}
			if (count == 30){
				WeatherStationManager.getInstance().unRegisterForWeatherUpdate(
						"1005", this);
				
			}
			if (count == 35){
				WeatherStationManager.getInstance().unRegisterForWeatherUpdate(
						"1006", this);
				WeatherStationManager.getInstance().shouldDownWeatherStation();
				
			}
			
			
		}
	};
}
