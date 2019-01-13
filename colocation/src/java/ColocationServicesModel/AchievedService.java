/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColocationServicesModel;

import java.awt.Image;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Phil
 */
@Entity
public class AchievedService implements Serializable {
    @Id
    @GeneratedValue
    private User from;
    private User to;
    private String date;
    private Image picture;
    private boolean valid;
    
    public AchievedService() {
    }

    public AchievedService(User from, User to, String date, Image picture, boolean valid) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.picture = picture;
        this.valid = valid;
    }
    public void setFrom(User from) {
        this.from = from;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public Image getPicture() {
        return picture;
    }

    public boolean isValid() {
        return valid;
    }
}
