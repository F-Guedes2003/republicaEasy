package org.republica.easy.republicaeasy.DTOS;

import java.util.UUID;

public record UserResponseDto(String email, String name, UUID republicaId) {
}
