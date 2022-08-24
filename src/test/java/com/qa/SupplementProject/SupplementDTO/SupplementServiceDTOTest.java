package com.qa.SupplementProject.SupplementDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.qa.SupplementProject.Exception.IDNotFoundException;
import com.qa.SupplementProject.Exception.NameExistsException;
import com.qa.SupplementProject.Exception.NameNotFoundException;
import com.qa.SupplementProject.Exception.PubChemIdExistsException;
import com.qa.SupplementProject.Supplement.Supplement;
import com.qa.SupplementProject.Supplement.SupplementRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SupplementServiceDTO.class})
@ExtendWith(SpringExtension.class)
class SupplementServiceDTOTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private SupplementRepository supplementRepository;

    @Autowired
    private SupplementServiceDTO supplementServiceDTO;

    @Test
    void testGetAllSupplements() {
        //Checks that a mock array returns the same supplements as the actual DB
        ArrayList<Supplement> supplementList = new ArrayList<>();
        when(supplementRepository.findAll()).thenReturn(supplementList);
        List<Supplement> actualAllSupplements = supplementServiceDTO.getAllSupplements();
        assertSame(supplementList, actualAllSupplements);
        assertTrue(actualAllSupplements.isEmpty());
        verify(supplementRepository).findAll();
    }

    @Test
    void testGetAllSupplements2() {

        //Checks exceptions thrown
        when(supplementRepository.findAll()).thenThrow(new IDNotFoundException("An error occurred"));
        assertThrows(IDNotFoundException.class, () -> supplementServiceDTO.getAllSupplements());
        verify(supplementRepository).findAll();
    }

    @Test
    void testGetAllSupplementsSortedByName() {
        ArrayList<Supplement> supplementList = new ArrayList<>();
        when(supplementRepository.findAllByOrderByNameAsc()).thenReturn(supplementList);
        List<Supplement> actualAllSupplementsSortedByName = supplementServiceDTO.getAllSupplementsSortedByName();
        assertSame(supplementList, actualAllSupplementsSortedByName);
        assertTrue(actualAllSupplementsSortedByName.isEmpty());
        verify(supplementRepository).findAllByOrderByNameAsc();
    }

    @Test
    void testGetAllSupplementsSortedByName2() {
        when(supplementRepository.findAllByOrderByNameAsc()).thenThrow(new IDNotFoundException("An error occurred"));
        assertThrows(IDNotFoundException.class, () -> supplementServiceDTO.getAllSupplementsSortedByName());
        verify(supplementRepository).findAllByOrderByNameAsc();
    }

    @Test
    void testGetAllSupplementsSortedById() {
        ArrayList<Supplement> supplementList = new ArrayList<>();
        when(supplementRepository.findAllByOrderByIdAsc()).thenReturn(supplementList);
        List<Supplement> actualAllSupplementsSortedById = supplementServiceDTO.getAllSupplementsSortedById();
        assertSame(supplementList, actualAllSupplementsSortedById);
        assertTrue(actualAllSupplementsSortedById.isEmpty());
        verify(supplementRepository).findAllByOrderByIdAsc();
    }

    @Test
    void testGetAllSupplementsSortedById2() {
        when(supplementRepository.findAllByOrderByIdAsc()).thenThrow(new IDNotFoundException("An error occurred"));
        assertThrows(IDNotFoundException.class, () -> supplementServiceDTO.getAllSupplementsSortedById());
        verify(supplementRepository).findAllByOrderByIdAsc();
    }

    @Test
    void testGetSupplementById() {
        Supplement supplement = new Supplement();
        supplement.setDescription("Supplement Test Description");
        supplement.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement.setId(123L);
        supplement.setLowerBoundaryDoseMG(10.0d);
        supplement.setName("SupplementName_Test");
        supplement.setPubChemId(123L);
        supplement.setUpperBoundaryDoseMG(15.0d);
        Optional<Supplement> ofResult = Optional.of(supplement);
        when(supplementRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(supplement, supplementServiceDTO.getSupplementById(123L));
        verify(supplementRepository).findById((Long) any());
    }

    @Test
    void testGetSupplementById2() {
        when(supplementRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IDNotFoundException.class, () -> supplementServiceDTO.getSupplementById(123L));
        verify(supplementRepository).findById((Long) any());
    }

    @Test
    void testGetSupplementByName() {
        Supplement supplement = new Supplement();
        supplement.setDescription("Supplement Test Description");
        supplement.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement.setId(123L);
        supplement.setLowerBoundaryDoseMG(15.0d);
        supplement.setName("SupplementName_Test");
        supplement.setPubChemId(123L);
        supplement.setUpperBoundaryDoseMG(15.0d);
        Optional<Supplement> ofResult = Optional.of(supplement);
        when(supplementRepository.findByName((String) any())).thenReturn(ofResult);
        assertSame(supplement, supplementServiceDTO.getSupplementByName("SupplementName_Test"));
        verify(supplementRepository).findByName((String) any());
    }

    @Test
    void testGetSupplementByName2() {
        when(supplementRepository.findByName((String) any())).thenReturn(Optional.empty());
        assertThrows(NameNotFoundException.class, () -> supplementServiceDTO.getSupplementByName("SupplementName_Test"));
        verify(supplementRepository).findByName((String) any());
    }

    @Test
    void testAddNewSupplement() {
        Supplement supplement = new Supplement();
        supplement.setDescription("Supplement Test Description");
        supplement.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement.setId(123L);
        supplement.setLowerBoundaryDoseMG(10.0d);
        supplement.setName("SupplementName_Test");
        supplement.setPubChemId(123L);
        supplement.setUpperBoundaryDoseMG(15.0d);

        Supplement supplement1 = new Supplement();
        supplement1.setDescription("Supplement Test Description");
        supplement1.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement1.setId(123L);
        supplement1.setLowerBoundaryDoseMG(10.0d);
        supplement1.setName("SupplementName_Test");
        supplement1.setPubChemId(123L);
        supplement1.setUpperBoundaryDoseMG(15.0d);
        Optional<Supplement> ofResult = Optional.of(supplement1);

        Supplement supplement2 = new Supplement();
        supplement2.setDescription("Supplement Test Description");
        supplement2.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement2.setId(123L);
        supplement2.setLowerBoundaryDoseMG(10.0d);
        supplement2.setName("SupplementName_Test");
        supplement2.setPubChemId(123L);
        supplement2.setUpperBoundaryDoseMG(15.0d);
        Optional<Supplement> ofResult1 = Optional.of(supplement2);
        when(supplementRepository.save((Supplement) any())).thenReturn(supplement);
        when(supplementRepository.findByName((String) any())).thenReturn(ofResult);
        when(supplementRepository.findByPubChemId((Long) any())).thenReturn(ofResult1);

        Supplement supplement3 = new Supplement();
        supplement3.setDescription("Supplement Test Description");
        supplement3.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement3.setId(123L);
        supplement3.setLowerBoundaryDoseMG(10.0d);
        supplement3.setName("SupplementName_Test");
        supplement3.setPubChemId(123L);
        supplement3.setUpperBoundaryDoseMG(15.0d);
        assertThrows(NameExistsException.class, () -> supplementServiceDTO.addNewSupplement(supplement3));
        verify(supplementRepository).findByName((String) any());
        verify(supplementRepository).findByPubChemId((Long) any());
    }

    @Test
    void testDeleteSuppByID() {
        doNothing().when(supplementRepository).deleteById((Long) any());
        when(supplementRepository.existsById((Long) any())).thenReturn(true);
        supplementServiceDTO.deleteSuppByID(123L);
        verify(supplementRepository).existsById((Long) any());
        verify(supplementRepository).deleteById((Long) any());
        assertTrue(supplementServiceDTO.getAllSupplements().isEmpty());
    }

    @Test
    void testDeleteSuppByID2() {
        doThrow(new IDNotFoundException("An error occurred")).when(supplementRepository).deleteById((Long) any());
        when(supplementRepository.existsById((Long) any())).thenReturn(true);
        assertThrows(IDNotFoundException.class, () -> supplementServiceDTO.deleteSuppByID(123L));
        verify(supplementRepository).existsById((Long) any());
        verify(supplementRepository).deleteById((Long) any());
    }

    @Test
    void testDeleteSuppByName() {
        doNothing().when(supplementRepository).deleteByName((String) any());
        when(supplementRepository.existsByName((String) any())).thenReturn(true);
        supplementServiceDTO.deleteSuppByName("Supplement Name");
        verify(supplementRepository).existsByName((String) any());
        verify(supplementRepository).deleteByName((String) any());
        assertTrue(supplementServiceDTO.getAllSupplements().isEmpty());
    }

    @Test
    void testDeleteSuppByName2() {
        doThrow(new IDNotFoundException("An error occurred")).when(supplementRepository).deleteByName((String) any());
        when(supplementRepository.existsByName((String) any())).thenReturn(true);
        assertThrows(IDNotFoundException.class, () -> supplementServiceDTO.deleteSuppByName("Supplement Name"));
        verify(supplementRepository).existsByName((String) any());
        verify(supplementRepository).deleteByName((String) any());
    }

    @Test
    void testUpdateSupplement() {
        Supplement supplement = new Supplement();
        supplement.setDescription("Supplement Test Description");
        supplement.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement.setId(123L);
        supplement.setLowerBoundaryDoseMG(10.0d);
        supplement.setName("SupplementName_Test");
        supplement.setPubChemId(123L);
        supplement.setUpperBoundaryDoseMG(15.0d);

        Supplement supplement1 = new Supplement();
        supplement1.setDescription("Supplement Test Description");
        supplement1.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement1.setId(123L);
        supplement1.setLowerBoundaryDoseMG(10.0d);
        supplement1.setName("SupplementName_Test");
        supplement1.setPubChemId(123L);
        supplement1.setUpperBoundaryDoseMG(15.0d);
        Optional<Supplement> ofResult = Optional.of(supplement1);
        when(supplementRepository.save((Supplement) any())).thenReturn(supplement);
        when(supplementRepository.findByName((String) any())).thenReturn(ofResult);
        Supplement supplement2 = mock(Supplement.class);
        when(supplement2.getName()).thenReturn("SupplementName_Test");
        doNothing().when(supplement2).setDescription((String) any());
        doNothing().when(supplement2).setEntryDate((LocalDate) any());
        doNothing().when(supplement2).setId((Long) any());
        doNothing().when(supplement2).setLowerBoundaryDoseMG((Double) any());
        doNothing().when(supplement2).setName((String) any());
        doNothing().when(supplement2).setPubChemId((Long) any());
        doNothing().when(supplement2).setUpperBoundaryDoseMG((Double) any());

        supplement2.setDescription("Supplement Test Description");
        supplement2.setEntryDate(LocalDate.of(2022, 8, 24));
        supplement2.setId(123L);
        supplement2.setLowerBoundaryDoseMG(10.0d);
        supplement2.setName("SupplementName_Test");
        supplement2.setPubChemId(123L);
        supplement2.setUpperBoundaryDoseMG(15.0d);
        assertSame(supplement, supplementServiceDTO.updateSupplement(123L, supplement2));
        verify(supplementRepository).save((Supplement) any());
        verify(supplementRepository).findByName((String) any());
        verify(supplement2).getName();
        verify(supplement2).setDescription((String) any());
        verify(supplement2).setEntryDate((LocalDate) any());
        verify(supplement2, atLeast(1)).setId((Long) any());
        verify(supplement2).setLowerBoundaryDoseMG((Double) any());
        verify(supplement2).setName((String) any());
        verify(supplement2).setPubChemId((Long) any());
        verify(supplement2).setUpperBoundaryDoseMG((Double) any());
    }
}

