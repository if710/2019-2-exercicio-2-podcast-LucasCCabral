package br.ufpe.cin.android.podcast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemlista.view.*

class PodcastAdapter(private val podcastList: List<ItemFeed>) :
    RecyclerView.Adapter<PodcastAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val itemList: View) : RecyclerView.ViewHolder(itemList)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PodcastAdapter.MyViewHolder {
        // create a new view
        // nesse caso vai ser o itemlista
        val itemList = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemlista, parent, false)

        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(itemList)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemList.item_title.text = podcastList[position].title
        holder.itemList.item_date.text = podcastList[position].pubDate
        //holder.itemList.item_action.text = podcastList[position].link
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = podcastList.size
}
