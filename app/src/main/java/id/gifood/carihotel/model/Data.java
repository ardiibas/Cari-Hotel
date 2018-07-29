package id.gifood.carihotel.model;

import java.util.List;

public class Data {
    private String current_page;
    private List<HotelModel> data;
    private String first_page_url;
    private String from;
    private String last_page;
    private String last_page_url;
    private String next_page_url;
    private String path;
    private String per_page;
    private String prev_page_url;
    private String to;
    private String total;



    public String getCurrent_page() {
        return current_page;
    }

    public List<HotelModel> getData() {
        return data;
    }

    public String getFirst_page_url() {
        return first_page_url;
    }

    public String getFrom() {
        return from;
    }

    public String getLast_page() {
        return last_page;
    }

    public String getLast_page_url() {
        return last_page_url;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public String getPath() {
        return path;
    }

    public String getPer_page() {
        return per_page;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public String getTo() {
        return to;
    }

    public String getTotal() {
        return total;
    }
}
