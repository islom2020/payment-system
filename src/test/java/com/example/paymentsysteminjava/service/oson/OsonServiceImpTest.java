package com.example.paymentsysteminjava.service.oson;

import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.repository.OsonRepository;
import com.example.paymentsysteminjava.repository.merchant.MerchantServiceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;

@ContextConfiguration(classes = {OsonServiceImp.class})
@ExtendWith(SpringExtension.class)
@DataJpaTest
class OsonServiceImpTest {

    @Autowired
    private OsonServiceImp osonServiceImp;

    @Autowired
    private OsonRepository osonRepository;

    @Autowired
    private MerchantServiceRepository merchantServiceRepository;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MerchantServiceEntity merchantServiceEntity = new MerchantServiceEntity();
        merchantServiceEntity.setMerchantServiceId(100L);
        merchantServiceEntity.setName("merchant");
        merchantServiceEntity.setMerchantEntity(null);
        MerchantServiceEntity save = merchantServiceRepository.save(merchantServiceEntity);
        assertNotNull(save);
    }

//    /**
//     * Method under test: {@link OsonServiceImp#add(Long)}
//     */
//    @Test
//    void testAdd() {
//        MerchantEntity merchantEntity = new MerchantEntity();
//        merchantEntity.setActive(true);
//        merchantEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
//        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantEntity.setCreatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
//        merchantEntity.setId(123L);
//        merchantEntity.setName("Name");
//        merchantEntity.setPassword("iloveyou");
//        merchantEntity.setPayme(true);
//        merchantEntity.setSecretKey("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
//        merchantEntity.setUcell(true);
//        merchantEntity.setUpdatedBy("2020-03-01");
//        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantEntity.setUpdatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
//        merchantEntity.setUsername("janedoe");
//        merchantEntity.setYandex(true);
//
//        MerchantServiceEntity merchantServiceEntity = new MerchantServiceEntity();
//        merchantServiceEntity.setActive(true);
//        merchantServiceEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
//        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantServiceEntity.setCreatedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
//        merchantServiceEntity.setId(123L);
//        merchantServiceEntity.setMerchantEntity(merchantEntity);
//        merchantServiceEntity.setMerchantServiceId(123L);
//        merchantServiceEntity.setName("Name");
//        merchantServiceEntity.setUpdatedBy("2020-03-01");
//        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantServiceEntity.setUpdatedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
//
//        OsonServiceEntity osonServiceEntity = new OsonServiceEntity();
//        osonServiceEntity.setActive(true);
//        osonServiceEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
//        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        osonServiceEntity.setCreatedDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
//        osonServiceEntity.setId(123L);
//        osonServiceEntity.setMerchantServiceEntity(merchantServiceEntity);
//        osonServiceEntity.setName("Name");
//        osonServiceEntity.setUpdatedBy("2020-03-01");
//        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        osonServiceEntity.setUpdatedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
//        when(this.osonRepository.save((OsonServiceEntity) any())).thenReturn(osonServiceEntity);
//
//        MerchantEntity merchantEntity1 = new MerchantEntity();
//        merchantEntity1.setActive(true);
//        merchantEntity1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
//        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantEntity1.setCreatedDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
//        merchantEntity1.setId(123L);
//        merchantEntity1.setName("Name");
//        merchantEntity1.setPassword("iloveyou");
//        merchantEntity1.setPayme(true);
//        merchantEntity1.setSecretKey("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
//        merchantEntity1.setUcell(true);
//        merchantEntity1.setUpdatedBy("2020-03-01");
//        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantEntity1.setUpdatedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
//        merchantEntity1.setUsername("janedoe");
//        merchantEntity1.setYandex(true);
//
//        MerchantServiceEntity merchantServiceEntity1 = new MerchantServiceEntity();
//        merchantServiceEntity1.setActive(true);
//        merchantServiceEntity1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
//        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantServiceEntity1.setCreatedDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
//        merchantServiceEntity1.setId(123L);
//        merchantServiceEntity1.setMerchantEntity(merchantEntity1);
//        merchantServiceEntity1.setMerchantServiceId(123L);
//        merchantServiceEntity1.setName("Name");
//        merchantServiceEntity1.setUpdatedBy("2020-03-01");
//        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantServiceEntity1.setUpdatedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
//        when(this.merchantServiceRepository.findByMerchantServiceId((Long) any())).thenReturn(merchantServiceEntity1);
//        assertTrue(this.osonServiceImp.add(123L));
//        verify(this.osonRepository).save((OsonServiceEntity) any());
//        verify(this.merchantServiceRepository).findByMerchantServiceId((Long) any());
//    }
//
//    /**
//     * Method under test: {@link OsonServiceImp#add(Long)}
//     */
//    @Test
//    void testAdd2() {
//        when(this.osonRepository.save((OsonServiceEntity) any())).thenThrow(new UsernameNotFoundException("Msg"));
//
//        MerchantEntity merchantEntity = new MerchantEntity();
//        merchantEntity.setActive(true);
//        merchantEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
//        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantEntity.setCreatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
//        merchantEntity.setId(123L);
//        merchantEntity.setName("Name");
//        merchantEntity.setPassword("iloveyou");
//        merchantEntity.setPayme(true);
//        merchantEntity.setSecretKey("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
//        merchantEntity.setUcell(true);
//        merchantEntity.setUpdatedBy("2020-03-01");
//        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantEntity.setUpdatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
//        merchantEntity.setUsername("janedoe");
//        merchantEntity.setYandex(true);
//
//        MerchantServiceEntity merchantServiceEntity = new MerchantServiceEntity();
//        merchantServiceEntity.setActive(true);
//        merchantServiceEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
//        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantServiceEntity.setCreatedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
//        merchantServiceEntity.setId(123L);
//        merchantServiceEntity.setMerchantEntity(merchantEntity);
//        merchantServiceEntity.setMerchantServiceId(123L);
//        merchantServiceEntity.setName("Name");
//        merchantServiceEntity.setUpdatedBy("2020-03-01");
//        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        merchantServiceEntity.setUpdatedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
//        when(this.merchantServiceRepository.findByMerchantServiceId((Long) any())).thenReturn(merchantServiceEntity);
//        assertThrows(UsernameNotFoundException.class, () -> this.osonServiceImp.add(123L));
//        verify(this.osonRepository).save((OsonServiceEntity) any());
//        verify(this.merchantServiceRepository).findByMerchantServiceId((Long) any());
//    }

    @AfterEach
    void tearDown() {
        merchantServiceRepository.deleteAll();
    }

    @Test
    void add() {
        assertThat(osonServiceImp).isNotNull();

        MerchantServiceEntity merchantServiceEntity = merchantServiceRepository.findByMerchantServiceId(100L);
        assertThat(merchantServiceEntity).isNotNull();
        assertThat(merchantServiceEntity.getName()).isEqualTo("merchant");

        Mockito.when(merchantServiceRepository.findByMerchantServiceId(200L)).thenReturn(null);

        UsernameNotFoundException aThrows = assertThrows(UsernameNotFoundException.class, () -> {
            osonServiceImp.add(200L);
        });
        String expectedMessage = "merchant service not found";
        String actualMessage = aThrows.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        Mockito.when(merchantServiceRepository.findByMerchantServiceId(any(Long.class))).thenReturn(notNull());
        assertTrue(osonServiceImp.add(any(Long.class)));

    }
}