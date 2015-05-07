package com.example.gramos.raportiti;

// maintain single contact as an object
//import org.json.JSONException;
//import org.json.JSONObject;


@SuppressWarnings("ALL")
public class DatabaseO {

    @SuppressWarnings("WeakerAccess")
    int _id;
    @SuppressWarnings("WeakerAccess")
    double lat;
    @SuppressWarnings("WeakerAccess")
    double lng;

    // Empty constructor
    public DatabaseO() {

    }

    // constructor
    public DatabaseO(int id, double lat, double lng) {
        this._id = id;
        this.lat = lat;
        this.lng = lng;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting ID
    public int setID(int id) {
        return this._id = id;
    }

    // getting Lat
    public double getLat() {
        return this.lat;
    }

    // setting Lat
    public void setLat(double lat) {
        this.lat = lat;
    }

    // getting Lng
    public double getLng() {
        return this.lng;
    }

    // setting Lng
    public void setLng(double lng) {
        this.lng = lng;
    }
    /**
     public String toJSON(){

     JSONObject jsonObject = new JSONObject();
     try{
     jsonObject.put("id", getID());
     jsonObject.put("lat", getLat());
     jsonObject.put("lng", getlng());

     return jsonObject.toString();
     }catch (JSONException e){
     // TODO Auto-generated catch block
     e.printStackTrace();
     return "";
     }
     }
     */
}
