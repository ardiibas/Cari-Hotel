package id.gifood.carihotel.model.list;

/**
 * id.gifood.carihotel.model
 * Created by ROFIE SAGARA on 8/2/2018.
 * Cari-Hotel
 */
public class Facility {
    private int id;
    private String name;

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Facility) obj).id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
