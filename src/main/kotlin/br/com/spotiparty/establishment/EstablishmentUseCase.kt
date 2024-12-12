package br.com.spotiparty.establishment

import br.com.spotiparty.entity.Geolocation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.MathContext
import java.math.RoundingMode

@Service
class EstablishmentUseCase(
    private val establishmentRepository: EstablishmentRepository
) {

    @Transactional
    fun create(createEstablishmentDTO: CreateEstablishmentDTO): CreateEstablishmentResponseDTO =
        establishmentRepository.save(createEstablishmentDTO.let {
            Establishment(
                name = it.name,
                geoLocation = Geolocation(
                    it.latitude.round(MathContext(6, RoundingMode.HALF_UP)),
                    it.longitude.round(MathContext(6, RoundingMode.HALF_UP))
                )
            )
        }).let {
            CreateEstablishmentResponseDTO(
                id = it.id!!,
                name = it.name,
                latitude = it.geoLocation.latitude,
                longitude = it.geoLocation.longitude
            )
        }

}