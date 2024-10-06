package com.reactdialpad

import android.graphics.Color
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.ReactDialpadViewManagerInterface
import com.facebook.react.viewmanagers.ReactDialpadViewManagerDelegate

@ReactModule(name = ReactDialpadViewManager.NAME)
class ReactDialpadViewManager : SimpleViewManager<ReactDialpadView>(),
  ReactDialpadViewManagerInterface<ReactDialpadView> {
  private val mDelegate: ViewManagerDelegate<ReactDialpadView>

  init {
    mDelegate = ReactDialpadViewManagerDelegate(this)
  }

  override fun getDelegate(): ViewManagerDelegate<ReactDialpadView>? {
    return mDelegate
  }

  override fun getName(): String {
    return NAME
  }

  public override fun createViewInstance(context: ThemedReactContext): ReactDialpadView {
    return ReactDialpadView(context)
  }

  @ReactProp(name = "color")
  override fun setColor(view: ReactDialpadView?, color: String?) {
    view?.setBackgroundColor(Color.parseColor(color))
  }

  companion object {
    const val NAME = "ReactDialpadView"
  }
}
