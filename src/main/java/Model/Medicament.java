package Model;

import java.util.Date;

/**
 * Created by Anastasia on 18.03.2017.
 */
public class Medicament {

    private Integer id_med;
    private String name_med;
    private Boolean recipe;
    private Date date_med;
    private Date limitation;
    private Integer id_m;


    public Medicament(){

    }
    public Integer getId_med() {
        return id_med;
    }

    public void setId_med(Integer id_med) {
        this.id_med = id_med;
    }

    public String getName_med() {
        return name_med;
    }

    public void setName_med(String name_med) {
        this.name_med = name_med;
    }


    public Boolean getRecipe() {
        return recipe;
    }

    public void setRecipe(Boolean recipe) {
        this.recipe = recipe;
    }

    public Date getDate_med() {
        return date_med;
    }

    public void setDate_med(Date date_med) {
        this.date_med = date_med;
    }

    public Date getLimitation() {
        return limitation;
    }

    public void setLimitation(Date limitation) {
        this.limitation = limitation;
    }

    public Integer getId_m() {
        return id_m;
    }

    public void setId_m(Integer id_m) {
        this.id_m = id_m;
    }
}

