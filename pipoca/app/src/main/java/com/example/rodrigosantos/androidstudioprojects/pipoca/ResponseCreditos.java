package com.example.rodrigosantos.androidstudioprojects.pipoca;

import java.util.List;

public class ResponseCreditos {
    private List<ResponseCrew> crew;

    public List<ResponseCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<ResponseCrew> crew) {
        this.crew = crew;
    }
}
