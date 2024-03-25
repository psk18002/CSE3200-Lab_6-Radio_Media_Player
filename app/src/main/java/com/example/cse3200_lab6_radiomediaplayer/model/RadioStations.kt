package com.example.cse3200_lab6_radiomediaplayer.model

class RadioStations {

    val urls = listOf(
        arrayOf("WMNR Fine Arts Radio", "http://amber.streamguys.com:6050/live"),
        arrayOf("Connecticut Public Radio", "http://playerservices.streamtheworld.com/api/livestream-redirect/WNPRFM.mp3"),
        arrayOf("WWUH Alternative Radio","https://stream3477.egihosting.com:8002"),
        arrayOf("Soft Rock 106.5","http://crystalout.surfernetwork.com:8001/WBMW_MP3"),
        arrayOf("Star 99.9","http://playerservices.streamtheworld.com/api/livestream-redirect/WEZNFM.mp3")
    )

    var currentUrl = 0
    var station = urls[currentUrl]
    var stationName = station[0]

    fun getURL() : String {
        var station = urls[currentUrl]
        stationName = station[0]
        return station[1]
    }

    fun incrementNextURL() {
        currentUrl = (currentUrl + 1) % urls.size
    }
}