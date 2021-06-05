package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.dto.CompanyDTO;
import it.unisalento.mylinkedin.entities.Company;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyModelmapperUnitTest {

    @Test
    public void whenConvertCompanyEntityToCompanyDto_thenCorrect() {

        Company company = new Company();
        company.setId(1);
        company.setName("testName");
        company.setDescription("testDescription");
        company.setPartitaIva("testPartIva");
        company.setAddress("testAddress");

        CompanyDTO companyDTO = new CompanyDTO().convertToDto(company);

        assertEquals(company.getId(), companyDTO.getId());
        assertEquals(company.getName(), companyDTO.getName());
        assertEquals(company.getDescription(), companyDTO.getDescription());
        assertEquals(company.getPartitaIva(), companyDTO.getPartitaIva());
        assertEquals(company.getAddress(), companyDTO.getAddress());
    }

    @Test
    public void whenConvertCompanyDtoToCompanyEntity_thenCorrect() {

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(1);
        companyDTO.setName("testName");
        companyDTO.setDescription("testDescription");
        companyDTO.setPartitaIva("testPartIva@test.com");
        companyDTO.setAddress("testAddress");

        Company company = new Company().convertToEntity(companyDTO);

        assertEquals(company.getId(), companyDTO.getId());
        assertEquals(company.getName(), companyDTO.getName());
        assertEquals(company.getDescription(), companyDTO.getDescription());
        assertEquals(company.getPartitaIva(), companyDTO.getPartitaIva());
        assertEquals(company.getAddress(), companyDTO.getAddress());
    }
}
