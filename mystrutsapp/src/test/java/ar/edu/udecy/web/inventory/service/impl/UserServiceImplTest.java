package ar.edu.udecy.web.inventory.service.impl;

import ar.edu.udecy.web.inventory.dto.UserRequestDTO;
import ar.edu.udecy.web.inventory.dto.UserResponseDTO;
import ar.edu.udecy.web.inventory.entity.UserEntity;
import ar.edu.udecy.web.inventory.handler.exception.UserNotFoundException;
import ar.edu.udecy.web.inventory.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldCreateUserSuccessfully() {
        UserRequestDTO requestDTO = new UserRequestDTO("testUser", "password123", Set.of("ROLE_USER"));
        UserEntity savedEntity = new UserEntity(1L, "testUser", "password123", Set.of("ROLE_USER"));

        when(userRepository.save(any(UserEntity.class))).thenReturn(savedEntity);

        UserResponseDTO result = userService.createUser(requestDTO);

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals(Set.of("ROLE_USER"), result.getRoles());
    }

    @Test
    void shouldReturnUserById() {
        UserEntity userEntity = new UserEntity(1L, "testUser", "password123", Set.of("ROLE_USER"));

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        UserResponseDTO result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals(Set.of("ROLE_USER"), result.getRoles());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundById() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void shouldReturnAllUsers() {
        List<UserEntity> users = List.of(
                new UserEntity(1L, "user1", "password1", Set.of("ROLE_USER")),
                new UserEntity(2L, "user2", "password2", Set.of("ROLE_ADMIN"))
        );

        when(userRepository.findAll()).thenReturn(users);

        List<UserResponseDTO> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        UserEntity existingUser = new UserEntity(1L, "oldUser", "oldPassword", Set.of("ROLE_USER"));
        UserRequestDTO requestDTO = new UserRequestDTO("updatedUser", "newPassword", Set.of("ROLE_ADMIN"));
        UserEntity updatedUser = new UserEntity(1L, "updatedUser", "newPassword", Set.of("ROLE_ADMIN"));

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(updatedUser);

        UserResponseDTO result = userService.updateUser(1L, requestDTO);

        assertNotNull(result);
        assertEquals("updatedUser", result.getUsername());
        assertEquals(Set.of("ROLE_ADMIN"), result.getRoles());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentUser() {
        UserRequestDTO requestDTO = new UserRequestDTO("updatedUser", "newPassword", Set.of("ROLE_ADMIN"));

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, requestDTO));
    }

    @Test
    void shouldDeleteUserSuccessfully() {
        when(userRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> userService.deleteUser(1L));
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentUser() {
        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
    }
}