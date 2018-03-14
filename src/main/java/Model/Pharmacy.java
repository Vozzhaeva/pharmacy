package Model;

/**
 * Created by Anastasia on 18.03.2017.
 */
public class Pharmacy {
    private Integer id_ph;
    private String name_ph;
    private String address_ph;
    private String phone_ph;
    private String site_ph;

    public Pharmacy(){

    }

    public String getName_ph() {
        return name_ph;
    }

    public void setName_ph(String name_ph) {
        this.name_ph = name_ph;
    }

    public String getAddress_ph() {
        return address_ph;
    }

    public void setAddress_ph(String address_ph) {
        this.address_ph = address_ph;
    }

    public String getPhone_ph() {
        return phone_ph;
    }

    public void setPhone_ph(String phone_ph) {
        this.phone_ph = phone_ph;
    }

    public String getSite_ph() {
        return site_ph;
    }

    public void setSite_ph(String site_ph) {
        this.site_ph = site_ph;
    }

    public Integer getId_ph() {
        return id_ph;
    }

    public void setId_ph(Integer id_ph) {
        this.id_ph = id_ph;
    }
}

