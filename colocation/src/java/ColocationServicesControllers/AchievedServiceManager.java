/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColocationServicesControllers;

import ColocationServicesDAO.DAOAchievedService;
import ColocationServicesModel.AchievedService;
import ColocationServicesModel.User;
import java.awt.Image;
import java.util.List;

/**
 *
 * @author Phil
 */
public class AchievedServiceManager {
    static DAOAchievedService daoAchievedService = new DAOAchievedService();

    public static List<AchievedService> getAchievedServices() {
        List<AchievedService> lv = daoAchievedService.findAll();
        return lv;
    }

    public static AchievedService getAchievedService(String from) {
        if (from == null) {
            return null;
        }

        AchievedService as = daoAchievedService.find(from);
        return as;
    }

    public static boolean createAchievedService(User from, User to, String date,Image picture, boolean valid) {
        AchievedService as = daoAchievedService.find(from);
        if (as == null) {
            daoAchievedService.create(new AchievedService(from,to,date,picture,valid));
            return true;
        }
        return false;
    }
}
