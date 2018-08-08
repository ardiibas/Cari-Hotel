package id.gifood.carihotel.model;

public class Ranges {

    private int id;
    private int criteria_id;
    private double range_start;
    private double range_end;
    private int value;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public int getCriteria_id() {
        return criteria_id;
    }

    public double getRange_start() {
        return range_start;
    }

    public double getRange_end() {
        return range_end;
    }

    public int getValue() {
        return value;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCriteria_id(int criteria_id) {
        this.criteria_id = criteria_id;
    }

    public void setRange_start(double range_start) {
        this.range_start = range_start;
    }

    public void setRange_end(double range_end) {
        this.range_end = range_end;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        try {
            if(getRange_start() == 0.0){
                return String.valueOf(0) + " - " + String.valueOf(getRange_end());
            }else if(getRange_end() == 0.0){
                return String.valueOf(getRange_start()) + " - ~";
            }
            return String.valueOf(getRange_start()) + " - " + String.valueOf(getRange_end());
        }catch (Exception ex){
            return String.valueOf(getRange_start());
        }
    }
}
