package com.pdm.rankeuca.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.pdm.rankeuca.data.local.entities.OptionEntity
import com.pdm.rankeuca.data.local.entities.QuestionEntity
import com.pdm.rankeuca.domain.models.Question

data class QuestionWithOptions(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val options: List<OptionEntity>
)

fun QuestionWithOptions.toModel(): Question {
    return Question(
        id = question.id,
        title = question.title,
        optionCount = options.size,
    )
}
