package br.com.spotiparty.establishment.exception

import br.com.spotiparty.controller.NotFoundException

class EstablishmentNotFoundException(
    message: String
) : NotFoundException(message)