package com.example.rodrigosantos.androidstudioprojects.weatherforecastbycityccp3anbua;

import java.util.List;

class WeatherData {
    private Long dt;
    private WeatherDataDetails main;
    private List <ImageDatails> weather;

    public List<ImageDatails> getWeather() {
        return weather;
    }

    public void setWeather(List<ImageDatails> weather) {
        this.weather = weather;
    }

    public Long getDt() {
        return dt;
    }

    public WeatherDataDetails getMain() {
        return main;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public void setMain(WeatherDataDetails main) {
        this.main = main;
    }
}
