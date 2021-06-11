package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.StructureDTO;
import it.unisalento.mylinkedin.entities.Structure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StructureModelmapperUnitTest {

    @Test
    public void whenConvertStructureEntityToStructureDto_thenCorrect() {

        Structure structure = new Structure();
        structure.setId(1);
        structure.setTitle("testTitle");
        structure.setDescription("testDescription");
        structure.setUserCanPublish(Constants.CAN_PUBLISH_BOTH);

        StructureDTO structureDTO = new StructureDTO().convertToDto(structure);

        assertEquals(structure.getId(), structureDTO.getId());
        assertEquals(structure.getTitle(), structureDTO.getTitle());
        assertEquals(structure.getDescription(), structureDTO.getDescription());
        assertEquals(structure.getUserCanPublish(), structureDTO.getUserCanPublish());
    }

    @Test
    public void whenConvertStructureDtoToStructureEntity_thenCorrect() {

        StructureDTO structureDTO = new StructureDTO();
        structureDTO.setId(1);
        structureDTO.setTitle("testTitle");
        structureDTO.setDescription("testDescription");
        structureDTO.setUserCanPublish(Constants.CAN_PUBLISH_BOTH);

        Structure structure = new Structure().convertToEntity(structureDTO);

        assertEquals(structure.getId(), structureDTO.getId());
        assertEquals(structure.getTitle(), structureDTO.getTitle());
        assertEquals(structure.getDescription(), structureDTO.getDescription());
        assertEquals(structure.getUserCanPublish(), structureDTO.getUserCanPublish());
    }
}
