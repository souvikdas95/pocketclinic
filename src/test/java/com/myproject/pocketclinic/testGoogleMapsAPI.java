package com.myproject.pocketclinic;

import com.google.maps.*;
import com.google.maps.errors.*;
import com.google.maps.internal.*;
import com.google.maps.model.*;

public class testGoogleMapsAPI
{
    public static void main(String args[]) throws Exception
    {
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDnlSwkZl4qzLvFPdyAwSYtl8Uyhxd6c0s");
        
        // Manual Set Host / Current Location
        GeocodingApiRequest OGReq = GeocodingApi.newRequest(context);
        OGReq.address("Room 704, 14th Block, Boys Hostel, Manipal, India");
        GeocodingResult[] OGResult = OGReq.await(); // Direct Response
        if(OGResult.length > 0)
            System.out.println("Host Location: " + OGResult[0].geometry.location);
        
        // Get Nearby Places ( Hospitals ) List ( Rank by Distance )
        LatLng tempLoc = new LatLng(13.3605,74.7864);
        NearbySearchRequest ONSReq = PlacesApi.nearbySearchQuery(context, tempLoc);
        ONSReq.type(PlaceType.HOSPITAL);
        ONSReq.rankby(RankBy.DISTANCE);
        PlacesSearchResponse OPSResponse = ONSReq.await();
        PlacesSearchResult[] OPSResult = OPSResponse.results;
        
        // Find Distance b/w Host and each of the Nearby Places & Display
        for(PlacesSearchResult x:OPSResult)
        {
            DistanceMatrixApiRequest ODMARequest = DistanceMatrixApi.newRequest(context);
            ODMARequest.origins(x.geometry.location);
            ODMARequest.destinations(OGResult[0].geometry.location);
            DistanceMatrix ODMResponse = ODMARequest.await();
            DistanceMatrixRow[] ODMResult = ODMResponse.rows;
            if(ODMResult.length > 0)
                System.out.println(x.name + " ( " + ODMResult[0].elements[0].distance + " ) ");
        }
    }
}
