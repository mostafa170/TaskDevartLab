package com.devartLab.task.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.devartLab.task.R
import com.devartLab.task.databinding.FragmentHomeBinding
import com.devartLab.task.model.HomeResponseModel


class HomeFragment : Fragment(), HomeAdapter.OnHomeItemClicked {

    lateinit var binding : FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        init()
        handleObserver()
    }

    private fun handleObserver() {
        viewModel.homeList.observe(viewLifecycleOwner,{
            if(it.isNotEmpty()){
                binding.progressBar.visibility= View.INVISIBLE
                binding.recyOrders.adapter = HomeAdapter(it,this )
            }else{
                binding.tvOrderListEmpty.setVisibility(View.VISIBLE)
            }
        })
        binding.swipeRefreshLayout.setOnRefreshListener {
            refresh()
        }
    }

    private fun init() {
        viewModel.requestHomeData()
    }

    override fun onItemClicked(dataList: HomeResponseModel) {

        val bundle = bundleOf("address_form" to dataList.address_form
            , "address_to" to dataList.address_to
            ,"cost_order" to dataList.cost_order
            ,"customer_name" to dataList.customer_name
            ,"customer_rate" to dataList.customer_rate
            ,"detail_order" to dataList.detail_order
            ,"lat_form" to dataList.lat_form
            ,"lat_to" to dataList.lat_to
            ,"lng_form" to dataList.lng_form
            ,"lng_to" to dataList.lng_to
            ,"order" to dataList.order
            ,"phone" to dataList.phone
            ,"profile_img" to dataList.profile_img
            ,"date" to dataList.date)
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_detailsOrderFragment,bundle)
    }


    private fun refresh(){
        synchronized(this) {
            init()
            binding.swipeRefreshLayout.isRefreshing = false
            binding.progressBar.setVisibility(View.VISIBLE)
        }
    }
}