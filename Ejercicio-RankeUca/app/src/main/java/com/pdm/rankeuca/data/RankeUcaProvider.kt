package com.pdm.rankeuca.data

import android.content.Context
import com.pdm.rankeuca.data.local.RankeUcaDatabase
import com.pdm.rankeuca.data.remote.KtorClient
import com.pdm.rankeuca.data.repositories.OptionRepositoryImpl
import com.pdm.rankeuca.data.repositories.QuestionRepositoryImpl
import com.pdm.rankeuca.domain.repositories.OptionRepository
import com.pdm.rankeuca.domain.repositories.QuestionRepository

class AppProvider(context: Context) {

    private val appDatabase = RankeUcaDatabase.getDatabase(context)
    private val optionDao = appDatabase.optionDao()
    private val questionDao = appDatabase.questionDao()
    private val ktorClient = KtorClient()


    private val optionRepository: OptionRepository =
        OptionRepositoryImpl(optionDao, ktorClient)

    private val questionRepository: QuestionRepository =
        QuestionRepositoryImpl(questionDao)

    fun provideOptionRepository(): OptionRepository {
        return optionRepository
    }

    fun provideQuestionRepository(): QuestionRepository {
        return questionRepository
    }
}