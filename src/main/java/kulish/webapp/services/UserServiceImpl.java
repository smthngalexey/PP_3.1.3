package kulish.webapp.services;

import kulish.webapp.dao.RoleDao;
import kulish.webapp.dao.UserDao;
import kulish.webapp.models.Role;
import kulish.webapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, @Lazy PasswordEncoder encoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleDao.getRoleByName("ROLE_USER")));
        this.userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(roleDao.getRolesByName(user.getRoles()));
        this.userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        this.userDao.removeUser(id);
    }

    @Override
    public User findUserById(Long id) {
        return this.userDao.findUserById(id);
    }

    @Override
    public List<User> getListUsers() {
        return this.userDao.getListUsers();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return user;
    }

}
