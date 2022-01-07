package com.josimar.sicredieventtest.databinding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.josimar.sicredieventtest.R
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.imageLoader(url: String?){
        url?.let {
            Glide.with(this)
                .load(url)
                .error(R.drawable.placeholder_image)
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun showView(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("formatDate")
    fun TextView.formatDate(date: Long) {
        val dateString: String = SimpleDateFormat("dd/MM/yyyy").format(Date(date))
        text = dateString
    }

    @JvmStatic
    @BindingAdapter("formatMoney")
    fun TextView.formatDate(money: Double) {
        val nomeyString: String ="R$ %.2f".format(money)
        text = nomeyString
    }

}