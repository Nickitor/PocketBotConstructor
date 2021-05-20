package com.uneasypixel.pocketbotconstructor.Presentation.Adapters

interface IRecyclerViewClickListener {

    fun recyclerViewListClicked(position: Int)
    fun recyclerViewListAdd(position: Int)
    fun recyclerViewListDelete(position: Int)
    fun recyclerViewListMove(fromPosition: Int, toPosition: Int)
}