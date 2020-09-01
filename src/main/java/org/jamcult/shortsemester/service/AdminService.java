package org.jamcult.shortsemester.service;

import org.jamcult.shortsemester.model.Admin;
import org.jamcult.shortsemester.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isPresent()) {
            return admin.get();
        } else throw new UsernameNotFoundException(username + " not found!");
    }

    public void signUp(Admin admin) {
        admin.setPassword(
                bCryptPasswordEncoder.encode(admin.getPassword())
        );
        adminRepository.save(admin);
    }

    public void deleteByID(Long id) {
        adminRepository.deleteById(id);
    }

    public Iterable<Admin> getAll() {
       return adminRepository.findAll();
    }

    public Optional<Admin> getByID(Long id) {
        return adminRepository.findById(id);
    }
}
