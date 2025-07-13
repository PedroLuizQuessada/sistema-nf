package com.example.sistemanf.mappers;

import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.entities.Requester;

public class RequesterMapper {

    private RequesterMapper(){}

    public static Requester getEntidade(RequesterDto requesterDto) {
        return new Requester(requesterDto.tipo(), requesterDto.email());
    }

    public static RequesterDto getDto(Requester requester) {
        return new RequesterDto(requester.getTipo(), requester.getEmail());
    }
}
