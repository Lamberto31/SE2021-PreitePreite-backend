package it.unisalento.mylinkedin.service;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.AdministratorRepository;
import it.unisalento.mylinkedin.dao.ApplicantRepository;
import it.unisalento.mylinkedin.dao.OfferorRepository;
import it.unisalento.mylinkedin.dao.UserRepository;
import it.unisalento.mylinkedin.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    ApplicantRepository applicantRepository;

    @Autowired
    OfferorRepository offerorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(username);
            switch(user.getType()) {
                case Constants.TYPE_ADMIN:
                    Optional<Administrator> administrator = administratorRepository.findById(user.getId());
                    administrator.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
                    return administrator.map(MyUserDetails::new).get();

                case Constants.TYPE_APPLICANT:
                    Optional<Applicant> applicant = applicantRepository.findById(user.getId());
                    applicant.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
                    return applicant.map(MyUserDetails::new).get();

                case Constants.TYPE_OFFEROR:
                    Optional<Offeror> offeror = offerorRepository.findById(user.getId());
                    offeror.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
                    return offeror.map(MyUserDetails::new).get();

                default:
                    throw new UsernameNotFoundException("Not found: " + username);
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Not found: " + username);
        }
    }
}
