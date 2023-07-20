package com.example.day26api

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class ProductAdapter (private val context : Activity, private val productArrayList :List<Product> )
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.productTitle)
        val image = itemView.findViewById<ShapeableImageView>(R.id.productImage)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)

//        var title : TextView
//        var image : ShapeableImageView
//
//        init {
//            title = itemView.findViewById(R.id.productTitle)
//            image = itemView.findViewById(R.id.productImage)
//
//        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       val itemView = LayoutInflater.from(context).inflate(R.layout.eachitem,parent,false)
        return ProductViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.title.text = currentItem.title

         // image view : how to show image in image view if the image is in format of url
        // Use Third Part Library (Picasso)
        Picasso.get().load(currentItem.thumbnail).into(holder.image);
                        // from where we are picking image  // From where we have to push

        holder.ratingBar.rating = currentItem.rating.toFloat()
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

}
