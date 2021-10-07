package com.devartLab.task.ui.detailsOrder

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.devartLab.task.R
import com.devartLab.task.databinding.FragmentDetailsOrderBinding
import com.irozon.sneaker.Sneaker
import com.squareup.picasso.Picasso

class DetailsOrderFragment : Fragment() {

    lateinit var binding: FragmentDetailsOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater!!, R.layout.fragment_details_order, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        init()
        handleObserver()
    }

    private fun handleObserver() {
        binding.idNameSender.text = arguments?.getString("customer_name")
        binding.idFromCity.text = arguments?.getString("address_form")
        binding.idToCity.text = arguments?.getString("address_to")
        binding.idTvTimeTrip.text = arguments?.getString("date")
        binding.idTvPaymentTrip.text = arguments?.getString("cost_order")
        binding.idTvSizeTrip.text = arguments?.getString("order")
        binding.idDesTripTXT.text = arguments?.getString("detail_order")
        binding.tvCustomerRate.rating = arguments?.getFloat("customer_rate")!!

        Picasso.get()
            .load(arguments?.getString("profile_img"))
            .error(android.R.drawable.stat_notify_error)
            .into(binding.idSenderPhoto)

        binding.directions.setOnClickListener {
            val uri = ("http://maps.google.com/maps?saddr=" + arguments?.getString("lat_form") +
                    "," + arguments?.getString("lng_form") +
                    "&daddr=" + arguments?.getString("lat_to")
                    + "," + arguments?.getString("lng_to"))
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)

        }
        binding.imgCall.setOnClickListener {
            callPhoneNumber(arguments?.getString("phone"))
        }

        binding.btnAcceptShipment.setOnClickListener {
            showCodeShipmentDialog()
        }

    }

    private fun callPhoneNumber(mobile: String? = null) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.CALL_PHONE),
                        170
                    )
                    return
                }
            }
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$mobile")
            startActivity(callIntent)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun showCodeShipmentDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_code_shipment)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window = dialog.window
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        val BtnCode = dialog.findViewById<Button>(R.id.btn_codeShipment)
        val EdCode = dialog.findViewById<EditText>(R.id.ed_codeShipment_txt)
        BtnCode.setOnClickListener {
            if (EdCode.text.toString().equals("1945")) {
                showCostShipmentDialog()
                dialog.dismiss()
            } else {
                activity?.let { it1 ->
                    Sneaker.with(it1) // Activity, Fragment or ViewGroup
                        .setTitle("خطا!!")
                        .setMessage("كود الشحنة غير صحيح")
                        .sneakError()
                }
            }
        }
        dialog.show()
    }

    fun showCostShipmentDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_pay_shipment)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window = dialog.window
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        val BtnCost = dialog.findViewById<Button>(R.id.btn_acceptShipment)
        val EdCost = dialog.findViewById<EditText>(R.id.tv_noTripFees_txt)
        val TvCost = dialog.findViewById<TextView>(R.id.id_tv_payment_trip)
        TvCost.text = arguments?.getString("cost_order")
        val strs = TvCost.text.split(" ").toTypedArray()
        BtnCost.setOnClickListener {
            if (EdCost.text.toString().toInt() >= strs[0]!!.toInt()) {
                Navigation.findNavController(requireView()).navigate(R.id.action_detailsOrderFragment_to_homeFragment)
                dialog.dismiss()
            } else {
                activity?.let { it1 ->
                    Sneaker.with(it1) // Activity, Fragment or ViewGroup
                        .setTitle("خطا!!")
                        .setMessage("القيمة التي قمت بإدخالها اقل من المطلوب")
                        .sneakError()
                }
            }
        }
        dialog.show()
    }
}