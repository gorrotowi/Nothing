package com.gorro.nothing

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.text.Text

@Suppress("FunctionNaming")
class NothingWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        NothingWidgetUI()
    }

    @Composable
    @Preview
    fun ContentPreview() {
        NothingWidgetUI()
    }

    @Composable
    private fun NothingWidgetUI() {
        Text(text = "Nothing")
    }
}

class NothingWidgetReceiver : GlanceAppWidgetReceiver(){
    override val glanceAppWidget: GlanceAppWidget
        get() = NothingWidget()
}
