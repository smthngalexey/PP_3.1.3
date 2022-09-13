package kulish.webapp.services;

import kulish.webapp.models.Role;

import java.util.Set;

public interface RoleService {

    void addRole(Role role);


    Role getRoleByName(String name);

    public Set<Role> getRolesByName(Set<Role> roles);


    Set<Role> getAllRoles();
}
