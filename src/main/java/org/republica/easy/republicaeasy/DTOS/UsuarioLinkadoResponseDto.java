package org.republica.easy.republicaeasy.DTOS;

import org.republica.easy.republicaeasy.Entities.User;

public record UsuarioLinkadoResponseDto(String message, int status, UserResponseDto data) {
}
