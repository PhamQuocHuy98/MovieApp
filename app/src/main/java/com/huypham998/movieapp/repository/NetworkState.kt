package com.huypham998.movieapp.repository



sealed class NetworkState{
    class Error : NetworkState()
    class Loading : NetworkState()

}