package br.com.catolica.user.services;

import br.com.catolica.user.domains.StandardUser;
import br.com.catolica.user.dto.UserDTO;
import br.com.catolica.user.mapper.UserMapper;
import br.com.catolica.user.repositories.UserRespository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRespository userRespository;
    private final UserMapper userMapper;

    public List<StandardUser> getAll() {
        return userRespository.findAll();
    }

    public Optional<StandardUser> getUserById(Long id) {
        return userRespository.findById(id);
    }

    public StandardUser save(UserDTO userDTO) {
        Optional.of(userDTO.getId()).ifPresent(ex -> {
            throw new IllegalArgumentException("O id deve ser nulo");
        });

        return userRespository.save(userMapper.dtoToEntity(userDTO));
    }

    public void deleteUser(Long id) {
        userRespository.deleteById(id);
    }
}
