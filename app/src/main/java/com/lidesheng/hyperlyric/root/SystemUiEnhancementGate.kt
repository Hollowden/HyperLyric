package com.lidesheng.hyperlyric.root

import com.lidesheng.hyperlyric.common.RootConstants

internal object SystemUiEnhancementGate {
    /** 通过超级岛长按临时暂停歌词显示与渲染，恢复系统默认灵动岛以降低功耗。 */
    @Volatile
    var suspendedByIslandLongPress: Boolean = false

    fun isBaseEnabled(): Boolean {
        val entry = HookEntry.instance ?: return false
        return runCatching {
            entry.prefs.getBoolean(
                RootConstants.KEY_HOOK_ENABLE,
                RootConstants.DEFAULT_HOOK_ENABLE
            )
        }.getOrDefault(false)
    }

    fun isEnabled(): Boolean = isBaseEnabled() && !suspendedByIslandLongPress
}
