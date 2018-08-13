package id.gifood.carihotel.model.list;

import java.util.List;

public class Criterias {

    private int id;
    private String name;
    private String key;
    private String pattern;
    private String attribute;
    private String description;
    private String calcuate;
    private String created_at;
    private String updated_at;
    private List<Ranges> ranges;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getPattern() {
        return pattern;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getDescription() {
        return description;
    }

    public String getCalcuate() {
        return calcuate;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public List<Ranges> getRanges() {
        return ranges;
    }
}
