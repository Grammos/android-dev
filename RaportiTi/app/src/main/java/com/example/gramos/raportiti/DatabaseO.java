package com.example.gramos.raportiti;

//import org.json.JSONException;
//import org.json.JSONObject;

public class DatabaseO {

    int _id;
    double lat;
    double lng;

    public DatabaseO() {

    }

    public DatabaseO(int id, double lat, double lng) {
        this._id = id;
        this.lat = lat;
        this.lng = lng;
    }

    public DatabaseO(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public int getID() {
        return this._id;
    }

    public int setID(int id) {
        return this._id = id;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

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
