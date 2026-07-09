package com.pdm.rankeuca.data.remote.dto

import com.pdm.rankeuca.domain.models.Option
import com.pdm.rankeuca.domain.models.Question
import com.pdm.rankeuca.domain.models.Questionary
import kotlinx.serialization.Serializable

/* Respuesta de la API
[
    {
        "id": 30,
        "text": "Mejor lugar para parquearse",
        "options": [
            {
                "id": 53,
                "value": "Parqueo techado",
                "votes": 2
            },
            {
                "id": 55,
                "value": "Parqueo del anexo",
                "votes": 0
            },
            {
                "id": 56,
                "value": "Parqueo de las A y B",
                "votes": 0
            }
        ]
    }
]*/

@Serializable
data class QuestionaryDto(
    val id: Int,
    val text: String,
    val options: List<RemoteOptionDto>
)

@Serializable
data class RemoteOptionDto(
    val id: Int,
    val value: String,
    val votes: Int
)


fun RemoteOptionDto.toModel(questionId: Int) = Option(
    id = id,
    value = value,
    votes = votes,
    questionId = questionId
)

fun QuestionaryDto.toModel(): Questionary {
    val question = Question(
        id = id,
        title = text,
        optionCount = options.size
    )

    return Questionary(
        question = question,
        options = options.map { it.toModel(questionId = id) }
    )
}