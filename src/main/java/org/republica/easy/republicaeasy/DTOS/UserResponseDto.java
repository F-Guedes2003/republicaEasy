package org.republica.easy.republicaeasy.DTOS;

import org.republica.easy.republicaeasy.Entities.Localization;

import java.util.UUID;

public record UserResponseDto(UUID id, String email, String name, UUID republicaId, Localization localization) {
}
