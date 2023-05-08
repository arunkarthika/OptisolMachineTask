package com.machinetask.roomdatabasewithflow.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.machinetask.roomdatabasewithflow.Model.Feed
import com.machinetask.roomdatabasewithflow.ViewModel.UserViewModel
import com.machinetask.roomdatabasewithflow.databinding.DialogAddBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class BottomDialogFragment() : BottomSheetDialogFragment() {
    private lateinit var binding: DialogAddBinding
    private lateinit var userViewModel: UserViewModel
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


        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding = DialogAddBinding.inflate(layoutInflater)

        binding.etFeedName.setText(feedData?.name)
        binding.checkboxLive.isChecked = feedData?.isLive == true
        binding.checkboxLive.isChecked
        if (feedData?.name != null) {
            binding.btnCreate.text = "Edit"
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
            val user = Feed(
                getName,
                binding.checkboxLive.isChecked,
                dateTime = Calendar.getInstance().getTime().toString()
            )
            if (feedData?.name!=null){
                userViewModel.update(
                    Feed(  getName,
                        binding.checkboxLive.isChecked,feedData!!.dateTime)
                )
            }else{
                userViewModel.insert(user)
            }
        } else {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }

    }

}
