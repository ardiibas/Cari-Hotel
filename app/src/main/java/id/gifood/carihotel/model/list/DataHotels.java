package id.gifood.carihotel.model.list;

import java.util.List;

public class DataHotels {
    private int id;
    private String name;
    private String address;
    private List<String> facilities;
    private Double latitude;
    private Double longitude;
    private List<String> arounds;
    private List<String> images;
    private String created_at;
    private String updated_at;
    private List<Criteria> criterias = null;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getFacilities() {
        return facilities;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public List<String> getArounds() {
        return arounds;
    }

    public List<String> getImages() {
        return images;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }
}
