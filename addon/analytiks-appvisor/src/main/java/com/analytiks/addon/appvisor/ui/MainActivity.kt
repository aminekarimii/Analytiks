package com.analytiks.addon.appvisor.ui

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.analytiks.addon.appvisor.R
import com.analytiks.addon.appvisor.databinding.ActivityVisorBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVisorBinding
    private val interceptor: LoggingAnalytiksInterceptor by lazy {
        initialize()
    }

    private val adapter: EventsAdapter by lazy {
        EventsAdapter()
    }
    private val appVisorDataController: AppVisorDataController by lazy {
        AppVisorDataController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.visorsRecyclerView.layoutManager = layoutManager
        binding.visorsRecyclerView.adapter = adapter

        appVisorDataController.events.observe(this) {
            adapter.addEvent(it.last())
        }

        binding.textView.setOnClickListener {
            appVisorDataController.addEvent("Event ${appVisorDataController.events.value?.size ?: 0}")
        }
    }

    companion object {

        fun initialize(): LoggingAnalytiksInterceptor {
            return LoggingAnalytiksInterceptor()
        }

        fun createShortcut(context: Context) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) {
                return
            }

            val shortcutManager = context.getSystemService<ShortcutManager>() ?: return
            if (shortcutManager.dynamicShortcuts.any { it.id == "APP_VISOR_ID" }) {
                return
            }

            val shortcut =
                ShortcutInfo.Builder(context, "APP_VISOR_ID")
                    .setShortLabel("Analytiks Visor")
                    .setLongLabel("Analytiks App visor - Analyze your app events")
                    .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                    .setIntent(getLaunchIntent(context).setAction(Intent.ACTION_VIEW))
                    .build()
            try {
                shortcutManager.addDynamicShortcuts(listOf(shortcut))
            } catch (e: IllegalArgumentException) {
                Log.d("ShortcutManagerTag", "ShortcutManager addDynamicShortcuts failed $e")
            } catch (e: IllegalStateException) {
                Log.d("ShortcutManagerTag", "ShortcutManager addDynamicShortcuts failed $e")
            }
        }

        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        }
    }
}