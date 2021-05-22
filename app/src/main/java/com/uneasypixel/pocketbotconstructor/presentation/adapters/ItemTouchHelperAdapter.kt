package com.uneasypixel.pocketbotconstructor.presentation.adapters

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int) : Boolean
    fun onItemDismiss(position: Int)
}