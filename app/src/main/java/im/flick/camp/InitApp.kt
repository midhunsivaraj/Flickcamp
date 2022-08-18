package im.flick.camp

import android.app.Application
import im.flick.camp.helpers.RoomDisplayNameFallbackProviderImpl
import org.matrix.android.sdk.api.Matrix
import org.matrix.android.sdk.api.MatrixConfiguration

class InitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // You should first init Matrix before using it
        Matrix.initialize(
            context = this,
            matrixConfiguration = MatrixConfiguration(
                roomDisplayNameFallbackProvider = RoomDisplayNameFallbackProviderImpl()
            )
        )
        val matrix = Matrix.getInstance(this)
        val lastSession = matrix.authenticationService().getLastAuthenticatedSession()
        if (lastSession != null) {
            SessionHolder.currentSession = lastSession

            lastSession.open()
            lastSession.startSync(true)
        }
    }
}
