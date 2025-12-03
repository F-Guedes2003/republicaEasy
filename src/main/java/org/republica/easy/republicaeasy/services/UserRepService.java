package org.republica.easy.republicaeasy.services;

import org.republica.easy.republicaeasy.DTOS.RemoverMembroRequestDto;
import org.republica.easy.republicaeasy.DTOS.UserResponseDto;
import org.republica.easy.republicaeasy.DTOS.UsuarioLinkadoRequestDto;
import org.republica.easy.republicaeasy.DTOS.UsuarioLinkadoResponseDto;
import org.republica.easy.republicaeasy.Entities.Localization;
import org.republica.easy.republicaeasy.Entities.Republica;
import org.republica.easy.republicaeasy.Entities.User;
import org.republica.easy.republicaeasy.repositories.RepublicaRepository;
import org.republica.easy.republicaeasy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        if (reqDto.email() == null || reqDto.email().isBlank())
            return ResponseEntity.status(400).body(
                    new UsuarioLinkadoResponseDto("Campo email do usuário é obrigatório", 400, null)
            );

        if (reqDto.republicaId() == null)
            return ResponseEntity.status(400).body(
                    new UsuarioLinkadoResponseDto("Campo Id da república é obrigatório", 400, null)
            );

        if (possiblyUsuario.isEmpty())
            return ResponseEntity.status(400).body(
                    new UsuarioLinkadoResponseDto("Usuário não encontrado", 400, null)
            );

        if (possiblyRepublica.isEmpty())
            return ResponseEntity.status(400).body(
                    new UsuarioLinkadoResponseDto("República não encontrada", 400, null)
            );

        var republica = possiblyRepublica.get();
        var usuario = possiblyUsuario.get();

        if (republica.getLocalization() != null) {
            var loc = republica.getLocalization();

            Localization userLoc = new Localization();
            userLoc.setCity(loc.getCity());
            userLoc.setState(loc.getState());
            userLoc.setNeighborhood(loc.getNeighborhood());
            userLoc.setNumber(loc.getNumber());
            userLoc.setCep(loc.getCep());
            userLoc.setStreet(loc.getStreet());

            usuario.setLocalization(userLoc);
        }


        republica.addUser(usuario);

        userRepository.save(usuario);
        republicaRepository.save(republica);

        UserResponseDto userDto = new UserResponseDto(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getName(),
                republica.getId(),
                usuario.getLocalization()
        );

        return ResponseEntity.ok().body(
                new UsuarioLinkadoResponseDto(
                        "Usuário adicionado à república com sucesso!",
                        200,
                        userDto
                )
        );
    }

    public ResponseEntity<Void> removeMembro(RemoverMembroRequestDto reqDto) {

        User membro = userRepository.findUserByEmail(reqDto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        UUID id = membro.getRepublica().getId();

        Republica rep = republicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("República não encontrada"));

        rep.removeUser(membro);
        republicaRepository.save(rep);
        userRepository.save(membro);

        return ResponseEntity.noContent().build();
    }
}
