package com.machinetask.optisol.view.Fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.machinetask.optisol.Model.Feed
import com.machinetask.optisol.ViewModel.FeedViewModel
import com.machinetask.optisol.databinding.DialogAddBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.OffsetDateTime
import java.time.ZoneOffset


@AndroidEntryPoint
class BottomDialogFragment() : BottomSheetDialogFragment() {
    private lateinit var binding: DialogAddBinding
    private lateinit var userViewModel: FeedViewModel
    var feedData: Feed? = null


    companion object {
        private const val ARGUMENT_KEY = "argument_key"

        fun newInstance(argument: Feed): BottomDialogFragment {
            val fragment = BottomDialogFragment()
            val args = Bundle()
            args.putSerializable(ARGUMENT_KEY, argument)
            fragment.arguments = args
            fragment.feedData = args.getSerializable(ARGUMENT_KEY) as Feed
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        userViewModel = ViewModelProvider(this)[FeedViewModel::class.java]

        binding = DialogAddBinding.inflate(layoutInflater)

        binding.etFeedName.setText(feedData?.name)
        binding.checkboxLive.isChecked = feedData?.isLive == true
        binding.checkboxLive.isChecked
        if (feedData?.name != null) {
            binding.btnCreate.text = "Edit"
            binding.etFeedName.isEnabled=false
        }

        binding.btnCreate.setOnClickListener {
            setUserData()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    private fun setUserData() {
        val getName = binding.etFeedName.text.toString().trim()
        if (!TextUtils.isEmpty(getName)) {

            val utcDateTimeString = OffsetDateTime.now(ZoneOffset.UTC)
            println("UTC Date and Time: $utcDateTimeString")

            val user = Feed(
                name = getName,
                isLive = binding.checkboxLive.isChecked,
                dateTime = utcDateTimeString.toString()
            )
            if (feedData?.name!=null){
                userViewModel.update(
                    Feed(feedData!!.id,getName,
                        binding.checkboxLive.isChecked,feedData!!.dateTime)
                )
                dismiss()

            }else{
                userViewModel.insert(user)
                dismiss()
            }
        } else {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }

    }

}
