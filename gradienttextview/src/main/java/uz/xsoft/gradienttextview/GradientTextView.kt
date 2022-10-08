package uz.xsoft.gradienttextview

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat


class GradientTextView : AppCompatTextView {

    private var _startTextColor: Int = ContextCompat.getColor(context, R.color.colorStart)
    private var _endTextColor: Int = ContextCompat.getColor(context, R.color.colorEnd)

    var startColor: Int
        get() = _startTextColor
        set(value) {
            _startTextColor = value
            invalidateTextPaintAndMeasurements()
        }

    var endColor: Int
        get() = _endTextColor
        set(value) {
            _endTextColor = value
            invalidateTextPaintAndMeasurements()
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.GradientTextView, defStyle, 0)

        _startTextColor = a.getColor(R.styleable.GradientTextView_startColor, _startTextColor)
        _endTextColor = a.getColor(R.styleable.GradientTextView_endColor, _endTextColor)

        a.recycle()
        invalidateTextPaintAndMeasurements()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        //Setting the gradient if layout is changed
        if (changed) {
            paint.shader = LinearGradient(
                0f, 0f, width.toFloat(), height.toFloat(), startColor, endColor,
                Shader.TileMode.CLAMP
            )
        }
    }

    private fun invalidateTextPaintAndMeasurements() {
        paint.shader = LinearGradient(
            0f, 0f, width.toFloat(), height.toFloat(), startColor, endColor,
            Shader.TileMode.CLAMP
        )
    }
}