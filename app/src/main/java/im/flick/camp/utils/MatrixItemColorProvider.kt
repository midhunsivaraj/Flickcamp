package im.flick.camp.utils

import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.VisibleForTesting
import kotlin.math.abs
import org.matrix.android.sdk.api.util.MatrixItem
import im.flick.camp.R

class MatrixItemColorProvider(private val resources: Resources) {
    private val cache = mutableMapOf<String, Int>()

    @ColorInt
    fun getColor(matrixItem: MatrixItem): Int {
        return cache.getOrPut(matrixItem.id) {
            resources.getColor(
                when (matrixItem) {
                    is MatrixItem.UserItem -> getColorFromUserId(matrixItem.id)
                    else -> getColorFromRoomId(matrixItem.id)
                }
            )
        }
    }

    companion object {
        @ColorRes
        @VisibleForTesting
        fun getColorFromUserId(userId: String?): Int {
            var hash = 0

            userId?.toList()?.map { chr -> hash = (hash shl 5) - hash + chr.toInt() }

            return when (abs(hash) % 8) {
                1 -> R.color.username_2
                2 -> R.color.username_3
                3 -> R.color.username_4
                4 -> R.color.username_5
                5 -> R.color.username_6
                6 -> R.color.username_7
                7 -> R.color.username_8
                else -> R.color.username_1
            }
        }

        @ColorRes
        private fun getColorFromRoomId(roomId: String?): Int {
            return when ((roomId?.toList()?.sumBy { it.toInt() } ?: 0) % 3) {
                1 -> R.color.avatar_fill_2
                2 -> R.color.avatar_fill_3
                else -> R.color.avatar_fill_1
            }
        }
    }
}
