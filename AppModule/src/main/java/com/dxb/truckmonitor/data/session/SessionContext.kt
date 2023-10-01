package com.dxb.truckmonitor.data.session

import javax.inject.Inject

class SessionContext @Inject constructor() {

    enum class FEED_SORT_ORDER {
        ASC, DSC
    }

    var feedSortOrder = FEED_SORT_ORDER.ASC

    fun updateFeedSortOrder() {
        feedSortOrder = if(feedSortOrder == FEED_SORT_ORDER.ASC)
            FEED_SORT_ORDER.DSC
        else FEED_SORT_ORDER.ASC
    }
}