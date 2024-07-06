package com.example.beerapp.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import com.example.beerapp.data.local.BeerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    pager: Pager<Int, BeerEntity>
): ViewModel() {
}