package com.danilkharytonov.retrofitroomrepository.domain.mapper

interface Mapper<Domain, Network> {
    fun mapToNetwork(domain: Domain) : Network
    fun mapToDomain(network: Network) : Domain
}