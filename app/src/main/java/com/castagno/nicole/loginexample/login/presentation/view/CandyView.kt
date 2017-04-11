package com.castagno.nicole.loginexample.login.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutParams
import android.support.v7.widget.RecyclerView.LayoutParams.MATCH_PARENT
import android.support.v7.widget.RecyclerView.LayoutParams.WRAP_CONTENT
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.bindView
import com.castagno.nicole.loginexample.R
import java.lang.IllegalArgumentException

data class Candy(val name: String)

class CandyListView : RecyclerView {
    private val candyAdapter = CandyAdapter(
            listOf(
                    Candy("The colorful liquorice one"), Candy("Swedish fish"),
                    Candy("Swedish fish"), Candy("Swedish fish"),
                    Candy("Swedish fish"), Candy("Swedish fish"),
                    Candy("Swedish fish"), Candy("Swedish fish"),
                    Candy("Swedish fish"), Candy("Swedish fish"),
                    Candy("Swedish fish")
            ))

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(context, attributes, defStyle)

    init {
        adapter = candyAdapter
        layoutManager = GridLayoutManager(context, 2)
        addItemDecoration(CandyItemDecoration())
    }
}

class CandyView : RelativeLayout {
    private val nameView: TextView by bindView(R.id.text_name)

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(context, attributes, defStyle)

    init {
        inflate(context, R.layout.view_candy, this)
    }

    fun showCandy(candy: Candy) {
        nameView.text = candy.name
    }
}

class CandyAdapter(var candies: List<Candy>) : RecyclerView.Adapter<CandyAdapter.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_CANDY = 0
        private const val VIEW_TYPE_SNACK = 1
    }

    open class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    class CandyViewHolder(private val view: CandyView) : ViewHolder(view) {
        fun bindCandy(candy: Candy) {
            view.showCandy(candy)
        }
    }

    class SnackViewHolder(private val view: CandyView) : ViewHolder(view) {
        fun bindSnack(snack: Candy) {
            view.showCandy(snack)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            VIEW_TYPE_CANDY -> {
                val view = CandyView(parent.context).apply {
                    layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                    setBackgroundColor(Color.GREEN)
                }

                return CandyViewHolder(view)
            }
            VIEW_TYPE_SNACK -> {
                val view = CandyView(parent.context).apply {
                    layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                    setBackgroundColor(Color.RED)
                }

                return SnackViewHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val candy = candies[position]
        when (holder) {
            is CandyViewHolder -> holder.bindCandy(candy)
            is SnackViewHolder -> holder.bindSnack(candy)
            else -> throw IllegalArgumentException("Unknown view holder: $holder")
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            3 -> return VIEW_TYPE_SNACK
            else -> return VIEW_TYPE_CANDY
        }
    }

    override fun getItemCount() = candies.size
}

class CandyItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view)

        if (position == 0 || position == 1) {
            outRect.top = 8.dpToPx(parent.resources.displayMetrics)
        } else {
            outRect.top = 4.dpToPx(parent.resources.displayMetrics)
        }

        outRect.bottom = 4.dpToPx(parent.resources.displayMetrics)


        if (position.mod(2) == 0) {
            outRect.right = 4.dpToPx(parent.resources.displayMetrics)
            outRect.left = 8.dpToPx(parent.resources.displayMetrics)
        } else {
            outRect.right = 8.dpToPx(parent.resources.displayMetrics)
            outRect.left = 4.dpToPx(parent.resources.displayMetrics)
        }
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        (0..parent.childCount - 1).map { parent.getChildAt(it) }.forEach { childView ->
            canvas.drawLine(
                    childView.left.toFloat(),
                    childView.top.toFloat(),
                    childView.right.toFloat(),
                    childView.bottom.toFloat(),
                    Paint())
        }
    }
}

fun Float.dpToPx(displayMetrics: DisplayMetrics) = this * displayMetrics.density
fun Int.dpToPx(displayMetrics: DisplayMetrics) = this.toFloat().dpToPx(displayMetrics).toInt()
