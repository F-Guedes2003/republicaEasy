package org.republica.easy.republicaeasy.DTOS;

import org.republica.easy.republicaeasy.Entities.Republica;

public record RepublicaCriadaResponseDto(String message, int status, Republica data) {
}
