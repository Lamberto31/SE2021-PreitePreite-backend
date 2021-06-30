package it.unisalento.mylinkedin.iservice;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.AdministratorRepository;
import it.unisalento.mylinkedin.dao.ApplicantRepository;
import it.unisalento.mylinkedin.dao.OfferorRepository;
import it.unisalento.mylinkedin.dao.UserRepository;
import it.unisalento.mylinkedin.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IMyUserDetailsServiceTest {

    @Autowired
    UserDetailsService myUserDetailsService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AdministratorRepository administratorRepository;

    @MockBean
    private ApplicantRepository applicantRepository;

    @MockBean
    private OfferorRepository offerorRepository;


    String adminUsername;
    String applicantUsername;
    String offerorUsername;
    String wrongUsername;
    @Mock
    User userAdministrator;
    @Mock
    User userApplicant;
    @Mock
    User userOfferor;
    Administrator administrator;
    Applicant applicant;
    Offeror offeror;
    MyUserDetails adminDetailsMapped;
    UserDetails applicantDetailsMapped;
    MyUserDetails offerorDetailsMapped;

    @BeforeEach
    void init() {

        this.adminUsername = "adminUsername";
        this.applicantUsername = "applicantUsername";
        this.offerorUsername = "offerorUsername";
        this.wrongUsername = "wrongUsername";

        when(userAdministrator.getId()).thenReturn(1);
        when(userAdministrator.getEmail()).thenReturn(adminUsername);
        when(userAdministrator.getPassword()).thenReturn("testPassword");
        when(userAdministrator.getType()).thenReturn(Constants.TYPE_ADMIN);

        when(userRepository.findByEmail(adminUsername)).thenReturn(userAdministrator);

        when(userApplicant.getId()).thenReturn(2);
        when(userApplicant.getEmail()).thenReturn(applicantUsername);
        when(userApplicant.getPassword()).thenReturn("testPassword");
        when(userApplicant.getType()).thenReturn(Constants.TYPE_APPLICANT);

        when(userRepository.findByEmail(applicantUsername)).thenReturn(userApplicant);

        when(userOfferor.getId()).thenReturn(3);
        when(userOfferor.getEmail()).thenReturn(offerorUsername);
        when(userOfferor.getPassword()).thenReturn("testPassword");
        when(userOfferor.getType()).thenReturn(Constants.TYPE_OFFEROR);

        when(userRepository.findByEmail(offerorUsername)).thenReturn(userOfferor);

        this.administrator = new Administrator();
        this.administrator.setEmail(this.adminUsername);
        this.administrator.setPassword("testPassword");

        when(administratorRepository.findById(userAdministrator.getId())).thenReturn(java.util.Optional.ofNullable(administrator));


        this.applicant = new Applicant();
        this.applicant.setEmail(this.applicantUsername);
        this.applicant.setPassword("testPassword");
        this.applicant.setStatus(Constants.REGISTRATION_PENDING);

        when(applicantRepository.findById(userApplicant.getId())).thenReturn(java.util.Optional.ofNullable(applicant));

        this.offeror = new Offeror();
        this.offeror.setEmail(this.offerorUsername);
        this.offeror.setPassword("testPassword");
        this.offeror.setStatus(Constants.REGISTRATION_PENDING);

        when(offerorRepository.findById(userOfferor.getId())).thenReturn(java.util.Optional.ofNullable(offeror));

        this.adminDetailsMapped = new MyUserDetails(administrator);

        this.applicantDetailsMapped = java.util.Optional.ofNullable(applicant).map(MyUserDetails::new).get();

        this.offerorDetailsMapped = java.util.Optional.ofNullable(offeror).map(MyUserDetails::new).get();
    }

    @Test
    void loadUserAdministratorByUsernameTest() {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(userAdministrator.getEmail());
        assertThat(userDetails.getUsername().equals(adminDetailsMapped.getUsername())).isTrue();
        assertThat(userDetails.getPassword().equals(adminDetailsMapped.getPassword())).isTrue();
        assertThat(userDetails.isEnabled()).isEqualTo(adminDetailsMapped.isEnabled());
        assertThat(userDetails.getAuthorities().equals(adminDetailsMapped.getAuthorities())).isTrue();
    }

    @Test
    void loadUserApplicantByUsernameTest() {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(userApplicant.getEmail());
        assertThat(userDetails.getUsername().equals(applicantDetailsMapped.getUsername())).isTrue();
        assertThat(userDetails.getPassword().equals(applicantDetailsMapped.getPassword())).isTrue();
        assertThat(userDetails.isEnabled()).isEqualTo(applicantDetailsMapped.isEnabled());
        assertThat(userDetails.getAuthorities().equals(applicantDetailsMapped.getAuthorities())).isTrue();

    }

    @Test
    void loadUserOfferorByUsernameTest() {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(userOfferor.getEmail());
        assertThat(userDetails.getUsername().equals(offerorDetailsMapped.getUsername())).isTrue();
        assertThat(userDetails.getPassword().equals(offerorDetailsMapped.getPassword())).isTrue();
        assertThat(userDetails.isEnabled()).isEqualTo(offerorDetailsMapped.isEnabled());
        assertThat(userDetails.getAuthorities().equals(offerorDetailsMapped.getAuthorities())).isTrue();
    }

    @Test
    void loadUserByUsernameThrowsExTest() {
        Exception exp = assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername(wrongUsername));
        assertThat(exp).isNotNull();
    }


}
