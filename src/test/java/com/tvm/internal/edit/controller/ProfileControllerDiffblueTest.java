package com.tvm.internal.edit.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvm.internal.edit.model.Gender;
import com.tvm.internal.edit.model.MaritalStatus;
import com.tvm.internal.edit.model.Profile;
import com.tvm.internal.edit.service.ProfileService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProfileController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProfileControllerDiffblueTest {
    @Autowired
    private ProfileController profileController;

    @MockBean
    private ProfileService profileService;

    /**
     * Test {@link ProfileController#createProfile(Profile)}.
     * <p>
     * Method under test: {@link ProfileController#createProfile(Profile)}
     */
    @Test
    @DisplayName("Test createProfile with multipart data")
    void testCreateProfile() throws Exception {
        // Arrange
        Profile profile = new Profile();
        profile.setCity("Oxford");
        profile.setCountry("GB");
        profile.setFirstname("Jane");
        profile.setLastname("Doe");
        profile.setEmail("jane.doe@example.org");
        profile.setEmployeephoto("Employee Photo");

        String profileJson = new ObjectMapper().writeValueAsString(profile);

        MockMultipartFile file = new MockMultipartFile("employeePhoto", "photo.jpg", MediaType.IMAGE_JPEG_VALUE, "test-image".getBytes());

        when(profileService.createProfile(Mockito.any(Profile.class))).thenReturn(profile);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/profile").file(file).param("profile", profileJson)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }


    /**
     * Test {@link ProfileController#getAllProfiles()}.
     * <p>
     * Method under test: {@link ProfileController#getAllProfiles()}
     */
    @Test
    @DisplayName("Test getAllProfiles()")
    void testGetAllProfiles() throws Exception {
        // Arrange
        when(profileService.getAllProfiles()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/profile");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(profileController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link ProfileController#getProfileById(Long)}.
     * <p>
     * Method under test: {@link ProfileController#getProfileById(Long)}
     */
    @Test
    @DisplayName("Test getProfileById(Long)")
    void testGetProfileById() throws Exception {
        // Arrange
        Profile profile = new Profile();
        profile.setRead(true);
        profile.setSpeak(true);
        profile.setWrite(true);
        profile.setCity("Oxford");
        profile.setCountry("GB");
        profile.setDistrict("District");
        profile.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        profile.setEmail("jane.doe@example.org");
        profile.setEmployeephoto(Arrays.toString("AXAXAXAX".getBytes("UTF-8")));
        profile.setFirstname("Jane");
        profile.setGender(Gender.MALE);
        profile.setHomeNumber(1L);
        profile.setId(1L);
        profile.setLandmark("Landmark");
        profile.setLanguage("en");
        profile.setLastname("Doe");
        profile.setLocality("Locality");
        profile.setMaritalStatus(MaritalStatus.SINGLE);
        profile.setMobileNumber(1L);
        profile.setOfficeNumber(1L);
        profile.setPassport("Passport");
        profile.setPincode(1);
        profile.setPosition("Position");
        profile.setPresentAddress("42 Main St");
        profile.setState("MD");
        profile.setAge(1);
        profile.setName("Name");
        profile.setOccupation("Too");
        profile.setRelationship("127.0.0.1");
        profile.setVisa("Visa");
        when(profileService.getProfileById(Mockito.<Long>any())).thenReturn(profile);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/profile/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(profileController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("Test updateProfile with multipart data")
    void testUpdateProfile() throws Exception {
        // Arrange
        Profile profile = new Profile();
        profile.setCity("Oxford");
        profile.setCountry("GB");
        profile.setFirstname("Jane");
        profile.setLastname("Doe");
        profile.setEmail("jane.doe@example.org");
        profile.setEmployeephoto("Employee Photo");

        String profileJson = new ObjectMapper().writeValueAsString(profile);

        MockMultipartFile file = new MockMultipartFile("employeePhoto", "photo.jpg", MediaType.IMAGE_JPEG_VALUE, "test-image".getBytes());

        when(profileService.updateProfile(Mockito.anyLong(), Mockito.any(Profile.class))).thenReturn(profile);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/profile/{id}", 1L).file(file).param("profile", profileJson).with(request -> {
            request.setMethod("PUT"); // Multipart automatically sets POST, override here
            return request;
        })).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Test {@link ProfileController#deleteProfile(Long)}.
     * <ul>
     *   <li>Given {@code /api/profile/{id}}.</li>
     *   <li>When formLogin.</li>
     *   <li>Then status {@link StatusResultMatchers#isNotFound()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProfileController#deleteProfile(Long)}
     */
    @Test
    @DisplayName("Test deleteProfile(Long); given '/api/profile/{id}'; when formLogin; then status isNotFound()")
    void testDeleteProfile_givenApiProfileId_whenFormLogin_thenStatusIsNotFound() throws Exception {
        // Arrange
        when(profileService.deleteProfile(Mockito.<Long>any())).thenReturn(true);
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(profileController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Test {@link ProfileController#deleteProfile(Long)}.
     * <ul>
     *   <li>Given {@link ProfileService} {@link ProfileService#deleteProfile(Long)}
     * return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProfileController#deleteProfile(Long)}
     */
    @Test
    @DisplayName("Test deleteProfile(Long); given ProfileService deleteProfile(Long) return 'false'")
    void testDeleteProfile_givenProfileServiceDeleteProfileReturnFalse() throws Exception {
        // Arrange
        when(profileService.deleteProfile(Mockito.<Long>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/profile/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(profileController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Test {@link ProfileController#deleteProfile(Long)}.
     * <ul>
     *   <li>Given {@link ProfileService} {@link ProfileService#deleteProfile(Long)}
     * return {@code true}.</li>
     *   <li>Then status {@link StatusResultMatchers#isOk()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProfileController#deleteProfile(Long)}
     */
    @Test
    @DisplayName("Test deleteProfile(Long); given ProfileService deleteProfile(Long) return 'true'; then status isOk()")
    void testDeleteProfile_givenProfileServiceDeleteProfileReturnTrue_thenStatusIsOk() throws Exception {
        // Arrange
        when(profileService.deleteProfile(Mockito.<Long>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/profile/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(profileController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1")).andExpect(MockMvcResultMatchers.content().string("Deleted Successfully!!!"));
    }
}
