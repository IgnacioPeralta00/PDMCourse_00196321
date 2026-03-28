package com.fipruno.lemonade

class LemonadeState(var imageResource: Int, var stringResource: Int) {
    // metodos set y get
    fun setImageResource(imageResource: Int) {
        this.imageResource = imageResource
    }
    fun getImageResource(): Int {
        return imageResource
    }
    fun setStringResource(stringResource: Int) {
        this.stringResource = stringResource
    }
    fun getStringResource(): Int {
        return stringResource
    }
}