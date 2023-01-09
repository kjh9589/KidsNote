package com.kjs.kidsnote.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.kjs.kidsnote.R
import com.kjs.kidsnote.components.util.enums.ResType
import com.kjs.kidsnote.components.util.enums.ViewState
import com.kjs.kidsnote.components.util.interfaces.ViewUpdateListener

class StateResImageView(
    context: Context,
    attrs: AttributeSet?
): ImageFilterView(context, attrs), ViewUpdateListener {
    private var type: ResType = ResType.DRAWABLE
    private var state: ViewState = ViewState.INACTIVE
    private val attrId = R.styleable.StateResButtonViewAttrs

    private var inactiveDrawable: Drawable? = null
    private var activeDrawable: Drawable? = null

    private var inactiveRes: Int = -1
    private var activeRes: Int = -1


    init {
        setDefaultTypedArray(attrs)
    }

    private fun setDefaultTypedArray(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, attrId)

        inactiveDrawable = typedArray.getDrawable(R.styleable.StateResButtonViewAttrs_inactiveRes)
        activeDrawable = typedArray.getDrawable(R.styleable.StateResButtonViewAttrs_activeRes)

        if (typedArray.getBoolean(R.styleable.StateResButtonViewAttrs_isEnableInactive, false)) {
            disableInactive()
        } else {
            inactive()
        }

        typedArray.recycle()
    }

    // Drawable로 세팅하면 Drawable만 쓸 것
    // ResInt로 세팅하면 Res만 쓸 것

    fun setInactiveDrawable(drawable: Drawable?) {
        type = ResType.DRAWABLE
        inactiveDrawable = drawable

        if (isEnabled) {
            inactive()
        } else {
            disableInactive()
        }
    }

    fun setActiveDrawable(drawable: Drawable?) {
        type = ResType.DRAWABLE
        activeDrawable = drawable
    }

    fun setInactiveRes(res: Int) {
        type = ResType.RES
        inactiveRes = res

        if (isEnabled) {
            inactive()
        } else {
            disableInactive()
        }
    }

    fun setActiveRes(res: Int) {
        type = ResType.RES
        activeRes = res
    }

    private fun updateState() {
        when(state) {
            ViewState.INACTIVE -> {
                when(type) {
                    ResType.DRAWABLE -> {
                        setImageDrawable(inactiveDrawable)
                    }
                    ResType.RES -> {
                        setImageResource(inactiveRes)
                    }
                }
            }
            ViewState.ACTIVE -> {
                when(type) {
                    ResType.DRAWABLE -> {
                        setImageDrawable(activeDrawable)
                    }
                    ResType.RES -> {
                        setImageResource(activeRes)
                    }
                }
            }
        }
    }

    fun changeState() {
        state = if (state == ViewState.INACTIVE) {
            ViewState.ACTIVE
        } else {
            ViewState.INACTIVE
        }

        updateState()
    }

    fun getState(): ViewState = state

    fun getResType(): ResType = type

    override fun active() {
        state = ViewState.ACTIVE
        updateState()
    }

    override fun inactive() {
        state = ViewState.INACTIVE
        updateState()
    }

    override fun enableActive() {
        isEnabled = true
        active()
    }

    override fun disableInactive() {
        isEnabled = false
        inactive()
    }
}