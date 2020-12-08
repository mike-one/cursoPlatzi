package mike.machine.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_speakers.*
import mike.machine.platziconf.R
import mike.machine.platziconf.model.Speaker
import mike.machine.platziconf.view.adapter.SpeakerAdapter
import mike.machine.platziconf.view.adapter.SpeakerListener
import mike.machine.platziconf.viewmodel.SpeakersViewModel

class SpeakersFragment : Fragment(), SpeakerListener {
    private lateinit var  speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakersViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpeakersViewModel::class.java)
        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = speakerAdapter
        }

        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.listSpeaker.observe(this, Observer<List<Speaker>>{speaker ->
            speakerAdapter.udpateData(speaker)
        })
        viewModel.isLoading.observe(this,Observer<Boolean>{
            if (it != null)
                rlBaseSpeaker.visibility = View.INVISIBLE
        })
    }

    override fun onSpeakerCliked(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.sepakerDetailFragmentDialgo,bundle)

    }

}