package com.tvm.internal.edit.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tvm.internal.edit.model.Gender;
import com.tvm.internal.edit.model.MaritalStatus;
import com.tvm.internal.edit.model.Profile;
import com.tvm.internal.edit.repo.ProfileRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProfileServiceimpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProfileServiceimplDiffblueTest {
    @MockBean
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileServiceimpl profileServiceimpl;

    /**
     * Test {@link ProfileServiceimpl#createProfile(Profile)}.
     * <p>
     * Method under test: {@link ProfileServiceimpl#createProfile(Profile)}
     */
    @Test
    @DisplayName("Test createProfile(Profile)")
    void testCreateProfile() throws UnsupportedEncodingException {
        // Arrange
        Profile profile = new Profile();
        profile.setCanRead(true);
        profile.setCanSpeak(true);
        profile.setCanWrite(true);
        profile.setCity("Oxford");
        profile.setCountry("GB");
        profile.setDistrict("District");
        profile.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        profile.setEmail("jane.doe@example.org");
        profile.setEmployeePhoto("AXAXAXAX".getBytes("UTF-8"));
        profile.setFirstName("Jane");
        profile.setGender(Gender.MALE);
        profile.setHomeNumber(1L);
        profile.setId(1L);
        profile.setLandmark("Landmark");
        profile.setLanguage("en");
        profile.setLastName("Doe");
        profile.setLocality("Locality");
        profile.setMaritalStatus(MaritalStatus.SINGLE);
        profile.setMobileNumber(1L);
        profile.setOfficeNumber(1L);
        profile.setPassport("Passport");
        profile.setPincode(1);
        profile.setPosition("Position");
        profile.setPresentAddress("42 Main St");
        profile.setState("MD");
        profile.setTage(1);
        profile.setTname("Tname");
        profile.setToo("Too");
        profile.setTrelationship("127.0.0.1");
        profile.setVisa("Visa");
        when(profileRepository.save(Mockito.<Profile>any())).thenReturn(profile);

        Profile profile2 = new Profile();
        profile2.setCanRead(true);
        profile2.setCanSpeak(true);
        profile2.setCanWrite(true);
        profile2.setCity("Oxford");
        profile2.setCountry("GB");
        profile2.setDistrict("District");
        profile2.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        profile2.setEmail("jane.doe@example.org");
        profile2.setEmployeePhoto("AXAXAXAX".getBytes("UTF-8"));
        profile2.setFirstName("Jane");
        profile2.setGender(Gender.MALE);
        profile2.setHomeNumber(1L);
        profile2.setId(1L);
        profile2.setLandmark("Landmark");
        profile2.setLanguage("en");
        profile2.setLastName("Doe");
        profile2.setLocality("Locality");
        profile2.setMaritalStatus(MaritalStatus.SINGLE);
        profile2.setMobileNumber(1L);
        profile2.setOfficeNumber(1L);
        profile2.setPassport("Passport");
        profile2.setPincode(1);
        profile2.setPosition("Position");
        profile2.setPresentAddress("42 Main St");
        profile2.setState("MD");
        profile2.setTage(1);
        profile2.setTname("Tname");
        profile2.setToo("Too");
        profile2.setTrelationship("127.0.0.1");
        profile2.setVisa("Visa");

        // Act
        Profile actualCreateProfileResult = profileServiceimpl.createProfile(profile2);

        // Assert
        verify(profileRepository).save(isA(Profile.class));
        assertSame(profile, actualCreateProfileResult);
    }

    /**
     * Test {@link ProfileServiceimpl#getProfileById(Long)}.
     * <ul>
     *   <li>Given {@link Profile} (default constructor) CanRead is {@code true}.</li>
     *   <li>Then return {@link Profile} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test: {@link ProfileServiceimpl#getProfileById(Long)}
     */
    @Test
    @DisplayName("Test getProfileById(Long); given Profile (default constructor) CanRead is 'true'; then return Profile (default constructor)")
    void testGetProfileById_givenProfileCanReadIsTrue_thenReturnProfile() throws UnsupportedEncodingException {
        // Arrange
        Profile profile = new Profile();
        profile.setCanRead(true);
        profile.setCanSpeak(true);
        profile.setCanWrite(true);
        profile.setCity("Oxford");
        profile.setCountry("GB");
        profile.setDistrict("District");
        profile.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        profile.setEmail("jane.doe@example.org");
        profile.setEmployeePhoto("AXAXAXAX".getBytes("UTF-8"));
        profile.setFirstName("Jane");
        profile.setGender(Gender.MALE);
        profile.setHomeNumber(1L);
        profile.setId(1L);
        profile.setLandmark("Landmark");
        profile.setLanguage("en");
        profile.setLastName("Doe");
        profile.setLocality("Locality");
        profile.setMaritalStatus(MaritalStatus.SINGLE);
        profile.setMobileNumber(1L);
        profile.setOfficeNumber(1L);
        profile.setPassport("Passport");
        profile.setPincode(1);
        profile.setPosition("Position");
        profile.setPresentAddress("42 Main St");
        profile.setState("MD");
        profile.setTage(1);
        profile.setTname("Tname");
        profile.setToo("Too");
        profile.setTrelationship("127.0.0.1");
        profile.setVisa("Visa");
        Optional<Profile> ofResult = Optional.of(profile);
        when(profileRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Profile actualProfileById = profileServiceimpl.getProfileById(1L);

        // Assert
        verify(profileRepository).findById(eq(1L));
        assertSame(profile, actualProfileById);
    }

    /**
     * Test {@link ProfileServiceimpl#getProfileById(Long)}.
     * <ul>
     *   <li>Given {@link ProfileRepository} {@link CrudRepository#findById(Object)}
     * return empty.</li>
     *   <li>Then return {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProfileServiceimpl#getProfileById(Long)}
     */
    @Test
    @DisplayName("Test getProfileById(Long); given ProfileRepository findById(Object) return empty; then return 'null'")
    void testGetProfileById_givenProfileRepositoryFindByIdReturnEmpty_thenReturnNull() {
        // Arrange
        Optional<Profile> emptyResult = Optional.empty();
        when(profileRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act
        Profile actualProfileById = profileServiceimpl.getProfileById(1L);

        // Assert
        verify(profileRepository).findById(eq(1L));
        assertNull(actualProfileById);
    }

    /**
     * Test {@link ProfileServiceimpl#getAllProfiles()}.
     * <p>
     * Method under test: {@link ProfileServiceimpl#getAllProfiles()}
     */
    @Test
    @DisplayName("Test getAllProfiles()")
    void testGetAllProfiles() {
        // Arrange
        when(profileRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Profile> actualAllProfiles = profileServiceimpl.getAllProfiles();

        // Assert
        verify(profileRepository).findAll();
        assertTrue(actualAllProfiles.isEmpty());
    }

    /**
     * Test {@link ProfileServiceimpl#updateProfile(Long, Profile)}.
     * <p>
     * Method under test: {@link ProfileServiceimpl#updateProfile(Long, Profile)}
     */
    @Test
    @DisplayName("Test updateProfile(Long, Profile)")
    void testUpdateProfile() throws UnsupportedEncodingException {
        // Arrange
        Profile profile = new Profile();
        profile.setCanRead(true);
        profile.setCanSpeak(true);
        profile.setCanWrite(true);
        profile.setCity("Oxford");
        profile.setCountry("GB");
        profile.setDistrict("District");
        profile.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        profile.setEmail("jane.doe@example.org");
        profile.setEmployeePhoto("AXAXAXAX".getBytes("UTF-8"));
        profile.setFirstName("Jane");
        profile.setGender(Gender.MALE);
        profile.setHomeNumber(1L);
        profile.setId(1L);
        profile.setLandmark("Landmark");
        profile.setLanguage("en");
        profile.setLastName("Doe");
        profile.setLocality("Locality");
        profile.setMaritalStatus(MaritalStatus.SINGLE);
        profile.setMobileNumber(1L);
        profile.setOfficeNumber(1L);
        profile.setPassport("Passport");
        profile.setPincode(1);
        profile.setPosition("Position");
        profile.setPresentAddress("42 Main St");
        profile.setState("MD");
        profile.setTage(1);
        profile.setTname("Tname");
        profile.setToo("Too");
        profile.setTrelationship("127.0.0.1");
        profile.setVisa("Visa");
        Optional<Profile> ofResult = Optional.of(profile);

        Profile profile2 = new Profile();
        profile2.setCanRead(true);
        profile2.setCanSpeak(true);
        profile2.setCanWrite(true);
        profile2.setCity("Oxford");
        profile2.setCountry("GB");
        profile2.setDistrict("District");
        profile2.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        profile2.setEmail("jane.doe@example.org");
        profile2.setEmployeePhoto("AXAXAXAX".getBytes("UTF-8"));
        profile2.setFirstName("Jane");
        profile2.setGender(Gender.MALE);
        profile2.setHomeNumber(1L);
        profile2.setId(1L);
        profile2.setLandmark("Landmark");
        profile2.setLanguage("en");
        profile2.setLastName("Doe");
        profile2.setLocality("Locality");
        profile2.setMaritalStatus(MaritalStatus.SINGLE);
        profile2.setMobileNumber(1L);
        profile2.setOfficeNumber(1L);
        profile2.setPassport("Passport");
        profile2.setPincode(1);
        profile2.setPosition("Position");
        profile2.setPresentAddress("42 Main St");
        profile2.setState("MD");
        profile2.setTage(1);
        profile2.setTname("Tname");
        profile2.setToo("Too");
        profile2.setTrelationship("127.0.0.1");
        profile2.setVisa("Visa");
        when(profileRepository.save(Mockito.<Profile>any())).thenReturn(profile2);
        when(profileRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Profile profile3 = new Profile();
        profile3.setCanRead(true);
        profile3.setCanSpeak(true);
        profile3.setCanWrite(true);
        profile3.setCity("Oxford");
        profile3.setCountry("GB");
        profile3.setDistrict("District");
        profile3.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        profile3.setEmail("jane.doe@example.org");
        profile3.setEmployeePhoto("AXAXAXAX".getBytes("UTF-8"));
        profile3.setFirstName("Jane");
        profile3.setGender(Gender.MALE);
        profile3.setHomeNumber(1L);
        profile3.setId(1L);
        profile3.setLandmark("Landmark");
        profile3.setLanguage("en");
        profile3.setLastName("Doe");
        profile3.setLocality("Locality");
        profile3.setMaritalStatus(MaritalStatus.SINGLE);
        profile3.setMobileNumber(1L);
        profile3.setOfficeNumber(1L);
        profile3.setPassport("Passport");
        profile3.setPincode(1);
        profile3.setPosition("Position");
        profile3.setPresentAddress("42 Main St");
        profile3.setState("MD");
        profile3.setTage(1);
        profile3.setTname("Tname");
        profile3.setToo("Too");
        profile3.setTrelationship("127.0.0.1");
        profile3.setVisa("Visa");

        // Act
        Profile actualUpdateProfileResult = profileServiceimpl.updateProfile(1L, profile3);

        // Assert
        verify(profileRepository).findById(eq(1L));
        verify(profileRepository).save(isA(Profile.class));
        assertSame(profile2, actualUpdateProfileResult);
    }

    /**
     * Test {@link ProfileServiceimpl#deleteProfile(Long)}.
     * <ul>
     *   <li>Given {@link ProfileRepository} {@link CrudRepository#deleteById(Object)}
     * does nothing.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProfileServiceimpl#deleteProfile(Long)}
     */
    @Test
    @DisplayName("Test deleteProfile(Long); given ProfileRepository deleteById(Object) does nothing; then return 'true'")
    void testDeleteProfile_givenProfileRepositoryDeleteByIdDoesNothing_thenReturnTrue() {
        // Arrange
        doNothing().when(profileRepository).deleteById(Mockito.<Long>any());
        when(profileRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act
        boolean actualDeleteProfileResult = profileServiceimpl.deleteProfile(1L);

        // Assert
        verify(profileRepository).deleteById(eq(1L));
        verify(profileRepository).existsById(eq(1L));
        assertTrue(actualDeleteProfileResult);
    }

    /**
     * Test {@link ProfileServiceimpl#deleteProfile(Long)}.
     * <ul>
     *   <li>Given {@link ProfileRepository} {@link CrudRepository#existsById(Object)}
     * return {@code false}.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProfileServiceimpl#deleteProfile(Long)}
     */
    @Test
    @DisplayName("Test deleteProfile(Long); given ProfileRepository existsById(Object) return 'false'; then return 'false'")
    void testDeleteProfile_givenProfileRepositoryExistsByIdReturnFalse_thenReturnFalse() {
        // Arrange
        when(profileRepository.existsById(Mockito.<Long>any())).thenReturn(false);

        // Act
        boolean actualDeleteProfileResult = profileServiceimpl.deleteProfile(1L);

        // Assert
        verify(profileRepository).existsById(eq(1L));
        assertFalse(actualDeleteProfileResult);
    }
}
