package it.unisalento.mylinkedin.iservice;

import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IUserServiceTest {

    @Autowired
    private IUserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AdministratorRepository administratorRepository;

    @MockBean
    private ApplicantRepository applicantRepository;

    @MockBean
    private OfferorRepository offerorRepository;

    @MockBean
    private ProfileImageRepository profileImageRepository;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private CompanyRepository companyRepository;

    @BeforeEach
    void init() {
    }


}
