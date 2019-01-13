/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColocationServicesControllers;

import ColocationServicesDAO.DAOColocation;
import ColocationServicesModel.Colocation;
import java.util.List;

/**
 *
 * @author Phil
 */
public class ColocationManager {
    static DAOColocation daoColocation = new DAOColocation();
    
    public static List<Colocation> getUsers() {
		List<Colocation> lv = daoColocation.findAll();
		return lv;
	}
    
    public static Colocation getColocation(String name) {		
		if (name==null)
			return null;
		
		Colocation u=daoColocation.find(name);
		return u;
	}
    
    public static boolean createColocation(String name) {
		Colocation c = daoColocation.find(name);
		if (c == null) {
			daoColocation.create(new Colocation(name));
			return true;
		}
		return false;
	}
    
}
