package mike.machine.platziconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import mike.machine.platziconf.R
import mike.machine.platziconf.model.Speaker

class SpeakerAdapter(val speakerListener: SpeakerListener):RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {
    var listSpeaker = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker,parent,false))

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {
        val speaker = listSpeaker[position] as Speaker

        holder.tvSpeakerJob.text = speaker.jobtitle
        holder.tvSpeakerName.text = speaker.name

        Glide.with(holder.itemView.context) //Contexto
            .load(speaker.image) //URL de la imágen
            .apply(RequestOptions.circleCropTransform()) //Convertir imágen en circular
            .into(holder.ivSpeakerImage) //En dónde se debe colocar

        holder.itemView.setOnClickListener { speakerListener.onSpeakerCliked(speaker,position) }
    }

    override fun getItemCount()= listSpeaker.size
    fun udpateData(data : List<Speaker>){
        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val ivSpeakerImage = itemView.findViewById<ImageView>(R.id.ivItemSpeakerImage)
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvItemSpeakerName)
        val tvSpeakerJob = itemView.findViewById<TextView>(R.id.tvItemSpeakerJob)
    }

}