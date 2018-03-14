package Model;

import java.util.Date;

/**
 * Created by Anastasia on 18.03.2017.
 */
public class Realization {
    private Integer id_ph;
    private Integer id_med;
    private Date date_r;
    private Integer price;


    public Realization(){

    }
    public Integer getId_ph() {
        return id_ph;
    }

    public void setId_ph(Integer id_ph) {
        this.id_ph = id_ph;
    }

    public Integer getId_med() {
        return id_med;
    }

    public void setId_med(Integer id_med) {
        this.id_med = id_med;
    }

    public Date getDate_r() {
        return date_r;
    }

    public void setDate_r(Date date_r) {
        this.date_r = date_r;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
