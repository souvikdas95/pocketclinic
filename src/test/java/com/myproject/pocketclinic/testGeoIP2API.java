package com.myproject.pocketclinic;

import com.maxmind.db.*;
import com.maxmind.geoip2.*;
import com.maxmind.geoip2.exception.*;
import com.maxmind.geoip2.model.*;
import com.maxmind.geoip2.record.*;
import java.io.*;
import java.net.*;

public class testGeoIP2API
{
    public static void main(String[] args) throws IOException, GeoIp2Exception
    {
        // A File object pointing to your GeoIP2 or GeoLite2 database
        File database = new File("src\\main\\resources\\GeoLite2-City.mmdb");

        // This creates the DatabaseReader object, which should be reused across
        // lookups.
        DatabaseReader reader = new DatabaseReader.Builder(database).withCache(new CHMCache()).build();

        InetAddress ipAddress = InetAddress.getByName("1.186.13.64");

        // Replace "city" with the appropriate method for your database, e.g.,
        // "country".
        CityResponse response = reader.city(ipAddress);

        Country country = response.getCountry();
        System.out.println(country.getIsoCode());            // 'US'
        System.out.println(country.getName());               // 'United States'
        System.out.println(country.getNames().get("zh-CN")); // '美国'

        Subdivision subdivision = response.getMostSpecificSubdivision();
        System.out.println(subdivision.getName());    // 'Minnesota'
        System.out.println(subdivision.getIsoCode()); // 'MN'

        City city = response.getCity();
        System.out.println(city.getName()); // 'Minneapolis'

        Postal postal = response.getPostal();
        System.out.println(postal.getCode()); // '55455'

        Location location = response.getLocation();
        System.out.println(location.getLatitude());  // 44.9733
        System.out.println(location.getLongitude()); // -93.2323
    }
}

