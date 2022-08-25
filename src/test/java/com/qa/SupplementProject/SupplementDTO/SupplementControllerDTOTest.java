package com.qa.SupplementProject.SupplementDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.qa.SupplementProject.Supplement.Supplement;
import com.qa.SupplementProject.Supplement.SupplementRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SupplementControllerDTO.class})
@ExtendWith(SpringExtension.class)
class SupplementControllerDTOTest {
    @Autowired
    private SupplementControllerDTO supplementControllerDTO;

    @MockBean
    private SupplementServiceDTO supplementServiceDTO;

    @Test
    void testGetSupplementById() throws Exception {

        // Creating a new supplement and instantiating it
        Supplement supplement = new Supplement();
        supplement.setDescription("Supplement test description");
        supplement.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement.setId(123L);
        supplement.setLowerBoundaryDoseMG(10.0d);
        supplement.setName("SupplementName_Test");
        supplement.setPubChemId(123L);
        supplement.setUpperBoundaryDoseMG(15.0d);

        // When any ID is searched for it should return a supplement
        when(supplementServiceDTO.getSupplementById((Long) any())).thenReturn(supplement);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v6.2/dto/supplement/getById/{supplementId}", 123L);
        MockMvcBuilders.standaloneSetup(supplementControllerDTO).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":123,\"entryDate\":[2022,8,24],\"name\":\"SupplementName_Test\",\"pubChemId\":123,\"description\":\"Supplement test description\",\"lowerBoundaryDoseMG\":10.0,\"upperBoundaryDoseMG\":15.0}"));
    }
    // Above lines search for the supplementID of 123L and expect the result to be a json,
    // return the status Ok and match the instantiated supplement


    @Test
    void testGetSupplementByName() throws Exception {

        // This test does the same as above test BUT uses the name instead of Id
        Supplement supplement = new Supplement();
        supplement.setDescription("Supplement test description");
        supplement.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement.setId(123L);
        supplement.setLowerBoundaryDoseMG(10.0d);
        supplement.setName("SupplementName_Test");
        supplement.setPubChemId(123L);
        supplement.setUpperBoundaryDoseMG(15.0d);

        when(supplementServiceDTO.getSupplementByName((String) any())).thenReturn(supplement);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v6.2/dto/supplement/getByName/{name}", "SupplementName_Test");
        MockMvcBuilders.standaloneSetup(supplementControllerDTO).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":123,\"entryDate\":[2022,8,24],\"name\":\"SupplementName_Test\",\"pubChemId\":123,\"description\":\"Supplement test description\",\"lowerBoundaryDoseMG\":10.0,\"upperBoundaryDoseMG\":15.0}"));
    }

    @Test
    void testAddNewSupplement() {

        // This test checks that a supplement has been added
        Supplement supplement = new Supplement();
        supplement.setDescription("Supplement test description");
        supplement.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement.setId(123L);
        supplement.setLowerBoundaryDoseMG(10.0d);
        supplement.setName("SupplementName_Test");
        supplement.setPubChemId(123L);
        supplement.setUpperBoundaryDoseMG(15.0d);

        // Mocks behaviour of service/controller communications
        SupplementServiceDTO supplementServiceDTO = mock(SupplementServiceDTO.class);
        when(supplementServiceDTO.addNewSupplement((Supplement) any())).thenReturn(supplement);
        SupplementControllerDTO supplementControllerDTO = new SupplementControllerDTO(supplementServiceDTO);

        Supplement supplement1 = new Supplement();
        supplement1.setDescription("Supplement test description");
        supplement1.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement1.setId(123L);
        supplement1.setLowerBoundaryDoseMG(10.0d);
        supplement1.setName("SupplementName_Test");
        supplement1.setPubChemId(123L);
        supplement1.setUpperBoundaryDoseMG(15.0d);
        supplementControllerDTO.addNewSupplement(supplement1);

        verify(supplementServiceDTO).addNewSupplement((Supplement) any());
    }

    @Test
    void testDeleteSuppByID() throws Exception {

        //Mock request builder is used to delete a supplement by ID
        doNothing().when(supplementServiceDTO).deleteSuppByID((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/api/v6.2/dto/supplement/delByID/{supplementID}", 1L
        );
        MockMvcBuilders.standaloneSetup(supplementControllerDTO).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk() //Checks whether HTTP status code is 200/Ok i.e. no errors deleting
        );

    }

    @Test
    void testDeleteSuppByName() throws Exception {

        // Same as above but using name as parameter
        doNothing().when(supplementServiceDTO).deleteSuppByName((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/api/v6.2/dto/supplement/delByName/{supplementName}", "Supplement Name");
        MockMvcBuilders.standaloneSetup(supplementControllerDTO).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    void testFindAllSortedById() throws Exception {

        Supplement supplement = new Supplement();
        supplement.setDescription("Supplement test description");
        supplement.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement.setId(123L);
        supplement.setLowerBoundaryDoseMG(10.0d);
        supplement.setName("SupplementName_Test");
        supplement.setPubChemId(123L);
        supplement.setUpperBoundaryDoseMG(15.0d);

        // Creates an ArrayList of supplements, adds the object above then
        // checks the object found using the id is the same as above
        ArrayList<Supplement> supplementList = new ArrayList<>();
        supplementList.add(supplement);
        when(supplementServiceDTO.getAllSupplementsSortedById()).thenReturn(supplementList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v6.2/dto/supplement/sortedById"
        );
        MockMvcBuilders.standaloneSetup(supplementControllerDTO).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(
                        MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                                MockMvcResultMatchers.content().string(
                                        "[{\"id\":123,\"entryDate\":[2022,8,24],\"name\":\"SupplementName_Test\",\"pubChemId\":123,\"description\":\"Supplement test description\",\"lowerBoundaryDoseMG\":10.0,\"upperBoundaryDoseMG\":15.0}]"
                                )
        );
    }

    @Test
    void testFindAllSortedByName() throws Exception {

        // Does the same as above but using name
        Supplement supplement = new Supplement();
        supplement.setDescription("Supplement test description");
        supplement.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement.setId(123L);
        supplement.setLowerBoundaryDoseMG(10.0d);
        supplement.setName("SupplementName_Test");
        supplement.setPubChemId(123L);
        supplement.setUpperBoundaryDoseMG(15.0d);

        ArrayList<Supplement> supplementList = new ArrayList<>();
        supplementList.add(supplement);
        when(supplementServiceDTO.getAllSupplementsSortedByName()).thenReturn(supplementList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v6.2/dto/supplement/sortedByName"
        );
        MockMvcBuilders.standaloneSetup(supplementControllerDTO).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(
                        MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                                MockMvcResultMatchers.content().string("[{\"id\":123,\"entryDate\":[2022,8,24],\"name\":\"SupplementName_Test\",\"pubChemId\":123,\"description\":\"Supplement test description\",\"lowerBoundaryDoseMG\":10.0,\"upperBoundaryDoseMG\":15.0}]"
                                )
        );
    }

    @Test
    void testFindByName() throws Exception {
        //Works similarly to those above and uses name to find supplement
        Supplement supplement = new Supplement();
        supplement.setDescription("Supplement test description");
        supplement.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement.setId(123L);
        supplement.setLowerBoundaryDoseMG(10.0d);
        supplement.setName("SupplementName_Test");
        supplement.setPubChemId(123L);
        supplement.setUpperBoundaryDoseMG(15.0d);

        ArrayList<Supplement> supplementList = new ArrayList<>();
        supplementList.add(supplement);
        when(supplementServiceDTO.getAllSupplements()).thenReturn(supplementList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v6.2/dto/supplement/all")
                .param("SupplementName_Test", "foo"
                );
        MockMvcBuilders.standaloneSetup(supplementControllerDTO).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(
                        MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                                MockMvcResultMatchers.content().string("[{\"id\":123,\"entryDate\":[2022,8,24],\"name\":\"SupplementName_Test\",\"pubChemId\":123,\"description\":\"Supplement test description\",\"lowerBoundaryDoseMG\":10.0,\"upperBoundaryDoseMG\":15.0}]"
                                )
        );
    }
}

