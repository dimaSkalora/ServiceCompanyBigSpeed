package org.speed.big.company.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.AuthorizedUser;
import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(getClass());

    private UserRepository userRepository;

    public UserDetailsServiceImpl(@Qualifier("dataJpaUserRepositoryImpl") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(username.toLowerCase());
        AuthorizedUser.setUser(user);
        log.info(" AuthorizedUser.setUser "+user);

        AuthorizedUser.setId(user.getId());

        if (user == null)
            throw new UsernameNotFoundException("User " + username + " is not found");

        List<Role> getRoleList = user.getRoleList();
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (getRoleList != null) {
            for (Role roleName : getRoleList) {
                GrantedAuthority authority = new SimpleGrantedAuthority(roleName.getName());
                grantList.add(authority);
            }
        }

        log.info("Login ["+user.getEmail()+"], grantList: ["+grantList+"]");

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, grantList);
    }
}
