package org.republica.easy.republicaeasy.services;

import org.republica.easy.republicaeasy.DTOS.UserResponseDto;
import org.republica.easy.republicaeasy.DTOS.UsuarioLinkadoRequestDto;
import org.republica.easy.republicaeasy.DTOS.UsuarioLinkadoResponseDto;
import org.republica.easy.republicaeasy.repositories.RepublicaRepository;
import org.republica.easy.republicaeasy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserRepService {
    private final UserRepository userRepository;
    private final RepublicaRepository republicaRepository;

    @Autowired
    public UserRepService(
            UserRepository userRepository,
            RepublicaRepository republicaRepository) {
        this.userRepository = userRepository;
        this.republicaRepository = republicaRepository;
    }

    public ResponseEntity<UsuarioLinkadoResponseDto> linkaUsuarioERepublica(
            UsuarioLinkadoRequestDto reqDto
    ) {
        var possiblyUsuario = userRepository.findUserByEmail(reqDto.email());
        var possiblyRepublica = republicaRepository.findById(reqDto.republicaId());

        if (reqDto.email() == null || reqDto.email().isBlank()) return ResponseEntity.status(400).body(
                new UsuarioLinkadoResponseDto("Campo email do usuário é obrigatório", 400, null)
        );

        if (reqDto.republicaId() == null || reqDto.republicaId().toString().isBlank()) return ResponseEntity.status(400).body(
                new UsuarioLinkadoResponseDto("Campo Id da república é obrigatório", 400, null)
        );

        if (possiblyUsuario.isEmpty()) return ResponseEntity.status(400).body(
                new UsuarioLinkadoResponseDto("Usuário não encotrado", 400, null)
        );

        if (possiblyRepublica.isEmpty()) return ResponseEntity.status(400).body(
                new UsuarioLinkadoResponseDto("República não encotrada", 400, null)
        );

        var republica = possiblyRepublica.get();
        republica.addUser(possiblyUsuario.get());
        var usuario = possiblyUsuario.get();
        UserResponseDto userDto = new UserResponseDto(usuario.getId(), usuario.getEmail(), usuario.getEmail(), republica.getId());
        var responseDto = new UsuarioLinkadoResponseDto("usuário adicionado à república com sucesso!", 200, userDto);
        republicaRepository.save(republica);
        return ResponseEntity.ok().body(responseDto);
    }
}
