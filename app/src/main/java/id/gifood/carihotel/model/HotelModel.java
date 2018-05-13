package id.gifood.carihotel.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

public class HotelModel implements Parcelable {
    int id;
    String name;
    String address;
    String facilities;
    Double latitude;
    Double longitude;
    String arounds;
    String imagesl;
    Timestamp created_at;
    Timestamp updated_at;

    public HotelModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getArounds() {
        return arounds;
    }

    public void setArounds(String arounds) {
        this.arounds = arounds;
    }

    public String getImagesl() {
        return imagesl;
    }

    public void setImagesl(String imagesl) {
        this.imagesl = imagesl;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.facilities);
        dest.writeValue(this.latitude);
        dest.writeValue(this.longitude);
        dest.writeString(this.arounds);
        dest.writeString(this.imagesl);
        dest.writeSerializable(this.created_at);
        dest.writeSerializable(this.updated_at);
    }

    protected HotelModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.address = in.readString();
        this.facilities = in.readString();
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.arounds = in.readString();
        this.imagesl = in.readString();
        this.created_at = (Timestamp) in.readSerializable();
        this.updated_at = (Timestamp) in.readSerializable();
    }

    public static final Parcelable.Creator<HotelModel> CREATOR = new Parcelable.Creator<HotelModel>() {
        @Override
        public HotelModel createFromParcel(Parcel source) {
            return new HotelModel(source);
        }

        @Override
        public HotelModel[] newArray(int size) {
            return new HotelModel[size];
        }
    };
}
