package br.com.branas.glstore.domain.services;

import br.com.branas.glstore.domain.vo.CoordinateVO;

public class DistanceCalculatorService {

    public double calculate(CoordinateVO coordFrom, CoordinateVO coordTo){
        if(coordFrom == null || coordTo == null) return 0;
        var radlat1 = (Math.PI * coordFrom.getLatitude()) / 180;
        var radlat2 = (Math.PI * coordTo.getLatitude()) / 180;
        var theta = coordFrom.getLongitude() - coordTo.getLongitude();
        var radtheta = (Math.PI * theta) / 180;
        var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
        if (dist > 1) dist = 1;
        dist = Math.acos(dist);
        dist = (dist * 180) / Math.PI;
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344; //convert miles to km
        return dist;
    }
}
