package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.enums.TipoUsuarioEnum;

public interface RequesterDataSource {
    RequesterDto getRequester(TipoUsuarioEnum tipo, String email);
}
