package com.example.unitconverter.viewmodels

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitconverter.R

class SpeedViewModel : ViewModel() {
    private val _unit: MutableLiveData<Int> = MutableLiveData(R.string.kilometersPerHour)

    val unit: LiveData<Int>
        get() = _unit

    fun setUnit(value: Int) {
        _unit.value = value
    }

    private val _speed: MutableLiveData<String> = MutableLiveData("")

    val speed: LiveData<String>
        get() = _speed

    fun getSpeedAsFloat(): Float = (_speed.value ?: "").let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }

    fun setSpeed(value: String) {
        _speed.value = value
    }

    fun convert() = getSpeedAsFloat().let {
        if (!it.isNaN())
            if (_unit.value == R.string.MilePerHour)
                it * 1.60934F
            else
                it / 1.60934F
        else
            Float.NaN
    }
}