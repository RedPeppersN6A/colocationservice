/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColocationServicesModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Phil
 */
@Entity
public class Service {

    @Id
    @GeneratedValue
    private Colocation c;
    private String title;
    private String description;
    private int cost;

    public Service() {
    }

    public Service(Colocation c, String title, String description, int cost) {
        this.c = c;
        this.title = title;
        this.description = description;
        this.cost = cost;
    }

    public void setC(Colocation c) {
        this.c = c;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Colocation getC() {
        return c;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

}
