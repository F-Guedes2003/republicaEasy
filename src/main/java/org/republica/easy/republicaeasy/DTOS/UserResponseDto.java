package org.republica.easy.republicaeasy.DTOS;

import java.util.UUID;

public record UserResponseDto(UUID id, String email, String name, UUID republicaId) {
}
