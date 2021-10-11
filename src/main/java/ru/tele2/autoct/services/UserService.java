package ru.tele2.autoct.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tele2.autoct.dto.security.UserDto;
import ru.tele2.autoct.jpa.entity.security.UserEntity;
import ru.tele2.autoct.jpa.repository.security.RoleRepository;
import ru.tele2.autoct.jpa.repository.security.UserRepository;
import ru.tele2.autoct.mappers.security.UserMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    UserRepository userRepository;

    RoleRepository roleRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    UserMapper userMapper;

    @Override
    @Transactional
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        UserDto userDto = new UserDto();
        return userMapper.convert(user);
    }

    public List<UserEntity> allUsers() {
        return userRepository.findAll();
    }

    public String getPasswordByUsername (String username){
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userEntity.getPassword();
    }

    public boolean changePassword (UserDto userDto, String newPassword){
        UserEntity updatedUser = userMapper.convert(userDto);
        updatedUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(updatedUser);
        return true;
    }

}
