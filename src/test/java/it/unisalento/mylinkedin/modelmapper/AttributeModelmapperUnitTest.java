package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.dto.AttributeDTO;
import it.unisalento.mylinkedin.entities.Attribute;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributeModelmapperUnitTest {

    @Test
    public void whenConvertAttributeEntityToAttributeDto_thenCorrect() {

        Attribute attribute = new Attribute();
        attribute.setId(1);
        attribute.setTitle("testTitle");
        attribute.setType("testType");

        AttributeDTO attributeDTO = new AttributeDTO().convertToDto(attribute);

        assertEquals(attribute.getId(), attributeDTO.getId());
        assertEquals(attribute.getTitle(), attributeDTO.getTitle());
        assertEquals(attribute.getType(), attributeDTO.getType());
    }

    @Test
    public void whenConvertAttributeDtoToAttributeEntity_thenCorrect() {

        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setId(1);
        attributeDTO.setTitle("testTitle");
        attributeDTO.setType("testType");

        Attribute attribute = new Attribute().convertToEntity(attributeDTO);

        assertEquals(attribute.getId(), attributeDTO.getId());
        assertEquals(attribute.getTitle(), attributeDTO.getTitle());
        assertEquals(attribute.getType(), attributeDTO.getType());
    }
}
