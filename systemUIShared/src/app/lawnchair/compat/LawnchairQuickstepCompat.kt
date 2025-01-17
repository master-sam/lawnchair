package app.lawnchair.compat

import android.os.Build
import app.lawnchair.compatlib.ActivityManagerCompat
import app.lawnchair.compatlib.ActivityOptionsCompat
import app.lawnchair.compatlib.QuickstepCompatFactory
import app.lawnchair.compatlib.RemoteTransitionCompat
import app.lawnchair.compatlib.eleven.QuickstepCompatFactoryVR
import app.lawnchair.compatlib.fourteen.QuickstepCompatFactoryVU
import app.lawnchair.compatlib.ten.QuickstepCompatFactoryVQ
import app.lawnchair.compatlib.thirteen.QuickstepCompatFactoryVT
import app.lawnchair.compatlib.twelve.QuickstepCompatFactoryVS

object LawnchairQuickstepCompat {

    @JvmField
    val ATLEAST_Q: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    @JvmField
    val ATLEAST_R: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    @JvmField
    val ATLEAST_S: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    @JvmField
    val ATLEAST_T: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

    @JvmField
    val ATLEAST_U: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE


    @JvmStatic
    val factory: QuickstepCompatFactory = if (ATLEAST_U) {
        QuickstepCompatFactoryVU()
    } else if (ATLEAST_T) {
        QuickstepCompatFactoryVT()
    } else if (ATLEAST_S) {
        QuickstepCompatFactoryVS()
    } else if (ATLEAST_R) {
        QuickstepCompatFactoryVR()
    } else {
        QuickstepCompatFactoryVQ()
    }

    @JvmStatic
    val activityManagerCompat: ActivityManagerCompat = factory.activityManagerCompat

    @JvmStatic
    val activityOptionsCompat: ActivityOptionsCompat = factory.activityOptionsCompat

    @JvmStatic
    val remoteTransitionCompat: RemoteTransitionCompat = factory.remoteTransitionCompat
}