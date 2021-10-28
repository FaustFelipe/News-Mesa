package br.com.felipefaustini.mesanews.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class PairMediatorLiveData<F, S>(a: LiveData<F>, b: LiveData<S>) : MediatorLiveData<Pair<F?, S?>>() {
    init {
        addSource(a) { value = it to b.value }
        addSource(b) { value = a.value to it }
    }
}