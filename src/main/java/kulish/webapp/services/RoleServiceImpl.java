package kulish.webapp.services;

import kulish.webapp.dao.RoleDao;
import kulish.webapp.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }


    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public Set<Role> getRolesByName(Set<Role> roles) {
        return roleDao.getRolesByName(roles);
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
