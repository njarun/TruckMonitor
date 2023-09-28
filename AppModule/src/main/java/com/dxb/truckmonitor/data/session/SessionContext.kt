package com.dxb.truckmonitor.data.session

import javax.inject.Inject

class SessionContext @Inject constructor() {

    enum class FEED_SORT_ORDER {
        ASC, DSC
    }

    var feedSortOrder = FEED_SORT_ORDER.ASC
}