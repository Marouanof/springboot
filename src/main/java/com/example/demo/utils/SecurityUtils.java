package com.example.demo.utils;

import com.example.demo.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityUtils {
    @Autowired
    private UserAccountRepo userAccountRepo;
    public Boolean isOwner(UUID employee){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userAccountRepo.isOwner(userDetails.getUsername(),employee);
    }
}
