package com.uneasypixel.pocketbotconstructor.Presentation.Adapters

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int) : Boolean
    fun onItemDismiss(position: Int)
}