package com.uneasypixel.pocketbotconstructor.presentation.adapters

interface IRecyclerViewClickListener {

    fun recyclerViewListClicked(position: Int)
    fun recyclerViewListAdd(position: Int)
    fun recyclerViewListDelete(position: Int)
    fun recyclerViewListMove(fromPosition: Int, toPosition: Int)
}