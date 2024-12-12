package br.com.spotiparty.queue

import br.com.spotiparty.establishment.player.PlayerType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("v1/establishment/{id}/queue")
internal class MusicQueueController(
    private val musicQueueUseCase: MusicQueueUseCase
) {

    @GetMapping
    fun getQueue(
        @PathVariable("id") establishmentId: UUID,
        @RequestParam("playerType") playerType: PlayerType
    ): Any = musicQueueUseCase.getQueue(establishmentId, playerType)

}
