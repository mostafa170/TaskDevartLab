package com.devartLab.task.ui.home

import com.devartLab.task.model.HomeResponseModel
import com.devartLab.task.networking.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepo()  {

    suspend fun getHomeData(): Flow<List<HomeResponseModel>?> {
        return flow {
            emit(ServiceBuilder.makeRetrofitService().requestHomeData("fDugPRZjtV-5cCxp9nOl3yWY6txqmQzGGY3K9YxLALRXqvIrs7eQHLUDtHFEWa2g7R4rcTU3BzXXimBQwi-kMdUGpBxCIf37m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnHwgRdEAEXEwBFgIMKZ4SRKTQyTqhL06JtZCkwibZaVLCR3JefkggWxkt495iubmY7-UTBW9Qr3JpT8_W0Wokr5yVj3D6BuB9Q"
                ,"Maw_sFS92XbEz7dXsHZgIhmlwPPObYAHe").body())
        }.flowOn(Dispatchers.IO)
    }
}