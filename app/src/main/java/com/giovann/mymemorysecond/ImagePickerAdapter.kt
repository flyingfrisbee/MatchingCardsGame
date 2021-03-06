package com.giovann.mymemorysecond

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.giovann.mymemorysecond.models.BoardSize
import kotlin.math.min

class ImagePickerAdapter(
    private val context: Context,
    private val imageUris: List<Uri>,
    private val boardSize: BoardSize,
    private val imageClickListener: ImageClickListener
) : RecyclerView.Adapter<ImagePickerAdapter.ViewHolder>() {

    companion object {
        private const val MARGIN_SIZE = 8
        private const val TAG = "ImagePickerAdapter"
    }

    interface ImageClickListener {
        fun onPlaceholderClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_image, parent, false)
        val cardHeight = parent.height / boardSize.getHeight() - (2 * MARGIN_SIZE)
        val cardWidth = parent.width / boardSize.getWidth() - (2 * MARGIN_SIZE)
        val cardSideLength = min(cardHeight, cardWidth)
        val layoutParams = view.findViewById<ImageView>(R.id.ivCustomImage).layoutParams
        layoutParams.height = cardSideLength
        layoutParams.width = cardSideLength
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < imageUris.size) {
            holder.bind(imageUris[position])
        }
        else {
            holder.bind()
        }
    }

    override fun getItemCount() = boardSize.getNumPairs()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivCustomImage = itemView.findViewById<ImageView>(R.id.ivCustomImage)

        fun bind(uri: Uri) {
            ivCustomImage.setImageURI(uri)
            ivCustomImage.setOnClickListener(null)
        }
        fun bind() {
            ivCustomImage.setOnClickListener {
                //launch intent select photos
                imageClickListener.onPlaceholderClicked()
            }
        }
    }

}
