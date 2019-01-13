/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColocationServicesControllers;

import ColocationServicesDAO.DAOService;
import ColocationServicesModel.Colocation;
import ColocationServicesModel.Service;
import java.util.List;

/**
 *
 * @author Phil
 */
public class ServiceManager {

    static DAOService daoService = new DAOService();

    public static List<Service> getServices() {
        List<Service> lv = daoService.findAll();
        return lv;
    }

    public static Service getService(String title) {
        if (title == null) {
            return null;
        }

        Service s = daoService.find(title);
        return s;
    }

    public static boolean createService(Colocation c, String title, String description, int cost) {
        Service s = daoService.find(title);
        if (s == null) {
            daoService.create(new Service(c, title, description, cost));
            return true;
        }
        return false;
    }
}
