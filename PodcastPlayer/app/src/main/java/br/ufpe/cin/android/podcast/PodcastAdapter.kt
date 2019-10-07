package br.ufpe.cin.android.podcast

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemlista.view.*
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_episode_detail.view.*
import kotlinx.android.synthetic.main.activity_main.view.*


class PodcastAdapter(private val podcastList: List<ItemFeed>) :
    RecyclerView.Adapter<PodcastAdapter.MyViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val itemList: View, val podcastList: List<ItemFeed>)  : RecyclerView.ViewHolder(itemList), View.OnClickListener {

        init {
            itemList.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            val intent = Intent(v.context, EpisodeDetailActivity::class.java)
            Toast.makeText(
                v.context,
                "Clicou no item da posição: $position",
                Toast.LENGTH_SHORT
            ).show()

            intent.putExtra("itemfeed", podcastList[position])
            v.context.startActivity(intent)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PodcastAdapter.MyViewHolder {
        // create a new view
        // nesse caso vai ser o itemlista
        val itemList = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemlista, parent, false)
        return MyViewHolder(itemList, podcastList)
    }

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
