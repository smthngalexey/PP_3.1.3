package kulish.webapp.dao;


import kulish.webapp.models.Role;

import java.util.Set;

public interface RoleDao {

    void addRole(Role role);

    Role getRoleByName(String name);

    Set<Role> getAllRoles();

    public Set<Role> getRolesByName(Set<Role> roles);
}