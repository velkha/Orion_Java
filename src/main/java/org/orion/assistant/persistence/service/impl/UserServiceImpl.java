package org.orion.assistant.persistence.service.impl;

import org.orion.assistant.persistence.service.UserService;
import org.modelmapper.ModelMapper;
import org.orion.assistant.persistence.dto.UserDTO;
import org.orion.assistant.persistence.model.User;
import org.orion.assistant.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return convertToDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return convertToDTO(user);
    }

    @Override
    public UserDTO getUserBySessionId(String sessionId) {
        User user = userRepository.findBySessionId(sessionId);
        return convertToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return convertToDTOList(users);
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private List<UserDTO> convertToDTOList(List<User> users) {
        return users.stream()
            .map(user -> modelMapper.map(user, UserDTO.class))
            .collect(Collectors.toList());
    }
}